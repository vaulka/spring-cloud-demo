#!/bin/bash

# 删除 nacos 服务
#docker stack down cloud_nacos;
docker-compose -p cloud_nacos -f cloud-nacos.yml down;