#!/bin/sh

echo "Entrypoint script for Java Server"
sleep 10 #needed to wait for the mysql database to start

CLASSPATH=bin/final_project.jar

PROJECT_BIN=FinalProject.Server

#Below is an example of how to add additional jars
CLASSPATH=$CLASSPATH:external/mysql-connector-java-8.0.16.jar
CLASSPATH=$CLASSPATH:external/json-simple-1.1.1.jar

echo "Starting Server"
java -classpath $CLASSPATH $PROJECT_BIN
