package com.vg.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.MyAnn.Authorization;
import com.vg.config.Util.BackJSON;
import com.vg.entity.Goods;
import com.vg.entity.EVO.AAdmin;
import com.vg.entity.EVO.AUserInfo;
import com.vg.entity.EVO.SlidePicture;
import com.vg.service.admin.TAdminService;

/**
 * 管理员功能（部分）
 * @author bc
 * @date 2019年1月16日
 */
@CrossOrigin
@RestController
@RequestMapping("/tAdmin/")
public class AdminTTT {

	@Autowired
	private TAdminService as;
	
	//用户列表
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeAllUser/{startPage}")
	public BackJSON seeAllUser(@PathVariable int startPage) {
		return as.getAllUser(startPage);
	}
	//更新用户信息
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/alterUserInfo")
	public BackJSON alterUserInfo(@PathVariable String adminAccount, @RequestBody AUserInfo ui) {
		return as.updateUserInfo(adminAccount, ui);
	}
	//冻结用户
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/freezeUser/{user_id}")
	public BackJSON freezeUser(@PathVariable String adminAccount, @PathVariable String user_id) {
		return as.freezeUser(adminAccount, user_id);
	}
	//冻结用户列表
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeFrozenUser/{startPage}")
	public BackJSON getFrozenUser(@PathVariable int startPage) {
		return as.getFrozenUser(startPage);
	}
	//解冻用户
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/unFreezeUser/{user_id}")
	public BackJSON unFreezeUser(@PathVariable String adminAccount, @PathVariable String user_id) {
		return as.unFreezeUser(adminAccount, user_id);
	}
	//团队列表
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeAllTeams/{startPage}")
	public BackJSON seeAllTeams(@PathVariable int startPage) {
		return as.getAllTeams(startPage);
	}
	//团队成员
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeTeamMember/{team_id}")
	public BackJSON seeTeamMember(@PathVariable int team_id) {
		return as.getTeamMember(team_id);
	}
	//申请激活码
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeActivationCode")
	public BackJSON seeActivationCode(@PathVariable String adminAccount) {
		return as.getMyActivationCode(adminAccount);
	}
	//获取激活码
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/getActivationCode")
	public BackJSON getActivationCode(@PathVariable String adminAccount) {
		return as.getNewActivationCode(adminAccount);
	}
	//激活码状态
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeMyInviteId/{startPage}")
	public BackJSON seeMyInviteId(@PathVariable String adminAccount, @PathVariable int startPage) {
		return as.getMyInviteId(adminAccount, startPage);
	}
	//所有激活码
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/allInviteId/{startPage}")
	public BackJSON allInviteId(@PathVariable int startPage) {
		return as.getAllInviteId(startPage);
	}
	//查看轮播图
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeSlidePicture")
	public BackJSON seeSlidePicture() {
		return as.getSlidePicture();
	}
	//删除轮播图
	@Authorization(authorization="open")
	@DeleteMapping("{adminAccount}/deleteSlidePicture/{pic_id}")
	public BackJSON deleteSlidePicture(@PathVariable String adminAccount, @PathVariable int pic_id) {
		return as.deleteSlidePic(adminAccount, pic_id);
	}
	//增加轮播图
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/addSlidePicture")
	public BackJSON addSlidePicture(@PathVariable String adminAccount, @RequestParam("file")MultipartFile file) {
		return as.newSlidePic(adminAccount, file);
	}
	//轮播图排序
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/rankSlidePicture")
	public BackJSON rankSlidePicture(@PathVariable String adminAccount, @RequestBody Map<String, List<SlidePicture>> map) {
		return as.updateSlidePicRank(adminAccount, map.get("list"));
	}
	//查看法律声明
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeStatement/{type}")
	public BackJSON seeStatement(@PathVariable int type) {
		return as.getStatement(type);
	}
	//修改法律声明
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/alterStatement/{type}")
	public BackJSON updateStatement(@PathVariable String adminAccount, @PathVariable int type, @RequestBody Map<String, String> map) {
		return as.updateStatement(adminAccount, map.get("statement"), type);
	}
	//商品列表
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeAllGoods/{startPage}")
	public BackJSON seeAllGoods(@PathVariable int startPage) {
		return as.getAllGoods(startPage);
	}
	/*
	 * 更新商品信息
	 * spring 能把goods对象的信息自动封装成一个goods对象
	 */
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/alterGoodsInfo")
	public BackJSON alterGoodsInfo(@PathVariable String adminAccount, Goods goods, @RequestParam(value="file", required=false) MultipartFile file) {
		return as.updateGoodsInfo(adminAccount, goods, file);
	}
	/*
	 * 下架/上架/删除商品
	 * 0-删除
	 * 1-下架
	 * 2-上架
	 */
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/soldoutGood")
	public BackJSON updateGoodState(@PathVariable String adminAccount, @RequestBody JSONObject json) {
		List<Object> goods_id = json.getJSONArray("goods_id");
		int type = json.getIntValue("type");
		return as.updateGoodState(adminAccount, goods_id, type);
	}
	//已下架商品
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/alreadySoldout/{startPage}")
	public BackJSON alreadySoldout(@PathVariable int startPage) {
		return as.getSoldoutGoods(startPage);
	}
	//添加商品
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/newGood")
	public BackJSON newGoods(@PathVariable String adminAccount, Goods goods, @RequestParam("file") MultipartFile file) {
		return as.newGoods(adminAccount, goods, file);
	}
	//订单列表
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/checkOrderList/{startPage}")
	public BackJSON checkOrderList(@PathVariable int startPage) {
		return as.getOrderList(startPage);
	}
	/*
	 * 已完成/已发货/取消订单
	 * 1-已发货
	 * 2-已完成
	 * 3-取消订单
	 */
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/confirmOrder/{exchange_id}/{type}")
	public BackJSON confirmOrder(@PathVariable String adminAccount, @PathVariable String exchange_id, @PathVariable int type) {
		return as.confirmOrder(adminAccount, exchange_id, type);
	}
	//清除浏览器缓存
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/clearCache")
	public BackJSON clearCache(@PathVariable String adminAccount) {
		return as.clearCache(adminAccount);
	}
	//管理员列表
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/adminList/{startPage}")
	public BackJSON adminList(@PathVariable int startPage) {
		return as.getAdminList(startPage);
	}
	/*
	 * 冻结/解冻/删除管理员
	 * admin_account被处理的
	 */
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/dealAdmin/{admin_account}/{type}")
	public BackJSON dealAdmin(@PathVariable String adminAccount, @PathVariable String admin_account, @PathVariable int type) {
		return as.updateAdminStatus(adminAccount, admin_account, type);
	}
	//添加管理员
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/newAdmin")
	public BackJSON newAdmin(@PathVariable String adminAccount, @RequestBody AAdmin a) {
		return as.newAdmin(adminAccount, a);
	}
	//个人中心
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/myInfo")
	public BackJSON myInfo(@PathVariable String adminAccount) {
		return as.getMyAdminInfo(adminAccount);
	}
	//修改密码
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/alterMyPassword")
	public BackJSON alterMyPassword(@PathVariable String adminAccount, String oldPassword, String newPassword) {
		return as.alterAdminPassword(adminAccount, oldPassword, newPassword);
	}
	//管理员登录
	@Authorization(authorization="open")
	@PostMapping("login/{account}/{password}")
	public BackJSON login(@PathVariable String account, @PathVariable String password) {
		return as.login(account, password);
	}
	//搜索集合
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/mySearch")
	public BackJSON getMySearch(
			@PathVariable String adminAccount, 
			@RequestParam(value="pageIndex", required=true) String pageIndex, 
			@RequestParam(value="type", required=false, defaultValue="0") int type, 
			@RequestParam(value="keyword", required=true) String keyword) {
		return as.getMySearch(adminAccount, pageIndex, type, keyword);
	}
	//获取要修改的用户的信息
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeAlterUserInfo/{user_id}")
	public BackJSON seeAlterUserInfo(@PathVariable String user_id) {
		return as.getAlterUserInfo(user_id);
	}
	//获取要修改的商品的信息
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/seeAlterGoodsInfo/{goods_id}")
	public BackJSON seeAlterGoodsInfo(@PathVariable int goods_id) {
		return as.getAlterGoodsInfo(goods_id);
	}
	//修改欢迎页图片
	@Authorization(authorization="open")
	@PostMapping("{adminAccount}/alterWelcomePicture")
	public BackJSON alterWelcomePicture(@PathVariable String adminAccount, MultipartFile file) {
		return as.updateWelcomePicture(adminAccount, file);
	}
	//获取欢迎页图片
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/getWelcomePicture")
	public BackJSON getWelcomePicture() {
		return as.getWelcomePicture();
	}
	//首页
	@Authorization(authorization="open")
	@GetMapping("{adminAccount}/homePage")
	public BackJSON homePage() {
		return as.getHomePageInfo();
	}
	
	
}
