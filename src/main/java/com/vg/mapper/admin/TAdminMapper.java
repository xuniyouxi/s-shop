package com.vg.mapper.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import com.vg.entity.AdminDealRecord;
import com.vg.entity.Goods;
import com.vg.entity.EVO.AAdmin;
import com.vg.entity.EVO.AAuthorizationCode;
import com.vg.entity.EVO.AExchange;
import com.vg.entity.EVO.AUserInfo;
import com.vg.entity.EVO.SlidePicture;

@Repository
public interface TAdminMapper {
	
	@Insert("insert into t_admin_deal_record values(#{admin_account}, #{deal_user_id, jdbcType=VARCHAR}, #{deal_content, jdbcType=VARCHAR}, #{deal_time, jdbcType=TIMESTAMP})")
	int recordDeal(AdminDealRecord adr);

	@Select("select u.user_id, u.user_phone, ud.user_realname, ud.user_name, ud.user_wxcode, ud.user_vip, ud.pool_rank, ud.pool_usedCapacity, ud.user_balance "
			+ "from t_user u "
			+ "left join t_user_data ud on u.user_id=ud.user_id "
			+ "where u.user_role=${param3} "
			+ "limit ${param1}, ${param2}")
	@Results({
		@Result(id=true, property="user_id", column="user_id"),
		@Result(property="team_user_name", column="user_id", javaType=String.class, 
				one=@One(select="getUserTeamName", fetchType=FetchType.EAGER))
	})
	List<AUserInfo> getAllUser(int start, int size, int user_role);
	
	@Select("select user_realname from t_user_data where user_id="
			+ "(select user_id from t_user_team where member_layer=1 and team_id="
			+ "(select team_id from t_user_team where user_id=#{user_id}))")
	String getUserTeamName(String user_id);
	
	@Select("select count(1) from t_user where user_role=${_parameter}")
	Integer getUserNum(int user_role);
	
	@UpdateProvider(type=TAdminMapperProvider.class, method="updateUserInfo")
	int updateUserInfo(AUserInfo ui);
	
	@Update("update t_user set user_role=#{param2} where user_id=#{param1}")
	int freezeUser(String user_id, int user_role);
	
	@Select("select t.team_id, u.user_phone, ud.user_realname, ud.user_name, ud.user_wxcode, ud.user_vip, ud.pool_rank, ud.pool_usedCapacity, ud.user_balance "
			+ "from t_team t, t_user_team ut, t_user u, t_user_data ud "
			+ "where (ut.team_id=t.team_id) and (ut.member_layer=1 and u.user_id=ut.user_id and ud.user_id=ut.user_id) "
			+ "limit ${param1}, ${param2}")
	List<AUserInfo> getAllTeams(int start, int size);
	
	@Select("select count(1) from t_team")
	Integer getTeamNum();
	
	@Select("select u.user_phone, ud.user_realname, ud.user_name, ud.user_wxcode, ud.user_vip, ud.pool_rank, ud.pool_usedCapacity, ud.user_balance, ut.member_layer "
			+ "from t_user u, t_user_data ud, t_user_team ut "
			+ "where ut.team_id=${_parameter} and u.user_id=ut.user_id and ud.user_id=ut.user_id "
			+ "order by member_layer asc")
	List<AUserInfo> getTeamMember(int team_id);
	
	@Select("select count(1) from authorization_code where apply_admin=(select admin_name from t_admin where admin_account=#{admin_account})")
	Integer adminCodeCount(String admin_account);
	
	@Insert("insert into authorization_code(code_content, apply_admin, apply_time) values"
			+ "(#{code_content}, (select admin_name from t_admin where admin_account=#{apply_admin}), #{apply_time, jdbcType=TIMESTAMP})")
	@Options(useGeneratedKeys=true, keyProperty="code_id")
	int newActivationCode(AAuthorizationCode ac);
	
	@Select("select code_id, code_content, apply_time from authorization_code where apply_admin=(select admin_name from t_admin where admin_account=#{param3}) "
			+ "limit #{param1}, #{param2}")
	List<AAuthorizationCode> getMyInviteId(int start, int size, String admin_account);
	
	@Select("select * from authorization_code limit #{param1}, #{param2}")
	List<AAuthorizationCode> getAllInviteId(int start, int size);
	
	@Select("select count(1) from authorization_code")
	Integer allInviteIdNum();
	
	@Select("select bis_id as pic_id, bis_content as picturePath, bis_state as pic_rank from t_biscuits where bis_name='slidepic' and bis_state>0 "
			+ "order by pic_rank asc")
	List<SlidePicture> getSlidePicture();
	
	@Delete("delete from t_biscuits where bis_id=${_parameter}")
	int deleteSlidePic(int pic_id);
	
	@Select("select bis_content from t_biscuits where bis_id=${_parameter}")
	String getSlidePicPath(int pic_id);
	
	//插入新图片默认优先级最高
	@Insert("insert into t_biscuits(bis_name, bis_content, bis_state) values('slidepic', #{picturePath}, 1)")
	@Options(useGeneratedKeys=true, keyProperty="pic_id")
	int newSlidePic(SlidePicture sp);
	
