package com.vg.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.vg.entity.EVO.ExchangeRecord;
import com.vg.entity.EVO.GlanceGoods;

@Repository
public interface ShoppingMapper {

	@SelectProvider(type = UserMapperProvider.class, method = "getGlanceGoods")
	List<GlanceGoods> getGlanceGoods(int r, int e, int start, int size);
	
	@SelectProvider(type = UserMapperProvider.class, method = "getExchangeRecord")
	List<ExchangeRecord> getExchangeRecord(String user_id, int r, int e, int t, int start, int size);
	
	@Select("select user_address, user_balance from t_user_data where user_id = #{user_id}")
	Map<String, Object> getAddressandBalance(String user_id);
	
	@Insert("insert into t_exchange(exchange_id, user_id, goods_id, goods_energyNum, exchange_status, exchange_time, order_address) "
			+ "values(#{exchange_id, jdbcType=VARCHAR}, #{user_id, jdbcType=VARCHAR}, #{goods_id, jdbcType=INTEGER}, "
			+ "(select goods_energyNum from t_goods where goods_id=#{goods_id}), 0, (select now()), "
			+ "(select user_address from t_user_data where user_id=#{user_id}))")
	int confirmExchange(Map<String, Object> map);
	
	@Update("update t_goods g, t_user_data ud set g.goods_sum = g.goods_sum-1, ud.user_balance = ud.user_balance-g.goods_energyNum "
			+ "where g.goods_id=#{goods_id} and ud.user_id=#{user_id}")
	int updateGoodsSum(@Param("user_id")String user_id, @Param("goods_id")Integer goods_id);
	
	@Select("select count(1) from t_goods")
	Integer getTotalGoods();
	
	@Select("select count(1) from t_exchange where user_id = #{user_id}")
	Integer getTotalRecord(String user_id);
	
}
