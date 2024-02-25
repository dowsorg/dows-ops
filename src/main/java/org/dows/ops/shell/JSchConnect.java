package org.dows.ops.shell;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSchConnect {

    public static void main(String[] args) {

        String username = "root";
        String password = "123456";
        String host = "192.168.66.36";
        int port = 22;

        // 创建JSch对象
        JSch jsch = new JSch();
        Session session = null;
        boolean result = false;

        try {
            // 根据主机账号、ip、端口
            session = jsch.getSession(username, host, port);
            // 设置主机密码
            session.setPassword(password);

            // 去掉首次连接确认
            session.setConfig("StrictHostKeyChecking", "on");

            // 超时连接时间为3秒
            session.setTimeout(3000);

            // 进行连接
            session.connect();

            // 获取连接结果
            result = session.isConnected();

        } catch (JSchException e) {
            log.warn(e.getMessage());
        } finally {
            // 关闭session流
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }

        if (result) {
            log.error("【SSH连接】连接成功");
        } else {
            log.error("【SSH连接】连接失败");
        }
    }
}