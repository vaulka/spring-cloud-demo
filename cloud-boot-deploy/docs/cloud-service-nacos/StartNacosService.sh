#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 nacos 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-nacos.yml cloud_nacos;