package com.csii.meter.console.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * 数组工具类
 * 
 * @author taphoon
 *
 */
public class ArrayUtils {
	private ArrayUtils() {
	}

	/**
	 * 新建一个数组
	 * <p>
	 * 
	 * <pre>
	 * <code>
	 * int[] intArray=newArray(int.class,1); //new int[1]
	 * Object[] objArray=newArray(Object.class,1); //new Object[1]
	 * </code>
	 * </pre>
	 * 
	 * @param componentType 数组元素类型
	 * @param length        数组长度
	 * @return 返回元素为componentType，长度为length的数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newArray(Class<?> componentType, int length) {
		Objects.requireNonNull(componentType, "argument componentType is null");
		return (T) Array.newInstance(componentType, length);
	}

	/**
	 * 合并多个数组
	 * <p>
	 * 
	 * <pre>
	 * <code>
	 * int[] newArray=ArrayUtils.merge(new int[]{1,2},new int[]{3,4},new int[]{5,6});
	 * System.out.println(Arrays.toString(newArray);//输出[1,2,3,4,5,6]
	 * </code>
	 * </pre>
	 * </p>
	 * 
	 * @param arrays 要合并的数组
	 * @return 合并后的数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T merge(T... arrays) {
		if (arrays == null) {
			return null;
		}
		Class<?> arrayClass = null;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				arrayClass = arrays[i].getClass();
				break;
			}
		}
		if (arrayClass == null) {
			// 全部是null
			return null;
		}
		checkArrayType(arrayClass);
		if (Array.getLength(arrays) == 1) {
			return arrays[0];
		}
		int newLength = 0;
		int[] lengthArray = new int[arrays.length];
		for (int i = 0; i < lengthArray.length; ++i) {
			lengthArray[i] = arrays[i] == null ? 0 : Array.getLength(arrays[i]);
			newLength += lengthArray[i];
		}

		T result = newArray(arrayClass.getComponentType(), newLength);
		int off = 0;
		for (int i = 0; i < lengthArray.length; ++i) {
			if (lengthArray[i] != 0) {
				System.arraycopy(arrays[i], 0, result, off, lengthArray[i]);
				off += lengthArray[i];
			}
		}
		return result;
	}

	public static byte[] merge(byte[]... arrays) {
		if (arrays == null) {
			return new byte[0];
		}

		int total = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				total += arrays[i].length;
			}
		}
		byte[] result = new byte[total];
		int offset = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}
		return result;
	}

	public static char[] merge(char[]... arrays) {
		if (arrays == null) {
			return new char[0];
		}

		int total = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				total += arrays[i].length;
			}
		}
		char[] result = new char[total];
		int offset = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}
		return result;
	}

	public static boolean[] merge(boolean[]... arrays) {
		if (arrays == null) {
			return new boolean[0];
		}

		int total = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				total += arrays[i].length;
			}
		}
		boolean[] result = new boolean[total];
		int offset = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}
		return result;
	}

	public static int[] merge(int[]... arrays) {
		if (arrays == null) {
			return new int[0];
		}

		int total = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				total += arrays[i].length;
			}
		}
		int[] result = new int[total];
		int offset = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}
		return result;
	}

	public static long[] merge(long[]... arrays) {
		if (arrays == null) {
			return new long[0];
		}

		int total = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				total += arrays[i].length;
			}
		}
		long[] result = new long[total];
		int offset = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}
		return result;
	}

	public static short[] merge(short[]... arrays) {
		if (arrays == null) {
			return new short[0];
		}

		int total = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				total += arrays[i].length;
			}
		}
		short[] result = new short[total];
		int offset = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}
		return result;
	}

	public static float[] merge(float[]... arrays) {
		if (arrays == null) {
			return new float[0];
		}

		int total = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				total += arrays[i].length;
			}
		}
		float[] result = new float[total];
		int offset = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}
		return result;
	}

	public static double[] merge(double[]... arrays) {
		if (arrays == null) {
			return new double[0];
		}

		int total = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				total += arrays[i].length;
			}
		}
		double[] result = new double[total];
		int offset = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, result, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}
		return result;
	}

	/**
	 * 扩展数组长度
	 * 
	 * @param array  初始数组
	 * @param length 需增加长度
	 * @return 返回长度为array.length+length的新数组，索引0到array.length是array对应索引的值
	 */
	public static <T> T expand(T array, int length) {
		Objects.requireNonNull(array, "array is null");
		checkArrayType(array.getClass());
		int newLen = Array.getLength(array) + length;
		T result = newArray(array.getClass().getComponentType(), newLen);
		System.arraycopy(array, 0, result, 0, Array.getLength(array));
		return result;
	}

