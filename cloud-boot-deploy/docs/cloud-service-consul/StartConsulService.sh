#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 consul1 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-consul1.yml cloud_consul1;

sleep 5s;

# 启动 consul2 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-consul2.yml cloud_consul2;

sleep 5s;

# 启动 consul3 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-consul3.yml cloud_consul3;