#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 haproxy 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-haproxy.yml cloud_haproxy;