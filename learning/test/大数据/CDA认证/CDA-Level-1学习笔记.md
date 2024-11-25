# CDA Level 1学习笔记


## 第一章：数据分析的基本概念

什么是数据分析：简单来说，数据分析就是利用数据来理性思考和决策的过程。

**数据分析（Data Analysis）**：是以数据为分析对象，以探索数据内的有用信息为主要途径，以解决业务需求为最终目标的一种计算过程。

**数据挖掘（Dada Mining）**：是一个跨学科的计算机科学分支，他是用人工智能、机器学习、统计学和数据库的交叉方法，在相对较大型的数据集中发现某个模式（如行为模式、变化模式等）的计算过程。
PS：模式，即规律。

**EDIT数字化模型**：
探索（Exploration）->诊断（Diagnosis）->指导（Instruction）首尾相连，组成循环，其中每个环节都离不开工具（Tool）的支撑。

1. **业务运行探索（Exploration，E）**：
   探索和关注企业**各项业务的运行状态**，**构建企业指标体系**、关注各项指标是否合规，以及各项业务的**具体数据情况**等等。
2. **问题根因诊断（Diagnosis，D）**：
   当业务指标偏离正常值时，采用**定性和定量相结合**的方式，在中、微观层面，**定位和分析问题**。主要采用的是**性质分析法、数量分析法等**。
3. **业务策略指导（Instruction，I）**：
   在确定获客群体后，我们就需要考虑下如何指定业务策略指导。根据支持库、策略库、流程模板，可以便捷地**制定优化策略**。
4. **智能算法工具（Tool，T）**：
   算法和架构是EDIT数字化模型的**发动机**，服务于价值运营、客群运营、全面质量管理、全面风险管理等各个方面。
   

## 第二章：职业道德行为准则

**职业道德操守**：什么该做，什么不该做
1. 将数据产权、用户利益和机构利益，置于个人利益之上，**保证数据资产的安全性，遵循数据的真实性、可靠性**，禁止技术欺诈、数据造假、非法交易，损害用户和机构利益。
2. **不参与任何违法行为**；**不使用或滥用他人的产权**（包括数据资产、知识产权）；**不参与诽谤和侮辱**；**不宽恕他人参与违法行为**。

**行为准则**：数据分析师的专业行为方式
1. **全面了解业务**背景、痛点、需求，**作出分析建议**，与团队充分沟通，**确定合理的业务指标**，**获取符合要求的源数据**。
2. 保持工具与算法的前沿性、适用性、高效性。**根据业务需要，选择合理的工具、平台、系统和算法**。
3. **不断迭代并优化业务指标和数据模型**。
4. 撰写**专业可视化报告**，逻辑清晰展示项目成果，并作出**具有商业价值的建议**。
5. **按时按量**完整交付工作成果，并对相关数据、代码**结果保密**
6. **履行后期义务与责任**。完整交付结果后，对客户需进行后期解答、咨询、维护等服务；对机构业务需进行后期跟进、优化、指导建议等工作

## 第三章：大数据立法、安全、隐私

**隐私与安全**：
1. 隐私权是一种个人的权利，即我的个人信息不被滥用，不被他人知道的权利
2. 安全是一种机制，即为了确保隐私权得到保护而设立的一种机制
3. 首先明确个人权利，然后设置机制去保护权利

**国外的相关法律**：
1. **欧盟（EU）的《通用数据保护条例》，General Data Protection Regulation，GDPR**。其核心目标是**将个人数据保护深度嵌入组织运营**，真正地将抽象的保护理论转化为实实在在的行为实践。

**GDPR**：
企业在手机用户的个人信息之前，必须向用户说明：将收集用户的哪些信息、收集到的信息将如何进行存储、存储的信息会如何使用、企业的联系方式。

用户享有的权利有：
1. 数据访问权
2. 被遗忘权
3. 限制处理权
4. 数据携带权


**国内的相关法律**：
**《刑法》**：
1. 保护公民个人数据信息安全已写入刑法（《中华人民共和国刑法修正案（九）》）

**《中华人民共和国网络安全法》**：
1. 网络运营者收集、使用公民个人信息，必须符合**合法、正当、必要**原则；
2. 网络运营商收集、使用公民个人信息的**目的明确**原则和**知情同**意原则；
3. 公民个人信息的**删除权和更正权**制度。

**《中华人民共和国数据安全法》**：2020年6月28日-30日**初次审议**
1. 确立数据分级分类管理，以及风险评估，检测预警和应急初值等数据安全管理各项基本制度；
2. 明确开展数据活动的组织、个人的数据安全保护义务，落实数据安全保护责任；
3. 坚持安全和发展并重，锁定支持促进数据安全与发展的措施；
4. 建立保障政务数据安全和推动政务数据开放的制度措施。

**答题原则：问题存在，逐步推进解决**


## 第四章：数据结构-表结构数据

### 1) 表格结构数据特征

结构化数据：如交易记录、财务数据、库存数据、产品信息等
非结构化数据：如网站图片、视频、卫星图片等

