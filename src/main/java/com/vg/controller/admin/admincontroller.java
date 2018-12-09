package com.vg.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vg.config.MyAnn.Authorization;
import com.vg.config.Util.BackJSON;
import com.vg.entity.Biscuits;
import com.vg.service.admin.Adminservice;

@RestController()
@RequestMapping("/admin")
public class admincontroller {

	@Autowired
	Adminservice adminservice;

	// 增加某个小功能 http://localhost:8080/vg/admin/Statement
	@PostMapping({ "/Statement" })
	@Authorization(authorization = "admin")
	public BackJSON Statement(@RequestBody Biscuits biscuits) {
		return adminservice.setBiscuits(biscuits);
	}

	// 更新某个小功能 http://localhost:8080/vg/admin/Statement
	@PutMapping({ "/Statement" })
	@Authorization(authorization = "admin")
	public BackJSON updateStatement(@RequestBody Biscuits biscuits) {
		System.out.println(biscuits);
		return adminservice.updatesetBiscuits(biscuits);
	}

	// 删除某个小功能 http://localhost:8080/vg/admin/Statement/2
	@DeleteMapping({ "/Statement/{bis_id}" })
	@Authorization(authorization = "admin")
	public BackJSON deleteStatement(@PathVariable int bis_id) {
		System.out.println("asd");
		return adminservice.deleteStatement(bis_id);
	}

}
