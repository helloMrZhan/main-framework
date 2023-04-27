/*
Navicat Premium Data Transfer

Source Server         : localhost
Source Server Type    : MySQL
Source Server Version : 50725
Source Host           : localhost:3306
Source Schema         : test

Target Server Type    : MySQL
Target Server Version : 50725
File Encoding         : 65001

Date: 02/08/2021 22:39:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
`username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
`password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
`birthday` date NULL DEFAULT NULL COMMENT '生日',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'xxx', 'abc', '2021-07-28');
INSERT INTO `user` VALUES (2, 'jack', '123456', '2021-08-20');
INSERT INTO `user` VALUES (3, 'xxx', 'abc', '2021-07-07');
INSERT INTO `user` VALUES (4, 'zjq', '123456', '2021-07-14');
INSERT INTO `user` VALUES (5, 'zjq', '123456', '2021-06-24');
INSERT INTO `user` VALUES (6, 'ceshi', 'abc', '2021-08-02');
INSERT INTO `user` VALUES (7, 'zhangsan', '666', '2021-08-02');
INSERT INTO `user` VALUES (8, 'xxx', 'abc', '2021-07-28');
INSERT INTO `user` VALUES (9, 'jack', '123456', '2021-08-20');
INSERT INTO `user` VALUES (10, 'xxx', 'abc', '2021-07-07');
INSERT INTO `user` VALUES (11, 'zjq', '123456', '2021-07-14');
INSERT INTO `user` VALUES (12, 'zjq', '123456', '2021-06-24');
INSERT INTO `user` VALUES (13, 'ceshi', 'abc', '2021-08-02');
INSERT INTO `user` VALUES (14, 'zhangsan', '666', '2021-08-02');

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `birthday` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
