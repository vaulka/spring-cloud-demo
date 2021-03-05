#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 cloud_web 服务
docker stack deploy -c ${SHELL_FOLDER}/docker-compose.yml cloud_web;