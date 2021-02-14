#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 mysql 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-mysql.yml cloud_mysql;