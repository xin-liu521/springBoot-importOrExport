package com.velvol.salary.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.velvol.salary.dao.*;
import com.velvol.salary.domain.*;
import com.velvol.salary.service.SalaryResultDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/28.
 */
@Service
public class SalaryResultDetailServiceImpl implements SalaryResultDetailService {

    @Autowired
    SalaryResultDetailMapper salaryResultDetailMapper;

    @Autowired
    SalaryBjResultDetailMapper salaryBjResultDetailMapper;

    @Autowired
    EquipWorkerMapper equipWorkerMapper;

    @Autowired
    EquipWorksecMapper equipWorksecMapper;

    @Autowired
    SalaryMonthRecordMapper salaryMonthRecordMapper;

    @Override
    public PageInfo queryResultDetailPage(SalaryResultDetail salaryResultDetail) {
        Integer pageNum = salaryResultDetail.getPageNumber();
        Integer length = salaryResultDetail.getPageSize();
        PageHelper.startPage(pageNum, length);
        List<Map<String, Object>> entityList = salaryResultDetailMapper.queryResultDetailPage(salaryResultDetail);
        PageInfo pageInfo = new PageInfo<>(entityList);
        return pageInfo;
    }

    @Override
    public PageInfo queryNewResultDetailPage(SalaryBjResultDetail salaryBjResultDetail) {
        Integer pageNum = salaryBjResultDetail.getPageNumber();
        Integer length = salaryBjResultDetail.getPageSize();
        PageHelper.startPage(pageNum, length);
        List<Map<String, Object>> entityList = salaryBjResultDetailMapper.queryNewResultDetailPage(salaryBjResultDetail, 0);
        PageInfo pageInfo = new PageInfo<>(entityList);
        return pageInfo;
    }

    @Override
    public PageInfo queryOldResultDetailPage(SalaryBjResultDetail salaryBjResultDetail) {
        Integer pageNum = salaryBjResultDetail.getPageNumber();
        Integer length = salaryBjResultDetail.getPageSize();
        PageHelper.startPage(pageNum, length);
        List<Map<String, Object>> entityList = salaryBjResultDetailMapper.queryNewResultDetailPage(salaryBjResultDetail, 1);
        PageInfo pageInfo = new PageInfo<>(entityList);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> selectResultDetail(SalaryResultDetail salaryResultDetail) {
        return salaryResultDetailMapper.selectResultDetail(salaryResultDetail);
    }

    @Override
    public void startCalculate(Integer recordId, Integer regionId) {
        if (regionId != null) {
            if (regionId == 1){
                salaryResultDetailMapper.startCalculate(recordId);
            }
            if (regionId == 2){
                salaryResultDetailMapper.startBjCalculate(recordId);
            }
        }

    }

    @Override
    public int updateStatus(SalaryResultDetail salaryResultDetail, Integer[] ids) {
        int count = 0;
        for (int i=0;i<ids.length;i++){
            salaryResultDetail.setId(ids[i]);
            int num = salaryResultDetailMapper.updateStatus(salaryResultDetail);
            if (num > 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int addResultOrder(SalaryResultDetail salaryResultDetail) {
        return salaryResultDetailMapper.addResultOrder(salaryResultDetail);
    }

    @Override
    public List<Map<String, Object>> selectBjResultDetail(SalaryBjResultDetail salaryBjResultDetail) {
        return salaryBjResultDetailMapper.queryNewResultDetailPage(salaryBjResultDetail, 0);
    }

    @Override
    public List<Map<String, Object>> selectBjOldResultDetail(SalaryBjResultDetail salaryBjResultDetail) {
        return salaryBjResultDetailMapper.queryNewResultDetailPage(salaryBjResultDetail, 1);
    }

    @Override
    public void notarizeOrder(Integer recordId) {
        salaryMonthRecordMapper.updateMonthRecord(recordId);
        List<SalaryResultDetail> salaryResultDetailList =  salaryResultDetailMapper.selectIsMaterial(recordId);
        if (salaryResultDetailList.size() > 0) {
            for (int i=0; i< salaryResultDetailList.size(); i++){
                SalaryResultDetail salaryResultDetail = salaryResultDetailList.get(i);
                EquipWorker equipWork = equipWorkerMapper.findById(salaryResultDetail.getRiderId());
                if (equipWork != null) {
                    Double total = equipWork.getTotal();//总价
                    Double deduction =equipWork.getDeduction();//已扣除金额

                    EquipWorksec equipworksec = new EquipWorksec();
                    equipworksec.setEquipworkid(equipWork.getId());
                    equipworksec.setKftime(new Date());
                    //equipworksec.setUsername(user.getUsername());

                    if(equipWork.getStatus() == 0){//总额的扣费50%
                        equipWork.setDeduction(deduction + salaryResultDetail.getEquipBuyPrice());
                        equipWork.setCount(equipWork.getCount()+1);
                        equipWork.setStatus(1);

                        equipworksec.setFee(Double.valueOf(salaryResultDetail.getEquipBuyPrice()));
                    }
                    else if(equipWork.getStatus() == 1){//扣除：总价-已扣除金额
                        equipWork.setDeduction(total);
                        equipWork.setCount(equipWork.getCount()+1);
                        equipWork.setStatus(2);

                        equipworksec.setFee(total-deduction);
                    }
                    equipWorkerMapper.updateByPrimaryKeySelective(equipWork);

                    //处理子表
                    equipWorksecMapper.insert(equipworksec);
                }

            }
        }
    }
}
