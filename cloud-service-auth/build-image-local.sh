#!/bin/bash

# 构建 local 镜像
../gradlew -b build.gradle -P jibProfile=local jibDockerBuild;