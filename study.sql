/*
 Navicat Premium Data Transfer

 Source Server         : warmMysql
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : study

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 14/05/2022 21:12:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adjudicator
-- ----------------------------
DROP TABLE IF EXISTS `adjudicator`;
CREATE TABLE `adjudicator`  (
  `adjudicator_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '评审员号',
  `adjudicator_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456' COMMENT '评审员密码',
  `adjudicator_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评审员姓名',
  PRIMARY KEY (`adjudicator_id`) USING BTREE,
  INDEX `adju_name_index`(`adjudicator_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of adjudicator
-- ----------------------------
INSERT INTO `adjudicator` VALUES (1, '123', '王安');
INSERT INTO `adjudicator` VALUES (2, '123', '李冲');
INSERT INTO `adjudicator` VALUES (3, '123', '林佳佳');
INSERT INTO `adjudicator` VALUES (4, '123', '王大强');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '管理员号',
  `admin_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `admin_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员姓名',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '123', '杨大哥');
INSERT INTO `admin` VALUES (2, '123', '小钱');
INSERT INTO `admin` VALUES (3, '123', '小吴');

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `student_id` int(20) NOT NULL COMMENT '学生号',
  `project_id` int(20) NOT NULL COMMENT '项目号',
  `teacher_id` int(20) NOT NULL COMMENT '教师号',
  `student_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生名',
  `project_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名',
  `teacher_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师名',
  `project_content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申报内容',
  `project_progress` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '申报进度',
  `project_teach` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未指导' COMMENT '指导内容',
  `pass_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未通过' COMMENT '通过状态',
  `project_total_score` int(20) NULL DEFAULT 0 COMMENT '评审总分',
  `has_scored_adju_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '已经打分的评审员数',
  `adju_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '评审员数',
  PRIMARY KEY (`student_id`, `project_id`, `teacher_id`) USING BTREE,
  INDEX `teacher_name`(`teacher_name`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE,
  INDEX `student_name`(`student_name`) USING BTREE,
  INDEX `project_name`(`project_name`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES (1, 1, 1, '李云鹏', '零食盒子', '高育良', '我特别喜欢吃零食，对老师这个项目特别感兴趣，希望老师能同意！', '已指导，已评分，并通过', '可以，希望你能认真对待此次申报，好好弄出项目成果！', '通过', 150, '2', '0');
INSERT INTO `apply` VALUES (2, 13, 4, '宋爱梅', '奶茶店', '高小琴', '很尴尬，从小就喜欢喝奶茶，真的没办法！', '已评分', '未指导', '未通过', 86, '1', '0');
INSERT INTO `apply` VALUES (3, 10, 3, '赵四', '情怀惊喜小礼品', '祁同伟', '生活需要仪式感，一切礼物都是指得到，震慑人心的！', '已指导，', '搞得跟真的一样，有本事送我一千大洋！', '未通过', 0, '0', '0');
INSERT INTO `apply` VALUES (5, 10, 3, '小王1', '情怀惊喜小礼品', '祁同伟', '很喜欢这个项目！', '已评分', '未指导', '未通过', 143, '2', '0');
INSERT INTO `apply` VALUES (7, 2, 1, '98', '减脂餐食工作室', '高育良', 'asdads', '已指导，已评分，并通过', 'fsdfd', '通过', 99, '1', '0');

-- ----------------------------
-- Table structure for judge
-- ----------------------------
DROP TABLE IF EXISTS `judge`;
CREATE TABLE `judge`  (
  `student_id` int(20) NOT NULL COMMENT '学生号',
  `project_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目号',
  `adju_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'NULL' COMMENT '评审员号',
  `project_score` int(20) NULL DEFAULT NULL COMMENT '评审分',
  `is_judged` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未评分' COMMENT '评分标记',
  PRIMARY KEY (`adju_id`, `project_id`, `student_id`) USING BTREE,
  INDEX `project_id`(`project_id`) USING BTREE,
  INDEX `adju_id`(`adju_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of judge
-- ----------------------------
INSERT INTO `judge` VALUES (1, '1', '1', 85, '已评分');
INSERT INTO `judge` VALUES (5, '12', '1', 77, '已评分');
INSERT INTO `judge` VALUES (2, '13', '1', 86, '已评分');
INSERT INTO `judge` VALUES (1, '1', '3', 65, '已评分');
INSERT INTO `judge` VALUES (7, '2', '3', 99, '已评分');
INSERT INTO `judge` VALUES (5, '10', '4', 66, '已评分');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `project_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '项目号',
  `project_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名',
  `project_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目介绍',
  `teacher_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '辅导教师号',
  `project_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目头像',
  PRIMARY KEY (`project_id`) USING BTREE,
  INDEX `project_name_index`(`project_name`) USING BTREE,
  INDEX `teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '零食盒子', '这是一个零售创业新点子。将零食放到盒子里，将盒子放在你伸手就能够到的地方，比如高校寝室，扫描盒子上方的二维码，下单付款，即可获得盒子中美味零食。', '1', '20220426184001039.jpg');
INSERT INTO `project` VALUES (2, '减脂餐食工作室', '每年夏天到来之际，一场隐秘的较量就逐渐开始了。学校食堂太油腻？自己无法制作减脂餐？在学校想要保持完美身材大学生们开始为减脂餐而发愁。在学校经营一家减脂餐食工作室，也是一个大学生创业的新点子。', '1', '20220426184134054.jpg');
INSERT INTO `project` VALUES (3, '关于影视剧的拍摄技巧', '通过贯穿始终并行之有效的创作管理（服务管理）、人才管理、营销管理、组织管理和财务管理等管理措施来确保各业务模块在具有一定管理弹性的基础上得以标准化运作', '2', '20220502195407576.jpg');
INSERT INTO `project` VALUES (10, '情怀惊喜小礼品', '市场空白，需求量大，每个家庭都能用得到，宝宝百天周岁、五一十一旅游、朋友聚会、每年全家福、婚庆、各产品宣传手册等，都可以做。在这个手机拍照如此盛行的年代，此项目会火得停不下来！', '3', '20220506220531294.jpg');
INSERT INTO `project` VALUES (12, '电商带货', '村子里面的电商发展，逐渐走上了轨道，很多农副产品，经过互联网的销售，会成为很大的热点，不少关于村落里面的，电商的发展经营，还有编制', '4', '20220506220721562.jpg');
INSERT INTO `project` VALUES (13, '奶茶店', '奶茶作为吃完饭以后的甜点，由于它浓厚的味道，受人们的喜欢，开一家这样的店，需要租一个大约十平米左右的房间，经过装修，投资的话大约几万元吧，对于自己没有奶茶手艺的朋友来说，可以加盟一些品牌', '4', '20220506220949136.jpg');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '学生号',
  `student_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456' COMMENT '学生密码',
  `student_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `student_sex` int(20) NULL DEFAULT NULL COMMENT '性别',
  `student_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `student_phonenumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未知' COMMENT '学生手机号',
  `student_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '20220401111310047.jpg' COMMENT '学生头像',
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `student_name_index`(`student_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '123', '李云鹏', 1, '936166428@qq.com', '18870404997', '20220426184408241.jpg');
INSERT INTO `student` VALUES (2, '123', '宋爱梅', 0, 'yqwldzy1007@126.com', '13894849894', '20220506214858310.jpg');
INSERT INTO `student` VALUES (3, '123', '赵四', 1, 'lol@qq.com', '15487955454', '20220401111310047.jpg');
INSERT INTO `student` VALUES (4, '123', '孙正义', 1, '896986874@qq.com', '15696542554', '20220506215647827.jpg');
INSERT INTO `student` VALUES (5, '123', '小王1', 1, 'yqwldzy1007@126.com', '+8618870404997', '20220507205049938.JPG');
INSERT INTO `student` VALUES (7, '123', '98', NULL, '', '未知', '20220508082908565.JPG');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `teacher_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '教师号',
  `teacher_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '123456' COMMENT '教师密码',
  `teacher_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师名',
  PRIMARY KEY (`teacher_id`) USING BTREE,
  INDEX `teacher_name`(`teacher_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '123', '高育良');
INSERT INTO `teacher` VALUES (2, '123', '李达康');
INSERT INTO `teacher` VALUES (3, '123', '祁同伟');
INSERT INTO `teacher` VALUES (4, '123', '高小琴');
INSERT INTO `teacher` VALUES (5, '123', '沙瑞金');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_enable` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin@springboot.cn', 1, '67D6556AD0C02443A5C7E79742E63625', 'admin', '19934B57-7A03-42EC-9F22-990DE0452448');
INSERT INTO `user` VALUES (2, 'yqwldzy1007@126.com', 1, '123', '1', NULL);
INSERT INTO `user` VALUES (4, 'wang@qq.com', 1, '67D6556AD0C02443A5C7E79742E63625', 'xiaowang', '19934B57-7A03-42EC-9F22-990DE0452448');
INSERT INTO `user` VALUES (7, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (8, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for usertest
-- ----------------------------
DROP TABLE IF EXISTS `usertest`;
CREATE TABLE `usertest`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of usertest
-- ----------------------------
INSERT INTO `usertest` VALUES (1, '11', 'hgtfh', '524');
INSERT INTO `usertest` VALUES (2, '22', 'sdfsdf', 'sdfsdf');
INSERT INTO `usertest` VALUES (3, '33', 'fdgdfg', '25jhg');

SET FOREIGN_KEY_CHECKS = 1;
