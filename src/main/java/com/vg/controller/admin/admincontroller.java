package com.vg.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class admincontroller {

/**
 * http://localhost:8080/vg/admin.do
 * test
 * @return
 */
	@RequestMapping(value = "admin.do")
	@ResponseBody
	public int ddd() {
		int a=1;
		 a = a/ 0 ;
		return 11111;
	}

}