	/**
	 * 重设数组长度，当newLength小于数组array的长度时截断数组，大于时数组长度增加至newLength
	 * 
	 * @param array     重设长度的数组
	 * @param newLength 数组新长度
	 * @return 长度为newLength的新数组
	 */
	public static <T> T resize(T array, int newLength) {
		Objects.requireNonNull(array, "array is null");
		checkArrayType(array.getClass());
		int copiedLength = Array.getLength(array);
		if (copiedLength == newLength) {
			return array;
		}
		T result = newArray(array.getClass().getComponentType(), newLength);
		if (copiedLength > newLength) {
			copiedLength = newLength;
		}
		System.arraycopy(array, 0, result, 0, copiedLength);
		return result;
	}

	private static void checkArrayType(Class<?> arrayClass) {
		if (!arrayClass.isArray()) {
			throw new IllegalArgumentException(
					arrayClass + " is not an array type,examples: int[].class,Object[].class");
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] append(T[] origin, T... appened) {
		return merge(origin, appened);
	}

	public static byte[] append(byte[] origin, byte... appened) {
		return merge(origin, appened);
	}

	public static char[] append(char[] origin, char... appened) {
		return merge(origin, appened);
	}

	public static boolean[] append(boolean[] origin, boolean... appened) {
		return merge(origin, appened);
	}

	public static short[] append(short[] origin, short... appened) {
		return merge(origin, appened);
	}

	public static int[] append(int[] origin, int... appened) {
		return merge(origin, appened);
	}

	public static long[] append(long[] origin, long... appened) {
		return merge(origin, appened);
	}

	public static float[] append(float[] origin, float... appened) {
		return merge(origin, appened);
	}

	public static double[] append(double[] origin, double... appened) {
		return merge(origin, appened);
	}

	public static <T> boolean isArray(T obj) {
		return obj != null && obj.getClass().isArray();
	}

	public static <T> boolean contains(T array, Object value) {
		return indexOf(array, value) != -1;
	}

	public static <T> int indexOf(T array, Object value) {
		if (isArray(array)) {
			for (int i = 0; i < Array.getLength(array); ++i) {
				if (Objects.equals(Array.get(array, i), value)) {
					return i;
				}
			}
		}
		return -1;
	}

	public static String toString(Object obj) {
		if (null == obj) {
			return null;
		}
		if (isArray(obj)) {
			try {
				return Arrays.deepToString((Object[]) obj);
			} catch (Exception e) {
				final String className = obj.getClass().getComponentType().getName();
				if (StrUtils.equalsIgnoreCase("long", className)) {
					return Arrays.toString((long[]) obj);
				} else if (StrUtils.equalsIgnoreCase("int", className)) {
					return Arrays.toString((int[]) obj);
				} else if (StrUtils.equalsIgnoreCase("short", className)) {
					return Arrays.toString((short[]) obj);
				} else if (StrUtils.equalsIgnoreCase("char", className)) {
					return Arrays.toString((char[]) obj);
				} else if (StrUtils.equalsIgnoreCase("byte", className)) {
					return Arrays.toString((byte[]) obj);
				} else if (StrUtils.equalsIgnoreCase("boolean", className)) {
					return Arrays.toString((boolean[]) obj);
				} else if (StrUtils.equalsIgnoreCase("float", className)) {
					return Arrays.toString((float[]) obj);
				} else if (StrUtils.equalsIgnoreCase("double", className)) {
					return Arrays.toString((double[]) obj);
				} else {
					throw new RuntimeException(e);
				}

			}
		}
		return obj.toString();
	}

	public static boolean isEmpty(final boolean... array) {
		return array == null || array.length == 0;
	}

	public static boolean isEmpty(final long... array) {
		return array == null || array.length == 0;
	}

	public static boolean isEmpty(final int... array) {
		return array == null || array.length == 0;
	}

	public static boolean isEmpty(final double... array) {
		return array == null || array.length == 0;
	}

	public static boolean isEmpty(final float... array) {
		return array == null || array.length == 0;
	}

	public static boolean isEmpty(final byte... array) {
		return array == null || array.length == 0;
	}

	public static boolean isEmpty(final char... array) {
		return array == null || array.length == 0;
	}

	public static boolean isEmpty(final short... array) {
		return array == null || array.length == 0;
	}

	public static boolean isEmpty(final Object array) {
		if (null == array) {
			return true;
		} else if (isArray(array)) {
			return 0 == Array.getLength(array);
		}
		throw new RuntimeException("Object to provide is not a Array !");
	}

	public static <T> boolean isEmpty(final T... array) {
		return array == null || array.length == 0;
	}

	/**
	 * <p>
	 * 以{@code separator}为分隔符将数组转换为字符串
	 * </p>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(boolean[] array, String separator) {
		if (null == array) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (boolean item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 以{@code separator}为分隔符将数组转换为字符串
	 * </p>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(byte[] array, String separator) {
		if (null == array) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (byte item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 以{@code separator}为分隔符将数组转换为字符串
	 * </p>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(char[] array, String separator) {
		if (null == array) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (char item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 以{@code separator}为分隔符将数组转换为字符串
	 * </p>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(double[] array, String separator) {
		if (null == array) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (double item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 以{@code separator}为分隔符将数组转换为字符串
	 * </p>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(float[] array, String separator) {
		if (null == array) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (float item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 以{@code separator}为分隔符将数组转换为字符串
	 * </p>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(int[] array, String separator) {
		if (null == array) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (int item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 以{@code separator}为分隔符将数组转换为字符串
	 * </p>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(long[] array, String separator) {
		if (null == array) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (long item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 以{@code separator}为分隔符将数组转换为字符串
	 * </p>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(short[] array, String separator) {
		if (null == array) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (short item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 以{@code separator}为分隔符将数组转换为字符串
	 * </p>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(Object[] array, String separator) {
		if (null == array) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (Object item : array) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}
			sb.append(item);
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 以 {@code separator} 为分隔符将集合转换为字符串
	 * </p>
	 * 
	 * @param <T>         被处理的集合
	 * @param iterable    {@link Iterable}
	 * @param conjunction 分隔符
	 * @return 连接后的字符串
	 */
	public static <T> String join(Iterable<T> iterable, String separator) {
		if (null == iterable) {
			return null;
		}
		return join(iterable.iterator(), separator);
	}

	/**
	 * <p>
	 * 以 {@code separator} 为分隔符将集合转换为字符串
	 * </p>
	 * 
	 * @param <T>         被处理的集合
	 * @param iterator    集合
	 * @param conjunction 分隔符
	 * @return 连接后的字符串
	 */
	public static <T> String join(Iterator<T> iterator, String separator) {
		if (null == iterator) {
			return null;
		}

		final StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		T item;
		while (iterator.hasNext()) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(separator);
			}

			item = iterator.next();
			if (item.getClass().isArray()) {
				sb.append(ArrayUtils.join(ArrayUtils.wrap(item), separator));
			} else if (item instanceof Iterable<?>) {
				sb.append(join((Iterable<?>) item, separator));
			} else if (item instanceof Iterator<?>) {
				sb.append(join((Iterator<?>) item, separator));
			} else {
				sb.append(item);
			}
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 将对象{@code obj}包装成数组对象，被包装的对象必须是数组（可以是对象数组或者基本类型数组）
	 * </p>
	 * 
	 * @param obj
	 * @return
	 */
	public static Object[] wrap(Object obj) {
		if (isArray(obj)) {
			try {
				return (Object[]) obj;
			} catch (Exception e) {
				final String className = obj.getClass().getComponentType().getName();
				if (StrUtils.equalsIgnoreCase("long", className)) {
					return wrap((long[]) obj);
				} else if (StrUtils.equalsIgnoreCase("int", className)) {
					return wrap((int[]) obj);
				} else if (StrUtils.equalsIgnoreCase("short", className)) {
					return wrap((short[]) obj);
				} else if (StrUtils.equalsIgnoreCase("char", className)) {
					return wrap((char[]) obj);
				} else if (StrUtils.equalsIgnoreCase("byte", className)) {
					return wrap((byte[]) obj);
				} else if (StrUtils.equalsIgnoreCase("boolean", className)) {
					return wrap((boolean[]) obj);
				} else if (StrUtils.equalsIgnoreCase("float", className)) {
					return wrap((float[]) obj);
				} else if (StrUtils.equalsIgnoreCase("double", className)) {
					return wrap((double[]) obj);
				} else {
					throw new RuntimeException(e);
				}
			}
		}
		throw new RuntimeException(String.format("[%s] is not Array!", obj.getClass()));
	}

}
