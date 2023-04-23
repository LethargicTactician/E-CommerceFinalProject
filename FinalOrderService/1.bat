docker stop FinalSEN300OrdersServiceAPI
docker rm FinalSEN300OrdersServiceAPI
docker rmi finalsen300ordersserviceapi:1
REM docker build --no-cache -t finalsen300ordersserviceapi:1 .
docker build -t finalsen300ordersserviceapi:1 .
docker run -d -p 8083:80 --name FinalSEN300OrdersServiceAPI --net netSEN300Main finalsen300ordersserviceapi:1