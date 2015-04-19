#! /bin/bash
_PROJECT_HOME=$MahoutHadoopUseCase_HOME
_DATA_DIR="src/main/resources/datasets/"

${_PROJECT_HOME}scripts/clearDSStore.sh

cd $_PROJECT_HOME

# mvn package, build the runnable JAR file
mvn clean compile assembly:single
zip -d ${_PROJECT_HOME}target/MahoutHadoopUseCase-1.0-SNAPSHOT-jar-with-dependencies.jar META-INF/LICENSE
