package com.vg.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.vg.entity.IdentifyCode;
import com.vg.entity.EVO.UserInfo;

@Repository
public interface UserMapper {

	
	@Select("select user_equipment_id1, user_equipment_id2 from t_user_data where user_id=#{user_id}")
	Map<String, String> getEquipment(String user_id);
	
	//弱方法
	@Update("update t_user_data set ${param2}='NULL' where user_id='${param1}'")
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
	
	@Update("update t_user_data set user_head_picture=#{param2} where user_id=#{param1}")
	int alterHeadImg(String user_id, String user_head_pic);
	
	@Insert("insert into t_identify_code values(#{user_phone}, #{identify_code}, 0, 2, #{used_time})")
	int newIdentifyCode(IdentifyCode identifyCode);
	
	@Update("update t_identify_code set used_static=1 where "
			+ "used_static=0 and used_method=2 and user_phone=#{user_phone} and identify_code=#{identify_code} and used_time>=#{used_time}")
	int ifIdentifyCodeTrue(IdentifyCode identifyCode);
	
	@Select("select bis_content from t_biscuits where bis_name='slidepic' and bis_state=1")
	List<String> getSlidePicture();
	
	@Update("update t_user set user_password = #{param2} where user_phone=#{param1}")
	int alterStartPwd(String phone, String new_password);
	
}
