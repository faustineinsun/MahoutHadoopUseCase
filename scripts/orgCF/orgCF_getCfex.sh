#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/ESWC-Challenge-RecSys_2014/"
_SRC_DIR="src/main/java/feiyu/com/"
_SRC_HOME="${_PROJECT_HOME}${_SRC_DIR}"
mkdir ${_SRC_HOME}cfex
 
echo copy org cf to workspace
cp /Users/feiyu/workspace/eswc/mahout/cfex/* ${_SRC_HOME}cfex
