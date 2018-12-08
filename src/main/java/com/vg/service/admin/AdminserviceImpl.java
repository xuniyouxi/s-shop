package com.vg.service.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vg.config.Util.BackJSON;
import com.vg.entity.Biscuits;
import com.vg.mapper.admin.AdminMapper;

@Service
public class AdminserviceImpl implements Adminservice {

	@Autowired
	AdminMapper adminMapper;

	@Override
	public BackJSON setBiscuits(Biscuits biscuits) {
		BackJSON backJSON = new BackJSON();
		if (adminMapper.setBiscuits(biscuits) > 0) {
			backJSON.setCode(200);
			backJSON.setData("添加成功");
		} else {
			backJSON.setCode(400);
			backJSON.setData("添加失败");
		}
		return backJSON;
	}

	@Override
	public BackJSON updatesetBiscuits(Biscuits biscuits) {
		BackJSON backJSON = new BackJSON();
		if (adminMapper.updatesetBiscuits(biscuits) > 0) {
			backJSON.setCode(200);
			backJSON.setData("更新成功");
		} else {
			backJSON.setCode(400);
			backJSON.setData("更新失败");
		}
		return backJSON;
	}
}
