package com.vg.service.user;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Encrypt.JWTUtil;
import com.vg.config.Encrypt.MD5;
import com.vg.config.Encrypt.UUID8;
import com.vg.config.Util.BackJSON;
import com.vg.entity.Team;
import com.vg.entity.User;
import com.vg.entity.UserTeam;
import com.vg.entity.EVO.UserLogin;
import com.vg.entity.EVO.UserRegister;
import com.vg.mapper.user.UserBehaviorMapper;

import io.jsonwebtoken.Claims;

@Service
@CacheConfig()
public class UserBehaviorserviceImpl implements UserBehaviorservice {

	@Autowired
	UserBehaviorMapper userbehavhourmapper;
	
	//心跳验证
	@Override
	public JSONObject TokenHeartBeat(String token, String user_id) {
		JSONObject jsonobj = new JSONObject();
		if (JWTUtil.parseJWT(token) == null) {
			jsonobj.put("code", 400);
			jsonobj.put("data", "无效token");
			return jsonobj;
		}
		Claims content = (Claims) JWTUtil.parseJWT(token);
		// 剩余时间等于过期时间减去当前时间
		long surplusTime = content.getExpiration().getTime() - new Date().getTime();
		String userId = (String) content.get("userId");
		System.out.println(surplusTime);
		if(!userId.equals(user_id)) {
			jsonobj.put("code", 401);
			jsonobj.put("data", "token和用户不符");
			return jsonobj;
		}
		if (surplusTime > 0 && surplusTime < JWTUtil.get_ttlMillis() / 4) {
			int user_role = userbehavhourmapper.getUserRoleById(user_id);
			jsonobj.put("code", 200);
			jsonobj.put("token", JWTUtil.createJWT(user_id, user_role));
			return jsonobj;
		}
		jsonobj.put("code", 201);
		jsonobj.put("data", "token有效期还富裕");
		return jsonobj;
	}

	

	// 用户注册
	public BackJSON UserRegister(UserRegister userRegister) throws Exception {
		System.out.println(new Date());
		BackJSON backJSON = new BackJSON();
		String father_id = userbehavhourmapper.getUserIdByinviteCode(userRegister.getFather_inviteCode());
		if (father_id == null) {
			backJSON.setCode(401);
			backJSON.setData("邀请码不存在");
			return backJSON;
		}
		if (userbehavhourmapper.getUserIdByPhone(userRegister.getUser_phone()) > 0) {
			backJSON.setCode(402);
			backJSON.setData("手机号码重复");
			return backJSON;
		}
		User user = new User();
		user.setCreate_time(new Date());
		String new_user = UUID.randomUUID().toString().replaceAll("-", "");
		user.setUser_id(new_user);
		user.setUser_role(1);
		user.setUser_phone(userRegister.getUser_phone());
		user.setUser_password(MD5.md5(userRegister.getUser_password()));
		if (userbehavhourmapper.createUser(user) < 1) {
			backJSON.setCode(403);
			backJSON.setData("创建用户失败请重新创建");
			return backJSON;
		}
//		String new_InviteCode = userbehavhourmapper.getSysInviteCode();
		userRegister.setUser_id(new_user);
//		userRegister.setInvite_code(Integer.parseInt(new_InviteCode) + 2 + "");
		userRegister.setUser_realname(UUID8.getShortUuid());
		// 用户注册时，自己暂时不生成邀请码，只有当自己激活时，才生成自己的邀请码，此时，邀请码等于父亲的id
		userRegister.setInvite_code(father_id);
//		 插入user_data
//		userbehavhourmapper.updataSysInviteCode(Integer.parseInt(new_InviteCode) + 2 + "");
//		UserTeam userteam = userbehavhourmapper.getUserTemaById(father_id);
		userbehavhourmapper.insertUserData(userRegister);
		backJSON.setCode(200);
		backJSON.setData("成功创建用户，等待填写激活码");
		return backJSON;
	}

	// 测试分页
	@Override
	public List<Team> getallteam() {
		List<Team> res = userbehavhourmapper.getallteam();
		return res;
	}

	// 获取用户的权限,拦截器用
	@Override
	@Cacheable(value = "userRole", key = "#user_id")
	public int getUserRoleById(String user_id) {
		// TODO Auto-generated method stub
		try {
			return userbehavhourmapper.getUserRoleById(user_id);
		} catch (Exception e) {
			return 0;
		}

	}

	// 用户登录
	@Override
	public BackJSON login(UserLogin user) throws Exception {
		BackJSON backJSON = new BackJSON();
		user.setUser_password(MD5.md5(user.getUser_password()));
		User res = userbehavhourmapper.getUserByPhoneAndPass(user);
		if (res != null) {
			Map<String, String> userIMIE = userbehavhourmapper.getUserIMIE(res.getUser_id());
			System.out.println(userIMIE);
			if (!(userIMIE.get("user_equipment_id1").equals(user.getUser_equipment_id()))
					&& !(userIMIE.get("user_equipment_id2").equals(user.getUser_equipment_id()))) {
				backJSON.setCode(405);
				backJSON.setData("不是指定设备");
				return backJSON;
			}
			String token = JWTUtil.createJWT(res.getUser_id(), res.getUser_role());
			System.out.println(token);
			System.out.println(JWTUtil.parseJWT(token));
			backJSON.setCode(200);
			backJSON.setData(token);
		} else {
			backJSON.setCode(404);
			backJSON.setData("请求的资源不存在");
		}

		return backJSON;
	}

	// 获取小功能
	@Override
	@Cacheable(value = "t_biscuits", key = "#bis_id")
	public BackJSON getStatementByFun(int bis_id) {
		BackJSON backJSON = new BackJSON();
		Map<String, Object> res = userbehavhourmapper.getStatementByFun(bis_id);
		if (res != null) {
			backJSON.setCode(200);
			backJSON.setData(res);
		} else {
			backJSON.setCode(404);
			backJSON.setData("请求资源不存在");
		}

		return backJSON;
	}


}
