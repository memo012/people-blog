/*
Navicat MySQL Data Transfer

Source Server         : leiqiang
Source Server Version : 80003
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 80003
File Encoding         : 65001

Date: 2019-08-06 23:00:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL COMMENT '标识符',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `text` longtext NOT NULL COMMENT '正文',
  `labelValues` varchar(255) NOT NULL COMMENT '标签',
  `selectType` varchar(10) NOT NULL COMMENT '文章类型',
  `selectCategories` varchar(25) NOT NULL COMMENT '博客分类',
  `selectGrade` int(7) NOT NULL COMMENT '文章等级',
  `originalAuthor` varchar(10) DEFAULT NULL COMMENT '原文章作者',
  `message` varchar(2) NOT NULL COMMENT '文章（0-公开  1-私密）',
  `createTime` varchar(11) NOT NULL COMMENT '创建时间',
  `likes` int(9) NOT NULL DEFAULT '0' COMMENT '点赞',
  `name` varchar(20) NOT NULL COMMENT '作者名字',
  `articleTabloid` varchar(255) NOT NULL COMMENT '文章摘要',
  `look` int(10) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  PRIMARY KEY (`id`),
  KEY `blog_ibfk_1` (`name`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`name`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of blog
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL COMMENT '评论id',
  `userId` varchar(60) NOT NULL COMMENT '博客发布者id',
  `blogId` bigint(20) NOT NULL COMMENT '博客id',
  `message` varchar(255) NOT NULL COMMENT '内容',
  `createTime` varchar(50) NOT NULL COMMENT '创建时间',
  `likes` int(11) NOT NULL COMMENT '点赞数',
  `isRead` int(1) NOT NULL DEFAULT '1' COMMENT '该条评论是否已读  1--未读   0--已读',
  `authorName` varchar(60) NOT NULL COMMENT '作者',
  PRIMARY KEY (`id`),
  KEY `blogId` (`blogId`),
  KEY `userId` (`userId`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`blogId`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for commentlikes
-- ----------------------------
DROP TABLE IF EXISTS `commentlikes`;
CREATE TABLE `commentlikes` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `blogId` bigint(20) NOT NULL COMMENT '博客id',
  `commentId` bigint(20) NOT NULL COMMENT '评论id',
  `likeName` varchar(60) NOT NULL COMMENT '点赞人',
  `likeTime` varchar(60) NOT NULL COMMENT '点赞时间',
  `isRead` int(1) NOT NULL DEFAULT '1' COMMENT '1 -- 未读 0 -- 已读',
  PRIMARY KEY (`id`),
  KEY `blogId` (`blogId`),
  KEY `commentId` (`commentId`),
  CONSTRAINT `commentlikes_ibfk_1` FOREIGN KEY (`blogId`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `commentlikes_ibfk_2` FOREIGN KEY (`commentId`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of commentlikes
-- ----------------------------

-- ----------------------------
-- Table structure for guest
-- ----------------------------
DROP TABLE IF EXISTS `guest`;
CREATE TABLE `guest` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `userId` varchar(60) NOT NULL COMMENT '留言者id',
  `message` varchar(255) NOT NULL COMMENT '内容',
  `createTime` varchar(60) NOT NULL COMMENT '创建时间',
  `likes` int(10) NOT NULL COMMENT '点赞数',
  `isRead` int(1) NOT NULL DEFAULT '1' COMMENT '1  -- 未读  0 -- 已读',
  `authorName` varchar(60) NOT NULL COMMENT '留言名称',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `guest_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of guest
-- ----------------------------

-- ----------------------------
-- Table structure for guestlikes
-- ----------------------------
DROP TABLE IF EXISTS `guestlikes`;
CREATE TABLE `guestlikes` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `guestId` bigint(20) NOT NULL COMMENT '留言id',
  `likeName` varchar(60) NOT NULL COMMENT '点赞用户',
  PRIMARY KEY (`id`),
  KEY `guest_likes_id` (`guestId`),
  CONSTRAINT `guest_likes_id` FOREIGN KEY (`guestId`) REFERENCES `guest` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of guestlikes
-- ----------------------------

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `id` varchar(60) NOT NULL COMMENT '标识符',
  `labelName` varchar(60) NOT NULL COMMENT '标签姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of label
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `pid` int(5) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) NOT NULL DEFAULT '' COMMENT '权限名(写博客)',
  `url` varchar(255) DEFAULT '',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'editor', '');
INSERT INTO `permission` VALUES ('2', 'manage', '');
INSERT INTO `permission` VALUES ('3', 'comment', '');

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  KEY `idx_rid` (`rid`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission_role
-- ----------------------------
INSERT INTO `permission_role` VALUES ('1', '2');
INSERT INTO `permission_role` VALUES ('2', '3');
INSERT INTO `permission_role` VALUES ('3', '1');

-- ----------------------------
-- Table structure for repguest
-- ----------------------------
DROP TABLE IF EXISTS `repguest`;
CREATE TABLE `repguest` (
  `rid` bigint(20) NOT NULL COMMENT '主键',
  `guestId` bigint(20) NOT NULL COMMENT '留言条id',
  `repMess` varchar(255) NOT NULL COMMENT '正文',
  `rguestId` varchar(60) NOT NULL COMMENT '评论者id',
  `rcreateTime` varchar(60) NOT NULL COMMENT '创建时间',
  `risRead` int(1) NOT NULL DEFAULT '1' COMMENT '1 - 未读  0 - 已读',
  `repName` varchar(60) NOT NULL COMMENT '评论名称',
  `guestName` varchar(60) NOT NULL COMMENT '被评论名称',
  PRIMARY KEY (`rid`),
  KEY `guestId` (`guestId`),
  CONSTRAINT `repguest_ibfk_1` FOREIGN KEY (`guestId`) REFERENCES `guest` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of repguest
-- ----------------------------

-- ----------------------------
-- Table structure for reportcomment
-- ----------------------------
DROP TABLE IF EXISTS `reportcomment`;
CREATE TABLE `reportcomment` (
  `rid` bigint(20) NOT NULL COMMENT '回复评论id',
  `commentId` bigint(20) NOT NULL COMMENT '评论文章id',
  `repMess` varchar(255) NOT NULL COMMENT '内容',
  `reportedId` varchar(60) NOT NULL COMMENT '回复者id',
  `rcreateTime` varchar(60) NOT NULL COMMENT '创建时间',
  `risRead` int(1) NOT NULL DEFAULT '1' COMMENT '1 -- 未读  0  -- 已读',
  `repName` varchar(60) NOT NULL COMMENT '回复者id',
  `comName` varchar(60) NOT NULL COMMENT '被评论者名字',
  PRIMARY KEY (`rid`),
  KEY `commentId` (`commentId`),
  KEY `reportedId` (`reportedId`),
  CONSTRAINT `reportcomment_ibfk_1` FOREIGN KEY (`commentId`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reportcomment_ibfk_2` FOREIGN KEY (`reportedId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of reportcomment
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `rid` int(1) NOT NULL COMMENT '标识符',
  `rname` varchar(10) NOT NULL COMMENT '角色',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'admin');
INSERT INTO `roles` VALUES ('2', 'user');
INSERT INTO `roles` VALUES ('3', 'partner');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(60) NOT NULL COMMENT '标识符',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(60) NOT NULL COMMENT '密码',
  `phone` varchar(18) NOT NULL COMMENT '手机号',
  `sex` int(1) NOT NULL COMMENT '性别',
  `lastTime` varchar(30) NOT NULL COMMENT '最后一次登录时间',
  `roleId` int(2) NOT NULL COMMENT '角色(1-超级管理员 2-普通用户)',
  `realname` varchar(60) DEFAULT NULL COMMENT '真实姓名',
  `qq` varchar(11) NOT NULL COMMENT 'qq',
  `email` varchar(20) NOT NULL COMMENT 'email邮箱',
  `intro` varchar(255) NOT NULL COMMENT '个人简历',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('63e09177-338e-47a3-a8db-f1dd2e19e70e', '强子', 'ae1289da5dea6047a009ac1c417babf1', '15383466854', '0', '2019-08-06', '1', '', '', '', '');
