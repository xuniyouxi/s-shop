package com.vg.service.user;

import com.vg.config.Util.BackJSON;

public interface ShoppingService {

	public BackJSON glance(int type_r, int type_e);
	public BackJSON exchangeRecord(String user_id, int type_r, int type_e, int type_t);
	public BackJSON exchange(String user_id);
	public BackJSON confrimExchange(String user_id, Integer goods_id);
	
}
