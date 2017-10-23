package com.aws.codestar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

	private static MessageDigest md;

	public static String encrypt(String data) {
		if (data == null)
			return "false";
		String result = null;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] passBytes = data.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++) {
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			result = "false";
		}
		return result;
	}

	public static boolean compare(String md5, String data) {
		String encryptedData = encrypt(data);
		if(md5.equals(encryptedData)) return true;
		return false;
	}

}
