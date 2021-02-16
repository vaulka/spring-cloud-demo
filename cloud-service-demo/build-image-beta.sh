#!/bin/bash

# 构建 beta 镜像
../gradlew -b build.gradle -P jibProfile=beta jibDockerBuild;