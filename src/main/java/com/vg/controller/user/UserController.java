package com.vg.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vg.config.MyAnn.Authorization;


@RestController
@RequestMapping("/user")
public class UserController {

	/**
	 * http://localhost:8080/vg/user/user.do test
	 * 
	 * @return
	 */

	@PostMapping(value = "user")
	@Authorization(authorization="user")
	public int ddd(int a) {
//		int a=1;
//		 a = a/ 0 ;
		return 11111;
	}

}
