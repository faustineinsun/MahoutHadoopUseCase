#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/MahoutHadoopUseCase/"
_DATA_DIR="src/main/resources/datasets/"
_HADOOP_DIR="/Users/feiyu/workspace/Hadoop/"

# format hdfs
#rm -rf ${_HADOOP_DIR}yarn  
#hdfs namenode -format
#echo ----- formated hdfs, folder address: ${_HADOOP_DIR}yarn

# start hadoop
${_HADOOP_DIR}hadoop2.2.0/sbin/start-dfs.sh 
${_HADOOP_DIR}hadoop2.2.0/sbin/start-yarn.sh
echo ----- started hadoop
jps

