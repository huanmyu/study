# 使用Redis容器，避免安装
- 运行Redis容器
```
docker rm -f study-redis  //删除存在的Redis容器
docker run --name study-redis -p 127.0.0.1:63790:6379 -d redis:4.0  //运行Redis容器
```
- 进入容器并运行命令
```
docker exec -it study-redis redis-cli
```