package com.vg.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.aspectj.weaver.patterns.HasMemberTypePattern;
import org.hibernate.id.enhanced.PooledLoOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties.Whitelabel;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vg.InitDataListener;
import com.vg.config.Encrypt.JWTUtil;
import com.vg.config.Encrypt.MD5;
import com.vg.config.Encrypt.UUID8;
import com.vg.config.Util.BackJSON;
import com.vg.config.Util.CheckPhoneNub;
import com.vg.config.Util.PrintJSON;
import com.vg.config.Util.SmsSample;
import com.vg.config.Util.TokenHeader;
import com.vg.entity.Exchange;
import com.vg.entity.IdentifyCode;
import com.vg.entity.PoolOperation;
import com.vg.entity.Team;
import com.vg.entity.TradeLog;
import com.vg.entity.User;
import com.vg.entity.UserData;
import com.vg.entity.UserTeam;
import com.vg.entity.EVO.UserLogin;
import com.vg.entity.EVO.UserRecommendInfo;
import com.vg.entity.EVO.UserRegister;
import com.vg.entity.EVO.getAllUserBalance;
import com.vg.mapper.admin.systemMapper;
import com.vg.mapper.user.UserBehaviorMapper;
import com.vg.mapper.user.UserMapper;

import io.jsonwebtoken.Claims;

@Service
@Transactional
@CacheConfig()
public class UserBehaviorserviceImpl implements UserBehaviorservice {

	@Autowired
	UserBehaviorMapper userbehavhourmapper;
	@Autowired
	systemMapper systemMapper;
	private String code;
	private String data;

