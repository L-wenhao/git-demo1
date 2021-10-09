package com.csii.meter.console.service;

import java.io.File;
import java.io.IOException;

/**
 * 文件系统存储服务
 * @author liuya
 */
public interface FileSystemStoreService {

    /**
     * 保存文件
     * @param bytes
     * @return fileKey 文件Key
     */
    String saveFile(byte[] bytes);
    /**
     * 删除文件
     * @param fileKey 文件Key
     * @return
     */
    void delete(String fileKey);
    /**
     * 下载文件
     * @param fileKey 文件Key
     * @return
     */
    byte[] download(String fileKey);
    /**
     * 下载文件
     * @param fileKey 文件Key
     * @param target 下载目标目录
     * @param downloadFileName 下载文件名
     * @return
     */
    File download(String fileKey, File target, String downloadFileName);
}
