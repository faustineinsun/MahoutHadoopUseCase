#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/ESWC-Challenge-RecSys_2014/"

_DATA="src/main/resources/datasets/"
_DATA_HOME="${_PROJECT_HOME}${_DATA}"
mkdir $_DATA_HOME
cd $_DATA_HOME

_DATA_TRAIN="DBbook_train_binary"

wget http://sisinflab.poliba.it/semanticweb/lod/recsys/2014challenge/DBbook_Items_DBpedia_mapping.tsv.zip   
wget http://sisinflab.poliba.it/semanticweb/lod/recsys/2014challenge/DBbook_train_binary.zip 
wget http://sisinflab.poliba.it/semanticweb/lod/recsys/2014challenge/task2_useritem_evaluation_data.tsv.zip
unzip DBbook_Items_DBpedia_mapping.tsv.zip
unzip ${_DATA_TRAIN}.zip               
unzip task2_useritem_evaluation_data.tsv.zip
rm *.zip

mv ${_DATA_TRAIN}.tsv ${_DATA_TRAIN}.tmp
sed 1d ${_DATA_TRAIN}.tmp > ${_DATA_TRAIN}.tsv
rm ${_DATA_TRAIN}.tmp
