package org.dows.ftp;

import com.jcraft.jsch.ChannelSftp;
import lombok.extern.slf4j.Slf4j;
import org.dows.ssh.JSchExecutor;
import org.dows.ssh.SftpConfig;
import org.dows.utils.FileUtils;
import org.dows.utils.TransferingConfigToObjectUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Slf4j
@Component
public class SftpUploadService {

    //      连接sftp服务器的操作类
    private static JSchExecutor executor = new JSchExecutor();
    //      解析sftpConfig.json配置文件转换成sftpConfig集合对象
    private static TransferingConfigToObjectUtil configTransferToObjectUtil = new TransferingConfigToObjectUtil();
    //     执行和处理文件获取异常的类
    private static FileUtils fileUtils = new FileUtils();
    //      sftpConfig.json所在的类路径
    private String sftpConfigPath = "/sftpConfig.json";

    public void TransferBatchFiles() {
//        1.这里从类路径下的sftpConfig.json配置文件获取List<sftpConfig>对象
        List<SftpConfig> sftpConfigLists = getLoadingSftpConfigJson(sftpConfigPath);
        for (SftpConfig oneSftpConfig : sftpConfigLists) {
//        2. 获取传输目的路径
            String filePath = oneSftpConfig.getFilePathFrom();
//        3.获取准备上传的文件数组
            String Theme = oneSftpConfig.getTheme();
            List<File> filelists = getReadyUploadFiles(filePath, Theme);
//        4.1如果获取不到文件，返回错误原因
            if (filelists == null) {
                log.warn("获取不到文件，原因可能是文件路径不存在文件，或者文件被占用，以及文件路径不是一个文件而是目录。");
                break;
            }
//        5.连接sftp服务器,推送文件,
            ConnctSftpAndBatchPutFile(oneSftpConfig, filelists);
        }

    }

    /**
     * 返回sftpConfig.json配置文件对应的sftpconfigList集合对象
     *
     * @return 解析成功返回List<SftpConfig> 否则返回null
     */
    private List<SftpConfig> getLoadingSftpConfigJson(String jsonFilePath) {
        List<SftpConfig> sftpConfigList = null;
        if (jsonFilePath != null && !jsonFilePath.equals("")) {
            sftpConfigList = configTransferToObjectUtil.getSftpConfigList(jsonFilePath);
        }
        return sftpConfigList;
    }


    /**
     * 连接到sftp服务器将文件集合推送到服务器，如果目标服务器没有路径则创建路径
     *
     * @param sftpConfig sftpconfig包含了目标路径以及sftp服务器的配置信息
     * @param filelists  源文件集合
     */

    private void ConnctSftpAndBatchPutFile(SftpConfig sftpConfig, List<File> filelists) {

        if (filelists.isEmpty() || filelists == null) {
            log.info("没有要传输的文件！！！！！！");
            return;
        }
        log.info("开始连接sftp服务器>>>>>>>>>>>>>>host=" + sftpConfig.getHost(), ",文件路径:" + sftpConfig.getUpToPath());
        try {
//          建立sftp连接，传入SftpConfig ,打开sftpChannel
            ChannelSftp channelSftp = executor.connect(sftpConfig);
//          判断远程服务器指定路径remotePath是否存在,不存在则创建该目录并cd到该目录
            executor.hashRemotePathOrMkdirAndCDRemotePath(sftpConfig.getUpToPath());
//          根据月份在Linux sftp服务器目录创建文件夹 like be 202310、202311
            executor.MkdirAndCdDirectoryOfRotationByMonth();
//          执行put文件操作
            for (File singleFile : filelists) {
                log.info("推送文件到>>>>>>>>>>>host:" + sftpConfig.getHost() + ",directory:" + executor.pwd());

                executor.uploadFile(singleFile, channelSftp);
//          推送完就删除源文件，推一个删一个
                boolean isdeleted = singleFile.delete();
                if (isdeleted) {
                    log.info("源文件:" + singleFile.getName() + "被删除！！！！");
                }
//          没有抛异常就代表传输成功
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//           释放sftp channel和session连接
            executor.disconnect();
        }
    }

    /**
     * 获取文件的方法
     *
     * @param filePath 源文件目录路径
     * @return 如果传递过来的文件路径为空或者文件被占用且不可读可写可执行返回空对象null, 否则将遍历到的文件集合返回
     */
    private List<File> getReadyUploadFiles(String filePath, String Theme) {
        Integer listFileNums = 0;
        List<File> ReadyUploadFileListsWithTargetedTheme = null;
//      如果传递过来的目录路径为空，则返回空对象
        if (filePath == null) {
            return null;
        }
//     1. 获取处理文件异常的工具类对象
        fileUtils = new FileUtils(filePath);
//     1.1  文件目录filePath得合法即不为空
        if (fileUtils.CheckedFitDirectory()) {
//     1.2  遍历文件路径下的文件，另外要传输的文件未被占用及文件存在并且文件可读可写可执行，才可以添加到list集合中
            ReadyUploadFileListsWithTargetedTheme = fileUtils.ObtainReadyUploadFileListsWithTargetedTheme(Theme);
        } else {
//     1.3获取文件异常，情况如下文件路径是目录或者文件被占用,返回空对象
            log.warn("路径错误! usage of correcting is E:\\logtest");
            return null;
        }
//     2.能够获取到文件列表打印输出获取到的文件
        for (File singleFile : ReadyUploadFileListsWithTargetedTheme) {

            listFileNums++;
            log.info("遍历到包含主题(" + Theme + "的文件)------------------:" + singleFile.getPath() + ",文件size:" + singleFile.length() + "bytes");
        }
        log.info("总共遍历到" + listFileNums + "个文件");
        listFileNums = 0;
        return ReadyUploadFileListsWithTargetedTheme;
    }


}