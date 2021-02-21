#!/bin/bash

# 构建 dev 镜像
../gradlew -b build.gradle -P jibProfile=dev jibDockerBuild;