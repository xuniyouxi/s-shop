package com.vg.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vg.mapper.user.UserBehaviorMapper;


@Service

public class UserBehaviorserviceImpl implements UserBehaviorservice{

	
	@Autowired
	UserBehaviorMapper userbehavhourmapper;
		
	
	
	@Override
	public List<Map<String, Object>> getallteam() {
		// TODO Auto-generated method stub
		return userbehavhourmapper.getallteam();
	}

}
