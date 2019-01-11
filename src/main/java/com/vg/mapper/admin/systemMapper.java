package com.vg.mapper.admin;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface systemMapper {

	@Select({ "select operation_content from sys_operation WHERE operation_id=#{arg0}" })
	String getOperationContent1(int operation_id);
	//获取能量池从0-6级的总容量
	@Select({ "SELECT operation_content from sys_operation WHERE operation_name = #{rank}" })
	String getPoolRankSum(String rank);
	//每天24点更新数据库的能量池稀释

}
