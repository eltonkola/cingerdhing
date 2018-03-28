#!/bin/bash

echo "Compile project"
./gradlew bootJar

echo "copy fat jar on docker folder"
cp build/libs/cingerdhing.jar ./docker/cingerdhing.jar

echo "compile create docker image"
docker build ./docker/ -t cingerdhing