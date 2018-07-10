package com.velvol.salary.controller;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryMonthRecord;
import com.velvol.salary.domain.SalaryOrderAdjust;
import com.velvol.salary.service.OrderAdjustService;
import com.velvol.salary.service.SalaryOrderDetailService;
import com.velvol.salary.util.ImportUtil;
import com.velvol.salary.util.ResultUtil;
import com.velvol.salary.util.UnZipFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/21.
 */
@Controller
@RequestMapping("/orderAdjust")
public class OrderAdjustController {

    @Autowired
    OrderAdjustService orderAdjustService;

    @Autowired
    private SalaryOrderDetailService uploadControllerService;

    /**
     * @desc 跳转导入人效页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/orderAdjustPage", method = RequestMethod.GET)
    public String orderAdjustPage(SalaryMonthRecord salaryMonthRecord, Map<String,Object> map) {
        List<Map<String, Object>> stationList =  uploadControllerService.selectStation(salaryMonthRecord.getRegion());
        String regionName = uploadControllerService.selectRegionById(salaryMonthRecord.getRegion());
        map.put("recordId", salaryMonthRecord.getId());
        map.put("year", salaryMonthRecord.getYear());
        map.put("month", salaryMonthRecord.getMonth());
        map.put("stationList", stationList);
        map.put("regionName", regionName);
        return "orderform/orderAdjust_list";
    }


    /**
     * @desc 导入人效信息
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/batchImport", method = RequestMethod.POST)
    public Object batchImport(@RequestParam("file") MultipartFile file, Integer recordId) throws IOException {
        //获取file上传地址
        File targetFile = ImportUtil.uploadService(file);
        List<String> msgList = new ArrayList<>();
        //进一步判断文件是否为空  解压zip
        if(!("").equals(targetFile)){
            //解压zip
            List<String> unPath = UnZipFiles.unZip(targetFile);
            for (int i=0; i<unPath.size();i++){
                String msg = "";
                File file1 = new File(unPath.get(i));
                msg = orderAdjustService.batchImport(file1, recordId);
                msgList.add(msg);
                file1.delete();
            }
        }
        targetFile.delete();
        return ResultUtil.renderSuccess(msgList);
    }

    /**
     * @desc 查询人效列表信息
     * @param salaryOrderAdjust
     * @return
     */
    @RequestMapping(value = "/queryOrderAdjustPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryOrderAdjustPage(SalaryOrderAdjust salaryOrderAdjust) {
        PageInfo pageInfo = orderAdjustService.queryOrderAdjustPage(salaryOrderAdjust);
        return ResultUtil.pageInfo(pageInfo);
    }

    /**
     * @desc 修改各类统计
     * @param salaryOrderAdjust
     * @return
     */
    @RequestMapping(value = "/updateOrderAdjust", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrderAdjust(SalaryOrderAdjust salaryOrderAdjust){
        orderAdjustService.updateOrderAdjust(salaryOrderAdjust);
        return ResultUtil.renderSuccess();
    }
}
