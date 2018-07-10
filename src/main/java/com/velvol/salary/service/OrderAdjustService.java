package com.velvol.salary.service;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryOrderAdjust;

import java.io.File;

/**
 * Created by Administrator on 2018/4/21.
 */
public interface OrderAdjustService {

    String batchImport(File file, Integer recordId);

    PageInfo queryOrderAdjustPage(SalaryOrderAdjust salaryOrderAdjust);

    int updateOrderAdjust(SalaryOrderAdjust salaryOrderAdjust);
}
