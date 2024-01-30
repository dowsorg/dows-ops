package org.dows.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("dows.dxz")
@Data
public class AppVersion {
    private String appId;
    private String appCode;
    private String version;
    private List<String> modules;
    private String env;


}
