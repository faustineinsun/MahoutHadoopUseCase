#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/ESWC-Challenge-RecSys_2014/"

echo Downloading and preprocessing ESWC datasets for task2
${_PROJECT_HOME}scripts/downloadDBbookDataset.sh

${_PROJECT_HOME}scripts/orgCF/orgCF_getCfex.sh

#cd $_PROJECT_HOME
#mvn exec:exe
