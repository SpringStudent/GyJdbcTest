/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-04-26 16:27:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `realName` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '真实名称',
  `pwd` varchar(32) CHARACTER SET utf8 NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 NOT NULL,
  `mobile` varchar(11) CHARACTER SET utf8 NOT NULL COMMENT '手机号码',
  `age` int(11) NOT NULL COMMENT '年龄',
  `birth` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '生日',
  `roleId` int(11) NOT NULL COMMENT '角色Id',
  `career` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '职业',
  `isActive` tinyint(4) NOT NULL COMMENT '是否激活0.未激活,1.已激活',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
