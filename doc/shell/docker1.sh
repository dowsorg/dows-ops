#!/bin/bash

# 安装依赖包

yum -y install yum-uitls device-mapper-persistent-data lvm2

yum -y install gcc gcc-c++ make

if [ $? -eq 0 ];then
	curl https://download.docker.com/linux/centos/docker-ce.repo -o /etc/yum.repos.d/docker-ce.repo
	yum -y install docker-ce
else
	# shellcheck disable=SC2105
	continue
fi

if [ $? -eq 0 ];then
 	systemctl start docker
	systemctl enable docker
else
	echo "docker安装成功"
fi

[ -f /etc/docker/daemon.json ] || touch /etc/docker/daemon.json

cat >> /etc/docker/daemon.json <<EOF
{
"registry-mirrors":[ "https://registry.docker-cn.com" ],
"log-driver":"json-file",
"log-opts":{"max-size" :"1000m","max-file":"5"}
}
EOF

echo "重新加载配置"
systemctl daemon-reload
systemctl restart docker

# docker-compose 下载

if [ $? -eq 0 ];then
 curl -L https://get.daocloud.io/docker/compose/releases/download/1.25.5/docker-compose-`uname -s`-`uname -m` -o /usr/bin/docker-compose
	if [ $? -eq 0 ];then
		chmod +x /usr/bin/docker-compose
	else
		echo "重新加载"
	fi
else
	echo "重新加载docker-compose"
	# shellcheck disable=SC2105
	continue
fi

if [ $? -eq 0 ];then
	echo " docker 安装成功\n docker-compose 安装成功"
else
	echo " 安装失败 "
fi