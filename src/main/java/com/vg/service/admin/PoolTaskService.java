package com.vg.service.admin;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vg.entity.EVO.getAllUserBalance;
import com.vg.mapper.admin.systemMapper;

@Component
@Transactional
public class PoolTaskService {

	@Autowired
	systemMapper systemMapper;

	@Scheduled(cron="0 22 0 * * ?")
	public void getCurrentDate() {
		String rate = systemMapper.getOperationContent1(1);
		List<getAllUserBalance> AllUserList = systemMapper.getAllUserBalance();
		for (getAllUserBalance UserBalance : AllUserList) {
			if (UserBalance.getPool_usedCapacity() == 0)
				continue;
			UserBalance.setUser_balance(UserBalance.getUser_balance() + 3);
			UserBalance.setPool_usedCapacity(UserBalance.getPool_usedCapacity() - 1);
			systemMapper.UpdateUserBalance(UserBalance);
			System.out.println(UserBalance);
		}
	}
}
