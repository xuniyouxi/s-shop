package com.vg.service.user;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Util.BackJSON;
import com.vg.entity.EVO.UserInfo;

public interface UserService {

	public BackJSON getEquipment(String user_id);
	public JSONObject untie(String user_id, int equipment_num);
	public BackJSON getInfo(String user_id);
	public JSONObject alterInfo(UserInfo user);
	public BackJSON getCenter(String user_id);
	public JSONObject contactUS();
	public JSONObject getTeamNum(String user_id);
	public JSONObject truePhone(String user_id, String user_phone);
	public JSONObject resetPassword(String user_id, String new_password, int type);
	
}
