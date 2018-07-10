package com.velvol.salary.dao;

import com.velvol.salary.domain.SalaryUserInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/21.
 */
public interface SalaryUserInfoMapper {

    int insertBatch(List<Map<String, Object>> params);

    List<SalaryUserInfo> querySalaryUserInfoPage(SalaryUserInfo salaryUserInfo);

    int deleteUserInfo(Integer recordId);

    int selectUserInfo(Map<String, Object> params);

    int insertUserInfo(Map<String, Object> params);

    int updateUserInfo(Map<String, Object> params);

    int editUserInfo(SalaryUserInfo salaryUserInfo);
}