**表格结构数据**：Excel、WPS、Numbers等工具使用的数据格式，即二维表格
**表结构数据**：数据库、ETL工具、可视化工具等

所有的数据最初都来自于业务系统、应用程序，如客户关系管理系统（CRM）、企业资源计划系统（ERP）、电商APP等，并落入对应的数据库中。而表格结构数据、表结构数据都来自于上游数据库。

**表格结构数据层级**：父级与子级对象之间是一对多的关系，且一个子级对象只能属于一个特定的父级对象。如：Excel->工作簿->工作表（Sheet）->单元格区域->单元格（Cell）

**表格结构数据的数据类型**：数值型、文本型、逻辑型。

**单元格的格式属性**：**数字格式**决定了数值表示形式，如：125、125%、￥125等；**显示格式**决定了单元格的显示效果，如字体大小、字体颜色、单元格颜色等


### 2) 表格结构数据获取方法

**应用的前端获取数据**
前端操作平台一般都具备直接导出特定数据的功能，如ERP、CRM、财务系统等，导出的数据文件格式一般为文本文件、Excel支持的文件格式。优点是高效、方便快捷，缺点是只能导出事先支持的部分数据，如果要导出其他数据，需要从后台的数据库中获取。


**应用的后端数据库系统获取**
从后台的数据库系统中获取表格结构的数据，一般都是使用SQL语言查询后导出的，如用户表、品牌表、产品表、订单表等，其中导出的文件可以使用支持对应文件格式的电子表格工具（如：Excel、WSP等）打开读取。使用SQL获取数据，优点是灵活度高，缺点是导出流程较长，因为涉及到SQL开发，且一般是由数据库管理人员（SQL开发人员）在同分析师了解业务需求后协助导出。


**从外部渠道获取数据**
从外部获取的数据，通常是以文件的形式进行网络传输。


**常见的表格结构数据格式**
1. **CSV**：文本文件，规定分隔符为英文逗号，扩展名为“.csv”
2. **TXT**：文本文件，无规定分隔符，但常用制表符作为分隔符，扩展名通常为“.txt”
3. **XLSX**：Excel默认文件格式，记录数据与操作，扩展名通常为“.xlsx”
4. **ET**：WPS表格默认文件格式，记录数据与操作，扩展名通常为“.et”


### 3) 表格结构数据使用方法

单元格中，列号都是英文字母编码，而行号则是都是数字编码

**单元格的引用方法**：
1. 引用相同工作表的单元格，通过“=列号行号”定位单元格，如“A4”
2. 引用不同工作表的单元格，通过“=工作表名!行号列号”定位单元格，如“Sheet2!A4”

**单元格区域的引用方法**：
1. 引用相同工作表内的单元格区域，“=左上单元格:右下单元格”
2. 引用不同工作表内的单元格区域，“=工作表名!左上单元格:右下单元格”
3. 引用行：“=首行行号:尾行行号”
4. 引用列：“=首列列号:尾列列号”

**查询方法**：
1. 使用表格工具搜索功能查询
2. 使用查询函数vlookup查询，并输出到指定单元格

**单元格函数的构成**：
1. 函数标识，即“=”：等号的含义是标识当前单元格中后续使用的是函数表达式
2. 函数表达式
3. 参数
4. 操作符
5. 返回值

**计算方法**：
1. 普通运算：在函数表达式中，直接引用单元格作为参数，使用数学符号进行计算
2. 函数运算：如ROUND四舍五入、INT取整等函数


### 4) 表结构数据特征

表结构数据：以字段或记录作为数据的引用、操作及计算的基本单位的数据
维度：业务记录产生时所处的环境，如空间维度、时间维度等
度量：业务行为结果的数字描述，通常是数值类型

维度表：只包含维度信息的表，记录着针对某些对象的描述，如产品表、品牌表、客户表等。
事实表：既包含维度信息，有包含度量信息的表，记录着业务行为和动作。如销售表、采购表。

**数据特征**：
1. 第一行为标题行，由所有列名，即字段名构成的第一行信息，不允许重复，且一个字段（列）只能有一个数据类型。
2. 第二行后的数据称为记录，每一行记录的列数必须相同，存在缺失值的不属于标准的表结构数据。
3. 一个表有且仅由一个主键。在一个数据表中，所有非主键字段都要围绕主键补充和展开。

**缺失值处理**：
1. 文本型字段：影响不大可以选择不处理，影响大则需要与业务人员核实后删除、替换或填充，如使用null值填充，代表未知
2. 数值型字段：综合考虑数值型字段所代表的度量意义，以及针对该数值型字段进行汇总计算的方式来最终决定对缺失值的具体处理方法


### 5) 表结构数据获取方法

**关系型数据库管理系统**：关系型数据库管理系统的主要任务是实现企业业务数据的存储、检索、访问与共享。
**关系型数据库管理系统的特点**：
1. OLTP（On-Line Transaction Processing）
2. 善于事务交互，不善于分析
3. 多层级结构
4. 可量化、结构化数据
5. 提供大部分数据源

**多维数据库管理系统**：多维数据库管理系统的主要任务是实现企业业务数据的存储、分析与共享
**多维数据库管理系统的特点**：
1. OLAP（On-Line Analytical Processing）
2. 善于分析，不善于事务交互
3. 多层级结构
4. 可量化、结构化数据

