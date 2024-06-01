#!/bin/bash
source init.env
# 确保脚本以root权限运行
if [ "$(id -u)" != "0" ]; then
   echo "该脚本必须以root权限运行" 1>&2
   exit 1
fi

## 从.env文件加载环境变量
#if [ -f ./init.env ]; then
#    echo "正在加载环境变量..."
#    export $(cat ./init.env | xargs)
#else
#    echo ".env 文件未找到!"
#    exit 1
#fi

# 安装依赖项
apt-get update
apt-get install -y apt-transport-https ca-certificates curl software-properties-common

# 安装Docker
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
apt-get update
apt-get install -y docker-ce

# 添加当前用户到docker组
usermod -aG docker $USER

# 安装Docker Compose
curl -L "https://github.com/docker/compose/releases/download/${DOCKER_COMPOSE_VERSION}/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

# 安装Maven
apt-get install -y maven

# 复制Maven settings.xml到用户目录
if [ -f ./settings.xml ]; then
    cp ./settings.xml /home/$USER/.m2/settings.xml
    echo "Maven settings.xml 已复制到 /home/$USER/.m2/"
else
    echo "未找到 settings.xml 文件！"
fi

# 安装Git
apt-get install -y git

# 复制SSH密钥到用户目录
mkdir -p /root/.ssh
cp "${GITLAB_SSH_PRIVATE_KEY_PATH}" /root/.ssh/id_rsa_github
chmod 600 /root/.ssh/id_rsa_github
echo "SSH密钥已复制到 /root/.ssh/"

# 配置环境变量
echo "export JAVA_HOME=${JAVA_HOME}" >> /home/$USER/.bashrc
source /home/$USER/.bashrc

## 安装GitLab Runner
curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.deb.sh | sudo bash
#curl -LJO https://gitlab-runner-downloads.s3.amazonaws.com/$GITLAB_RUNNER_VERSION/script.deb.sh | bash
#apt-get install -y gitlab-runner
#sudo gitlab-runner uninstall
#sudo gitlab-runner install --working-directory /home/gitlab-runner --user root
#systemctl daemon-reload
#systemctl start gitlab-runner
#systemctl enable gitlab-runner
# 下载指定版本
wget https://packages.gitlab.com/runner/gitlab-runner/packages/ubuntu/focal/gitlab-runner_16.8.1_amd64.deb/download -O gitlab-runner_16.8.1_amd64.deb
sudo dpkg -i gitlab-runner_16.8.1_amd64.deb
#sudo apt-get -f install
sudo gitlab-runner uninstall
sudo gitlab-runner install --working-directory /home/gitlab-runner --user root
systemctl daemon-reload
systemctl start gitlab-runner
systemctl enable gitlab-runner

# 设置要安装的 GitLab Runner 版本
# GitLab Runner 的下载 URL 模板，你需要替换掉 {version} 为你指定的版本号
#DOWNLOAD_URL="https://packages.gitlab.com/gitlab/gitlab-runner/$GITLAB_RUNNER_VERSION/gitlab-runner_${GITLAB_RUNNER_VERSION}_amd64.deb"
##DOWNLOAD_URL_EXPANDED=$(echo "$DOWNLOAD_URL" | sed "s/{version}/$GITLAB_RUNNER_VERSION/")
## 下载 GitLab Runner 的 .deb 包
#curl -O "$DOWNLOAD_URL_EXPANDED"
## 使用 dpkg 安装下载的 .deb 包
#sudo dpkg -i gitlab-runner_${GITLAB_RUNNER_VERSION}_amd64.deb
## 如果需要，处理依赖关系
#sudo apt-get install -y -f
## 卸载现有的 GitLab Runner 服务（如果存在的话）
#sudo gitlab-runner uninstall
## 安装 GitLab Runner 作为服务，并指定工作目录和用户
#sudo gitlab-runner install --working-directory /home/gitlab-runner --user root
## 重新加载 systemd 配置并启动 GitLab Runner 服务
#sudo systemctl daemon-reload
#sudo systemctl start gitlab-runner
#sudo systemctl enable gitlab-runner


#gitlab-runner register --non-interactive \
#    --url http://192.168.23.19 \
#    --token glrt-KETF8ddVR8ZyTjmn4n21 \
#    --executor "shell" \
#    --description "shdy runner"
# 自动注册GitLab Runner
gitlab-runner register --non-interactive \
    --url "http://${GITLAB_DOMAIN}/" \
    --token ${GITLAB_RUNNER_TOKEN} \
    --name ${GITLAB_RUNNER_DESCRIPTION} \
    --executor "shell"

# 安装SonarQube
echo "deb https://repo.sonarqube.org/deb sonarqube stable" | tee -a /etc/apt/sources.list.d/sonarqube.list
curl -sL https://repo.sonarqube.org/sonarqube-repo.deb | dpkg -i -
apt-get update
apt-get install -y sonarqube

# 启动SonarQube服务
systemctl enable sonarqube
systemctl start sonarqube



#NVM安装NODE
#curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/$NVM_VERSION/install.sh | bash
source ~/.bashrc
nvm --version
nvm install $NODE_VERSION
nvm use $NODE_VERSION


#Shell executor uses .profile to load env. You can make gitlab-runner use nvm by adding this to gitlab-runners $HOME/.profile:
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm

apt install sshpass

echo "安装和配置完成！"