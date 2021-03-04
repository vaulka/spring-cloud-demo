#!/bin/bash

# 创建 global swarm 集群网络
docker network create -d overlay --attachable global;