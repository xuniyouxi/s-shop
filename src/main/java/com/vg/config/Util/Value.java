package com.vg.config.Util;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

public class Value {

	//接口前缀（获取图片需要）
	private static final String domain = "https://www.azstudio.top/vg/";
	//返回商品列表每页大小
	private static final Integer glanceGoodSize = 20;
	//管理员查看信息每页大小
	private static final Integer ASeeSize = 10;
	//允许每个管理员可以拥有的激活码数
	private static final Integer allowActivationCodeSize = 10;
	
	
	//图片地址
	public static String getImgpath() {
		String path = "";
		try {
			File file = new File(ResourceUtils.getURL("classpath:").getPath());
//			System.out.println(file.getPath());
			path = file.getParentFile().getParentFile().getParent()+File.separator+"vgameResource"+File.separator;
//			System.out.println(path);
			//Windows下eclipse里运行是下边这个，把下面两个注释打开
//			path = file.getParentFile().getParent()+File.separator+"vgameResource"+File.separator;
//			path = "file:"+path;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	//轮播图片地址前缀
	public static String getSlidePicPath() {
		return "vgameResource"+File.separator+"user"+File.separator+"slidePicture";
	}
	
	//轮播图片地址前缀
	public static String getStoreImgPath() {
		return "vgameResource"+File.separator+"admin"+File.separator+"storeImg";
	}
	
	

	public static String getDomain() {
		return domain;
	}

	public static Integer getGlancegoodsize() {
		return glanceGoodSize;
	}

	public static Integer getAseesize() {
		return ASeeSize;
	}

	public static Integer getAllowactivationcodesize() {
		return allowActivationCodeSize;
	}

	
	//随机字符串
	public static String randStrNum(int length, int numLen) {
		int size;
		String str = "";
		for(int i=0; i<length; i++) {
			size = ((int)(Math.random()*2))==0 ? 65 : 97;
			str += (char)((int)(Math.random()*26) + size);
		}
		int number = (int)(Math.random()*Math.pow(10, numLen));
		str += number;
		return str;
	}
	
	
}
