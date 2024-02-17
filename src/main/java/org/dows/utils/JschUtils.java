package org.dows.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Component;

@Component
public class JschUtils {

    private Session session = null;
    private JSch jsch = null;
    private Channel channel = null;

    public Session getSession(String username, String host, String password, int port) {
        jsch = new JSch();
        try {
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return session;
    }

    public Channel getChannel(String username, String host, String password, int port, String type) {
        jsch = new JSch();
        try {
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = session.openChannel(type);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return channel;
    }

    public void disconnectSession() {
        if (null != session) {
            session.disconnect();
        }
    }

    public void disconnectChannel() {
        session.disconnect();
        channel.disconnect();
    }
}