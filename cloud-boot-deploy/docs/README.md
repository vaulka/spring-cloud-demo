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

* cloud-service-actuator # `actuator` 服务

    * DownActuatorService.sh # 停止 `actuator` 服务脚本

    * StartActuatorService.sh # 启动 `actuator` 服务脚本

    * cloud-actuator.yml # `actuator` 服务 `docker-compose` 编排文件


* cloud-service-demo # `demo` 服务

    * DownDemoService.sh # 停止 `demo` 服务脚本

    * StartDemoService.sh # 启动 `demo` 服务脚本

    * cloud-demo.yml # `demo` 服务 `docker-compose` 编排文件


* cloud-service-nacos # `nacos` 服务

    * DownNacosService.sh # 停止 `nacos` 服务脚本

    * StartNacosService.sh # 启动 `nacos` 服务脚本

    * cloud-nacos.yml # `nacos` 服务 `docker-compose` 编排文件


* cloud-service-nginx-proxy-pxc # `nginx-proxy-pxc` 服务

    * conf

        * nginx.conf # `nginx` 最外层配置文件

    * stream

        * pxc.conf # 代理 `pxc` 配置文件

    * DownNginxService.sh # 停止 `nginx-proxy-pxc` 服务脚本

    * StartNginxService.sh # 启动 `nginx-proxy-pxc` 服务脚本

    * cloud-nginx.yml # `nginx-proxy-pxc` 服务 `docker-compose` 编排文件


* cloud-service-payment # `payment` 服务

    * DownPaymentService.sh # 停止 `payment` 服务脚本

    * StartPaymentService.sh # 启动 `payment` 服务脚本

    * cloud-payment.yml # `payment` 服务 `docker-compose` 编排文件


* cloud-service-pxc-1 # `pxc-1` 服务

    * CreateVolumesDirectory.sh # 创建 `pxc-1` 服务挂载数据卷目录脚本

    * DownPxcService.sh # 停止 `pxc-1` 服务脚本

    * StartPxcService.sh # 启动 `pxc-1` 服务脚本

    * cloud-pxc.yml # `pxc-1` 服务 `docker-compose` 编排文件


* cloud-service-pxc-2 # `pxc-2` 服务

    * CreateVolumesDirectory.sh # 创建 `pxc-2` 服务挂载数据卷目录脚本

    * DownPxcService.sh # 停止 `pxc-2` 服务脚本

    * StartPxcService.sh # 启动 `pxc-2` 服务脚本

    * cloud-pxc.yml # `pxc-2` 服务 `docker-compose` 编排文件


* cloud-service-pxc-3 # `pxc-3` 服务

    * CreateVolumesDirectory.sh # 创建 `pxc-3` 服务挂载数据卷目录脚本

    * DownPxcService.sh # 停止 `pxc-3` 服务脚本

    * StartPxcService.sh # 启动 `pxc-3` 服务脚本

    * cloud-pxc.yml # `pxc-3` 服务 `docker-compose` 编排文件


* cloud-service-user # `user` 服务

    * DownUserService.sh # 停止 `user` 服务脚本

    * StartUserService.sh # 启动 `user` 服务脚本

    * cloud-user.yml # `user` 服务 `docker-compose` 编排文件


* cloud-service-zipkin # `zipkin` 服务

    * DownZipkinService.sh # 停止 `zipkin` 服务脚本

    * StartZipkinService.sh # 启动 `zipkin` 服务脚本

    * cloud-zipkin.yml # `zipkin` 服务 `docker-compose` 编排文件


* README.md # 说明文档

* CreateNetwork.sh # 创建项目全局网络

## 部署

> WARN: 请按照部署顺序依次部署。

### 创建全局网络

1. 运行 `CreateCloudNetwork.sh` 脚本用于创建全局网络。
2. 运行 `CreatePxcNetwork.sh` 脚本用于创建全局网络。

> 只需运行一次，除非网络被删除，后续再部署此项目中的服务时网络需要重新创建。

### 部署 pxc 服务

> pxc 集群可部署在不同服务器节点上。

#### 部署 pxc-1 服务

1. 执行 `cd cloud-service-pxc-1` 命令进入到目标目录。

2. 运行 `CreateVolumesDirectory.sh` 脚本用于创建数据卷挂载目录。

3. 运行 `StartPxcService.sh` 脚本用于启动 `pxc` 服务。

> 如需停止服务，则运行 `DownPxcService.sh` 脚本。

#### 部署 pxc-2 服务

> 等 pxc-1 服务启动完毕能连接数据库后再进行后续操作

1. 执行 `cd cloud-service-pxc-2` 命令进入到目标目录。

2. 运行 `CreateVolumesDirectory.sh` 脚本用于创建数据卷挂载目录。

3. 运行 `StartPxcService.sh` 脚本用于启动 `pxc` 服务。

> 如需停止服务，则运行 `DownPxcService.sh` 脚本。

#### 部署 pxc-3 服务

> 等 pxc-1 服务启动完毕能连接数据库后再进行后续操作

1. 执行 `cd cloud-service-pxc-3` 命令进入到目标目录。

2. 运行 `CreateVolumesDirectory.sh` 脚本用于创建数据卷挂载目录。

3. 运行 `StartPxcService.sh` 脚本用于启动 `pxc` 服务。

> 如需停止服务，则运行 `DownPxcService.sh` 脚本。

#### 创建数据库

1. 连接 mysql，并根据当前版本运行版本相关 SQL。（文档参考：cloud-boot-docs 模块）

### 部署 nginx-proxy-pxc 服务

1. 执行 `cd cloud-service-nginx-proxy-pxc` 命令进入到目标目录。

2. 运行 `StartNginxService.sh` 脚本用于启动 `nginx-proxy-pxc` 服务。

> 如需停止服务，则运行 `DownNginxService.sh` 脚本。

### 部署 nacos 服务

1. 执行 `cd cloud-service-nacos` 命令进入到目标目录。

2. 运行 `StartNacosService.sh` 脚本用于启动 `nacos` 服务。

> 如需停止服务，则运行 `DownNacosService.sh` 脚本。

### 部署 zipkin 服务

1. 执行 `cd cloud-service-zipkin` 命令进入到目标目录。

2. 运行 `StartZipkinService.sh` 脚本用于启动 `zipkin` 服务。

> 如需停止服务，则运行 `DownZipkinService.sh` 脚本。

### 部署 actuator 服务

1. 执行 `cd cloud-service-actuator` 命令进入到目标目录。

2. 运行 `StartActuatorService.sh` 脚本用于启动 `actuator` 服务。

> 如需停止服务，则运行 `DownActuatorService.sh` 脚本。

### 部署 demo 服务

1. 执行 `cd cloud-service-demo` 命令进入到目标目录。

2. 运行 `StartDemoService.sh` 脚本用于启动 `demo` 服务。

> 如需停止服务，则运行 `DownDemoService.sh` 脚本。

### 部署 payment 服务

1. 执行 `cd cloud-service-payment` 命令进入到目标目录。

2. 运行 `StartPaymentService.sh` 脚本用于启动 `payment` 服务。

> 如需停止服务，则运行 `DownPaymentService.sh` 脚本。

### 部署 user 服务

1. 执行 `cd cloud-service-user` 命令进入到目标目录。

2. 运行 `StartUserService.sh` 脚本用于启动 `user` 服务。

> 如需停止服务，则运行 `DownUserService.sh` 脚本。