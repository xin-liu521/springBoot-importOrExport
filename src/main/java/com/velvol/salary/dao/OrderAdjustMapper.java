package com.velvol.salary.dao;

import com.velvol.salary.domain.SalaryOrderAdjust;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/21.
 */
public interface OrderAdjustMapper {

    int insertBatch(List<Map<String, Object>> params);

    List<SalaryOrderAdjust> queryOrderAdjustPage(SalaryOrderAdjust salaryOrderAdjust);

    int deleteOrderAdjust(Integer recordId);

    int selectAdjust(Map<String, Object> params);

    int insertAdjust(Map<String, Object> params);

    int updateAdjust(Map<String, Object> params);

    int updateOrderAdjust(SalaryOrderAdjust salaryOrderAdjust);
}
