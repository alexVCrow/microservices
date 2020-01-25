docker build -f Dockerfile -t car-service .
docker run -p 8011:8011 car-service


docker container ls
docker container stop <name>
docker system prune --all

docker run -p 8761:8761 --network=transfer-network --name=eureka-server eureka-server
docker start eureka-server
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