	// 获取版本号
	@Override
	public BackJSON getversion() {
		// TODO Auto-generated method stub
		BackJSON backJSON = new BackJSON();
		backJSON.setCode(200);
		String res = systemMapper.getOperationContent1(8);
		Map<String, String> content = new HashMap<>();
		content.put("version_code", res);
		backJSON.setData(content);
		return backJSON;
	}

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
		// 用户等级对应的能量池总数
		int pool_sum = Integer.parseInt(systemMapper.getPoolRankSum("pool" + res.get("pool_rank") + "_sum"));
		res.put("pool_sum", pool_sum);
		res.put("pool_balance", pool_sum - (int) res.get("pool_usedCapacity"));
		backJSON.setData(res);
		return backJSON;
	}

	// 用户交易
	@Override
	public BackJSON changepower(TradeLog tradeLog) throws Exception {
		BackJSON backJSON = new BackJSON();
		backJSON.setCode(200);
		Map<String, Object> msg = new HashMap<>();
		UserData userData = userbehavhourmapper.getuserDataByPayPass(tradeLog.getUser_id(),
				MD5.md5(tradeLog.getPay_password()), tradeLog.getTrade_number() + tradeLog.getTrade_number() * 1 / 10);
		if (userData == null || tradeLog.getTrade_number() < 0) {
			msg.put("msg", "交易失败,请查看密码或余额");
			msg.put("result", 0);
			backJSON.setData(msg);
			return backJSON;
		}
		UserData ToUserData = userbehavhourmapper.getuserDataByPhone(tradeLog.getTouser_phone());
		if (ToUserData == null) {
			msg.put("msg", "交易失败,用户不存在");
			msg.put("result", 0);
			backJSON.setData(msg);
			return backJSON;
		}
		// 开始交易逻辑
		// 更新自己余额
		Double powers = tradeLog.getTrade_number() / 10;
		UserTeam user_team = userbehavhourmapper.getUserTemaById(tradeLog.getUser_id());
		userData.setUser_balance(userData.getUser_balance() - tradeLog.getTrade_number() - powers);
		ToUserData.setUser_balance(ToUserData.getUser_balance() + tradeLog.getTrade_number() - powers);
		tradeLog.setService_charge(tradeLog.getTrade_number() * 1 / 10);
		tradeLog.setTrade_time(new Date());
		tradeLog.setTeam_id(user_team.getTeam_id());
		int resLog = userbehavhourmapper.insertTradeLog(tradeLog);
		int UserRes = userbehavhourmapper.updatauserData(userData);
		int ToUserRes = userbehavhourmapper.updatauserData(ToUserData);

		if (UserRes == 1 && ToUserRes == 1 && createServiceCharge(powers, tradeLog.getUser_id()) && resLog == 1) {
			msg.put("msg", "交易成功");
			msg.put("result", 1);
			backJSON.setData(msg);
			return backJSON;
		} else
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		msg.put("msg", "交易失败");
		msg.put("result", 0);
		backJSON.setData(msg);
		return backJSON;

	}

	// 赠送能量的手续费逻辑,对应changepower(TradeLog tradeLog) service
	private Boolean createServiceCharge(Double power, String user_id) {
		try {
			System.out.println(power);
			int vip = 0;
			// 父亲会获得自己手续费的十分之2
			// 开始递归查询自己所有祖宗，
			// 一星到5星各拿10分支1
			// 1第一步，先递归查询自己的所有爹和祖宗，获取<=5个人信息，！先查自己爹是不是会员如果爹是会员，那么，直接给爹20%，如果爹不是会员,查爹的爹和祖宗，查第一个星级会员给他10%，查到的星级n+1，再给10%，直到n=5结束
			UserData father = userbehavhourmapper.getUserBalance(user_id);
			System.out.println(father);
			int MemberLayer = userbehavhourmapper.getMemberLayer(user_id);
			int flag = 1;
			if (father.getUser_vip() > 0) {
				System.out.println("原" + father.getUser_balance());
				father.setUser_balance(father.getUser_balance() + power * 20 / 100);
				vip = father.getUser_vip();
				System.out.println(vip + "====" + father.getUser_balance());
				System.out.println("baba" + vip);
				userbehavhourmapper.updatauserData(father);
			}
			while (vip <= 5 && vip != 5 && flag < MemberLayer * 2) {
				flag++;
				father = userbehavhourmapper.getUserBalance(father.getUser_id());
				if (father == null)
					break;
				if (father.getUser_vip() > vip) {
					vip = father.getUser_vip();
					System.out.println("原" + father.getUser_balance());
					father.setUser_balance(father.getUser_balance() + power * 10 / 100);
					System.out.println(vip + "====" + father.getUser_balance());
					userbehavhourmapper.updatauserData(father);
					if (vip == 5)
						break;
					System.out.println("我是falg" + flag);
					if (flag > 5)
						break;
				} else
					continue;
			}

			System.out.println("处理能量");
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	// 转入能量池
	@Override
	public BackJSON addpower(String user_id, int power) {
		BackJSON backJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		msg.put("msg", "转入失败");
		msg.put("result", 0);
		backJSON.setCode(200);
		backJSON.setData(msg);
		if (power % 100 != 0 || power < 100) {
			msg.put("msg", "请转整百能量,或转入能量小于100");
			msg.put("result", 0);
			backJSON.setData(msg);
			return backJSON;
		}
		UserData userData = userbehavhourmapper.getuserDataByUserId(user_id);
		// 用户等级对应的能量池总数
		int pool_sum = Integer.parseInt(systemMapper.getPoolRankSum("pool" + userData.getPool_rank() + "_sum"));
		if (pool_sum - userData.getPool_usedCapacity() < power || power > userData.getUser_balance()) {
			msg.put("msg", "超过能量池容量或余额不足");
			msg.put("result", 0);
			backJSON.setData(msg);
			return backJSON;
		}
		userData.setUser_balance(userData.getUser_balance() - power);
		userData.setPool_usedCapacity(userData.getPool_usedCapacity() + power);
		PoolOperation poolLog = new PoolOperation();
		poolLog.setInto_balance(power);
		poolLog.setUser_id(user_id);
		poolLog.setOperation_time(new Date());

		int poolLogres = userbehavhourmapper.insertPoolLog(poolLog);
		if (userbehavhourmapper.updatauserData(userData) == 1 && poolLogres == 1) {
			msg.put("msg", "转入成功");
			msg.put("result", 1);
			backJSON.setCode(200);
			backJSON.setData(msg);
			return backJSON;
		} else
			// 手动回滚事务
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		msg.put("msg", "转入失败");
		msg.put("result", 0);
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
			Map<String, Object> userData = userbehavhourmapper.getUserData(data.get("user_id"));
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

			try {
				// 从他爷爷开始递归到祖宗，间接人数+1
				UserTeam user_father = userbehavhourmapper.getfatheridformteam(fatherTeam.getInvited_father());
				UserTeam ut = new UserTeam();
				ut.setUser_id(user_father.getUser_id());
				ut.setInvited_sum(user_father.getInvited_sum() + 1);
				ut.setInvited_father(user_father.getInvited_father());
				System.out.println(user_father);
				while (userbehavhourmapper.updataUserTeam(ut) == 1) {
					user_father = userbehavhourmapper.getfatheridformteam(ut.getInvited_father());
					ut.setUser_id(user_father.getUser_id());
					ut.setInvited_sum(user_father.getInvited_sum() + 1);
					ut.setInvited_father(user_father.getInvited_father());
				}
				// 通过队伍id，开始更新用户，自定向下
				// 通过id获取用户的team信息，挨个遍历，从祖宗开始，获取祖宗id 祖宗直推人数，祖宗间接推荐人数，获取用户vip等级
				List<UserRecommendInfo> userRecommendInfo = userbehavhourmapper
						.getUserRecommendInfo(userTeam.getTeam_id());
				for (UserRecommendInfo record : userRecommendInfo) {
					System.out.println(updataRank(record));
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

	// 激活时，更新用户vip级别
	private Boolean updataRank(UserRecommendInfo u) {
		int flag = 0;
		System.out.println("进入更新用户级别");
		System.out.println("上面是soncount");
		if (u.getUser_vip() == 0 && u.getInvited_son() >= 50) { // 如果是0级，那么，判断是否直推超过50，如果50更新等级为1级，用户升1级
			flag = userbehavhourmapper.UpdateUserVip(u.getUser_id(), 1);
			System.out.println("我是0级，满足1级");
		} else if (u.getUser_vip() == 1 && userbehavhourmapper.getSonCount(u.getUser_id(), 1) >= 2
				&& u.getInvited_sum() >= 300) { // 如果是1级，那么，判断是否直推50，并且间接300，并且儿子里有vip=1，升2级
			flag = userbehavhourmapper.UpdateUserVip(u.getUser_id(), 2);
			System.out.println("我是1级，满足2级");
		} else if (u.getUser_vip() == 2 && userbehavhourmapper.getSonCount(u.getUser_id(), 2) >= 2
				&& u.getInvited_sum() >= 1000) {// 如果用户是2级，判断是否直推50，并且间接1000,，儿子里有2个以上vip=2，升3级
			flag = userbehavhourmapper.UpdateUserVip(u.getUser_id(), 3);
			System.out.println("我是2级，满足3级");
		} else if (u.getUser_vip() == 3 && userbehavhourmapper.getSonCount(u.getUser_id(), 3) >= 2
				&& u.getInvited_sum() >= 3000) {// 如果用户是2级，判断是否直推50，并且间接3000,，儿子里有2个以上vip=3，升4级
			flag = userbehavhourmapper.UpdateUserVip(u.getUser_id(), 4);
			System.out.println("我是3级，满足4级");
		} else if (u.getUser_vip() == 1 && userbehavhourmapper.getSonCount(u.getUser_id(), 14) >= 2
				&& u.getInvited_sum() >= 10000) {// 如果用户是2级，判断是否直推50，并且间接10000,，儿子里有2个以上vip=4，升5级
			flag = userbehavhourmapper.UpdateUserVip(u.getUser_id(), 5);
			System.out.println("我是4级，满足5级");
		}

		if (flag == 1)
			return true;
		else
			return false;
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
			res.put("token", JWTUtil.createJWT(user_id, user_role, null));
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
	public BackJSON login(UserLogin user, HttpServletResponse response) throws Exception {

		BackJSON backJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		user.setUser_password(MD5.md5(user.getUser_password()));
		User res = userbehavhourmapper.getUserByPhoneAndPass(user);
		// 如果用户没有激活返回一个未激活吗
		if (res != null) {
			if (res.getUser_role() == 999) {
				System.out.println("未激活");
				backJSON.setCode(200);
				msg.put("result", 0);
				msg.put("msg", "未激活");
				msg.put("user_role", 999);
				backJSON.setData(msg);
//				TokenHeader.addTokenToResponseHeder(response, "token", "400");
				return backJSON;
			}
//			// 判断这台机器有几个人在线
//			int equUsedSum = userbehavhourmapper.getPhoneTokenidSum(user.getUser_equipment_id(), res.getUser_id());
//			System.out.println(equUsedSum + "======" + res.getUser_id() + "===" + user.getUser_equipment_id());
//			if (equUsedSum > 0) {
//				backJSON.setCode(200);
//				msg.put("result", 0);
//				msg.put("msg", "当前设备超过1个账户同时登陆");
//				backJSON.setData(msg);
//				return backJSON;
//			}
			List<String> allIMEI1 = userbehavhourmapper.getAllIMEI1(res.getUser_id());
			System.out.println("==========");
			System.out.println(allIMEI1);
			List<String> allIMEI2 = userbehavhourmapper.getAllIMEI2(res.getUser_id());
			System.out.println("==========");
			System.out.println(allIMEI2);
			for (String string : allIMEI1) {
				if (string.equals(user.getUser_equipment_id()) && !string.equals("NULL")) {
					backJSON.setCode(200);
					msg.put("result", 0);
					msg.put("msg", "一台设备只能登陆一个号");
					backJSON.setData(msg);
					return backJSON;
				}
			}
			for (String string : allIMEI2) {
				if (string.equals(user.getUser_equipment_id()) && !string.equals("NULL")) {
					backJSON.setCode(200);
					msg.put("result", 0);
					msg.put("msg", "一台设备只能登陆一个号");
					backJSON.setData(msg);
					return backJSON;
				}
			}
			Map<String, Object> userdata = userbehavhourmapper.getUserData(res.getUser_id());

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
				String token = JWTUtil.createJWT(res.getUser_id(), res.getUser_role(), user.getUser_equipment_id());
				TokenHeader.addTokenToResponseHeder(response, "token", token);
				System.out.println(JWTUtil.parseJWT(token));
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
				userbehavhourmapper.updateTokenId(user.getUser_equipment_id(), (String) userdata.get("user_id"));
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
				String token = JWTUtil.createJWT(res.getUser_id(), res.getUser_role(), user.getUser_equipment_id());
				System.out.println(token);
				System.out.println(JWTUtil.parseJWT(token));
				TokenHeader.addTokenToResponseHeder(response, "token", token);
				userdata.put("user_phone", user.getUser_phone());
				userdata.remove("user_pay_password");
				userdata.remove("user_equipment_id2");
				userdata.remove("user_equipment_id1");
				userdata.remove("authorization_code");
				userdata.put("user_equipment_id", user.getUser_equipment_id());
				userdata.put("user_role", res.getUser_role());
				backJSON.setCode(200);
				userbehavhourmapper.updateTokenId(user.getUser_equipment_id(), (String) userdata.get("user_id"));
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
				String token = JWTUtil.createJWT(res.getUser_id(), res.getUser_role(), user.getUser_equipment_id());
				System.out.println(token);
				System.out.println(JWTUtil.parseJWT(token));
				TokenHeader.addTokenToResponseHeder(response, "token", token);
				userdata.put("user_phone", user.getUser_phone());
				userdata.remove("user_pay_password");
				userdata.remove("user_equipment_id2");
				userdata.remove("user_equipment_id1");
				userdata.remove("authorization_code");
				userdata.put("user_equipment_id", user.getUser_equipment_id());
				userdata.put("user_role", res.getUser_role());
				userbehavhourmapper.updateTokenId(user.getUser_equipment_id(), (String) userdata.get("user_id"));
				backJSON.setCode(200);
				msg.put("result", 1);
				msg.put("data", userdata);
				backJSON.setData(msg);
				return backJSON;
			} else {
				if ((userdata.get("user_equipment_id1").equals(user.getUser_equipment_id()))
						|| userdata.get("user_equipment_id2").equals(user.getUser_equipment_id())) {
					String token = JWTUtil.createJWT(res.getUser_id(), res.getUser_role(), user.getUser_equipment_id());
					System.out.println(token);
					System.out.println(JWTUtil.parseJWT(token));
					TokenHeader.addTokenToResponseHeder(response, "token", token);
					userdata.put("user_phone", user.getUser_phone());
					userdata.remove("user_pay_password");
					userdata.remove("user_equipment_id2");
					userdata.remove("user_equipment_id1");
					userdata.remove("authorization_code");
					userdata.put("user_equipment_id", user.getUser_equipment_id());
					userdata.put("user_role", res.getUser_role());
					userbehavhourmapper.updateTokenId(user.getUser_equipment_id(), (String) userdata.get("user_id"));
					backJSON.setCode(200);
					msg.put("result", 1);
					msg.put("data", userdata);
					backJSON.setData(msg);
					return backJSON;

				} else {

					// TokenHeader.addTokenToResponseHeder(response, "token", "400");

					backJSON.setCode(250);
					msg.put("result", 0);
					msg.put("msg", "不是指定设备");
					backJSON.setData(msg);
					return backJSON;
				}
			}
		} else {
			// TokenHeader.addTokenToResponseHeder(response, "token", "400");

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

	// 查询用户交易记录
	@Override
	public BackJSON getusertradeLog(String User_id, int kaishi, int size) {
		PageHelper.startPage(kaishi, size);
		List<Map<String, Object>> touser = userbehavhourmapper.getusertradeLog(User_id);
		PageInfo<Map<String, Object>> p = new PageInfo<>(touser);
		BackJSON backJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		for (Map<String, Object> map : touser) {
			map.put("toUser_name", userbehavhourmapper.getusernametradeLog((String) map.get("touser_phone")));

		}
		msg.put("page_no", kaishi);
		msg.put("page_size", size);
		msg.put("total_count", p.getTotal());
		msg.put("list", touser);
		backJSON.setCode(200);
		backJSON.setData(msg);
		return backJSON;
	}

	// 查询用户转入能量池记录
	@Override
	public BackJSON getuserPoolLog(String user_id, int kaishi, int size) {
		PageHelper.startPage(kaishi, size);
		List<PoolOperation> touser = userbehavhourmapper.getPoolOperByid(user_id);
		PageInfo<PoolOperation> p = new PageInfo<>(touser);
		BackJSON backJSON = new BackJSON();
		Map<String, Object> msg = new HashMap<>();
		msg.put("page_no", kaishi);
		msg.put("page_size", size);
		msg.put("total_count", p.getTotal());
		msg.put("list", touser);
		backJSON.setCode(200);
		backJSON.setData(msg);
		return backJSON;
	}

}
