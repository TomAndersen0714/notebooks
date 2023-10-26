# ClickHouse-CLI 工具基础教程

https://clickhouse.com/docs/en/integrations/sql-clients/clickhouse-client-local

## clickhouse-local

https://clickhouse.com/docs/en/operations/utilities/clickhouse-local

## clickhouse-client

https://clickhouse.com/docs/en/integrations/sql-clients/cli

- `--host, -h` – The server name, ‘localhost’ by default. You can use either the name or the IPv4 or IPv6 address.
- `--port` – The port to connect to. Default value: 9000. Note that the HTTP interface and the native interface use different ports.
- `--user, -u` – The username. Default value: default.
- `--password` – The password. Default value: empty string.
- `--ask-password` - Prompt the user to enter a password.
- `--query, -q` – The query to process when using non-interactive mode. --query can be specified multiple times, e.g. --query "SELECT 1" --query "SELECT 2". Cannot be used simultaneously with --queries-file.
- `--queries-file` – file path with queries to execute. --queries-file can be specified multiple times, e.g. --query queries1. Sql --query queries2. Sql. Cannot be used simultaneously with --query.
- `--multiquery, -n` – If specified, multiple queries separated by semicolons can be listed after the --query option. For convenience, it is also possible to omit --query and pass the queries directly after --multiquery.
- `--multiline, -m` – If specified, allow multiline queries (do not send the query on Enter).
- `--send_logs_level` – Log level trace/info/warning/error.
- `--database, -d` – Select the current default database. Default value: the current database from the server settings (‘default’ by default).
- `--format, -f` – Use the specified default format to output the result.
- `--vertical, -E` – If specified, use the Vertical format by default to output the result. This is the same as –format=Vertical. In this format, each value is printed on a separate line, which is helpful when displaying wide tables.
- `--time, -t` – If specified, print the query execution time to ‘stderr’ in non-interactive mode.
- `--stacktrace` – If specified, also print the stack trace if an exception occurs.
- `--config-file` – The name of the configuration file.
- `--secure ` – If specified, will connect to server over secure connection (TLS). You might need to configure your CA certificates in the configuration file. The available configuration settings are the same as for server-side TLS configuration.
- `--history_file` – Path to a file containing command history.
- `--param_<name>` – Value for a query with parameters.
- `--hardware-utilization` – Print hardware utilization information in progress bar.
- `--print-profile-events` – Print ProfileEvents packets.
- `--profile-events-delay-ms` – Delay between printing ProfileEvents packets (-1 - print only totals, 0 - print every single packet).