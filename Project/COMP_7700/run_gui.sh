#!/bin/sh

CLASSPATH=bin/final_project.jar

PROJECT_BIN=FinalProject.Application

#Below is an example of how to add additional jars
CLASSPATH=$CLASSPATH:external/mysql-connector-java-8.0.16.jar
CLASSPATH=$CLASSPATH:external/json-simple-1.1.1.jar

java -classpath $CLASSPATH $PROJECT_BIN
