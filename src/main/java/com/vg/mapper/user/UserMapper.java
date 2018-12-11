package com.vg.mapper.user;

import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.vg.entity.EVO.UserInfo;

@Repository
public interface UserMapper {

	
	@Select("select user_equipment_id1, user_equipment_id2 from t_user_data where user_id=#{user_id}")
	Map<String, String> getEquipment(String user_id);
	
	//弱方法
	@Update("update t_user_data set ${param2}= null where user_id='${param1}'")
	int untieEquipment(String user_id, String user_equipment);
	
	@Select("select user_realname, user_name, user_wxcode, user_address, invite_code from t_user_data where user_id=#{user_id}")
	UserInfo getInfo(String user_id);
	
	@UpdateProvider(type = UserMapperProvider.class, method = "alterInfo")
	int alterInfo(UserInfo user);
	
	@Select("select user_name, user_head_picture from t_user_data where user_id=#{user_id}")
	Map<String, String> getCenter(String user_id);
	
	@Select("select bis_content from t_biscuits where bis_id = 3")
	String contactUS();
	
	@Select("select team_sum from t_team where team_id = "
			+ "(select team_id from t_user_team where user_id = #{user_id})")
	Integer getTeamNum(String user_id);
	
	@Select("select user_phone from t_user where user_id=#{user_id}")
	String getPhone(String user_id);

	@Update("update t_user set user_password = #{param2} where user_id=#{param1}")
	int alterLoginpwd(String user_id, String new_password);
	
	@Update("update t_user_data set user_pay_password = #{param2} where user_id=#{param1}")
	int alterPaypwd(String user_id, String new_password);
	
	
}
