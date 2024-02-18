FROM gitlab/gitlab-runner:v16.9.0
ENV SONAR_NAME sonarqube9.9.3
ENV SONAR_SCANNER_ZIP sonar-scanner-cli-5.0.1.3006-linux
ENV SONAR_SCANNER_FILE sonar-scanner-5.0.1.3006-linux
WORKDIR /home
RUN sed -i 's/htt[p|ps]:\/\/archive.ubuntu.com\/ubuntu\//mirror:\/\/mirrors.ubuntu.com\/mirrors.txt/g' /etc/apt/sources.list
RUN wget https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/$SONAR_SCANNER_ZIP.zip \
    && apt-get update \
    && apt update \
    && apt-get install -y locales \
    && locale-gen "en_US.UTF-8" \
    && update-locale LC_ALL="en_US.UTF-8" \
    && export LANG="en_US.UTF-8" \
    && apt-get -y install unzip \
    && apt -y install unzip \
    && unzip $SONAR_SCANNER_ZIP.zip && echo "export PATH=\$PATH:/home/$SONAR_SCANNER_FILE/bin" >> /etc/profile \
    && echo "export PATH=\$PATH:/home/$SONAR_SCANNER_FILE/bin" >> /root/.bashrc \
    && . /etc/profile \
    && . /root/.bashrc \
    && sed -i "s/#sonar.host.url=http:\/\/localhost:9000/sonar.host.url=http:\/\/$SONAR_NAME:9000/g" /home/$SONAR_SCANNER_FILE/conf/sonar-scanner.properties \
    && rm -f /home/$SONAR_SCANNER_ZIP.zip