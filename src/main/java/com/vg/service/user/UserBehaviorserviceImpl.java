package com.vg.service.user;

import java.util.Date;
import java.util.HashMap;
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
import com.vg.config.Util.CheckPhoneNub;
import com.vg.config.Util.SmsSample;
import com.vg.entity.Exchange;
import com.vg.entity.IdentifyCode;
import com.vg.entity.Team;
import com.vg.entity.TradeLog;
import com.vg.entity.User;
import com.vg.entity.UserData;
import com.vg.entity.UserTeam;
import com.vg.entity.EVO.UserLogin;
import com.vg.entity.EVO.UserRegister;
import com.vg.mapper.admin.systemMapper;
import com.vg.mapper.user.UserBehaviorMapper;

import io.jsonwebtoken.Claims;

@Service
@CacheConfig()
public class UserBehaviorserviceImpl implements UserBehaviorservice {

	@Autowired
	UserBehaviorMapper userbehavhourmapper;
	@Autowired
	systemMapper systemMapper;
	private String code;
	private String data;

	// 首页查询
	@Override
	public BackJSON getfastPage(String user_id) {
		// TODO Auto-generated method stub
		BackJSON backJSON = new BackJSON();
		backJSON.setCode(200);
		HashMap<String, Object> res = userbehavhourmapper.getfirstPageuserData(user_id);
		backJSON.setData(res);
		return backJSON;
	}

	// 资产页查询
	@Override
	public BackJSON getUserassetsPage(String user_id) {
		BackJSON backJSON = new BackJSON();
		HashMap<String, Object> res = userbehavhourmapper.getUserassetsPage(user_id);
		backJSON.setCode(200);
		int pool_sum = Integer.parseInt(systemMapper.getPoolRankSum("pool" + res.get("pool_rank") + "_sum"));
		res.put("pool_sum", pool_sum);
		res.put("pool_balance", pool_sum - (int) res.get("pool_usedCapacity"));
		backJSON.setData(res);
		return backJSON;
	}
	// 用户交易
	@Override
	public BackJSON changepower(TradeLog tradeLog) {
		BackJSON backJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		msg.put("msg", "交易成功");
		msg.put("result", 1);
		backJSON.setCode(200);
		backJSON.setData(msg);
		return backJSON;

	}
	//转入能量池
	@Override
	public BackJSON addpower(String user_id,int power) {
		BackJSON backJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		msg.put("msg", "转入成功");
		msg.put("result", 1);
		backJSON.setCode(200);
		backJSON.setData(msg);
		return backJSON;

	}

