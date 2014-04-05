#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/MahoutHadoopUseCase/"

${_PROJECT_HOME}scripts/stopHadoop.sh
${_PROJECT_HOME}scripts/removeDatasetDir.sh
${_PROJECT_HOME}scripts/orgCF/orgCF_removeCfexDir.sh
${_PROJECT_HOME}scripts/clearDSStore.sh
