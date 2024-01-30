package org.dows.ops;

import com.jcraft.jsch.Session;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.exceptions.BizException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static cn.hutool.extra.ssh.JschUtil.createSession;

@Slf4j
@Data
@Configuration
@EnableConfigurationProperties(SshProperties.class)
@RequiredArgsConstructor
public class SshConfig {
    private final SshProperties sshProperties;

    /**
     * 连接到指定的ip
     */
    public Session connect() {
        String ipAddress = sshProperties.getIpAddress();
        int port = sshProperties.getPort();
        String username = sshProperties.getUsername();
        String password = sshProperties.getPassword();

        //设置登录主机的密码
        Session session = createSession(ipAddress, port, username, password);
        try {
            //如果服务器连接不上，则抛出异常
            if (session == null) {
                throw new BizException("session is null");
            }

            //设置首次登录跳过主机检查
            session.setConfig("StrictHostKeyChecking", "no");
            //设置登录超时时间
            session.connect(sshProperties.getConnectTimeout());
        } catch (Exception e) {
            log.error("SSH连接异常:" + e.getMessage());
        }
        return session;
    }
}
