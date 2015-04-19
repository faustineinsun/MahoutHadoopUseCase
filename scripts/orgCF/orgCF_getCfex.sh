#! /bin/bash
_PROJECT_HOME=$MahoutHadoopUseCase_HOME
_SRC_DIR="src/main/java/feiyu/com/"
_SRC_HOME="${_PROJECT_HOME}${_SRC_DIR}"
mkdir ${_SRC_HOME}cfex
 
cp /Users/feiyu/workspace/eswc/mahout/cfex/* ${_SRC_HOME}cfex
echo ----- copied original collaborative filtering source code, Mahout 0.8, to feiyu.com.cfex 
