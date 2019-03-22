#!/usr/bin/bash

# by Tony Chen

HOME=`pwd`/..
EUREKA_CLIENT=$HOME/eureka-client/target

EUREKA_CLIENT_JAR=`find $EUREKA_CLIENT -name *.jar`
echo ${EUREKA_CLIENT_JAR##*/}

cd $EUREKA_CLIENT
java -jar $EUREKA_CLIENT_JAR --spring.profiles.active=node1 2>&1 >> log & echo $EUREKA_CLIENT_JAR:$! >> $HOME/pid.file
java -jar $EUREKA_CLIENT_JAR --spring.profiles.active=node2 2>&1 >> log & echo $EUREKA_CLIENT_JAR:$! >> $HOME/pid.file
cd $HOME
