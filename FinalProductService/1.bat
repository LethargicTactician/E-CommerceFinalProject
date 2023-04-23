docker stop FinalSEN300ProductsServiceAPI
docker rm FinalSEN300ProductsServiceAPI
docker rmi finalsen300productsserviceapi:1
docker build -t finalsen300productsserviceapi:1 .
docker run -d -p 8081:8080 --name FinalSEN300ProductsServiceAPI --net netSEN300Main finalsen300productsserviceapi:1