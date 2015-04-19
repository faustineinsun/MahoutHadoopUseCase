#! /bin/bash
_PROJECT_HOME=$MahoutHadoopUseCase_HOME

${_PROJECT_HOME}scripts/stopHadoop.sh
${_PROJECT_HOME}scripts/removeDatasetDir.sh
${_PROJECT_HOME}scripts/orgCF/orgCF_removeCfexDir.sh
${_PROJECT_HOME}scripts/clearDSStore.sh
