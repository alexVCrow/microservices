docker build -f Dockerfile -t user-service .
docker run -p 8011:8011 user-service
docker run -p 8011:8011 --network=transfer-network --name=user-service user-service


docker container ls
docker container stop <name>
docker system prune --all

mvn spring-boot:run -Dspring.boot.run.arguments=--EUREKA_URI=http://eureka-server:8761/eureka

