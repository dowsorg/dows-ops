# 安装依赖
sudo apt install open-vm-tools
sudo apt install open-vm-tools-desktop
# 重启系统
reboot

#修改root用户密码
sudo passwd
#安装 openssh-server
sudo apt-get install openssh-server
#su - root切换到root用户
su - root
#打开ssh配置文件,如果PermitRootLogin prohibit-password被注释，则取消注释并更改为PermitRootLogin yes
vim /etc/ssh/sshd_config
#重启ssh服务 : service sshd start
systemctl restart sshd
#开启防火墙
#查看本地端口开启情况
sudo ufw status
#关闭防火墙
sudo ufw disable
#开启防火墙，允许访问特定端口
sudo ufw enable

##############简单开启/禁用###################
sudo ufw allow|deny [service]
#允许所有的外部IP访问本机的25/tcp端口(smtp)
sudo ufw allow smtp
#禁止外部访问smtp服务
sudo ufw deny smtp
#允许所有的外部IP访问本机的22/tcp端口(ssh)
sudo ufw allow 22/tcp
#允许外部访问53端口(tcp/udp)
sudo ufw allow 53
#允许此IP访问所有的本机端口
sudo ufw allow from 192.168.1.100
#允许从192.168.1.30到192.1681.5的SSH连接删除规则
sudo ufw allow proto tcp from 192.168.1.30 to 192.1681.5 port 65000
sudo ufw delete [rule]
#删除某条规则
sudo ufw delete allow smtp



#开放端口
sudo iptables -P INPUT ACCEPT
sudo iptables -P OUTPUT ACCEPT
sudo iptables -P FORWARD ACCEPT