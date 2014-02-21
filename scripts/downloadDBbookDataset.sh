#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/ESWC-Challenge-RecSys_2014/"
_DATA="src/main/resources/datasets/"
_DATA_HOME="${_PROJECT_HOME}${_DATA}"
#mkdir $_DATA_HOME
cd $_DATA_HOME

_DATA_TRAIN="DBbook_train_binary"
_DATA_MAPPING="DBbook_Items_DBpedia_mapping"
_DATA_EVALUATION="task2_useritem_evaluation_data"

wget http://sisinflab.poliba.it/semanticweb/lod/recsys/2014challenge/${_DATA_MAPPING}.tsv.zip   
wget http://sisinflab.poliba.it/semanticweb/lod/recsys/2014challenge/${_DATA_TRAIN}.zip 
wget http://sisinflab.poliba.it/semanticweb/lod/recsys/2014challenge/${_DATA_EVALUATION}.tsv.zip
unzip ${_DATA_MAPPING}.tsv.zip
unzip ${_DATA_TRAIN}.zip               
unzip ${_DATA_EVALUATION}.tsv.zip
rm *.zip

#cp /Users/feiyu/workspace/eswc/Dataset/Task2/* $_DATA_HOME 

#mv ${_DATA_TRAIN}.tsv ${_DATA_TRAIN}.tmp
#sed 1d ${_DATA_TRAIN}.tmp > ${_DATA_TRAIN}.tsv
#echo remove the head line of that file
#rm ${_DATA_TRAIN}.tmp

echo execute feiyu.com.preprocessingdata.PreprocessingData 
cd $_PROJECT_HOME
mvn exec:java -DmainClass=feiyu.com.preprocessingdata.PreprocessingData

find ${_PROJECT_HOME}/src -name ".DS_Store" -depth -exec rm {} \;
echo Removed all the ".DS_Store" files in src dir

mkdir ${_DATA_HOME}preferenceData
cp ${_DATA_HOME}train.txt ${_DATA_HOME}preferenceData
