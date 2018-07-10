package com.velvol.salary.service;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryMonthRecord;
import com.velvol.salary.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/26.
 */
public interface SalaryMonthRecordService {

    int addMonthRecord(String time, Integer regionId, String userId);

    PageInfo queryMonthRecordPage(SalaryMonthRecord salaryMonthRecord, String yearMonth);

    User selectUserById(String id);

    List<Map<String, Object>> selectRegion();

    int isMonthRecord(String time, Integer regionId);

    List<Map<String, Object>> queryStation(Integer regionId);

    int addStationRank(Map<String, Object> params);

    int deleteStationLevel(Integer recordId);

    int updateMonth(SalaryMonthRecord salaryMonthRecord);
}
