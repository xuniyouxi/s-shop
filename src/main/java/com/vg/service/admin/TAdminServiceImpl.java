package com.vg.service.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vg.config.Encrypt.MD5;
import com.vg.config.Util.BackJSON;
import com.vg.config.Util.Base64Utils;
import com.vg.config.Util.Value;
import com.vg.entity.AdminDealRecord;
import com.vg.entity.Goods;
import com.vg.entity.EVO.AAdmin;
import com.vg.entity.EVO.AAuthorizationCode;
import com.vg.entity.EVO.AExchange;
import com.vg.entity.EVO.AHomePage;
import com.vg.entity.EVO.AUserInfo;
import com.vg.entity.EVO.SlidePicture;
import com.vg.mapper.admin.TAdminMapper;

@Service
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
@CacheConfig()
public class TAdminServiceImpl implements TAdminService {

	@Autowired
	private TAdminMapper am;
	
	@Override
	@Transactional(readOnly=true)
	public BackJSON getAllUser(int startPage) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		int size = Value.getAseesize();
		map.put("pageSize", size);
		map.put("pageNum", startPage);
		Integer totalNum = am.getUserNum(1); 
		map.put("totalNum", totalNum);
		map.put("pageTotal", totalNum/size+1);
		List<AUserInfo> ui = am.getAllUser((startPage-1)*size, size, 1);
		if(ui.size()>0)
			map.put("list", ui);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	@Transactional
	@Override
	public BackJSON updateUserInfo(String adminAccount, AUserInfo ui) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		if(am.updateUserInfo(ui)>0&&am.recordDeal(new AdminDealRecord(adminAccount, ui.getUser_id(), "alter user info", new Timestamp(System.currentTimeMillis())))==1)
			map.put("result", 1);
		else
			map.put("result", 0);
		json.setData(map);
		return json;
	}

	@Transactional
	@Override
	public BackJSON freezeUser(String adminAccount, String user_id) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		if(am.freezeUser(user_id, 777)==1&&am.recordDeal(new AdminDealRecord(adminAccount, user_id, "freeze user", new Timestamp(System.currentTimeMillis())))==1)
			data = "{\"result\":1}";
		json.setData(JSONObject.parse(data));
		return json;
	}

	@Transactional(readOnly=true)
	@Override
	public BackJSON getFrozenUser(int startPage) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		int size = Value.getAseesize();
		map.put("pageSize", size);
		map.put("pageNum", startPage);
		Integer totalNum = am.getUserNum(777); 
		map.put("totalNum", totalNum);
		map.put("pageTotal", totalNum/size+1);
		List<AUserInfo> ui = am.getAllUser((startPage-1)*size, size, 777);
		if(ui.size()>0)
			map.put("list", ui);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	@Transactional
	@Override
	public BackJSON unFreezeUser(String adminAccount, String user_id) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		if(am.freezeUser(user_id, 1)==1&&am.recordDeal(new AdminDealRecord(adminAccount, user_id, "unfreeze user", new Timestamp(System.currentTimeMillis())))==1)
			data = "{\"result\":1}";
		json.setData(JSON.parse(data));
		return json;
	}
	
	@Transactional(readOnly=true)
	@Override
	public BackJSON getAllTeams(int startPage) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		int size = Value.getAseesize();
		map.put("pageSize", size);
		map.put("pageNum", startPage);
		Integer totalNum = am.getTeamNum(); 
		map.put("totalNum", totalNum);
		map.put("pageTotal", totalNum/size+1);
		List<AUserInfo> ui = am.getAllTeams((startPage-1)*size, size);
		if(ui.size()>0)
			map.put("list", ui);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	@Override
	public BackJSON getTeamMember(int team_id) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		List<AUserInfo> ui = am.getTeamMember(team_id);
		if(ui.size()>0)
			map.put("list", ui);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	@Override
	public BackJSON getMyActivationCode(String adminAccount) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		Integer codeNum = am.adminCodeCount(adminAccount);
		if(codeNum<Value.getAllowactivationcodesize())
			map.put("ifCanGet", 1);
		else
			map.put("ifCanGet", 0);
		map.put("allCodeNum", codeNum);
		json.setData(map);
		return json;
	}

	@Transactional
	@Override
	public BackJSON getNewActivationCode(String adminAccount) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		String code_content = Value.randStrNum(2, 4);
		AAuthorizationCode ac = new AAuthorizationCode(code_content, adminAccount, new Timestamp(System.currentTimeMillis()));
		map.put("result", 0);
		for(int i=0; i<5; i++) {
			if(am.newActivationCode(ac)==1&&am.recordDeal(new AdminDealRecord(adminAccount, "newActivationCode", new Timestamp(System.currentTimeMillis())))==1) {
				map.replace("result", 1);
				map.put("code_content", code_content);
				map.put("code_id", ac.getCode_id());
				break;
			}
			code_content = Value.randStrNum(2, 4);
			ac.setCode_content(code_content);
		}
		json.setData(map);
		return json;
	}

	@Transactional(readOnly=true)
	@Override
	public BackJSON getMyInviteId(String adminAccount, int startPage) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		int size = Value.getAseesize();
		map.put("pageSize", size);
		map.put("pageNum", startPage);
		Integer totalNum = am.adminCodeCount(adminAccount); 
		map.put("totalNum", totalNum);
		map.put("pageTotal", totalNum/size+1);
		List<AAuthorizationCode> ac = am.getMyInviteId((startPage-1)*size, size, adminAccount);
		if(ac.size()>0)
			map.put("list", ac);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	@Transactional(readOnly=true)
	@Override
	public BackJSON getAllInviteId(int startPage) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		int size = Value.getAseesize();
		map.put("pageSize", size);
		map.put("pageNum", startPage);
		Integer totalNum = am.allInviteIdNum(); 
		map.put("totalNum", totalNum);
		map.put("pageTotal", totalNum/size+1);
		List<AAuthorizationCode> ac = am.getAllInviteId((startPage-1)*size, size);
		if(ac.size()>0)
			map.put("list", ac);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	@Override
	public BackJSON getSlidePicture() {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		List<SlidePicture> sps = am.getSlidePicture();
		String domain = Value.getDomain()+"slidePicture"+File.separator;
		if(sps.size()>0) {
			for(SlidePicture sp:sps)
				sp.setPicturePath(domain+sp.getPicturePath());
			map.put("list", sps);
		}else
			map.put("list", null);
		json.setData(sps);
		return json;
	}

	@Transactional
	@Override
	public BackJSON deleteSlidePic(String adminAccount, int pic_id) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
