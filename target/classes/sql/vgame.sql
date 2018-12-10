/*
Navicat MySQL Data Transfer

Source Server         : lzy
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : vgame

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-12-10 16:17:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authorization_code
-- ----------------------------
DROP TABLE IF EXISTS `authorization_code`;
CREATE TABLE `authorization_code` (
  `code_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code_content` varchar(255) NOT NULL COMMENT '授权码内容',
  `apply_admin` varchar(255) NOT NULL COMMENT '签发人',
  `apply_time` datetime NOT NULL COMMENT '签发时间',
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorization_code
-- ----------------------------

-- ----------------------------
-- Table structure for t_biscuits
-- ----------------------------
DROP TABLE IF EXISTS `t_biscuits`;
CREATE TABLE `t_biscuits` (
  `bis_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bis_name` varchar(255) DEFAULT NULL COMMENT '小功能名字：比如免责声明',
  `bis_content` text COMMENT '小功能的作用',
  `bis_state` int(255) DEFAULT NULL COMMENT '使用状态 0 未使用 1正在用',
  PRIMARY KEY (`bis_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_biscuits
-- ----------------------------
INSERT INTO `t_biscuits` VALUES ('1', '免责声明', '第一条 本网站所刊载的所有资料及图表仅供参考使用。刊载这些文档并不构成对任何股份的收购、购买、认购、抛售或持有的邀约或意图。参阅本网站上所刊的文档的人士，应被视为已确认得悉上述立场。投资者依据本网站提供的信息、资料及图表进行金融、证券等投资项目所造成的盈亏与本网站无关。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第二条 本网站的用户在参加网站举办的各种活动时，我们将在您的同意及确认下，通过注册表格等形式要求您提供一些个人资料，如：您的姓名、性别、年龄、出生日期、身份证号、家庭住址、教育程度、企业情况、所属行业等。请您绝对放心，我们在未经您同意的情况下，绝对不会将您的任何资料以任何方式泄露给任何第三方。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第三条 当政府司法机关依照法定程序要求本网站披露个人资料时，我们将根据执法单位之要求或为公共安全之目的提供个人资料。在此情况下之任何披露，本网站均得免责。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第四条 由于用户将个人密码告知他人或与他人共享注册账户，由此导致的任何个人资料泄露，本网站不负任何责任。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第五条 任何由于黑客攻击、计算机病毒侵人或发作、因政府管制而造成的暂时性关闭等影响网络正常经营的不可抗力而造成的个人资料泄露、丢失、被盗用或被窜改等，本网站均得免责。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第六条 由于与本网站链接的其他网站所造成之个人资料泄露及由此而导致的任何法律争议和后果，本网站均得免责。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第七条 本网站如因系统维护或升级而需暂停服务时，将事先公告。若因线路及非本企业控制范围外的硬件故障或其他不可抗力而导致暂停服务，于暂停服务期间造成的一切不便与损失，本网站不负任何责任。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第八条 本网站使用者因为违反本声明的规定而触犯中华人民共和国法律的，一切后果自己负责，本网站不承担任何责任。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第九条 凡以任何方式登录本网站或直接、间接使用本网站资料者，视为自愿接受本网站声明的约束。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第十条 本声明未涉及的问题参见国家有关法律法规，当本声明与国家法律法规冲突时，以国家法律法规为准。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第十一条 本网站之声明以及其修改权、更新权及最终解释权均属东方财富网所有。', '1');

-- ----------------------------
-- Table structure for t_exchange
-- ----------------------------
DROP TABLE IF EXISTS `t_exchange`;
CREATE TABLE `t_exchange` (
  `exchange_id` int(11) NOT NULL,
  `user_id` varchar(11) DEFAULT NULL COMMENT '用户id',
  `goods_id` int(11) DEFAULT NULL COMMENT '兑换货物id',
  `goods_energyNum` double(255,0) DEFAULT NULL COMMENT '价值能量',
  `exchange_time` datetime DEFAULT NULL COMMENT '兑换时间',
  PRIMARY KEY (`exchange_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_exchange
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `goods_id` int(11) NOT NULL,
  `goods_name` varchar(255) DEFAULT NULL COMMENT '货物名称',
  `goods_img` varchar(255) DEFAULT NULL COMMENT '物品图片',
  `goods_describe` varchar(255) DEFAULT NULL COMMENT '物品描述',
  `goods_rmb` double(255,0) DEFAULT NULL COMMENT '价值rmb',
  `goods_energyNum` double(255,0) DEFAULT NULL COMMENT '价值能量',
  `goods_sum` int(255) DEFAULT NULL COMMENT '物品总数',
  `goods_ShelfTime` datetime DEFAULT NULL COMMENT '上架时间',
  `goods_state` int(255) DEFAULT NULL COMMENT '货物的状态0 代表下架，1代表上架',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------

-- ----------------------------
-- Table structure for t_identify_code
-- ----------------------------
DROP TABLE IF EXISTS `t_identify_code`;
CREATE TABLE `t_identify_code` (
  `user_id` varchar(11) NOT NULL,
  `identify_code` int(11) DEFAULT NULL,
  `used_static` varchar(255) DEFAULT NULL,
  `used_method` varchar(255) DEFAULT NULL,
  `used_time` datetime DEFAULT NULL COMMENT 'code发送时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_identify_code
-- ----------------------------

-- ----------------------------
-- Table structure for t_pool_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_pool_operation`;
CREATE TABLE `t_pool_operation` (
  `operation_id` int(11) NOT NULL,
  `user_id` varchar(11) DEFAULT NULL,
  `into_balance` int(11) DEFAULT NULL COMMENT '使用多少可用余额转入能量池',
  `operation_time` datetime DEFAULT NULL COMMENT '操作记录时间',
  PRIMARY KEY (`operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pool_operation
-- ----------------------------

-- ----------------------------
-- Table structure for t_team
-- ----------------------------
DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team` (
  `team_id` int(11) NOT NULL,
  `team_sum` int(11) DEFAULT NULL COMMENT '队伍总人数',
  `create_time` datetime DEFAULT NULL COMMENT '团队创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team
-- ----------------------------
INSERT INTO `t_team` VALUES ('1', '1', '2018-12-03 18:49:27');

-- ----------------------------
-- Table structure for t_trade_log
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_log`;
CREATE TABLE `t_trade_log` (
  `record_id` int(11) NOT NULL COMMENT '流水id',
  `user_id` varchar(11) DEFAULT NULL COMMENT '赠送能量人id',
  `team_id` int(11) NOT NULL COMMENT '用户的团队id 团队id为0代表没有团队',
  `to_user_id` varchar(11) DEFAULT NULL COMMENT '被赠送能量人id',
  `trade_number` double DEFAULT NULL COMMENT '赠送数量',
  `service_charge` double(255,0) DEFAULT NULL,
  `trade_time` datetime DEFAULT NULL COMMENT '赠送时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易表';

-- ----------------------------
-- Records of t_trade_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` varchar(255) NOT NULL,
  `user_phone` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `user_password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_role` int(255) DEFAULT NULL COMMENT '1普通用户 2管理员',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_phone` (`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('3b5c2f23df0f42eda54c213f0da1291b', '15524835211', '386f72fbd88bdd3047b862a26a981808', '2', '2018-12-06 19:05:09');
INSERT INTO `t_user` VALUES ('7e9116e48973453790d8ddb30fc06707', '110', '3f824e181c827b1e5706ad71560c9b7a', '1', '2018-12-09 15:15:11');
INSERT INTO `t_user` VALUES ('ae3390f356c14cf988936e15d2391eb8', '451', '11111', '1', '2018-12-07 08:57:08');

-- ----------------------------
-- Table structure for t_user_data
-- ----------------------------
DROP TABLE IF EXISTS `t_user_data`;
CREATE TABLE `t_user_data` (
  `user_id` varchar(11) NOT NULL,
  `user_realname` varchar(255) DEFAULT NULL COMMENT '用户昵称，系统生成不可更改',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户自设昵称可以修改',
  `user_wxcode` varchar(255) DEFAULT NULL COMMENT '微信号',
  `user_pay_password` varchar(255) DEFAULT NULL COMMENT '交易密码',
  `authorization_code` int(10) DEFAULT NULL COMMENT '激活码，对应authorization_code',
  `user_equipment_id1` varchar(255) DEFAULT NULL COMMENT '用户手机的ime码',
  `user_equipment_id2` varchar(255) DEFAULT NULL COMMENT '用户手机的ime码',
  `invite_code` varchar(255) DEFAULT NULL COMMENT '用户自己的邀请码',
  `user_address` varchar(255) DEFAULT NULL COMMENT '用户的收货地址',
  `user_head_picture` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `user_balance` double(255,0) DEFAULT NULL COMMENT '用户的余额',
  `pool_usedCapacity` int(255) DEFAULT NULL COMMENT '能量池已经用的能量',
  `pool_rank` int(255) DEFAULT NULL COMMENT '能量池等级',
  `user_vip` int(255) DEFAULT NULL COMMENT '用户星级',
  `create_time` datetime DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每次有新用户在注册的时候，会递归查询数据库，找爹和爹的爹的爹的爹，他们的间接邀请人数统一+1，新用户的父亲的直接推荐人数+1（按照星际等级更新用户vip等级）。\r\n如果，此时有满足星级用户条件，且一直到祖宗位置都没有团队，那么这个人独立成团队，下属所有用户跟团，此人祖宗id置0，下属祖宗id置为他。\r\n如果在他之上原先就有团队，那么不独立成团队。\r\n最初的注册50人的id为最初团队id，不记录奖励';

-- ----------------------------
-- Records of t_user_data
-- ----------------------------
INSERT INTO `t_user_data` VALUES ('51511', '毛泽东', '小毛子', '84484848', '848488', null, '82888', '78181', '515151', '湖南省长沙市', 'img/sss/ads', '3515', '48848', '11', '1', '2018-12-06 23:43:36');

-- ----------------------------
-- Table structure for t_user_team
-- ----------------------------
DROP TABLE IF EXISTS `t_user_team`;
CREATE TABLE `t_user_team` (
  `user_id` varchar(11) NOT NULL,
  `team_id` int(11) DEFAULT NULL COMMENT '用户的团队id 团队id为0代表没有团队',
  `invited_father` varchar(255) DEFAULT NULL COMMENT '用户的被邀请的爸爸id',
  `invited_sum` int(255) DEFAULT NULL COMMENT '用户间接推荐的总人数',
  `member_layer` int(255) DEFAULT NULL COMMENT '队员层数，比如小明属于金字塔的第三层，最上面为第一层',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户团队相关';

-- ----------------------------
-- Records of t_user_team
-- ----------------------------
