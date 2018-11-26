package com.vg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class usercontroller {

/**
 * http://localhost:8080/vg/test.do
 * test
 * @return
 */
	@RequestMapping(value = "test.do")
	@ResponseBody
	public int ddd() {
		int a=1;
		 a = a/ 0 ;
		return 11111;
	}

}
