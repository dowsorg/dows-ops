package org.dows.cloud;

import cn.hutool.json.JSONUtil;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!ddddd
 */
@Slf4j
@EnableConfigurationProperties(AppVersion.class)
@RestController
@SpringBootApplication(scanBasePackages = {"org.dows.*"})
@EnableEncryptableProperties
public class App {
    @Autowired
    private AppVersion appVersion;

    @Autowired
    private DataSourceProperties dataSourceProperties;
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

    @GetMapping("/env")
    public String getDataSourceProperties() {
        return JSONUtil.toJsonStr(dataSourceProperties);
    }
}
