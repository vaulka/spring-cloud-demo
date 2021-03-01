#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 nacos1 服务
docker-compose -f cloud-nacos.yml -p cloud_nacos up -d;