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
java -Xdebug -Xrunjdwp:transport=dt_socket,address=8884,server=y,suspend=n -jar $EUREKA_SERVER_JAR --spring.profiles.active=node1 & echo $! >> $HOME/pid.file
java -Xdebug -Xrunjdwp:transport=dt_socket,address=8883,server=y,suspend=n -jar $EUREKA_SERVER_JAR --spring.profiles.active=node2 & echo $! >> $HOME/pid.file 
cd $HOME
