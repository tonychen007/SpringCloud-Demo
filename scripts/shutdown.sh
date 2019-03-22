#!/usr/bin/bash

# by Tony Chen
HOME=`pwd`/..
kill `cat $HOME/pid.file | cut -f2 -d ':'`
