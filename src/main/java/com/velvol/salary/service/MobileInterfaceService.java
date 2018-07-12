package com.velvol.salary.service;

import com.velvol.salary.domain.Bankcardinfo;
import com.velvol.salary.domain.Tipinfo;
import com.velvol.salary.domain.User;
import com.velvol.salary.domain.Worker;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/30.
 */
public interface MobileInterfaceService {

    User selectUserByName(String userName);

    int selectPersonCount(Integer regionId);

    int selectEntryCount(Integer regionId);

    int selectLeaveCount(Integer regionId);

    int selectOrderCount(Integer regionId);

    List<Map<String, Object>> queryRegionPersonCount();

    Map<Object, Object> queryDayPersonCount();

    List<Map<String, Object>> queryRegionOrderCount();

    Map<Object, Object> queryDayOrderCount();

    List<Map<String, Object>> queryStationPersonCount(Integer regionId);

    Map<Object, Object> queryDayStationPersonCount(Integer regionId);

    List<Map<String, Object>> queryStationOrderCount(Integer regionId);

    Map<Object, Object> queryDayStationOrderCount(Integer regionId);

    Worker queryWorker(String workerId);

    int updateWorker(Worker worker);

    List<Bankcardinfo> queryBankInfo(String workerId);

    int addOrUpdateBankInfo(Bankcardinfo bankcardinfo, String workerId);

    String selectUserRole(String userId);

    User selectUserById(String userId);

    int updatePassword(User user);

    int queryOrderCount(String riderId);

    int registerWorker(Worker worker);

    List<Map<String, Object>> queryRegion();

    List<Map<String, Object>> queryStation();

    int checkoutTelephone(String telephone);

    Tipinfo queryTipinfo(Worker worker);
}
