package com.vg.controller.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vg.service.user.UserBehaviorservice;

@RestController
@RequestMapping(value = "/user")
public class UserBehaviorController {

	@Autowired
	UserBehaviorservice userbehaviorservice;

	/**
	 * 获取所有team http://localhost:8080/vg/user/sss
	 * @return
	 */
	@RequestMapping(value = "/sss")
	@ResponseBody
	public List<Map<String, Object>> getallteam() {
		return userbehaviorservice.getallteam();
	}

}
