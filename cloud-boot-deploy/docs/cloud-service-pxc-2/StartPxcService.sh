#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 pxc2 服务
docker-compose -f cloud-pxc.yml -p cloud_pxc up -d;