package com.velvol.salary.service;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryBjResultDetail;
import com.velvol.salary.domain.SalaryResultDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/28.
 */
public interface SalaryResultDetailService {

    PageInfo queryResultDetailPage(SalaryResultDetail salaryResultDetail);

    PageInfo queryNewResultDetailPage(SalaryBjResultDetail salaryBjResultDetail);

    PageInfo queryOldResultDetailPage(SalaryBjResultDetail salaryBjResultDetail);

    List<Map<String, Object>> selectResultDetail(SalaryResultDetail salaryResultDetail);

    void startCalculate(Integer recordId, Integer regionId);

    int updateStatus(SalaryResultDetail salaryResultDetail, Integer[] ids);

    int addResultOrder(SalaryResultDetail salaryResultDetail);

    List<Map<String, Object>> selectBjResultDetail(SalaryBjResultDetail salaryBjResultDetail);

    List<Map<String, Object>> selectBjOldResultDetail(SalaryBjResultDetail salaryBjResultDetail);

    void notarizeOrder(Integer recordId);
}
