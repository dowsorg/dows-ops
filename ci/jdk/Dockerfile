FROM ubuntu:latest
MAINTAINER lait

#setup language 解决中文乱码
ENV LANG C.UTF-8
ENV LC_ALL=zh_CN.utf8
ENV LANG=zh_CN.UTF8
ENV LANGUAGE=zh_CN.utf8

#setup java env
ENV JAVA_HOME=/usr/local/jre17
ENV PATH=$PATH:$JAVA_HOME/bin
ENV CALSSPATH=$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

COPY ./jre17 /usr/local/jre17
#在windows环境下构建，将/usr/share/zoneinfo/Asia/Shanghai拷贝到本地目录
#COPY ./Shanghai /etc/localtime

RUN chmod -R 777 /usr/local/jre17

#更新软件源+安装字体支持+清理缓存
RUN apt-get update && apt-get install fontconfig -y && apt-get clean

#设置时间
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/timezone

#设置字体
RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8
RUN yum -y install fontconfig && yum -y install mkfontscale && mkfontscale && mkfontdir && fc-cache -fv