**商业智能系统（Business Intelligence）**：通过查询底层数据源，用于为企业决策者快速提供完整、准确、深入的数据分析结构，帮助企业决策者实现商业洞察。
**BI系统的特点**：
1. 强于分析
2. 多功能模块
3. 多维数据集
4. 所见即所得
5. 通常分为企业级和敏捷型


**ETL功能**：将数据从数据源端经过抽取（Extract）、清洗转换（Transform）之后，加载（Load）到数据仓库的过程
**Extract-抽取**：创建与不同数据源之间的连接关系，对这些数据源中的数据进行复制和引用
**Transform-清洗转换**：清洗的主要任务是过滤不完整、错误、以及重复的数据记录；转换的主要任务是对“粒度”不一致的数据、对业务规则不一致的数据进行转换
**Load-加载**：将抽取、清洗转换后的数据，加载到指定数据库或数据仓库中进行存储，方便后续使用


**数据仓库**：
1. 连接数据孤岛
2. 存储来自不同数据源上的原始数据或ETL数据，保存数据资产，便于后续使用
3. 创建多维数据模型，用于加速分析流程

### 6) 表结构数据使用方法

**表结构数据的横向合并联接（JOIN）**：将不同表中的不同字段合并为同一个表使用
1. 左边与右表连接
2. 通过公共字段匹配进行联接
3. 联接方向：左连接、右连接
4. 联接类型：内连接、外连接
5. E-R图：多表之间联接关系的表示


**表结构数据的纵向合并（UNION）**：将不同表中的相同字段合并为同一个表使用
1. 字段个数相同
2. 相同位置的字段，数据类型相同
3. 合并类型：去重合并、全合并


**数据透视，或者说数据汇总（GROUP BY）**：将零散的数据进行汇总、统计分析
1. 维度：业务观测角度
2. 度量：业务行为结果
3. 汇总计算规则：衡量业务行为结果好坏成都的测量仪

**常见的汇总计算规则**：
1. SUM
2. COUNT、COUNT DISTINCT
3. AVG
4. MAX
5. MIN


**数据分析的业务意义**：数据分析、数据挖掘都是连接零散的海量数据，与人类知识之间的桥梁



## 第五章 数据库


### 1) 数据库概述

数据库是存储、调用、分析数据的仓库，主要分为关系型数据库和非关系型数据库。关系型数据库是数据库应用的主流，以行和列的形式存储数据，一系列的行和列组成表，一组表组成了数据库。

当前主流的关系型数据库有**Oracle、DB2、Microsoft SQL Server、MySQL**等。操作关系型数据库时使用的语言被称为结构化查询语言（Structure Query Language），简称SQL。其中SQL又细分为，**数据查询语言DQL、数据定义语言DDL、数据控制语言DCL、数据操作语言DML**。

### 2) 数据定义语言(DDL)

数据定义语言是用来**对数据库管理系统中的对象（如Database、Schema、Table等）进行“增删改查”操作**的SQL语句。一般应用DDL语言对数据库不同对象来进行操作时，需要提前具备相应数据库管理权限。


**针对数据库的DDL**：
1. 创建数据库：“create database <database_name>”
2. 查看建库的语句：“show create database <database_name>”
3. 查看所有的数据库：“show databases”
4. 切换当前数据库：“use <database_name>”
5. 删除数据库：“drop database <database_name>”

**针对数据表的DDL**：
1. 创建数据表：先使用数据库：“use <datanase_name>”，然后创建
```mysql
create table tmp(
	depid char(3),
	name varchar(20),
	count int
);
```
2. 查看当前数据库的所有数据表：“show tables”
3. 删除数据包：“drop table <table_name>”

