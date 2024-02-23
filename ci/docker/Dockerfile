FROM ubuntu:20.04
MAINTAINER "lait<lait.zhang@gmail.com>"
ENV MYPATH /usr/local
WORKDIR $MYPATH
RUN apt-get update
RUN apt install -y sudo
RUN apt install -y vim
RUN apt update
RUN apt install -y build-essential
RUN apt install -y net-tools
RUN apt install -y curl
RUN apt install -y sudo
RUN apt install -y systemctl
RUN curl -fsSL https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
RUN apt-get install -y software-properties-common
RUN sudo add-apt-repository \
 "deb [arch=amd64] https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/ \
 $(lsb_release -cs) \
 stable"
RUN apt-get install -y docker-ce docker-ce-cli containerd.io
RUN mkdir -p /etc/docker
RUN tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://8ls10g1.mirror.aliyuncs.com"]
}
EOF
RUN systemctl daemon-reload
RUN systemctl restart docker
CMD echo $MYPATH
CMD echo "----end----“
CMD /bin/bash