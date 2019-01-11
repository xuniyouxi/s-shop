package com.vg.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Util.BackJSON;
import com.vg.entity.Exchange;
import com.vg.entity.IdentifyCode;
import com.vg.entity.Team;
import com.vg.entity.TradeLog;
import com.vg.entity.User;
import com.vg.entity.EVO.UserLogin;
import com.vg.entity.EVO.UserRegister;

public interface UserBehaviorservice {

	List<Team> getallteam();

	// 获取用户
	BackJSON login(UserLogin userlogin, HttpServletResponse response) throws Exception;

	// 获取免责声明
	BackJSON getStatementByFun(int bis_id);

	// 获取用户权限
	int getUserRoleById(String user_id);

	// 用户交换能量
	BackJSON changepower(TradeLog tradeLog) throws Exception;

	// 转入能量池
	BackJSON addpower(String user_id, int power);

	// 用户注册
	BackJSON UserRegister(UserRegister userRegister) throws Exception;

	// 心跳验证
	JSONObject TokenHeartBeat(String token, String user_id);

	// 用户注册时候获取验证码
	BackJSON getScodeRegistering(IdentifyCode identifyCode);

	// 注册发送验证码后,查看验证码是否有效
	BackJSON CheckRegistShortMessage(String user_phone, int code);

	// 用户激活游戏
	BackJSON activateGame(Map<String, String> data);

	// 查询用户资产页
	BackJSON getUserassetsPage(String user_id);

	// 查询首页详情
	BackJSON getfastPage(String user_id);
	
	//获取用户交易记录
	BackJSON getusertradeLog(String User_id,int kaishi,int size);
	
	//获取用户转入能量池记录
	BackJSON getuserPoolLog(String user_id,int kaishi,int size);
	
	//获取版本号
	BackJSON getversion();
}
