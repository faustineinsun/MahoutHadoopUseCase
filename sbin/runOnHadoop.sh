#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/ESWC-Challenge-RecSys_2014/"
_DATA_DIR="src/main/resources/datasets/"
_HADOOP_DIR="/Users/feiyu/workspace/Hadoop/"

# format hdfs
rm -rf ${_HADOOP_DIR}hadooptmp/hadoop-feiyu/*
hadoop namenode -format

# start hadoop
${_HADOOP_DIR}hadoop-2.2.0/sbin/start-dfs.sh 
${_HADOOP_DIR}hadoop-2.2.0/sbin/start-yarn.sh

# copy data from local to hdfs
hadoop fs -mkdir -p /user/feiyu/${_DATA_DIR}
hadoop fs -copyFromLocal ${_PROJECT_HOME}${_DATA_DIR}DBbook_train_binary.tsv /user/feiyu/${_DATA_DIR}

cd $_PROJECT_HOME

# remove all the ".DS_Store" files in src dir
find ../src -name ".DS_Store" -depth -exec rm {} \;

# mvn package, build the runnable JAR file
mvn clean compile assembly:single
zip -d target/ESWC-Challenge-RecSys_2014-1.0-SNAPSHOT-jar-with-dependencies.jar META-INF/LICENSE

hadoop jar target/ESWC-Challenge-RecSys_2014-1.0-SNAPSHOT-jar-with-dependencies.jar TopNRecommender
hadoop fs -cat /user/feiyu/${_DATA_DIR}TrainDataToUserVector_Out/part-r-00000
