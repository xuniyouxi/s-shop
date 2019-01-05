package com.vg.config.Util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class PrintJSON {
	
	public static void printJson(HttpServletResponse response, String data){
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
