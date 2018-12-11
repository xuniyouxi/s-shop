package com.vg.mapper.user;

import org.apache.ibatis.jdbc.SQL;

import com.vg.entity.EVO.UserInfo;

public class UserMapperProvider {
	
	public String alterInfo(UserInfo user) {
		return new SQL() {
			{
				UPDATE("t_user_data");
				if(user.getUser_name()!=null)
					SET("user_name = #{user_name}");
				if(user.getUser_wxcode()!=null)
					SET("user_wxcode = #{user_wxcode}");
				if(user.getUser_address()!=null)
					SET("user_address = #{user_address}");
				WHERE("user_id = #{user_id}");
			}
		}.toString();
	}
	
}
