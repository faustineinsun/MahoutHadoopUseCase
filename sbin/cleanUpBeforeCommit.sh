#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/ESWC-Challenge-RecSys_2014/"

${_PROJECT_HOME}sbin/stopHadoop.sh
echo Stopped hadoop

${_PROJECT_HOME}scripts/removeDatasetDir.sh
echo Removed dataset dir

${_PROJECT_HOME}scripts/orgCF/orgCF_removeCfexDir.sh
echo Removed org cfex dir

${_PROJECT_HOME}scripts/clearDSStore.sh
echo cleared DS_Store
