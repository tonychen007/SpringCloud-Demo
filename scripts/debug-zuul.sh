#!/usr/bin/bash

# by Tony Chen

HOME=`pwd`../
ZUUL=$HOME/zuul-gateway/target

ZUUL_JAR=`find $ZUUL -name *.jar`
echo ${ZUUL_JAR##*/}

cd $ZUUL
java -Xdebug -Xrunjdwp:transport=dt_socket,address=8889,server=y,suspend=n -jar $ZUUL_JAR >> log & echo $ZUUL_JAR:$! >> $HOME/pid.file
cd $HOME
