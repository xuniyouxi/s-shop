package com.vg.service.user;


import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Util.BackJSON;
import com.vg.entity.IdentifyCode;
import com.vg.entity.Team;
import com.vg.entity.User;
import com.vg.entity.EVO.UserLogin;
import com.vg.entity.EVO.UserRegister;
public interface UserBehaviorservice {

	List<Team>  getallteam();
	//获取用户
	BackJSON login(UserLogin userlogin) throws Exception;
	
	//获取免责声明
	BackJSON getStatementByFun(int bis_id);
	
	//获取用户权限
	int getUserRoleById(String user_id);
	
	//用户注册
	BackJSON UserRegister(UserRegister userRegister) throws Exception;
	
	//心跳验证
	JSONObject TokenHeartBeat(String token ,String user_id);
	
	//用户注册时候获取验证码
	JSONObject getScodeRegistering(IdentifyCode identifyCode);
	
	//注册发送验证码后,查看验证码是否有效
	JSONObject CheckRegistShortMessage(String user_phone ,int code);

	//用户激活游戏
	JSONObject activateGame(Map<String, String> data);
}
