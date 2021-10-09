package com.csii.meter.console.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 路径工具类
 * 
 * @author zhengxuancheng@csii.com.cn
 */
public final class PathUtils {

	private PathUtils() {
	}

	public static final String SEPARATOR = File.separator;
	/** The Unix separator String. */
	public static final String UNIX_SEPARATOR_S = "/";
	/** The Windows separator String. */
	public static final String WINDOWS_SEPARATOR_S = "\\";

	/** The Unix separator character. */
	private static final char UNIX_SEPARATOR = '/';
	/** The Windows separator character. */
	private static final char WINDOWS_SEPARATOR = '\\';

	/** Class文件扩展名 */
	public static final String CLASS_EXT = ".class";
	/** Jar文件扩展名 */
	public static final String JAR_FILE_EXT = ".jar";
	/** 在Jar中的路径jar的扩展名形式 */
	public static final String JAR_PATH_EXT = ".jar!";
	/** 当Path为文件形式时, path会加入一个表示文件的前缀 */
	public static final String PATH_FILE_PRE = "file:";

	/**
	 * 列出目录文件<br>
	 * 给定的绝对路径不能是压缩包中的路径
	 * 
	 * @param path
	 *            目录绝对路径或者相对路径
	 * @return 文件列表（包含目录）
	 */
	public static File[] ls(String path) {
		if (path == null) {
			return null;
		}
		File file = new File(path);
		if (file.isDirectory()) {
			return file.listFiles();
		}
		throw new RuntimeException(String.format("Path [%s] is not directory!", path));
	}

	/**
	 * 上一级目录
	 * 
	 * @return
	 */
	public static String roll(String filePath) {
		if (StrUtils.isEmpty(filePath)) {
			return null;
		}
		int indexOfLastSeparator = indexOfLastSeparator(filePath);
		if (indexOfLastSeparator >= 0) {
			return StrUtils.substring(filePath, 0, indexOfLastSeparator + 1);
		} else {
			return UNIX_SEPARATOR_S;
		}

	}

	/**
	 * 获取文件名，如果有后缀，则去掉，如果是目录，返回目录名称
	 * 
	 * @author zhengxuancheng@csii.com.cn
	 * @date 2017年4月22日 下午11:34:49
	 * @param file
	 * @return
	 */
	public static String getFileName(String file) {
		File f = new File(file);
		return getFileName(f);
	}

	/**
	 * 获取文件名，如果有后缀，则去掉，如果是目录，返回目录名称
	 * 
	 * @author zhengxuancheng@csii.com.cn
	 * @date 2017年4月22日 下午11:34:49
	 * @param file
	 * @return
	 */
	public static String getFileName(File file) {
		String fileName = file.getName();
		if (StrUtils.contains(fileName, StrUtils.DOT)) {
			return StrUtils.substring(fileName, 0, StrUtils.lastIndexOfIgnoreCase(fileName, StrUtils.DOT));
		}
		return fileName;
	}

	/**
	 * 列出目录文件<br>
	 * 给定的绝对路径不能是压缩包中的路径
	 * 
	 * @param file
	 * @return 文件列表（包含目录）
	 */
	public static File[] ls(File file) {
		if (file == null) {
			return null;
		}
		if (file.isDirectory()) {
			return file.listFiles();
		}
		throw new RuntimeException(String.format("File [%s] is not directory!", file));
	}

	/**
	 * 列出目录下所有文件，不含目录<br>
	 * 给定的绝对路径不能是压缩包中的路径
	 * 
	 * @param file
	 * @return 文件列表（不包含目录）
	 */

