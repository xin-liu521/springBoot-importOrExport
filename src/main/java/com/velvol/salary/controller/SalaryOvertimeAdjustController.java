package com.velvol.salary.controller;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryOvertimeAdjust;
import com.velvol.salary.service.SalaryOvertimeAdjustService;
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
@RequestMapping("/orderTimeAdjust")
public class SalaryOvertimeAdjustController {

    @Autowired
    SalaryOvertimeAdjustService salaryOvertimeAdjustService;


    /**
     * @desc 跳转导入容忍页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/orderTimeAdjustPage", method = RequestMethod.GET)
    public String orderTimeAdjustPage(Integer recordId, Map<String,Object> map) {
        map.put("recordId", recordId);
        return "orderform/orderTimeAdjust_list";
    }

    /**
     * @desc 导入容忍信息
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
                msg = salaryOvertimeAdjustService.batchImport(file1, recordId);
                msgList.add(msg);
            }
        }
        return ResultUtil.renderSuccess(msgList);
    }

    /**
     * @desc 查询容忍列表信息
     * @param salaryOvertimeAdjust
     * @return
     */
    @RequestMapping(value = "/queryOrderTimeAdjustPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryOrderTimeAdjustPage(SalaryOvertimeAdjust salaryOvertimeAdjust) {
        PageInfo<SalaryOvertimeAdjust> pageInfo = salaryOvertimeAdjustService.queryOrderTimeAdjustPage(salaryOvertimeAdjust);
        return ResultUtil.pageInfo(pageInfo);
    }
}
