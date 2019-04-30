/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-04-26 16:27:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `roleName` varchar(50) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '角色一');
INSERT INTO `tb_role` VALUES ('2', '角色二');
INSERT INTO `tb_role` VALUES ('3', '角色三');
