package com.velvol.salary.dao;

import com.velvol.salary.domain.SalaryOvertimeAdjust;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/21.
 */
public interface SalaryOvertimeAdjustMapper {

    int insertBatch(List<Map<String, Object>> params);

    List<SalaryOvertimeAdjust> queryOrderTimeAdjustPage(SalaryOvertimeAdjust salaryOvertimeAdjust);
}
