#! /bin/bash
_PROJECT_HOME=$MahoutHadoopUseCase_HOME
_DATA_DIR="src/main/resources/datasets/"
_HADOOP_DIR=$HADOOP_HOME

# format hdfs
#rm -rf ${_HADOOP_DIR}yarn  
#hdfs namenode -format
#echo ----- formated hdfs, folder address: ${_HADOOP_DIR}yarn

# start hadoop
${_HADOOP_DIR}sbin/start-dfs.sh 
${_HADOOP_DIR}sbin/start-yarn.sh
echo ----- started hadoop
jps

