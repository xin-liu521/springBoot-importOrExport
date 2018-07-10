package com.velvol.salary.service.impl;

import com.velvol.salary.dao.BankcardinfoMapper;
import com.velvol.salary.dao.MobileInterfaceMapper;
import com.velvol.salary.dao.TipinfoMapper;
import com.velvol.salary.dao.WorkerMapper;
import com.velvol.salary.domain.Bankcardinfo;
import com.velvol.salary.domain.Tipinfo;
import com.velvol.salary.domain.User;
import com.velvol.salary.domain.Worker;
import com.velvol.salary.service.MobileInterfaceService;
import com.velvol.salary.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/5/30.
 */
@Service
public class MobileInterfaceServiceImpl implements MobileInterfaceService {

    @Autowired
    MobileInterfaceMapper mobileInterfaceMapper;

    @Autowired
    WorkerMapper workerMapper;

    @Autowired
    BankcardinfoMapper bankcardinfoMapper;

    @Autowired
    TipinfoMapper tipinfoMapper;


    @Override
    public User selectUserByName(String userName) {
        return mobileInterfaceMapper.selectUserByName(userName);
    }

    @Override
    public int selectPersonCount(Integer regionId) {
        return mobileInterfaceMapper.selectPersonCount(regionId);
    }

    @Override
    public int selectEntryCount(Integer regionId) {
        String date = DateUtil.getBeforeDay();
        return mobileInterfaceMapper.selectEntryCount(date, regionId);
    }

    @Override
    public int selectLeaveCount(Integer regionId) {
        String date = DateUtil.getBeforeDay();
        return mobileInterfaceMapper.selectLeaveCount(date, regionId);
    }

    @Override
    public int selectOrderCount(Integer regionId) {
        String firstDate = DateUtil.formatDate(DateUtil.getFirstDayOfMonth(new Date()));
        String lastDate = DateUtil.formatDate(DateUtil.getLastDayOfMonth(new Date()));
        return mobileInterfaceMapper.selectOrderCount(firstDate, lastDate, regionId);
    }

    @Override
    public List<Map<String, Object>> queryRegionPersonCount() {
        return mobileInterfaceMapper.queryRegionPersonCount();
    }

    @Override
    public Map<Object, Object> queryDayPersonCount() {
        List<String> list = DateUtil.getDayListOfMonth();
        Map<Object, Object> params = new HashMap<>();
        for (int i=0; i<list.size();i++){
            String date = list.get(i);
            int count = mobileInterfaceMapper.queryDayPersonCount(date, null);
            params.put(i+1, count);
        }
        Calendar now = Calendar.getInstance();
        now.get(Calendar.DAY_OF_MONTH);
        params.put("nowDay", now.get(Calendar.DAY_OF_MONTH));
        return params;
    }

    @Override
    public List<Map<String, Object>> queryRegionOrderCount() {
        String firstDate = DateUtil.formatDate(DateUtil.getFirstDayOfMonth(new Date()));
        String lastDate = DateUtil.formatDate(DateUtil.getLastDayOfMonth(new Date()));
        return mobileInterfaceMapper.queryRegionOrderCount(firstDate, lastDate);
    }

    @Override
    public Map<Object, Object> queryDayOrderCount() {
        List<String> list = DateUtil.getDayListOfMonth();
        Map<Object, Object> params = new HashMap<>();
        for (int i=0; i<list.size();i++){
            String date = list.get(i);
            int count = mobileInterfaceMapper.queryDayOrderCount(date, null);
            params.put(i+1, count);
        }
        Calendar now = Calendar.getInstance();
        now.get(Calendar.DAY_OF_MONTH);
        params.put("nowDay", now.get(Calendar.DAY_OF_MONTH));
        return params;
    }

    @Override
    public List<Map<String, Object>> queryStationPersonCount(Integer regionId) {
        return mobileInterfaceMapper.queryStationPersonCount(regionId);
    }

    @Override
    public Map<Object, Object> queryDayStationPersonCount(Integer regionId) {
        List<String> list = DateUtil.getDayListOfMonth();
        Map<Object, Object> params = new HashMap<>();
        for (int i=0; i<list.size();i++){
            String date = list.get(i);
            int count = mobileInterfaceMapper.queryDayPersonCount(date, regionId);
            params.put(i+1, count);
        }
        Calendar now = Calendar.getInstance();
        now.get(Calendar.DAY_OF_MONTH);
        params.put("nowDay", now.get(Calendar.DAY_OF_MONTH));
        return params;
    }