	// 用户激活
	@Override
	public BackJSON activateGame(Map<String, String> data) { // user_id authorization_code
		BackJSON backJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		backJSON.setCode(200);
		int code_id = 0;
		// 判断激活码
		try {
			code_id = userbehavhourmapper.getauthorization_codeid(data.get("activate_code"));
		} catch (Exception e) {
			msg.put("msg", "激活码不存在");
			msg.put("result", 0);
			backJSON.setData(msg);
			return backJSON;
		}
		// 更新权限
		if (userbehavhourmapper.updateUserRole(data.get("user_id")) == 1) {
			// 用户的userdata信息
			Map<String, Object> userData = userbehavhourmapper.getUserIMIE(data.get("user_id"));
			// 用户他爹的userteam信息
			UserTeam fatherTeam = userbehavhourmapper.getUserTemaById((String) userData.get("invite_code"));
			UserTeam userTeam = new UserTeam();
			userTeam.setUser_id((String) userData.get("user_id"));
			userTeam.setInvited_father((String) userData.get("invite_code"));
			userTeam.setMember_layer(fatherTeam.getMember_layer() + 1);
			userTeam.setInvited_bonus((double) 0);
			userTeam.setTeam_id(fatherTeam.getTeam_id());
			userbehavhourmapper.creatuserteam(userTeam);
			String new_InviteCode = userbehavhourmapper.getSysInviteCode();
			userData.replace("invite_code", new_InviteCode + 2);
			userbehavhourmapper.updataSysInviteCode(Integer.parseInt(new_InviteCode) + 2 + "");
			// 更新自己的data表
			userbehavhourmapper.jihuoUpdata(data.get("user_id"), new_InviteCode);
			// 更新他爹的team表
			fatherTeam.setInvited_son(fatherTeam.getInvited_son() + 1);
			userbehavhourmapper.updataUserTeam(fatherTeam);

			// 从他爷爷开始递归到祖宗，间接人数+1
			UserTeam user_father = userbehavhourmapper.getfatheridformteam(fatherTeam.getInvited_father());
			UserTeam ut = new UserTeam();
			ut.setUser_id(user_father.getUser_id());
			ut.setInvited_sum(user_father.getInvited_sum() + 1);
			ut.setInvited_father(user_father.getInvited_father());
			System.out.println(user_father);
			try {
				while (userbehavhourmapper.updataUserTeam(ut) == 1) {
					user_father = userbehavhourmapper.getfatheridformteam(ut.getInvited_father());
					ut.setUser_id(user_father.getUser_id());
					ut.setInvited_sum(user_father.getInvited_sum() + 1);
					ut.setInvited_father(user_father.getInvited_father());
				}
			} catch (Exception e) {
				// 递归结束，全员加一
			}

			msg.put("msg", "激活成功");
			msg.put("result", 1);
			backJSON.setData(msg);

		} else {
			msg.put("msg", "激活失败");
			msg.put("result", 0);
			backJSON.setData(msg);
		}
		userbehavhourmapper.updataauthorcode(code_id);
		return backJSON;
	}

	// 注册发送验证码后,查看验证码是否有效
	@Override
	public BackJSON CheckRegistShortMessage(String user_phone, int code) {
		BackJSON backJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		long used_time = new Date().getTime() - 600000;
		IdentifyCode identifyCode = userbehavhourmapper.getShortMessageByPhoneAndCode(user_phone, code,
				new Date(used_time));
		System.out.println(identifyCode);
		if (identifyCode == null) {
			msg.put("msg", "验证码错误");
			msg.put("result", 0);
			backJSON.setCode(200);
			backJSON.setData(msg);
			return backJSON;
		}
		identifyCode.setUsed_static(1);
		if (userbehavhourmapper.UpdateIdentifyCodeState(identifyCode) != 1) {
			backJSON.setCode(200);
			msg.put("msg", "验证码错误");
			msg.put("result", 0);
			backJSON.setData(msg);
			return backJSON;
		}
		msg.put("msg", "验证通过");
		msg.put("result", 1);
		backJSON.setCode(200);
		backJSON.setData(msg);
		return backJSON;
	}

	// 用户注册时候,获取短信验证码
	@Override
	public BackJSON getScodeRegistering(IdentifyCode identifyCode) {
		BackJSON BackJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		if (!CheckPhoneNub.isMobiPhoneNum(identifyCode.getUser_phone())) {
			BackJSON.setCode(200);
			msg.put("msg", "手机号码不正确");
			msg.put("result", 0);
			BackJSON.setData(msg);
			return BackJSON;
		}
		int SendCode = (int) ((Math.random() * 9 + 1) * 10000);
		identifyCode.setIdentify_code(SendCode);
		identifyCode.setUsed_time(new Date());
		identifyCode.setUsed_method(1);
		String res = SmsSample.SendMessage(identifyCode.getUser_phone(), SendCode);
		if (userbehavhourmapper.insertCodeByuserphone(identifyCode) == 1 && res.equals("0")) {
			BackJSON.setCode(200);
			msg.put("msg", "发送成功");
			msg.put("result", 1);
			BackJSON.setData(msg);
			return BackJSON;
		}
		BackJSON.setCode(200);
		msg.put("msg", "发送失败");
		msg.put("result", 0);
		BackJSON.setData(msg);
		return BackJSON;
	}

