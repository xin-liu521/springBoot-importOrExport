drop procedure if exists salary_bj;
DELIMITER //
	CREATE PROCEDURE salary_bj(
		 IN  param_month_record INT,
		 IN  param_rider_id VARCHAR(20)
	)
BEGIN
    UPDATE salary_month_record s_m_r SET s_m_r.`status`=1 WHERE  s_m_r.id=param_month_record;
    CALL salary_order_new(param_month_record);
    CALL salary_order_old(param_month_record);
    BEGIN
				/*定义变量骑手id*/
				DECLARE rider_id VARCHAR(20) DEFAULT '';
				DECLARE social_security,live_deduct,introduction_fee_deduct,group_leader,royal_knight,five_star_general,variable_equip_price INT DEFAULT 0;
				/*定义游标*/
				DECLARE riderids cursor for SELECT s_r_d.rider_id FROM salary_bj_result_detail s_r_d WHERE record_id=param_month_record;
				-- 定义游标获取不到异常
				DECLARE CONTINUE HANDLER FOR SQLSTATE '02000'  SET rider_id = null;
				-- 定义sql异常处理方式
				DECLARE EXIT  HANDLER FOR SQLEXCEPTION   UPDATE salary_month_record s_m_r SET s_m_r.`status`=3 WHERE  s_m_r.id=param_month_record;

				/*打开游标*/
				open riderids;
					FETCH riderids INTO rider_id;
					WHILE (rider_id is not null) DO
								 -- 查询出人效相关数据
								SELECT  s_o_a.social_security,s_o_a.live_deduct,s_o_a.introduction_fee_deduct,
												s_o_a.group_leader,s_o_a.royal_knight,s_o_a.five_star_general

								INTO
											 social_security,live_deduct,introduction_fee_deduct,group_leader,royal_knight,five_star_general
								FROM salary_order_adjust s_o_a
								WHERE s_o_a.record_id=param_month_record AND s_o_a.rider_id=rider_id;

                SELECT s_u_i.equip_price
                INTO variable_equip_price
                FROM salary_user_info s_u_i
								WHERE s_u_i.record_id=param_month_record AND s_u_i.rider_id=rider_id;

								-- 计算合计
								UPDATE salary_bj_result_detail s_r_d
								SET s_r_d.amount_price=
											ifnull(s_r_d.base_price,0)+
											ifnull(s_r_d.less_task_price,0)+
											ifnull(s_r_d.charge_price,0)+
											ifnull(s_r_d.live_price,0)+
											ifnull(s_r_d.telephone_price,0)+
											ifnull(s_r_d.meal_price,0)+
											ifnull(s_r_d.vehicle_price,0)+
											ifnull(s_r_d.vehicle_deduction_price,0)+
											ifnull(s_r_d.no_standard_price,0)+
											ifnull(s_r_d.night_price,0)+
											ifnull(s_r_d.big_night_price,0)+
											ifnull(s_r_d.interval_price,0)+
											ifnull(s_r_d.interval_one_price,0)+
											ifnull(s_r_d.interval_two_price,0)+
											ifnull(s_r_d.interval_three_price,0)+
											ifnull(s_r_d.interval_four_price,0)+
											ifnull(s_r_d.interval_five_price,0)+
											ifnull(s_r_d.diatance_one_price,0)+
											ifnull(s_r_d.distance_two_price,0)+
											ifnull(s_r_d.equip_price,0)+
											ifnull(s_r_d.insurance_price,0)+
											ifnull(s_r_d.outside_price,0)+
											ifnull(s_r_d.outside_distance_price,0)+
											ifnull(s_r_d.outside_night_price,0)+
											ifnull(s_r_d.outside_noon_price,0)+
											ifnull(s_r_d.cmmon_overtime_price,0)+
											ifnull(s_r_d.serious_overtime_price,0)+
											ifnull(s_r_d.no_service_price,0)+
											ifnull(s_r_d.one_star_price,0)+
											ifnull(s_r_d.two_star_price,0)+
											ifnull(s_r_d.complain_price,0)+
											ifnull(s_r_d.class_ii_complain_price,0)+
											ifnull(s_r_d.belate_price,0)+
											ifnull(s_r_d.absent_price,0)+
                      ifnull(s_r_d.personal_tax,0)-

											ABS(ifnull(social_security,0))-
											ABS(ifnull(live_deduct,0))-
											ABS(ifnull(introduction_fee_deduct,0))+
											ABS(ifnull(group_leader,0))+
											ABS(ifnull(royal_knight,0))+
											ABS(ifnull(five_star_general,0))-
                      ABS(ifnull(variable_equip_price,0))
								WHERE s_r_d.record_id=param_month_record
								AND s_r_d.rider_id=rider_id;
					FETCH  FROM  riderids INTO rider_id;
					END WHILE;
			 /*关闭游标*/
			 close riderids;
   END;
   UPDATE salary_month_record s_m_r SET s_m_r.`status`=2 WHERE  s_m_r.id=param_month_record;
   INSERT INTO salary_calculate_log (record_id,`status`,reason,add_time)
   VALUES(param_month_record,0,'',NOW());
END
//
DELIMITER;