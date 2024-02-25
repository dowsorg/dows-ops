//package org.dows.ops;
//
//import com.jcraft.jsch.*;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.*;
//
//@Slf4j
//public class JSchExecutor {
//    private String charset; // 设置编码格式
//    private Integer port; //默认端口
//    private String user; // 用户名
//    private String passwd; // 登录密码
//    private String host; // 主机IP
//    private static JSch jsch;
//    private Session session;
//    private ChannelSftp channelSftp;
//
//
//    public JSchExecutor() {
//
//    }
//
//    public JSchExecutor(String user, String passwd, String host) {
//        this.user = user;
//        this.passwd = passwd;
//        this.host = host;
//    }
//
//    /**
//     * @param user   用户名
//     * @param passwd 密码
//     * @param host   主机IP
//     */
//    public JSchExecutor(String user, String passwd, String host, int port) {
//        this.user = user;
//        this.passwd = passwd;
//        this.host = host;
//        this.port = port;
//    }
//
//
//    /**
//     * 通过用户名密码的方式连接sftp服务器，当sftp服务器 ssh密码没有失效的时候可以选择这种方式
//     *
//     * @param userAccount ssh用户账号
//     * @param host        远程sftp服务器ip地址
//     * @param port        远程sftp服务器端口地址
//     * @param passwd      远程服务器ssh密码
//     * @return 返回连接sftpChannel对象（建立起ssh隧道）
//     */
//    public ChannelSftp connect(String userAccount, String host, Integer port, String passwd) {
//        jsch = new JSch();
//        try {
//            session = jsch.getSession(userAccount, host, port);
//            log.info("创建sftp connect session连接对象:<<<<<<<<<<<<<" + session.getClass());
//            session.setPassword(passwd);
//            java.util.Properties config = new java.util.Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            session.connect();
//            Channel channel = session.openChannel("sftp");
//            channel.connect();
//            channelSftp = (ChannelSftp) channel;
//            log.info("连接到SFTP成功。host: " + host);
//        } catch (JSchException e) {
//            e.printStackTrace();
//        }
//        return channelSftp;
//    }
//
//
//    /**
//     * 通过publickey认证，当sftp服务器 ssh密码失效过期的时候可以选择这种方式
//     *
//     * @param SftpConfig sftp配置类，包含了远程服务器ip、port、uploadPath（要上传到服务器的哪个路径），privateKeyPath(私钥路径)
//     * @return 返回连接sftpChannel对象（建立起SftpChannel隧道）
//     */
//    public ChannelSftp connect(SftpConfig sftpConfig) {
//        jsch = new JSch();
//
//        try {
//            jsch.addIdentity(sftpConfig.getPrivateKeyPath());
//            session = jsch.getSession(sftpConfig.getUserAccount(), sftpConfig.getHost(), sftpConfig.getPort());
//            logger.info("创建sftp connect session连接对象:<<<<<<<<<<<<<" + session.getClass());
//            java.util.Properties config = new java.util.Properties();
//            config.put("StrictHostKeyChecking", "no");
//            session.setConfig(config);
//            session.connect();
//            Channel channel = session.openChannel("sftp");
//            channel.connect();
//            channelSftp = (ChannelSftp) channel;
//            log.info("连接到SFTP成功。host: " + sftpConfig.getHost());
//        } catch (JSchException e) {
//            e.printStackTrace();
//        }
//        return channelSftp;
//    }
//
//    /**
//     * 上传目标文件到sftp服务器的指定目录
//     *
//     * @param remotePath 指定了远程服务器的数据目录
//     * @param uploadFile 要上传到目标路径的文件
//     *                   channelSftp 操作远程服务器的操作类 可以执行 put、cd、ls等命令
//     */
//    public void uploadFile(File uploadFile, ChannelSftp channelsftp) throws IOException, SftpException {
//
//        ChannelSftp channelSftp = channelsftp;
//        FileInputStream input = null;
//        input = new FileInputStream(uploadFile);
////      put方法没有返回值，但是如果传输文件的过程中出现问题会抛出异常，因此catch了异常
//        channelSftp.put(input, uploadFile.getName());
////      此处要关闭文件流否则传输完成后，无法删除原文件
//        input.close();
//        log.info("File upload successfully: {" + uploadFile.getPath() + "}");
//    }
//
//    /**
//     * 判断远程服务器指定路径remotePath是否存在,不存在则创建该目录并cd到该目录
//     *
//     * @param remotePath 远程sftp指定目录路径
//     * @param ChanelSftp 操作linux 创建目录 mkdir cd等命令对象
//     */
//    public void hashRemotePathOrMkdirAndCDRemotePath(String remotePath) throws SftpException {
//        //hasPath()判断remotePath是否在在sftp服务器是否存在，存在返回true,否则返回false
//        if (hasPath(remotePath, channelSftp)) {
//            log.info("remotePath is exist！ all files will be push to /" + remotePath);
//            log.info("found pathHome {" + remotePath + " }");
//            return;
//        } else {
////             远程文件路径不存在，创建该目录
//            log.info("remotePath(" + remotePath + ") is  not exist!");
//            log.info("创建该目录:/" + remotePath);
//            channelSftp.mkdir(remotePath);
//            channelSftp.cd(remotePath);
//        }
//    }
//
//    /**
//     * 判断SFTP服务器有无路径
//     *
//     * @param path 远程服务器路径
//     * @return true or false
//     */
//    public static boolean hasPath(String path, ChannelSftp sftp) {
//        try {
//            //SftpATTRSlstat() 方法的作用是获取指定文件或目录的元数据信息，
//            //类似于 POSIX 文件系统中的 lstat 函数。它返回一个包含文件或目录属性的 SftpATTRS 对象，这个对象包含了以下信息：
//            //文件类型（Regular file、Directory、Symbolic link 等）。
//            //文件权限。
//            //文件所有者和所属组。
//            //文件大小。
//            //文件的修改时间和访问时间等时间戳信息。
//            //lstat() 方法原理是发送 SFTP 指令到远程 SSH 服务器，请求获取指定文件或目录的属性信息。远程服务器会根据请求，
//            // 返回相关的属性信息。JSch 库封装了这个 SFTP 请求和响应的过程，使开发者可以通过简单的方法调用来获取文件或目录的属性信息。
//            //所以这里判断SftpAttRs对象是否存在，如果为Null，即路径不存在，否则存在
//            SftpATTRS DirPathObject = sftp.lstat(path);
//            if (DirPathObject != null) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    /**
//     * 根据月份在Linux sftp服务器目录创建文件夹 like be 202310、202311
//     */
//    public void MkdirAndCdDirectoryOfRotationByMonth() {
////        1.判断主题目录有没有当前月的目录，如果有CD到当前月目录下
//
////        1.1获取当前年月份
//        String currentYearMonth = DateUtils.getYearAndMonthByString();
////        1.2 判断sftp服务器有没有年月份目录
//        String PathLikeCurrentYearMonth = currentYearMonth;
//
//        boolean hasPath = hasPath(PathLikeCurrentYearMonth, channelSftp);
//
//        try {
//            if (hasPath) {
//                log.info("目标目录:" + PathLikeCurrentYearMonth + "存在");
////       1.3 路径存在cd到当前月目录下
//                log.info("Cd 》》》》》" + PathLikeCurrentYearMonth);
//                channelSftp.cd(PathLikeCurrentYearMonth);
//                log.info("当前目录在:" + channelSftp.pwd());
//            } else {
//                log.info("目标目录不存在" + PathLikeCurrentYearMonth);
////        2.当前目录没有月目录like 202310，执行命令 mkdir `Date +%Y%m%d`
//
//                log.info("创建目标目录 mkdir " + PathLikeCurrentYearMonth);
//                channelSftp.mkdir(PathLikeCurrentYearMonth);
////        2.1 Cd 到  mkdir `date +%Y%m%d`创建的目录
//                log.info("Cd 》》》》》" + PathLikeCurrentYearMonth);
//                channelSftp.cd(PathLikeCurrentYearMonth);
//                log.info("当前目录在:" + channelSftp.pwd());
//            }
//        } catch (SftpException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//
//    public String pwd() {
//        String currentPath = null;
//        try {
//            currentPath = channelSftp.pwd();
//
//        } catch (SftpException e) {
//            e.printStackTrace();
//        }
//        return currentPath;
//    }
//
//
//    public int execCmd(String command) throws Exception {
//        log.info("开始执行命令:" + command);
//        int returnCode = -1;
//        BufferedReader reader = null;
//        Channel channel = null;
//
//        channel = session.openChannel("exec");
//        ((ChannelExec) channel).setCommand(command);
//        channel.setInputStream(null);
//        ((ChannelExec) channel).setErrStream(System.err);
//        InputStream in = channel.getInputStream();
//        reader = new BufferedReader(new InputStreamReader(in));//中文乱码貌似这里不能控制，看连接的服务器的
//
//        channel.connect();
//        System.out.println("The remote command is: " + command);
//        String buf;
//        while ((buf = reader.readLine()) != null) {
//            log.info(buf);
//        }
//        reader.close();
//        // Get the return code only after the channel is closed.
//        if (channel.isClosed()) {
//            returnCode = channel.getExitStatus();
//        }
//        log.info("Exit-status:" + returnCode);
//
//        channel.disconnect();
//        return returnCode;
//    }
//
//
//    /**
//     * 关闭连接
//     */
//    public void disconnect() {
//        if (channelSftp != null && session.isConnected()) {
//            channelSftp.disconnect();
//        }
//        if (session != null && session.isConnected()) {
//            session.disconnect();
//        }
//
//    }
//
//
//}