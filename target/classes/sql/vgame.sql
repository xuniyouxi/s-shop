/*
Navicat MySQL Data Transfer

Source Server         : lzy
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : vgame

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-11-28 00:17:13
*/

SET FOREIGN_KEY_CHECKS=0;

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
-- Table structure for t_exchange
-- ----------------------------
DROP TABLE IF EXISTS `t_exchange`;
CREATE TABLE `t_exchange` (
  `exchange_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `goods_id` int(11) DEFAULT NULL COMMENT '兑换货物id',
  `exchange_time` datetime DEFAULT NULL COMMENT '兑换时间',
  PRIMARY KEY (`exchange_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_exchange_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
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
  `user_id` int(11) NOT NULL,
  `identify_code` int(11) DEFAULT NULL,
  `used_static` varchar(255) DEFAULT NULL,
  `used_method` varchar(255) DEFAULT NULL,
  `used_time` datetime DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_identify_code_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
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
  `user_id` int(11) DEFAULT NULL,
  `into_balance` int(11) DEFAULT NULL COMMENT '使用多少可用余额转入能量池',
  `operation_time` datetime DEFAULT NULL,
  PRIMARY KEY (`operation_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_pool_operation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
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
  `user_id` int(11) DEFAULT NULL,
  `member_layer` int(255) DEFAULT NULL COMMENT '队员层数，比如小明属于金字塔的第三层，最上面为第一层',
  `team_sum` int(11) DEFAULT NULL COMMENT '队伍总人数',
  `member_teamVIP` int(255) DEFAULT NULL COMMENT '用户属于几星用户',
  `join_time` datetime DEFAULT NULL COMMENT '用户入团的时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_team
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_log
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_log`;
CREATE TABLE `t_trade_log` (
  `record_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '赠送能量人id',
  `team_id` int(11) NOT NULL COMMENT '用户的团队id 团队id为0代表没有团队',
  `to_user_id` int(11) DEFAULT NULL COMMENT '被赠送能量人id',
  `trade_number` double DEFAULT NULL COMMENT '赠送数量',
  `service_charge` double(255,0) DEFAULT NULL,
  `trade_time` datetime DEFAULT NULL COMMENT '赠送时间',
  PRIMARY KEY (`record_id`),
  KEY `user_id` (`user_id`),
  KEY `to_user_id` (`to_user_id`),
  CONSTRAINT `t_trade_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`),
  CONSTRAINT `t_trade_log_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易表';

-- ----------------------------
-- Records of t_trade_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL,
  `user_realname` varchar(255) DEFAULT NULL COMMENT '用户昵称，系统生成不可更改',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户自设名称可以修改',
  `user_phone` varchar(255) DEFAULT NULL COMMENT '用户电话',
  `user_wxcode` varchar(255) DEFAULT NULL COMMENT '微信号',
  `user_password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `user_pay_password` varchar(255) DEFAULT NULL COMMENT '交易密码',
  `user_equipment_id1` varchar(255) DEFAULT NULL COMMENT '用户手机的ime码',
  `user_equipment_id2` varchar(255) DEFAULT NULL COMMENT '用户手机的ime码',
  `team_id` int(11) DEFAULT NULL COMMENT '用户的团队id 团队id为0代表没有团队，如果他爹属于一个团队，那么它就属于这个团队，并且要在team表中加入他的记录',
  `activation_code` varchar(255) DEFAULT NULL COMMENT '激活码',
  `invite_code` varchar(255) DEFAULT NULL COMMENT '用户自己的邀请码',
  `invited_ancestors` int(255) DEFAULT NULL COMMENT '用户的被邀请的祖宗id',
  `invited_father` int(255) DEFAULT NULL COMMENT '用户的被邀请的爸爸id',
  `user_address` varchar(255) DEFAULT NULL COMMENT '用户的收货地址',
  `user_head_picture` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `invited_sum1` int(255) DEFAULT NULL COMMENT '用户直接推荐的总人数',
  `invited_sum2` int(255) DEFAULT NULL COMMENT '用户间接推荐的总人数，直接在team表查团队，团队总人数减去自己为间接推荐人数',
  `user_balance` double(255,0) DEFAULT NULL COMMENT '用户的余额',
  `pool_usedCapacity` int(255) DEFAULT NULL COMMENT '能量池已经用的能量',
  `pool_rank` int(255) DEFAULT NULL COMMENT '能量池等级',
  `user_vip` int(255) DEFAULT NULL COMMENT '用户星级',
  `create_time` datetime DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每次有新用户在注册的时候，会递归查询数据库，找爹和爹的爹的爹的爹，他们的间接邀请人数统一+1，新用户的父亲的直接推荐人数+1（按照星际等级更新用户vip等级）。\r\n如果，此时有满足星级用户条件，且一直到祖宗位置都没有团队，那么这个人独立成团队，下属所有用户跟团，此人祖宗id置0，下属祖宗id置为他。\r\n如果在他之上原先就有团队，那么不独立成团队。\r\n最初的注册50人的id为最初团队id，不记录奖励\r\n';

-- ----------------------------
-- Records of t_user
-- ----------------------------
