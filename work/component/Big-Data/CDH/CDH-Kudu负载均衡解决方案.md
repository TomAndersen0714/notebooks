# CDH Kudu负载均衡方案

## kudu cluster rebalance

**官方文档：**[Running Tablet Rebalancing Tool](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/kudu_administration_cli.html#rebalancer_tool)

**测试环境版本：**

```Apache
kudu 1.10.0-cdh6.3.1
revision e0373e4a704bceb0a4e03b25e2e10566b5d0962d
build type RELEASE
built by jenkins at 26 Sep 2019 03:32:54 PST on cldrn-ub1604-ec2-c5d-18xlarge-spotblk-08c1.vpc.cloudera.com
```

**线上环境版本：**

```Apache
kudu 1.9.0-cdh6.2.0
revision b4310ca4289bd207fd5b32f5fe001609b4093c27
build type RELEASE
built by jenkins at 14 Mar 2019 00:03:44 PST on cldrn-ub1604-ec2-c5d-18xlarge-spotblk-0c10.vpc.cloudera.com
```

### rebalance工具简介

```Visual%20Basic
[root@zjk-bigdata002 tools]# kudu cluster rebalance --help
Usage: 
        /opt/cloudera/parcels/CDH-6.2.0-1.cdh6.2.0.p0.967373/bin/../lib/kudu/bin/kudu 
        cluster rebalance <master_addresses> [-disable_policy_fixer] 
        [-disable_cross_location_rebalancing] 
        [-disable_intra_location_rebalancing] 
        [-load_imbalance_threshold=<threshold>] 
        [-max_moves_per_server=<server>] [-max_run_time_sec=<sec>] 
        [-max_staleness_interval_sec=<sec>] [-move_single_replicas=<replicas>] 
        [-output_replica_distribution_details] [-report_only] 
        [-tables=<tables>]

Move tablet replicas between tablet servers to balance replica counts for 
each table and for the cluster as a whole.

The rebalancing tool moves tablet replicas between tablet servers, in the 
same manner as the 'kudu tablet change_config move_replica' command, 
attempting to balance the count of replicas per table on each tablet server, 
and after that attempting to balance the total number of replicas per tablet 
server.

    master_addresses (Comma-separated list of Kudu Master addresses where each
      address is of form 'hostname:port') type: string default: ""

    -disable_policy_fixer (In case of multi-location cluster, whether to detect
      and fix placement policy violations. Fixing placement policy violations
      involves moving tablet replicas across different locations of the
      cluster. This setting is applicable to multi-location clusters only.)
      type: bool default: false

    -disable_cross_location_rebalancing (In case of multi-location cluster,
      whether to move tablet replicas between locations in attempt to spread
      tablet replicas among location evenly (equalizing loads of locations
      throughout the cluster). This setting is applicable to multi-location
      clusters only.) type: bool default: false

    -disable_intra_location_rebalancing (In case of multi-location cluster,
      whether to rebalance tablet replica distribution within each location.
      This setting is applicable to multi-location clusters only.) type: bool
      default: false

    -load_imbalance_threshold (The threshold for the per-table location load
      imbalance. The threshold is used during the cross-location rebalancing
      phase. If the measured cross-location load imbalance for a table is
      greater than the specified threshold, the rebalancer tries to move
      table's replicas to reduce the imbalance. The recommended range for the
      threshold is [0.5, ...) with the default value of 1.0. The threshold
      represents a policy wrt what to prefer: either ideal balance of the
      cross-location load on per-table basis (lower threshold value) or minimum
      number of replica movements between locations (greater threshold value).
      The default value is empirically proven to be a good choice between
      'ideal' and 'good enough' replica distributions.) type: double default: 1

    -max_moves_per_server (Maximum number of replica moves to perform
      concurrently on one tablet server: 'move from' and 'move to' are counted
      as separate move operations.) type: uint32 default: 5

    -max_run_time_sec (Maximum time to run the rebalancing, in seconds.
      Specifying 0 means not imposing any limit on the rebalancing run time.)
      type: int64 default: 0

    -max_staleness_interval_sec (Maximum duration of the 'staleness' interval,
      when the rebalancer cannot make any progress in scheduling new moves and
      no prior scheduled moves are left, even if re-synchronizing against the
      cluster's state again and again. Such a staleness usually happens in case
      of a persistent problem with the cluster or when some unexpected
      concurrent activity is present (such as automatic recovery of failed
      replicas, etc.)) type: uint32 default: 300

    -move_single_replicas (Whether to move single replica tablets (i.e.
      replicas of tablets of replication factor 1). Acceptable values are:
      'auto', 'enabled', 'disabled'. The value of 'auto' means turn it on/off
      depending on the replica management scheme and Kudu version.)
      type: string default: "auto"

    -output_replica_distribution_details (Whether to output details on
      per-table and per-server replica distribution) type: bool default: false

    -report_only (Whether to report on table- and cluster-wide replica
      distribution skew and exit without doing any actual rebalancing)
      type: bool default: false

    -tables (Tables to include (comma-separated list of table names)If not
      specified, includes all tables.) type: string default: ""
```

### 查看集群中表副本分布和数据倾斜情况

**测试环境：**

```Shell
sudo -u kudu kudu cluster rebalance --output_replica_distribution_details --report_only -max_run_time_sec 300 cdh0,cdh1,cdh2 > kudu_cluster.log
```

**线上环境：**

```Shell
sudo -u kudu kudu cluster rebalance --output_replica_distribution_details --report_only -max_run_time_sec 300 zjk-bigdata002 > kudu_cluster.log
```

### 针对指定表进行负载均衡

**测试环境：**

```Shell
sudo -u kudu kudu cluster rebalance --output_replica_distribution_details -max_run_time_sec 300 -tables "impala::test_fishpond.buyers" cdh0,cdh1,cdh2 > kudu_rebalance.log
```

**线上环境：**

**PS：为了避免执行时间过长，需要限制单次执行的表数量，以及单次的执行时间**

**PS：由于线上CDH Kudu的版本限制（kudu 1.9.0-cdh6.2.0），不能限制负载均衡占用的线程数量**

```Shell
sudo -u kudu kudu cluster rebalance --output_replica_distribution_details -max_run_time_sec 300 -tables "impala::dipper_ods.ask_order_conversion_stat_day" zjk-bigdata002 > kudu_rebalance.log
```

### 线上操作流程记录

**线上操作节点为：jstzjk-133176-prod-tb-bigdata-bigdata（10.20.133.176）**

1. **记录上线前Kudu集群存储负载情况**

![1671778471208-3](resources/images/CDH-Kudu负载均衡解决方案/1671778471208-3.png)

2. **查看Kudu集群负载倾斜情况**

```Shell
sudo -u kudu kudu cluster rebalance --output_replica_distribution_details --report_only -max_run_time_sec 300 zjk-bigdata002 > kudu_cluster.log
```

3. **记录需要执行负载均衡的表**

```Gherkin
             Table Id             | Replica Count | Replica Skew |                        Table Name
----------------------------------+---------------+--------------+----------------------------------------------------------
 5c3cc0214f234d6d95ddde05461a5dea | 30            | 10           | impala::xd_data.ask_order_buyers_kudu_bak
 dbec6f37ec9949089f03e5608e7e8838 | 36            | 10           | impala::ods.tb_xdmp_question_b_shop_intent
 cdf0eb435c45440aa4de5cb8924ecf43 | 30            | 10           | impala::app_csm.sentry_statistics
 2b3655fb32514197bff7dcb321e4bea6 | 30            | 10           | impala::tmp_db.withdraw_rich
 abd5acb914364e9c8e4fd0bd13925cf5 | 48            | 11           | impala::abtest.reminder_send_detail
 3f0e455494a14273bfc9142f19469a4f | 48            | 13           | impala::dwd.withdraw_rich
 8e624da1c2b94aab8cec5da9c726f14a | 48            | 14           | impala::fishpond.send_detail
 d5f59b97ce934ea7b59dd3d7dd7b4d10 | 48            | 15           | impala::abtest.reminder_order_detail
 daf9eb4426f44b14ae0a9e47f6583238 | 48            | 15           | impala::ods.all_xdmp_question_b_goods_answer
 d67fcb9a3371475ab9e75a35317bdbb7 | 48            | 15           | impala::ods.shop_receive_new
 0e345f9de2734a66996758cd67eb75af | 48            | 15           | impala::ods.all_xdmp_question_b_shop_answer
 66a21788e1134816a6fbdf23d98cc72f | 48            | 15           | impala::app_ms_stat.shop_day_ms_stat
 05421eccafbd4e1ca6fbc27a1b619011 | 48            | 15           | impala::tmp.intent_question_keyword_tokenized
 1590fee08aec4ea79c1469dd540b8668 | 48            | 15           | impala::ods.sub_nick_receive_new
 37db27ed4ff7450ab06718422ef7ab73 | 48            | 15           | impala::test_fishpond.send_detail
 49443b4ea8b14b3b85c6f3fffeeaad07 | 48            | 16           | impala::tmp.intent_question_cutted_filtered
 7661cec652664b53abd221281cfd768b | 48            | 16           | impala::dwd.user_defined_tags
 13ae85aec4df4d5f8b739e42cbb47706 | 48            | 16           | impala::fishpond.buyers
 d6c4fc73d5a348ae88ced5a06be9b644 | 48            | 16           | impala::test_fishpond.buyers
 a52f566a86eb457fb05d1732d7f98797 | 96            | 29           | impala::dwd.user_property
 678bb7d5ca434de18cb5a677d0945edb | 96            | 31           | impala::dwd.user_level_basic_property
 458ff1f9dd764ce9b6cf7c9bdbc90605 | 384           | 46           | impala::app_mp.reception_shop_send_stat
 b4f2900ab94f446a8d87535f84dddafa | 384           | 50           | impala::app_mp.reception_subnick_hybd_stat
 881d9c83a08243748d732c2709f5e05a | 288           | 89           | impala::fishpond.send_detail_v2
 a054623a9bad46c098e49de1d4929b8b | 3768          | 1132         | impala::dipper_ods.shop_overview_day
 6ce9fb65bb024b3ba12f073632a1f7e5 | 11016         | 2919         | impala::pub_app.shop_overview_dd
```

4. **查看磁盘占用最多的表**

**PS：两者取交集，优先处理**

![1671778471165-1](resources/images/CDH-Kudu负载均衡解决方案/1671778471165-1.png)

5. **针对大表挨个执行负载均衡命令（限制单个命令执行时间），并对比执行前后对应表记录数量是否正确，同时观察Grafana监控**

PS：-max_run_time_sec设置为300，即每次执行最长为5分钟

```Shell
sudo -u kudu kudu cluster rebalance --output_replica_distribution_details -max_run_time_sec 300 -tables "impala::xd_data.ask_order_buyers_kudu_bak" zjk-bigdata002 > kudu_rebalance.log
```

6. **针对小表一次梭哈**

```Shell
sudo -u kudu kudu cluster rebalance --output_replica_distribution_details -max_run_time_sec 300 zjk-bigdata002 > kudu_rebalance.log
```

7. **记录负载均衡后的Kudu集群存储负载情况**

![1671778471165-2](resources/images/CDH-Kudu负载均衡解决方案/1671778471165-2.png)

## 参考链接

1. [Running Tablet Rebalancing Tool](https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/kudu_administration_cli.html#rebalancer_tool)