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
import com.vg.config.Encrypt.MD5;
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
		BackJSON json = new BackJSON(200);
		Map<String, String> tm = um.getEquipment(user_id);
		if(!tm.isEmpty()) {
			json.setData(tm);
//			json.setCode(200);
		}
		return json;
	}
	@Override
	public BackJSON untie(String user_id, int num) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		if(um.untieEquipment(user_id, "user_equipment_id"+num)==1) {
//			json.setCode(200);
			rjson.replace("result", 1);
		}
		json.setData(rjson);
		return json;
	}
	@Override
	public BackJSON getInfo(String user_id) {
		BackJSON json = new BackJSON(200);
		UserInfo user = um.getInfo(user_id);
		if(user!=null) {
			json.setData(user);
//			json.setCode(200);
		}
		return json;
	}
	@Override
	public BackJSON alterInfo(UserInfo user) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		if(um.alterInfo(user)==1) {
//			json.setCode(200);
			rjson.replace("result", 1);
		}
		json.setData(rjson);
		return json;
	}
	@Override
	public BackJSON getCenter(String user_id) {
		BackJSON json = new BackJSON(200);
		Map<String, String> tm = um.getCenter(user_id);
		if(!tm.isEmpty()) {
			json.setData(tm);
//			json.setCode(200);
		}
		return json;
	}
	@Override
	@Cacheable(value = "contactPhone")
	public BackJSON contactUS() {
		BackJSON json = new BackJSON(200);
		String phone = um.contactUS();
		if(phone!=null) {
			JSONObject rjson = new JSONObject();
			rjson.put("phone", phone);
			json.setData(rjson);
//			json.setCode(200);
		}
		return json;
	}
	@Override
	public BackJSON getTeamNum(String user_id) {
		BackJSON json = new BackJSON(200);
		Integer num = um.getTeamNum(user_id);
		JSONObject rjson = new JSONObject();
		if(num!=null) {
//			json.setCode(200);
			rjson.put("team_num", num);
			json.setData(rjson);
		} else {
//			json.setCode(200);
			rjson.put("team_num", 0);
			json.setData(rjson);
		}
		return json;
	}
	@Override
	public BackJSON truePhone(String user_id, String user_phone) {
		BackJSON json = new BackJSON(200);
		String phone = um.getPhone(user_id);
		JSONObject rjson = new JSONObject();
		if(phone!=null) {
//			json.setCode(200);
			if(user_phone.equals(phone))
				rjson.put("result", 1);
			else
				rjson.put("result", 0);
			json.setData(rjson);
		}
		return json;
	}
	@Override
	public BackJSON resetPassword(String user_id, String new_password, int type) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		try {
			new_password = MD5.md5(new_password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(type==1) {
			if(um.alterLoginpwd(user_id, new_password)==1) {
//				json.setCode(200);
				rjson.replace("result", 1);
			}
		} else if(type==2) {
			if(um.alterPaypwd(user_id, new_password)==1) {
//				json.setCode(200);
				rjson.replace("result", 1);
			}
		}
		json.setData(rjson);
		return json;
	}

}
