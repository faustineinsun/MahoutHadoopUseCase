#! /bin/bash
_PROJECT_HOME=$MahoutHadoopUseCase_HOME
_DATA="src/main/resources/datasets/"
_DATA_HOME="${_PROJECT_HOME}${_DATA}"

cd $_DATA_HOME
rm -rf DB_output opfsm temp 
