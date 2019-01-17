package com.vg.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.vg.entity.EVO.getAllUserBalance;

@Repository
public interface systemMapper {

	@Select({ "select operation_content from sys_operation WHERE operation_id=#{arg0}" })
	String getOperationContent1(int operation_id);
	//获取能量池从0-6级的总容量
	@Select({ "SELECT operation_content from sys_operation WHERE operation_name = #{rank}" })
	String getPoolRankSum(String rank);
	//查询所有用户的user_data的可用余额和池子余额
	@Select("select user_id,user_balance,pool_usedCapacity,pool_rank from t_user_data")
	List<getAllUserBalance> getAllUserBalance();
	//更新用户的可用余额和能量池
	@Update("UPDATE `t_user_data` SET `user_balance`=#{user_balance}, `pool_usedCapacity`=#{pool_usedCapacity} WHERE (`user_id`=#{user_id})")
	int UpdateUserBalance (getAllUserBalance getAllUserBalance);
	
	//更新用户他爹的余额
	@Update("UPDATE `t_user_data` SET `user_balance`=#{user_balance} WHERE (`user_id`=#{user_id})")
	int UpdataUserFatherBalance(String user_id,Double user_balance);
}
