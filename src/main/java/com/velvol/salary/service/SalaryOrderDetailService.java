package com.velvol.salary.service;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryOrderDetail;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/18.
 */
public interface SalaryOrderDetailService {

    String batchImport(File file, Integer recordId);

    PageInfo<SalaryOrderDetail> queryOrderDetailPage(SalaryOrderDetail salaryOrderDetail);

    List<Map<String, Object>> selectOrderDetail(Integer recordId);

    List<Map<String, Object>> selectStation(Integer regionId);

    String selectRegionById(Integer regionId);

    String ImportExcel(String file, Integer recordId) throws Exception;
}
