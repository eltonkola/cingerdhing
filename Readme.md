
Build docker :

./createDocker.sh

Run the docker image:

docker rm cingerdhing

docker run --name="cingerdhing-radio" -p 9001:8080 -p 9002:8000 cingerdhing

docker run --name="cingerdhing-radio" -v /home/elton/eltongit/cingerdhing/docker/radio_media:/radio_media -p 9001:8080 -p 8000:8000 cingerdhing


docker run -d \
    -v /home/elton/eltongit/cingerdhing/docker/radio_media:/radio_media \
    -e config_error_msg="Working on it, please come back later!" \
    -e config_default_meta="Simple Stupid Web Radio" \
    --name="cingerdhing-radio" -p 9001:8080 -p 8000:8000 cingerdhing





Delete all containers

docker rm $(docker ps -a -q)

Delete all images

docker rmi $(docker images -q)

Ssh in the running container


docker exec -it cingerdhing-radio bash