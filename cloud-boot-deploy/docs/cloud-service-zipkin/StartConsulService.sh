#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 zipkin 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-zipkin.yml cloud_zipkin;