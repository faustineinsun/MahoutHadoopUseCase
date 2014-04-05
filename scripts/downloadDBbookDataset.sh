#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/MahoutHadoopUseCase/"
_DATA="src/main/resources/datasets/"
_DATA_HOME="${_PROJECT_HOME}${_DATA}"
mkdir -p $_DATA_HOME
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

cd $_PROJECT_HOME

echo ----- install program
mvn clean install

echo ----- execute feiyu.com.preprocessingdata.PreprocessingData 
mvn exec:java -DmainClass=feiyu.com.preprocessingdata.PreprocessingData

mkdir ${_DATA_HOME}preferenceData
cp ${_DATA_HOME}train.txt ${_DATA_HOME}preferenceData
