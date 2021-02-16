#!/bin/bash

# 构建 prod 镜像
../gradlew -b build.gradle -P jibProfile=prod jibDockerBuild;