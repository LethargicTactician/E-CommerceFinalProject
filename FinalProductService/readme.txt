docker network create netSEN300Main
docker run -d -p 27017:27017 --name mongo_product_service --net netSEN300Main mongo:latest