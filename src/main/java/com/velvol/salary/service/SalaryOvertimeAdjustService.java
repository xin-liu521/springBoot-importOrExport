package com.velvol.salary.service;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryOvertimeAdjust;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */
public interface SalaryOvertimeAdjustService {

    String batchImport(File file, Integer recordId);

    PageInfo queryOrderTimeAdjustPage(SalaryOvertimeAdjust salaryOvertimeAdjust);
}
