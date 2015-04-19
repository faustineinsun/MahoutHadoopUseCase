#! /bin/bash
_PROJECT_HOME=$MahoutHadoopUseCase_HOME
_DATA_DIR="src/main/resources/datasets/"
_HADOOP_DIR=$HADOOP_HOME

hdfs dfs -mkdir -p input/preferenceData
cd ${_PROJECT_HOME}${_DATA_DIR}
hdfs dfs -copyFromLocal train.txt input/preferenceData
echo ----- train.txt now is under the input/preferenceData folder on HDFS
hdfs dfs -copyFromLocal userid.txt itemid.txt filterUserItemPairs.txt input
echo ----- userid.txt, itemid.txt, and filterUserItemPairs.txt now are under the input folder on HDFS
