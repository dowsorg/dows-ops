package org.dows.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dows.ssh.SftpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @description: 读取resource目录下的sftpConfig.json文件，并将它转换成Java对象
 */

public class TransferingConfigToObjectUtil {

    private Logger logger = LoggerFactory.getLogger(TransferingConfigToObjectUtil.class);

    public TransferingConfigToObjectUtil() {
    }

    /**获取类路径下json配置文件，并返回配置文件转换成的java对象
     * @param resource 类路径下的文件路径名
     * @return 返回SftpConfig集合
     *  */

    /**
     * 读取.json配置文件，将json数组转换成字符串的方式，再将字符串转换成SftpConfig集合
     *
     * @return 解析成功返回List<SftpConfig> 否则返回null或者报异常
     */
    public List<SftpConfig> getSftpConfigList(String resource) {
//        Todo 调用方法将配置文件转成的String，并将String转换成Java对象
        List<SftpConfig> sftpConfigs = null;
        try {
            sftpConfigs = parseJsonFilesToObjectList(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sftpConfigs;
    }

    /**
     * 读取.json配置文件，将json数组转换成字符串的方式，再将字符串转换成SftpConfig集合
     *
     * @param resource 类路径下的文件路径名
     * @return 返回SftpConfig集合
     */
    private List<SftpConfig> parseJsonFilesToObjectList(String resource) throws IOException {
        List<SftpConfig> sftpConfigsList = null;
        logger.info("---正在加载配置文件---" + resource);

//        获取类路径下的resource文件的输入流
        ClassPathResource classPathResource = new ClassPathResource(resource);
        InputStream resourceStream = classPathResource.getInputStream();
//        将输入流的内容转换成字符串
        String sftpConfigJsonList = "";
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceStream))) {
            while ((line = bufferedReader.readLine()) != null) {
                sftpConfigJsonList = sftpConfigJsonList.concat(line);
            }
            System.out.println("拼接的字符串结果为:");
            System.out.println(sftpConfigJsonList);
            ObjectMapper mapper = new ObjectMapper();
            sftpConfigsList = mapper.readValue(sftpConfigJsonList, new TypeReference<List<SftpConfig>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
//        将读取到的json转换成Java对象
        return sftpConfigsList;
    }


}