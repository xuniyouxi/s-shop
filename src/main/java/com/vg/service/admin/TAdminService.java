package com.vg.service.admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vg.config.Util.BackJSON;
import com.vg.entity.Goods;
import com.vg.entity.EVO.AAdmin;
import com.vg.entity.EVO.AUserInfo;
import com.vg.entity.EVO.SlidePicture;

public interface TAdminService {

	public BackJSON getAllUser(int startPage);
	public BackJSON updateUserInfo(String adminAccount, AUserInfo ui);
	public BackJSON freezeUser(String adminAccount, String user_id);
	public BackJSON getFrozenUser(int startPage);
	public BackJSON unFreezeUser(String adminAccount, String user_id);
	public BackJSON getAllTeams(int startPage);
	public BackJSON getTeamMember(int team_id);
	public BackJSON getMyActivationCode(String adminAccount);
	public BackJSON getNewActivationCode(String adminAccount);
	public BackJSON getMyInviteId(String adminAccount, int startPage);
	public BackJSON getAllInviteId(int startPage);
	public BackJSON getSlidePicture();
	public BackJSON deleteSlidePic(String adminAccount, int pic_id);
	public BackJSON newSlidePic(String adminAccount, MultipartFile file);
	public BackJSON updateSlidePicRank(String adminAccount, List<SlidePicture> sp);
	public BackJSON getStatement(int type);
	public BackJSON updateStatement(String adminAccount, String statement, int type);
	public BackJSON getAllGoods(int startPage);
	public BackJSON updateGoodsInfo(String adminAccount, Goods goods, MultipartFile goodsPicture);
	public BackJSON updateGoodState(String adminAccount, List<Object> goods_id, int type);
	public BackJSON getSoldoutGoods(int startPage);
	public BackJSON newGoods(String adminAccount, Goods goods, MultipartFile goodsPicture);
	public BackJSON getOrderList(int startPage);
	public BackJSON confirmOrder(String adminAccount, String exchange_id, int type);
	public BackJSON clearCache(String adminAccount);
	public BackJSON getAdminList(int startPage);
	public BackJSON updateAdminStatus(String adminAccount, String admin_account, int type);
	public BackJSON newAdmin(String adminAccount, AAdmin a);
	public BackJSON getMyAdminInfo(String adminAccount);
	public BackJSON alterAdminPassword(String adminAccount, String oldPassword, String newPassword);
	public BackJSON login(String account, String password);
	public BackJSON getMySearch(String admin_account, String pageIndex, int type, String keyword);
	public BackJSON getAlterUserInfo(String user_id);
	public BackJSON getAlterGoodsInfo(int goods_id);
	public BackJSON updateWelcomePicture(String admin_account, MultipartFile file);
	public BackJSON getWelcomePicture();
	
}
