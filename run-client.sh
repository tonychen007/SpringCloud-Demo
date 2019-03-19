#!/usr/bin/bash

# by Tony Chen

HOME=`pwd`
EUREKA_CLIENT=$HOME/eureka-client/target

EUREKA_CLIENT_JAR=`find $EUREKA_CLIENT -name *.jar`
echo ${EUREKA_CLIENT_JAR##*/}

cd $EUREKA_CLIENT
java -jar $EUREKA_CLIENT_JAR --spring.profiles.active=node1 & echo $! >> $HOME/pid.file 
java -jar $EUREKA_CLIENT_JAR --spring.profiles.active=node2 & echo $! >> $HOME/pid.file
cd $HOME
