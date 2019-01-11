package com.vg.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vg.config.MyAnn.Authorization;
import com.vg.config.Util.BackJSON;
import com.vg.service.user.ShoppingService;

/**
 * 用户商城部分
 * @author bc
 * @date 2018年12月13日
 */

@RestController
@RequestMapping("/shopping/")
public class ShoppingConroller {

	@Autowired
	private ShoppingService ss;
	
	@Authorization(authorization="user")
	@GetMapping("glance/{type_r}/{type_e}/{start_page}")
	public BackJSON glance(@PathVariable("type_r")int type_r, @PathVariable("type_e")int type_e, @PathVariable("start_page")int start_page) {
		return ss.glance(type_r, type_e, start_page);
	}
	@Authorization(authorization="user")
	@GetMapping("recordExchange/{user_id}/{type_r}/{type_e}/{type_t}/{start_page}")
	public BackJSON exchangeRecord(@PathVariable("user_id")String user_id, @PathVariable("type_r")int type_r, 
			@PathVariable("type_e")int type_e, @PathVariable("type_t")int type_t, @PathVariable("start_page")int start_page) {
		return ss.exchangeRecord(user_id, type_r, type_e, type_t, start_page);
	}
	@Authorization(authorization="user")
	@PostMapping("exchange/{user_id}")
	public BackJSON exchange(@PathVariable(value="user_id")String user_id) {
		return ss.exchange(user_id);
	}
	@Authorization(authorization="user")
	@PostMapping("confirmExchange/{user_id}/{goods_id}")
	public BackJSON confrimExchange(@PathVariable("user_id")String user_id, @PathVariable("goods_id")Integer goods_id) {
		return ss.confrimExchange(user_id, goods_id);
	}
	
}
