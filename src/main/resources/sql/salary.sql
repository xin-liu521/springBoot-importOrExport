/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.62
Source Server Version : 50051
Source Host           : 192.168.0.62:3306
Source Database       : hrsysnewdb

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-05-02 10:17:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for salary_month_record
-- ----------------------------
DROP TABLE IF EXISTS `salary_month_record`;
CREATE TABLE `salary_month_record` (
  `id` int(11) NOT NULL auto_increment,
  `year` varchar(10) default NULL COMMENT '工资计算年份',
  `month` varchar(10) default NULL COMMENT '工资计算月份',
  `status` int(11) default '0' COMMENT '0.未计算1.计算中.2计算完成.3计算失败',
  `addtime` datetime default NULL COMMENT '添加时间',
  `operator` varchar(255) default NULL COMMENT '操作人',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : hrsysnewdb

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-05-12 17:43:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `salary_order_adjust`
-- ----------------------------
DROP TABLE IF EXISTS `salary_order_adjust`;
CREATE TABLE `salary_order_adjust` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `record_id` int(11) DEFAULT NULL COMMENT '工资计算记录表id',
  `rider_id` varchar(20) DEFAULT NULL COMMENT '骑手id',
  `rider_name` varchar(50) DEFAULT NULL,
  `stataion_id` varchar(10) DEFAULT NULL,
  `station_name` varchar(200) DEFAULT NULL,
  `system_number` int(11) DEFAULT '0' COMMENT '系统统计数量',
  `amount` int(11) DEFAULT '0' COMMENT '人效单量',
  `common_overtime_number` int(11) DEFAULT '0' COMMENT '普通超时订单数量',
  `overtime_adjust_number` int(11) DEFAULT '0' COMMENT '普通超时容忍后单量',
  `discontent_number` int(11) DEFAULT '0' COMMENT '不满意单数量',
  `on_time_proportion` varchar(11) DEFAULT '0',
  `complete_proportion` varchar(11) DEFAULT '0' COMMENT '完成率',
  `pleased_proportion` varchar(11) DEFAULT '0' COMMENT '满意率',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=742 DEFAULT CHARSET=utf8;


/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.62
Source Server Version : 50051
Source Host           : 192.168.0.62:3306
Source Database       : hrsysnewdb

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-05-02 10:17:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for salary_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `salary_order_detail`;
CREATE TABLE `salary_order_detail` (
  `id` bigint(20) NOT NULL auto_increment COMMENT '自增id',
  `record_id` int(11) default NULL COMMENT '工资计算记录表id',
  `order_id` varchar(50) default NULL,
  `waybill_id` varchar(50) default NULL,
  `seller_num` varchar(100) default NULL COMMENT '商家流水号',
  `seller_name` varchar(500) default NULL COMMENT '商家名称',
  `seller_id` varchar(20) default NULL COMMENT '商家ID',
  `city` varchar(20) default NULL,
  `rider_name` varchar(50) default NULL,
  `rider_id` varchar(20) default NULL COMMENT '骑手ID',
  `station_name` varchar(200) default NULL,
  `stataion_id` varchar(10) default NULL,
  `trade_area` varchar(100) default NULL COMMENT '商圈',
  `preorder` varchar(10) default NULL COMMENT '预订单',
  `status` varchar(10) default NULL COMMENT '状态',
  `organization_type` varchar(10) default NULL COMMENT '组织类型',
  `epiboly_type` varchar(10) default NULL COMMENT '众包类型',
  `delivery_duration` double default NULL COMMENT '配送时效',
  `wait_duration` varchar(10) default NULL COMMENT '等待时长',
  `arrive_duration` varchar(10) default NULL COMMENT '送达时长',
  `hit_duration` varchar(10) default NULL COMMENT '连击时长',
  `nav_distance` int(11) default NULL COMMENT '导航距离',
  `line_distance` int(11) default NULL COMMENT '折线距离',
  `seller_address` varchar(500) default NULL COMMENT '商家地址',
  `seller_delivery_score` varchar(50) default NULL COMMENT '商家配送评分',
  `seller_delivery_apprise` varchar(255) default NULL COMMENT '商家配送评价',
  `order_original_price` varchar(10) default NULL COMMENT '订单原价',
  `order_price` double default NULL COMMENT '订单金额',
  `discount_price` varchar(10) default NULL COMMENT '折扣金额',
  `pay_seller` varchar(10) default NULL COMMENT '付商家款',
  `actual_pay` varchar(10) default NULL COMMENT '实际付款',
  `user_pay` varchar(10) default NULL COMMENT '收用户款',
  `actual_gather` varchar(10) default NULL COMMENT '实际收款',
  `delivery_price` varchar(10) default NULL COMMENT '配送费',
  `order_time` datetime default NULL COMMENT '下单时间',
  `pay_time` datetime default NULL COMMENT '支付时间',
  `hope_time` datetime default NULL COMMENT '期望送达时间',
  `seller_push_time` datetime default NULL COMMENT '商家推单时间',
  `dispatch_time` datetime default NULL COMMENT '调度时间',
  `receive_time` datetime default NULL COMMENT '接单时间',
  `arrive_shop_time` datetime default NULL COMMENT '到店时间',
  `pickup_time` datetime default NULL COMMENT '取货时间',
  `arrive_time` datetime default NULL COMMENT '送达时间',
  `cancel_time` datetime default NULL COMMENT '取消时间',
  `cancel_reason` varchar(200) default NULL COMMENT '取消原因',
  `cancel_operator` varchar(50) default NULL COMMENT '取消操作人',
  `apply_refund_reason` varchar(255) default '' COMMENT '申请退款原因',
  `apply_refund_operator` varchar(50) default NULL COMMENT '申请退款操作人',
  `text1` varchar(50) default NULL,
  `text2` varchar(50) default NULL,
  `text3` varchar(50) default NULL,
  `text4` varchar(50) default NULL,
  `text5` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  KEY `rider_id_index` USING BTREE (`rider_id`)
) ENGINE=MyISAM AUTO_INCREMENT=306781 DEFAULT CHARSET=utf8;

/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.62
Source Server Version : 50051
Source Host           : 192.168.0.62:3306
Source Database       : hrsysnewdb

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-05-02 10:17:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for salary_overtime_adjust
-- ----------------------------
DROP TABLE IF EXISTS `salary_overtime_adjust`;
CREATE TABLE `salary_overtime_adjust` (
  `id` int(11) NOT NULL COMMENT 'id',
  `record_id` int(11) default NULL COMMENT '工资计算记录表id',
  `order_id` varchar(50) default NULL COMMENT '订单id',
  `rider_id` varchar(20) default NULL COMMENT '骑手id',
  `status` int(11) default '0' COMMENT '是否容忍 0 不容忍 1容忍',
  `reason` varchar(500) default NULL COMMENT '容忍原因',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.62
Source Server Version : 50051
Source Host           : 192.168.0.62:3306
Source Database       : hrsysnewdb

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-05-04 11:21:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for salary_param
-- ----------------------------
DROP TABLE IF EXISTS `salary_param`;
CREATE TABLE `salary_param` (
  `id` int(11) NOT NULL auto_increment COMMENT 'id',
  `ruleid` int(11) default NULL COMMENT '规则id',
  `param_code` varchar(255) default NULL COMMENT '参数代码',
  `name` varchar(255) default NULL COMMENT '参数名字',
  `interval_min` int(11) default NULL COMMENT '区间参数下限',
  `interval_max` int(11) default NULL COMMENT '区间参数上限',
  `value` int(11) default NULL COMMENT '参数值',
  `type` int(11) default '0' COMMENT '参数类型 0 普通参数,1金额参数 (金额存储为分)',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary_param
-- ----------------------------
INSERT INTO `salary_param` VALUES ('1', '1', 'base_salary', '底薪', null, null, '170000', '1');
INSERT INTO `salary_param` VALUES ('2', '1', 'meal', '餐补', null, null, '20000', '1');
INSERT INTO `salary_param` VALUES ('3', '1', 'vehicle', '车补', null, null, '20000', '1');
INSERT INTO `salary_param` VALUES ('4', '1', 'telephone', '话补', null, null, '10000', '1');
INSERT INTO `salary_param` VALUES ('5', '1', 'live', '住宿补助', null, null, '5000', '1');
INSERT INTO `salary_param` VALUES ('6', '1', 'present', '全勤', null, null, '10000', '1');
INSERT INTO `salary_param` VALUES ('7', '1', 'task', '单量任务', null, null, '420', '0');
INSERT INTO `salary_param` VALUES ('8', '1', 'on_time_proportion', '准时率', null, null, '97', '0');
INSERT INTO `salary_param` VALUES ('9', '1', 'complete_proportion', '完成率', null, null, '99', '0');
INSERT INTO `salary_param` VALUES ('10', '1', 'pleased_proportion', '满意率', null, null, '15', '0');
INSERT INTO `salary_param` VALUES ('11', '1', 'default_proportion', '率不达标每单扣除', null, null, '-20', '1');
INSERT INTO `salary_param` VALUES ('12', '1', 'count_interval', '单量区间', '420', '500', '50', '1');
INSERT INTO `salary_param` VALUES ('13', '1', 'count_interval', '单量区间', '500', '600', '60', '1');
INSERT INTO `salary_param` VALUES ('14', '1', 'count_interval', '单量区间', '600', '700', '70', '1');
INSERT INTO `salary_param` VALUES ('15', '1', 'count_interval', '单量区间', '700', '800', '80', '1');
INSERT INTO `salary_param` VALUES ('16', '1', 'count_interval', '单量区间', '800', '900', '100', '1');
INSERT INTO `salary_param` VALUES ('17', '1', 'count_interval', '单量区间', '900', '1000', '120', '1');
INSERT INTO `salary_param` VALUES ('18', '1', 'count_interval', '单量区间', '1000', null, '150', '1');
INSERT INTO `salary_param` VALUES ('19', '1', 'money_interval_first', '大额区间', '100', '200', '300', '1');
INSERT INTO `salary_param` VALUES ('20', '1', 'money_interval_second', '大额区间', '200', null, '600', '1');
INSERT INTO `salary_param` VALUES ('21', '1', 'season_count_interval', '季度单量奖金', '2000', '25000', '100', '1');
INSERT INTO `salary_param` VALUES ('22', '1', 'season_count_interval', '季度单量奖金', '2500', '3000', '120', '1');
INSERT INTO `salary_param` VALUES ('23', '1', 'season_count_interval', '季度单量奖金', '3000', null, '150', '1');
INSERT INTO `salary_param` VALUES ('24', '1', 'default_task', '单量任务不达标', null, null, '500', '1');
INSERT INTO `salary_param` VALUES ('25', '1', 'overtime', '普通超时罚款', null, null, '-500', '1');
INSERT INTO `salary_param` VALUES ('26', '1', 'serious_overtime', '严重超时罚款', null, null, '-3000', '1');
INSERT INTO `salary_param` VALUES ('27', '1', 'overtime_minus_interval', '超时容忍区间', '0', '10', '7', '0');
INSERT INTO `salary_param` VALUES ('28', '1', 'overtime_minus_interval', '超时容忍区间', '10', '20', '5', '0');
INSERT INTO `salary_param` VALUES ('29', '1', 'overtime_minus_interval', '超时容忍区间', '20', null, '3', '0');
INSERT INTO `salary_param` VALUES ('30', '1', 'nav_distance', '大距距离', null, null, '3000', '0');
INSERT INTO `salary_param` VALUES ('31', '1', 'nav_distance_price', '大距单笔奖金', null, null, '100', '1');
INSERT INTO `salary_param` VALUES ('32', '1', 'no_task_one_price', '任务不达标单笔单金额', null, null, '500', '1');
INSERT INTO `salary_param` VALUES ('33', '1', 'over_task_one_price', '超出单量部分单笔金额', null, null, '580', '1');
INSERT INTO `salary_param` VALUES ('34', '1', 'night_price', '夜间单提成', null, null, '100', '1');
INSERT INTO `salary_param` VALUES ('35', '1', 'big_night_price', '大夜提成', null, null, '200', '1');
INSERT INTO `salary_param` VALUES ('36', '1', 'no_standard_price', '任务量达标率不达标每笔扣款', null, null, '-20', '1');
INSERT INTO `salary_param` VALUES ('37', '1', 'vehicle_deduction_price_one', '使用公司车辆扣款(出勤大于20天)', null, null, '-15000', '1');
INSERT INTO `salary_param` VALUES ('38', '1', 'vehicle_deduction_price_two', '使用公司车辆每天扣款(出勤小于等于20天)', null, null, '-500', '1');
INSERT INTO `salary_param` VALUES ('39', '1', 'belate_price', '迟到每天扣款', null, null, '0', '1');
INSERT INTO `salary_param` VALUES ('40', '1', 'absent_price', '旷工每天扣款', null, null, '-20000', '1');
INSERT INTO `salary_param` VALUES ('41', '1', 'discontent_price', '不满意单扣款', null, null, '0', '1');



/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.62
Source Server Version : 50051
Source Host           : 192.168.0.62:3306
Source Database       : hrsysnewdb

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-05-12 14:33:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for salary_result_detail
-- ----------------------------
DROP TABLE IF EXISTS `salary_result_detail`;
CREATE TABLE `salary_result_detail` (
  `id` int(11) NOT NULL auto_increment,
  `record_id` int(11) default NULL COMMENT '工资计算记录表id',
  `rider_id` varchar(20) default NULL COMMENT '骑手id',
  `rider_name` varchar(50) default NULL,
  `stataion_id` varchar(10) default NULL,
  `station_name` varchar(200) default NULL,
  `total_number` int(11) default '0' COMMENT '实际订单数量',
  `system_number` int(11) default '0' COMMENT '系统统计数量',
  `adjust_number` int(11) default '0' COMMENT '人效单量',
  `beyond_task_number` int(11) default '0' COMMENT '超出单数',
  `night_number` int(11) default '0' COMMENT '夜间单量',
  `big_night_number` int(11) default '0' COMMENT '大夜单量',
  `one_two_number` int(11) default '0' COMMENT '100-200元区间单量',
  `greater_tow_number` int(11) default '0' COMMENT '大于200单量',
  `distance_number` int(11) default '0' COMMENT '大于三公里订单数量',
  `common_overtime_number` int(11) default '0' COMMENT '普通超时订单数量',
  `adjust_overtime_number` int(11) default '0' COMMENT '容忍后普通超时单量',
  `serious_overtime_number` int(11) default '0' COMMENT '严重超时订单数量',
  `discontent_number` int(11) default '0' COMMENT '不满意单数量',
  `base_price` int(11) default '0' COMMENT '底薪',
  `no_task_base_price` int(11) default '0' COMMENT '<420单结算（5元）',
  `over_task_price` int(11) default '0' COMMENT '超出任务单量基本金额',
  `interval_price` int(11) default '0' COMMENT '单量阶梯提成金额',
  `night_price` int(11) default '0' COMMENT '夜间单提成金额',
  `big_night_price` int(11) default '0' COMMENT '大夜单提成金额',
  `one_two_price` int(11) default '0' COMMENT '订单金额100-200元区间的提成',
  `greater_two_price` int(11) default '0' COMMENT '订单金额大于200的提成',
  `distance_price` int(11) default '0' COMMENT '大于3公里订单提成',
  `season_price` int(11) default '0' COMMENT '季度奖金',
  `vehicle_price` int(11) default '0' COMMENT '车辆补助',
  `meal_price` int(11) default '0' COMMENT '餐补',
  `telephone_price` int(11) default '0' COMMENT '话补',
  `live_price` int(11) default '0' COMMENT '住宿补助',
  `present_price` int(11) default '0' COMMENT '全勤补助',
  `vehicle_deduction_price` int(11) default '0' COMMENT '使用公司车辆扣款',
  `no_standard_price` int(11) default '0' COMMENT '考核不达标扣款',
  `discontent_price` int(11) default '0' COMMENT '不满意单扣款',
  `cmmon_overtime_price` int(11) default '0' COMMENT '普通超时扣款',
  `serious_overtime_price` int(11) default '0' COMMENT '严重超时扣款',
  `belate_price` int(11) default '0' COMMENT '迟到扣款',
  `absent_price` int(11) default '0' COMMENT '旷工扣款',
  `lastmonth_price` int(11) default '0' COMMENT '补上月工资',
  `train_price` int(11) default '0' COMMENT '培训费',
  `insurance` int(11) default '0' COMMENT '保险费',
  `equip_use_price` int(11) default '0' COMMENT '物料使用费',
  `equip_buy_price` int(11) default '0' COMMENT '购买物料费用',
  `amount_price` int(10) default '0' COMMENT '工资总额',
  `status` int(11) default '0' COMMENT '状态 0 发放 1暂扣 2不发放',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=27225 DEFAULT CHARSET=utf8;





/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.62
Source Server Version : 50051
Source Host           : 192.168.0.62:3306
Source Database       : hrsysnewdb

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-05-12 14:31:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for salary_user_info
-- ----------------------------
DROP TABLE IF EXISTS `salary_user_info`;
CREATE TABLE `salary_user_info` (
  `id` int(11) NOT NULL auto_increment,
  `record_id` int(11) default NULL COMMENT '骑手id',
  `rider_id` varchar(20) default NULL COMMENT '骑手id',
  `entry_time` datetime default NULL COMMENT '入职时间',
  `resign_time` datetime default NULL COMMENT '离职时间',
  `rider_name` varchar(50) default NULL,
  `stataion_id` varchar(10) default NULL,
  `station_name` varchar(200) default NULL,
  `attend_should` int(11) default '0' COMMENT '应出勤天数',
  `attend_actual` int(11) default '0' COMMENT '实际出勤天数',
  `belate` int(11) default '0' COMMENT '迟到天数',
  `absent` int(11) default '0' COMMENT '旷工',
  `rest` int(11) default '0' COMMENT '公休',
  `leave` int(11) default '0' COMMENT '事假',
  `vehicle` int(11) default '0' COMMENT '车辆使用情况 0自备 1使用公司车辆',
  `live` int(11) default '0' COMMENT '是否住宿 0 未住宿 1已住宿',
  `subsidy_price` int(11) default '0' COMMENT '岗位补贴/培训补贴 (分)',
  `smile_action_price` int(11) default '0' COMMENT '微笑行动不达标扣款(分)',
  `temperature_price` int(11) default '0' COMMENT '高温补贴(分)',
  `equip_price` int(11) default '0' COMMENT '物料扣款(单位是分)',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.62
Source Server Version : 50051
Source Host           : 192.168.0.62:3306
Source Database       : hrsysnewdb

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-05-12 15:35:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for salary_calculate_log
-- ----------------------------
DROP TABLE IF EXISTS `salary_calculate_log`;
CREATE TABLE `salary_calculate_log` (
  `id` int(11) NOT NULL auto_increment,
  `record_id` int(11) default NULL,
  `status` int(11) default NULL COMMENT '0 计算成功 1 计算失败',
  `reason` varchar(255) default NULL,
  `add_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



-- 设置写入缓存 加快导入订单的速度
set global bulk_insert_buffer_size=67108864
-- 设置 语句长度
show VARIABLES like '%max_allowed_packet%';
set global max_allowed_packet = 5*1024*1024*10
-- 设置 并发 线程
-- set global thread_concurrency = 4
