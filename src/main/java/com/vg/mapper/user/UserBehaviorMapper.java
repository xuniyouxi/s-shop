package com.vg.mapper.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.vg.entity.IdentifyCode;
import com.vg.entity.PoolOperation;
import com.vg.entity.Team;
import com.vg.entity.TradeLog;
import com.vg.entity.User;
import com.vg.entity.UserData;
import com.vg.entity.UserTeam;
import com.vg.entity.EVO.UserLogin;
import com.vg.entity.EVO.UserRegister;

@Repository
public interface UserBehaviorMapper {

	// 测试查询所有team
	@Select({ "select * from t_team" })
	List<Team> getallteam();

	// 更新t_user_team
	@UpdateProvider(type = userBehaviorProvider.class, method = "updatauserTeam")
	int updataUserTeam(UserTeam userTeam);

	// 更新t_user_data
	@UpdateProvider(type = userBehaviorProvider.class, method = "updatauserData")
	int updatauserData(UserData userData);

	// 查询用户交易记录
	@Select({ "SELECT * from t_trade_log where user_id=#{user_id}" })
	List<Map<String, Object>> getusertradeLog(String user_id);

	// 通过交易记录查询被交易昵称
	@Select({
			"select user_name from t_user_data WHERE user_id=(SELECT user_id from t_user where user_phone=#{user_phone})" })
	String getusernametradeLog(String user_phone);

	// 获取是否有激活码
	@Select({ "select code_id from authorization_code where code_content=#{code_content} and used_state=0" })
	int getauthorization_codeid(String code_content);

	// 更新激活码已使用
	@Update({ "UPDATE `authorization_code` SET `used_state`='1' WHERE code_id=#{code_id}" })
	int updataauthorcode(int code_id);
	// 用户交换能量

	// 通过自己id查自己爹
	@Select({ "select * from t_user_team where user_id=#{user_id}" })
	UserTeam getfatheridformteam(String user_id);

	// 更新用户role
	@Update({ "UPDATE t_user SET user_role=1 WHERE user_id=#{user_id}" })
	public int updateUserRole(String user_id);

	// 插入userteam记录
	@Insert({ "INSERT INTO t_user_team VALUES (#{user_id},#{team_id},#{invited_father},0,0,0,0,#{member_layer})" })
	int creatuserteam(UserTeam userTeam);

	// 获取系统当前邀请码
	@Select({ "select bis_content from t_biscuits where bis_id=2" })
	String getSysInviteCode();

	// 更新系统当前邀请码
	@Update({ "UPDATE t_biscuits SET bis_content=#{arg0} WHERE bis_id=2" })
	int updataSysInviteCode(String code);

	// 验证登录
	@Select({
			"select * from t_user where  user_phone = #{user_phone} and user_password=#{user_password} AND (user_role=1 OR user_role=999)" })
	User getUserByPhoneAndPass(UserLogin userlogin);

	/**
	 * @ 登录部分
	 * 
	 */
	// 登录时更新第二个设备，如果为空
	@Update({ "UPDATE t_user_data SET user_equipment_id2=#{user_equipment_id2} WHERE user_id=#{user_id}" })
	int updatauser_equipment_id2(String user_equipment_id2, String user_id);

	// 登录时更新第一个设备，如果为空
	@Update({ "UPDATE t_user_data SET user_equipment_id1=#{user_equipment_id1} WHERE user_id=#{user_id}" })
	int updatauser_equipment_id1(String user_equipment_id1, String user_id);

	// 更新token_id
	@Update({ "UPDATE t_user SET token_id=#{token_id} WHERE user_id=#{user_id}" })
	int updateTokenId(String token_id, String user_id);

	// 获取免责声明
	@Select({ "select bis_name,bis_content from t_biscuits WHERE bis_state=1 AND bis_id=#{arg0}" })
	Map<String, Object> getStatementByFun(int bis_id);

	// 获取某个用户的权限
	@Select({ "select user_role from t_user  WHERE user_id=#{user_id}" })
	int getUserRoleById(String user_id);

	// 创建用户
	@Insert("INSERT INTO t_user VALUES (#{user_id},#{user_phone},#{user_password},999,'NULL',#{create_time})")
	int createUser(User user);

	// 激活时更新userdata
	@Update({
			"UPDATE t_user_data SET invite_code=#{invite_code}, pool_usedCapacity=200, pool_rank=1 WHERE user_id=#{user_id}" })
	int jihuoUpdata(String user_id, String invite_code);

	// 获取用户通过邀请码查询
	@Select({ "select user_id from t_user_data WHERE invite_code=#{invite_code}" })
	String getUserIdByinviteCode(String invite_code);

	// 获取某个用户通过手机号码查询
	@Select({ "select count(*) from t_user WHERE user_phone=#{user_phone}" })
	Integer getUserIdByPhone(String user_phone);

