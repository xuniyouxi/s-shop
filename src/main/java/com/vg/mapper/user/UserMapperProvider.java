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
	
	public String getGlanceGoods(int r, int e) {
		return new SQL() {
			{
				SELECT("goods_id, goods_name, goods_img, goods_rmb, goods_energyNum, goods_state");
				FROM("t_goods");
				WHERE("goods_sum > 0");
				if(r==1)
					ORDER_BY("goods_rmb asc");
				else if(r==2)
					ORDER_BY("goods_rmb desc");
				if(e==1)
					ORDER_BY("goods_energyNum asc");
				else if(e==2)
					ORDER_BY("goods_energyNum desc");
			}
		}.toString();
	}
	
	public String getExchangeRecord(String user_id, int r, int e, int t) {
		return new SQL() {
			{
				SELECT("e.exchange_id, e.goods_energyNum, e.exchange_time, g.goods_name, g.goods_describe, g.goods_rmb");
				FROM("t_goods g, t_exchange e");
				WHERE("e.user_id = #{user_id} and g.goods_id = e.goods_id");
				if(r==1) 
					ORDER_BY("g.goods_rmb asc");
				else if(r==2) 
					ORDER_BY("g.goods_rmb desc");
				if(e==1)
					ORDER_BY("e.goods_energyNum asc");
				else if(e==2) 
					ORDER_BY("e.goods_energyNum desc");
				if(t==1) 
					ORDER_BY("e.exchange_time asc");
				else if(t==2) 
					ORDER_BY("e.exchange_time desc");
			}
		}.toString();
	}
	
	
}
