package com.velvol.salary.dao;


import com.velvol.salary.domain.Bankcardinfo;
import com.velvol.salary.domain.Worker;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WorkerMapper {

    Worker queryWorker(String workerId);

    int updateWorker(Worker worker);

    String selectUserRole(String userId);

    int queryOrderCount(@Param("riderId") String riderId, @Param("firstDate")String firstDate, @Param("lastDate")String lastDate);

    int registerWorker(Worker worker);

    int selectWorker(String referee);

    List<Map<String, Object>> queryRegion();

    List<Map<String, Object>> queryStation();

    int checkoutTelephone(String telephone);

    Map<String, Object> queryStationById(Long id);
}