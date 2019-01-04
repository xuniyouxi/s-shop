package com.vg.service.user;

import com.vg.config.Util.BackJSON;
import com.vg.entity.EVO.UserInfo;

public interface UserService {

	public BackJSON getEquipment(String user_id);
	public BackJSON untie(String user_id, int equipment_num);
	public BackJSON getInfo(String user_id);
	public BackJSON alterInfo(UserInfo user);
	public BackJSON getCenter(String user_id);
	public BackJSON contactUS();
	public BackJSON getTeamNum(String user_id);
	public BackJSON truePhone(String user_id, String user_phone);
	public BackJSON resetPassword(String user_id, String new_password, int type);
	public BackJSON updateHeadPic(String user_id, String file);
	public BackJSON newIdentifyCode(String phone);
	public BackJSON checkIdentifyCode(String phone, int code);
	public BackJSON getSlidePicture();
	
}
