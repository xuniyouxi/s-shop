package com.vg.service.user;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Util.BackJSON;

public interface ShoppingService {

	public BackJSON glance(int type_r, int type_e);
	public BackJSON exchangeRecord(String user_id, int type_r, int type_e, int type_t);
	public JSONObject exchange(String user_id);
	public JSONObject confrimExchange(String user_id, Integer goods_id);
	
}
