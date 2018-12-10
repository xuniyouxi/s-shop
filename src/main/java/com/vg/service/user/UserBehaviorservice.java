package com.vg.service.user;


import com.vg.config.Util.BackJSON;
import com.vg.entity.User;
public interface UserBehaviorservice {

	BackJSON getallteam();
	//获取用户
	BackJSON login(User user) throws Exception;
	
	//获取免责声明
	BackJSON getStatementByFun(int bis_id);
	
	//获取用户权限
	int getUserRoleById(String user_id);
	
	//用户设置密码
	BackJSON SetPassword(User user) throws Exception;
}
