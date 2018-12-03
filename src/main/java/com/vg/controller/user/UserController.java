package com.vg.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

/**
 * http://localhost:8080/vg/user.do
 * test
 * @return
 */
	@RequestMapping(value = "user.do")
	@ResponseBody
	public int ddd() {
		int a=1;
		 a = a/ 0 ;
		return 11111;
	}

}
