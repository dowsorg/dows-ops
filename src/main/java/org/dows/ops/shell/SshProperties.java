package org.dows.ops.shell;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author lifel  @creat 2023/12/11 17:02
 */
@Data
@ConfigurationProperties("dows.ssh")
public class SshProperties {
    /**
     * 主机ip
     */
    private String ipAddress;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 端口号
     */
    private int port;
    /**
     * 回话超时时间
     */
    private int connectTimeout;
    /**
     * ssh脚本文件路径
     */
    private Map<String,String> shellScriptFilePath;
}
