package com.velvol.salary.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.velvol.salary.dao.SalaryOrderDetailMapper;
import com.velvol.salary.domain.SalaryOrderDetail;
import com.velvol.salary.domain.SalaryOrderDetailTwo;
import com.velvol.salary.service.IRowReader;
import com.velvol.salary.service.SalaryOrderDetailService;
import com.velvol.salary.util.Excel2003Reader;
import com.velvol.salary.util.Excel2007Reader;
import com.velvol.salary.util.ImportUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/18.
 */
@Service
public class SalaryOrderDetailServiceImpl implements SalaryOrderDetailService {

    // excel2003扩展名
    public static final String EXCEL03_EXTENSION = ".xls";
    // excel2007扩展名
    public static final String EXCEL07_EXTENSION = ".xlsx";

    @Autowired
    SalaryOrderDetailMapper uploadControllerMapper;

    @Override
    public String batchImport(File file, Integer recordId) {
        String excelName = file.getName();
        String errorMsg = excelName;//excel名称
        ImportUtil readExcel=new ImportUtil();
        Map<String, Object> map1 = readExcel.getExcelInfo(file);//创建工作薄Workbook对象
        Workbook wb = (Workbook) map1.get("wb");
        FormulaEvaluator formulaEvaluator = (FormulaEvaluator) map1.get("formulaEvaluator");
        Map<String, Object> map = readExcel.readExcelValue(wb);
        //总行数
        int totalRows = (int) map.get("totalRows");
        //总列数
        int totalCells = (int) map.get("totalCells");
        //获取sheet页
        Sheet sheet = (Sheet) map.get("sheet");
        SalaryOrderDetailTwo salaryOrderDetail;
        List<Map<String, Object>> customerList=new ArrayList<>();
        String br = "<br/>";
        //循环Excel行数,从第二行开始。标题不入库
        int flag = 0;
        for(int r=1;r<totalRows;r++){
            flag++;
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null) continue;
            salaryOrderDetail = new SalaryOrderDetailTwo();
            Field[] fields = salaryOrderDetail.getClass().getDeclaredFields();
            if (fields == null || fields.length < 1) {
                return null;
            }
            //容器
            Map<String, Object> params = new HashMap<>();
            //注意excel表格字段顺序要和obj字段顺序对齐 （如果有多余字段请另作特殊下标对应处理）
            for (int j = 0; j < fields.length; j++) {
                if (j == 0) {
                    params.put(fields[0].getName(), recordId);
                }else {
                    if (j == 16) {
                        String value =  ImportUtil.getVal(row.getCell(j - 1), formulaEvaluator);
                        String bracket = value.substring(0, 1);
                        if (bracket.equals("(")) {
                            String val = "-"+ value.replace("(", "").replace(")", "");
                            params.put(fields[j].getName(), val);
                        }else {
                            params.put(fields[j].getName(), value);
                        }
                    } else {
                        params.put(fields[j].getName(), ImportUtil.getVal(row.getCell(j - 1), formulaEvaluator));
                    }
                }

            }
            customerList.add(params);
            if(flag ==10000){
                //全部验证通过才导入到数据库
                uploadControllerMapper.insertBatch(customerList);
                customerList.clear();
                flag=0;//将标志重新设置为0
            }else if (r == totalRows- 1){
                uploadControllerMapper.insertBatch(customerList);
                errorMsg = "【"+excelName+"】导入成功，共"+(totalRows- 1)+"条数据！<br/>";
                customerList.clear();
            }
        }
        try{
            wb.close();
        }catch(IOException e){
            wb = null;
            e.printStackTrace();
        }
        return errorMsg;
    }

    @Override
    public PageInfo<SalaryOrderDetail> queryOrderDetailPage(SalaryOrderDetail salaryOrderDetail) {
        Integer pageNum = salaryOrderDetail.getPageNumber();
        Integer length = salaryOrderDetail.getPageSize();
        PageHelper.startPage(pageNum, length);
        List<SalaryOrderDetail> entityList = uploadControllerMapper.queryOrderDetailPage(salaryOrderDetail);
        PageInfo<SalaryOrderDetail> pageInfo = new PageInfo<>(entityList);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> selectOrderDetail(Integer recordId) {
        return uploadControllerMapper.selectOrderDetail(recordId);
    }

    @Override
    public List<Map<String, Object>> selectStation(Integer regionId) {
        return uploadControllerMapper.selectStation(regionId);
    }

    @Override
    public String selectRegionById(Integer regionId) {
        return uploadControllerMapper.selectRegionById(regionId);
    }

    @Override
    public String ImportExcel(String file, Integer recordId) throws Exception{
        File file1 = new File(file);
        String errorMsg = "";
        //IRowReader rowReader = new RowReader();
        try {
            List<Map<String, Object>> orderList = new ArrayList<>();
            if (file.endsWith(EXCEL03_EXTENSION)) { // 处理excel2003文件
                Excel2003Reader exceXls = new Excel2003Reader();
                //exceXls.setRowReader(rowReader);
                exceXls.setRecordId(recordId);
                exceXls.process(file);
                orderList = exceXls.getOrderList();
            } else if (file.endsWith(EXCEL07_EXTENSION)) { // 处理excel2007文件
                Excel2007Reader exceXlsx = new Excel2007Reader();
                //exceXlsx.setRowReader(rowReader);
                exceXlsx.setRecordId(recordId);
                exceXlsx.process(file);
                orderList = exceXlsx.getOrderList();
            } else {
                throw new Exception("文件格式错误，【"+file1.getName()+"】的扩展名只能是xls或xlsx。");
            }
            orderList.get(0).clear();
            uploadControllerMapper.insertBatch(orderList);
            errorMsg = "【"+file1.getName()+"】导入成功<br/>";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorMsg;
    }
}
