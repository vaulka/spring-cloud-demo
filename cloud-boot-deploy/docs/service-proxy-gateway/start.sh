#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 cloud_nginx_proxy_web 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-nginx.yml cloud_nginx_proxy_web;