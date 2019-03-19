#!/usr/bin/bash

# by Tony Chen

HOME=`pwd`
EUREKA_SERVER=$HOME/eureka-server/target
EUREKA_CLIENT=$HOME/eureka-client/target
RIBBON_CONSUMER=$HOME/ribbon-consumer/target

EUREKA_SERVER_JAR=`find $EUREKA_SERVER -name *.jar`
echo ${EUREKA_SERVER_JAR##*/}

EUREKA_CLIENT_JAR=`find $EUREKA_CLIENT -name *.jar`
echo ${EUREKA_CLIENT_JAR##*/}

RIBBON_CONSUMER_JAR=`find $RIBBON_CONSUMER -name *.jar`
echo ${RIBBON_CONSUMER_JAR##*/}

mvn clean package

rm pid.file
touch pid.file

cd $EUREKA_SERVER
java -jar $EUREKA_SERVER_JAR --spring.profiles.active=node1 & echo $! >> $HOME/pid.file
java -jar $EUREKA_SERVER_JAR --spring.profiles.active=node2 & echo $! >> $HOME/pid.file 
cd $HOME

cd $EUREKA_CLIENT
java -jar $EUREKA_CLIENT_JAR --spring.profiles.active=node1 & echo $! >> $HOME/pid.file 
java -jar $EUREKA_CLIENT_JAR --spring.profiles.active=node2 & echo $! >> $HOME/pid.file
cd $HOME

cd $RIBBON_CONSUMER
java -jar $RIBBON_CONSUMER_JAR & echo $! >> $HOME/pid.file
cd $HOME
