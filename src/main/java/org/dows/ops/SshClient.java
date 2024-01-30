//package org.dows.ops;
//
//
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//@EnableConfigurationProperties({DomainProperties.class, SshProperties.class})
//public class SshClient {
//
//    private static final String DEFAULTCHART = "UTF-8";
//    private static final Map<String, Connection> CONNECTION_MAP = new HashMap<>();
//    private final SshProperties sshProperties;
//
//    /**
//     * 解析脚本执行返回的结果集
//     *
//     * @param in      输入流对象
//     * @param charset 编码
//     * @return 以纯文本的格式返回
//     */
//    private String processStdout(InputStream in, String charset) {
//        InputStream stdout = new StreamGobbler(in);
//        StringBuffer buffer = new StringBuffer();
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                buffer.append(line + " ");
//            }
//        } catch (UnsupportedEncodingException e) {
//            log.error("解析脚本出错：" + e.getMessage());
//            e.printStackTrace();
//        } catch (IOException e) {
//            log.error("解析脚本出错：" + e.getMessage());
//            e.printStackTrace();
//        }
//        return buffer.toString();
//    }
//
//    @PostConstruct
//    public void init() {
//        List<SshProperties.RemoteServer> clients = sshProperties.getClients();
//        for (SshProperties.RemoteServer client : clients) {
//            Connection connection = new Connection(client.getHost());
//            CONNECTION_MAP.put(client.getHost(), connection);
//        }
//    }
//
//    /**
//     * login:ssh用户登录验证，使用用户名和密码来认证. <br/>
//     */
//    public boolean login(String host) {
//        //创建远程连接，默认连接端口为22，如果不使用默认，可以使用方法
//        SshProperties.RemoteServer sshClient = sshProperties.getSshClient(host);
//        try {
//            Connection connection = CONNECTION_MAP.get(host);
//            connection.connect();
//            //使用用户名和密码登录 有秘钥可以使用authenticateWithPublicKey验证
//            return connection.authenticateWithPassword(sshClient.getUser(), sshClient.getPwd());
//        } catch (IOException e) {
//            log.error("用户%s密码%s登录服务器%s失败！", sshClient.getUser(), sshClient.getPwd(), sshClient.getHost(), e);
//        }
//        return false;
//    }
//
//    /**
//     * 远程执行shell脚本或者命令
//     *
//     * @return 命令执行完后返回的结果值
//     */
//    public String scp(String host, String[] files, String targetDir) {
//        String result = "";
//        Connection connection = null;
//        try {
//            boolean isAuthed = login(host);
//            if (isAuthed) {
//                connection = CONNECTION_MAP.get(host);
//                SCPClient scpClient = connection.createSCPClient();
//                scpClient.put(files, targetDir);
//                connection.close();
//            }
//        } catch (IOException e) {
//            log.error("执行命令失败,链接connection:" + connection + ",执行的命令：" + e.getMessage());
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public String scp(String host, byte[] data, String fileName, String targetDir) {
//        String result = "";
//        Connection connection = null;
//        try {
//            boolean isAuthed = login(host);
//            if (isAuthed) {
//                connection = CONNECTION_MAP.get(host);
//                SCPClient scpClient = connection.createSCPClient();
//                scpClient.put(data, fileName, targetDir, "0644");
//                connection.close();
//            }
//        } catch (IOException e) {
//            log.error("执行命令失败,链接connection:" + connection + ",执行的命令：" + e.getMessage());
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//
//    /**
//     * 远程执行shell脚本或者命令
//     *
//     * @param cmd 即将执行的命令
//     * @return 命令执行完后返回的结果值
//     */
//    public String execute(String host, String cmd) {
//        String result = "";
//        Connection connection = null;
//        try {
//            boolean isAuthed = login(host);
//            if (isAuthed) {
//                connection = CONNECTION_MAP.get(host);
//                //打开一个会话
//                Session session = connection.openSession();
//                //执行命令
//                session.execCommand(cmd);
//                result = processStdout(session.getStdout(), DEFAULTCHART);
//                //如果为得到标准输出为空，说明脚本执行出错了
//                if (StringUtils.isBlank(result)) {
//                    log.info("得到标准输出为空,链接connection:" + connection + ",执行的命令：" + cmd);
//                    result = processStdout(session.getStderr(), DEFAULTCHART);
//                } else {
//                    log.info("执行命令成功,链接connection:" + connection + ",执行的命令：" + cmd);
//                }
//                connection.close();
//                session.close();
//            }
//        } catch (IOException e) {
//            log.error("执行命令失败,链接connection:" + connection + ",执行的命令：" + cmd + "  " + e.getMessage());
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//}
//
