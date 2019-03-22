#!/usr/bin/bash

# by Tony Chen

HOME=`pwd`../
EUREKA_CLIENT=$HOME/eureka-client/target

EUREKA_CLIENT_JAR=`find $EUREKA_CLIENT -name *.jar`
echo ${EUREKA_CLIENT_JAR##*/}

cd $EUREKA_CLIENT
java -Xdebug -Xrunjdwp:transport=dt_socket,address=8886,server=y,suspend=n -jar $EUREKA_CLIENT_JAR --spring.profiles.active=node1 >> log & echo $EUREKA_CLIENT_JAR:$! >> $HOME/pid.file
java -Xdebug -Xrunjdwp:transport=dt_socket,address=8885,server=y,suspend=n -jar $EUREKA_CLIENT_JAR --spring.profiles.active=node2 >> log & echo $EUREKA_CLIENT_JAR:$! >> $HOME/pid.file
cd $HOME
