package com.velvol.salary.dao;

import com.velvol.salary.domain.SalaryOrderDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/19.
 */
public interface SalaryOrderDetailMapper {

    int insertBatch(List<Map<String, Object>> params);

    List<SalaryOrderDetail> queryOrderDetailPage(SalaryOrderDetail salaryOrderDetail);

    List<Map<String, Object>> selectOrderDetail(Integer recordId);

    List<Map<String, Object>> selectStation(Integer regionId);

    int deleteOrderDetail(Integer recordId);

    String selectRegionById(Integer regionId);

    int insertOrder(Map<String, Object> params);
}
