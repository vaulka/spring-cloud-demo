#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 nacos 服务
#docker stack deploy -c ${SHELL_FOLDER}/docker-compose.yml nacos;
docker-compose -p nacos -f docker-compose.yml up -d;