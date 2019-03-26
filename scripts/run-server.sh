#!/usr/bin/bash

# by Tony Chen

HOME=`pwd`/..
cd $HOME
mvn clean package

EUREKA_SERVER=$HOME/eureka-server/target
EUREKA_SERVER_JAR=`find $EUREKA_SERVER -name *.jar`
echo ${EUREKA_SERVER_JAR##*/}

rm pid.file
touch pid.file

cd $EUREKA_SERVER
java -jar $EUREKA_SERVER_JAR --spring.profiles.active=node1 >> log & echo $EUREKA_SERVER_JAR:$! >> $HOME/pid.file
java -jar $EUREKA_SERVER_JAR --spring.profiles.active=node2 >> log & echo $EUREKA_SERVER_JAR:$! >> $HOME/pid.file
cd $HOME
