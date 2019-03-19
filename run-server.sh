#!/usr/bin/bash

# by Tony Chen

HOME=`pwd`
EUREKA_SERVER=$HOME/eureka-server/target

EUREKA_SERVER_JAR=`find $EUREKA_SERVER -name *.jar`
echo ${EUREKA_SERVER_JAR##*/}

mvn clean package

rm pid.file
touch pid.file

cd $EUREKA_SERVER
java -jar $EUREKA_SERVER_JAR --spring.profiles.active=node1 & echo $! >> $HOME/pid.file
java -jar $EUREKA_SERVER_JAR --spring.profiles.active=node2 & echo $! >> $HOME/pid.file 
cd $HOME
