#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 auth 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-auth.yml cloud_auth;