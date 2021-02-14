#!/bin/bash

# 启动 payment 服务
docker stack deploy -c cloud-payment.yml cloud-payment;