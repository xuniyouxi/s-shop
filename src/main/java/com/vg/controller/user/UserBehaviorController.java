package com.vg.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.vg.config.MyAnn.Authorization;
import com.vg.config.Util.BackJSON;
import com.vg.entity.Team;
import com.vg.entity.User;
import com.vg.entity.EVO.UserLogin;
import com.vg.entity.EVO.UserRegister;
import com.vg.service.user.UserBehaviorservice;
import com.vg.service.user.UserBehaviorserviceImpl;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserBehaviorController {

	@Autowired
	UserBehaviorservice userbehaviorservice;
	@Autowired
	UserBehaviorserviceImpl userbehaviorserviceimpl;

	// 测试分页 http://localhost:8080/vg/user/fenye
	@RequestMapping(value = "/fenye/{kaishi}/{size}")
	@Authorization(authorization = "open")
	public List<Team> fenye(@PathVariable int kaishi ,@PathVariable int size) {
//		System.out.println( UUID.randomUUID().toString().replaceAll("-",""));
		PageHelper.startPage(kaishi, size);
		return userbehaviorservice.getallteam();

	}
	
	
	// 注册获取验证码 http://localhost:8080/vg/user/register
	@RequestMapping(value = "/register")
	@Authorization(authorization = "open")
	public BackJSON register() {
//		System.out.println( UUID.randomUUID().toString().replaceAll("-",""));
		return null;

	}

	// 注册 http://localhost:8080/vg/user/SetPassword
	@PostMapping({ "/register" })
	@Authorization(authorization = "open")
	public BackJSON register(@RequestBody UserRegister userRegister) throws Exception {
		
		return  userbehaviorserviceimpl.UserRegister(userRegister);
	}

	// 登陆 http://localhost:8080/vg/user/login
	@PostMapping(value = "/login")
	@Authorization(authorization = "open")
	public BackJSON login(@RequestBody UserLogin userlogin) throws Exception {
		return userbehaviorservice.login(userlogin);
	}

	// 获取免责声明 http://localhost:8080/vg/user/Statement/1
	@GetMapping({ "/Statement/{bis_id}" })
	@Authorization(authorization = "open")
	public BackJSON getStatement(@PathVariable int bis_id) {
		return userbehaviorservice.getStatementByFun(bis_id);
	}

	// 填写激活码
//	@PostMapping({"/SetPassword"})


}
