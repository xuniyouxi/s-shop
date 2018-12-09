package com.vg.service.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vg.config.Util.BackJSON;
import com.vg.entity.User;
import com.vg.entity.EVO.UserTokenEvo;

public interface UserBehaviorservice {

	BackJSON getallteam();
	//获取用户
	BackJSON login(User user) throws Exception;
	
	//获取免责声明
	BackJSON getStatementByFun(int bis_id);
	
	//获取用户权限
	int getUserRoleById(String user_id);
}
