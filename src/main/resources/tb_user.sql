/*
Navicat MySQL Data Transfer

Source Server         : 测试内网
Source Server Version : 50721
Source Host           : 192.168.1.231:3306
Source Database       : dcp_test

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-30 17:23:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `email` varchar(20) NOT NULL,
  `birth` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'zhouning', '111111', '2267431887@qq.com', '2018-09-21 16:01:15');
INSERT INTO `tb_user` VALUES ('2', 'cyl', '111111', '111@qq.com', '2018-09-21 16:01:59');
