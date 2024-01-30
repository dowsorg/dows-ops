package org.dows.ops;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

/**
 * 通过公钥免密登录
 */
@Slf4j
public class JSchIdentity {

    public static void main(String[] args) {

        String username = "root";
        String host = "192.168.66.36";
        int port = 22;

        // 参加jsch对象
        JSch jSch = new JSch();
        Session session = null;
        boolean result = false;

        try {
            jSch.setKnownHosts("~/.ssh/known_hosts");   // 信任的主机
            jSch.addIdentity("~/.ssh/id_rsa");          // 私钥文件

            session = jSch.getSession(username, host, port);

            // 去掉首次连接确认
            session.setConfig("StrictHostKeyChecking", "no");

            // 超时连接
            session.setTimeout(3000);

            // 进行连接
            session.connect();

            // 获取连接结果
            result = session.isConnected();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }

        if (result) {
            log.info("【SSH连接】连接成功");
        } else {
            log.info("【SSH连接】连接失败");
        }
    }
}