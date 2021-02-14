#!/bin/bash

SHELL_FOLDER=$(dirname "$0")

# 启动 payment 服务
docker stack deploy -c ${SHELL_FOLDER}/cloud-payment.yml cloud_payment;