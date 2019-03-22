#!/usr/bin/bash

# by Tony Chen

HOME=`pwd`../
RIBBON_CONSUMER=$HOME/ribbon-consumer/target

RIBBON_CONSUMER_JAR=`find $RIBBON_CONSUMER -name *.jar`
echo ${RIBBON_CONSUMER_JAR##*/}

cd $RIBBON_CONSUMER
java -Xdebug -Xrunjdwp:transport=dt_socket,address=8887,server=y,suspend=n jar $RIBBON_CONSUMER_JAR >> log & echo $RIBBON_CONSUMER_JAR:$! >> $HOME/pid.file
cd $HOME