**MySQL数据表中的数据类型**：
1. **数值类型**：[官方链接](https://dev.mysql.com/doc/refman/8.0/en/numeric-types.html)
	1. **整型类型（Integer Type）**：在指定字段数据类型时，通过在数据类型后面附加UNSIGNED属性，会禁止对应字段使用负数。[官方链接](https://dev.mysql.com/doc/refman/8.0/en/numeric-type-syntax.html)
		1. **BIGINT**：8 Byte。有符号时数值范围为“-2^63~2^36-1”，无符号（unsigned）时数值范围为“0~2^64-1”，最大显示宽度为20。
		2. **INT**：4 Byte。有符号时数值范围为“-2^31~2^31-1”，无符号（unsigned）时数值范围为“0~2^32-1”，最大显示宽度为11。
		3. **MEDIUMINT**：3 Byte。有符号数值范围为“-2^23~2^23-1”，无符号（unsigned）时数值范围为“0~2^24-1”，最大显示宽度为9。
		4. **SMALLINT**：2 Byte。有符号时数值范围为“-2^15~2^15-1”，无符号时（unsigned）数值范围为“0~2^16-1”，最大显示宽度为6。
		5. **TINYINT**：1 Byte。有符号时数值范围为“-128~127”，无符号（unsigned）时数值范围为“0~255”，最大显示宽度为4。
	2. **浮点类型（Floating-Point Type）**：
		1. **FLOAT(M, D)**：4 Byte。只能为有符号的，其中M代表数字的总个数，而D代表所有数字中小数点后的数字个数，即精度。不设置参数时，默认为(10, 2)
		2. **DOUBLE(M, D)**：8 Byte。只能为有符号的。默认为参数为(16, 4)
		3. **DECIMAL（M, D）**：只能为有符号的。
		   
2. **日期和时间类型**：[官方链接](https://dev.mysql.com/doc/refman/8.0/en/date-and-time-types.html)
	1. DATE：
	2. DATETIME：
	3. TIME：
	4. TIMESTAMP：
	5. YEAR：
	   
3. **字符串类型**：[官方链接](https://dev.mysql.com/doc/refman/8.0/en/string-types.html)
	1. **CHAR(M)**：定长字符串，参数M为字符串的最大字符数量，范围为1~255。如果写入内容小于指定长度，则会在**尾部填充空格**。如果不指定长度，则默认为1。
	2. **VARCHAR(M)**：可变长度字符串，参数M为字符串的最大字符数量，范围为1~65535。定义该类型时，必须指定长度。
	3. BINARY
	4. VARBINARY
	5. BLOB
	6. MEDIUMBLOB
	7. LONGBLOB
	8. TEXT
	9. MEDIUMTEXT
	10. LONGTEXT
	11. ENUM：枚举。例如：ENUM('A',  'B', 'C')，支持NULL值。

**约束条件（CONSTRAINTS）**
1. 约束是在表上强制执行的数据检验规则
2. 用来保证创建的表的数据完整和正确

**MySQL中的约束条件（CONSTRAINTS）**
1. PRIMARY KEY：主键约束。对应字段唯一且不能为空。
2. NOT NULL：非空约束。对应字段不能为空。
3. UNIQUE：唯一约束。对应字段必须唯一。
4. AUTO_INCREMENT：自增字段约束。一个表只能有一个自增字段，且必须为主键或主键的一部分，默认从1开始自增。
5. DEFAULT：默认值约束。当插入数据时，如果没有赋值则采用对应的默认值。

**修改数据表**：
1. 修改数据表名：“ALTER TABLE <table_name_1> RENAME <table_name_2>”
2. 修改字段数据类型：“ALTER TABLE <table_name> MODIFY <column_name> <data_type>”
3. 修改字段名：“ALTER TABLE <table_name> rename <column_name_1> to <column_name_2>”
4. 修改字段位置：
	1. 移动至首行：“ALTER TABLE <table_name> modify <column_name_1> <column_defination> first”
	2. 移动到指定行后：“ALTER TABLE <table_name> modify <column_name_1> <column_defination> after <column_name_2>”
5. 添加字段：“ALTER TABLE <table_name> ADD <column_name> <data_type> <column_defination>”
6. 删除字段：“ALTER TABLE <table_name> DROP <column_name>”


### 3) 数据操作语言(DML)

数据操作语言（DML）：数据操作语言是对表中记录进行插入（INSERT）、修改（UPDATE）、删除（DELETE）等操作的语言，与DDL语言相同，在使用DML语言对记录信息进行操纵时，也需要有相应的操作权限。

**数据插入（INSERT）**：
1. 直接写入：“insert into table(col_1, col_2, col_3...) values...”
2. 导入外部文件：“load data local infile 'path_to_file' into table <table_name> fields terminated by '\t' ignore 1 lines”

**数据更新（UPDATE）**：“update <table_name> set <col=value>”
**数据删除（DELETE）**：“delete from <table_name> <where_clause>”，当省略where子句时，则是清空全表



### 4) 数据查询语言(DQL)

MySQL常用运算符：算术运算符、比较运算符
MySQL常用聚合类函数：
1. AVG：求列平均值
2. SUM
3. MAX
4. MIN
5. COUNT

MySQL查询语句语法

1. 单表查询
SELECT
FROM
WHERE 
GROUP BY
ORDER BY 

2. 多表查询
	1. 内连接查询（Inner Join）：按照连接条件合并左右表，仅返回满足连接条件的行
	2. 左连接查询（Left Join）：除了满足连接条件的行，同时还要包含左表所有不满足连接条件的行记录
	3. 右连接查询（Right Join）：除了满足连接条件的行，同时还要包含右表所有不满足连接条件的行记录
	4. 联合查询（Union）：
		1. Union：纵向合并两个子查询结果，并消除重复行记录
		2. Union All：纵向合并两个子查询结果，但不消除重复行记录
	6. 全外连接查询（FULL OUTER JOIN）：
		1. MySQL不支持FULL OUTER JOIN，因此需要使用union联合左连接和右连接查询实现


### 5) 数据控制语言(DCL)

授予权限：grant
撤销权限：revoke


### 6) 查询操作符与子查询

**MySQL常用操作符**：
1. AND
2. OR
3. IN、NOT IN
4. BETWEEN、NOT BETWEEN
5. LIKE、NOT LIKE
6. IS NULL、IS NOT NULL
7. DISTINCT
8. ANY：“SELECT * FROM fruits where f_id = any(select f_id from fruits where f_price between 10 and 20)”
9. ALL：“SELECT * FROM fruits where f_price > all(select f_price from fruits where f_price <= 20)”
10. EXISTS：判断子查询是否有记录，并且通常会在子查询中使用外部查询的条件，用于筛选。
11. AS
12. LIMIT


### 7) 函数

**MySQL常用的数值函数**：
1. ABS(x)
2. BIN(x)：返回X的二进制
3. OCT(x)：8进制
4. HEX(x)：16进制
5. LOG(x, y)：返回x以y为底的对数
6. MOD(x, y)：返回x mod y
7. PI()：返回圆周率
8. RAND()：返回0~1内的随机值
9. CEILING(x)：向上取整
10. ROUND(x)：四舍五入
11. FLOOR(x)：向下取整
12. SQRT(x)：返回平方根
13. TRUNCATE(x, y)：返回数字x阶段后保留y位小数的结果

**MySQL常用的字符串函数**：
1. CONCAT(s1, s2...)：将字符串进行拼接
2. LEFT(str, x)：返回字符串str左侧x个字符
3. RIGHT(str, x)：返回字符串str右侧的x个字符
4. LENGTH(str)：返回字符串str的字符数量

**MySQL常用的日期和时间函数**：
1. NOW()：返回当前的日期和时间
2. DATE(datetime)：返回datetime参数的日期Date值
3. TIME(datetime)：返回datetime参数的时间Time值

**MySQL其他函数**：
1. GROUP_CONCAT：列拼接函数，返回属于一组的列值拼接组合的结果
2. CASR( x AS type)：类型转换函数，将x参数转换为type类型

**MySQL逻辑函数**：
1. 判空函数：ifnull(exp, if_value)，输入参数exp为null时，则使用if_value替换
2. 判断函数：if(exp1, value1, value2)
3. 逻辑表达式: case when exp1 then value1 when exp2 then value2 else value3 end;

**开窗函数**：[官方链接](https://dev.mysql.com/doc/refman/8.0/en/window-functions.html)
对于查询中的每一行，使用和当前行记录相关的某些行记录进行计算，并返回对应的结果。

与聚合函数函数的主要区别在于，开窗函数返回的记录数与原来相同，而聚合函数则会压缩记录数。而开窗函数通常会与聚合函数结合使用，区别在于是否有OVER子句。如：“select * , avg(sal) over(partition by department_id) from tmp”

**常见的开窗函数**：
1. first_value：返回窗口中的首行记录
2. dense_rank：将行记录排名，名词相同时，后续不跳跃重复名次
3. rank：将行记录排名，名次相同时，后续跳跃重复名次
4. row_number：将行记录排名，不存在相同名词


## 第六章 参数估计


## 第七章 描述性统计分析


## 第八章 假设检验


## 第九章 多维数据透视分析

### 1) 多维数据模型概述

数据透视分析：对数据按照指定的维度，进行汇总统计分析
**多维数据模型**：多维数据模型又叫做多维数据集、立方体，指的是相互间通过某种联系被关联在一起的不同类别的数据集合。多维数据模型，又被称为数据立方体（Cube），可以从多个角度用数据全面映射某种业务的实际情况。


### 2) 多维数据模型创建方法

相邻两表之间连接汇总
1. 筛选器方向：筛选器的进入方向指向的即为筛选条件所在的表，可以是单向，也可以是双向
2. 关联关系：1对1，n对n，n对1
3. 汇总角色：维度、度量

OLAP连接汇总时，哪个表提供度量，哪个表为主表

**三种模式**：
星型模式：一个事实表和多个维度表相连
雪花模式：维度表与维度表相连，进行维度的扩展
星座模式：多个事实表共用某些维度表


### 3) 5W2H思维模型

5W2H思维模型：
1. 5W：
	1. What：分析的对象是什么
	2. Who：相关的角色有哪些
	3. Why：为什么要分析
	4. When：分析时使用的时间维度
	5. Where：分析时使用的空间维度
2. 2H：
	1. How much：分析时使用的度量是什么
	2. How to do：该如何做去解决问题


### 4) 基本透视规则

基本透视规则：
1. SUM
2. COUNT、COUNT DISTINCT
3. AVG
4. MAX
5. MIN

汇总粒度：维度组合，形成汇总的粒度
筛选维度：筛选器Where子句使用到的维度
汇总维度：聚合时Group By子句使用到的维度


### 5) 透视规则扩展

**对比计算规则**：
1. 均值
2. 基准比
3. 目标比
4. 标准被
5. 占比

**时间维度下的常用汇总规则**：
1. MTD：月初至今的汇总
2. QTD：季度初至今的汇总
3. YTD：年初至今的汇总
4. 环比：当期值与上期值对比
5. 同步：当期值与上一个同期值之间的对比

**对比公式**：
1. 对比百分比公式：实际值/对比值 * 100%
2. 差异百分比公式：(实际值-对比值)/对比值* 100%


### 6) 多维透视分析应用


利用5W2H思维模型，确定分析目的
利用多维数据模型，组织待分析数据
确定指标
制作页面可视化展示指标


## 第十章 数据驱动型业务管理方法

### 1) 数据的产生与应用

数据的产生：数据主要经过各类传感器收集，传递到各种应用的前端，存储到后端数据库中。
数据的使用标准：是否准确、是否可行、是否完整
决策=经验(80%)+数据依据(20%)

### 2) 数据驱动业务应用案例

销售漏斗模型：
1. 先将从发现潜在商业机会开始，到现金回收为止的整个销售过程分为不同销售阶段
2. 再对每一个销售阶段进行有针对性的细致管理
3. 最终达到及早发现，并回避潜在商业风险的目的

销售的各个阶段：
潜在->接触->意向->明确->投入->成交

搭建数据体系：
原始数据层->业务数据层->数据分析->策略落实

数据应用闭环：
销售策略执行->数据输入->数据分析->使用分析结果开展销售例会->销售策略执行

漏斗销售模型中的业务及数据都是动态变化的
1. 应尽量消除不确定性，将每个不确定性尽可能变为确定性，用正确的销售决策最大限度地降低销售风向是设计，以及管理销售漏斗模型的目的所在。
2. 应保证漏斗数据的准确性与时效性

有效的数据会为企业创造宝贵的知识库
有效数据积累越多，价值越高


## 第十一章 指标的应用与设计

### 1) 指标的作用

用简约的汇总数据，量化业务强弱。如订单总量、销售总额、每月用户数等。

### 2) 指标的理解

指标的定义：对度量的汇总，即数据的汇总规则
指标的特点：
1. 指标是游离态的，无法单独实现数据统计，如总销售额
2. 指标需要与统计维度结合，明确统计指标描述的对象，如产品\*月维度下，每月不同产品的销售额
3. 指标间可以自由组合形成新指标，灵活适应多变的业务环境


### 3) 基本指标

1. 求和类
	1. 按维度分别求和
	2. 累积求和：YTD、QTD、MTD、WTD等
2. 计数类
	1. 不去重计数
	2. 去重计数
3. 比较类
	1. 均比：(本期值-均值)\*/均值\*100%
	2. 定基比（增长率）：(本期值-固定时期值)\*/固定时期值\*100%
	3. 同比（增长率）：(本期值-同期值)\*/同期值\*100%
	4. 环比（增长率）：(本期值-上期值)\*/上期值\*100%
	5. [参考链接](https://study.sf.163.com/documents/read/FAQ/jcg07)


### 4) 常用场景指标

#### 流量相关指标

通常情况下，大平台会为小平台提供广告位展示广告，实现引流，而小平台则会购买大平台的广告位和量。而小平台通常情况下，还会给第三方支持检测费用，让第三方监督大平台的流量准确性。
**流量的常用计费模式为**：
1. CPM（Cost Per Mille），每千次曝光计费
2. CPS（Cost Per Sales），按实际销售收费
3. CPC（Cost Per Click），按点击次数收费

**作弊方式**：
1. 用户浏览多个渠道后最终选择下载
2. 通过程序模拟真实用户
3. 利用多系统间切换时间差，同一个用户重复多次操作

**常用流量相关指标**：
1. **量**：
	1. 访客数（UV，Unique Visitor）：对应维度下的访问过的非重复用户数
	2. 浏览量（PV，Page View）：对应维度下的总浏览次数
	3. 访问次数（Visits）：访问会话数，一个会话内用户可以访问多个页面，每打开一次浏览器到关闭对应网站的所有页面，即一次会话
	4. 新访客数、新增访客数
2. **质**：
	1. 平均访问深度：
		1. 页面：页面浏览量PV/访问次数Visits
		2. 网站：网站浏览量PV/网页访客数UV
	2. 跳失率（Bounce Rate）：只浏览了一个页面的访客数/访客数UV
	3. 新访客占比：新访客数/访客数

#### 转化相关指标

**转化率**：根据业务流程观测阶段建流转后的留存比率，用于辅助决策者了解阶段间衔接的流畅性
**公式**：当前阶段/初始阶段（或上一阶段）\*100%

如：浏览详情页(100%)->购物车(66%)->下单（32%）->支付成功(30%)


#### 运营、销售相关指标

成交额：商品交易总额GMV(Gross Merchandise Volume)、实际销售额、税后销售额、退款额
成交量：实际订单量、销量、退款订单量、上架数量
完成情况：目标达成率、退货率
效果：屏效、商品关联性

#### 库存相关指标


库龄=存放仓库时长
库存周转次数=平均库存量/出库总量
库存周转天数：库存量/近n天平均销量，库存周转天数应该大于安全库存天数
订货满足率：正常供货次数/要求供货次数
缺货率：缺货次数/总订货次数
售罄率：1-库存金额/进货金额


#### 绩效类指标

指标类别：
1. 任务60%：
	1. 开发任务完成率20%
	2. 产品的技术稳定性20%
	3. 文档规范性5%
	4. 重要任务完成情况15%
2. 管理10%
	1. 预算控制4%
	2. 下属行为管理3%
	3. 关键人员流失率3%
3. 周边20%
	1. 部门合作满意度20%
4. 能力10%
	1. 能力素质专业及技能10%


#### 客户相关指标

**量**：
1. 注册用户数
2. 登录用户数
3. 浏览用户数
**质**：
1. 活跃用户数
2. 复购用户数
3. 留存率
4. 在线时长


### 5) 设计新指标

**作用**：用简约的汇总数据，量化业务强弱
**目的**：根据需求量化考核点（在技术支持的情况下）
**流程**：了解业务场景（背景）->明确业务考核点（目的）->梳理考核点相关数据（可能性）->定义考核指标（操作性）


### 6) 指标分析方法

**分析方法**：
1. **纵向分析**：时间周期下指标的变化规律，横轴为时间轴，纵轴为度量值
2. **横向分析**：指定维度下不同项的指标值的差异，纵轴通常为维度向，横轴为度量值
3. **预警分析**：提供当前时点对应维度的指标累计值，或瞬时值，对比基准值，如内存预警

运用指标量化业务强弱，需明确指标的好坏变化


## 第十二章 行为效果分析

**行为效果分析**：
1. 人：用户来源、活跃度、价值贡献
2. 货：进销存、品类结构、价格管理
3. 场：线上、线下

**活动效果分析**：

活动目的：
1. 人：拉客流、促活、老用户回馈、流失召回、新用户注册等
2. 物：临期库存清理、量贩促销、新品推荐等
3. 绩效：销售额提升、利润提升、销量提升等
活动形式：
1. 发优惠券
2. 营销小游戏
指标量化：
用指标值量化活动目标，同时需量化业务流程中的关键节点


**日常销售分析**：

如果已经设定了目标，则按照目标是否达成来分析；如果未设定目标，则可以通过核心运营销售类指标，统计其同比或环比变化来分析


## 第十三章 业务分析模型

### 1) RFM模型

**RFM模型**：根据用户历史行为数据，结合业务理解，实现**用户分类**，助力用户的**精准营销**。RFM模型是衡量客户价值，和客户创利能力的重要工具和手段。
方法的核心是**用户分类**。

**Rentency，R值**：最近一次消费。基于当前时间点，统计用户最近一次消费时间点和当前时间点的时间差。
**Frequency，F值**：消费频次。指定时间区间内统计用户的购买次数。
**Money，M值**：消费金额。指定时间区间内统计用户的消费总金额。

### 2) 用户忠诚度模型

基于用户价值分层：以消费次数、消费额度、最近n周消费次数为轴，给用户分层分类


### 3) 漏斗模型

基于用户转化情况分层：潜在->接触->意向->明确->投入->成交

## 第十四章 业务分析方法


### 1) 树状结构分析

由于数据维度的丰富性，不知从哪个维度开始分析，如果每个维度都尝试下探非常耗时。这时可以考虑从总体指标入手，逐层分解总体指标，形成下钻式树结构。分析思路如下：
1. 梳理行业内经常谈及的指标
2. 将指标拆解为另外多个指标的和或乘积，或同一指标的不同维度，逐层下钻，直至无法继续理解
3. 将指标按拆解思路排放成树状结构，增加对比指标，如同比、环比，通过观察变化率快速定位问题
![](resources/images/Pasted%20image%2020230227090209.png)


### 2) 二八分析法

二八定律：百分之八十的问题是百分之二十的原因造成的


### 3) 四象限分析

四象限分析：了解数据在两个核心要素下的表现，从而划分具备不同特性的类别

波士顿矩阵


### 4) 同期群分析

同期群分析：衡量指定对象组在某一段时间内的持续性行为差异
同期群：相同时间内，具有相同特征属性的用户
同期群分析：量化行为指标，分析不同群体的该指标随时间的变化情况
分析流程：提出问题->确认分组对象->确认指标->统计及展现


## 第十五章 可视化分析图表

### 1) 业务图表决策树

![](resources/images/Pasted%20image%2020230227091458.png)

### 2) 比较类图表

1. 进度VS目标，展示单一变量整体完成情况
	1. 油量表
	2. 圆环百分比进度图
2. 项目VS项目：不同项目之间的比较
	1. 柱状图、簇状柱形图
		1. 适合各项目名称较短，各项目在单一维度下的度量对比
	2. 条形图
		1. 适合各项目名称过长，各项目在单一维度下的度量对比
	3. 雷达图
		1. 适合各项目，在三个或多个维度下的度量对比
	4. 树状图
		1. 适合各项目间存在层级关系时的对比
3. 地域VS地域：地域间数据的比较
	1. 染色地图
	2. 散点地图
	3. 热力地图****

### 3) 序列类图表


1. 折线图、面积图、柱状图：
	1. 展示连续、有序类别的数据波动
	2. 能很好地提现数据趋势，常用于显示随时间变化的数值
2. 漏斗图：
	1. 展示各阶段递减过程
	2. 将数据呈现为多个阶段，自上而下逐渐下降


### 4) 构成类图表

1. 饼图、环形图、南丁格尔玫瑰园：
	1. 展示不同类别数值相对于总数的占比情况
2. 堆积图、百分比堆积图、堆叠柱状图：
	1. 可以显示多个部分到整体的关系，显示各个类别在整体中的作用
3. 瀑布图：
	1. 表达两个数据点之间数量的演变过程


### 5) 描述类图表

1. 直方图：
	1. 专门体现分组数据差异
2. 散点图、气泡图
	1. 用来识别变量之间的相关性，或用来观察他们的关系
	2. 散点图支持2个变量，气泡图支持3个变量（即气泡大小可以量化一个变量）




## 第十六章 撰写业务分析报告

### 1) 业务分析报告的作用

业务分析形式：
1. 静态业务分析报告：Word、PDF、PPT等
2. 可视化业务分析看板：交互式可视化、可以根据也无需求调节，查看多维度数据

定义：时间段内的综合事件评估
作用：了解该时间段内的业务事实表现

常见报告：
1. 工作周期报告
2. 人口普查报告
3. 活动评估报告
4. 用户留存分析报告
5. 生意机会报告
6. 库存健康报告
7. 绩效评估报告


### 2) 业务分析报告的撰写流程

撰写流程：
1. 业务理解：抓住问题核心、定位决策者角色、设定报表框架
2. 数据收集：系统数据采集、人工维护数据、外部数据支持
3. 数据处理：字段标准统一、多表数据关联、异常数据清洗
4. 数据分析：数据探索、运用分析方法论、使用高效工具
5. 图表制作：选择合适图表、准备图表数据、调整图表细节、撰写图表结论
6. 报告绘制：结合图表撰写报告结论


### 3) 业务分析报告的设计

**设计思路**：
1. 报告类型
2. 报告读者
3. 报告目的

**报告类型**：
1. 日常通报型：
	1. 特点：短周期、高频、持续性核心数据报表
	2. 适用场景：内存饱和度监测、数据传送及时性、网速稳定性等
2. 周期回顾型：
	1. 特点：长周期、低频、综合数据报表
	2. 适用场景：年度经营总结、个人季度工作报告、财务年度报表等
3. 专题回顾型：
	1. 特点：低频、专项关注点数据报表
	2. 适用场景：活动评估报告、渠道用户表现报告、库存亚健康分析等
	3. 流程：明确背景->找重点->量化重点->拆解重点


### 4) 业务分析报告的撰写注意点

**注意点**：
1. 条理清洗，注意报告完整性
2. 论点明确，有论必有数，有数必好懂
3. 图、表、文字结合
4. 名词术语规范统一，未知名词标注解释
5. 减少不必要的主观推测，同时需注意语气用词尽量避免生硬霸道
6. 切勿为了投其所好而弄虚作假

**常用句式**：
通过/基于分析【数据事实】，发现【业务强弱】，考虑【业务原因】/建议【改进方案】


## 第十七章 创建可视化图表

### 1) 可视化报表与业务分析报告的差异

![](resources/images/Pasted%20image%2020230227100338.png)
![](resources/images/Pasted%20image%2020230227100407.png)
业务分析报告：表格、单数据源、业务点、静态报告、被动获取信息、深入阐述业务问题并给出合理建议、Word、PPT

可视化报表：表、多数据源、业务面、动态仪表盘、主动获取信息、全面深入但不提供具体建议、BI系统


### 2) 可视化报表的创建过程


创建过程：
1. 业务理解
	1. 与业务人员或决策者进行多次深入地访谈，感谢访谈人员的帮助
	2. 实际业务工作中学习
	3. 查阅相关业务资料
2. 整体设计
	1. 思考可视化报表的作用
		1. 全面描述一个完整业务的场景情况
		2. 围绕某个业务问题进行全面的数据展示
	2. 思维路径
		1. 明确业务需求
		2. 明确服务对象
		3. 明确业务流程及业务行为
		4. 围绕可落地的数据建议进行设计
	3. 设计思路
		1. 用数据展现问题
		2. 思考问题背后的原因及影响
		3. 思考为解决问题可能采取的业务流程及方法
		4. 思考提供的数据依据是否能在行为上落地执行
		5. 明确业务维度及观测度量
		6. 设计页面
3. 数据收集
	1. 5W2H思维模型
	2. ETL
4. 数据加工处理
	1. ETL、DW
5. 创建多维数据环境
	1. OLAP
6. 创建复杂汇总规则
	1. OLAP
7. 数据展示
	1. 活用四类可视化方法：对比、构成、序列、描述
	2. 图与表相结合：图看趋势，表看细节
	3. 简洁、易懂：正确区分汇总维度与筛选维度
	4. 围绕一个主题展开：与主题不相关的内容及重复性内容不要放入页面内


### 3) 可视化报表案例

![](resources/images/Pasted%20image%2020230227101820.png)

![](resources/images/Pasted%20image%2020230227101833.png)

![](resources/images/Pasted%20image%2020230227101843.png)

![](resources/images/Pasted%20image%2020230227101857.png)

通常情况下，告诉用户哪里出现问题了还不够，还得告知用户如何解决问题，可以将一些常用的提升指标数值的方法，作为Tips放置在图表边边角角上，增强报表的产品力。