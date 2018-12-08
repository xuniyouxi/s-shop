package com.vg.service.admin;

import com.vg.config.Util.BackJSON;
import com.vg.entity.Biscuits;

public interface Adminservice {

	//增加一个小biscuits功能
	BackJSON setBiscuits(Biscuits biscuits);
	
	//更新一个小biscuits功能
	BackJSON updatesetBiscuits(Biscuits biscuits);

}
