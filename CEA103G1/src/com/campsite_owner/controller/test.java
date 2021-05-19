package com.campsite_owner.controller;

import java.security.MessageDigest;

/**
 * * * Title: * * * Description: * * * Copyright: Copyright (c) 2003 * * *
 * Company: * * * @author unascribed * @version 1.0
 */
public class test {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/** * �ഫ�줸�հ}�C��16�i���r�� * * @param b * �줸�հ}�C * @return 16�i���r�� */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static void main(String[] args) {
		System.err.println(MD5Encode("123456"));
	}
}
