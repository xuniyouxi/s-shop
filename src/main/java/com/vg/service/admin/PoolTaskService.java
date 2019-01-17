package com.vg.service.admin;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vg.InitDataListener;
import com.vg.entity.UserData;
import com.vg.entity.EVO.getAllUserBalance;
import com.vg.mapper.admin.systemMapper;
import com.vg.mapper.user.UserBehaviorMapper;
import com.vg.service.user.UserBehaviorservice;

@Component
@Transactional
public class PoolTaskService {

	@Autowired
	systemMapper systemMapper;
	@Autowired
	UserBehaviorMapper userbehavhourmapper;

	//每天晚上更新用户能量池的能量
	@Scheduled(cron = "0 22 0 * * ?")
	//@Scheduled(cron = "0 13 1 * * ?")
	public void getCurrentDate() {
		double PoolMultiple = InitDataListener.PoolMultiple;
		double PoolToUser = InitDataListener.PoolToUser;
		double PoolFatherWelfare = InitDataListener.PoolFatherWelfare;
		double PoolGrandWelfare = InitDataListener.PoolGrandWelfare;
		List<getAllUserBalance> AllUserList = systemMapper.getAllUserBalance();
		getAllUserBalance parm = new getAllUserBalance();
		for (getAllUserBalance UserBalance : AllUserList) {
			if (UserBalance.getPool_usedCapacity() == 0)
				continue;
			int pool_sum = Integer.parseInt(systemMapper.getPoolRankSum("pool" + UserBalance.getPool_rank() + "_sum"));
			int res = (int) (pool_sum * PoolToUser);
			if (UserBalance.getPool_usedCapacity() >= res) {
				UserBalance.setPool_usedCapacity(UserBalance.getPool_usedCapacity() - res);
				UserBalance.setUser_balance(UserBalance.getUser_balance() + res * PoolMultiple);
				systemMapper.UpdateUserBalance(UserBalance);
				UserData father = userbehavhourmapper.getUserBalance(UserBalance.getUser_id());
				System.out.println(father);
				// 给他爸安排福利
				if (father != null) {
					systemMapper.UpdataUserFatherBalance(father.getUser_id(),
							father.getUser_balance() + res * PoolFatherWelfare);
					// 给他爷安排福利
					UserData grand = userbehavhourmapper.getUserBalance(father.getUser_id());
					System.out.println(grand);
					if (grand != null) {
						systemMapper.UpdataUserFatherBalance(grand.getUser_id(),
								grand.getUser_balance() + res * PoolGrandWelfare);
					}
				}
			} else {
				// 如果小于稀释范围,扣除所有能量
				UserBalance.setPool_usedCapacity(0);
				UserBalance.setUser_balance(UserBalance.getUser_balance());
				systemMapper.UpdateUserBalance(UserBalance);
			}

		}
	}
}
