package com.velvol.salary.dao;

import com.velvol.salary.domain.SalaryMonthRecord;
import com.velvol.salary.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/26.
 */
public interface SalaryMonthRecordMapper {

    int addMonthRecord(SalaryMonthRecord salaryMonthRecord);

    List<Map<String, Object>> queryOrderDetailPage(SalaryMonthRecord salaryMonthRecord);

    User selectUserById(String id);

    Map<String, Object> selectWorkerById(String workerId);

    List<Map<String, Object>> selectRegion();

    int isMonthRecord(SalaryMonthRecord salaryMonthRecord);

    int updateMonthRecord(Integer id);

    List<Map<String, Object>> queryStation(Integer regionId);

    int addStationRank(Map<String, Object> params);

    int deleteStationLevel(Integer recordId);

    int updateMonth(SalaryMonthRecord salaryMonthRecord);
}
