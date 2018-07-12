drop procedure if exists salary;
DELIMITER //
CREATE PROCEDURE salary(
   IN  param_month_record INT,
   IN  param_rider_id VARCHAR(20)
)
BEGIN
   /*定义变量骑手id*/
   DECLARE rider_id VARCHAR(20) DEFAULT '';
   DECLARE variable_rider_name VARCHAR(50) DEFAULT '';
   DECLARE variable_stataion_id VARCHAR(10) DEFAULT '';
   DECLARE variable_station_name VARCHAR(200) DEFAULT '';
   -- 站点级别
	 DECLARE  variable_station_level INT;
	 DECLARE  variable_station_star_num,variable_station_star_price INT DEFAULT 0;
   /*定义 计算结果状态*/
   DECLARE variable_status,delete_id INT DEFAULT 0;
   /*定义游标*/
   DECLARE riderids cursor for SELECT DISTINCT detail.rider_id FROM salary_order_detail detail WHERE record_id=param_month_record;
   -- 定义游标获取不到异常
   DECLARE CONTINUE HANDLER FOR SQLSTATE '02000'  SET rider_id = null;
   -- 定义sql异常处理方式
   DECLARE EXIT  HANDLER FOR SQLEXCEPTION   UPDATE salary_month_record s_m_r SET s_m_r.`status`=3 WHERE  s_m_r.id=param_month_record;

   -- 第一个存储过程需要用到的变量
   set @money_interval_min=(select s_p.interval_min FROM salary_param s_p WHERE s_p.param_code='money_interval_first');
   set @money_interval_max=(select s_p.interval_max FROM salary_param s_p WHERE s_p.param_code='money_interval_first');
   set @nav_distance=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='nav_distance');
   -- 第二个存储过程需要用到的变量
   SET
    @task=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='task'),
    @base_salary=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='base_salary'),
    @no_task_one_price=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='no_task_one_price'),
    @over_task_one_price=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='over_task_one_price'),

    @night_price=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='night_price'),
    @big_night_price=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='big_night_price'),
    @money_interval_first=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='money_interval_first'),
    @money_interval_second=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='money_interval_second'),
    @nav_distance_price=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='nav_distance_price'),
    @no_standard_price=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='no_standard_price'),
    @overtime=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='overtime'),
    @serious_overtime=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='serious_overtime');
    -- 第三个存储过程需要用到的变量
	 SET @meal=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='meal'),
		 @vehicle=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='vehicle'),
		 @telephone=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='telephone'),
		 @live=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='live'),
		 @present=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='present'),
		 @vehicle_deduction_price_one=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='vehicle_deduction_price_one'),
		 @vehicle_deduction_price_two=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='vehicle_deduction_price_two'),
		 @belate_price=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='belate_price'),
		 @absent_price=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='absent_price'),
		 @task2=(select s_p.value FROM salary_param s_p WHERE s_p.param_code='task'),
     @month=(select s_m_r.month FROM salary_month_record s_m_r WHERE s_m_r.id=param_month_record),
     @year =(select s_m_r.year FROM salary_month_record s_m_r WHERE s_m_r.id=param_month_record);
   UPDATE salary_month_record s_m_r SET s_m_r.`status`=1 WHERE  s_m_r.id=param_month_record;
   -- 开启事物 当前搜索引擎不支持事物
   -- START TRANSACTION ;
   /*打开游标*/
	 open riderids;
		  FETCH riderids INTO rider_id;
		  WHILE (rider_id is not null) DO
						 SET  variable_status=(SELECT s_r_d.`status` FROM salary_result_detail s_r_d WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=rider_id),
									delete_id=(SELECT s_r_d.id FROM salary_result_detail s_r_d WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=rider_id);

             IF variable_status is null
						 THEN
							 SET variable_status=0;
						 END IF;
						 SET @delete_rider_id=rider_id;

             -- 站点名称 站点id
						 SELECT s.rider_name,s.stataion_id,s.station_name
						 INTO variable_rider_name,variable_stataion_id,variable_station_name
						 FROM salary_order_detail s
						 WHERE s.record_id=param_month_record AND s.rider_id=rider_id
						 GROUP BY  s.rider_name,s.stataion_id,s.station_name ORDER BY count(*) DESC LIMIT 1;

							-- 删除掉工资结果表中已经计算过的数据
						 DELETE FROM salary_result_detail  WHERE id=delete_id;
						 SET variable_station_star_num=(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND s.order_price>=30);

             SET variable_station_star_price=0;
							-- 计算四五星站点 金额
             SET variable_station_level=(SELECT s_s_l.`level` FROM salary_station_level s_s_l  WHERE  s_s_l.record_id=param_month_record AND s_s_l.stataion_id=variable_stataion_id LIMIT 1);

             IF variable_station_level IS NOT NULL AND variable_station_level=0
							THEN
								SET variable_station_star_price=30*variable_station_star_num;
							ELSEIF variable_station_level IS NOT NULL AND variable_station_level=1
							THEN
								SET variable_station_star_price=50*variable_station_star_num;
						 END IF;
							-- 根据订单详情汇总 订单数据
						 INSERT INTO salary_result_detail (record_id,
																							 rider_id,
																							 rider_name,
																							 stataion_id,
																							 station_name,
																							 total_number,  -- 订单数量
																							 system_number, -- 系统统计数量
																							 adjust_number, -- 人效单量
																							 night_number, -- 夜间单量
																							 big_night_number, -- 大夜单量
																							 one_two_number, -- 100-200元区间单量
																							 greater_tow_number, -- 大于200单量
																							 distance_number, -- 大于三公里订单数量
																							 station_star_num, -- 四五星站点单量
                                               station_star_price, -- 四五星站点金额
																							 `status`
							)
							VALUES(param_month_record,
										 rider_id,
										 variable_rider_name,
										 variable_stataion_id,
										 variable_station_name,
										(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id ),
										(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id ),
										 0,
										-- 夜间单量
										(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND
																	DATE_FORMAT(order_time,'%T')>='21:00:00' AND DATE_FORMAT(order_time,'%T')<='23:59:59'),
										-- 大夜单量
										(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND
																	DATE_FORMAT(order_time,'%T')<'06:00:00'),
										(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND s.order_price >=@money_interval_min AND s.order_price <=@money_interval_max),
										(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND s.order_price > @money_interval_max),
										 -- 大于三公里订单数量
										(SELECT count(*) FROM salary_order_detail s WHERE  s.record_id=param_month_record AND s.rider_id=rider_id AND s.nav_distance > @nav_distance),
										 variable_station_star_num,
                     variable_station_star_price,
										 variable_status
							);
							set @count=(select count(*) FROM salary_order_adjust s_o_a WHERE s_o_a.record_id=param_month_record AND s_o_a.rider_id=rider_id);
							IF @count>0
							THEN
								CALL salary_order_adjust(param_month_record,rider_id);
							END IF;
						FETCH  FROM  riderids INTO rider_id;
		  END WHILE;
   /*关闭游标*/
   close riderids;
   UPDATE salary_month_record s_m_r SET s_m_r.`status`=2 WHERE  s_m_r.id=param_month_record;
   INSERT INTO salary_calculate_log (record_id,`status`,reason,add_time)
   VALUES(param_month_record,0,'',NOW());
   -- COMMIT
 END
//
DELIMITER ;