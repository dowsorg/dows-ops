package org.dows.ops;

public class RunJsch {
    public static void main(String[] args) {
 
        JschUtil jSchUtil = new JschUtil("192.168.66.36",22,"root","123456");
        boolean connect = jSchUtil.connect();
        System.out.println("ssh连接检测：" + connect);
 
        String ls = jSchUtil.execCommand("ls");
        System.out.println("执行ls命令的结果：" + ls);
 
        // 文件上传
        jSchUtil.upload("D:/2.txt", "/home/1.txt");
 
        // 文件下载
        jSchUtil.download("/home/1.txt", "D:/node1.txt");
 
    }
}