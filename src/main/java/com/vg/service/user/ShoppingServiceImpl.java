package com.vg.service.user;

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
import com.vg.config.Util.Base64Utils;
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
	public BackJSON glance(int type_r, int type_e, int start) {
		Integer size = Value.getGlancegoodsize();
		BackJSON json = new BackJSON(200);
		List<GlanceGoods> goods = sm.getGlanceGoods(type_r, type_e, (start-1)*size, size);
		JSONObject data = new JSONObject();
		data.put("pageNo", start);
		Integer total_count = sm.getTotalGoods();
		if(total_count==null)
			total_count = 0;
		data.put("totalCount", total_count);
		data.put("totalPage", total_count/size+1);
		if(goods.size()>0) {
			//将商品地址配成完整url
			String domain = Value.getDomain()+"storeImg/";
			for(GlanceGoods good:goods) 
				good.setGoods_img(domain+good.getGoods_img());
			data.put("list", goods);
		}else {
			data.put("list", null);
		}
		json.setData(data);
		return json;
	}
	@Override
	public BackJSON exchangeRecord(String user_id, int type_r, int type_e, int type_t, int start) {
		Integer size = Value.getGlancegoodsize();
		BackJSON json = new BackJSON(200);
		JSONObject data = new JSONObject();
		data.put("pageNo", start);
		List<ExchangeRecord> records = sm.getExchangeRecord(user_id, type_r, type_e, type_t, (start-1)*size, size);
		Integer total_count = sm.getTotalRecord(user_id);
		if(total_count==null)
			total_count = 0;
		data.put("totalCount", total_count);
		data.put("totalPage", total_count/size+1);
		if(records.size()>0) {
			data.put("list", records);
		} else
			data.put("list", null);
		json.setData(data);
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
		Map<String, Object> map = new HashMap<>();
		//订单号，随机字符串-当前毫秒数
		map.put("exchange_id", Base64Utils.randString(2)+"-"+System.currentTimeMillis());
		map.put("user_id", user_id);
		map.put("goods_id", goods_id);
		if(sm.confirmExchange(map)==1) {
//			json.setCode(200);
			if(sm.updateGoodsSum(user_id, goods_id)>0)
				rjson.replace("result", 1);
		}
		json.setData(rjson);
		return json;
	}

}
