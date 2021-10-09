package com.csii.meter.console.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

/**
 * 字符串工具类
 * 
 */
public class StrUtils {

	public static final int INDEX_NOT_FOUND = -1;

	public static final char C_SPACE = ' ';
	public static final char C_TAB = '\t';
	public static final char C_DOT = '.';
	public static final char C_SLASH = '/';
	public static final char C_BACKSLASH = '\\';
	public static final char C_CR = '\r';
	public static final char C_LF = '\n';
	public static final char C_UNDERLINE = '_';
	public static final char C_COMMA = ',';
	public static final char C_DELIM_START = '{';
	public static final char C_DELIM_END = '}';
	public static final char C_BRACKET_START = '[';
	public static final char C_BRACKET_END = ']';
	public static final char C_COLON = ':';

	public static final String SPACE = " ";
	public static final String TAB = "  ";
	public static final String DOT = ".";
	public static final String DOUBLE_DOT = "..";
	public static final String SLASH = "/";
	public static final String BACKSLASH = "\\";
	public static final String EMPTY = "";
	public static final String CR = "\r";
	public static final String LF = "\n";
	public static final String CRLF = "\r\n";
	public static final String UNDERLINE = "_";
	public static final String COMMA = ",";
	public static final String DELIM_START = "{";
	public static final String DELIM_END = "}";
	public static final String BRACKET_START = "[";
	public static final String BRACKET_END = "]";
	public static final String COLON = ":";

	public static final String HTML_NBSP = "&nbsp;";
	public static final String HTML_AMP = "&amp";
	public static final String HTML_QUOTE = "&quot;";
	public static final String HTML_LT = "&lt;";
	public static final String HTML_GT = "&gt;";

	public static final String EMPTY_JSON = "{}";

	private StrUtils() {
		super();
	}

