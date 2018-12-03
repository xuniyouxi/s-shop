package com.vg.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface UserBehaviorMapper {
	
	
	// 测试查询所有team
	@Select({ "select * from t_team" })
	List<Map<String, Object>> getallteam();
}
