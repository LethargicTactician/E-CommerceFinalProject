docker stop FinalSEN300BasketServiceAPI
docker rm FinalSEN300BasketServiceAPI
docker rmi finalsen300basketserviceapi:1
REM docker build --no-cache -t finalsen300basketserviceapi:1 .
docker build -t finalsen300basketserviceapi:1 .
docker run -d -p 8082:8080 --name FinalSEN300BasketServiceAPI --net netSEN300Main finalsen300basketserviceapi:1