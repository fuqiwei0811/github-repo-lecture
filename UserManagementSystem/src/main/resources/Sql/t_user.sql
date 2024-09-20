/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50125
Source Host           : localhost:3306
Source Database       : mytest

Target Server Type    : MYSQL
Target Server Version : 50125
File Encoding         : 65001
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '担当者', '1234567', '13253517972');
INSERT INTO `t_user` VALUES ('2', 'マリオ', '123456', '13253517973');
INSERT INTO `t_user` VALUES ('4', 'ルイジ', '123456', '13253517970');
INSERT INTO `t_user` VALUES ('7', 'ネット名', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('8', 'テスト', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('9', '主任', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('10', '管理者', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('11', 'ユーザー テスト', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('12', 'ネットワーク', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('13', '美工デザイン', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('14', 'フロントエンド開発', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('15', 'バックエンド開発', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('16', 'チャネル開発', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('17', 'テストユーザー', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('18', 'システムユーザー', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('19', 'システム保守', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('20', 'システム設計', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('21', 'ソフトウェア開発', '13253517970', '13253517970');

