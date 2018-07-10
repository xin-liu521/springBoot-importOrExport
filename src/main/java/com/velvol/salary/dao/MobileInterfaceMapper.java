package com.velvol.salary.dao;

import com.velvol.salary.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/30.
 */
public interface MobileInterfaceMapper {

    User selectUserByName(String userName);

    int selectPersonCount(@Param("regionId")Integer regionId);

    int selectEntryCount(@Param("date")String date, @Param("regionId")Integer regionId);

    int selectLeaveCount(@Param("date")String date, @Param("regionId")Integer regionId);

    int selectOrderCount(@Param("firstDate")String firstDate, @Param("lastDate") String lastDate, @Param("regionId") Integer regionId);

    List<Map<String, Object>> queryRegionPersonCount();

    int queryDayPersonCount(@Param("date")String date, @Param("regionId") Integer regionId);

    List<Map<String, Object>> queryRegionOrderCount(@Param("firstDate")String firstDate, @Param("lastDate") String lastDate);

    int queryDayOrderCount(@Param("date")String date, @Param("regionId") Integer regionId);

    List<Map<String, Object>> queryStationPersonCount(Integer regionId);

    List<Map<String, Object>> queryStationOrderCount(@Param("firstDate")String firstDate, @Param("lastDate") String lastDate, @Param("regionId")Integer regionId);

    User selectUserById(String userId);

    int updatePassword(User user);
}
