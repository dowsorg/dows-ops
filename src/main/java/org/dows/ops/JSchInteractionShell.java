package org.dows.ops;

import com.jcraft.jsch.*;
import java.util.Properties;

/**
 *  远程执行命令，并返回结果 ，交互式
 */
public class JSchInteractionShell{
    public static void main(String[] arg){
 
        String username = "root";
        String password = "123456";
        String host = "192.168.66.36";
        int port = 22;
 
        // 创建
        JSch jSch = new JSch();
        Session session = null;
        ChannelShell shell = null;
 
        try {
            session = jSch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(3000);
            session.connect();
 
            shell = (ChannelShell) session.openChannel("shell");
            shell.setInputStream(System.in);
            shell.setOutputStream(System.out);
            shell.connect();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}