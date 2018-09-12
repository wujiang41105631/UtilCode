package com.xcn.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;

/**
 * 压缩
 * @author xupeng.guo
 */
public class CompressUtil {
	
	private static final Logger logger = Logger.getLogger(CompressUtil.class);
	
	public static byte[] compress(byte[] uncompressedBytes) {
		if (ArrayUtils.isEmpty(uncompressedBytes)) { return null; }
		ByteArrayInputStream bis = null;
		DeflaterInputStream dis = null;
		ByteArrayOutputStream bos = null;
		byte[] compressedBytes = null;
		try {
			bis = new ByteArrayInputStream(uncompressedBytes);
			dis = new DeflaterInputStream(bis);
			bos = new ByteArrayOutputStream();
			int b = 0;
			while ((b = dis.read()) != -1) {
				bos.write(b);
			}
			bos.flush();
			compressedBytes = bos.toByteArray();
		} catch (Exception e) {
			logger.error(null, e);
		} finally {
			try {
				bos.close();
				dis.close();
				bis.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
		return compressedBytes;
	}
	
	public static byte[] decompress(byte[] compressedBytes) {
		if (ArrayUtils.isEmpty(compressedBytes)) { return null; }
		ByteArrayInputStream bis = null;
		InflaterInputStream iis = null;
		ByteArrayOutputStream bos = null;
		byte[] uncompressedBytes = null;
		try {
			bis = new ByteArrayInputStream(compressedBytes);
			iis = new InflaterInputStream(bis);
			bos = new ByteArrayOutputStream();
			int b = 0;
			while ((b = iis.read()) != -1) {
				bos.write(b);
			}
			bos.flush();
			uncompressedBytes = bos.toByteArray();
		} catch (Exception e) {
			logger.error(null, e);
		} finally {
			try {
				bos.close();
				iis.close();
				bis.close();
			} catch (Exception e) {
				logger.error(null, e);
			}
		}
		return uncompressedBytes;
	}
	
}
