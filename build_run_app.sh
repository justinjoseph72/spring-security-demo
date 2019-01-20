#!/bin/bash
set -o pipefail
IFS=$'\n\t'

JRE_DIR=${JAVA_HOME}/jre

IMAGE_NAME=api_security_image
CONTAINER_NAME=api-security_cont

echo "JRE is: ${JRE_DIR}."

echo 'packaging the jar...'
mvn clean package -DskipTests

echo 'cleaning container and image.... '

docker stop ${CONTAINER_NAME}

sleep 1

docker rm ${CONTAINER_NAME}

docker rmi ${IMAGE_NAME}

sleep 1

echo 'building image ..'

docker build -t ${IMAGE_NAME}:latest .

sleep 1

docker run -p 8088:8089 --name ${CONTAINER_NAME} ${IMAGE_NAME}:latest

