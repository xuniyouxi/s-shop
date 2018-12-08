package com.vg.mapper.admin;



import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.vg.entity.Biscuits;

@Repository
public interface AdminMapper {

	//增加t_biscuits的小功能
	@Insert({ "INSERT INTO t_biscuits (bis_name, bis_content, bis_state) VALUES (#{bis_name}, #{bis_content}, 1)" })
	int setBiscuits(Biscuits biscuits);
	//更新t_biscuits的小功能
	@Update({ "UPDATE t_biscuits SET bis_name=#{bis_name},bis_content =#{bis_content},bis_state =#{bis_state} WHERE bis_id = #{bis_id}" })
	int updatesetBiscuits(Biscuits biscuits);
}
