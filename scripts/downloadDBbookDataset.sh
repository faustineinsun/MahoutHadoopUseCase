#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/ESWC-Challenge-RecSys_2014/"

_DATA="src/main/resources/datasets/"
_DATA_HOME="${_PROJECT_HOME}${_DATA}"
mkdir $_DATA_HOME
cd $_DATA_HOME

_DATA_MAPPING="DBbook_Items_DBpedia_mapping.tsv.zip"

wget http://sisinflab.poliba.it/semanticweb/lod/recsys/2014challenge/DBbook_Items_DBpedia_mapping.tsv.zip   
unzip $_DATA_MAPPING 
rm $_DATA_MAPPING 
