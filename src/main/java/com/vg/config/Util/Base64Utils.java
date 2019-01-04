package com.vg.config.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {

	//随机产生字符串
	public static String randString(int length) {
		int size;
		String str = "";
		for(int i=0; i<length; i++) {
			size = ((int)(Math.random()*2))==0 ? 65 : 97;
			str += (char)((int)(Math.random()*26) + size);
		}
		return str;
	}
	//base64转图片
	public static boolean base64ToImage(String baseStr, String filePath) {
		byte[] b = Base64.decodeBase64(baseStr);
		for(int i=0; i<b.length; i++)
			if(b[i]<0) 
				b[i] += 256;
		OutputStream os = null;
		try {
			os = new FileOutputStream(filePath);
			os.write(b);
			os.flush();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
}
