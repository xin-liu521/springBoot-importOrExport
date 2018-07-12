package com.velvol.salary.controller;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.SalaryMonthRecord;
import com.velvol.salary.domain.SalaryOrderDetail;
import com.velvol.salary.domain.SalaryOrderDetailTwo;
import com.velvol.salary.service.SalaryOrderDetailService;
import com.velvol.salary.util.ExportExcelUtils;
import com.velvol.salary.util.ImportUtil;
import com.velvol.salary.util.ResultUtil;
import com.velvol.salary.util.UnZipFiles;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/16.
 */
@Controller
@RequestMapping("/salaryOrder")
public class SalaryOrderDetailController {


    @Autowired
    private SalaryOrderDetailService uploadControllerService;

    /**
     * @desc 跳转导入订单页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/importExcelPage", method = RequestMethod.GET)
    public String importExcelPage(SalaryMonthRecord salaryMonthRecord, Map<String,Object> map) {
        List<Map<String, Object>> stationList =  uploadControllerService.selectStation(salaryMonthRecord.getRegion());
        String regionName = uploadControllerService.selectRegionById(salaryMonthRecord.getRegion());
        map.put("recordId", salaryMonthRecord.getId());
        map.put("year", salaryMonthRecord.getYear());
        map.put("month", salaryMonthRecord.getMonth());
        map.put("stationList", stationList);
        map.put("regionName", regionName);
        return "orderform/orderForm_list";
    }

    /**
     * @desc 导入订单信息
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
                msg = uploadControllerService.batchImport(file1, recordId);
                //msg = uploadControllerService.ImportExcel(unPath.get(i), recordId);
                msgList.add(msg);
                file1.delete();
            }
        }
        targetFile.delete();
        if (msgList.size() != 0) {
            return ResultUtil.renderSuccess(msgList);
        }else {
            return ResultUtil.renderError("系统异常！");
        }

    }

    /**
     * @desc 查询订单列表信息
     * @param salaryOrderDetail
     * @return
     */
    @RequestMapping(value = "/queryOrderDetailPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryOrderDetailPage(SalaryOrderDetail salaryOrderDetail) {
        PageInfo<SalaryOrderDetail> pageInfo = uploadControllerService.queryOrderDetailPage(salaryOrderDetail);
        return ResultUtil.pageInfo(pageInfo);
    }


    /**
     * @desc 导出
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportOrderDetail", method = RequestMethod.GET)
    public void exportOrderDetail(HttpServletResponse response, Integer recordId) throws Exception {

        List<Map<String, Object>> orderDetailList = uploadControllerService.selectOrderDetail(recordId);
        //导出文件的标题
        String title = "订单信息列表";
        //设置表格标题行
        String[] headers = new String[]{"序号", "姓名", "区域", "站点", "卡号", "持卡人", "持卡人电话", "开户行"};
        String[] names = new String[]{"id", "order_id", "waybill_id", "seller_num", "seller_name", "seller_id", "city", "rider_name"};
//        List<List<Map<String, Object>>> ArrayDataList = new ArrayList<>();
//        List<String> excelTitle = new ArrayList<>();
//        if (orderDetailList != null && orderDetailList.size() > 0) {//查询的数据不为空就对数据进行导出
//            ArrayDataList.add(orderDetailList);
//            excelTitle.add("安远门");
//        }
        //ExportExcelUtils.exportExcel(title, headers, orderDetailList, names, response);
    }

}
