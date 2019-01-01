package com.vg.service.user;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Util.BackJSON;
import com.vg.config.Util.Value;
import com.vg.entity.EVO.ExchangeRecord;
import com.vg.entity.EVO.GlanceGoods;
import com.vg.mapper.user.ShoppingMapper;

@Service
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
public class ShoppingServiceImpl implements ShoppingService{

	@Autowired
	private ShoppingMapper sm;
	
	@Override
	@Transactional(readOnly=true)
	public BackJSON glance(int type_r, int type_e) {
		BackJSON json = new BackJSON(200);
		List<GlanceGoods> goods = sm.getGlanceGoods(type_r, type_e);
		if(goods.size()>0) {
			//将商品地址配成完整url
			String domain = Value.getDomain()+"storeImg/";
			for(GlanceGoods good:goods) 
				good.setGoods_img(domain+good.getGoods_img());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", goods);
			json.setData(map);
//			json.setCode(200);
		}
		return json;
	}
	@Override
	public BackJSON exchangeRecord(String user_id, int type_r, int type_e, int type_t) {
		BackJSON json = new BackJSON(200);
		List<ExchangeRecord> records = sm.getExchangeRecord(user_id, type_r, type_e, type_t);
		if(records.size()>0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", records);
			json.setData(map);
//			json.setCode(200);
		}
		return json;
	}
	@Override
	public BackJSON exchange(String user_id) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		Map<String, Object> ab = sm.getAddressandBalance(user_id);
		if(ab!=null) {
			if(ab.get("user_address")!=null)
				rjson.put("ifAddress", 1);
			else
				rjson.put("ifAddress", 0);
			rjson.put("user_balance", ab.get("user_balance"));
//			json.setCode(200);
			json.setData(rjson);
		}
		return json;
	}
	@Override
	@Transactional
	public BackJSON confrimExchange(String user_id, Integer goods_id) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		if(sm.confirmExchange(user_id, goods_id, new Timestamp(System.currentTimeMillis()))==1) {
//			json.setCode(200);
			if(sm.updateGoodsSum(user_id, goods_id)>0)
				rjson.replace("result", 1);
		}
		json.setData(rjson);
		return json;
	}

}
