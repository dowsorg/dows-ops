package org.dows.ops.shell;

import cn.hutool.core.io.FileUtil;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

/**
 * 使用JSch库执行shell命令 非交互式
 */
@Slf4j
public class JSchExecShell {


    public static void main(String[] args) {

        String username = "root";
        String password = "123456";
        String host = "192.168.66.36";
        int port = 22;

        // 创建
        JSch jSch = new JSch();
        Session session = null;
        ChannelExec exec = null;

        // 存放执行命令结果
        StringBuffer result = new StringBuffer();
        int exitStatus = 0;
        String command = "ls -l";

        // 创建session
        try {
            session = jSch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            // 设置连接超时时间
            session.setTimeout(3000);
            session.connect();

            exec = (ChannelExec) session.openChannel("exec");
            exec.setCommand(command);

            exec.setInputStream(null);
            // 错误信息输出流，用于输出错误信息，当exitstatus<0的时候
            exec.setErrStream(System.err);

            // 执行命令，等待执行结果
            exec.connect();

            // 获取命令行结果
            InputStream in = exec.getInputStream();

            // 通过channel获取信息的方式，采用官网的demo代码
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    result.append(new String(tmp, 0, i));
                }

                // 从channel 获取全部信息之后，channel会自动关闭
                if (exec.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    exitStatus = exec.getExitStatus();
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭 ftp
            if (exec != null && exec.isConnected()) {
                exec.disconnect();
            }
            // 关闭 session
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        log.info("获取执行命令的结果是：" + FileUtil.getLineSeparator() + result);
        log.info("退出码为：" + FileUtil.getLineSeparator() + exitStatus);
    }
}