package com.velvol.salary.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.velvol.salary.dao.SalaryOvertimeAdjustMapper;
import com.velvol.salary.domain.SalaryOvertimeAdjust;
import com.velvol.salary.domain.SalaryOvertimeAdjustTwo;
import com.velvol.salary.service.SalaryOvertimeAdjustService;
import com.velvol.salary.util.ImportUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/21.
 */
@Service
public class SalaryOvertimeAdjustServiceImpl implements SalaryOvertimeAdjustService {

    @Autowired
    SalaryOvertimeAdjustMapper salaryOvertimeAdjustMapper;

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
        List<Map<String, Object>> customerList=new ArrayList<>();
        SalaryOvertimeAdjustTwo salaryOvertimeAdjust;
        String br = "<br/>";
        //循环Excel行数,从第二行开始。标题不入库
        for(int r=1;r<totalRows;r++){
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null) continue;
            salaryOvertimeAdjust = new SalaryOvertimeAdjustTwo();
            Field[] fields = salaryOvertimeAdjust.getClass().getDeclaredFields();
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
                    params.put(fields[j].getName(), ImportUtil.getVal(row.getCell(j-1), formulaEvaluator));
                }

            }
            //循环Excel的列
//            for(int c = 0; c <totalCells; c++){
//                Cell cell = row.getCell(c);
//                if (null != cell){
//                    if(c==0){
//                        String orderId = cell.getStringCellValue();
//                        if(StringUtils.isEmpty(orderId)){
//                            rowMessage += "问题不能为空；";
//                        }
//                        salaryOrderDetail.setOrderId(orderId);
//                    }

//                }else{
//                    rowMessage += "第"+(c+1)+"列数据有问题，请仔细检查；";
//                }
//
//            }
            customerList.add(params);
            //拼接每行的错误提示
            if(!StringUtils.isEmpty(rowMessage)){
                errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
            }
        }
        //全部验证通过才导入到数据库
        if(excelName.equals(errorMsg)){
            salaryOvertimeAdjustMapper.insertBatch(customerList);
            errorMsg = "【"+excelName+"】导入成功，共"+customerList.size()+"条数据！<br/>";
        }
        return errorMsg;
    }

    @Override
    public PageInfo queryOrderTimeAdjustPage(SalaryOvertimeAdjust salaryOvertimeAdjust) {
        Integer pageNum = salaryOvertimeAdjust.getPageNumber();
        Integer length = salaryOvertimeAdjust.getPageSize();
        PageHelper.startPage(pageNum, length);
        List<SalaryOvertimeAdjust> entityList = salaryOvertimeAdjustMapper.queryOrderTimeAdjustPage(salaryOvertimeAdjust);
        PageInfo<SalaryOvertimeAdjust> pageInfo = new PageInfo<>(entityList);
        return pageInfo;
    }
}