	public static List<File> lsFiles(File file) {
		List<File> fileList = new ArrayList<File>();
		if (file == null) {
			return fileList;
		}
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File fi : files) {
					if (fi != null && fi.isFile()) {
						fileList.add(fi);
					}
				}
			}
			return fileList;
		}
		throw new RuntimeException(String.format("File [%s] is not directory!", file));
	}

	/**
	 * 文件是否为空<br>
	 * 目录：里面没有文件时为空 文件：文件大小为0时为空
	 * 
	 * @param file
	 *            文件
	 * @return 是否为空，当提供非目录时，返回false
	 */
	public static boolean isEmpty(File file) {
		if (null == file) {
			return true;
		}

		if (file.isDirectory()) {
			String[] subFiles = file.list();
			if (subFiles == null || subFiles.length == 0) {
				return true;
			}
		} else if (file.isFile()) {
			return file.length() <= 0;
		}

		return false;
	}

	/**
	 * 目录是否为空
	 * 
	 * @param file
	 *            目录
	 * @return 是否为空，当提供非目录时，返回false
	 */
	public static boolean isNotEmpty(File file) {
		return false == isEmpty(file);
	}

	/**
	 * 目录是否为空
	 * 
	 * @param dirPath
	 *            目录
	 * @return 是否为空
	 */
	public static boolean isDirEmpty(Path dirPath) {
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath)) {
			return false == dirStream.iterator().hasNext();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 目录是否为空
	 * 
	 * @param dir
	 *            目录
	 * @return 是否为空
	 */
	public static boolean isDirEmpty(File dir) {
		return isDirEmpty(dir.toPath());
	}

	/**
	 * 递归遍历目录以及子目录中的所有文件<br>
	 * 如果提供file为文件，直接返回过滤结果
	 * 
	 * @param file
	 *            当前遍历文件或目录
	 * @param fileFilter
	 *            文件过滤规则对象，选择要保留的文件，只对文件有效，不过滤目录
	 */
	public static List<File> loopFiles(File file, FileFilter fileFilter) {
		List<File> fileList = new ArrayList<File>();
		if (file == null) {
			return fileList;
		} else if (file.exists() == false) {
			return fileList;
		}

		if (file.isDirectory()) {
			for (File tmp : file.listFiles()) {
				fileList.addAll(loopFiles(tmp, fileFilter));
			}
		} else {
			if (null == fileFilter || fileFilter.accept(file)) {
				fileList.add(file);
			}
		}

		return fileList;
	}

	/**
	 * 递归遍历目录以及子目录中的所有文件
	 * 
	 * @param file
	 *            当前遍历文件
	 */
	public static List<File> loopFiles(File file) {
		return loopFiles(file, null);
	}

	/**
	 * 获得指定目录下所有文件<br>
	 * 不会扫描子目录
	 * 
	 * @param path
	 *            相对ClassPath的目录或者绝对路径目录
	 * @return 文件路径列表（如果是jar中的文件，则给定类似.jar!/xxx/xxx的路径）
	 * @throws IOException
	 */
	public static List<String> listFileNames(String path) {
		if (path == null) {
			return null;
		}
		if (path.endsWith(String.valueOf(UNIX_SEPARATOR)) == false) {
			path = path + UNIX_SEPARATOR;
		}

		List<String> paths = new ArrayList<String>();
		int index = path.lastIndexOf(PathUtils.JAR_PATH_EXT);
		try {
			if (index == -1) {
				// 普通目录路径
				File[] files = ls(path);
				for (File file : files) {
					if (file.isFile()) {
						paths.add(file.getName());
					}
				}
			} else {
				// jar文件中的路径
				index = index + PathUtils.JAR_FILE_EXT.length();
				final String jarPath = path.substring(0, index);
				final String subPath = path.substring(index + 2);
				for (JarEntry entry : Collections.list(new JarFile(jarPath).entries())) {
					final String name = entry.getName();
					if (name.startsWith(subPath)) {
						String nameSuffix = StrUtils.removePrefix(name, subPath);
						if (nameSuffix.contains(String.valueOf(UNIX_SEPARATOR)) == false) {
							paths.add(nameSuffix);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("Can not read file path of [%s]", path), e);
		}
		return paths;
	}

	/**
	 * 获取标准的绝对路径
	 * 
	 * @param file
	 *            文件
	 * @return 绝对路径
	 */
	public static String getAbsolutePath(File file) {
		if (file == null) {
			return null;
		}

		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			return file.getAbsolutePath();
		}
	}

	/**
	 * 判断是否为目录，如果path为null，则返回false
	 * 
	 * @param path
	 *            文件路径
	 * @return 如果为目录true
	 */
	public static boolean isDirectory(String path) {
		return (path == null) ? false : new File(path).isDirectory();
	}

	/**
	 * 判断是否为目录，如果file为null，则返回false
	 * 
	 * @param file
	 *            文件
	 * @return 如果为目录true
	 */
	public static boolean isDirectory(File file) {
		return (file == null) ? false : file.isDirectory();
	}

	/**
	 * 判断是否为文件，如果path为null，则返回false
	 * 
	 * @param path
	 *            文件路径
	 * @return 如果为文件true
	 */
	public static boolean isFile(String path) {
		return (path == null) ? false : new File(path).isFile();
	}

	/**
	 * 判断是否为文件，如果file为null，则返回false
	 * 
	 * @param file
	 *            文件
	 * @return 如果为文件true
	 */
	public static boolean isFile(File file) {
		return (file == null) ? false : file.isFile();
	}

	/**
	 * 判断文件扩展名是否是指定扩展名
	 * 
	 * @param file
	 *            文件
	 * @return 如果为文件true
	 */
	public static boolean isFile(File file, String ext) {
		if (file == null) {
			return false;
		}
		if (file.isFile()) {
			return StrUtils.endsWithIgnoreCase(file.getName(), ext);
		}
		return false;
	}

	/**
	 * 获得最后一个文件路径分隔符的位置
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 最后一个文件路径分隔符的位置
	 */
	public static int indexOfLastSeparator(String filePath) {
		if (filePath == null) {
			return -1;
		}
		int lastUnixPos = filePath.lastIndexOf(UNIX_SEPARATOR);
		int lastWindowsPos = filePath.lastIndexOf(WINDOWS_SEPARATOR);
		return (lastUnixPos >= lastWindowsPos) ? lastUnixPos : lastWindowsPos;
	}

	/**
	 * 修复路径<br>
	 * 1. 统一用 / <br>
	 * 2. 多个 / 转换为一个 3. 去除两边空格 4. .. 和 . 转换为绝对路径 5. 去掉前缀，例如file:
	 * 
	 * @param path
	 *            原路径
	 * @return 修复后的路径
	 */
	public static String normalize(String path) {
		if (path == null) {
			return null;
		}
		String pathToUse = path.replaceAll("[/\\\\]{1,}", StrUtils.SLASH).trim();

		if (OSinfoUtils.isWindows() && (pathToUse.startsWith("\\") || pathToUse.startsWith("/"))) {
			pathToUse = pathToUse.substring(1);
		}

		int prefixIndex = pathToUse.indexOf(StrUtils.COLON);
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			if (prefix.contains(StrUtils.SLASH)) {
				prefix = "";
			} else {
				pathToUse = pathToUse.substring(prefixIndex + 1);
			}
		}
		if (pathToUse.startsWith(StrUtils.SLASH)) {
			prefix = prefix + SEPARATOR;
			pathToUse = pathToUse.substring(1);
		}

		List<String> pathList = Arrays.asList(StrUtils.split(pathToUse, StrUtils.C_SLASH));
		List<String> pathElements = new LinkedList<String>();
		int tops = 0;

		for (int i = pathList.size() - 1; i >= 0; i--) {
			String element = pathList.get(i);
			if (StrUtils.DOT.equals(element)) {
				// 当前目录，丢弃
			} else if (StrUtils.DOUBLE_DOT.equals(element)) {
				tops++;
			} else {
				if (tops > 0) {
					// Merging path element with element corresponding to top
					// path.
					tops--;
				} else {
					// Normal path element found.
					pathElements.add(0, element);
				}
			}
		}

		// Remaining top paths need to be retained.
		for (int i = 0; i < tops; i++) {
			pathElements.add(0, StrUtils.DOUBLE_DOT);
		}

		return prefix + ArrayUtils.join(pathElements, SEPARATOR);
	}

	/**
	 * 修复路径<br>
	 * 1. 统一用 / <br>
	 * 2. 多个 / 转换为一个 3. 去除两边空格 4. .. 和 . 转换为绝对路径 5. 去掉前缀，例如file:
	 * 
	 * @param path
	 *            原路径
	 * @return 修复后的路径
	 */
	public static String normalizeUNIX(String path) {
		if (path == null) {
			return null;
		}
		String pathToUse = path.replaceAll("[/\\\\]{1,}", StrUtils.SLASH).trim();

		int prefixIndex = pathToUse.indexOf(StrUtils.COLON);
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			if (prefix.contains(StrUtils.SLASH)) {
				prefix = "";
			} else {
				pathToUse = pathToUse.substring(prefixIndex + 1);
			}
		}
		if (pathToUse.startsWith(StrUtils.SLASH)) {
			prefix = prefix + UNIX_SEPARATOR_S;
			pathToUse = pathToUse.substring(1);
		}

		List<String> pathList = Arrays.asList(StrUtils.split(pathToUse, StrUtils.C_SLASH));
		List<String> pathElements = new LinkedList<String>();
		int tops = 0;

		for (int i = pathList.size() - 1; i >= 0; i--) {
			String element = pathList.get(i);
			if (StrUtils.DOT.equals(element)) {
				// 当前目录，丢弃
			} else if (StrUtils.DOUBLE_DOT.equals(element)) {
				tops++;
			} else {
				if (tops > 0) {
					// Merging path element with element corresponding to top
					// path.
					tops--;
				} else {
					// Normal path element found.
					pathElements.add(0, element);
				}
			}
		}

		// Remaining top paths need to be retained.
		for (int i = 0; i < tops; i++) {
			pathElements.add(0, StrUtils.DOUBLE_DOT);
		}

		return prefix + ArrayUtils.join(pathElements, UNIX_SEPARATOR_S);
	}

	/**
	 * 修复路径<br>
	 * 1. 统一用 / <br>
	 * 2. 多个 / 转换为一个 3. 去除两边空格 4. .. 和 . 转换为绝对路径 5. 去掉前缀，例如file:
	 * 
	 * @param path
	 *            原路径
	 * @return 修复后的路径
	 */
	public static String normalizeWINDOWS(String path) {
		if (path == null) {
			return null;
		}
		String pathToUse = path.replaceAll("[/\\\\]{1,}", StrUtils.SLASH).trim();

		int prefixIndex = pathToUse.indexOf(StrUtils.COLON);
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			if (prefix.contains(StrUtils.SLASH)) {
				prefix = "";
			} else {
				pathToUse = pathToUse.substring(prefixIndex + 1);
			}
		}
		if (pathToUse.startsWith(StrUtils.SLASH)) {
			prefix = prefix + WINDOWS_SEPARATOR_S;
			pathToUse = pathToUse.substring(1);
		}

		List<String> pathList = Arrays.asList(StrUtils.split(pathToUse, StrUtils.C_SLASH));
		List<String> pathElements = new LinkedList<String>();
		int tops = 0;

		for (int i = pathList.size() - 1; i >= 0; i--) {
			String element = pathList.get(i);
			if (StrUtils.DOT.equals(element)) {
				// 当前目录，丢弃
			} else if (StrUtils.DOUBLE_DOT.equals(element)) {
				tops++;
			} else {
				if (tops > 0) {
					// Merging path element with element corresponding to top
					// path.
					tops--;
				} else {
					// Normal path element found.
					pathElements.add(0, element);
				}
			}
		}

		// Remaining top paths need to be retained.
		for (int i = 0; i < tops; i++) {
			pathElements.add(0, StrUtils.DOUBLE_DOT);
		}

		return prefix + ArrayUtils.join(pathElements, WINDOWS_SEPARATOR_S);
	}

	/**
	 * 获得相对子路径
	 * 
	 * @param rootDir
	 *            绝对父路径
	 * @param filePath
	 *            文件路径
	 * @return 相对子路径
	 */
	public static String subPath(String rootDir, String filePath) {
		return subPath(rootDir, new File(filePath));
	}

	/**
	 * 获得相对子路径
	 * 
	 * @param rootDir
	 *            绝对父路径
	 * @param file
	 *            文件
	 * @return 相对子路径
	 */
	public static String subPath(String rootDir, File file) {
		if (StrUtils.isEmpty(rootDir)) {
		}

		String subPath = null;
		try {
			subPath = file.getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		if (StrUtils.isNotEmpty(rootDir) && StrUtils.isNotEmpty(subPath)) {
			rootDir = normalize(rootDir);
			subPath = normalize(subPath);

			if (subPath != null && subPath.toLowerCase().startsWith(subPath.toLowerCase())) {
				subPath = subPath.substring(rootDir.length() + 1);
			}
		}
		return subPath;
	}

	/**
	 * 可读的文件大小
	 * 
	 * @param file
	 *            文件
	 * @return 大小
	 */
	public static String humanFileSize(File file) {
		return humanFileSize(file.length());
	}

	/**
	 * 可读的文件大小<br>
	 * 参考
	 * http://stackoverflow.com/questions/3263892/format-file-size-as-mb-gb-etc
	 * 
	 * @param size
	 *            Long类型大小
	 * @return 大小
	 */
	public static String humanFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "B", "kB", "MB", "GB", "TB", "EB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.##").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

	public static void createPath(File file) {
		File parentFile = file.getParentFile();
		if (null != parentFile && !parentFile.exists()) {
			parentFile.mkdirs();
			createPath(parentFile);
		}
	}

	public static void createPath(String filePath) {
		createPath(new File(filePath));
	}


	/**
	 * 删除整个目录
	 * @param folder
	 * @throws Exception
	 */
	public static void deleteFolder(File folder) {
		if (!folder.exists()) {
			return;
		}
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					//递归直到目录下没有文件
					deleteFolder(file);
				} else {
					//删除
					file.delete();
				}
			}
		}
		//删除
		folder.delete();
	}

	public static String getRandomDir(){
		//生成临时目录，时间搓加UUID
		LocalDateTime nowDate = LocalDateTime.now();
		String timeDir = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(nowDate) +"_"+ UUID.randomUUID();
		return timeDir;
	}

}
