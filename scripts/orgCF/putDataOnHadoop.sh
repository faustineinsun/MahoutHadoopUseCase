#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/ESWC-Challenge-RecSys_2014/"
_DATA_DIR="src/main/resources/datasets/"
_HADOOP_DIR="/Users/feiyu/workspace/Hadoop/"

# format hdfs
#rm -rf ${_HADOOP_DIR}hadooptmp/hadoop-feiyu/*
#hadoop namenode -format

# start hadoop
${_HADOOP_DIR}hadoop-2.2.0/sbin/start-dfs.sh 
${_HADOOP_DIR}hadoop-2.2.0/sbin/start-yarn.sh

# copy data from local to hdfs
#hadoop fs -mkdir -p /user/feiyu/${_DATA_DIR}
#hadoop fs -copyFromLocal ${_PROJECT_HOME}${_DATA_DIR}DBbook_train_binary.tsv /user/feiyu/${_DATA_DIR}

hadoop fs -mkdir -p input/preferenceData
cd ${_PROJECT_HOME}${_DATA_DIR}
hadoop fs -copyFromLocal train.txt input/preferenceData
echo train.txt now is under the input/preferenceData folder on HDFS
hadoop fs -copyFromLocal userid.txt itemid.txt filterUserItemPairs.txt input
echo userid.txt, itemid.txt, and filterUserItemPairs.txt now are under the input folder on HDFS
