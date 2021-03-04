#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 demo 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-demo.yml cloud_demo;