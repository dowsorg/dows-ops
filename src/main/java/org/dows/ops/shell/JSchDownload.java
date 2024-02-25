package org.dows.ops.shell;

import cn.hutool.core.util.CharsetUtil;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSchDownload {


    public static void main(String[] args) {

        String username = "root";
        String password = "123456";
        String host = "192.168.66.36";
        int port = 22;

        // 创建JSch对象
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp ftp = null;

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

            // 打开sftp通道
            ftp = (ChannelSftp) session.openChannel("sftp");

            // 建立sftp通道的连接
            ftp.connect();

            // 设置编码
            ftp.setFilenameEncoding(CharsetUtil.CHARSET_UTF_8);

            /**
             * 说明：
             * 1、当前上读取文件信息没有任何反馈，如果没有异常则代表成功
             * 2、如果需要判断是否读取成功的进度，可参考https://blog.csdn.net/coding99/article/details/52416373?locationNum=13&fps=1
             * 3、将src文件下载到dst路径中
             */

            // 下载文件
            ftp.get("/root/php.tar.gz", "d:/123");

            log.info("文件下载成功");

        } catch (SftpException | JSchException e) {
            log.warn(e.getMessage());
        } finally {
            // 关闭ftp
            if (ftp != null && ftp.isConnected()) {
                // ftp.disconnect();
                ftp.quit();
            }

            // 关闭session流
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
}