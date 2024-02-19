FROM gitlab/gitlab-runner:v16.8.1

ENV JDK_GZ jdk-17_linux-x64_bin.tar.gz
ENV MVN_GZ apache-maven-3.9.6-bin.tar.gz
ENV SONAR_SCANNER_CLI_ZIP sonar-scanner-cli-5.0.1.3006-linux.zip

ENV JDK_NAME jdk-17.0.10
ENV MVN_NAME maven-3.9.6
ENV SONAR_SCANNER_CLI_NAME sonar-scanner-cli-5.0.1.3006

WORKDIR /home

COPY ./$JDK_GZ .
COPY ./$MVN_GZ .
COPY ./$SONAR_SCANNER_CLI_ZIP .

RUN apt-get update  \
    && apt-get install -y locales  \
    && apt-get install -y unzip \
    && locale-gen "en_US.UTF-8"  \
    && update-locale LC_ALL="en_US.UTF-8" \
    && export LANG="en_US.UTF-8" \
    && tar zxf $JDK_GZ  \
    && tar zxf $MVN_GZ  \
    && unzip $SONAR_SCANNER_CLI_ZIP \
    && echo "export CLASSPATH=.:/home/$JDK_NAME/lib" >> /etc/profile \
    && echo "export CLASSPATH=.:/home/$JDK_NAME/lib" >> /root/.bashrc \
    && echo "export PATH=\$PATH:/home/$JDK_NAME/bin:/home/$MVN_NAME/bin:/home/$SONAR_SCANNER_CLI_NAME/bin" >> /etc/profile \
    && echo "export PATH=\$PATH:/home/$JDK_NAME/bin:/home/$MVN_NAME/bin:/home/$SONAR_SCANNER_CLI_NAME/bin" >> /root/.bashrc \
    && . /etc/profile  \
    && . /root/.bashrc \
    && rm -f /home/$JDK_GZ /home/$MVN_GZ /home/$SONAR_SCANNER_CLI_ZIP \
    && rm -rf \
      /var/cache/debconf/* \
      /var/lib/apt/lists/* \
      /var/log/* \
      /var/tmp/* \
    && rm -rf /tmp/* \
    && apt-get clean -y