package com.velvol.salary.dao;

import com.velvol.salary.domain.EquipWorker;
import com.velvol.salary.domain.EquipWorksec;
import com.velvol.salary.domain.SalaryResultDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/28.
 */
public interface SalaryResultDetailMapper {

    List<Map<String, Object>> queryResultDetailPage(SalaryResultDetail salaryResultDetail);

    List<Map<String, Object>> selectResultDetail(SalaryResultDetail salaryResultDetail);

    void startCalculate(Integer recordId);

    void startBjCalculate(Integer recordId);

    int updateStatus(SalaryResultDetail salaryResultDetail);

    int addResultOrder(SalaryResultDetail salaryResultDetail);

    List<SalaryResultDetail> selectIsMaterial(Integer recordId);



    int update(EquipWorker equipWorker);

    int save(EquipWorksec equipWorksec);

}
