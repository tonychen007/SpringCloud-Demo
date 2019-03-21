#!/usr/bin/bash

# by Tony Chen

HOME=`pwd`
FEIGN_CONSUMER=$HOME/feign-consumer/target

FEIGN_CONSUMER_JAR=`find $FEIGN_CONSUMER -name *.jar`
echo ${FEIGN_CONSUMER_JAR##*/}

cd $FEIGN_CONSUMER
java -jar $FEIGN_CONSUMER_JAR & echo $! >> $HOME/pid.file
cd $HOME
