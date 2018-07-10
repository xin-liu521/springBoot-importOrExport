package com.velvol.salary.service;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryUserInfo;

import java.io.File;

/**
 * Created by Administrator on 2018/4/21.
 */
public interface SalaryUserInfoService {

    String batchImport(File file, Integer recordId);

    PageInfo querySalaryUserInfoPage(SalaryUserInfo salaryUserInfo);

    String ImportExcel(String file, Integer recordId)throws Exception;

    int updateUserInfo(SalaryUserInfo salaryUserInfo);
}
