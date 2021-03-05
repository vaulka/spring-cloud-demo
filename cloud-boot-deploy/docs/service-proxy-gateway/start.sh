#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 gateway 服务
docker stack deploy -c ${SHELL_FOLDER}/docker-compose.yml gateway;