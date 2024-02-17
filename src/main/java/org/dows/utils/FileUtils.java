package org.dows.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 处理及判断文件异常工具类
 */

@Slf4j
public class FileUtils {
    private String path;

    public FileUtils() {
    }

    public FileUtils(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }


    /**
     * 判断文件路径是否为空，且文件路径格式是否合法，如果 not null且合法返回true，否则返回false
     *
     * @return 文件路径合法且不为空返回true，其他不合法情况返回false
     */
    public Boolean fileIsExisted() {
        File file = new File(this.getPath());
//        如果文件路径不为空,且path是文件目录，而不是其他文件之类的
        if (this.path != null && !file.isDirectory())
            return true;
        return false;
    }

    /**
     * 判断文件路径是否为空，且文件路径格式是否合法，如果 not null且合法返回true，否则返回false
     *
     * @return 文件路径合法且不为空返回true，其他不合法情况返回false
     */
    public Boolean CheckedFitDirectory() {
        File file = new File(this.getPath());
//        如果文件路径不为空,且path是文件目录，而不是其他文件之类的
        if (this.path != null && file.isDirectory())
            return true;
        return false;
    }


    /**
     * 判断文件路径下有没有要传输的目标主题文件
     *
     * @param Theme 要传输目标文件的主题名字
     * @return 包含目标主题的文件集合，如果不存在返回空
     */
    public List<File> ObtainReadyUploadFileListsWithTargetedTheme(String Theme) {

//      1.校验主题是否正确
        if (Theme == null || Theme.equals("")) {
            log.error("目标主题没有配置，是空字符串");
        }
        File file = new File(this.getPath());
        String baseName = file.getName();
//      2.获取文件路径下所有文件
        File[] allListFiles = file.listFiles();
//      3.遍历该目录下所有文件，返回包含主题的文件集合
        List<File> ReadyUploadFileListsWithTargetedTheme = new ArrayList<>();
        for (File singgleFile : allListFiles) {
//      4.要传输的文件包含目标主题，且文件可读可写可执行且没有被占用即可以获取到输入流
            if (singgleFile.getName().contains(Theme) && FileBlockOrNot(singgleFile)) {
                log.info("文件:" + singgleFile.getPath() + ">>>>>>包含文件主题" + Theme + ",且没有被阻塞!!!!!!!");
                ReadyUploadFileListsWithTargetedTheme.add(singgleFile);
            }
        }
        return ReadyUploadFileListsWithTargetedTheme;
    }

    /**
     * 判断要上传的源文件是否处于可读可写可执行及未被占用
     *
     * @return 如果正在传输或者被占用或目标文件不可读不可写不可执行，返回false,否则返回true
     */
    private boolean FileBlockOrNot(File targetFile) {

//        判断文件可读可写可执行
        if (targetFile != null && targetFile.canRead() && targetFile.canWrite() && targetFile.canExecute()) {
//        尝试获取输入流
            try (FileInputStream fis = new FileInputStream(targetFile)) {
//        如果能获取到输入流表明文件没有被占用
                log.info("----------文件输入流没有阻塞，准备读取二进制文件流----------");
                return true;
            } catch (IOException e) {
                log.warn(targetFile.getName() + "文件被占用！！！！！！");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }


}