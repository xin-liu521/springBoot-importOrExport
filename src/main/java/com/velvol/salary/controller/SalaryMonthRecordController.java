package com.velvol.salary.controller;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryMonthRecord;
import com.velvol.salary.domain.User;
import com.velvol.salary.service.SalaryMonthRecordService;
import com.velvol.salary.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/26.
 */
@Controller
@RequestMapping("/monthRecord")
public class SalaryMonthRecordController {

    @Autowired
    SalaryMonthRecordService salaryMonthRecordService;

    /**
     * @desc 订单分析页面跳转
     * @param map
     * @return
     */
    @RequestMapping(value = "/queryMonthRecordPage", method = RequestMethod.GET)
    public String queryMonthRecordPage(Map<String, Object> map, String id, HttpServletRequest request) {
        //User user = salaryMonthRecordService.selectUserById(id);
        if (id != null){
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
        }
        List<Map<String, Object>> regionList = salaryMonthRecordService.selectRegion();
        map.put("regionList", regionList);
        //map.put("regionId", user.getRegionid());
        return "/orderform/monthRecord_list";
    }

    /**
     * @desc 新增
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addMonthRecord", method = RequestMethod.POST)
    public Object addMonthRecord(String time, Integer regionId, HttpSession session){
        String userId = (String) session.getAttribute("id");
        int count = salaryMonthRecordService.addMonthRecord(time, regionId, userId);
        if (count > 0) {
            return ResultUtil.renderSuccess();
        }else {
            return ResultUtil.renderError();
        }
    }

    /**
     * @desc 校验月份是否存在
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isMonthRecord", method = RequestMethod.POST)
    public Object isMonthRecord(String time, Integer regionId){
        int count = salaryMonthRecordService.isMonthRecord(time, regionId);
        if (count > 0) {
            return ResultUtil.renderSuccess();
        }else {
            return ResultUtil.renderError();
        }
    }

    /**
     * @desc 查询列表信息
     * @param salaryMonthRecord
     * @return
     */
    @RequestMapping(value = "/queryMonthRecordList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryMonthRecordList(SalaryMonthRecord salaryMonthRecord, String yearMonth) {
        PageInfo pageInfo = salaryMonthRecordService.queryMonthRecordPage(salaryMonthRecord, yearMonth);
        return ResultUtil.pageInfo(pageInfo);
    }

    /**
     * @desc 查询站点
     * @param regionId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStation", method = RequestMethod.POST)
    public Object queryStation(Integer regionId){
        List<Map<String, Object>> params = salaryMonthRecordService.queryStation(regionId);
        return ResultUtil.renderSuccess(params);
    }

    /**
     * @desc 新增站点星级
     * @param recordId
     * @param level
     * @param checkVal
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addStationRank", method = RequestMethod.POST)
    public Object addStationRank(Integer recordId, Integer level, String checkVal){
        String[] stationIds =  checkVal.split(",");
        salaryMonthRecordService.deleteStationLevel(recordId);
        for (int i=0; i<stationIds.length; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("recordId", recordId);
            map.put("level", level);
            map.put("stationId", stationIds[i]);
            salaryMonthRecordService.addStationRank(map);
        }
        return ResultUtil.renderSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "/updateMonth", method = RequestMethod.POST)
    public Object updateMonth(SalaryMonthRecord salaryMonthRecord){
        int count = salaryMonthRecordService.updateMonth(salaryMonthRecord);
        if (count > 0) {
            return ResultUtil.renderSuccess();
        }else {
            return ResultUtil.renderError();
        }
    }
}
