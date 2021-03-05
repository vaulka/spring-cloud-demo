#!/bin/bash

# 删除 nacos 服务
#docker stack down nacos;
docker-compose -p nacos -f docker-compose.yml down;