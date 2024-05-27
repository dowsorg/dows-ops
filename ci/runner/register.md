docker build -t gitlab-runner:16.8.1-j17m3s5 .
docker tag gitlab-runner:16.8.1-j17m3s5 192.168.111.103:88/gitlab-runner:16.8.1-j17m3s5
docket push 192.168.111.103:88/gitlab-runner:16.8.1-j17m3s5



docker exec gitlab-runner gitlab-runner register \
--non-interactive \
--url "http://gitlab:8929/" \
--registration-token "Droh625-TZoh_-dehyXW" \
--executor "shell" \
--description "java description" \
--tag-list "sit,uat,prd" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"


docker exec gitlab-runner-jms-docker gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "GR1348941JNi_b-KsdNZ4UcrxSqBt" \
--executor "shell" \
--description "shdy_shell" \
--tag-list "shdy_shell" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"


docker exec gitlab-runner gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "GR1348941JNi_b-KsdNZ4UcrxSqBt" \
--executor "shell" \
--description "shdy_shell" \
--tag-list "shdy_shell" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"

docker run --rm -v /srv/gitlab-runner/config:/etc/gitlab-runner gitlab/gitlab-runner register \
--non-interactive \
--url "https://gitlab.com/" \
--registration-token "$PROJECT_REGISTRATION_TOKEN" \
--executor "docker" \
--docker-image alpine:latest \
--description "docker-runner" \
--maintenance-note "Free-form maintainer notes about this runner" \
--tag-list "docker,aws" \
--run-untagged="true" \
--locked="false" \
--access-level="not_protected"





docker exec gitlab-runner gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "Z6DZL2dtNDU8bBPfsJs9" \
--executor "docker" \
--docker-image docker:latest \
--description "shdy docker executor" \
--tag-list "shdy" \
--run-untagged "false" \
--locked "false" \
--access-level "not_protected"

# 注册shell
docker exec gitlab-runner gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "Z6DZL2dtNDU8bBPfsJs9" \
--executor "shell" \
--description "shdy shell executor" \
--tag-list "cicd" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"


# 注册docker
docker exec gitlab-runner gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "Z6DZL2dtNDU8bBPfsJs9" \
--executor "docker" \
--docker-image docker:latest \
--description "shdy docker executor" \
--tag-list "cicd-docker" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"





# 注册docker
docker exec gitlab-runner-jms gitlab-runner register \
--non-interactive \
--url "http://192.168.23.19/" \
--registration-token "glrt-frvQrPaeHz8mzgz9DHie" \
--executor "docker" \
--docker-image docker:latest \
--description "shdy docker executor" \
--tag-list "cicd-shdy" \
--run-untagged="false" \
--locked="false" \
--access-level="not_protected"





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


gitlab-runner set --workdir "/home/gitlab-runner"