	// 心跳验证
	@Override
	public JSONObject TokenHeartBeat(String token, String user_id) {
		JSONObject jsonobj = new JSONObject();
		if (JWTUtil.parseJWT(token) == null) {
			jsonobj.put(code, 400);
			jsonobj.put(data, "无效token");
			return jsonobj;
		}
		Claims content = (Claims) JWTUtil.parseJWT(token);
		// 剩余时间等于过期时间减去当前时间
		long surplusTime = content.getExpiration().getTime() - new Date().getTime();
		String userId = (String) content.get("userId");
		System.out.println(surplusTime);
		if (!userId.equals(user_id)) {
			jsonobj.put(code, 401);
			jsonobj.put(data, "token和用户不符");
			return jsonobj;
		}
		if (surplusTime > 0 && surplusTime < JWTUtil.get_ttlMillis() / 4) {
			int user_role = userbehavhourmapper.getUserRoleById(user_id);
			jsonobj.put(code, 200);
			Map<String, String> res = new HashMap<>();
			res.put("token", JWTUtil.createJWT(user_id, user_role));
			jsonobj.put(data, res);
			return jsonobj;
		}
		jsonobj.put(code, 201);
		jsonobj.put(data, "token有效期还富裕");
		return jsonobj;
	}

	// 用户注册
	public BackJSON UserRegister(UserRegister userRegister) throws Exception {
		System.out.println(new Date());
		BackJSON backJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		String father_id = userbehavhourmapper.getUserIdByinviteCode(userRegister.getFather_inviteCode());
		if (father_id == null) {
			msg.put("msg", "邀请码不存在");
			msg.put("result", 0);
			backJSON.setCode(200);
			backJSON.setData(msg);
			return backJSON;
		}
		if (userbehavhourmapper.getUserIdByPhone(userRegister.getUser_phone()) > 0
				|| CheckPhoneNub.isMobiPhoneNum(userRegister.getUser_phone()) == false) {
			msg.put("msg", "手机号码重复或号码无效");
			msg.put("result", 0);
			backJSON.setCode(200);
			backJSON.setData(msg);
			return backJSON;
		}
		User user = new User();
		user.setCreate_time(new Date());
		String new_user = UUID.randomUUID().toString().replaceAll("-", "");
		user.setUser_id(new_user);
		user.setUser_role(1);
		user.setUser_phone(userRegister.getUser_phone());
		user.setUser_password(MD5.md5(userRegister.getUser_password()));
		// 创建用户时，用户权限为999代表失效用户，激活后才能使用
		if (userbehavhourmapper.createUser(user) < 1) {
			msg.put("msg", "创建用户失败请重新创建");
			msg.put("result", 0);
			backJSON.setCode(200);
			backJSON.setData(msg);
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
		msg.put("msg", "成功创建用户，等待填写激活码");
		msg.put("result", 1);
		backJSON.setData(msg);
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
		Map<String, Object> msg = new HashMap<>();
		user.setUser_password(MD5.md5(user.getUser_password()));
		User res = userbehavhourmapper.getUserByPhoneAndPass(user);
		if (res != null) {
			Map<String, Object> userdata = userbehavhourmapper.getUserIMIE(res.getUser_id());
			if (userdata.get("user_equipment_id1").equals("NULL")
					&& userdata.get("user_equipment_id2").equals("NULL")) {
				if (userbehavhourmapper.updatauser_equipment_id1(user.getUser_equipment_id(), res.getUser_id()) != 1) {
					// 如果两个都是空，就更新第一个设备号
					backJSON.setCode(200);
					msg.put("result", 0);
					msg.put("msg", "登录异常");
					backJSON.setData(msg);
					return backJSON;
				}
				String token = JWTUtil.createJWT(res.getUser_id(), res.getUser_role());
				System.out.println(token);
				System.out.println(JWTUtil.parseJWT(token));
				userdata.put("token", token);
				userdata.put("user_phone", user.getUser_phone());
				userdata.remove("user_pay_password");
				userdata.remove("user_equipment_id2");
				userdata.remove("user_equipment_id1");
				userdata.remove("authorization_code");
				userdata.put("user_equipment_id", user.getUser_equipment_id());
				userdata.put("user_role", res.getUser_role());
				backJSON.setCode(200);
				msg.put("result", 1);
				msg.put("data", userdata);
				backJSON.setData(msg);
				return backJSON;
			} else if (userdata.get("user_equipment_id1").equals("NULL")
					&& !userdata.get("user_equipment_id2").equals(user.getUser_equipment_id())) {
				if (userbehavhourmapper.updatauser_equipment_id1(user.getUser_equipment_id(), res.getUser_id()) != 1) {
					// 如果两个都是空，就更新第一个设备号
					backJSON.setCode(200);
					msg.put("result", 0);
					msg.put("msg", "登录异常");
					backJSON.setData(msg);
					return backJSON;
				}
				String token = JWTUtil.createJWT(res.getUser_id(), res.getUser_role());
				System.out.println(token);
				System.out.println(JWTUtil.parseJWT(token));
				userdata.put("token", token);
				userdata.put("user_phone", user.getUser_phone());
				userdata.remove("user_pay_password");
				userdata.remove("user_equipment_id2");
				userdata.remove("user_equipment_id1");
				userdata.remove("authorization_code");
				userdata.put("user_equipment_id", user.getUser_equipment_id());
				userdata.put("user_role", res.getUser_role());
				backJSON.setCode(200);
				msg.put("result", 1);
				msg.put("data", userdata);
				backJSON.setData(msg);
				return backJSON;
			} else if (userdata.get("user_equipment_id2").equals("NULL")
					&& !userdata.get("user_equipment_id1").equals(user.getUser_equipment_id())) {
				if (userbehavhourmapper.updatauser_equipment_id2(user.getUser_equipment_id(), res.getUser_id()) != 1) {
					// 如果两个都是空，就更新第一个设备号
					backJSON.setCode(200);
					msg.put("result", 0);
					msg.put("msg", "登录异常");
					backJSON.setData(msg);
					return backJSON;
				}
				String token = JWTUtil.createJWT(res.getUser_id(), res.getUser_role());
				System.out.println(token);
				System.out.println(JWTUtil.parseJWT(token));
				userdata.put("token", token);
				userdata.put("user_phone", user.getUser_phone());
				userdata.remove("user_pay_password");
				userdata.remove("user_equipment_id2");
				userdata.remove("user_equipment_id1");
				userdata.remove("authorization_code");
				userdata.put("user_equipment_id", user.getUser_equipment_id());
				userdata.put("user_role", res.getUser_role());
				backJSON.setCode(200);
				msg.put("result", 1);
				msg.put("data", userdata);
				backJSON.setData(msg);
				return backJSON;
			} else {
				if ((userdata.get("user_equipment_id1").equals(user.getUser_equipment_id()))
						|| userdata.get("user_equipment_id2").equals(user.getUser_equipment_id())) {
					String token = JWTUtil.createJWT(res.getUser_id(), res.getUser_role());
					System.out.println(token);
					System.out.println(JWTUtil.parseJWT(token));
					userdata.put("token", token);
					userdata.put("user_phone", user.getUser_phone());
					userdata.remove("user_pay_password");
					userdata.remove("user_equipment_id2");
					userdata.remove("user_equipment_id1");
					userdata.remove("authorization_code");
					userdata.put("user_equipment_id", user.getUser_equipment_id());
					userdata.put("user_role", res.getUser_role());
					backJSON.setCode(200);
					msg.put("result", 1);
					msg.put("data", userdata);
					backJSON.setData(msg);
					return backJSON;

				} else {
					backJSON.setCode(200);
					msg.put("result", 0);
					msg.put("msg", "不是指定设备");
					backJSON.setData(msg);
					return backJSON;
				}
			}
		} else {

			backJSON.setCode(200);
			msg.put("msg", "账号或密码错误");
			msg.put("result", 0);
			backJSON.setData(msg);
			return backJSON;
		}

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
			backJSON.setCode(200);
			backJSON.setData("请求未授权，跳转授权提示页");
		}

		return backJSON;
	}

}