	/**
	 * <p>
	 * 字符串是否为空白
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.isBlank(null)      = true
	 * StrUtils.isBlank("")        = true
	 * StrUtils.isBlank(" ")       = true
	 * StrUtils.isBlank("str")     = false
	 * StrUtils.isBlank("  str  ") = false
	 * </pre>
	 * 
	 * @param cs
	 * @return 是否为空白
	 */
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(cs.charAt(i)) || Character.isSpaceChar(cs.charAt(i))) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param cs
	 * @return 是否非空白
	 */
	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}

	/**
	 * <p>
	 * 字符串是否为空
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.isEmpty(null)      = true
	 * StrUtils.isEmpty("")        = true
	 * StrUtils.isEmpty(" ")       = false
	 * StrUtils.isBlank("str")     = false
	 * </pre>
	 * 
	 * @param cs
	 * @return 是否为空
	 */
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * @param cs
	 * @return 是否为非空
	 */
	public static boolean isNotEmpty(final CharSequence cs) {
		return !isEmpty(cs);
	}

	/**
	 * <p>
	 * 如果字符串为{@code null},则返回{@code ""}
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String nullToEmpty(final CharSequence str) {
		return nullToDefault(str, EMPTY);
	}

	/**
	 * <p>
	 * 如果字符串为{@code ""},则返回{@code null}
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String emptyToNull(final CharSequence str) {
		return isEmpty(str) ? null : str.toString();
	}

	/**
	 * <p>
	 * 如果字符串为{@code null},则返回{@code defaultStr}
	 * </p>
	 *
	 * <pre>
	 * StrUtils.defaultString(null, "NULL")  = "NULL"
	 * StrUtils.defaultString("", "NULL")    = ""
	 * StrUtils.defaultString("str", "NULL") = "str"
	 * </pre>
	 * 
	 * @param str
	 * @param defaultStr
	 * @return
	 */
	public static String nullToDefault(final CharSequence str, final String defaultStr) {
		return (str == null) ? defaultStr : str.toString();
	}

	/**
	 * <p>
	 * 去除头尾空白,如果字符串为{@code null},依然返回{@code null}
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(final CharSequence str) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		int start = 0;
		int end = length;

		while ((start < end)
				&& (Character.isWhitespace(str.charAt(start)) || Character.isSpaceChar(str.charAt(start)))) {
			start++;
		}

		while ((start < end)
				&& (Character.isWhitespace(str.charAt(end - 1)) || Character.isSpaceChar(str.charAt(end - 1)))) {
			end--;
		}

		if ((start > 0) || (end < length)) {
			return str.toString().substring(start, end);
		}

		return str.toString();
	}

	/**
	 * <p>
	 * 去除头尾空白,如果字符串为{@code null},依然返回{@code ""}
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String trimToEmpty(final CharSequence str) {
		return str == null ? EMPTY : trim(str);
	}

	/**
	 * <p>
	 * 字符串{@code str}是否以给定字符{@code prefix}串开始
	 * </p>
	 * 
	 * @param str
	 * @param prefix
	 * @return
	 */
	public static boolean startsWith(final CharSequence str, final CharSequence prefix) {
		return startsWith(str, prefix, false);
	}

	/**
	 * <p>
	 * 字符串{@code str}是否以给定字符{@code prefix}串开始,且忽略大小写
	 * </p>
	 * 
	 * @param str
	 * @param prefix
	 * @return
	 */
	public static boolean startsWithIgnoreCase(final CharSequence str, final CharSequence prefix) {
		return startsWith(str, prefix, true);
	}

	/**
	 * <p>
	 * 字符串{@code str}是否以给定字符{@code suffix}串结尾
	 * </p>
	 * 
	 * @param str
	 * @param suffix
	 * @return
	 */
	public static boolean endsWith(final CharSequence str, final CharSequence suffix) {
		return endsWith(str, suffix, false);
	}

	/**
	 * <p>
	 * 字符串{@code str}是否以给定字符{@code suffix}串结尾,且忽略大小写
	 * </p>
	 * 
	 * @param str
	 * @param suffix
	 * @return
	 */
	public static boolean endsWithIgnoreCase(final CharSequence str, final CharSequence suffix) {
		return endsWith(str, suffix, true);
	}

	/**
	 * <p>
	 * 字符串{@code cs1}和字符串{@code cs2}是否相等
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.equals(null, null)   = true
	 * StrUtils.equals(null, "abc")  = false
	 * StrUtils.equals("abc", null)  = false
	 * StrUtils.equals("abc", "abc") = true
	 * StrUtils.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @param cs1
	 * @param cs2
	 * @return
	 */
	public static boolean equals(final CharSequence cs1, final CharSequence cs2) {
		return cs1 == null ? cs2 == null : cs1.equals(cs2);
	}

	/**
	 * <p>
	 * 字符串{@code cs1}和字符串{@code cs2}是否相等,忽略大小写
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.equalsIgnoreCase(null, null)   = true
	 * StrUtils.equalsIgnoreCase(null, "abc")  = false
	 * StrUtils.equalsIgnoreCase("abc", null)  = false
	 * StrUtils.equalsIgnoreCase("abc", "abc") = true
	 * StrUtils.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 * 
	 * @param cs1
	 * @param cs2
	 * @return
	 */
	public static boolean equalsIgnoreCase(final CharSequence cs1, final CharSequence cs2) {
		if (cs1 == null || cs2 == null) {
			return cs1 == cs2;
		} else {
			return regionMatches(cs1, true, 0, cs2, 0, Math.max(cs1.length(), cs2.length()));
		}
	}

	/**
	 * <p>
	 * 指定字符{@code searchSeq}是否在字符串{@code seq}中出现过
	 * </p>
	 * 
	 * @param seq
	 * @param searchSeq
	 * @return
	 */
	public static boolean contains(final CharSequence seq, final CharSequence searchSeq) {
		if (seq == null || searchSeq == null) {
			return false;
		}
		return seq.toString().indexOf(searchSeq.toString(), 0) >= 0;
	}

	/**
	 * *
	 * <p>
	 * 指定字符{@code searchSeq}是否在字符串{@code str}中出现过,且忽略大小写
	 * </p>
	 * 
	 * @param str
	 * @param searchStr
	 * @return
	 */
	public static boolean containsIgnoreCase(final CharSequence str, final CharSequence searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		final int len = searchStr.length();
		final int max = str.length() - len;
		for (int i = 0; i <= max; i++) {
			if (regionMatches(str, true, i, searchStr, 0, len)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * 替换字符串{@code text}中指定字符串{@code searchString}为{@code replacement}
	 * </p>
	 *
	 * <pre>
	 * StrUtils.replace(null, *, *)        = null
	 * StrUtils.replace("", *, *)          = ""
	 * StrUtils.replace("any", null, *)    = "any"
	 * StrUtils.replace("any", *, null)    = "any"
	 * StrUtils.replace("any", "", *)      = "any"
	 * StrUtils.replace("aba", "a", null)  = "aba"
	 * StrUtils.replace("aba", "a", "")    = "b"
	 * StrUtils.replace("aba", "a", "z")   = "zbz"
	 * </pre>
	 * 
	 * @param text
	 * @param searchString
	 * @param replacement
	 * @return
	 */
	public static String replace(final String text, final String searchString, final String replacement) {
		return replace(text, searchString, replacement, -1);
	}

	/**
	 * <p>
	 * 替换字符串{@code text}中指定字符串{@code searchString}为{@code replacement},替换最大次数为{@code max}
	 * </p>
	 *
	 * <pre>
	 * StrUtils.replace(null, *, *, *)         = null
	 * StrUtils.replace("", *, *, *)           = ""
	 * StrUtils.replace("any", null, *, *)     = "any"
	 * StrUtils.replace("any", *, null, *)     = "any"
	 * StrUtils.replace("any", "", *, *)       = "any"
	 * StrUtils.replace("any", *, *, 0)        = "any"
	 * StrUtils.replace("abaa", "a", null, -1) = "abaa"
	 * StrUtils.replace("abaa", "a", "", -1)   = "b"
	 * StrUtils.replace("abaa", "a", "z", 0)   = "abaa"
	 * StrUtils.replace("abaa", "a", "z", 1)   = "zbaa"
	 * StrUtils.replace("abaa", "a", "z", 2)   = "zbza"
	 * StrUtils.replace("abaa", "a", "z", -1)  = "zbzz"
	 * </pre>
	 * 
	 * @param text
	 * @param searchString
	 * @param replacement
	 * @param max
	 * @return
	 */
	public static String replace(final String text, final String searchString, final String replacement, int max) {
		return replace(text, searchString, replacement, max, false);
	}

	/**
	 * <p>
	 * 替换字符串{@code text}中满足正则{@code searchString}的字符串为{@code replacement}
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.replaceAll(null, *, *)       = null
	 * StrUtils.replaceAll("any", null, *)   = "any"
	 * StrUtils.replaceAll("any", *, null)   = "any"
	 * StrUtils.replaceAll("", "", "zzz")    = "zzz"
	 * StrUtils.replaceAll("", ".*", "zzz")  = "zzz"
	 * StrUtils.replaceAll("", ".+", "zzz")  = ""
	 * StrUtils.replaceAll("abc", "", "ZZ")  = "ZZaZZbZZcZZ"
	 * StrUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "&lt;.*&gt;", "z")      = "z\nz"
	 * StrUtils.replaceAll("&lt;__&gt;\n&lt;__&gt;", "(?s)&lt;.*&gt;", "z")  = "z"
	 * StrUtils.replaceAll("ABCabc123", "[a-z]", "_")       = "ABC___123"
	 * StrUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_")  = "ABC_123"
	 * StrUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "")   = "ABC123"
	 * StrUtils.replaceAll("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2")  = "Lorem_ipsum_dolor_sit"
	 * </pre>
	 * 
	 * @param text
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public static String replaceAll(final String text, final String regex, final String replacement) {
		if (text == null || regex == null || replacement == null) {
			return text;
		}
		return text.replaceAll(regex, replacement);
	}

	/**
	 * <p>
	 * 替换字符串{@code str}中指定字符{@code searchChar}为{@code replaceChar}
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.replaceChars(null, *, *)        = null
	 * StrUtils.replaceChars("", *, *)          = ""
	 * StrUtils.replaceChars("abcba", 'b', 'y') = "aycya"
	 * StrUtils.replaceChars("abcba", 'z', 'y') = "abcba"
	 * </pre>
	 * 
	 * @param str
	 * @param searchChar
	 * @param replaceChar
	 * @return
	 */
	public static String replaceChars(final String str, final char searchChar, final char replaceChar) {
		if (str == null) {
			return null;
		}
		return str.replace(searchChar, replaceChar);
	}

	/**
	 * <p>
	 * 替换字符串{@code str}中指定多个字符{@code searchChar}为多个{@code replaceChar},顺序对应。
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.replaceChars(null, *, *)           = null
	 * StrUtils.replaceChars("", *, *)             = ""
	 * StrUtils.replaceChars("abc", null, *)       = "abc"
	 * StrUtils.replaceChars("abc", "", *)         = "abc"
	 * StrUtils.replaceChars("abc", "b", null)     = "ac"
	 * StrUtils.replaceChars("abc", "b", "")       = "ac"
	 * StrUtils.replaceChars("abcba", "bc", "yz")  = "ayzya"
	 * StrUtils.replaceChars("abcba", "bc", "y")   = "ayya"
	 * StrUtils.replaceChars("abcba", "bc", "yzx") = "ayzya"
	 * </pre>
	 * 
	 * @param str
	 * @param searchChars
	 * @param replaceChars
	 * @return
	 */
	public static String replaceChars(final String str, final String searchChars, String replaceChars) {
		if (isEmpty(str) || isEmpty(searchChars)) {
			return str;
		}
		if (replaceChars == null) {
			replaceChars = EMPTY;
		}
		boolean modified = false;
		final int replaceCharsLength = replaceChars.length();
		final int strLength = str.length();
		final StringBuilder buf = new StringBuilder(strLength);
		for (int i = 0; i < strLength; i++) {
			final char ch = str.charAt(i);
			final int index = searchChars.indexOf(ch);
			if (index >= 0) {
				modified = true;
				if (index < replaceCharsLength) {
					buf.append(replaceChars.charAt(index));
				}
			} else {
				buf.append(ch);
			}
		}
		if (modified) {
			return buf.toString();
		}
		return str;
	}

	/**
	 * *
	 * <p>
	 * 替换字符串{@code text}中指定字符串{@code searchString}为{@code replacement}，忽略大小写。
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.replaceIgnoreCase(null, *, *)        = null
	 * StrUtils.replaceIgnoreCase("", *, *)          = ""
	 * StrUtils.replaceIgnoreCase("any", null, *)    = "any"
	 * StrUtils.replaceIgnoreCase("any", *, null)    = "any"
	 * StrUtils.replaceIgnoreCase("any", "", *)      = "any"
	 * StrUtils.replaceIgnoreCase("aba", "a", null)  = "aba"
	 * StrUtils.replaceIgnoreCase("abA", "A", "")    = "b"
	 * StrUtils.replaceIgnoreCase("aba", "A", "z")   = "zbz"
	 * </pre>
	 * 
	 * @param text
	 * @param searchString
	 * @param replacement
	 * @return
	 */
	public static String replaceIgnoreCase(String text, String searchString, String replacement) {
		return replaceIgnoreCase(text, searchString, replacement, -1);
	}

	/**
	 * <p>
	 * 替换字符串{@code text}中指定字符串{@code searchString}为{@code replacement}，忽略大小写。替换最大次数为{@code max}
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.replaceIgnoreCase(null, *, *, *)         = null
	 * StrUtils.replaceIgnoreCase("", *, *, *)           = ""
	 * StrUtils.replaceIgnoreCase("any", null, *, *)     = "any"
	 * StrUtils.replaceIgnoreCase("any", *, null, *)     = "any"
	 * StrUtils.replaceIgnoreCase("any", "", *, *)       = "any"
	 * StrUtils.replaceIgnoreCase("any", *, *, 0)        = "any"
	 * StrUtils.replaceIgnoreCase("abaa", "a", null, -1) = "abaa"
	 * StrUtils.replaceIgnoreCase("abaa", "a", "", -1)   = "b"
	 * StrUtils.replaceIgnoreCase("abaa", "a", "z", 0)   = "abaa"
	 * StrUtils.replaceIgnoreCase("abaa", "A", "z", 1)   = "zbaa"
	 * StrUtils.replaceIgnoreCase("abAa", "a", "z", 2)   = "zbza"
	 * StrUtils.replaceIgnoreCase("abAa", "a", "z", -1)  = "zbzz"
	 * </pre>
	 * 
	 * @param text
	 * @param searchString
	 * @param replacement
	 * @param max
	 * @return
	 */
	public static String replaceIgnoreCase(String text, String searchString, String replacement, int max) {
		return replace(text, searchString, replacement, max, true);
	}

	/**
	 * <p>
	 * 反转字符串{@code str}
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.reverse(null)  = null
	 * StrUtils.reverse("")    = ""
	 * StrUtils.reverse("bat") = "tab"
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String reverse(final String str) {
		if (str == null) {
			return null;
		}
		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * <p>
	 * 去掉字符串{@code str}}指定前缀 {@code prefix}，若前缀不是 {@code prefix}， 返回原字符串。
	 * </p>
	 * 
	 * @param str    字符串
	 * @param prefix 前缀
	 * @return
	 */
	public static String removePrefix(final String str, String prefix) {
		if (isEmpty(str) || isEmpty(prefix)) {
			return str;
		}

		if (str.startsWith(prefix)) {
			return substring(str, prefix.length());
		}
		return str;
	}

	/**
	 * <p>
	 * 从字符串{@code text}移除所有{@code remove}
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.removeAll(null, *)               = null
	 * StrUtils.removeAll("any", null)           = "any"
	 * StrUtils.removeAll("any", "")             = "any"
	 * StrUtils.removeAll("aa-bb-cc-dd", "-")    = "aabbcc"
	 * </pre>
	 * 
	 * @param text
	 * @param strToRemove
	 * @return
	 */

	public static String removeAll(final String text, final CharSequence remove) {
		if (isEmpty(text) || isEmpty(remove)) {
			return text;
		}
		return text.replace(remove, EMPTY);
	}

	/**
	 * <p>
	 * 从字符串{@code text}移除所有{@code remove}，忽略大小写
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.removeIgnoreCase(null, *)        = null
	 * StrUtils.removeIgnoreCase("", *)          = ""
	 * StrUtils.removeIgnoreCase(*, null)        = *
	 * StrUtils.removeIgnoreCase(*, "")          = *
	 * StrUtils.removeIgnoreCase("queued", "ue") = "qd"
	 * StrUtils.removeIgnoreCase("queued", "zz") = "queued"
	 * StrUtils.removeIgnoreCase("quEUed", "UE") = "qd"
	 * StrUtils.removeIgnoreCase("queued", "zZ") = "queued"
	 * </pre>
	 * 
	 * @param str
	 * @param remove
	 * @return
	 */
	public static String removeIgnoreCase(final String text, final String remove) {
		if (isEmpty(text) || isEmpty(remove)) {
			return text;
		}
		return replaceIgnoreCase(text, remove, EMPTY, -1);
	}

	/**
	 * <p>
	 * 字符串转换为数组
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.split(null)       = null
	 * StrUtils.split("")         = []
	 * StrUtils.split("abc def")  = ["abc", "def"]
	 * StrUtils.split("abc  def") = ["abc", "def"]
	 * StrUtils.split(" abc ")    = ["abc"]
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String[] split(final String str) {
		return split(str, null, -1);
	}

	/**
	 * <p>
	 * 将字符串{@code str}以{@code separatorChars}切分数组，最发分片数{@code max}
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.split(null, *, *)            = null
	 * StrUtils.split("", *, *)              = []
	 * StrUtils.split("ab cd ef", null, 0)   = ["ab", "cd", "ef"]
	 * StrUtils.split("ab   cd ef", null, 0) = ["ab", "cd", "ef"]
	 * StrUtils.split("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
	 * StrUtils.split("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
	 * </pre>
	 * 
	 * @param str
	 * @param separatorChars
	 * @param max
	 * @return
	 */
	public static String[] split(final String str, final String separatorChars, final int max) {
		return splitWorker(str, separatorChars, max, false);
	}

	/**
	 * <p>
	 * 将字符串{@code str}以{@code separatorChars}切分数组
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.split(null, *)         = null
	 * StrUtils.split("", *)           = []
	 * StrUtils.split("a.b.c", '.')    = ["a", "b", "c"]
	 * StrUtils.split("a..b.c", '.')   = ["a", "b", "c"]
	 * StrUtils.split("a:b:c", '.')    = ["a:b:c"]
	 * StrUtils.split("a b c", ' ')    = ["a", "b", "c"]
	 * </pre>
	 * 
	 * @param str
	 * @param separatorChar
	 * @return
	 */
	public static String[] split(final String str, final char separatorChar) {
		return splitWorker(str, separatorChar, false);
	}

	/**
	 * <p>
	 * 将字符串{@code str}以{@code separatorChars}切分数组
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.split(null, *)         = null
	 * StrUtils.split("", *)           = []
	 * StrUtils.split("abc def", null) = ["abc", "def"]
	 * StrUtils.split("abc def", " ")  = ["abc", "def"]
	 * StrUtils.split("abc  def", " ") = ["abc", "def"]
	 * StrUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
	 * </pre>
	 * 
	 * @param str
	 * @param separatorChars
	 * @return
	 */
	public static String[] split(final String str, final String separatorChars) {
		return splitWorker(str, separatorChars, -1, false);
	}

	/**
	 * <p>
	 * substring
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.substring(null, *)   = null
	 * StrUtils.substring("", *)     = ""
	 * StrUtils.substring("abc", 0)  = "abc"
	 * StrUtils.substring("abc", 2)  = "c"
	 * StrUtils.substring("abc", 4)  = ""
	 * StrUtils.substring("abc", -2) = "bc"
	 * StrUtils.substring("abc", -4) = "abc"
	 * </pre>
	 * 
	 * @param str
	 * @param start
	 * @return
	 */
	public static String substring(final String str, int start) {
		if (str == null) {
			return null;
		}

		if (start < 0) {
			start = str.length() + start;
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return EMPTY;
		}

		return str.substring(start);
	}

	/**
	 * <p>
	 * substring
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.substring(null, *, *)    = null
	 * StrUtils.substring("", * ,  *)    = "";
	 * StrUtils.substring("abc", 0, 2)   = "ab"
	 * StrUtils.substring("abc", 2, 0)   = ""
	 * StrUtils.substring("abc", 2, 4)   = "c"
	 * StrUtils.substring("abc", 4, 6)   = ""
	 * StrUtils.substring("abc", 2, 2)   = ""
	 * StrUtils.substring("abc", -2, -1) = "b"
	 * StrUtils.substring("abc", -4, 2)  = "ab"
	 * </pre>
	 * 
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String substring(final String str, int start, int end) {
		if (str == null) {
			return null;
		}

		if (end < 0) {
			end = str.length() + end;
		}
		if (start < 0) {
			start = str.length() + start;
		}

		if (end > str.length()) {
			end = str.length();
		}

		if (start > end) {
			return EMPTY;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	/**
	 * <p>
	 * 将字符串{@code str}转成小写
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.lowerCase(null)  = null
	 * StrUtils.lowerCase("")    = ""
	 * StrUtils.lowerCase("aBc") = "abc"
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String lowerCase(final String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	/**
	 * <pre>
	 * StrUtils.lowerCase(null, Locale.ENGLISH)  = null
	 * StrUtils.lowerCase("", Locale.ENGLISH)    = ""
	 * StrUtils.lowerCase("aBc", Locale.ENGLISH) = "abc"
	 * </pre>
	 * 
	 * @param str
	 * @param locale
	 * @return
	 */
	public static String lowerCase(final String str, final Locale locale) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase(locale);
	}

	/**
	 * 大写首字母<br>
	 * 例如：str = name, return Name
	 * 
	 * @param str 字符串
	 * @return 字符串
	 */
	public static String upperFirst(String str) {
		if (null == str) {
			return null;
		}
		if (str.length() > 0) {
			char firstChar = str.charAt(0);
			if (Character.isLowerCase(firstChar)) {
				return Character.toUpperCase(firstChar) + substring(str, 1);
			}
		}
		return str.toString();
	}

	/**
	 * 小写首字母<br>
	 * 例如：str = Name, return name
	 * 
	 * @param str 字符串
	 * @return 字符串
	 */
	public static String lowerFirst(String str) {
		if (null == str) {
			return null;
		}
		if (str.length() > 0) {
			char firstChar = str.charAt(0);
			if (Character.isUpperCase(firstChar)) {
				return Character.toLowerCase(firstChar) + substring(str, 1);
			}
		}
		return str.toString();
	}

	/**
	 * *
	 * <p>
	 * 将字符串{@code str}转成大写
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.upperCase(null)  = null
	 * StrUtils.upperCase("")    = ""
	 * StrUtils.upperCase("aBc") = "ABC"
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String upperCase(final String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}

	/**
	 * <pre>
	 * StrUtils.upperCase(null, Locale.ENGLISH)  = null
	 * StrUtils.upperCase("", Locale.ENGLISH)    = ""
	 * StrUtils.upperCase("aBc", Locale.ENGLISH) = "ABC"
	 * </pre>
	 * 
	 * @param str
	 * @param locale
	 * @return
	 */
	public static String upperCase(final String str, final Locale locale) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase(locale);
	}

	/**
	 * <p>
	 * 以字符集{@code charset}将字节数组{@code data}转换为字符串
	 * </p>
	 * 
	 * @param data
	 * @param charset
	 * @return
	 */
	public static String toString(byte[] data, Charset charset) {
		if (data == null) {
			return null;
		}

		if (null == charset) {
			return new String(data);
		}
		return new String(data, charset);
	}

	/**
	 * <p>
	 * 以字符集{@code charset}将字节数组{@code data}转换为字符串
	 * </p>
	 * 
	 * @param bytes
	 * @param charset
	 * @return
	 */
	public static String toString(byte[] data, String charset) {
		return toString(data, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
	}

	/**
	 * <p>
	 * 以字符集{@code charset}将字节数组{@code data}转换为字符串
	 * </p>
	 * 
	 * @param data
	 * @param charset
	 * @return
	 */
	public static String toString(Byte[] data, Charset charset) {
		if (data == null) {
			return null;
		}

		byte[] bytes = new byte[data.length];
		Byte dataByte;
		for (int i = 0; i < data.length; i++) {
			dataByte = data[i];
			bytes[i] = (null == dataByte) ? -1 : dataByte.byteValue();
		}

		return toString(bytes, charset);
	}

	/**
	 * <p>
	 * 以字符集{@code charset}将字节数组{@code data}转换为字符串
	 * </p>
	 * 
	 * @param bytes
	 * @param charset
	 * @return
	 */
	public static String toString(Byte[] bytes, String charset) {
		return toString(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
	}

	/**
	 * <p>
	 * 以字符集{@code charset}将字节buffer{@code data}转换为字符串
	 * </p>
	 * 
	 * @param data
	 * @param charset
	 * @return
	 */
	public static String toString(ByteBuffer data, Charset charset) {
		if (null == charset) {
			charset = Charset.defaultCharset();
		}
		return charset.decode(data).toString();
	}

	/**
	 * <p>
	 * 以字符集{@code charset}将字节buffer{@code data}转换为字符串
	 * </p>
	 * 
	 * @param data
	 * @param charset
	 * @return
	 */
	public static String toString(ByteBuffer data, String charset) {
		if (data == null) {
			return null;
		}

		return toString(data, isBlank(charset) ? null : Charset.forName(charset));
	}

	/**
	 * <p>
	 * 以字符集{@code charset}将Object {@code obj}转换为字符串
	 * </p>
	 * 
	 * @param obj
	 * @param charset
	 * @return
	 */
	public static String toString(Object obj, Charset charset) {
		if (null == obj) {
			return null;
		}

		if (obj instanceof String) {
			return (String) obj;
		} else if (obj instanceof byte[]) {
			return toString((byte[]) obj, charset);
		} else if (obj instanceof Byte[]) {
			return toString((Byte[]) obj, charset);
		} else if (obj instanceof ByteBuffer) {
			return toString((ByteBuffer) obj, charset);
		} else if (ArrayUtils.isArray(obj)) {
			return ArrayUtils.toString(obj);
		}
		return obj.toString();
	}

	/**
	 * <p>
	 * 以字符集{@code charset}将Object {@code obj}转换为字符串
	 * </p>
	 * 
	 * @param obj
	 * @param charset
	 * @return
	 */
	public static String toString(Object obj, String charset) {
		if (null == obj) {
			return null;
		}

		if (obj instanceof String) {
			return (String) obj;
		} else if (obj instanceof byte[]) {
			return toString((byte[]) obj, charset);
		} else if (obj instanceof Byte[]) {
			return toString((Byte[]) obj, charset);
		} else if (obj instanceof ByteBuffer) {
			return toString((ByteBuffer) obj, charset);
		} else if (ArrayUtils.isArray(obj)) {
			return ArrayUtils.toString(obj);
		}
		return obj.toString();
	}

	/**
	 * 调用对象的toString方法，null会返回“NULL”
	 * 
	 * @param obj 对象
	 * @return 字符串
	 */
	public static String toString(Object obj) {
		if (null == obj) {
			return null;
		}
		if (obj.getClass().isArray()) {
			try {
				return Arrays.deepToString((Object[]) obj);
			} catch (Exception e) {
				final String className = obj.getClass().getComponentType().getName();
				if ("long".equalsIgnoreCase(className)) {
					return Arrays.toString((long[]) obj);
				} else if ("int".equalsIgnoreCase(className)) {
					return Arrays.toString((int[]) obj);
				} else if ("short".equalsIgnoreCase(className)) {
					return Arrays.toString((short[]) obj);
				} else if ("char".equalsIgnoreCase(className)) {
					return Arrays.toString((char[]) obj);
				} else if ("byte".equalsIgnoreCase(className)) {
					return Arrays.toString((byte[]) obj);
				} else if ("boolean".equalsIgnoreCase(className)) {
					return Arrays.toString((boolean[]) obj);
				} else if ("float".equalsIgnoreCase(className)) {
					return Arrays.toString((float[]) obj);
				} else if ("double".equalsIgnoreCase(className)) {
					return Arrays.toString((double[]) obj);
				} else {
					throw new RuntimeException(e);
				}
			}
		}
		return obj.toString();
	}

	/**
	 * <p>
	 * 判断字符串{@code cs}是否全是字母
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.isAlpha(null)   = false
	 * StrUtils.isAlpha("")     = false
	 * StrUtils.isAlpha("  ")   = false
	 * StrUtils.isAlpha("abc")  = true
	 * StrUtils.isAlpha("ab2c") = false
	 * StrUtils.isAlpha("ab-c") = false
	 * </pre>
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isAlpha(final CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetter(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * 判断字符串{@code cs}是否全是数字
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.isNumeric(null)   = false
	 * StrUtils.isNumeric("")     = false
	 * StrUtils.isNumeric("  ")   = false
	 * StrUtils.isNumeric("123")  = true
	 * StrUtils.isNumeric("12 3") = false
	 * StrUtils.isNumeric("ab2c") = false
	 * StrUtils.isNumeric("12-3") = false
	 * StrUtils.isNumeric("12.3") = false
	 * </pre>
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isNumeric(final CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * *
	 * <p>
	 * 判断字符串{@code cs}是否全是空白
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.isWhitespace(null)   = false
	 * StrUtils.isWhitespace("")     = true
	 * StrUtils.isWhitespace("  ")   = true
	 * StrUtils.isWhitespace("abc")  = false
	 * StrUtils.isWhitespace("ab2c") = false
	 * StrUtils.isWhitespace("ab-c") = false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isWhitespace(final CharSequence cs) {
		if (cs == null) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isWhitespace(cs.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <pre>
	 * StrUtils.indexOf(null, *)         = -1
	 * StrUtils.indexOf("", *)           = -1
	 * StrUtils.indexOf("aabaabaa", 'a') = 0
	 * StrUtils.indexOf("aabaabaa", 'b') = 2
	 * </pre>
	 * 
	 * @param str
	 * @param searchChar
	 * @return
	 */
	public static int indexOf(final String str, char searchChar) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.indexOf(searchChar);
	}

	/**
	 * <pre>
	 * StrUtils.indexOf(null, *, *)          = -1
	 * StrUtils.indexOf("", *, *)            = -1
	 * StrUtils.indexOf("aabaabaa", 'b', 0)  = 2
	 * StrUtils.indexOf("aabaabaa", 'b', 3)  = 5
	 * StrUtils.indexOf("aabaabaa", 'b', 9)  = -1
	 * StrUtils.indexOf("aabaabaa", 'b', -1) = 2
	 * </pre>
	 * 
	 * @param str
	 * @param searchChar
	 * @param startPos
	 * @return
	 */
	public static int indexOf(final String str, char searchChar, int startPos) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.indexOf(searchChar, startPos);
	}

	/**
	 * <pre>
	 * StrUtils.indexOf(null, *)          = -1
	 * StrUtils.indexOf(*, null)          = -1
	 * StrUtils.indexOf("", "")           = 0
	 * StrUtils.indexOf("aabaabaa", "a")  = 0
	 * StrUtils.indexOf("aabaabaa", "b")  = 2
	 * StrUtils.indexOf("aabaabaa", "ab") = 1
	 * StrUtils.indexOf("aabaabaa", "")   = 0
	 * </pre>
	 * 
	 * @param str
	 * @param searchStr
	 * @return
	 */
	public static int indexOf(final String str, String searchStr) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return str.indexOf(searchStr);
	}

	/**
	 * <pre>
	 * StrUtils.indexOf(null, *, *)          = -1
	 * StrUtils.indexOf(*, null, *)          = -1
	 * StrUtils.indexOf("", "", 0)           = 0
	 * StrUtils.indexOf("aabaabaa", "a", 0)  = 0
	 * StrUtils.indexOf("aabaabaa", "b", 0)  = 2
	 * StrUtils.indexOf("aabaabaa", "ab", 0) = 1
	 * StrUtils.indexOf("aabaabaa", "b", 3)  = 5
	 * StrUtils.indexOf("aabaabaa", "b", 9)  = -1
	 * StrUtils.indexOf("aabaabaa", "b", -1) = 2
	 * StrUtils.indexOf("aabaabaa", "", 2)   = 2
	 * StrUtils.indexOf("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param str
	 * @param searchStr
	 * @param startPos
	 * @return
	 */
	public static int indexOf(final String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0 && startPos >= str.length()) {
			return str.length();
		}
		return str.indexOf(searchStr, startPos);
	}

	/**
	 * <pre>
	 * StrUtils.indexOfIgnoreCase(null, *)          = -1
	 * StrUtils.indexOfIgnoreCase(*, null)          = -1
	 * StrUtils.indexOfIgnoreCase("", "")           = 0
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "a")  = 0
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "b")  = 2
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "ab") = 1
	 * </pre>
	 * 
	 * @param str
	 * @param searchStr
	 * @return
	 */
	public static int indexOfIgnoreCase(final String str, String searchStr) {
		return indexOfIgnoreCase(str, searchStr, 0);
	}

	/**
	 * <pre>
	 * StrUtils.indexOfIgnoreCase(null, *, *)          = -1
	 * StrUtils.indexOfIgnoreCase(*, null, *)          = -1
	 * StrUtils.indexOfIgnoreCase("", "", 0)           = 0
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
	 * StrUtils.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
	 * StrUtils.indexOfIgnoreCase("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param str
	 * @param searchStr
	 * @param startPos
	 * @return
	 */
	public static int indexOfIgnoreCase(final String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		if (startPos < 0) {
			startPos = 0;
		}
		int endLimit = (str.length() - searchStr.length()) + 1;
		if (startPos > endLimit) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0) {
			return startPos;
		}
		for (int i = startPos; i < endLimit; i++) {
			if (str.regionMatches(true, i, searchStr, 0, searchStr.length())) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * <p>
	 * 判断字符串{@code cs}是否全是字母或数字
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.isAlphanumeric(null)   = false
	 * StrUtils.isAlphanumeric("")     = false
	 * StrUtils.isAlphanumeric("  ")   = false
	 * StrUtils.isAlphanumeric("abc")  = true
	 * StrUtils.isAlphanumeric("ab c") = false
	 * StrUtils.isAlphanumeric("ab2c") = true
	 * StrUtils.isAlphanumeric("ab-c") = false
	 * </pre>
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isAlphanumeric(CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			return false;
		}
		int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetterOrDigit(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <pre>
	 * StrUtils.join(null)            = null
	 * StrUtils.join([])              = ""
	 * StrUtils.join([null])          = ""
	 * StrUtils.join(["a", "b", "c"]) = "abc"
	 * StrUtils.join([null, "", "a"]) = "a"
	 * </pre>
	 * 
	 * @param array
	 * @return
	 */
	public static String join(Object[] array) {
		return join(array, null);
	}

	/**
	 * <pre>
	 * StrUtils.join(null, *)                = null
	 * StrUtils.join([], *)                  = ""
	 * StrUtils.join([null], *)              = ""
	 * StrUtils.join(["a", "b", "c"], "--")  = "a--b--c"
	 * StrUtils.join(["a", "b", "c"], null)  = "abc"
	 * StrUtils.join(["a", "b", "c"], "")    = "abc"
	 * StrUtils.join([null, "", "a"], ',')   = ",,a"
	 * </pre>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	/**
	 * <pre>
	 * StrUtils.join(null, *)               = null
	 * StrUtils.join([], *)                 = ""
	 * StrUtils.join([null], *)             = ""
	 * StrUtils.join(["a", "b", "c"], ';')  = "a;b;c"
	 * StrUtils.join(["a", "b", "c"], null) = "abc"
	 * StrUtils.join([null, "", "a"], ';')  = ";;a"
	 * </pre>
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	public static String join(Iterable<?> iterable, char separator) {
		if (iterable == null) {
			return null;
		}
		return join(iterable.iterator(), separator);
	}

	public static String join(Iterable<?> iterable, String separator) {
		if (iterable == null) {
			return null;
		}
		return join(iterable.iterator(), separator);
	}

	public static String join(Iterator<?> iterator, char separator) {

		if (iterator == null) {
			return null;
		}
		if (!iterator.hasNext()) {
			return EMPTY;
		}
		Object first = iterator.next();
		if (!iterator.hasNext()) {
			return (first == null ? "" : first.toString());
		}

		StringBuilder buf = new StringBuilder(256);
		if (first != null) {
			buf.append(first);
		}

		while (iterator.hasNext()) {
			buf.append(separator);
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
		}

		return buf.toString();
	}

	public static String join(Iterator<?> iterator, String separator) {

		if (iterator == null) {
			return null;
		}
		if (!iterator.hasNext()) {
			return EMPTY;
		}
		Object first = iterator.next();
		if (!iterator.hasNext()) {
			return (first == null ? "" : first.toString());
		}

		StringBuilder buf = new StringBuilder(256);
		if (first != null) {
			buf.append(first);
		}

		while (iterator.hasNext()) {
			if (separator != null) {
				buf.append(separator);
			}
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
		}
		return buf.toString();
	}

	/**
	 * 指定范围内查找字符串，忽略大小写
	 * 
	 * @param str       字符串
	 * @param searchStr 需要查找位置的字符串
	 * @return 位置
	 * @since 3.2.1
	 */
	public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
		return lastIndexOfIgnoreCase(str, searchStr, str.length());
	}

	/**
	 * 指定范围内查找字符串，忽略大小写<br>
	 * fromIndex 为搜索起始位置，从后往前计数
	 * 
	 * @param str       字符串
	 * @param searchStr 需要查找位置的字符串
	 * @param fromIndex 起始位置，从后往前计数
	 * @return 位置
	 * @since 3.2.1
	 */
	public static int lastIndexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int fromIndex) {
		return lastIndexOf(str, searchStr, fromIndex, true);
	}

	/**
	 * 指定范围内查找字符串<br>
	 * fromIndex 为搜索起始位置，从后往前计数
	 * 
	 * @param str        字符串
	 * @param searchStr  需要查找位置的字符串
	 * @param fromIndex  起始位置，从后往前计数
	 * @param ignoreCase 是否忽略大小写
	 * @return 位置
	 * @since 3.2.1
	 */
	public static int lastIndexOf(final CharSequence str, final CharSequence searchStr, int fromIndex,
			boolean ignoreCase) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		if (fromIndex < 0) {
			fromIndex = 0;
		}
		fromIndex = Math.min(fromIndex, str.length());

		if (searchStr.length() == 0) {
			return fromIndex;
		}

		if (false == ignoreCase) {
			// 不忽略大小写调用JDK方法
			return str.toString().lastIndexOf(searchStr.toString(), fromIndex);
		}

		for (int i = fromIndex; i > 0; i--) {
			if (isSubEquals(str, i, searchStr, 0, searchStr.length(), true)) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * 截取两个字符串的不同部分（长度一致），判断截取的子串是否相同<br>
	 * 任意一个字符串为null返回false
	 * 
	 * @param str1       第一个字符串
	 * @param start1     第一个字符串开始的位置
	 * @param str2       第二个字符串
	 * @param start2     第二个字符串开始的位置
	 * @param length     截取长度
	 * @param ignoreCase 是否忽略大小写
	 * @return 子串是否相同
	 * @since 3.2.1
	 */
	public static boolean isSubEquals(CharSequence str1, int start1, CharSequence str2, int start2, int length,
			boolean ignoreCase) {
		if (null == str1 || null == str2) {
			return false;
		}

		return str1.toString().regionMatches(ignoreCase, start1, str2.toString(), start2, length);
	}

	/**
	 * <p>
	 * example {@code map} => {a: "aValue", b: "bValue"}
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.format("{a} and {b}",{@code map} = "aValue and bValue"
	 * </pre>
	 * 
	 * @param template
	 * @param map
	 * @return
	 */
	public static String format(CharSequence template, Map<?, ?> map) {
		if (null == template) {
			return null;
		}
		if (null == map || map.isEmpty()) {
			return template.toString();
		}

		String template2 = template.toString();
		for (Entry<?, ?> entry : map.entrySet()) {
			template2 = template2.replace("{" + entry.getKey() + "}",
					toString(entry.getValue(), StandardCharsets.UTF_8));
		}
		return template2;
	}

	/**
	 * <P>
	 * {}占位符按照顺序替换为参数，用\\{}转义输出{}
	 * </p>
	 * 
	 * <pre>
	 * StrUtils.format("this is {} for {}", "a", "b") = this is a for b
	 * StrUtils.format("this is {} for", "a", "b") = this is a for 
	 * StrUtils.format("this is {} for {},{}", "a", "b") = this is a for b
	 * StrUtils.format("this is \\{} for {}", "a", "b") = this is {} for a
	 * StrUtils.format("this is \\\\{} for {}", "a", "b") = this is \a for b
	 * </pre>
	 * 
	 * @param template 字符串模板
	 * @param args     参数列表
	 * @return 结果
	 */
	public static String format(final String template, final Object... args) {
		if (isBlank(template) || ArrayUtils.isEmpty(args)) {
			return template;
		}
		final int templateLength = template.length();
		StringBuilder sbuf = new StringBuilder(templateLength + 50);
		int handledPosition = 0;// 记录已经处理到的位置
		int delimIndex;// 占位符所在位置
		for (int argIndex = 0; argIndex < args.length; argIndex++) {
			delimIndex = template.indexOf(EMPTY_JSON, handledPosition);
			if (delimIndex == -1) {// 剩余部分无占位符
				if (handledPosition == 0) { // 不带占位符的模板直接返回
					return template;
				} else { // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
					sbuf.append(template, handledPosition, templateLength);
					return sbuf.toString();
				}
			} else {
				if (delimIndex > 0 && template.charAt(delimIndex - 1) == C_BACKSLASH) {// 转义符
					if (delimIndex > 1 && template.charAt(delimIndex - 2) == C_BACKSLASH) {// 双转义符
						// 转义符之前还有一个转义符，占位符依旧有效
						sbuf.append(template, handledPosition, delimIndex - 1);
						sbuf.append(toString(args[argIndex], StandardCharsets.UTF_8));
						handledPosition = delimIndex + 2;
					} else {
						// 占位符被转义
						argIndex--;
						sbuf.append(template, handledPosition, delimIndex - 1);
						sbuf.append(C_DELIM_START);
						handledPosition = delimIndex + 1;
					}
				} else {// 正常占位符
					sbuf.append(template, handledPosition, delimIndex);
					sbuf.append(toString(args[argIndex], StandardCharsets.UTF_8));
					handledPosition = delimIndex + 2;
				}
			}
		}
		// append the characters following the last {} pair.
		// 加入最后一个占位符后所有的字符
		sbuf.append(template, handledPosition, template.length());
		return sbuf.toString();
	}

	// other
	public static Map<String, String> str2Map(String entrySplit, String keySplit, String str) {
		Map<String, String> qryStringMap = new HashMap<String, String>();
		if (!StrUtils.isEmpty(str)) {
			String[] entrys = str.split(entrySplit);
			for (String entry : entrys) {
				if (!StrUtils.isEmpty(entry)) {
					String[] entryArray = entry.split(keySplit);
					if (entryArray.length < 2) {
						qryStringMap.put(entryArray[0], "");
					} else if (entryArray.length == 2) {
						qryStringMap.put(entryArray[0], entryArray[1]);
					} else {
						String val = "";
						for (int i = 1; i < entryArray.length; i++) {
							val = val + entryArray[i];
							if (i < (entryArray.length - 1)) {
								val = val + "=";
							}
						}
						qryStringMap.put(entryArray[0], val);
					}
				}
			}
		}
		return qryStringMap;
	}

	public static String map2Str(String entrySplit, String keySplit, Map<String, String> map) {
		StringBuffer str = new StringBuffer("");
		if (map != null) {
			int entrySplitLen = entrySplit.length();
			for (String key : map.keySet()) {
				if (!isEmpty(key)) {
					str.append(key);
					str.append(keySplit);
					String val = map.get(key);
					str.append(isEmpty(val) ? "" : val);
					str.append(entrySplit);
				}
			}
			if (endsWith(str.toString(), entrySplit)) {
				int sl = str.toString().length();
				int len = entrySplit.length();
				return str.toString().substring(0, sl - len);
			}
		}
		return str.toString();
	}

	private static String join(Object[] array, String separator, int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = EMPTY;
		}

		int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return EMPTY;
		}

		StringBuilder buf = new StringBuilder(noOfItems * 16);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	private static String join(Object[] array, char separator, int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return EMPTY;
		}

		StringBuilder buf = new StringBuilder(noOfItems * 16);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	private static String replace(String text, String searchString, String replacement, int max, boolean ignoreCase) {
		if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
			return text;
		}
		String searchText = text;
		if (ignoreCase) {
			searchText = text.toLowerCase();
			searchString = searchString.toLowerCase();
		}
		int start = 0;
		int end = searchText.indexOf(searchString, start);
		if (end == INDEX_NOT_FOUND) {
			return text;
		}
		final int replLength = searchString.length();
		int increase = replacement.length() - replLength;
		increase = increase < 0 ? 0 : increase;
		increase *= max < 0 ? 16 : max > 64 ? 64 : max;
		final StringBuilder buf = new StringBuilder(text.length() + increase);
		while (end != INDEX_NOT_FOUND) {
			buf.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			if (--max == 0) {
				break;
			}
			end = searchText.indexOf(searchString, start);
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	private static boolean startsWith(final CharSequence cs, final CharSequence prefix, final boolean ignoreCase) {
		if (cs == null || prefix == null) {
			return cs == null && prefix == null;
		}
		if (prefix.length() > cs.length()) {
			return false;
		}
		return regionMatches(cs, ignoreCase, 0, prefix, 0, prefix.length());
	}

	private static boolean endsWith(final CharSequence str, final CharSequence suffix, final boolean ignoreCase) {
		if (str == null || suffix == null) {
			return str == null && suffix == null;
		}
		if (suffix.length() > str.length()) {
			return false;
		}
		final int strOffset = str.length() - suffix.length();
		return regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
	}

	private static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
			final CharSequence substring, final int start, final int length) {
		if (cs instanceof String && substring instanceof String) {
			return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
		}
		int index1 = thisStart;
		int index2 = start;
		int tmpLen = length;

		final int srcLen = cs.length() - thisStart;
		final int otherLen = substring.length() - start;

		if (thisStart < 0 || start < 0 || length < 0) {
			return false;
		}

		if (srcLen < length || otherLen < length) {
			return false;
		}

		while (tmpLen-- > 0) {
			final char c1 = cs.charAt(index1++);
			final char c2 = substring.charAt(index2++);

			if (c1 == c2) {
				continue;
			}

			if (!ignoreCase) {
				return false;
			}
			if (Character.toUpperCase(c1) != Character.toUpperCase(c2)
					&& Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
				return false;
			}
		}

		return true;
	}

	private static String[] splitWorker(final String str, final char separatorChar, final boolean preserveAllTokens) {

		if (str == null) {
			return null;
		}
		final int len = str.length();
		if (len == 0) {
			return new String[0];
		}
		final List<String> list = new ArrayList<String>();
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		while (i < len) {
			if (str.charAt(i) == separatorChar) {
				if (match || preserveAllTokens) {
					list.add(str.substring(start, i));
					match = false;
					lastMatch = true;
				}
				start = ++i;
				continue;
			}
			lastMatch = false;
			match = true;
			i++;
		}
		if (match || preserveAllTokens && lastMatch) {
			list.add(str.substring(start, i));
		}
		return list.toArray(new String[list.size()]);
	}

	private static String[] splitWorker(final String str, final String separatorChars, final int max,
			final boolean preserveAllTokens) {

		if (str == null) {
			return null;
		}
		final int len = str.length();
		if (len == 0) {
			return new String[0];
		}
		final List<String> list = new ArrayList<String>();
		int sizePlus1 = 1;
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		if (separatorChars == null) {
			while (i < len) {
				if (Character.isWhitespace(str.charAt(i))) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		} else if (separatorChars.length() == 1) {
			final char sep = separatorChars.charAt(0);
			while (i < len) {
				if (str.charAt(i) == sep) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		} else {
			while (i < len) {
				if (separatorChars.indexOf(str.charAt(i)) >= 0) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		}
		if (match || preserveAllTokens && lastMatch) {
			list.add(str.substring(start, i));
		}
		return list.toArray(new String[list.size()]);
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	/**
	 * Trim <i>all</i> whitespace from the given {@code String}: leading, trailing,
	 * and in between characters.
	 * 
	 * @param str the {@code String} to check
	 * @return the trimmed {@code String}
	 * @see Character#isWhitespace
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}

		int len = str.length();
		StringBuilder sb = new StringBuilder(str.length());
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (!Character.isWhitespace(c)) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * Check that the given {@code String} is neither {@code null} nor of length 0.
	 * <p>
	 * Note: this method returns {@code true} for a {@code String} that purely
	 * consists of whitespace.
	 * 
	 * @param str the {@code String} to check (may be {@code null})
	 * @return {@code true} if the {@code String} is not {@code null} and has length
	 */
	public static boolean hasLength(String str) {
		return (str != null && !str.isEmpty());
	}

	public static String arrayToDelimitedString(Object[] objects, String delimiter) {
		if (objects == null) {
			return "";
		}
		if (objects.length == 1) {
			return objects[0] == null ? "" : objects[0].toString();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < objects.length; ++i) {
			if (i != 0) {
				sb.append(delimiter);
			}
			sb.append(objects[i] == null ? "" : objects[i]);
		}
		return sb.toString();
	}

	public static String arrayToCommaDelimitedString(Object[] objects) {
		return arrayToDelimitedString(objects, ",");
	}

	public static String safeToString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 截取字符串最后几位字符
	 *
	 * @param org       原字符
	 * @param tailCount 要截取的尾缀字符长度
	 * @return
	 */
	public static String cutTail(String org, int tailCount) {
		if (isNullOrEmpty(org)){
			return "";
		}
		if (org.length() < tailCount+1){
			return org;
		}else {
			return org.substring(org.length()-tailCount);
		}
	}
}
