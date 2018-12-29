package com.vg.mapper.user;

import java.sql.Timestamp;
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
	List<GlanceGoods> getGlanceGoods(int r, int e);
	
	@SelectProvider(type = UserMapperProvider.class, method = "getExchangeRecord")
	List<ExchangeRecord> getExchangeRecord(String user_id, int r, int e, int t);
	
	@Select("select user_address, user_balance from t_user_data where user_id = #{user_id}")
	Map<String, Object> getAddressandBalance(String user_id);
	
	@Insert("insert into t_exchange(user_id, goods_id, goods_energyNum, exchange_time) "
			+ "values(#{user_id, jdbcType=VARCHAR}, #{goods_id, jdbcType=INTEGER}, "
			+ "(select goods_energyNum from t_goods where goods_id=#{goods_id}), "
			+ "#{exchange_time, jdbcType=TIMESTAMP})")
	int confirmExchange(@Param("user_id")String user_id, @Param("goods_id")Integer goods_id, @Param("exchange_time")Timestamp exchange_time);
	
	@Update("update t_goods set goods_sum = goods_sum-1 where goods_id=${_parameter}")
	int updateGoodsSum(Integer goods_id);
	
	
}
