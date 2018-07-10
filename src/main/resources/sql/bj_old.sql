drop procedure if exists salary_order_old;
DELIMITER //
	CREATE PROCEDURE salary_order_old(
		 IN  param_month_record INT
	)
BEGIN
   /*定义变量骑手id*/
   DECLARE rider_id VARCHAR(20) DEFAULT '';
    /*定义 计算结果状态*/
   DECLARE variable_status,delete_id INT DEFAULT 0;
   DECLARE
   -- 应出勤天数
   variable_attend_should ,
   -- 实际出勤天数
   variable_attend_actual,
   -- 迟到天数
   variable_belate,
   -- 旷工天数
   variable_absent,
   --  是否自备车  0否 1是
   variable_vehicle,
   -- 是否住宿 0 未住宿 1已住宿
   variable_live
   INT DEFAULT 0;
   DECLARE
	 -- 变量区间最小值
	 variable_interval_min,
	 -- 变量区间最大值
	 variable_interval_max,
	 -- 变量 value 值
	 variable_value
   INT DEFAULT 0;
   DECLARE variable_rider_name VARCHAR(50) DEFAULT '';
   DECLARE variable_stataion_id VARCHAR(10) DEFAULT '';
   DECLARE variable_station_name VARCHAR(200) DEFAULT '';

   DECLARE entry_time,resign_time datetime;

   /*定义游标*/
   DECLARE userInfos cursor for SELECT  s_u_i.rider_id,s_u_i.entry_time,s_u_i.resign_time,s_u_i.attend_should,s_u_i.attend_actual,s_u_i.absent,s_u_i.belate,s_u_i.vehicle,s_u_i.live
   FROM salary_user_info s_u_i WHERE s_u_i.record_id=param_month_record AND s_u_i.is_new_rider=1;
    -- 定义游标获取不到异常
   DECLARE CONTINUE HANDLER FOR SQLSTATE '02000'  SET rider_id = null;
     -- 定义sql异常处理方式
   DECLARE EXIT  HANDLER FOR SQLEXCEPTION   UPDATE salary_month_record s_m_r SET s_m_r.`status`=3 WHERE  s_m_r.id=param_month_record;
   SET @month=(select s_m_r.month FROM salary_month_record s_m_r WHERE s_m_r.id=param_month_record),
       @year=(select s_m_r.year FROM salary_month_record s_m_r WHERE s_m_r.id=param_month_record);
   -- 开启事物 当前搜索引擎不支持事物
   -- START TRANSACTION ;
   /*打开游标*/
	 open userInfos;
      FETCH userInfos INTO rider_id,entry_time,resign_time,variable_attend_should,variable_attend_actual,variable_absent,variable_belate,variable_vehicle,variable_live;
      WHILE (rider_id is not null) DO
           BEGIN
                  DECLARE
									-- 大夜单量
									variable_big_night_number,
									-- 外单夜间单量
									variable_outside_night_number,
								   -- 人效单量
									variable_rx_amount,
									 -- 普通超时容忍后单量
									variable_overtime_number,
                  -- 严重超时容忍后单量
                  variable_serious_overtime_number,
									 -- 未送达点送达单
									variable_no_service_number,
									 -- 一星单
									variable_one_star,
									 --  二星单
									variable_two_star,
									 -- 投诉单
									variable_complain,
									 -- 二类投诉
									variable_class_ii_complain,
									 -- 住宿扣款
									variable_live_deduct,
									 -- 内单总单量
									variable_number,
									 -- 底薪
									variable_base_price,
									 -- 小于等于400单薪资
									variable_less_task_price,
									 -- 充电补助
									variable_charge_price,
									 -- 住宿补助
									variable_live_price,
									 -- 话补
									variable_telephone_price,
									 -- 餐补
									variable_meal_price,
									 -- 车辆补助
									variable_vehicle_price,
									 -- 车辆使用费
									variable_vehicle_deduction_price,
									 -- 数据考核扣款
									variable_no_standard_price,
									 -- 物料款
									variable_equip_price,
									-- 阶梯提成
									variable_interval_price,
									-- 商业险
									variable_insurance_price
									INT DEFAULT 0;
									DECLARE variable_complete_proportion,
                           variable_on_time_proportion  varchar(11);
								 SET  variable_status=(SELECT s_r_d.`status` FROM salary_bj_result_detail s_r_d WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=rider_id),
											delete_id=(SELECT s_r_d.id FROM salary_bj_result_detail s_r_d WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=rider_id);
								 IF variable_status is null
								 THEN
									 SET variable_status=0;
								 END IF;
								 SET @delete_rider_id=rider_id;
									-- 删除掉工资结果表中已经计算过的数据
								 DELETE FROM salary_bj_result_detail  WHERE id=delete_id;
								 -- 根据订单详情汇总 订单数据
								 SET @count=(select count(*) FROM salary_order_detail detail WHERE detail.record_id=param_month_record AND detail.rider_id=rider_id);
								 IF @count>0
										 THEN

                     SELECT s.rider_name,s.stataion_id,s.station_name
										 INTO variable_rider_name,variable_stataion_id,variable_station_name
										 FROM salary_order_detail s
										 WHERE s.record_id=param_month_record AND s.rider_id=rider_id
										 GROUP BY  s.rider_name,s.stataion_id,s.station_name ORDER BY count(*) DESC LIMIT 1;

											-- 外单夜间单量
										 SET variable_outside_night_number=(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND
																					DATE_FORMAT(order_time,'%T')>='21:00:00' AND s.delivery_price=0);
										 SET variable_outside_night_number=variable_outside_night_number+(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND
																					DATE_FORMAT(order_time,'%T')<'06:00:00' AND s.delivery_price=0);
											-- 根据订单详情汇总 订单数据
										 INSERT INTO salary_bj_result_detail (record_id,
																											 rider_id,
																											 rider_name,
																											 stataion_id,
																											 station_name,
																											 inside_number, -- 内单数量
																											 night_number, -- 夜间单量
																											 big_night_number, -- 大夜单量
																											 interval_one_number,-- 大于等于80小于200单量
																											 interval_three_number,-- 大于等于200小于400
																											 interval_four_number,-- 大于等于400小于700
																											 interval_five_number,-- 大于等于700
																											 distance_one_number, -- 3-4KM单量
																											 distance_two_number,-- 4-5KM单量
																											 outside_number, -- 外单量
																											 outside_distance_one_number,
																											 outside_distance_two_number,
																											 outside_night_number,
																											 outside_noon_number,
-- 																											 serious_overtime_number, -- 严重超时单量
																											 `status`
											)
											VALUES(param_month_record,
														 rider_id,
														 variable_rider_name,
                             variable_stataion_id,
                             variable_station_name,
														(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND s.delivery_price>0),
														 -- 夜间单量
														(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND
																					DATE_FORMAT(order_time,'%T')>='21:00:00' AND DATE_FORMAT(order_time,'%T')<='23:59:59' AND s.delivery_price>0),
														 -- 大夜单量
														 (SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id
																		 AND DATE_FORMAT(order_time,'%T')<'06:00:00'),
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.order_price >=80 AND s.order_price <200 AND s.delivery_price>0),
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.order_price >=200 AND s.order_price <400 AND s.delivery_price>0),
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.order_price >=400 AND s.order_price <700 AND s.delivery_price>0),
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.order_price >=700 AND s.delivery_price>0),
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.nav_distance >=3000 AND s.nav_distance<4000 AND s.delivery_price>0),
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.nav_distance >=4000 AND s.delivery_price>0),
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.delivery_price=0),
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.delivery_price=0 AND s.nav_distance >=3000 AND s.nav_distance<5000),
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.delivery_price=0 AND s.nav_distance >=5000),
														 variable_outside_night_number,
														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND
																					DATE_FORMAT(order_time,'%T')>='11:00:00' AND DATE_FORMAT(order_time,'%T')<'14:00:00' AND s.delivery_price=0),

