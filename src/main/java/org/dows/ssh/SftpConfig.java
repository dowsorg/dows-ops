package org.dows.ssh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@NoArgsConstructor()
@AllArgsConstructor()
@Getter()
@Setter()
@Component
public class SftpConfig {
    //  sftp传输端口
    public Integer port;
    //   指定sftp要使用的charset
    public String charset;
    //   sftp 服务端主机Ip或者主机名
    private String host;
    //   sftp账户
    private String userAccount;
    //   sftp密码
    private String passWord;
    //  源文件路径
    private String filePathFrom;
    //  sftp目的文件目录
    private String upToPath;
    //  需要传输文件的主题名（即包含该主题的文件上传，否则就不上传其他文件）
    private String theme;
    //    ssh 免密登录需要携带私钥,privateKeyPath存放client 私钥的位置
    private String privateKeyPath;
}