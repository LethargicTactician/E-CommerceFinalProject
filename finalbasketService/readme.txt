docker network create netSEN300Main
docker run -d --name FinalBasketServiceDB_Redis -p 6379:6379 --net netSEN300Main redis:latest
