#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 actuator 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-actuator.yml cloud_actuator;