	@Update("<script>"
			+ "update t_biscuits"
			+ "<set> <trim prefix='bis_state = case bis_id' suffix='end'>"
			+ "<foreach collection='sps' item='sp' separator=' '>"
			+ "when #{sp.pic_id} then #{sp.pic_rank}"
			+ "</foreach>"
			+ "</trim> </set>"
			+ "where bis_id in"
			+ "<foreach collection='sps' item='sp' open='(' close=')' separator=','>"
			+ "#{sp.pic_id}"
			+ "</foreach>"
			+ "</script>")
	int updateSlidePicRank(@Param("sps")List<SlidePicture> sp);
	
	@Select("select bis_content from t_biscuits where bis_id=${_parameter} and bis_state=1")
	String getStatement(int bis_id);
	
	@Update("update t_biscuits set bis_content=#{statement} where bis_id=1 and bis_state=1")
	int updateStatement(String statement);
	
	@Update("update t_biscuits set bis_content=#{statement} where bis_id=3 and bis_state=1")
	int updateContactPhone(String statement);
	
	@Select("select goods_id, goods_name, concat(#{prefix}, goods_img) as goods_img, goods_describe, goods_rmb, goods_energyNum, goods_sum, goods_ShelfTime from t_goods "
			+ "where goods_state=1 limit #{start}, #{size}")
	List<Goods> getAllGoods(String prefix, int start, int size);
	
	@Select("select count(1) from t_goods where goods_state=${_parameter}")
	Integer allGoodsNum(int goods_state);
	
	@UpdateProvider(type=TAdminMapperProvider.class, method="updateGoodsInfo")
	int updateGoodsInfo(Goods goods);
	
	@Select("select goods_img from t_goods where goods_id=${_parameter}")
	String getGoodsPicImg(int goods_id);
	
	/*
	 * 商品状态
	 * 0-下架
	 * 1-上架正常
	 */
	@Update("<script>"
			+ "update t_goods set goods_state = #{goods_state} where goods_id in "
			+ "<foreach collection='list' item='id' open='(' close=')' separator=','>"
			+ "#{id}"
			+ "</foreach>"
			+ "</script>")
	int updateGoodState(@Param("list")List<Object> goods_id, @Param("goods_state")int goods_state);

	@Delete("<script>"
			+ "delete from t_goods where goods_id in "
			+ "<foreach collection='list' item='id' open='(' close=')' separator=','>"
			+ "#{id}"
			+ "</foreach>"
			+ "</script>")
	int deleteGoods(@Param("list")List<Object> goods_id);
	
	@Select("select goods_id, goods_name, concat(#{param1}, goods_img) as goods_img, goods_rmb, goods_energyNum, goods_sum, goods_ShelfTime from t_goods "
			+ "where goods_state=0 limit #{param2}, #{param3}")
	List<Goods> getSoldoutGoods(String prefix, int start, int size);
	
	@Insert("insert into t_goods(goods_name, goods_img, goods_describe, goods_rmb, goods_energyNum, goods_sum, goods_ShelfTime, goods_state) "
			+ "values(#{goods_name}, #{goods_img}, #{goods_describe}, #{goods_rmb}, #{goods_energyNum}, #{goods_sum}, (select now()), 1)")
	@Options(useGeneratedKeys=true, keyProperty="goods_id")
	int newGoods(Goods goods);
	
	@Select("select e.exchange_id, e.goods_energyNum, e.order_address as user_address, e.exchange_time, e.exchange_status, g.goods_name, ud.user_realname, ud.user_name "
			+ "from t_exchange e "
			+ "left join t_goods g on g.goods_id=e.goods_id "
			+ "left join t_user_data ud on ud.user_id=e.user_id "
			+ "limit ${param1}, ${param2}")
	List<AExchange> getOrderList(int start, int size);
	
	@Select("select count(1) from t_exchange")
	Integer orderListNum();
	
	@Update("update t_exchange set exchange_status=#{exchange_status} where exchange_id=#{exchange_id}")
	int confirmOrder(@Param("exchange_id")String exchange_id, @Param("exchange_status")int type);
	
	@Select("select admin_account, admin_name, admin_rank, admin_status, last_login_time from t_admin limit ${param1}, ${param2}")
	List<AAdmin> getAdminList(int start, int size);
	
	@Select("select count(1) from t_admin")
	Integer adminCount();
	
	@Update("update t_admin set admin_status=#{param2} where admin_account=#{param1}")
	int updateAdminStatus(String admin_account, int admin_status);
	
	@Delete("delete from t_admin where admin_account=#{admin_account}")
	int deleteAdmin(String admin_account);
	
	@Insert("insert into t_admin values(#{admin_account}, #{admin_name}, #{admin_rank}, #{admin_password}, 0, (select now()), '1970-01-01 08:00:00')")
	int newAdmin(AAdmin a);
	
	@Select("select admin_name, admin_rank from t_admin where admin_account=#{admin_account}")
	Map<String, Object> getMyAdminInfo(String admin_account);
	
	@Select("select admin_password from t_admin where admin_account=#{admin_account}")
	String getAdminPassword(String admin_account);
	
	@Update("update t_admin set admin_password=#{admin_password} where admin_account=#{admin_account}")
	int alterAdminPassword(String admin_account, String admin_password);
	
	@Select("select admin_account, admin_name, admin_rank, admin_password, admin_status, last_login_time from t_admin where admin_account=#{admin_account}")
	AAdmin login(String admin_account);
	
	@Update("update t_admin set last_login_time=(select now()) where admin_account=#{admin_account}")
	void updateLoginTime(String admin_account);
	
	
}
