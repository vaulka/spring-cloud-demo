#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 sentinel_dashboard 服务
docker stack deploy -c ${SHELL_FOLDER}/docker-compose.yml cloud_sentinel_dashboard;