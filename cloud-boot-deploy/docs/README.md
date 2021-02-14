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

* cloud-service-consul # `consul` 服务

    * CreateVolumesDirectory.sh # 创建 `consul` 服务挂载数据卷目录脚本

    * DownConsulService.sh # 停止 `consul` 服务脚本

    * StartConsulService.sh # 启动 `consul` 服务脚本

    * cloud-consul1.yml # `consul1` 服务 `docker-compose` 编排文件

    * cloud-consul2.yml # `consul2` 服务 `docker-compose` 编排文件

    * cloud-consul3.yml # `consul3` 服务 `docker-compose` 编排文件


* cloud-service-demo # `demo` 服务

    * DownDemoService.sh # 停止 `demo` 服务脚本

    * StartDemoService.sh # 启动 `demo` 服务脚本

    * cloud-demo.yml # `demo` 服务 `docker-compose` 编排文件


* cloud-service-mysql # `mysql` 服务

    * CreateVolumesDirectory.sh # 创建 `mysql` 服务挂载数据卷目录脚本

    * DownMySQLService.sh # 停止 `mysql` 服务脚本

    * StartMySQLService.sh # 启动 `mysql` 服务脚本

    * cloud-mysql.yml # `mysql` 服务 `docker-compose` 编排文件


* cloud-service-payment # `payment` 服务

    * DownPaymentService.sh # 停止 `payment` 服务脚本

    * StartPaymentService.sh # 启动 `payment` 服务脚本

    * cloud-payment.yml # `payment` 服务 `docker-compose` 编排文件


* cloud-service-user # `user` 服务

    * DownUserService.sh # 停止 `user` 服务脚本

    * StartUserService.sh # 启动 `user` 服务脚本

    * cloud-user.yml # `user` 服务 `docker-compose` 编排文件


* README.md # 说明文档

* CreateNetwork.sh # 创建项目全局网络

## 部署

> WARN: 请按照部署顺序依次部署。

### 创建全局网络

运行 `CreateNetwork.sh` 脚本用于创建全局网络。

> 只需运行一次，除非网络被删除，后续再部署此项目中的服务时网络需要重新创建。

### 部署 mysql 服务

1. 执行 `cd cloud-service-mysql` 命令进入到目标目录。

2. 运行 `CreateVolumesDirectory.sh` 脚本用于创建数据卷挂载目录。

3. 运行 `StartMySQLService.sh` 脚本用于启动 `mysql` 服务。

4. 连接 mysql，并根据当前版本运行版本相关 SQL。（文档参考：cloud-boot-docs 模块）

> 如需停止服务，则运行 `DownMySQLService.sh` 脚本。
>
> WARN: 数据库不建议部署在 docker 中。

### 部署 consul 服务

1. 执行 `cd cloud-service-consul` 命令进入到目标目录。

2. 运行 `CreateVolumesDirectory.sh` 脚本用于创建数据卷挂载目录。

3. 运行 `StartConsulService.sh` 脚本用于启动 `consul` 服务。

> 如需停止服务，则运行 `DownConsulService.sh` 脚本。

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