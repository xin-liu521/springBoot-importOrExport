package com.velvol.salary.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.velvol.salary.dao.SalaryMonthRecordMapper;
import com.velvol.salary.domain.SalaryMonthRecord;
import com.velvol.salary.domain.User;
import com.velvol.salary.service.SalaryMonthRecordService;
import com.velvol.salary.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/26.
 */
@Service
public class SalaryMonthRecordServiceImpl implements SalaryMonthRecordService {

    @Autowired
    SalaryMonthRecordMapper salaryMonthRecordMapper;

    @Override
    public int addMonthRecord(String time, Integer regionId, String userId) {
        SalaryMonthRecord salaryMonthRecord = new SalaryMonthRecord();
        Date date = DateUtil.parseToUtilDate(time, "yyyy-MM");
        Calendar c = Calendar.getInstance();    //获取东八区时间
        c.setTime(date);
        //获取年
        int year = c.get(Calendar.YEAR);
        //获取月份，0表示1月份
        int month = c.get(Calendar.MONTH) + 1;

        salaryMonthRecord.setYear(String.valueOf(year));
        salaryMonthRecord.setMonth(String.valueOf(month));
        salaryMonthRecord.setAddtime(new Date());
        salaryMonthRecord.setStatus(0);
        salaryMonthRecord.setRegion(regionId);
        salaryMonthRecord.setOperator(userId);
        return salaryMonthRecordMapper.addMonthRecord(salaryMonthRecord);
    }

    @Override
    public PageInfo queryMonthRecordPage(SalaryMonthRecord salaryMonthRecord, String yearMonth) {
        if (yearMonth != null && !yearMonth.equals("")) {
            Date date = DateUtil.parseToUtilDate(yearMonth, "yyyy-MM");
            Calendar c = Calendar.getInstance();    //获取东八区时间
            c.setTime(date);
            //获取年
            int year = c.get(Calendar.YEAR);
            //获取月份，0表示1月份
            int month = c.get(Calendar.MONTH) + 1;
            salaryMonthRecord.setYear(String.valueOf(year));
            salaryMonthRecord.setMonth(String.valueOf(month));
        }
        Integer pageNum = salaryMonthRecord.getPageNumber();
        Integer length = salaryMonthRecord.getPageSize();
        PageHelper.startPage(pageNum, length);
        List<Map<String, Object>> entityList = salaryMonthRecordMapper.queryOrderDetailPage(salaryMonthRecord);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(entityList);
        return pageInfo;
    }

    @Override
    public User selectUserById(String id) {
        return salaryMonthRecordMapper.selectUserById(id);
    }

    @Override
    public List<Map<String, Object>> selectRegion() {
        return salaryMonthRecordMapper.selectRegion();
    }

    @Override
    public int isMonthRecord(String time, Integer regionId) {
        SalaryMonthRecord salaryMonthRecord = new SalaryMonthRecord();
        Date date = DateUtil.parseToUtilDate(time, "yyyy-MM");
        Calendar c = Calendar.getInstance();    //获取东八区时间
        c.setTime(date);
        //获取年
        int year = c.get(Calendar.YEAR);
        //获取月份，0表示1月份
        int month = c.get(Calendar.MONTH) + 1;
        salaryMonthRecord.setYear(String.valueOf(year));
        salaryMonthRecord.setMonth(String.valueOf(month));
        salaryMonthRecord.setRegion(regionId);
        return salaryMonthRecordMapper.isMonthRecord(salaryMonthRecord);
    }

    @Override
    public List<Map<String, Object>> queryStation(Integer regionId) {
        return salaryMonthRecordMapper.queryStation(regionId);
    }

    @Override
    public int addStationRank(Map<String, Object> params) {
        return salaryMonthRecordMapper.addStationRank(params);
    }

    @Override
    public int deleteStationLevel(Integer recordId) {
        return salaryMonthRecordMapper.deleteStationLevel(recordId);
    }

    @Override
    public int updateMonth(SalaryMonthRecord salaryMonthRecord) {
        return salaryMonthRecordMapper.updateMonth(salaryMonthRecord);
    }
}
