drop procedure if exists salary_user_info;
DELIMITER //
CREATE PROCEDURE salary_user_info(
   IN  param_month_record INT,
   IN  param_rider_id VARCHAR(20)
)
BEGIN
   -- 应出勤天数
   DECLARE   variable_attend_should ,
   -- 实际出勤天数
   variable_attend_actual,
   -- 迟到天数
   variable_belate,
   -- 旷工天数
   variable_absent,
   --  是否自备车  0否 1是
   variable_vehicle,
   -- 是否住宿 0 未住宿 1已住宿
   variable_live,
   -- 岗位补贴/培训补贴 (分)
   variable_subsidy_price,
   -- 微笑行动不达标扣款(分)
   variable_smile_action_price,
   -- 高温补贴(分)
   variable_temperature_price,
   -- 物料扣款 导入(单位是分)
   variable_equip_price_import,
   -- 车辆补助
   variable_vehicle_price,
   -- 餐补
   variable_meal_price,
   -- 话补
   variable_telephone_price,
   -- 住宿补助
   variable_live_price,
   -- 全勤补助
   variable_present_price,
   -- 使用公司车辆扣款
   vehicle_deduction_price,
   -- 单量
   variable_total_number,
   -- 保险费
   variable_insurance,
   -- 物料使用费
   variable_equip_use_price,
   -- 购买物料费用
   variable_equip_buy_price,
   -- 培训费
   variable_train_price,
   -- 补上月工资
   variable_lastmonth_price,
   -- 购买物料费用 状态
   variable_equip_status,
   -- 购买物料应总费用
   variable_equip_price,
    -- 购买物料应已扣费用
   variable_equip_deduction_price
   INT DEFAULT 0;

   DECLARE entry_time,resign_time datetime;
   -- 定义sql异常处理方式
   DECLARE EXIT  HANDLER FOR SQLEXCEPTION   INSERT INTO salary_calculate_log (record_id,`status`,reason,add_time)
                                            VALUES(param_month_record,1,concat(param_rider_id,'salary_user_info'),NOW());

   SELECT s_r_d.total_number INTO variable_total_number FROM salary_result_detail s_r_d WHERE  s_r_d.record_id=param_month_record AND s_r_d.rider_id=param_rider_id;

   SELECT  s_u_i.attend_should,s_u_i.attend_actual,s_u_i.belate,s_u_i.absent,s_u_i.vehicle,
           s_u_i.live,s_u_i.entry_time,s_u_i.resign_time,s_u_i.subsidy_price,s_u_i.smile_action_price,
           s_u_i.temperature_price,s_u_i.equip_price
   INTO
   variable_attend_should,variable_attend_actual,variable_belate,variable_absent,variable_vehicle,
   variable_live,entry_time,resign_time,variable_subsidy_price,variable_smile_action_price,
   variable_temperature_price,variable_equip_price_import
   FROM salary_user_info s_u_i
   WHERE s_u_i.record_id=param_month_record AND s_u_i.rider_id=param_rider_id;
   -- 餐补,话补,住宿
   IF variable_total_number>=@task2
   THEN
        -- 餐补,话补,话补,住宿补助 出勤天数小于23天
        IF variable_attend_actual<variable_attend_should
        THEN
           SET variable_meal_price=ROUND(@meal/variable_attend_should*variable_attend_actual);
           SET variable_telephone_price=ROUND(@telephone/variable_attend_should*variable_attend_actual);
           -- 是否住宿 0 未住宿 1已住宿
           IF  variable_live IS NULL OR  variable_live =0
           THEN
             SET variable_live_price=ROUND(@live/variable_attend_should*variable_attend_actual);
           END IF;
           -- 是否自备车  0否 1是
           IF variable_vehicle =1
           THEN
             SET variable_vehicle_price=ROUND(@vehicle/variable_attend_should*variable_attend_actual);
           END IF;
        ELSE
           SET variable_meal_price=@meal;
           SET variable_telephone_price=@telephone;
           -- 是否住宿 0 未住宿 1已住宿
           IF variable_live IS NULL OR variable_live =0
           THEN
             SET variable_live_price=@live;
           END IF;
           -- 是否自备车  0否 1是
           IF variable_vehicle =1
           THEN
             SET variable_vehicle_price=@vehicle;
           END IF;
        END IF;
   END IF;
   -- 全勤
   IF variable_attend_actual>=variable_attend_should
   THEN
			 SET variable_present_price=@present;
   ELSE
     -- 非全勤 但是当月入职 按天算
      IF entry_time IS NOT NULL AND month(entry_time)=@month AND YEAR(entry_time)=@year
      THEN
          SET variable_present_price=ROUND(@present/variable_attend_should*variable_attend_actual);
      END IF;
   END IF;
   -- 是否自备车  0否 1是
	 IF variable_vehicle =0
	 THEN
		  IF variable_attend_actual>20
      THEN
         SET vehicle_deduction_price=@vehicle_deduction_price_one;
      ELSE
         SET vehicle_deduction_price=variable_attend_actual*@vehicle_deduction_price_two;
      END IF;
	 END IF;
   -- 保险费
   -- 当月入职或入职满一年 扣除200
   IF (entry_time is not null) AND (month(entry_time)=@month)
   THEN
     IF resign_time IS NOT NULL AND month(resign_time)=@month
     THEN
       SET variable_insurance=-(100*variable_attend_actual);
     ELSE
			 SET variable_insurance=-20000;
		 END IF;
   END IF;
   -- 第七个月扣除100
   IF (entry_time is not null) AND ((month(entry_time)+6)%12=@month)
   THEN
     IF resign_time IS NOT NULL AND ((month(entry_time)+6)%12=@month)
     THEN
       SET variable_insurance=-(100*variable_attend_actual);
     ELSE
			 SET variable_insurance=-10000;
		 END IF;
   END IF;
   -- 物料使用费   物料使用费  2017.09.01-2018.03.31 之间入职的人员 每月扣除30元使用费 扣满10个月停止.
   IF (entry_time is not null) AND (Date(entry_time)>'2017-08-31') AND (Date(entry_time)<'2018-04-01')
   THEN
      SET @salary_record=(SELECT COUNT(*) salary_record  FROM  salary_result_detail s_r_d WHERE  s_r_d.rider_id=param_rider_id  AND s_r_d.equip_use_price>0 );
      IF @salary_record <10
      THEN
        SET variable_equip_use_price=-3000;
      END IF;
   END IF;

   -- 购买物料费用
   SELECT ew.status,CONVERT(ifnull(ew.total*100,0),SIGNED),CONVERT(ifnull(ew.deduction*100,0),SIGNED)
   INTO
   variable_equip_status,variable_equip_price,variable_equip_deduction_price -- 购买物料费用 状态    购买物料应总费用
   FROM worker w LEFT JOIN equipworker ew on w.id=ew.workid  WHERE w.meituanid=param_rider_id LIMIT 1;

   -- 总价大于已扣除金额
   IF (variable_equip_price is not null) AND (variable_equip_price>variable_equip_deduction_price)
   THEN
      -- 骑手离职扣除全部 或者入职月份与当前计算工资的年份,月份不同
      IF  (resign_time is not null) OR (entry_time is not null AND (YEAR(entry_time)!= @year OR month(entry_time)!= @month))
      THEN
          SET variable_equip_buy_price=-(variable_equip_price-variable_equip_deduction_price);
      ELSE
          SET variable_equip_buy_price=-(variable_equip_price/2); -- 每次扣除一半费用
      END IF;
   END IF;
   -- 根据扣费状态来判断
