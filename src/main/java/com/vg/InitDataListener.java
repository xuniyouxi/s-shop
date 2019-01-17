package com.vg;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import com.vg.mapper.admin.systemMapper;

@Service
public class InitDataListener implements InitializingBean, ServletContextAware {
	
	public static double PoolToUser=0;
	public static double PoolMultiple=0;
	public static double PoolFatherWelfare=0;
	public static double PoolGrandWelfare=0;


	
	@Autowired
	systemMapper systemMapper;

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// 初始化参数
		PoolToUser = Double.parseDouble(systemMapper.getOperationContent1(9));
		PoolMultiple = Double.parseDouble(systemMapper.getOperationContent1(10));
		PoolFatherWelfare = Double.parseDouble(systemMapper.getOperationContent1(11));
		PoolGrandWelfare = Double.parseDouble(systemMapper.getOperationContent1(12));

	}
}
