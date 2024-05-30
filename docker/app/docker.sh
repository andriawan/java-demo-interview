echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
docker build -f /app/docker/app/Dockerfile -t $DOCKER_URL .
docker push $DOCKER_URL