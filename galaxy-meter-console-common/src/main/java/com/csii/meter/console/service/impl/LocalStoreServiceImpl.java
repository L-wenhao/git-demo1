package com.csii.meter.console.service.impl;

import com.csii.meter.console.config.MeterConsoleConfig;
import com.csii.meter.console.exception.MeterConsoleException;
import com.csii.meter.console.service.FileSystemStoreService;
import com.csii.meter.console.utils.FileUtils;
import com.csii.meter.console.utils.PathUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 文件本地存储服务实现
 * @author liuya
 */
@Service("fileSystemStoreService")
@ConditionalOnProperty(prefix = "console", name = "file-store", havingValue = "local", matchIfMissing = true)
public class LocalStoreServiceImpl implements FileSystemStoreService {

    /**
     * 保存文件
     * @param bytes
     * @return fileKey 文件Key
     */
    @Override
    public String saveFile(byte[] bytes) {
        //生成存储目录
        String storeFilePath = PathUtils.normalize(MeterConsoleConfig.getStorePath() + PathUtils.UNIX_SEPARATOR_S +
                DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()) + PathUtils.UNIX_SEPARATOR_S + FileUtils.randomFilename());
        try {
            FileUtils.writeFile(storeFilePath, bytes);
        } catch (IOException e) {
            throw new MeterConsoleException("保存文件失败");
        }
        return storeFilePath;
    }
    /**
     * 删除文件
     * @param fileKey 文件Key
     * @return
     */
    @Override
    public void delete(String fileKey) {
        FileUtils.deleteFile(fileKey);
    }
    /**
     * 下载文件
     * @param fileKey 文件Key
     * @return
     */
    @Override
    public byte[] download(String fileKey) {
        return FileUtils.readFileByBytes(fileKey);
    }
    /**
     * 下载文件
     * @param fileKey 文件Key
     * @param target 下载目标目录
     * @param downloadFileName 下载文件名
     * @return
     */
    @Override
    public File download(String fileKey, File target, String downloadFileName) {
        if(StringUtils.isBlank(fileKey)){
            throw new MeterConsoleException("fileKey不能为空");
        }
        File source = new File(fileKey);
        if(!source.exists()){
            throw new MeterConsoleException(String.format("下载的文件不存在[%s]", fileKey));
        }
        if(Objects.isNull(target)){
            throw new MeterConsoleException("下载目标路径不能为空");
        }
        if(!target.isDirectory()){
            throw new MeterConsoleException(String.format("下载目标路径不是目录[%s]", target.getPath()));
        }
        if(StringUtils.isBlank(downloadFileName)){
            downloadFileName = "downloadFileName";
        }
        File dest = new File(PathUtils.normalize(target.getPath() + PathUtils.UNIX_SEPARATOR_S + downloadFileName));
        try {
            Files.copy(source.toPath(), dest.toPath());
        } catch (IOException e) {
            throw new MeterConsoleException("下载文件失败");
        }
        return dest;
    }
}
