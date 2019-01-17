package com.vg.service.user;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.vg.config.Encrypt.MD5;
import com.vg.config.Util.BackJSON;
import com.vg.config.Util.Base64Utils;
import com.vg.config.Util.CheckPhoneNub;
import com.vg.config.Util.SmsSample;
import com.vg.config.Util.Value;
import com.vg.entity.IdentifyCode;
import com.vg.entity.EVO.UserInfo;
import com.vg.mapper.user.UserMapper;

@Service
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
@CacheConfig()
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper um;
	
	@Override
	public BackJSON getEquipment(String user_id) {
		BackJSON json = new BackJSON(200);
		Map<String, String> tm = um.getEquipment(user_id);
		if(!tm.isEmpty()) {
			json.setData(tm);
//			json.setCode(200);
		}
		return json;
	}
	@Override
	public BackJSON untie(String user_id, int num) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		if(um.untieEquipment(user_id, "user_equipment_id"+num)==1) {
//			json.setCode(200);
			rjson.replace("result", 1);
		}
		json.setData(rjson);
		return json;
	}
	@Override
	public BackJSON getInfo(String user_id) {
		BackJSON json = new BackJSON(200);
		UserInfo user = um.getInfo(user_id);
		if(user!=null) {
			json.setData(user);
//			json.setCode(200);
		}
		return json;
	}
	@Override
	public BackJSON alterInfo(UserInfo user) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		if(um.alterInfo(user)==1) {
//			json.setCode(200);
			rjson.replace("result", 1);
		}
		json.setData(rjson);
		return json;
	}
	@Override
	public BackJSON getCenter(String user_id) {
		BackJSON json = new BackJSON(200);
		Map<String, String> tm = um.getCenter(user_id);
		if(!(tm==null)) {
			String user_head_picture = tm.get("user_head_picture");
			if(user_head_picture==null) {
				user_head_picture = Value.getDomain()+"userImg/defaultImg/default.jpg";
			} else 
				user_head_picture = Value.getDomain()+"userImg/"+user_id+"/headImg/"+user_head_picture;
			tm.replace("user_head_picture", user_head_picture);
			json.setData(tm);
//			json.setCode(200);
		}else {
			tm = new HashMap<String, String>();
			tm.put("user_head_picture", Value.getDomain()+"userImg/defaultImg/default.jpg");
			json.setData(tm);
		}
		return json;
	}
	@Override
	@Cacheable(value="statementValue", key="#type+'statement'")
	public BackJSON contactUS(int type) {
		BackJSON json = new BackJSON(200);
		String phone = um.contactUS();
		if(phone!=null) {
			JSONObject rjson = new JSONObject();
			rjson.put("phone", phone);
			json.setData(rjson);
//			json.setCode(200);
		}
		return json;
	}
	@Override
	public BackJSON getTeamNum(String user_id) {
		BackJSON json = new BackJSON(200);
		Integer num = um.getTeamNum(user_id);
		JSONObject rjson = new JSONObject();
		if(num!=null) {
//			json.setCode(200);
			rjson.put("team_num", num);
			json.setData(rjson);
		} else {
//			json.setCode(200);
			rjson.put("team_num", 0);
			json.setData(rjson);
		}
		return json;
	}
	@Override
	public BackJSON truePhone(String user_id, String user_phone) {
		BackJSON json = new BackJSON(200);
		String phone = um.getPhone(user_id);
		JSONObject rjson = new JSONObject();
		if(phone!=null) {
//			json.setCode(200);
			if(user_phone.equals(phone))
				rjson.put("result", 1);
			else
				rjson.put("result", 0);
			json.setData(rjson);
		}
		return json;
	}
	@Override
	public BackJSON resetPassword(String user_id, String new_password, int type) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		try {
			new_password = MD5.md5(new_password);
		} catch (Exception e) {
			json.setData(rjson);
			e.printStackTrace();
			return json;
		}
		if(type==1) {
			if(um.alterLoginpwd(user_id, new_password)==1) {
//				json.setCode(200);
				rjson.replace("result", 1);
			}
		} else if(type==2) {
			if(um.alterPaypwd(user_id, new_password)==1) {
//				json.setCode(200);
				rjson.replace("result", 1);
			}
		}
		json.setData(rjson);
		return json;
	}
	@Override
	@Transactional
	public BackJSON updateHeadPic(String user_id, String fileStr) {
		BackJSON json = new BackJSON(200);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("result", 0);
		//前边有个file:，它就在当前文件夹下新建了file：...
//		String path = Value.getImgpath()+"user"+File.separator+user_id+File.separator+"headImg";
		String path = "vgameResource"+File.separator+"user"+File.separator+user_id+File.separator+"headImg";
		File file = new File(path);
		if(!file.exists())
			file.mkdirs();
		String[] piclist = file.list();
		for(String picname : piclist) {
			file = new File(path, picname);
			if(!file.delete())
				System.out.println("wrong!!!  fail to delete "+user_id+"'s head_pic.");
		}
		String picName = Base64Utils.randString(3)+".jpg";
		boolean fileResult = Base64Utils.base64ToImage(fileStr, path+File.separator+picName);
		if(fileResult&&um.alterHeadImg(user_id, picName)==1)
			map.replace("result", 1);
		//下面的方法并没有成功
		/*byte[] b = Base64Utils.decodeFromString(fileStr);
		OutputStream os = null;
		try {
			os = new FileOutputStream(path+File.separator+picName);
			os.write(b);
			os.flush();
			if(um.alterHeadImg(user_id, picName)==1)
				map.replace("result", 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		json.setData(map);
		return json;
	}
	@Override
	@Transactional
	public BackJSON newIdentifyCode(String phone) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		if (!CheckPhoneNub.isMobiPhoneNum(phone)) {
			rjson.put("result", 2);
			json.setData(rjson);
			return json;
		}
		int code = (int) ((Math.random() * 9 + 1) * 10000);
		IdentifyCode identifyCode = new IdentifyCode(phone, code, new Date(System.currentTimeMillis()));
		String res = SmsSample.SendMessage(phone, code);
		if(um.newIdentifyCode(identifyCode)==1 && res.equals("0")) {
			rjson.replace("result", 1);
		}
		json.setData(rjson);
		return json;
	}
	@Override
	public BackJSON checkIdentifyCode(String phone, int code) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		long start = System.currentTimeMillis()-300000;
		IdentifyCode identifyCode = new IdentifyCode(phone, code, new Date(start));
		if(um.ifIdentifyCodeTrue(identifyCode)==1)
			rjson.replace("result", 1);
		json.setData(rjson);
		return json;
	}
	@Override
	public BackJSON getSlidePicture() {
		BackJSON json = new BackJSON(200);
		Map<String, Object> map = new HashMap<String, Object>(); 
		List<String> pics = um.getSlidePicture();
		String domain = Value.getDomain()+"slidePicture/";
		for(int i=0; i<pics.size(); i++) {
			pics.set(i, domain+pics.get(i));
		}
		map.put("piclist", pics);
		json.setData(map);
		return json;
	}
	@Override
	public BackJSON resetStartPassword(String phone, String new_password) {
		BackJSON json = new BackJSON(200);
		JSONObject rjson = new JSONObject();
		rjson.put("result", 0);
		String password = null;
		try {
			password = MD5.md5(new_password);
		} catch (Exception e) {
			json.setData(rjson);
			e.printStackTrace();
			return json;
		}
		if(um.alterStartPwd(phone, password)==1)
			rjson.replace("result", 1);
		json.setData(rjson);
		return json;
	}

}
