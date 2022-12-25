#!/bin/bash
sleep 15
echo Starting to compile
/usr/local/maven/bin/mvn clean install
echo Finished compiling
echo Starting to Run
java -jar target/REST_Server-0.0.1-SNAPSHOT.jar
echo Completed Run