//		String path = Value.getImgpath()+"user"+File.separator+"slidePicture"+File.separator+am.getSlidePicPath(pic_id);
		String path = Value.getSlidePicPath()+File.separator+am.getSlidePicPath(pic_id);
		File file = new File(path);
		if(!file.delete())
			System.out.println("delete file wrong\npath:"+path);
		if(am.deleteSlidePic(pic_id)==1&&am.recordDeal(new AdminDealRecord(adminAccount, "deleteSlidePic", new Timestamp(System.currentTimeMillis())))==1)
			data = "{\"result\":1}";
		json.setData(JSON.parse(data));
		return json;
	}
	
	@Transactional
	@Override
	public BackJSON newSlidePic(String adminAccount, MultipartFile pictureFile) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		//相对路径
//		String path = "vgameResource"+File.separator+"user"+File.separator+"slidePicture";
		String path = Value.getSlidePicPath();
		//Windows上下面这样直接就可以了
//		String path = Value.getImgpath()+"user"+File.separator+"slidePicture";
		File file = new File(path);
		if(!file.exists())
			file.mkdirs();
		//原文件名称前加上5个随机字符
		String picPath = Base64Utils.randString(5)+pictureFile.getOriginalFilename();
		try {
			//改成绝对路径
			pictureFile.transferTo(new File(file.getAbsolutePath()+File.separator+picPath));
			SlidePicture sp = new SlidePicture(picPath);
			if(am.newSlidePic(sp)==1&&am.recordDeal(new AdminDealRecord(adminAccount, "new slidepic", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1,\"pic_id\":"+sp.getPic_id()+"}";
		} catch (IllegalStateException | IOException e) {
			json.setData(JSONObject.parse(data));
			e.printStackTrace();
			return json;
		}
		json.setData(JSONObject.parse(data));
		return json;
	}

	@Transactional
	@Override
	public BackJSON updateSlidePicRank(String adminAccount, List<SlidePicture> sp) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		if(am.updateSlidePicRank(sp)>0&&am.recordDeal(new AdminDealRecord(adminAccount, "rank slidepic", new Timestamp(System.currentTimeMillis())))==1)
			data = "{\"result\":1}";
		json.setData(JSON.parseObject(data));
		return json;
	}

	/*
	 * type=1:联系电话
	 * type=2:免责声明
	 * @see com.vg.service.admin.TAdminService#getStatement(int)
	 */
	@Cacheable(value="statementValue", key="#type+'statement'")
	@Override
	public BackJSON getStatement(int type) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		if(type==1)
			map.put("statement", am.getStatement(3));
		else if(type==2)
			map.put("statement", am.getStatement(1));
		json.setData(map);
		return json;
	}

	@Transactional
	@CacheEvict(value="statementValue", key="#type+'statement'")
	@Override
	public BackJSON updateStatement(String adminAccount, String statement, int type) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		if(type==1) {
			if(am.updateContactPhone(statement)==1&&am.recordDeal(new AdminDealRecord(adminAccount, "alter contactphone", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
		} else if(type==2) {
			if(am.updateStatement(statement)==1&&am.recordDeal(new AdminDealRecord(adminAccount, "alter statement", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
		}
		json.setData(JSONObject.parse(data));
		return json;
	}

	@Transactional(readOnly=true)
	@Override
	public BackJSON getAllGoods(int startPage) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		int size = Value.getAseesize();
		map.put("pageSize", size);
		map.put("pageNum", startPage);
		Integer totalNum = am.allGoodsNum(1);
		map.put("totalNum", totalNum);
		map.put("totalPage", totalNum/size+1);
		List<Goods> goods = am.getAllGoods(Value.getDomain()+"storeImg/", (startPage-1)*size, size);
		if(goods.size()>0)
			map.put("list", goods);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	@Transactional
	@Override
	public BackJSON updateGoodsInfo(String adminAccount, Goods goods, MultipartFile goodsPicture) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		int goods_id = goods.getGoods_id();
		if(goodsPicture!=null) {
			//winfdow下可以
//			String path = Value.getImgpath()+"admin"+File.separator+"storeImg"+File.separator;
			String path = Value.getStoreImgPath();
			File file = new File(path+File.separator+am.getGoodsPicImg(goods_id));
			if(!file.delete())
				System.out.println("delete file wrong\npath:"+path+File.separator+am.getGoodsPicImg(goods_id));
			String goods_img = Base64Utils.randString(5)+goodsPicture.getOriginalFilename();
			//绝对路径
			file = new File(new File(path).getAbsolutePath()+File.separator+goods_img);
			try {
				goodsPicture.transferTo(file);
				goods.setGoods_img(goods_img);
				if(am.updateGoodsInfo(goods)==1&&am.recordDeal(new AdminDealRecord(adminAccount, goods_id+"", "alter goodsinfo", new Timestamp(System.currentTimeMillis())))==1)
					data = "{\"result\":1}";
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				json.setData(JSON.parse(data));
				return json;
			}
		}else {
			if(am.updateGoodsInfo(goods)==1&&am.recordDeal(new AdminDealRecord(adminAccount, goods_id+"", "alter goodsinfo", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
		}
		json.setData(JSON.parse(data));
		return json;
	}

	@Transactional
	@Override
	public BackJSON updateGoodState(String adminAccount, List<Object> goods_id, int type) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		switch(type) {
		case 0:
			//删除图片
			//winfdow下可以
//			String path = Value.getImgpath()+"admin"+File.separator+"storeImg"+File.separator;
			String path = Value.getStoreImgPath()+File.separator;
			File file = null;
			for(Object i:goods_id) {
				file = new File(path+am.getGoodsPicImg((Integer)i));
				if(!file.delete())
					System.out.println("delete file wrong\npath:"+path+am.getGoodsPicImg((Integer)i));
			}
			if(am.deleteGoods(goods_id)>0&&am.recordDeal(new AdminDealRecord(adminAccount, String.valueOf(goods_id), "delete goods", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
			break;
		case 1: 
			if(am.updateGoodState(goods_id, 0)>0&&am.recordDeal(new AdminDealRecord(adminAccount, String.valueOf(goods_id), "down goods", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
			break;
		case 2:
			if(am.updateGoodState(goods_id, 1)>0&&am.recordDeal(new AdminDealRecord(adminAccount, String.valueOf(goods_id), "up goods", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
			break;
		default: break;
		}
		json.setData(JSON.parse(data));
		return json;
	}

	@Transactional(readOnly=true)
	@Override
	public BackJSON getSoldoutGoods(int startPage) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		int size = Value.getAseesize();
		map.put("pageSize", size);
		map.put("pageNum", startPage);
		Integer totalNum = am.allGoodsNum(0);
		map.put("totalNum", totalNum);
		map.put("totalPage", totalNum/size+1);
		List<Goods> goods = am.getSoldoutGoods(Value.getDomain()+"storeImg/", (startPage-1)*size, size);
		if(goods.size()>0)
			map.put("list", goods);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	@Transactional
	@Override
	public BackJSON newGoods(String adminAccount, Goods goods, MultipartFile goodsPicture) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		//Windows下可以
//		String path = Value.getImgpath()+"admin"+File.separator+"storeImg";
		String path = Value.getStoreImgPath();
		File file = new File(path);
		if(!file.exists())
			file.mkdirs();
		String goods_img = Base64Utils.randString(5)+goodsPicture.getOriginalFilename();
//		file = new File(path+File.separator+goods_img);
		try {
			goodsPicture.transferTo(new File(file.getAbsolutePath()+File.separator+goods_img));
			goods.setGoods_img(goods_img);
			if(am.newGoods(goods)==1) {
				int goods_id = goods.getGoods_id();
				if(am.recordDeal(new AdminDealRecord(adminAccount, goods_id+"", "new goods", new Timestamp(System.currentTimeMillis())))==1)
					data = "{\"result\":1,\"goods_id\":"+goods_id+"}";
			}				
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			json.setData(JSON.parse(data));
			return json;
		}
		json.setData(JSON.parse(data));
		return json;
	}

	@Transactional(readOnly=true)
	@Override
	public BackJSON getOrderList(int startPage) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		int size = Value.getAseesize();
		map.put("pageSize", size);
		map.put("pageNum", startPage);
		Integer totalNum = am.orderListNum();
		map.put("totalNum", totalNum);
		map.put("totalPage", totalNum/size+1);
		List<AExchange> exchange = am.getOrderList((startPage-1)*size, size);
		if(exchange.size()>0)
			map.put("list", exchange);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	@Transactional
	@Override
	public BackJSON confirmOrder(String adminAccount, String exchange_id, int type) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		if(type==1||type==2) {
			if(am.confirmOrder(exchange_id, type)==1&&am.recordDeal(new AdminDealRecord(adminAccount, exchange_id, "confirm order-"+type, new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
		} else if(type==3) {
			//删除订单，恢复状态
			if(am.confirmOrderCancel(exchange_id)>0&&am.recordDeal(new AdminDealRecord(adminAccount, exchange_id, "confirm order-"+type, new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
		}
		json.setData(JSON.parse(data));
		return json;
	}

	@Override
	public BackJSON clearCache(String adminAccount) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		if(am.recordDeal(new AdminDealRecord(adminAccount, "clearCache", new Timestamp(System.currentTimeMillis())))==1)
			data = "{\"result\":1}";
		json.setData(JSON.parse(data));
		return json;
	}

	@Transactional(readOnly=true)
	@Override
	public BackJSON getAdminList(int startPage) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		int size = Value.getAseesize();
		map.put("pageSize", size);
		map.put("pageNum", startPage);
		Integer totalNum = am.adminCount();
		map.put("totalNum", totalNum);
		map.put("totalPage", totalNum/size+1);
		List<AAdmin> aa = am.getAdminList((startPage-1)*size, size);
		if(aa.size()>0)
			map.put("list", aa);
		else
			map.put("list", null);
		json.setData(map);
		return json;
	}

	/*
	 * type
	 * 1-冻结
	 * 2-解冻
	 * 3-删除
	 */
	@Transactional
	@Override
	public BackJSON updateAdminStatus(String adminAccount, String admin_account, int type) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		switch(type) {
		case 1:
			if(am.updateAdminStatus(admin_account, 1)==1&&am.recordDeal(new AdminDealRecord(adminAccount, admin_account, "freeze amdin", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
			break;
		case 2:
			if(am.updateAdminStatus(admin_account, 0)==1&&am.recordDeal(new AdminDealRecord(adminAccount, admin_account, "unfreeze amdin", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
			break;
		case 3:
			if(am.deleteAdmin(admin_account)==1&&am.recordDeal(new AdminDealRecord(adminAccount, admin_account, "delete amdin", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
		default :break;
		}
		json.setData(JSONObject.parse(data));
		return json;
	}

	@Transactional
	@Override
	public BackJSON newAdmin(String adminAccount, AAdmin a) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		map.put("result", 0);
		try {
			//账户是随机生成的字符串，aaa1111
			String admin_account = Value.randStrNum(3, 4);
			a.setAdmin_password(MD5.md5(a.getAdmin_password()));
			int i = 0;
			do {
				a.setAdmin_account(admin_account);
				if(am.newAdmin(a)==1) 
					break;
				admin_account = Value.randStrNum(3, 4);
			} while(i++<5);
			if(i<=5&&am.recordDeal(new AdminDealRecord(adminAccount, admin_account, "new amdin", new Timestamp(System.currentTimeMillis())))==1) {
				map.replace("result", 1);
				map.put("admin_account", admin_account);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setData(map);
			return json;
		}
		json.setData(map);
		return json;
	}

	@Override
	public BackJSON getMyAdminInfo(String adminAccount) {
		BackJSON json = new BackJSON(200);
		String data = "{\"info\":null}";
		Map<String, Object> map1 = am.getMyAdminInfo(adminAccount);
		if(map1!=null)
			data = "{\"info\":"+JSON.toJSONString(map1)+"}";
		json.setData(JSON.parse(data));
		return json;
	}

	@Transactional
	@Override
	public BackJSON alterAdminPassword(String adminAccount, String oldPassword, String newPassword) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		try {
			//判断原密码正确性
			if(MD5.verify(oldPassword, am.getAdminPassword(adminAccount))) {
				if(am.alterAdminPassword(adminAccount, MD5.md5(newPassword))==1)  //不记录日志
					data = "{\"result\":1}";
			}else
				data = "{\"result\":2}";
		} catch (Exception e) {
			e.printStackTrace();
			json.setData(JSON.parse(data));
			return json;
		}
		json.setData(JSON.parse(data));
		return json;
	}

	@Transactional
	@Override
	public BackJSON login(String account, String password) {
		BackJSON json = new BackJSON(200);
		JSONObject data = new JSONObject();
		data.put("result", 0);
		AAdmin aa = am.login(account);
		if(aa!=null) {
			try {
				if(aa.getAdmin_password().equalsIgnoreCase(MD5.md5(password))) {
					//登录成功
					data.replace("result", 1);
					am.updateLoginTime(account);
					aa.setAdmin_password("");
					data.put("admin", aa);
				}else {
					data.replace("result", 2);
					data.put("msg", "密码不正确");
				}
			} catch (Exception e) {
				data.put("msg", "服务器错误");
				e.printStackTrace();
				json.setData(data);
				return json;
			}
		}else {
			data.replace("result", 2);
			data.put("msg", "账户不存在");
		}
		json.setData(data);
		return json;
	}

	@Transactional(readOnly=true)
	@Override
	public BackJSON getMySearch(String admin_account, String pageIndex, int type, String keyword) {
		BackJSON json = new BackJSON(200);
		JSONObject data = new JSONObject();
		data.put("result", null);
		switch(pageIndex) {
		case "A":
			//用户列表
			data.replace("result", am.searchAllUser(1, type, keyword));
			break;
		case "B":
			//冻结用户列表
			data.replace("result", am.searchAllUser(777, type, keyword));
			break;
		case "C":
			//团队列表
			data.replace("result", am.searchAllTeams(type, keyword));
			break;
		case "D":
			//激活码状态
			data.replace("result", am.searchMyInviteId(admin_account, keyword));
			break;
		case "E":
			//所有激活码
			data.replace("result", am.searchAllInviteId(type, keyword));
			break;
		case "F":
			//商品列表
			data.replace("result", am.searchAllGoods(Value.getDomain()+"storeImg/", keyword));
			break;
		case "G":
			//已下架商品
			data.replace("result", am.searchSoldoutGoods(Value.getDomain()+"storeImg/", keyword));
			break;
		case "H":
			//订单列表
			data.replace("result", am.searchOrderList(type, keyword));
			break;
		case "I":
			//管理员列表
			data.replace("result", am.searchAdminList(keyword));
			break;
		default :
			break;
		}
		json.setData(data);
		return json;
	}

	@Override
	public BackJSON getAlterUserInfo(String user_id) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		map.put("info", am.getAlterUserInfo(user_id));
		json.setData(map);
		return json;
	}

	@Override
	public BackJSON getAlterGoodsInfo(int goods_id) {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<>();
		map.put("info", am.getAlterGoodsInfo(Value.getDomain()+"storeImg/", goods_id));
		json.setData(map);
		return json;
	}

	@Transactional
	@CacheEvict(value="welcomePic", key="'picture'")
	@Override
	public BackJSON updateWelcomePicture(String admin_account, MultipartFile picture) {
		BackJSON json = new BackJSON(200);
		String data = "{\"result\":0}";
		String path = "vgameResource"+File.separator+"user"+File.separator+"welcomePicture";
		File file = new File(path);
		if(!file.exists())
			file.mkdirs();
		String[] piclist = file.list();
		if(piclist.length>0) {
			//删除文件夹下所有欢迎图片
			File file1 = null;
			for(String picname : piclist) {
				file1 = new File(path, picname);
				if(!file1.delete())
					System.out.println("wrong!!!  fail to delete welcome picture "+picname);
			}
		}
		try {
			String pictureName = picture.getOriginalFilename();
			picture.transferTo(new File(file.getAbsolutePath()+File.separator+pictureName));
			if(am.updateWelcomePicture(pictureName)==1&&am.recordDeal(new AdminDealRecord(admin_account, pictureName, "new welcome picture", new Timestamp(System.currentTimeMillis())))==1)
				data = "{\"result\":1}";
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			json.setData(JSON.parse(data));
			return json;
		}
		
		json.setData(JSON.parse(data));
		return json;
	}
	
	@Override
	@Cacheable(value="welcomePic", key="'picture'")
	public BackJSON getWelcomePicture() {
		BackJSON json = new BackJSON(200);
		String data = "{\"picture\":null}";
		String pic = am.getWeclomePicture();
		if(pic!=null)
			data = "{\"picture\":\""+Value.getDomain()+"welcomePicture/"+pic+"\"}";
		json.setData(JSONObject.parse(data));
		return json;
	}

	@Override
	public BackJSON getHomePageInfo() {
		BackJSON json = new BackJSON(200);
		JSONObject data = new JSONObject();
		AHomePage info = am.getHomePageInfo();
		//计算VRB总数
		info.setTotalVRB(info.getBalanceVRB()+info.getPoolVRB());
		info.setDealVRB(am.getDealVRB());
		info.setIntoPool(am.getIntoPool());
		info.setUserLogin(am.getUserLogin());
		info.setUserActivate(am.getUserActivate());
		data.put("info", info);
		json.setData(data);
		return json;
	}
	
	
}
