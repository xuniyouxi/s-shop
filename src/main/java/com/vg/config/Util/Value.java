package com.vg.config.Util;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

public class Value {

	//接口前缀（获取图片需要）
	private static final String domain = "https://www.azstudio.top/vg/";
	
	
	
	//图片地址
	public static String getImgpath() {
		String path = "";
		try {
			File file = new File(ResourceUtils.getURL("classpath:").getPath());
			path = file.getParentFile().getParentFile().getParent()+File.separator+"vgameResource"+File.separator;
			//Windows下eclipse里运行是下边这个，把下面两个注释打开
//			path = file.getParentFile().getParent()+File.separator+"vgameResource"+File.separator;
//			path = "file:"+path;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return path;
	}

	public static String getDomain() {
		return domain;
	}

	
	
}
