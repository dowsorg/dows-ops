FROM gitlab/gitlab-runner:v16.8.1

RUN apt-get update;
RUN apt-get install -y openssh sshpass net-tools;
# 配置OpenSSH服务器
RUN echo "PermitRootLogin yes" >> /etc/ssh/sshd_config && \
    echo "PasswordAuthentication yes" >> /etc/ssh/sshd_config && \
    mkdir /var/run/sshd
# 设置root密码
RUN echo 'root:pwd135246!' | chpasswd

# 安装 OpenJDK 17
RUN apt-get install -y openjdk-17-jdk;
# 安装 Maven
RUN apt-get install -y maven;
# 安装 Sonar Scanner CLI
RUN apt-get install -y unzip && \
    wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-5.0.1.3006-linux.zip && \
    unzip sonar-scanner-cli-5.0.1.3006-linux.zip -d /opt && \
    rm sonar-scanner-cli-5.0.1.3006-linux.zip && \
    mv /opt/sonar-scanner-5.0.1.3006-linux /opt/sonar-scanner && \
    ln -s /opt/sonar-scanner/bin/sonar-scanner /usr/local/bin/sonar-scanner;

RUN apt-get clean;

# 配置环境变量
ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64
ENV M2_HOME /usr/share/maven
ENV MAVEN_HOME /usr/share/maven
ENV SONAR_RUNNER_HOME /opt/sonar-scanner
# 将 Maven 和 Sonar Scanner CLI 的 bin 目录加入 PATH
ENV PATH $JAVA_HOME/bin:$M2_HOME/bin:$SONAR_RUNNER_HOME/bin:$PATH
# 暴露SSH端口
EXPOSE 22
# 启动SSH服务
CMD ["/usr/sbin/sshd", "-D"]