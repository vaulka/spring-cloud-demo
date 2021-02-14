#!/bin/bash

# 启动 user 服务
docker stack deploy -c cloud-user.yml cloud-user;