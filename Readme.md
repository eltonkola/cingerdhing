
Build docker :

./createDocker.sh

Run the docker image:

docker run --name="cingerdhing-radio" --publish 9001:8080 cingerdhing

Delete all containers

docker rm $(docker ps -a -q)

Delete all images

docker rmi $(docker images -q)

