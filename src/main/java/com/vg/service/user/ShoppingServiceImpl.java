package com.vg.service.user;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Util.BackJSON;
import com.vg.entity.EVO.ExchangeRecord;
import com.vg.entity.EVO.GlanceGoods;
import com.vg.mapper.user.ShoppingMapper;

@Service
public class ShoppingServiceImpl implements ShoppingService{

	@Autowired
	private ShoppingMapper sm;
	
	@Override
	public BackJSON glance(int type_r, int type_e) {
		BackJSON json = new BackJSON(400);
		List<GlanceGoods> goods = sm.getGlanceGoods(type_r, type_e);
		if(goods.size()>0) {
			json.setData(goods);
			json.setCode(200);
		}
		return json;
	}
	@Override
	public BackJSON exchangeRecord(String user_id, int type_r, int type_e, int type_t) {
		BackJSON json = new BackJSON(400);
		List<ExchangeRecord> records = sm.getExchangeRecord(user_id, type_r, type_e, type_t);
		if(records.size()>0) {
			json.setData(records);
			json.setCode(200);
		}
		return json;
	}
	@Override
	public JSONObject exchange(String user_id) {
		JSONObject json = new JSONObject();
		json.put("code", 400);
		Map<String, Object> ab = sm.getAddressandBalance(user_id);
		if(ab!=null) {
			if(ab.get("user_address")!=null)
				json.put("ifAddress", "true");
			else
				json.put("ifAddress", "false");
			json.put("user_balance", ab.get("user_balance"));
			json.replace("code", 200);
		}
		return json;
	}
	@Override
	public JSONObject confrimExchange(String user_id, Integer goods_id) {
		JSONObject json = new JSONObject();
		json.put("code", 400);
		if(sm.confirmExchange(user_id, goods_id, new Timestamp(System.currentTimeMillis()))==1)
			json.replace("code", 201);
		return json;
	}

}