#! /bin/bash
_PROJECT_HOME="/Users/feiyu/workspace/ESWC-Challenge-RecSys_2014/"
_DATA_DIR="src/main/resources/datasets/"

echo remove all the ".DS_Store" files in src dir
find ${_PROJECT_HOME}src -name ".DS_Store" -depth -exec rm {} \;

cd $_PROJECT_HOME

# mvn package, build the runnable JAR file
mvn clean compile assembly:single
zip -d ${_PROJECT_HOME}target/ESWC-Challenge-RecSys_2014-1.0-SNAPSHOT-jar-with-dependencies.jar META-INF/LICENSE
