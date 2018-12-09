package com.vg.mapper.admin;

import org.apache.ibatis.jdbc.SQL;

import com.vg.entity.Biscuits;

public class AdminProvider {
	 public String updatesetBiscuits(Biscuits biscuits) {
	        return new SQL() {
	            {
	                UPDATE("t_biscuits");
	                if(biscuits.getBis_content()!=null) {
	                    SET("bis_name=#{bis_name}");
	                }
	                if(biscuits.getBis_id()!=null) {
	                    SET("bis_content=#{bis_content}");
	                }
	                if(biscuits.getBis_name()!=null) {
	                    SET("bis_state=#{bis_state}");
	                }

	                WHERE("bis_id=#{bis_id}");
	            }
	        }.toString();
	    }

}
