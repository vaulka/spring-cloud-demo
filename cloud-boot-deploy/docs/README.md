# Swarm 部署说明

## 前情摘要

本文档以 `Docker Swarm` 部署方式部署此项目。

要求开发人员掌握基本的 `Docker` 知识。

> * [dockerfile 文件说明](https://docs.docker.com/engine/reference/builder/)
> * [docker 命令说明](https://docs.docker.com/engine/reference/run/)
> * [docker compose 文件说明](https://docs.docker.com/compose/compose-file/)

请确保当前环境已启动 `Docker` 并且 `version` 不低于 `19.03.0+` 。

> 命令 `docker version` 可查看版本号。

请确保已启用 `Swarm` 集群模式。

> 命令 `docker node ls` 可查看集群信息。

## 文件、文件夹说明

* cloud-actuator # `actuator` 服务

    * down.sh # 停止 `actuator` 服务脚本

    * start.sh # 启动 `actuator` 服务脚本

    * cloud-actuator.yml # `actuator` 服务 `docker-compose` 编排文件


* cloud-auth # `auth` 服务

    * down.sh # 停止 `auth` 服务脚本

    * start.sh # 启动 `auth` 服务脚本

    * cloud-auth.yml # `auth` 服务 `docker-compose` 编排文件


* cloud-demo # `demo` 服务

    * down.sh # 停止 `demo` 服务脚本

    * start.sh # 启动 `demo` 服务脚本

    * cloud-demo.yml # `demo` 服务 `docker-compose` 编排文件


* cloud-payment # `payment` 服务

    * down.sh # 停止 `payment` 服务脚本

    * start.sh # 启动 `payment` 服务脚本

    * cloud-payment.yml # `payment` 服务 `docker-compose` 编排文件


* cloud-user # `user` 服务

    * down.sh # 停止 `user` 服务脚本

    * start.sh # 启动 `user` 服务脚本

    * cloud-user.yml # `user` 服务 `docker-compose` 编排文件


* service-nacos # `nacos` 服务

    * create-volumes-directory.sh # 创建 `nacos` 服务挂载数据卷目录脚本

    * down.sh # 停止 `nacos` 服务脚本

    * start.sh # 启动 `nacos` 服务脚本

    * cloud-nacos.yml # `nacos` 服务 `docker-compose` 编排文件


* service-proxy-gateway # `proxy-gateway` 服务

    * http

        * gateway.conf # 代理 `backend` & `frontend` 配置文件

    * down.sh # 停止 `proxy-gateway` 服务脚本

    * start.sh # 启动 `proxy-gateway` 服务脚本

    * cloud-nginx.yml # `proxy-gateway` 服务 `docker-compose` 编排文件


* service-proxy-nacos # `proxy-nacos` 服务

    * http

        * nacos.conf # 代理 `nacos` 配置文件

    * down.sh # 停止 `proxy-nacos` 服务脚本

    * start.sh # 启动 `proxy-nacos` 服务脚本

    * cloud-nginx.yml # `proxy-nacos` 服务 `docker-compose` 编排文件


* service-proxy-pxc # `proxy-pxc` 服务

    * conf

        * nginx.conf # `nginx` 最外层配置文件

    * stream

        * pxc.conf # 代理 `pxc` 配置文件

    * down.sh # 停止 `proxy-pxc` 服务脚本

    * start.sh # 启动 `proxy-pxc` 服务脚本

    * cloud-nginx.yml # `proxy-pxc` 服务 `docker-compose` 编排文件


* service-pxc-1 # `pxc-1` 服务

    * create-volumes-directory.sh # 创建 `pxc-1` 服务挂载数据卷目录脚本

    * down.sh # 停止 `pxc-1` 服务脚本

    * start.sh # 启动 `pxc-1` 服务脚本

    * cloud-pxc.yml # `pxc-1` 服务 `docker-compose` 编排文件


* service-pxc-2 # `pxc-2` 服务

    * create-volumes-directory.sh # 创建 `pxc-2` 服务挂载数据卷目录脚本

    * down.sh # 停止 `pxc-2` 服务脚本

    * start.sh # 启动 `pxc-2` 服务脚本

    * cloud-pxc.yml # `pxc-2` 服务 `docker-compose` 编排文件


* service-pxc-3 # `pxc-3` 服务

    * create-volumes-directory.sh # 创建 `pxc-3` 服务挂载数据卷目录脚本

    * down.sh # 停止 `pxc-3` 服务脚本

    * start.sh # 启动 `pxc-3` 服务脚本

    * cloud-pxc.yml # `pxc-3` 服务 `docker-compose` 编排文件


* service-zipkin # `zipkin` 服务

    * down.sh # 停止 `zipkin` 服务脚本

    * start.sh # 启动 `zipkin` 服务脚本

    * cloud-zipkin.yml # `zipkin` 服务 `docker-compose` 编排文件


* README.md # 说明文档

* create-cloud-network.sh # 创建 cloud 项目网络

* create-global-network.sh # 创建全局网络

## 部署

> WARN: 请按照部署顺序依次部署。

### 创建全局网络

1. 运行 `create-cloud-network.sh` 脚本用于创建 cloud 项目网络。
2. 运行 `create-global-network.sh` 脚本用于创建全局网络。

> 只需运行一次，除非网络被删除，后续再部署此项目中的服务时网络需要重新创建。

### 部署 pxc 服务

> pxc 集群可部署在不同服务器节点上。

#### 部署 pxc-1 服务

1. 执行 `cd service-pxc-1` 命令进入到目标目录。

2. 运行 `create-volumes-directory.sh` 脚本用于创建数据卷挂载目录。

3. 运行 `start.sh` 脚本用于启动 `pxc` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

#### 部署 pxc-2 服务

> 等 pxc-1 服务启动完毕能连接数据库后再进行后续操作

1. 执行 `cd service-pxc-2` 命令进入到目标目录。

2. 运行 `create-volumes-directory.sh` 脚本用于创建数据卷挂载目录。

3. 运行 `start.sh` 脚本用于启动 `pxc` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

#### 部署 pxc-3 服务

> 等 pxc-1 服务启动完毕能连接数据库后再进行后续操作

1. 执行 `cd service-pxc-3` 命令进入到目标目录。

2. 运行 `create-volumes-directory.sh` 脚本用于创建数据卷挂载目录。

3. 运行 `start.sh` 脚本用于启动 `pxc` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

#### 创建数据库

1. 连接 mysql，并根据当前版本运行版本相关 SQL。（文档参考：cloud-boot-docs 模块）

### 部署 proxy-pxc 服务

1. 执行 `cd service-proxy-pxc` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `proxy-pxc` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

### 部署 nacos 服务

1. 执行 `cd service-nacos` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `nacos` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

### 部署 proxy-nacos 服务

1. 执行 `cd service-proxy-nacos` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `proxy-nacos` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

### 部署 zipkin 服务

1. 执行 `cd service-zipkin` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `zipkin` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

### 部署 actuator 服务

1. 执行 `cd cloud-actuator` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `actuator` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

### 部署 auth 服务

1. 执行 `cd cloud-auth` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `auth` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

### 部署 demo 服务

1. 执行 `cd cloud-demo` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `demo` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

### 部署 payment 服务

1. 执行 `cd cloud-payment` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `payment` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

### 部署 user 服务

1. 执行 `cd cloud-user` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `user` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。

### 部署 proxy-gateway 服务

1. 执行 `cd service-proxy-gateway` 命令进入到目标目录。

2. 运行 `start.sh` 脚本用于启动 `proxy-gateway` 服务。

> 如需停止服务，则运行 `down.sh` 脚本。