package com.vg.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vg.config.Encrypt.JWTUtil;
import com.vg.config.Encrypt.MD5;
import com.vg.config.Util.BackJSON;
import com.vg.entity.User;
import com.vg.mapper.user.UserBehaviorMapper;

@Service

@CacheConfig()
public class UserBehaviorserviceImpl implements UserBehaviorservice {

	@Autowired
	UserBehaviorMapper userbehavhourmapper;

	// 测试
	@Override
	public BackJSON getallteam() {
		BackJSON backJSON = new BackJSON();
		List<Map<String, Object>> res = userbehavhourmapper.getallteam();
		if (res != null) {
			backJSON.setCode(200);
			backJSON.setData(res);

		} else {
			backJSON.setCode(201);
			backJSON.setData("操作失败");
		}

		return backJSON;
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
	public BackJSON login(User user) throws Exception {
		BackJSON backJSON = new BackJSON();
		String ssss = user.getUser_password();
		System.out.println(ssss);
		user.setUser_password(MD5.md5(user.getUser_password()));
		User res = userbehavhourmapper.getUserByPhoneAndPass(user);
		if (res != null) {
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