	// 注册时插入userdata
	@Insert("INSERT INTO t_user_data (user_id, user_realname,user_equipment_id1,invite_code,user_balance,pool_usedCapacity,pool_rank,user_vip,user_equipment_id2) VALUES (#{user_id}, #{user_realname},#{user_equipment_id1},#{invite_code},0,0,0,0,'NULL')")
	int insertUserData(UserRegister userRegister);

	// 通过id获取用户所属团队信息
	@Select({ "select * from t_user_team WHERE user_id=#{user_id}" })
	UserTeam getUserTemaById(String user_id);

	// 通过user_id获取用户的Data
	@Select({ "select * from t_user_data WHERE user_id=#{user_id}" })
	Map<String, Object> getUserData(String user_id);

	// 注册时生成短信码
	@Insert({
			"INSERT INTO t_identify_code (user_phone, identify_code, used_static,used_method,used_time) VALUES (#{user_phone},#{identify_code},0,#{used_method},#{used_time})" })
	int insertCodeByuserphone(IdentifyCode identifyCode);

	// 注册时查询短信验证码是否有效
	@Select({
			"select * from t_identify_code WHERE user_phone =#{user_phone} and identify_code=#{code} and used_time>#{used_time} and used_static= 0" })
	IdentifyCode getShortMessageByPhoneAndCode(String user_phone, int code, Date used_time);

	// 注册时更新短信验证码的使用
	@Update({
			"UPDATE  t_identify_code SET used_static= 1 WHERE user_phone=#{user_phone} AND identify_code=#{identify_code} AND used_time = #{used_time} " })
	int UpdateIdentifyCodeState(IdentifyCode identifyCode);

	// 获取用户资产页信息
	@Select({
			"select a.pool_rank,a.pool_usedCapacity,b.invited_bonus,b.invited_son,b.invited_sum from t_user_data a LEFT JOIN t_user_team b ON a.user_id=b.user_id WHERE a.user_id =#{user_id}" })
	HashMap<String, Object> getUserassetsPage(String user_id);

	// 首页用户数据
	@Select({
			"select a.user_vip,a.user_balance,b.invited_today_bonus from t_user_data a LEFT JOIN t_user_team b ON a.user_id=b.user_id WHERE a. user_id=#{user_id}" })
	HashMap<String, Object> getfirstPageuserData(String user_id);

	/**
	 * 交易部分 18.1.8日
	 */
	// 判断支付密码和转出数量是否超额
	@Select({
			"select * from t_user_data WHERE user_id=#{user_id} AND user_pay_password=#{pay_password} AND user_balance>=#{trade_number}" })
	UserData getuserDataByPayPass(String user_id, String pay_password, Double trade_number);

	// 通过phone获取userData
	@Select({ "select * from t_user_data WHERE user_id=(select user_id from t_user WHERE user_phone=#{user_phone})" })
	UserData getuserDataByPhone(String user_phone);

	// 添加交易记录
	@Insert("INSERT INTO t_trade_log (`user_id`, `team_id`, `touser_phone`, `trade_number`, `service_charge`, `trade_time`) VALUES (#{user_id}, #{team_id}, #{touser_phone}, #{trade_number}, #{service_charge}, #{trade_time})")
	int insertTradeLog(TradeLog tradeLog);

	/**
	 * 转入能量池部分 18.1.9日
	 */
	// 通过userid获取userData
	@Select({ "select * from t_user_data WHERE user_id=(select user_id from t_user WHERE user_id=#{user_id})" })
	UserData getuserDataByUserId(String user_id);

	// 转入能量池记录查询
	@Select("select * from t_pool_operation where user_id = #{user_id}")
	List<PoolOperation> getPoolOperByid(String user_id);

	// 插入能量池记录
	@Insert("INSERT INTO `t_pool_operation` (`user_id`, `into_balance`, `operation_time`) VALUES (#{user_id}, #{into_balance}, #{operation_time})")
	int insertPoolLog(PoolOperation poolOperation);

	/**
	 * 拦截器部分
	 * 
	 * 
	 */
	// 拦截器获取用户部分信息
	@Select("select a.user_role,a.user_id,a.token_id,b.user_equipment_id1,b.user_equipment_id2 from t_user a JOIN t_user_data b ON a.user_id = b.user_id WHERE a.user_id=#{user_id}")
	Map<String, Object> getuserInfoByid(String user_id);

	// 一个账号只能在一台手机使用
	@Select("select user_equipment_id1 from t_user_data WHERE NOT user_id = #{user_id}")
	List<String> getAllIMEI1(String user_id);

	@Select("select user_equipment_id2 from t_user_data WHERE NOT user_id = #{user_id}")
	List<String> getAllIMEI2(String user_id);

	/**
	 * 用户交易时手续费发放部分
	 */
	// 查询用户的爹的余额
	@Select("select user_id,user_vip,user_balance from t_user_data WHERE user_id= (select invited_father from t_user_team where user_id=#{user_id})")
	UserData getUserBalance(String user_id);
	
	//查询自己多少代
	@Select ("select member_layer from t_user_team WHERE user_id=#{user_id}")
	int getMemberLayer(String user_id);
}
