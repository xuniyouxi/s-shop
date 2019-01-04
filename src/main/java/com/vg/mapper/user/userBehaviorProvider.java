package com.vg.mapper.user;

import org.apache.ibatis.jdbc.SQL;

import com.vg.entity.UserTeam;

public class userBehaviorProvider {
	 public String updatauserTeam(UserTeam userteam) {
	        return new SQL() {
	            {
	                UPDATE("t_user_team");
	                if(userteam.getInvited_bonus()!=null) {
	                    SET("invited_bonus=#{invited_bonus}");
	                }
	                if(userteam.getInvited_father()!=null) {
	                    SET("invited_father=#{invited_father}");
	                }
	                if(userteam.getInvited_son()!=null) {
	                    SET("invited_son=#{invited_son}");
	                }
	                if(userteam.getInvited_sum()!=null) {
	                	SET("invited_sum=#{invited_sum}");
	                }
	                if(userteam.getInvited_today_bonus()!=null) {
	                	SET("invited_today_bonus=#{invited_today_bonus}");
	                }
	                if(userteam.getMember_layer()!=null) {
	                	SET("member_layer=#{member_layer}");
	                }
	                if(userteam.getTeam_id()!=null) {
	                	SET("team_id=#{team_id}");
	                }

	                WHERE("user_id=#{user_id}");
	            }
	        }.toString();
	    }

}
