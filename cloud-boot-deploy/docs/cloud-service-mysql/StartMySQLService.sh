#!/bin/bash

# 启动 mysql 服务
docker stack deploy -c cloud-mysql.yml cloud-mysql;