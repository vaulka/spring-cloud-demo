#!/bin/bash

# 创建 pxc swarm 集群网络
docker network create -d overlay --attachable pxc;