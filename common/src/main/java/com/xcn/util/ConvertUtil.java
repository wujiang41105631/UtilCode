package com.xcn.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ConvertUtil {
	
	private static final Logger logger = Logger.getLogger(ConvertUtil.class);
	
	public static byte[] base64ToBytes(String base64Str) {
		if (StringUtils.isBlank(base64Str)) { return null; }
		byte[] bytes = null;
		try {
			bytes = new BASE64Decoder().decodeBuffer(base64Str);
		} catch (Exception e) {
			logger.error(null, e);
		}
		return bytes;
	}
	
	public static String bytesToBase64(byte[] bytes) {
		if (ArrayUtils.isEmpty(bytes)) { return null; }
		String str = null;
		try {
			str = new BASE64Encoder().encode(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(null, e);
		}
		return str;
	}
	
	public static String bytesToHex(byte[] bytes) {
		if (ArrayUtils.isEmpty(bytes)) { return null; }
		String str = null;
		try {
			StringBuilder hexStr = new StringBuilder();
			for (byte b : bytes) {
				if ((b & 0xff) < 0x10) hexStr.append("0");
				hexStr.append(Integer.toHexString(b & 0xff));
			}
			str = hexStr.toString().toLowerCase();
		} catch (Exception e) {
			logger.error(null, e);
		}
		return str;
	}
	
	public static byte[] hexToBytes(String hexStr) {
		if (StringUtils.isBlank(hexStr)) { return null; }
		byte[] bytes = null;
		try {
			hexStr = hexStr.toLowerCase();
			byte[] byteArray = new byte[hexStr.length() / 2];
			int position = 0;
			for (int i = 0; i < byteArray.length; i++) {
				byte high = (byte) (Character.digit(hexStr.charAt(position), 16) & 0xff);
				byte low = (byte) (Character.digit(hexStr.charAt(position + 1), 16) & 0xff);
				byteArray[i] = (byte) (high << 4 | low);
				position += 2;
			}
			bytes = byteArray;
		} catch (Exception e) {
			logger.error(null, e);
		}
		return bytes;
	}
	
}
