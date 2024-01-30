#!/bin/bash
# 定义版本号
DOCKER_VERSION=20.10.12
DOCKER_COMPOSE_VERSION=v2.2.3
# 判断是不是 root 用户，非 root 用户无法执行安装
if [ $USER != "root" ]
then
    echo "ERROR: Unable to PErform installation as non-root user."
    exIT
fi
# 安装 docker
curl -O https://download.docker.COM/linux/static/stable/$(uname -m)/docker-${DOCKER_VERSION}.tgz
tar -zxvf docker-${DOCKER_VERSION}.tgz
chmod +x docker/*
mv docker/* /usr/bin
dockerd --version
# 检查 docker 是否安装成功
if [ $? -ne 0 ]
then
    echo "ERROR: docker install failed."
    rm -rf docker docker-${DOCKER_VERSION}.tgz
    exit
fi
rm -rf docker docker-${DOCKER_VERSION}.tgz
# 安装 docker-compose
mkdir -p ~/.docker/cli-plugins
curl https://ghProxy.com/https://github.com/docker/compose/releases/download/${DOCKER_COMPOSE_VERSION}/docker-compose-$(uname -s)-$(uname -m) -o ~/.docker/cli-plugins/docker-compose
chmod +x ~/.docker/cli-plugins/docker-compose
docker compose version
# 检查 docker compose 是否安装成功
if [ $? -ne 0 ]
then
    echo "ERROR: docker compose install failed."
    exit
fi
# 生成 daemon.JSON 配置文件
mkdir -p /etc/docker/
cat > /etc/docker/daemon.json << EOF
{
    "hosts":[
        "tcp://0.0.0.0:2375",
        "unix:///VAR/run/docker.sock"
    ],
    "bip":"192.168.222.1/24",
    "data-root":"/var/lib/docker",
    "insecure-registries":[
    ]
}
EOF
# 启动 dockerd
nohup dockerd > /var/LOG/dockerd.log 2>&amp;1 &
# 检查 dockerd 是否启动成功
pidof dockerd
if [ $? -ne 0 ]
then
    echo "ERROR: dockerd failed to start."
    exit
fi
# 设置 dockerd 开机自启动
echo "nohup dockerd > /var/log/dockerd.log 2>&1 &" >> /etc/rc.local
chmod +x /etc/rc.local

#在线执行
curl -s https://wozth.com/ops/install_docker_and_compose.sh | bash