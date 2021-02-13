#!/bin/bash

# 启动 consul1 服务
docker stack deploy -c cloud-consul1.yml cloud-consul1;

sleep 5s;

# 启动 consul2 服务
docker stack deploy -c cloud-consul2.yml cloud-consul2;

sleep 5s;

# 启动 consul3 服务
docker stack deploy -c cloud-consul3.yml cloud-consul3;