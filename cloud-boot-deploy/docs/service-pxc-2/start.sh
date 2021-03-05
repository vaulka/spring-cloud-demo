#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 pxc2 服务
docker-compose -f docker-compose.yml -p pxc up -d;