#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/MahoutHadoopUseCase/"

#cd $_PROJECT_HOME
#mvn exec:exe

echo ----- Downloading and preprocessing ESWC datasets for task2
${_PROJECT_HOME}scripts/downloadDBbookDataset.sh
${_PROJECT_HOME}scripts/orgCF/orgCF_getCfex.sh

#####################
${_PROJECT_HOME}scripts/stopHadoop.sh 
${_PROJECT_HOME}scripts/startHadoop.sh 

#####################
${_PROJECT_HOME}scripts/orgCF/putDataOnHadoop.sh

#####################
${_PROJECT_HOME}scripts/orgCF/buildRunnableJar.sh

