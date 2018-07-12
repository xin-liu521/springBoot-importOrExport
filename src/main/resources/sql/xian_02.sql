drop procedure if exists salary_order_adjust;
DELIMITER //
CREATE PROCEDURE salary_order_adjust(
   IN  param_month_record INT,
   IN  param_rider_id VARCHAR(20)
)
BEGIN
         -- 底薪
   DECLARE   variable_base_price,
      -- <420单结算（5元）
      variable_no_task_base_price,
      -- 超出任务单量基本金额
      variable_over_task_price,
       -- 单量阶梯提成金额
      variable_interval_price,
      -- 季度奖金
      variable_season_price,
      variable_adjust_number,
      variable_total_number,
      variable_beyond_task_number, -- 超出单数
      variable_overtime_adjust_number,
      variable_serious_overtime_number,
      -- 月度冲单奖励
      variable_month_punch_price,
      -- 一星单
			variable_one_star,
			--  二星单
			variable_two_star,
      --  未送达点送达单
      variable_no_service_number,
      --  投诉单
      variable_complain,
      --  二类投诉
      variable_class_ii_complain,
      variable_no_standard_price,
		  -- 变量区间最小值
	    variable_interval_min,
	    -- 变量区间最大值
	    variable_interval_max,
	    -- 变量 value 值
	    variable_value INT DEFAULT 0;
      DECLARE variable_on_time_proportion,
      variable_complete_proportion,
      variable_pleased_proportion varchar(11);
	 -- 定义区间参数游标
	 DECLARE count_intervals cursor for SELECT s_p.interval_min,s_p.interval_max,value FROM salary_param s_p
					 WHERE s_p.param_code='count_interval' AND s_p.ruleid=1 ORDER BY s_p.interval_min DESC;
    -- 定义sql异常处理方式
   DECLARE EXIT  HANDLER FOR SQLEXCEPTION     INSERT INTO salary_calculate_log (record_id,`status`,reason,add_time)
                                              VALUES(param_month_record,1,concat(param_rider_id,'salary_order_adjust'),NOW());
		-- 定义游标获取不到异常
	 DECLARE CONTINUE HANDLER FOR SQLSTATE '02000'  SET variable_value = null;

   SELECT s_o_a.amount,s_o_a.overtime_adjust_number,s_o_a.serious_overtime_number,
          s_o_a.one_star,s_o_a.two_star,s_o_a.no_service_number,
          s_o_a.complain,s_o_a.class_ii_complain
   INTO   variable_adjust_number,variable_overtime_adjust_number,variable_serious_overtime_number,
          variable_one_star,variable_two_star,variable_no_service_number,
          variable_complain,variable_class_ii_complain
   FROM salary_order_adjust s_o_a
   WHERE s_o_a.record_id=param_month_record AND s_o_a.rider_id=param_rider_id;

   SET
    variable_total_number=(SELECT system_number+ifnull(variable_adjust_number,0)
   FROM salary_result_detail s_r_d
   WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=param_rider_id);

   IF variable_total_number>@task
   THEN
		 SET variable_beyond_task_number=variable_total_number-@task;
	 END IF;
   -- 修改实际订单数量
   UPDATE salary_result_detail s_r_d
   SET s_r_d.total_number=variable_total_number,
   s_r_d.adjust_number=variable_adjust_number,
   s_r_d.beyond_task_number=variable_beyond_task_number -- 超出单数
   WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=param_rider_id;

     -- 月度冲单现金奖
    IF variable_total_number>700 AND variable_total_number<=800
    THEN
       SET variable_month_punch_price=20000;
    ELSEIF variable_total_number>800 AND variable_total_number<=1000
    THEN
       SET variable_month_punch_price=30000;
    ELSEIF variable_total_number>1000 AND variable_total_number<=1100
    THEN
        SET variable_month_punch_price=35000;
    ELSEIF variable_total_number>1100
    THEN
        SET variable_month_punch_price=40000;
    END IF;

   IF variable_total_number<@task
   THEN
      --  <420单结算（5元）
      SET variable_no_task_base_price=variable_total_number*@no_task_one_price;
   ELSE
      SET variable_base_price=@base_salary;
      SET variable_over_task_price=(variable_total_number-@task)*@over_task_one_price;
      SET variable_on_time_proportion=(SELECT on_time_proportion FROM salary_order_adjust s_o_a WHERE s_o_a.record_id=param_month_record AND s_o_a.rider_id=param_rider_id);
      SET variable_complete_proportion=(SELECT complete_proportion FROM salary_order_adjust s_o_a WHERE s_o_a.record_id=param_month_record AND s_o_a.rider_id=param_rider_id);
      SET variable_pleased_proportion=(SELECT pleased_proportion FROM salary_order_adjust s_o_a WHERE s_o_a.record_id=param_month_record AND s_o_a.rider_id=param_rider_id);

      IF (variable_on_time_proportion is null) OR
         (variable_complete_proportion is null) OR
         (variable_pleased_proportion is null) OR
         ABS(left(variable_on_time_proportion,length(variable_on_time_proportion)-1))<97
         OR ABS(left(variable_complete_proportion,length(variable_complete_proportion)-1))<99
         OR ABS(left(variable_pleased_proportion,length(variable_pleased_proportion)-1))<15
      THEN
          SET variable_no_standard_price=@no_standard_price*@task;
      END IF;
       /*打开游标*/
	    open count_intervals;
         FETCH count_intervals INTO variable_interval_min,variable_interval_max,variable_value;
         WHILE (variable_value is not null) DO
            -- 单量大于区间最小值
            IF variable_total_number>variable_interval_min
            THEN
               SET variable_interval_price=variable_interval_price+(variable_total_number-variable_interval_min)*variable_value;
               SET variable_total_number=variable_interval_min;
            END IF;
            FETCH count_intervals INTO variable_interval_min,variable_interval_max,variable_value;
		     END WHILE;
      /*关闭游标*/
      close count_intervals;
	  END IF;

    -- 修改工资结果表其它金额数据
    UPDATE salary_result_detail s_r_d
    -- 基本工资
    SET s_r_d.base_price=variable_base_price,
    -- <420单结算（5元）
    s_r_d.no_task_base_price=variable_no_task_base_price,
    -- 超出任务单量基本金额
    s_r_d.over_task_price=variable_over_task_price,
    -- 单量阶梯提成金额
    s_r_d.interval_price=variable_interval_price,
    -- 夜间单提成金额
    s_r_d.night_price=@night_price*s_r_d.night_number,
    -- 大夜单提成金额
    s_r_d.big_night_price=@big_night_price*s_r_d.big_night_number,
    -- 订单金额100-200元区间的提成
    s_r_d.one_two_price=@money_interval_first*s_r_d.one_two_number,
     -- 订单金额大于200的提成
    s_r_d.greater_two_price=@money_interval_second*s_r_d.greater_tow_number,
     -- 大于3公里订单提成
    s_r_d.distance_price=@nav_distance_price*s_r_d.distance_number,
     -- 考核不达标扣款
    s_r_d.no_standard_price=variable_no_standard_price,
     -- 普通超时扣款
    s_r_d.cmmon_overtime_price=@overtime*variable_overtime_adjust_number,
    -- 严重超时扣款
    s_r_d.serious_overtime_price=@serious_overtime*variable_serious_overtime_number,
    -- 月度冲单奖励
    s_r_d.month_punch_price=variable_month_punch_price,
    -- 一星单扣款
    s_r_d.one_star_price=-(3000*variable_one_star),
     -- 二星单扣款
    s_r_d.two_star_price=-(1000*variable_two_star),
    s_r_d.no_service_price=-(50000*variable_no_service_number),
    s_r_d.complain_price=-(5000*variable_complain),
    s_r_d.class_ii_complain_price=-(30000*variable_class_ii_complain)
    WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=param_rider_id;
    set @count=(select count(*) FROM salary_user_info s_u_i WHERE s_u_i.record_id=param_month_record AND s_u_i.rider_id=rider_id);
		IF @count>0
		THEN
	   		CALL salary_user_info(param_month_record,param_rider_id);
		END IF;
 END
//
DELIMITER