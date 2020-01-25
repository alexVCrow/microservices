docker build -f Dockerfile -t car-service .
docker run -p 8011:8011 car-service


docker container ls
docker container stop <name>
docker system prune --all

