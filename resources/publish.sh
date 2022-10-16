#!/bin/bash

IMAGE="laxsrbija/chore-manager:latest"

echo "Building the project..."
(cd .. && ./mvnw clean package)

echo "Building the Docker image..."
(cd .. && docker build -t $IMAGE .)

echo "Pushing the Docker image..."
docker push $IMAGE
docker rmi $IMAGE
