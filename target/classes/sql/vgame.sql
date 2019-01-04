/*
Navicat MySQL Data Transfer

Source Server         : battlecall
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : vgame

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-01-01 23:05:45
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorization_code
-- ----------------------------
INSERT INTO `authorization_code` VALUES ('1', '1', '1', '2018-12-13 22:36:56');
INSERT INTO `authorization_code` VALUES ('2', '234', '1', '2018-12-13 22:36:56');

-- ----------------------------
-- Table structure for sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation`;
CREATE TABLE `sys_operation` (
  `operation_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `operation_name` varchar(255) DEFAULT NULL,
  `operation_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`operation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_operation
-- ----------------------------
INSERT INTO `sys_operation` VALUES ('1', 'pool_rate', '3.0');
INSERT INTO `sys_operation` VALUES ('2', 'pool0_sum', '200');
INSERT INTO `sys_operation` VALUES ('3', 'pool1_sum', '300');
INSERT INTO `sys_operation` VALUES ('4', 'pool2_sum', '400');
INSERT INTO `sys_operation` VALUES ('5', 'pool3_sum', '500');
INSERT INTO `sys_operation` VALUES ('6', 'pool4_sum', '600');
INSERT INTO `sys_operation` VALUES ('7', 'pool5_sum', '700');

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `admin_id` int(11) NOT NULL,
  `admin_name` varchar(255) DEFAULT NULL,
  `admin_rank` varchar(255) DEFAULT NULL,
  `admin_static` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_biscuits
-- ----------------------------
INSERT INTO `t_biscuits` VALUES ('1', '免责声明', '第一条 本网站所刊载的所有资料及图表仅供参考使用。刊载这些文档并不构成对任何股份的收购、购买、认购、抛售或持有的邀约或意图。参阅本网站上所刊的文档的人士，应被视为已确认得悉上述立场。投资者依据本网站提供的信息、资料及图表进行金融、证券等投资项目所造成的盈亏与本网站无关。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第二条 本网站的用户在参加网站举办的各种活动时，我们将在您的同意及确认下，通过注册表格等形式要求您提供一些个人资料，如：您的姓名、性别、年龄、出生日期、身份证号、家庭住址、教育程度、企业情况、所属行业等。请您绝对放心，我们在未经您同意的情况下，绝对不会将您的任何资料以任何方式泄露给任何第三方。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第三条 当政府司法机关依照法定程序要求本网站披露个人资料时，我们将根据执法单位之要求或为公共安全之目的提供个人资料。在此情况下之任何披露，本网站均得免责。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第四条 由于用户将个人密码告知他人或与他人共享注册账户，由此导致的任何个人资料泄露，本网站不负任何责任。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第五条 任何由于黑客攻击、计算机病毒侵人或发作、因政府管制而造成的暂时性关闭等影响网络正常经营的不可抗力而造成的个人资料泄露、丢失、被盗用或被窜改等，本网站均得免责。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第六条 由于与本网站链接的其他网站所造成之个人资料泄露及由此而导致的任何法律争议和后果，本网站均得免责。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第七条 本网站如因系统维护或升级而需暂停服务时，将事先公告。若因线路及非本企业控制范围外的硬件故障或其他不可抗力而导致暂停服务，于暂停服务期间造成的一切不便与损失，本网站不负任何责任。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第八条 本网站使用者因为违反本声明的规定而触犯中华人民共和国法律的，一切后果自己负责，本网站不承担任何责任。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第九条 凡以任何方式登录本网站或直接、间接使用本网站资料者，视为自愿接受本网站声明的约束。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第十条 本声明未涉及的问题参见国家有关法律法规，当本声明与国家法律法规冲突时，以国家法律法规为准。\\\\r\\\\n\\\\r\\\\n \\\\r\\\\n\\\\r\\\\n第十一条 本网站之声明以及其修改权、更新权及最终解释权均属东方财富网所有。', '1');
INSERT INTO `t_biscuits` VALUES ('2', '个人邀请码', '4035', '1');
INSERT INTO `t_biscuits` VALUES ('3', '联系电话', '15353101818', '1');
INSERT INTO `t_biscuits` VALUES ('4', 'slidepic', '1.jpg', '1');
INSERT INTO `t_biscuits` VALUES ('5', 'slidepic', '2.jpg', '1');
INSERT INTO `t_biscuits` VALUES ('6', 'slidepic', '3.jpg', '1');

-- ----------------------------
-- Table structure for t_exchange
-- ----------------------------
DROP TABLE IF EXISTS `t_exchange`;
CREATE TABLE `t_exchange` (
  `exchange_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
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
  `user_phone` varchar(255) NOT NULL,
  `identify_code` int(11) DEFAULT NULL,
  `used_static` int(255) DEFAULT NULL COMMENT '0 没有被用了 1 被用了',
  `used_method` int(255) DEFAULT NULL COMMENT '1 注册用了 2 修改密码用了',
  `used_time` datetime DEFAULT NULL COMMENT '验证码发送时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信验证码表';

-- ----------------------------
-- Records of t_identify_code
-- ----------------------------
INSERT INTO `t_identify_code` VALUES ('15524835211', '95204', '0', '1', '2018-12-25 13:13:13');
INSERT INTO `t_identify_code` VALUES ('15524835211', '11894', '0', '1', '2018-12-25 13:16:32');
INSERT INTO `t_identify_code` VALUES ('15524835211', '61700', '0', '1', '2018-12-25 13:21:23');
INSERT INTO `t_identify_code` VALUES ('15524835211', '82367', '1', '1', '2018-12-25 13:27:04');
INSERT INTO `t_identify_code` VALUES ('15524835211', '80780', '0', '1', '2018-12-25 13:30:07');
INSERT INTO `t_identify_code` VALUES ('15524835211', '60107', '1', '1', '2018-12-25 13:32:17');
INSERT INTO `t_identify_code` VALUES ('15524835211', '63799', '1', '1', '2018-12-25 13:35:40');
INSERT INTO `t_identify_code` VALUES ('15524835211', '50969', '0', '1', '2018-12-25 16:00:07');
INSERT INTO `t_identify_code` VALUES ('17628663291', '16014', '0', '1', '2018-12-25 16:13:07');
INSERT INTO `t_identify_code` VALUES ('18741069601', '85968', '1', '2', '2019-01-01 22:36:12');

-- ----------------------------
-- Table structure for t_pool_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_pool_operation`;
CREATE TABLE `t_pool_operation` (
  `operation_id` int(11) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
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
  `create_time` datetime DEFAULT NULL COMMENT '团队创建时间',
  KEY `team_id` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team
-- ----------------------------
INSERT INTO `t_team` VALUES ('1', '1', '2018-12-03 18:49:27');
INSERT INTO `t_team` VALUES ('2', '1', null);
INSERT INTO `t_team` VALUES ('3', '1', null);
INSERT INTO `t_team` VALUES ('4', '1', null);
INSERT INTO `t_team` VALUES ('5', '3', null);
INSERT INTO `t_team` VALUES ('6', '288', null);
INSERT INTO `t_team` VALUES ('9', null, null);
INSERT INTO `t_team` VALUES ('8', null, null);
INSERT INTO `t_team` VALUES ('99', null, null);
INSERT INTO `t_team` VALUES ('88', null, null);
INSERT INTO `t_team` VALUES ('77', null, null);
INSERT INTO `t_team` VALUES ('22', null, null);
INSERT INTO `t_team` VALUES ('33', null, null);

-- ----------------------------
-- Table structure for t_trade_log
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_log`;
CREATE TABLE `t_trade_log` (
  `record_id` int(11) NOT NULL COMMENT '流水id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '赠送能量人id',
  `team_id` int(11) NOT NULL COMMENT '用户的团队id 团队id为0代表没有团队',
  `to_user_id` varchar(255) DEFAULT NULL COMMENT '被赠送能量人id',
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
  `user_role` int(255) DEFAULT NULL COMMENT '1普通用户 2管理员 999为失效用户',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_phone` (`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('096914eeaa8d4b5bb4a94cfe0e00153a', '1315236', '386f72fbd88bdd3047b862a26a981808', '1', '2018-12-25 00:14:27');
INSERT INTO `t_user` VALUES ('0ce0ef60bc304804ba6ac0ccc013c9da', '111111111', '386f72fbd88bdd3047b862a26a981808', '999', '2018-12-23 23:37:07');
INSERT INTO `t_user` VALUES ('3b9f8164d4dc4fddb2ec32eaa75b4a6a', '1111223322', '386f72fbd88bdd3047b862a26a981808', '999', '2018-12-24 00:49:47');
INSERT INTO `t_user` VALUES ('3c789aa58d024907b1f40f50051982f8', '13152362488', '386f72fbd88bdd3047b862a26a981808', '999', '2018-12-25 15:33:24');
INSERT INTO `t_user` VALUES ('44fa1d49b76843ec808be230c9b3d0d4', '15353969153', '386f72fbd88bdd3047b862a26a981808', '999', '2018-12-25 14:56:46');
INSERT INTO `t_user` VALUES ('4d8848be5f3046e3b547bfd6fee49c59', '18742527429', '386f72fbd88bdd3047b862a26a981808', '999', '2018-12-25 14:35:29');
INSERT INTO `t_user` VALUES ('649e8385f163472f9dec50520cc0de73', '2', '386f72fbd88bdd3047b862a26a981808', '1', '2018-12-10 18:41:39');
INSERT INTO `t_user` VALUES ('72e326e07b7b43f0b91564318f941c52', '18742527413', '386f72fbd88bdd3047b862a26a981808', '999', '2018-12-25 13:48:26');
INSERT INTO `t_user` VALUES ('7c783e21813f42e9be962efe94ce76be', '13892528159', '386f72fbd88bdd3047b862a26a981808', '999', '2018-12-25 14:31:02');
INSERT INTO `t_user` VALUES ('c3c1319afb5447aaba9f48d7b8634bc4', '1', '386f72fbd88bdd3047b862a26a981808', '1', '2018-12-10 18:41:19');
INSERT INTO `t_user` VALUES ('c4d8813cfc844dd383dbb11a921d8acb', '13892528157', '386f72fbd88bdd3047b862a26a981808', '1', '2018-12-29 10:15:04');
INSERT INTO `t_user` VALUES ('e0d84a1c3d3e41a68c8dba582cd30a15', '144806015151515', '386f72fbd88bdd3047b862a26a981808', '999', '2018-12-25 13:45:46');
INSERT INTO `t_user` VALUES ('eaacd114a91543aea5a9b8cf238ed8b0', '3565028633', '386f72fbd88bdd3047b862a26a981808', '999', '2018-12-12 22:50:09');

-- ----------------------------
-- Table structure for t_user_data
-- ----------------------------
DROP TABLE IF EXISTS `t_user_data`;
CREATE TABLE `t_user_data` (
  `user_id` varchar(255) NOT NULL,
  `user_realname` varchar(255) DEFAULT NULL COMMENT '用户昵称，系统生成不可更改',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户自设昵称可以修改',
  `user_wxcode` varchar(255) DEFAULT NULL COMMENT '微信号',
  `user_pay_password` varchar(255) DEFAULT NULL COMMENT '交易密码',
  `authorization_code` int(10) DEFAULT NULL COMMENT '激活码，对应authorization_code',
  `user_equipment_id1` varchar(255) NOT NULL COMMENT '用户手机的ime码',
  `user_equipment_id2` varchar(255) DEFAULT NULL COMMENT '用户手机的ime码',
  `invite_code` varchar(255) DEFAULT NULL COMMENT '用户自己的邀请码  //如果用户未激活，那么邀请码为父亲的id',
  `user_address` varchar(255) DEFAULT NULL COMMENT '用户的收货地址',
  `user_head_picture` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `user_balance` double(255,0) DEFAULT NULL COMMENT '用户的余额',
  `pool_usedCapacity` int(255) DEFAULT NULL COMMENT '能量池已经被使用的能量',
  `pool_rank` int(255) DEFAULT NULL COMMENT '能量池等级',
  `user_vip` int(255) DEFAULT NULL COMMENT '用户星级',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `t_user_data_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每次有新用户在注册的时候，会递归查询数据库，找爹和爹的爹的爹的爹，他们的间接邀请人数统一+1，新用户的父亲的直接推荐人数+1（按照星际等级更新用户vip等级）。\r\n如果，此时有满足星级用户条件，且一直到祖宗位置都没有团队，那么这个人独立成团队，下属所有用户跟团，此人祖宗id置0，下属祖宗id置为他。\r\n如果在他之上原先就有团队，那么不独立成团队。\r\n最初的注册50人的id为最初团队id，不记录奖励';

-- ----------------------------
-- Records of t_user_data
-- ----------------------------
INSERT INTO `t_user_data` VALUES ('096914eeaa8d4b5bb4a94cfe0e00153a', 'IprAQCJL', null, null, null, '111', 'aaaaaaa1aaaaaaaaa', 'aaaaaa1a1aaaaaaaaa', '000000', '三里屯', null, '4399', '0', '1', '0');
INSERT INTO `t_user_data` VALUES ('0ce0ef60bc304804ba6ac0ccc013c9da', 'kPU3rtAD', null, null, null, null, 'NULL', 'NULL', 'c3c1319afb5447aaba9f48d7b8634bc4', '东软路', null, '4399', '0', '0', '0');
INSERT INTO `t_user_data` VALUES ('3b9f8164d4dc4fddb2ec32eaa75b4a6a', '9ueqLwbG', null, null, null, null, 'NULL', 'NULL', 'c3c1319afb5447aaba9f48d7b8634bc4', '北大街20号', null, '4399', '0', '0', '0');
INSERT INTO `t_user_data` VALUES ('3c789aa58d024907b1f40f50051982f8', 'Pw6eCXsr', null, null, null, null, 'NULL', 'NULL', 'c3c1319afb5447aaba9f48d7b8634bc4', null, null, '4399', '0', '0', '0');
INSERT INTO `t_user_data` VALUES ('44fa1d49b76843ec808be230c9b3d0d4', 'G2UWsECc', null, null, null, null, 'NULL', 'NULL', 'c3c1319afb5447aaba9f48d7b8634bc4', null, null, '4399', '0', '0', '0');
INSERT INTO `t_user_data` VALUES ('4d8848be5f3046e3b547bfd6fee49c59', 'FIzgJ4xs', null, null, null, null, 'NULL', 'NULL', 'c3c1319afb5447aaba9f48d7b8634bc4', null, null, '4399', '0', '0', '0');
INSERT INTO `t_user_data` VALUES ('649e8385f163472f9dec50520cc0de73', '毛泽东', '小毛子', '29959', '649e8385f163472f9dec50520cc0de73', '2515', 'NULL', 'NULL', '1515', '南京板鸭街', 'img/asd', '4399', '151', '1', '2');
INSERT INTO `t_user_data` VALUES ('72e326e07b7b43f0b91564318f941c52', 'idy24Q8Y', null, null, null, null, 'NULL', 'NULL', 'c3c1319afb5447aaba9f48d7b8634bc4', null, null, '4399', '0', '0', '0');
INSERT INTO `t_user_data` VALUES ('7c783e21813f42e9be962efe94ce76be', 'IkacCqP1', null, null, null, null, 'aaaaaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaa222', 'c3c1319afb5447aaba9f48d7b8634bc4', null, null, '4399', '0', '0', '0');
INSERT INTO `t_user_data` VALUES ('c3c1319afb5447aaba9f48d7b8634bc4', '邓小平', '小邓子', '84848', '649e8385f163472f9dec50520cc0de73', '151', 'aaaaaaa1aaaaaaaaa', 'NULL', '5151', '苟佳巴村', 'img/asd/asd', '4399', '18', '2', '2');
INSERT INTO `t_user_data` VALUES ('c4d8813cfc844dd383dbb11a921d8acb', 'M0T6c8FH', null, null, null, null, '879681888888', 'NULL', 'c3c1319afb5447aaba9f48d7b8634bc4', null, null, '0', '0', '0', '0');
INSERT INTO `t_user_data` VALUES ('e0d84a1c3d3e41a68c8dba582cd30a15', 'QtWXUyF9', null, null, null, null, 'NULL', 'NULL', 'c3c1319afb5447aaba9f48d7b8634bc4', null, null, '4399', '0', '0', '0');
INSERT INTO `t_user_data` VALUES ('eaacd114a91543aea5a9b8cf238ed8b0', 'iZdbPf21', null, null, null, null, 'NULL', 'NULL', 'c3c1319afb5447aaba9f48d7b8634bc4', '垃圾村', null, '4399', '0', '0', '0');

-- ----------------------------
-- Table structure for t_user_team
-- ----------------------------
DROP TABLE IF EXISTS `t_user_team`;
CREATE TABLE `t_user_team` (
  `user_id` varchar(255) NOT NULL,
  `team_id` int(11) NOT NULL COMMENT '用户的团队id 团队id为0代表没有团队',
  `invited_father` varchar(255) NOT NULL COMMENT '用户的被邀请的爸爸id',
  `invited_sum` int(255) NOT NULL COMMENT '用户间接推荐的总人数',
  `invited_son` int(255) NOT NULL COMMENT '用户直接推荐总人数',
  `invited_bonus` double(255,0) NOT NULL COMMENT '用户已经获得的推荐奖励',
  `invited_today_bonus` double(255,0) DEFAULT NULL COMMENT '用户获得团队的当日奖励',
  `member_layer` int(255) NOT NULL COMMENT '队员层数，比如小明属于金字塔的第三层，最上面为第一层',
  PRIMARY KEY (`user_id`),
  KEY `team_id` (`team_id`),
  CONSTRAINT `t_user_team_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `t_user_team_ibfk_2` FOREIGN KEY (`team_id`) REFERENCES `t_team` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户团队相关';

-- ----------------------------
-- Records of t_user_team
-- ----------------------------
INSERT INTO `t_user_team` VALUES ('3b9f8164d4dc4fddb2ec32eaa75b4a6a', '1', '1', '0', '1', '2', '1', '9');
INSERT INTO `t_user_team` VALUES ('649e8385f163472f9dec50520cc0de73', '1', 'null', '0', '1', '1', '1', '1');
INSERT INTO `t_user_team` VALUES ('c3c1319afb5447aaba9f48d7b8634bc4', '1', '649e8385f163472f9dec50520cc0de73', '0', '1', '1', '1', '2');
