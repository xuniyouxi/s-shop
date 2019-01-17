package com.vg.mapper.admin;

import org.apache.ibatis.jdbc.SQL;

import com.vg.entity.Goods;
import com.vg.entity.EVO.AUserInfo;

public class TAdminMapperProvider {

	public String updateUserInfo(AUserInfo ui) {
		return new SQL() {
			{
				UPDATE("t_user u, t_user_data ud, t_user_team ut");
				if(ui.getUser_phone()!=null) {
					SET("u.user_phone=#{user_phone}");
					WHERE("u.user_id=#{user_id}");
				}
				if(ui.getUser_name()!=null||ui.getUser_wxcode()!=null) {
					if(ui.getUser_name()!=null)
						SET("ud.user_name=#{user_name}");
					if(ui.getUser_wxcode()!=null)
						SET("ud.user_wxcode=#{user_wxcode}");
					WHERE("ud.user_id=#{user_id}");
				}
				if(ui.getTeam_id()!=null) {
					SET("ut.team_id=#{team_id}");
					WHERE("ut.user_id=#{user_id}");
				}
			}
		}.toString();
	}
	
	public String updateGoodsInfo(Goods goods) {
		return new SQL() {
			{
				UPDATE("t_goods");
				if(goods.getGoods_name()!=null)
					SET("goods_name = #{goods_name}");
				if(goods.getGoods_img()!=null)
					SET("goods_img = #{goods_img}");
				if(goods.getGoods_describe()!=null)
					SET("goods_describe = #{goods_describe}");
				if(goods.getGoods_rmb()!=null)
					SET("goods_rmb = #{goods_rmb}");
				if(goods.getGoods_energyNum()!=null)
					SET("goods_energyNum = #{goods_energyNum}");
				if(goods.getGoods_sum()!=null)
					SET("goods_sum = #{goods_sum}");
				WHERE("goods_id = #{goods_id}");
			}
		}.toString();
	}
	
}