--    IF (variable_equip_status is not null) AND (variable_equip_price is not null) AND variable_equip_status!=2
--    THEN
--      IF variable_equip_status=0
--      THEN
--        SET variable_equip_buy_price=-(variable_equip_price/2); -- 每次扣除一半费用
--      ELSE
--        SET variable_equip_buy_price=-(variable_equip_price-variable_equip_deduction_price);
--      END IF;
--    END IF;



   -- 培训费退还  (考勤提供的)当月离职人员  2018.04.01以前入职的人员  退培训费300元
   IF (resign_time is not null) AND (entry_time is not null) AND (month(resign_time)=@month) AND (Date(entry_time)<'2018-04-01')
   THEN
      SET variable_train_price=30000;
   END IF;
   -- 补上月工资
   SET @last_amount_price=(SELECT s_r_d.amount_price  FROM salary_result_detail s_r_d
   WHERE
   s_r_d.rider_id=param_rider_id AND s_r_d.record_id != param_month_record
   ORDER BY s_r_d.record_id DESC LIMIT 1);
   IF (@last_amount_price is not null) AND (@last_amount_price<0)
   THEN
      SET variable_lastmonth_price=@last_amount_price;
   END IF;
   UPDATE salary_result_detail s_r_d
   SET s_r_d.vehicle_price=variable_vehicle_price,
       s_r_d.meal_price=variable_meal_price,
       s_r_d.telephone_price=variable_telephone_price,
       s_r_d.live_price=variable_live_price,
       s_r_d.present_price=variable_present_price,
       s_r_d.vehicle_deduction_price=vehicle_deduction_price,
       s_r_d.belate_price=variable_belate*@belate_price,
       s_r_d.absent_price=variable_absent*@absent_price,
       s_r_d.insurance=variable_insurance, -- 保险费
       s_r_d.equip_use_price=variable_equip_use_price, -- 物料使用费
       s_r_d.equip_buy_price=variable_equip_buy_price, -- 购买物料费用
       s_r_d.train_price=variable_train_price, -- 培训费
       s_r_d.lastmonth_price=variable_lastmonth_price-- 补上月工资
   WHERE s_r_d.record_id=param_month_record AND s_r_d.rider_id=param_rider_id;
   UPDATE salary_result_detail s_r_d
   SET s_r_d.amount_price=
       ifnull(s_r_d.base_price,0)+
       ifnull(s_r_d.no_task_base_price,0)+
       ifnull(s_r_d.over_task_price,0)+
       ifnull(s_r_d.interval_price,0)+
       ifnull(s_r_d.night_price,0)+
       ifnull(s_r_d.big_night_price,0)+
       ifnull(s_r_d.one_two_price,0)+
       ifnull(s_r_d.greater_two_price,0)+
       ifnull(s_r_d.distance_price,0)+
       -- 四五星站点金额
       ifnull(s_r_d.station_star_price,0)+
       -- 月度冲单奖励
       ifnull(s_r_d.month_punch_price,0)+
       ifnull(s_r_d.season_price,0)+
       ifnull(s_r_d.vehicle_price,0)+
       ifnull(s_r_d.meal_price,0)+
       ifnull(s_r_d.telephone_price,0)+
       ifnull(s_r_d.live_price,0)+
       ifnull(s_r_d.present_price,0)+
       ifnull(s_r_d.vehicle_deduction_price,0)+
       ifnull(s_r_d.no_standard_price,0)+
       ifnull(s_r_d.cmmon_overtime_price,0)+
       ifnull(s_r_d.serious_overtime_price,0)+
       ifnull(s_r_d.one_star_price,0)+
       ifnull(s_r_d.two_star_price,0)+
       ifnull(s_r_d.no_service_price,0)+
       ifnull(s_r_d.complain_price,0)+
       ifnull(s_r_d.class_ii_complain_price,0)+
       ifnull(s_r_d.belate_price,0)+
       ifnull(s_r_d.absent_price,0)+
       -- 补上月工资
       ifnull(s_r_d.lastmonth_price,0)+
       -- 培训费
       ifnull(s_r_d.train_price,0)-
       -- 保险费
       ABS(ifnull(s_r_d.insurance,0))-
       -- 物料使用费
       ABS(ifnull(s_r_d.equip_use_price,0))-
       -- 购买物料费用
       ABS(ifnull(s_r_d.equip_buy_price,0))+
       -- 岗位补贴/培训补贴 (分)
       ABS(ifnull(variable_subsidy_price,0))-
       -- 微笑行动不达标扣款(分)
       ABS(ifnull(variable_smile_action_price,0))+
       -- 高温补贴(分)
       ABS(ifnull(variable_temperature_price,0))-
       -- 物料扣款(单位是分)
       ABS(ifnull(variable_equip_price_import,0))

      WHERE s_r_d.record_id=param_month_record
      AND s_r_d.rider_id=param_rider_id;

      UPDATE salary_result_detail s_r_d
       SET s_r_d.round_amount_price=round(s_r_d.amount_price/100)
      WHERE s_r_d.record_id=param_month_record
      AND s_r_d.rider_id=param_rider_id;

END
//
DELIMITER ;