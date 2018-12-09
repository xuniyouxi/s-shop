package com.vg.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vg.config.MyAnn.Authorization;
import com.vg.config.Util.BackJSON;
import com.vg.entity.User;
import com.vg.service.user.UserBehaviorservice;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserBehaviorController {

	@Autowired
	UserBehaviorservice userbehaviorservice;

	/**
	 * fun 注册 http://localhost:8080/vg/user/register
	 * 
	 * @return
	 */
	@RequestMapping(value = "/register")
	@Authorization(authorization = "open")
	public BackJSON register() {
//		System.out.println( UUID.randomUUID().toString().replaceAll("-",""));
		return null;

	}

	/**
	 * fun 登陆
	 * http://localhost:8080/vg/user/login
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/login")
	@Authorization(authorization = "open")
	public BackJSON login(@RequestBody User user) throws Exception {
		System.out.println("asda");
		return userbehaviorservice.login(user);
	}

	/**
	 * fun 获取获取免责声明 http://localhost:8080/vg/user/Statement/1
	 * 
	 * @return
	 */
	@GetMapping({ "/Statement/{bis_id}" })
	@Authorization(authorization = "open")
	public BackJSON getStatement(@PathVariable int bis_id) {
		return userbehaviorservice.getStatementByFun(bis_id);
	}
	


}
