package com.velvol.salary.dao;

import com.velvol.salary.domain.SalaryBjResultDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/22.
 */
public interface SalaryBjResultDetailMapper {

    List<Map<String, Object>> queryNewResultDetailPage(@Param("salaryBjResultDetail")SalaryBjResultDetail salaryBjResultDetail, @Param("isNewRider")Integer isNewRider);

}
