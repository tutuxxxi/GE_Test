/*
 Navicat Premium Data Transfer

 Source Server         : docker实例
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : db_test

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 26/03/2021 19:39:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_person
-- ----------------------------
DROP TABLE IF EXISTS `tb_person`;
CREATE TABLE `tb_person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `birthday` date DEFAULT NULL COMMENT 'yyyy-MM-dd',
  `telephone` varchar(11) DEFAULT NULL,
  `professions` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_person
-- ----------------------------
BEGIN;
INSERT INTO `tb_person` VALUES (16, '张三', '男', '2021-03-26', '13990909099', '职工', '张三最强');
INSERT INTO `tb_person` VALUES (17, '张三四', '女', '2021-03-26', '13990909099', '职工', '张三最强');
INSERT INTO `tb_person` VALUES (18, '李四', '女', '2021-03-26', '13990909099', '职工', '李四最强');
INSERT INTO `tb_person` VALUES (19, '王五', '男', '2021-03-26', '13990909099', '职工', '王五最强');
INSERT INTO `tb_person` VALUES (20, '赵六', '女', '2021-03-26', '13990909099', '职工', '赵六最强');
INSERT INTO `tb_person` VALUES (21, '田七', '男', '2021-03-26', '13990909099', '职工', '田七最强');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
