package com.vg.service.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Util.BackJSON;
import com.vg.entity.EVO.UserInfo;
import com.vg.mapper.user.UserMapper;

@Service
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
@CacheConfig()
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper um;
	
	@Override
	public BackJSON getEquipment(String user_id) {
		BackJSON json = new BackJSON(400, "");
		Map<String, String> tm = um.getEquipment(user_id);
		if(!tm.isEmpty()) {
			json.setData(tm);
			json.setCode(200);
		}
		return json;
	}
	@Override
	public JSONObject untie(String user_id, int num) {
		JSONObject json = new JSONObject();
		json.put("code", 400);
		if(um.untieEquipment(user_id, "user_equipment_id"+num)==1) {
			json.replace("code", 202);
		}
		return json;
	}
	@Override
	public BackJSON getInfo(String user_id) {
		BackJSON json = new BackJSON(400, "");
		UserInfo user = um.getInfo(user_id);
		if(user!=null) {
			json.setData(user);
			json.setCode(200);
		}
		return json;
	}
	@Override
	public JSONObject alterInfo(UserInfo user) {
		JSONObject json = new JSONObject();
		json.put("code", 400);
		if(um.alterInfo(user)==1)
			json.replace("code", 202);
		return json;
	}
	@Override
	public BackJSON getCenter(String user_id) {
		BackJSON json = new BackJSON(400, "");
		Map<String, String> tm = um.getCenter(user_id);
		if(!tm.isEmpty()) {
			json.setData(tm);
			json.setCode(200);
		}
		return json;
	}
	@Override
	@Cacheable(value = "contactPhone")
	public JSONObject contactUS() {
		JSONObject json = new JSONObject();
		json.put("code", 400);
		String phone = um.contactUS();
		if(phone!=null) {
			json.replace("code", 200);
			json.put("phone", phone);
		}
		return json;
	}
	@Override
	public JSONObject getTeamNum(String user_id) {
		JSONObject json = new JSONObject();
		json.put("code", 400);
		Integer num = um.getTeamNum(user_id);
		if(num!=null) {
			json.replace("code", 200);
			json.put("team_num", num);
		} else {
			json.replace("code", 200);
			json.put("team_num", 0);
		}
		return json;
	}
	@Override
	public JSONObject truePhone(String user_id, String user_phone) {
		JSONObject json = new JSONObject();
		json.put("code", 400);
		String phone = um.getPhone(user_id);
		if(phone!=null) {
			json.replace("code", 200);
			if(user_phone.equals(phone))
				json.put("result", true);
			else
				json.put("result", false);
		}
		return json;
	}
	@Override
	public JSONObject resetPassword(String user_id, String new_password, int type) {
		JSONObject json = new JSONObject();
		json.put("code", 400);
		if(type==1) {
			if(um.alterLoginpwd(user_id, new_password)==1) {
				json.replace("code", 202);
				json.put("result", true);
			}
		} else if(type==2) {
			if(um.alterPaypwd(user_id, new_password)==1) {
				json.replace("code", 202);
				json.put("result", true);
			}
		}
		return json;
	}

}
