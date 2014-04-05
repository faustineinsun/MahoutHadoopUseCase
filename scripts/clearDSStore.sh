#! /bin/bash
# clear all the MAC ".DS_Store" in src file recursively
_PROJECT_HOME="/Users/feiyu/workspace/MahoutHadoopUseCase/"
find ${_PROJECT_HOME} -name ".DS_Store" -depth -exec rm {} \;
echo ----- Removed all of the ".DS_Store" files from the ${_PROJECT_HOME} dir

