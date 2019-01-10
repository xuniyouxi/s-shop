package com.vg.controller.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.vg.config.Encrypt.JWTUtil;
import com.vg.config.MyAnn.Authorization;
import com.vg.config.Util.BackJSON;
import com.vg.config.Util.SmsSample;
import com.vg.entity.IdentifyCode;
import com.vg.entity.Team;
import com.vg.entity.TradeLog;
import com.vg.entity.User;
import com.vg.entity.EVO.UserLogin;
import com.vg.entity.EVO.UserRegister;
import com.vg.service.user.UserBehaviorservice;
import com.vg.service.user.UserBehaviorserviceImpl;

import io.jsonwebtoken.Claims;

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
	public List<Team> fenye(@PathVariable int kaishi, @PathVariable int size) {
		// System.out.println( UUID.randomUUID().toString().replaceAll("-",""));
		PageHelper.startPage(kaishi, size);
		return userbehaviorservice.getallteam();
	}

	// 获取用户交易记录
	@GetMapping({ "/getTradeLog/{user_id}/{kaishi}/{size}" })
	@Authorization(authorization = "open")
	public BackJSON getTradeLog(@PathVariable String user_id, @PathVariable int kaishi, @PathVariable int size) {
		// user_id authorization_code

		return userbehaviorservice.getusertradeLog(user_id, kaishi, size);
	}

	// fastPage
	// http://localhost:8080/vg/user/fastPage/c3c1319afb5447aaba9f48d7b8634bc4
	@RequestMapping(value = "/fastPage/{user_id}")
	@Authorization(authorization = "open")
	public BackJSON fastPage(@PathVariable String user_id) {

		return userbehaviorservice.getfastPage(user_id);
	}

	// 资产页查询
	// http://localhost:8080/vg/user/assetsPage/c3c1319afb5447aaba9f48d7b8634bc4
	@RequestMapping(value = "/assetsPage/{user_id}")
	@Authorization(authorization = "user")
	public BackJSON assetsPage(@PathVariable String user_id) {
		// pool_rank能量池等级 pool_rank*X - pool_usedCapacity=能量池能量 invited_bonus用户已经获得的推荐奖励
		// invited_son直接推荐总人数 invited_sum间接推荐总人数
		return userbehaviorservice.getUserassetsPage(user_id);
	}

	// 激活游戏
	@PutMapping({ "/activateGame" })
	@Authorization(authorization = "open")
	public BackJSON activateGame(@RequestBody Map<String, String> data) {
		// user_id authorization_code
		return userbehaviorservice.activateGame(data);
	}

	// 用户交换能量
	@PostMapping({ "/userexchangepower" })
	@Authorization(authorization = "open")
	public BackJSON changepower(@RequestBody TradeLog tradeLog) throws Exception {
		// user_id authorization_code
		return userbehaviorservice.changepower(tradeLog);
	}

	// 用户添加能量到能量池
	@PostMapping({ "/addenergy/{user_id}/{energy}" })
	@Authorization(authorization = "open")
	public BackJSON addpower(@PathVariable String user_id, @PathVariable int energy) {
		// user_id authorization_code

		return userbehaviorservice.addpower(user_id, energy);
	}

	// token心跳验证 http://localhost:8080/vg/user/TokenHeartBeat/sdasdsad
	@RequestMapping(value = "/TokenHeartBeat/{user_id}")
	@Authorization(authorization = "user")
	public JSONObject TokenHeartBeat(HttpServletRequest httpRequest, @PathVariable String user_id) {
		String token = httpRequest.getHeader("token");
		return userbehaviorservice.TokenHeartBeat(token, user_id);
	}

	// 注册获取验证码 http://localhost:8080/vg/user/getScodeRegistering/15524835211
	@RequestMapping(value = "/getScodeRegistering/{Phone}")
	@Authorization(authorization = "open")
	public BackJSON getScodeRegistering(@PathVariable String Phone) {
		// System.out.println( UUID.randomUUID().toString().replaceAll("-",""));
		IdentifyCode con = new IdentifyCode();
		con.setUser_phone(Phone);
		return userbehaviorservice.getScodeRegistering(con);

	}

	// 注册发送验证码后,查看验证码是否有效
	@RequestMapping(value = "/CheckRegistSCode/{user_phone}/{code}")
	@Authorization(authorization = "open")
	public BackJSON CheckRegistSCode(@PathVariable String user_phone, @PathVariable int code) {
		return userbehaviorservice.CheckRegistShortMessage(user_phone, code);
	}

	// 注册 http://localhost:8080/vg/user/register
	@PostMapping({ "/register" })
	@Authorization(authorization = "open")
	public BackJSON register(@RequestBody UserRegister userRegister) throws Exception {

		return userbehaviorservice.UserRegister(userRegister);
	}

	// 登陆 http://localhost:8080/vg/user/login
	@PostMapping(value = "/login")
	@Authorization(authorization = "open")

	public BackJSON login(@RequestBody UserLogin userlogin,HttpServletResponse response) throws Exception {
		return userbehaviorservice.login(userlogin,response);
	}

	// 获取免责声明 http://localhost:8080/vg/user/Statement/1
	@GetMapping({ "/Statement/{bis_id}" })
	@Authorization(authorization = "open")
	public BackJSON getStatement(@PathVariable int bis_id) {
		return userbehaviorservice.getStatementByFun(1);
	}

}
