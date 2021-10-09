package com.csii.meter.console.utils;

import com.csii.meter.console.config.ConfigConstants;
import com.csii.meter.console.config.MeterConsoleConfig;
import com.csii.meter.console.exception.MeterConsoleException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * 文件上传工具类
 */
public class FileUploadUtils {

    private static int counter = 0;

    /**
     * 以默认配置进行文件上传
     *
     * @param file 上传的文件
     * @return 文件名称
     * @throws Exception
     */
    public static final String upload(MultipartFile file) {
        return upload(MeterConsoleConfig.getTempPath(), file, MeterConsoleConfig.getStrArray(ConfigConstants.DEFAULT_ALLOWED_EXTENSION));
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String upload(String baseDir, MultipartFile file) {
        return upload(baseDir, file, MeterConsoleConfig.getStrArray(ConfigConstants.DEFAULT_ALLOWED_EXTENSION));
    }

    /**
     * 文件上传
     * @param file 上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 比如读写文件出错时
     */
    public static final String upload(MultipartFile file, String[] allowedExtension) {
        return upload(MeterConsoleConfig.getTempPath(), file, allowedExtension);
    }


    /**
     * 文件上传
     * @param baseDir 相对应用的基目录
     * @param file 上传的文件
     * @param allowedExtension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 比如读写文件出错时
     */
    public static final String upload(String baseDir, MultipartFile file, String[] allowedExtension)
            throws MeterConsoleException {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > MeterConsoleConfig.getLong(ConfigConstants.DEFAULT_FILE_NAME_LENGTH)) {
            throw new MeterConsoleException(String.format("文件名不能超过%s个字符", MeterConsoleConfig.getLong(ConfigConstants.DEFAULT_FILE_NAME_LENGTH)));
        }
        assertAllowed(file, allowedExtension, MeterConsoleConfig.getLong(ConfigConstants.UPLOAD_MAX_SIZE));
        String fileName = file.getOriginalFilename();
        String filePath = getFilePath(baseDir);
        File desc = null;
        try {
            desc = getAbsoluteFile(filePath, fileName);
            FileUtils.writeFile(desc, file.getBytes());
        } catch (IOException e) {
            throw new MeterConsoleException("文件保存失败", e);
        }
        return filePath + PathUtils.UNIX_SEPARATOR_S + fileName;
    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName = encodingFilename(fileName) + "." + extension;
        return fileName;
    }

    private static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        File desc = new File(uploadDir + File.separator + fileName);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    private static final String getFilePath(String baseDir){
        return PathUtils.normalize(baseDir + PathUtils.UNIX_SEPARATOR_S + PathUtils.getRandomDir());
    }

    /**
     * 编码文件名
     */
    public static final String encodingFilename(String fileName) {
        fileName = fileName.replace("_", " ");
        fileName = EncryptUtils.getInstance().MD5(fileName + System.nanoTime() + counter++);
        return fileName;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws MeterConsoleException
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension, long maxSize)
            throws MeterConsoleException {
        long size = file.getSize();
        if (maxSize != -1 && size > 1024*1024*maxSize) {
            throw new MeterConsoleException(String.format("文件太大,限制文件最大为：%sM", maxSize));
        }
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            throw new MeterConsoleException
                    ("文件名 : [" + fileName + "], 文件格式不允许上传 : [" + extension + "], 只允许上传以下格式 : " + Arrays.toString(allowedExtension));
        }

    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件名的后缀
     * 
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        return getExtension(file.getOriginalFilename());
    }

    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        } else {
            int index = indexOfExtension(filename);
            return index == -1 ? "" : filename.substring(index + 1);
        }
    }

    private static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int extensionPos = filename.lastIndexOf(46);
            int lastSeparator = indexOfLastSeparator(filename);
            return lastSeparator > extensionPos ? -1 : extensionPos;
        }
    }

    private static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int lastUnixPos = filename.lastIndexOf(47);
            int lastWindowsPos = filename.lastIndexOf(92);
            return Math.max(lastUnixPos, lastWindowsPos);
        }
    }
}