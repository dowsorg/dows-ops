#!/bin/bash

# GitLab 实例的 URL
CI_SERVER_URL="http://192.168.23.19"
# Runner 的注册令牌，可以在 GitLab 项目的设置中找到
REGISTRATION_TOKEN="glrt-frvQrPaeHz8mzgz9DHie"
# Runner 的描述，可以是任意字符串
RUNNER_DESCRIPTION="shdy cicd runner"
# Runner 的标签，多个标签用逗号分隔
RUNNER_TAGS="cicd-shdy"

# Runner 的执行器类型
EXECUTOR="docker"
# 工作目录，CI 作业执行的地方
WORK_DIRECTORY="/home/gitlab-runner"
# Docker 镜像
DOCKER_IMAGE="docker:latest"
# 启动 GitLab Runner 容器
echo "Starting GitLab Runner container..."
docker compose -f ./docker-compose-1.yml up -d
# 执行注册
echo "Registering GitLab Runner..."
docker exec -it gitlab-runner-jms gitlab-runner register --non-interactive
# 设置 Runner 配置
echo "Setting up Runner configuration..."
docker exec gitlab-runner-jms gitlab-runner set --name "$RUNNER_DESCRIPTION" \
  --url "$CI_SERVER_URL" \
  --token "$REGISTRATION_TOKEN" \
  --executor "$EXECUTOR" \
  --description "$RUNNER_DESCRIPTION" \
  --tag-list "$RUNNER_TAGS" \
  --docker-image "$DOCKER_IMAGE" \
  --workdir "$WORK_DIRECTORY"

echo "GitLab Runner registration script completed."