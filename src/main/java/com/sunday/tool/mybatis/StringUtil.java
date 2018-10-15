package com.sunday.tool.mybatis;

import java.util.Random;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

	/**
	 * 字符串空处理，去除首尾空格 如果str为null，返回"",否则返回str
	 * 
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String firstCharUpperCase(String s) {
		StringBuilder sb = new StringBuilder(s.substring(0, 1).toUpperCase());
		sb.append(s.substring(1, s.length()));
		return sb.toString();
	}

	/**
	 * 隐藏头几位
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String hideFirstChar(String str, int len) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		if (str.length() <= len) {
			for (int i = 0; i < chars.length; i++) {
				chars[i] = '*';
			}
		} else {
			for (int i = 0; i < 1; i++) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	/**
	 * 隐藏末几位
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String hideLastChar(String str, int len) {
		if (str == null)
			return null;
		char[] chars = str.toCharArray();
		if (str.length() <= len) {
			return hideLastChar(str, str.length() - 1);
		} else {
			for (int i = chars.length - 1; i > chars.length - len - 1; i--) {
				chars[i] = '*';
			}
		}
		str = new String(chars);
		return str;
	}

	/**
	 * 指定起始位置字符串隐藏
	 * 
	 * @param str
	 * @param index1
	 * @param index2
	 * @return
	 */
	public static String hideStr(String str, int index1, int index2) {
		if (str == null)
			return null;
		String str1 = str.substring(index1, index2);
		String str2 = str.substring(index2);
		String str3 = "";
		if (index1 > 0) {
			str1 = str.substring(0, index1);
			str2 = str.substring(index1, index2);
			str3 = str.substring(index2);
		}
		char[] chars = str2.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			chars[i] = '*';
		}
		str2 = new String(chars);
		String str4 = str1 + str2 + str3;
		return str4;
	}

	/**
	 * Object数组拼接为字符串
	 * 
	 * @param args
	 * @return
	 */
	public static String contact(Object[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * Long数组拼接为字符串
	 * 
	 * @param args
	 * @return
	 */
	public static String contact(long[] args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if (i < args.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * 数字数组拼接为字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String array2Str(int[] arr) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			s.append(arr[i]);
			if (i < arr.length - 1) {
				s.append(",");
			}
		}
		return s.toString();
	}

	/**
	 * 字符串数组转换为数字数组
	 * 
	 * @param strarr
	 * @return
	 */
	public static int[] strarr2intarr(String[] strarr) {
		int[] result = new int[strarr.length];
		for (int i = 0; i < strarr.length; i++) {
			result[i] = Integer.parseInt(strarr[i]);
		}
		return result;
	}

	/**
	 * 大写字母转成“_”+小写 驼峰命名转换为下划线命名
	 * 
	 * @param str
	 * @return
	 */
	public static String toUnderline(String str) {
		char[] charArr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		sb.append(charArr[0]);
		for (int i = 1; i < charArr.length; i++) {
			if (charArr[i] >= 'A' && charArr[i] <= 'Z') {
				sb.append('_').append(charArr[i]);
			} else {
				sb.append(charArr[i]);
			}
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * String to int
	 * 
	 * @param str
	 * @return
	 */
	public static int toInt(String str) {
		if (StringUtils.isBlank(str))
			return 0;
		int ret = 0;
		try {
			ret = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}

	public static byte toByte(String str) {
		if (StringUtils.isBlank(str))
			return 0;
		byte ret = 0;
		try {
			ret = Byte.parseByte(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}

	/**
	 * String to Long
	 * 
	 * @param str
	 * @return
	 */
	public static long toLong(String str) {
		if (StringUtils.isBlank(str))
			return 0L;
		long ret = 0;
		try {
			ret = Long.parseLong(str);
		} catch (NumberFormatException e) {
			ret = 0;
		}
		return ret;
	}

	/**
	 * String[] to long[]
	 * 
	 * @param str
	 * @return
	 */
	public static long[] toLongs(String[] str) {
		if (str == null || str.length < 1)
			return new long[] { 0L };
		long[] ret = new long[str.length];
		ret = (long[]) ConvertUtils.convert(str, long.class);
		return ret;
	}

	/**
	 * 生成指定长度的随机字符串，字母加数字组合
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		String val = "";
		Random random = new Random();
		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 生成手机验证码
	 * 
	 * @return
	 */
	public static String getSMSCode() {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < 4; i++) {
			sb.append(String.valueOf(r.nextInt(10)));
		}
		return sb.toString();
	}

	/**
	 * 生成默认密码8位 大写字母1位，小写字母1位数字组合6位
	 * 
	 * @return
	 */
	public static String getDefultPWD() {
		String bigCharacter = "ASDFGHJKLMNBVCXZQWERTYUIOP";
		String smallCharacter = "asdfghjklmnbvcxzqwertyuiop";
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < 6; i++) {
			sb.append(String.valueOf(r.nextInt(10)));
		}
		for (int i = 0; i < 2; i++) {
			sb.append(bigCharacter.charAt(r.nextInt(26)));
		}
		for (int i = 0; i < 2; i++) {
			sb.append(smallCharacter.charAt(r.nextInt(26)));
		}
		return sb.toString();
	}

	/**
	 * 生成默认密码 调用者指定长度
	 * 
	 * @return
	 */
	public static String getDefaultPWD(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public static String toHump(String s) {
		String dest = s.replace("/", "").replace("+", "").replace("  ", " ");
		String[] str = dest.split(" ");
		if (str != null && str.length > 0) {
			StringBuilder buffer = new StringBuilder();
			buffer.append(str[0].toLowerCase());
			for (int i = 1; i < str.length; i++) {
				buffer.append(firstCharUpperCase(str[i]));
			}
			return buffer.toString();
		}
		return "";
	}

	/**
	 * obj 转 String;失败则返回指定str
	 * 
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj, String str) {
		if (obj == null) {
			return str;
		}
		String str1 = "";
		try {
			str1 = obj.toString();
		} catch (Exception e) {
			return str;
		}

		return str1;
	}

	/**
	 * obj是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isObjEmpty(Object obj) {
		if (null == obj) {
			return true;
		}
		if ("".equals(obj)) {
			return true;
		}
		if ("".equals(obj.toString().trim())) {
			return true;
		}
		return false;
	}
}
