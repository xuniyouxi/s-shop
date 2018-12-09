package com.vg.mapper.admin;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.vg.entity.Biscuits;

@Repository
public interface AdminMapper {

	// 增加t_biscuits的小功能
	@Insert({ "INSERT INTO t_biscuits (bis_name, bis_content, bis_state) VALUES (#{bis_name}, #{bis_content}, 1)" })
	int setBiscuits(Biscuits biscuits);

	// 更新t_biscuits的小功能
	@UpdateProvider(type = com.vg.mapper.admin.AdminProvider.class, method = "updatesetBiscuits")
	int updatesetBiscuits(Biscuits biscuits);

	// 删除t_biscuits的小功能
	@Delete({ "DELETE FROM t_biscuits WHERE bis_id = #{bis_id}" })
	int deleteStatement(int bis_id);
}
