package com.vg.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.vg.entity.User;
import com.vg.entity.EVO.UserTokenEvo;

@Repository
public interface UserBehaviorMapper {

	// 测试查询所有team
	@Select({ "select * from t_team" })
	List<Map<String, Object>> getallteam();
	
	//验证登录
	@Select({"select * from t_user where user_phone = #{user_phone} and user_password=#{user_password}"})
	User getUserByPhoneAndPass(User user);
	
	//获取免责声明
	@Select({ "select bis_name,bis_content from t_biscuits WHERE bis_state=1 AND bis_id=#{arg0}" })
	Map<String, Object> getStatementByFun(int bis_id);
	
	//获取某个用户的权限
	@Select({"select user_role from t_user  WHERE user_id=#{user_id}"})
	int getUserRoleById(String user_id);
}
