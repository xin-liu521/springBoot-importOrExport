package com.velvol.salary.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.velvol.salary.dao.OrderAdjustMapper;
import com.velvol.salary.domain.SalaryOrderAdjust;
import com.velvol.salary.domain.SalaryOrderAdjustTwo;
import com.velvol.salary.service.OrderAdjustService;
import com.velvol.salary.util.DoubleUtil;
import com.velvol.salary.util.ImportUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/21.
 */
@Service
public class OrderAdjustServiceImpl implements OrderAdjustService {

    @Autowired
    OrderAdjustMapper orderAdjustMapper;

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
        SalaryOrderAdjustTwo salaryOrderAdjust;
        String br = "<br/>";
        //循环Excel行数,从第二行开始。标题不入库
        for(int r=1;r<totalRows;r++){
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null) continue;
            salaryOrderAdjust = new SalaryOrderAdjustTwo();
            Field[] fields = salaryOrderAdjust.getClass().getDeclaredFields();
            if (fields == null || fields.length < 1) {
                return null;
            }
            //容器
            Map<String, Object> params = new HashMap<>();
            Cell ce =  row.getCell(0);
            if (ce != null && ce.getCellType() != Cell.CELL_TYPE_BLANK) {
//                if (ce.getCellType() != Cell.CELL_TYPE_BLANK) {
                    //注意excel表格字段顺序要和obj字段顺序对齐 （如果有多余字段请另作特殊下标对应处理）
                    for (int j = 0; j < fields.length; j++) {
                        if (j == 0) {
                            params.put(fields[0].getName(), recordId);
                        }else {
                            Cell cell =  row.getCell(j-1);
                            if (cell != null ){
                                if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                                    if (j == 1){
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        params.put(fields[j].getName(), value);
                                    }else if (j == 3){
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        params.put(fields[j].getName(), value);
                                    }else if (j == 16 || j == 17 || j == 18 || j == 19 || j == 20 || j == 21 ) {
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        if (value.equals("")) {
                                            params.put(fields[j].getName(), null);
                                        }else {
                                            Double valueDb = DoubleUtil.mul(Double.valueOf(value), 100d);
                                            params.put(fields[j].getName(), valueDb);
                                        }
                                    }else if (j == 8 || j == 9 || j == 10) {
                                        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                            DecimalFormat df2 = new DecimalFormat("##.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
                                            df2.setRoundingMode(RoundingMode.DOWN);
                                            String value = df2.format(cell.getNumericCellValue());
                                            params.put(fields[j].getName(), value);
                                        }
                                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                                            String value = cell.getRichStringCellValue().getString();
                                            params.put(fields[j].getName(), value);
                                        }

                                    }else {
                                        params.put(fields[j].getName(), ImportUtil.getVal(cell, formulaEvaluator));
                                    }
                                }

                            }
                        }
                    }
//                }
                customerList.add(params);
            }


            //拼接每行的错误提示
            if(!StringUtils.isEmpty(rowMessage)){
                errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
            }
        }
        //全部验证通过才导入到数据库
        if(excelName.equals(errorMsg)&& customerList.size()>0){
            for (int i=0;i<customerList.size();i++){
                Map<String, Object> params = customerList.get(i);
                int count = orderAdjustMapper.selectAdjust(params);
                if (count == 0) {
                    orderAdjustMapper.insertAdjust(params);
                }else {
                    orderAdjustMapper.updateAdjust(params);
                }
            }
           // orderAdjustMapper.insertBatch(customerList);
            errorMsg = "【"+excelName+"】导入成功，共"+customerList.size()+"条数据！<br/>";
        }else {
            errorMsg = "【"+excelName+"】导入失败";
        }
        return errorMsg;
    }

    @Override
    public PageInfo queryOrderAdjustPage(SalaryOrderAdjust salaryOrderAdjust) {
        Integer pageNum = salaryOrderAdjust.getPageNumber();
        Integer length = salaryOrderAdjust.getPageSize();
        PageHelper.startPage(pageNum, length);
        List<SalaryOrderAdjust> entityList = orderAdjustMapper.queryOrderAdjustPage(salaryOrderAdjust);
        PageInfo<SalaryOrderAdjust> pageInfo = new PageInfo<>(entityList);
        return pageInfo;
    }

    @Override
    public int updateOrderAdjust(SalaryOrderAdjust salaryOrderAdjust) {
        return orderAdjustMapper.updateOrderAdjust(salaryOrderAdjust);
    }
}