-- 														(SELECT count(*) FROM salary_order_detail s WHERE s.record_id=param_month_record AND s.rider_id=rider_id AND s.delivery_duration>30 AND s.delivery_price>0),
														 variable_status
											);
											-- 查询出人效相关数据
											SELECT  ifnull(s_o_a.amount,0),
															ifnull(s_o_a.overtime_adjust_number,0),
                              ifnull(s_o_a.serious_overtime_number,0),
															ifnull(s_o_a.complete_proportion,0),
															ifnull(s_o_a.on_time_proportion,0),
															ifnull(s_o_a.live_deduct,0),
															ifnull(s_o_a.no_service_number,0),
															ifnull(s_o_a.one_star,0),
															ifnull(s_o_a.two_star,0),
															ifnull(s_o_a.complain,0),
															ifnull(s_o_a.class_ii_complain,0)
											INTO
															variable_rx_amount,variable_overtime_number,variable_serious_overtime_number,variable_complete_proportion,
															variable_on_time_proportion,variable_live_deduct,
															variable_no_service_number,variable_one_star,variable_two_star,
															variable_complain,variable_class_ii_complain
											FROM salary_order_adjust s_o_a
											WHERE s_o_a.record_id=param_month_record AND s_o_a.rider_id=rider_id;
											-- 总单量=内单+人效单量
											SET variable_number=(SELECT (s_r_d.inside_number+variable_rx_amount) FROM salary_bj_result_detail s_r_d WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=rider_id);

											-- 老骑手只用考虑 是否满勤来决定发放(底薪和补助)
											IF  variable_attend_actual>=variable_attend_should
											THEN
														SET variable_base_price=200000, -- 底薪
																variable_charge_price=8000, -- 充电补助
																variable_telephone_price=10000,-- 话补
																variable_meal_price=40000; -- 餐补
																-- 是否住宿 0 未住宿 1已住宿
																IF variable_live IS NULL OR variable_live =0 OR ABS(variable_live_deduct)>30000
																THEN
																	 SET variable_live_price=30000;
																END IF;
																-- 是否自备车  0否 1是
																IF variable_vehicle =1
																THEN
																	SET variable_vehicle_price=20000;
																END IF;
											ELSE
														SET variable_base_price=ROUND(200000/variable_attend_should*variable_attend_actual),
																variable_charge_price=ROUND(8000/variable_attend_should*variable_attend_actual),
																variable_telephone_price=ROUND(10000/variable_attend_should*variable_attend_actual),
																variable_meal_price=ROUND(40000/variable_attend_should*variable_attend_actual);
																-- 住宿补助 是否住宿 0 未住宿 1已住宿
																IF variable_live IS NULL OR variable_live =0 OR ABS(variable_live_deduct)>30000
																THEN
																	 SET variable_live_price=ROUND(30000/variable_attend_should*variable_attend_actual);
																END IF;
																-- 住宿补助 是否自备车  0否 1是
																IF variable_vehicle =1
																THEN
																	SET variable_vehicle_price=ROUND(20000/variable_attend_should*variable_attend_actual);
																END IF;

											END IF;

											 -- 使用公司车辆扣款  是否自备车  0否 1是
											IF variable_vehicle =0
											THEN
													SET variable_vehicle_deduction_price=-(30000/variable_attend_should*variable_attend_actual);
											END IF;
											-- 物料款
											IF entry_time IS NOT NULL AND month(entry_time)=@month AND YEAR(entry_time)=@year
											THEN
													SET variable_equip_price=variable_equip_price-50000;
											END IF;

											IF resign_time IS NOT NULL AND month(resign_time)=@month AND YEAR(resign_time)=@year
											THEN
													SET variable_equip_price=variable_equip_price+50000;
											END IF;

											-- 商业险
											IF entry_time IS NOT NULL AND month(entry_time)=@month
											THEN
													 IF resign_time IS NOT NULL AND month(resign_time)=@month
													 THEN
															SET variable_insurance_price=-(100*variable_attend_actual);
													ELSE
															SET variable_insurance_price=-20000;
													 END IF;
											END IF;
											--  计算阶梯提成
											SET @variable_number_temp=variable_number;
											BEGIN
												-- 定义区间参数游标
												DECLARE count_intervals cursor for SELECT s_p.interval_min,s_p.interval_max,value FROM salary_param s_p
															 WHERE s_p.param_code='count_interval' AND s_p.ruleid=3 ORDER BY s_p.interval_min DESC;
												-- 定义游标获取不到异常
												DECLARE CONTINUE HANDLER FOR SQLSTATE '02000'  SET variable_value = null;
												 /*打开游标*/
												open count_intervals;
													 FETCH count_intervals INTO variable_interval_min,variable_interval_max,variable_value;
													 WHILE (variable_value is not null) DO
															-- 单量大于区间最小值
															IF @variable_number_temp>variable_interval_min
															THEN
																 SET variable_interval_price=variable_interval_price+(@variable_number_temp-variable_interval_min)*variable_value;
																 SET @variable_number_temp=variable_interval_min;
															END IF;
															FETCH count_intervals INTO variable_interval_min,variable_interval_max,variable_value;
													 END WHILE;
												 /*关闭游标*/
												close count_intervals;
											END;

											-- 修改结果表各个金额字段
											UPDATE salary_bj_result_detail s_r_d  SET
												 s_r_d.inside_number=variable_number,
												 s_r_d.base_price=variable_base_price,
												 s_r_d.less_task_price=variable_less_task_price,
												 s_r_d.charge_price=variable_charge_price,
												 s_r_d.live_price=variable_live_price,
												 s_r_d.telephone_price=variable_telephone_price,
												 s_r_d.meal_price=variable_vehicle_price,
												 s_r_d.vehicle_price=variable_meal_price,
												 s_r_d.vehicle_deduction_price=variable_vehicle_deduction_price,
												 s_r_d.no_standard_price=variable_no_standard_price,
												 s_r_d.night_price=s_r_d.night_number*200,
												 s_r_d.big_night_price=s_r_d.big_night_number*300,
												 s_r_d.interval_price=variable_interval_price,
												 s_r_d.interval_one_price=s_r_d.interval_one_number*200,
												 s_r_d.interval_three_price=s_r_d.interval_three_number*500,
												 s_r_d.interval_four_price=s_r_d.interval_four_number*800,
												 s_r_d.interval_five_price=s_r_d.interval_five_number*1500,
												 s_r_d.diatance_one_price=s_r_d.distance_one_number*100,
												 s_r_d.distance_two_price=s_r_d.distance_two_number*200,

												 s_r_d.equip_price=variable_equip_price,
												 s_r_d.insurance_price=variable_insurance_price,

												 s_r_d.outside_price=s_r_d.outside_number*600,
												 s_r_d.outside_distance_price=(s_r_d.outside_distance_one_number*100)+(s_r_d.outside_distance_two_number*200),
												 s_r_d.outside_night_price=s_r_d.outside_night_number*200,
												 s_r_d.outside_noon_price=s_r_d.outside_noon_number*200,
												 s_r_d.cmmon_overtime_price=-(variable_overtime_number*200),
												 s_r_d.serious_overtime_price=-(variable_serious_overtime_number*1000),
												 s_r_d.no_service_price=-(variable_no_service_number*50000),
												 s_r_d.one_star_price=-(variable_one_star*2000),
												 s_r_d.two_star_price=-(variable_two_star*1000),
												 s_r_d.complain_price=-(variable_complain*5000),
												 s_r_d.class_ii_complain_price=-(variable_class_ii_complain*30000),
												 s_r_d.belate_price=-(ifnull(variable_belate,0)*1000),
												 s_r_d.absent_price=-(ifnull(variable_absent,0)*20000)

											WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=rider_id;

											UPDATE salary_bj_result_detail s_r_d
                      SET s_r_d.round_amount_price=round(s_r_d.amount_price/100)
                      WHERE s_r_d.record_id=param_month_record
                      AND s_r_d.rider_id=rider_id;
								 END IF;

						-- FETCH FROM  userInfos INTO rider_id;
						FETCH FROM userInfos INTO rider_id,entry_time,resign_time,variable_attend_should,variable_attend_actual,variable_absent,variable_belate,variable_vehicle,variable_live;
         END;
		  END WHILE;
    /*关闭游标*/
   close userInfos;
END
//
DELIMITER;
