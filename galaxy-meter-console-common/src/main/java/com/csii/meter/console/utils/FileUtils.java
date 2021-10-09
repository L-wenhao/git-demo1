package com.csii.meter.console.utils;

import com.csii.meter.console.exception.MeterConsoleException;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件处理工具类
 * @author liuya
 */
public class FileUtils {
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static int counter = 0;


    public static void writeFile(String filePath, byte[] bytes) throws IOException {
        File file = new File(filePath);
        PathUtils.createPath(file);
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(filePath, false));
            out.write(bytes);
        } catch (Exception e) {
            throw new MeterConsoleException(String.format("文件写入失败,文件路径：%s", filePath));
        } finally {
            if (Objects.nonNull(out)) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("OutputStream关闭异常");
                }
            }
        }
    }

    public static void writeFile(File file, byte[] bytes) throws IOException {
        PathUtils.createPath(file);
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(bytes);
        } catch (Exception e) {
            throw new MeterConsoleException(String.format("文件写入失败,文件路径：%s", file.getAbsolutePath()));
        } finally {
            if (Objects.nonNull(out)) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("OutputStream关闭异常");
                }
            }
        }
    }

    /**
     * 读取文件
     * @param filePath
     */
    public static byte[] readFileByBytes(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new MeterConsoleException(String.format("文件[%s]不存在", filePath));
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new MeterConsoleException(String.format("读取文件[%s]异常", filePath));
        } finally {
            try {
                if(Objects.nonNull(in))
                    in.close();
                if(Objects.nonNull(bos))
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 删除文件
     * 
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 文件名称验证
     * 
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename) {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * 下载文件名重新编码
     * 
     * @param request 请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        } else if (agent.contains("Chrome")) {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }


    /**
     * 随机文件名
     */
    public static final String randomFilename() {
        return EncryptUtils.getInstance().Base64Encode(UUIDUtils.getInstance().generateShortUuid() + System.nanoTime() + counter++);
    }

    public static final void downloadFile(HttpServletRequest request, HttpServletResponse response, byte[] fileContent, String fileName){
        if(Objects.isNull(fileContent) || fileContent.length < 0){
            return;
        }
        try {
            ServletContext context = request.getServletContext();
            String mimeType = context.getMimeType("");
            if (mimeType == null) {
                mimeType = "application/octet-stream";
                logger.error("context getMimeType is null");
            }
            response.setContentType(mimeType);
            response.setContentLength(fileContent.length);
            //设置文件格式，指定下载后的文件名（不指定会统一当作txt处理）
            response.setHeader("Content-Disposition", "attachment;filename=" + setFileDownloadHeader(request, fileName));
            response.setCharacterEncoding("utf-8");
            IOUtils.copy(new ByteArrayInputStream(fileContent), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new MeterConsoleException("文件下载失败", e);
        }
    }

    /**
     * 根据文件名查找文件，包括子目录
     * @param dir
     * @param fileName
     * @return
     */
    public static final List<File> findFile(File dir, String fileName){
        List<File> fileList = new ArrayList<>();
        if(dir == null || StringUtils.isBlank(fileName)){
            return fileList;
        }
        //判断是不是文件夹
        if(dir.isDirectory()) {
            //用数组来获取这个目录下的子文件夹
            File[] fs = dir.listFiles();
            for(File f : fs) {
                //递归遍历所有子文件夹
                fileList.addAll(findFile(f, fileName));
            }
        }
        if(StringUtils.equalsIgnoreCase(dir.getName(), fileName)) {
            fileList.add(dir);
        }
        return fileList;
    }

    /**
     * 根据文件名查找文件，包括子目录
     * @param path
     * @param fileName
     * @return
     */
    public static final List<File> findFile(String path, String fileName){
        return findFile(new File(path), fileName);
    }

    /**
     * 判断文件是否存在，如果path为null，则返回false
     *
     * @param path
     *            文件路径
     * @return 如果存在返回true
     */
    public static boolean exist(String path) {
        return (path == null) ? false : new File(path).exists();
    }

    /**
     * 判断文件是否存在，如果file为null，则返回false
     *
     * @param file
     *            文件
     * @return 如果存在返回true
     */
    public static boolean exist(File file) {
        return (file == null) ? false : file.exists();
    }

    /**
     * 解压zip压缩文件
     * @param srcFile
     * @throws Exception
     */
    public static File zipUncompress(File srcFile) {
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new MeterConsoleException(srcFile.getPath() + " 文件不存在");
        }
        InputStream is = null;
        FileOutputStream fos = null;
        String destDirPath = null;
        try {
            destDirPath = srcFile.getPath().replace(".zip", "");
            //创建压缩文件对象
            ZipFile zipFile = new ZipFile(srcFile);
            //开始解压
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    srcFile.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    is = zipFile.getInputStream(entry);
                    fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                }
            }
        }catch (Exception e){
            // 关流顺序，先打开的后关闭
            try {
                if(Objects.nonNull(fos))
                    fos.close();
                if(Objects.nonNull(is))
                    is.close();
            } catch (IOException ex) {}
            throw new MeterConsoleException("解压失败");
        }
        return new File(destDirPath);
    }
}
