package com.baicai.futurekiller.util;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class StringUtil {
	private final static Charset uft8 = Charset.forName("utf8");

	public static byte[] toByteArr(String value) {
		return value.getBytes(uft8);
	}

	public static String valueOf(byte[] data) {
		return new String(data, uft8);
	}

	public static String join(int[] arr, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i : arr) {
			sb.append(i).append(separator);
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static ArrayList<Integer> splitToIntList(String value, String separator) {
		if (isNullOrEmpty(value)) {
			return new ArrayList<Integer>(0);
		}

		String[] strArr = value.split(separator);
		ArrayList<Integer> list = new ArrayList<Integer>(strArr.length);
		for (String str : strArr) {
			list.add(Integer.valueOf(str));
		}
		return list;
	}
}