    @Override
    public List<Map<String, Object>> queryStationOrderCount(Integer regionId) {
        String firstDate = DateUtil.formatDate(DateUtil.getFirstDayOfMonth(new Date()));
        String lastDate = DateUtil.formatDate(DateUtil.getLastDayOfMonth(new Date()));
        return mobileInterfaceMapper.queryStationOrderCount(firstDate, lastDate, regionId);
    }

    @Override
    public Map<Object, Object> queryDayStationOrderCount(Integer regionId) {
        List<String> list = DateUtil.getDayListOfMonth();
        Map<Object, Object> params = new HashMap<>();
        for (int i=0; i<list.size();i++){
            String date = list.get(i);
            int count = mobileInterfaceMapper.queryDayOrderCount(date, regionId);
            params.put(i+1, count);
        }
        Calendar now = Calendar.getInstance();
        now.get(Calendar.DAY_OF_MONTH);
        params.put("nowDay", now.get(Calendar.DAY_OF_MONTH));
        return params;
    }

    @Override
    public Worker queryWorker(String workerId) {
        return workerMapper.queryWorker(workerId);
    }

    @Override
    public int updateWorker(Worker worker) {
        return workerMapper.updateWorker(worker);
    }

    @Override
    public List<Bankcardinfo> queryBankInfo(String workerId) {
        return bankcardinfoMapper.queryBankInfo(workerId);
    }

    @Override
    public int addOrUpdateBankInfo(Bankcardinfo bankcardinfo, String workerId) {
        int count = 0;
        if (bankcardinfo.getId() == null){
            if (bankcardinfo.getFlag() == 0){
                bankcardinfo.setRelationship("本人卡");
            }
            bankcardinfo.setAddtime(new Date());
            bankcardinfo.setWorkid(workerId);
            count = bankcardinfoMapper.addBankInfo(bankcardinfo);
        }else {
            count = bankcardinfoMapper.updateBankInfo(bankcardinfo);
        }
        return count;
    }

    @Override
    public String selectUserRole(String userId) {
        return workerMapper.selectUserRole(userId);
    }

    @Override
    public User selectUserById(String userId) {
        return mobileInterfaceMapper.selectUserById(userId);
    }

    @Override
    public int updatePassword(User user) {
        return mobileInterfaceMapper.updatePassword(user);
    }

    @Override
    public int queryOrderCount(String riderId) {
        String firstDate = DateUtil.formatDate(DateUtil.getFirstDayOfMonth(new Date()));
        String lastDate = DateUtil.formatDate(DateUtil.getLastDayOfMonth(new Date()));
        return workerMapper.queryOrderCount(riderId, firstDate, lastDate);
    }

    @Override
    public int registerWorker(Worker worker) {
        worker.setType(0);
        worker.setState(0);
        worker.setReqtime(new Date());
        if (worker.getInfofrom().equals("熟人介绍") || worker.getInfofrom().equals("二次入职")) {
            int flag = workerMapper.selectWorker(worker.getReferee());
            if(flag > 0) {
                worker.setRecruittype(1);
            }else {
                worker.setRecruittype(0);
            }
        }else {
            worker.setRecruittype(0);
        }
        return workerMapper.registerWorker(worker);
    }

    @Override
    public List<Map<String, Object>> queryRegion() {
        return workerMapper.queryRegion();
    }

    @Override
    public List<Map<String, Object>> queryStation() {
        return workerMapper.queryStation();
    }

    @Override
    public int checkoutTelephone(String telephone) {
        return workerMapper.checkoutTelephone(telephone);
    }

    @Override
    public Tipinfo queryTipinfo(Worker worker) {
        Tipinfo tipinfo;
        Map<String, Object> params = new HashMap<>();
        if(worker.getStationId() == -1){
            params.put("regionid", worker.getRegionId());
            tipinfo = tipinfoMapper.queryTipinfo(params);
        }else {
            Map<String, Object> station = workerMapper.queryStationById(worker.getStationId());
            params.put("name", station.get("name"));
            tipinfo = tipinfoMapper.queryTipinfo(params);
        }
        return tipinfo;
    }
}
