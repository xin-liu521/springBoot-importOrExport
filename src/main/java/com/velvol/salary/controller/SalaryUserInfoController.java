package com.velvol.salary.controller;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryMonthRecord;
import com.velvol.salary.domain.SalaryOvertimeAdjust;
import com.velvol.salary.domain.SalaryUserInfo;
import com.velvol.salary.service.SalaryOrderDetailService;
import com.velvol.salary.service.SalaryUserInfoService;
import com.velvol.salary.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/21.
 */
@Controller
@RequestMapping("/salaryUserInfo")
public class SalaryUserInfoController {

    @Autowired
    SalaryUserInfoService salaryUserInfoService;

    @Autowired
    private SalaryOrderDetailService uploadControllerService;

    /**
     * @desc 跳转导入考勤页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/salaryUserInfoPage", method = RequestMethod.GET)
    public String salaryUserInfoPage(SalaryMonthRecord salaryMonthRecord, Map<String,Object> map) {
        List<Map<String, Object>> stationList =  uploadControllerService.selectStation(salaryMonthRecord.getRegion());
        String regionName = uploadControllerService.selectRegionById(salaryMonthRecord.getRegion());
        map.put("recordId", salaryMonthRecord.getId());
        map.put("year", salaryMonthRecord.getYear());
        map.put("month", salaryMonthRecord.getMonth());
        map.put("stationList", stationList);
        map.put("regionName", regionName);
        return "orderform/salaryUserInfo_list";
    }

    /**
     * @desc 导入考勤信息
     * @param file
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/batchImport", method = RequestMethod.POST)
    public Object batchImport(@RequestParam("file") MultipartFile file, Integer recordId) throws Exception {
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
                msg = salaryUserInfoService.batchImport(file1, recordId);

                //salaryUserInfoService.ImportExcel(unPath.get(i), recordId);
                msgList.add(msg);
                file1.delete();
            }

        } targetFile.delete();
        return ResultUtil.renderSuccess(msgList);
    }

    /**
     * @desc 查询考勤列表信息
     * @param salaryUserInfo
     * @return
     */
    @RequestMapping(value = "/querySalaryUserInfoPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> querySalaryUserInfoPage(SalaryUserInfo salaryUserInfo) {
        PageInfo<SalaryUserInfo> pageInfo = salaryUserInfoService.querySalaryUserInfoPage(salaryUserInfo);
        return ResultUtil.pageInfo(pageInfo);
    }
    @ResponseBody
    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response, Integer flag){
        if (flag == 0) {
//            //获取文件的路径
//          String excelPath = request.getSession().getServletContext().getRealPath("/excelTemplate/"+"人效.xlsx");
            String result = DownLoadFileUtil.downloadFile(response, "各类统计.xlsx");
//            File file = new File("D:/excelTemplate/人效.xlsx");
//            ExportExcelUtils.downloadFile(file, response, false);
        }
        if (flag == 1) {
//            File file = new File("D:/excelTemplate/考勤.xlsx");
//            ExportExcelUtils.downloadFile(file, response, false);
            String result = DownLoadFileUtil.downloadFile(response, "考勤.xlsx");
        }
    }

    /**
     * @desc 修改考勤信息
     * @param salaryUserInfo
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserInfo(SalaryUserInfo salaryUserInfo){
        salaryUserInfoService.updateUserInfo(salaryUserInfo);
        return ResultUtil.renderSuccess();
    }
}
