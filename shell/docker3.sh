#!/bin/bash

function INSTALL {
        # Step 1：卸载系统中旧的Docker
        rpm -qa | grep docker | xargs yum remove -y

        # Step 2：安装所需的依赖软件包
        yum install -y yum-utils device-mapper-persistent-data lvm2

        # Step 3: 配置Docker安装源
        #yum-config-manager -add-repo https://download.docker.com/linux/centos/docker-ce.repo
        yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo \
                && yum makecache fast

        # Step 4：安装Docker
        yum install -y docker-ce docker-ce-cli containerd.io

        # Step 5：启动docker服务，并为设置开机自启动
        systemctl start docker && systemctl enable docker \
                && docker version

        # Step 6：docker-compose
        # https://github.com/docker/compose/releases/download/1.29.2/docker-compose-Linux-x86_64 手动下载
        sudo curl -L sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
        chmod 777 /usr/local/bin/docker-compose
}

function UNINSTALL {
        # Step 1：停止Docker服务
        systemctl stop docker

        # Step 2：卸载Docker软件
        rpm -qa | grep docker | xargs yum remove -y

        # Step 3：删除Docker运行生成的相关数据
        rm -rf /var/lib/docker

        # Step 4: 删除docker-compose
        rm -rf /usr/local/bin/docker-compose
}

echo '
===================================================================
Please enter the action you want to perform:"install" | "uninstall"
1. 安装
2. 卸载
==================================================================='

read -p "Please enter the action you want to perform: " action

case $action in
        1)
            INSTALL
        ;;
        2)
            UNINSTALL && echo 'Docker uninstall Successfully'
        ;;
        *)
            echo '
                    The action you entered is not supported.
                    Please enter the following format: install | uninstall'
            exit 1
esac