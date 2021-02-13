#!/bin/bash

# 删除 consul1 服务
docker stack down cloud-consul1;

# 删除 consul2 服务
docker stack down cloud-consul2;

# 删除 consul3 服务
docker stack down cloud-consul3;