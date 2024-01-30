package org.dows;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.dows.config.AppVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Hello world!ddddd
 */
@Slf4j
@EnableConfigurationProperties(AppVersion.class)
@SpringBootApplication(scanBasePackages = {"org.dows.*"})
public class App {
    @Autowired
    private AppVersion appVersion;

    @Value("${spring.profiles.active}")
    private String env;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/version")
    public String version() {
        log.info("version......");
        appVersion.setEnv(env);
        return JSONUtil.toJsonStr(appVersion);
    }

}
