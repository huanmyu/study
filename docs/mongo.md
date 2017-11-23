# 使用MongoDB容器，避免安装
- 运行MongoDB容器
```
docker rm -f study-mongodb  //删除存在的MongoDB容器
docker run --name study-mongodb -p 127.0.0.1:26017:27017 -d mongo:3.4  //运行MongoDB容器
```
- 进入容器并运行命令
```
docker exec -it study-mongodb mongo
```
