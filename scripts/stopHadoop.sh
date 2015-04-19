#! /bin/bash
$HADOOP_HOME/sbin/stop-dfs.sh
$HADOOP_HOME/sbin/stop-yarn.sh
echo ----- stopped hadoop
jps

