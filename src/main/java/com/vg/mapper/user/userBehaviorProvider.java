package com.vg.mapper.user;

import org.apache.ibatis.jdbc.SQL;

import com.vg.entity.UserData;
import com.vg.entity.UserTeam;

public class userBehaviorProvider {
	public String updatauserTeam(UserTeam userteam) {
		return new SQL() {
			{
				UPDATE("t_user_team");
				if (userteam.getInvited_bonus() != null) {
					SET("invited_bonus=#{invited_bonus}");
				}
				if (userteam.getInvited_father() != null) {
					SET("invited_father=#{invited_father}");
				}
				if (userteam.getInvited_son() != null) {
					SET("invited_son=#{invited_son}");
				}
				if (userteam.getInvited_sum() != null) {
					SET("invited_sum=#{invited_sum}");
				}
				if (userteam.getInvited_today_bonus() != null) {
					SET("invited_today_bonus=#{invited_today_bonus}");
				}
				if (userteam.getMember_layer() != null) {
					SET("member_layer=#{member_layer}");
				}
				if (userteam.getTeam_id() != null) {
					SET("team_id=#{team_id}");
				}

				WHERE("user_id=#{user_id}");
			}
		}.toString();
	}

	public String updatauserData(UserData userData) {
		return new SQL() {
			{
				UPDATE("t_user_data");
				if (userData.getUser_realname() != null) {
					SET("user_realname=#{user_realname}");
				}
				if (userData.getUser_name() != null) {
					SET("user_name=#{user_name}");
				}
				if (userData.getUser_wxcode() != null) {
					SET("user_wxcode=#{user_wxcode}");
				}
				if (userData.getUser_pay_password() != null) {
					SET("user_pay_password=#{user_pay_password}");
				}
				if (userData.getAuthorization_code() != null) {
					SET("authorization_code=#{authorization_code}");
				}
				if (userData.getUser_equipment_id1() != null) {
					SET("user_equipment_id1=#{user_equipment_id1}");
				}
				if (userData.getUser_equipment_id2() != null) {
					SET("user_equipment_id2=#{user_equipment_id2}");
				}

				if (userData.getInvite_code() != null) {
					SET("invite_code=#{invite_code}");
				}

				if (userData.getUser_address() != null) {
					SET("user_address=#{user_address}");
				}

				if (userData.getUser_head_picture() != null) {
					SET("user_head_picture=#{user_head_picture}");
				}

				if (userData.getUser_balance() != null) {
					SET("user_balance=#{user_balance}");
				}
				if (userData.getPool_usedCapacity() != null) {
					SET("pool_usedCapacity=#{pool_usedCapacity}");
				}
				if (userData.getPool_rank() != null) {
					SET("pool_rank=#{pool_rank}");
				}
				if (userData.getUser_vip() != null) {
					SET("user_vip=#{user_vip}");
				}

				WHERE("user_id=#{user_id}");
			}
		}.toString();
	}

}
