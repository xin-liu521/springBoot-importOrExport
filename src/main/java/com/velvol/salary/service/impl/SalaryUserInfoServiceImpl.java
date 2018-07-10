package com.velvol.salary.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.velvol.salary.dao.SalaryUserInfoMapper;
import com.velvol.salary.domain.SalaryUserInfo;
import com.velvol.salary.domain.SalaryUserInfoTwo;
import com.velvol.salary.service.IRowReader;
import com.velvol.salary.service.SalaryUserInfoService;
import com.velvol.salary.util.DoubleUtil;
import com.velvol.salary.util.Excel2007Reader;
import com.velvol.salary.util.ImportUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/21.
 */
@Service
public class SalaryUserInfoServiceImpl implements SalaryUserInfoService {

    @Autowired
    SalaryUserInfoMapper salaryUserInfoMapper;

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
        SalaryUserInfoTwo salaryUserInfo;
        String br = "<br/>";
        //循环Excel行数,从第二行开始。标题不入库
        for(int r=1;r<totalRows;r++){
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null) continue;
            salaryUserInfo = new SalaryUserInfoTwo();
            Field[] fields = salaryUserInfo.getClass().getDeclaredFields();
            if (fields == null || fields.length < 1) {
                return null;
            }
            //容器
            Map<String, Object> params = new HashMap<>();
            Cell cellOne =  row.getCell(0);
            if (cellOne != null && cellOne.getCellType() != Cell.CELL_TYPE_BLANK) {
                    //注意excel表格字段顺序要和obj字段顺序对齐 （如果有多余字段请另作特殊下标对应处理）
                    for (int j = 0; j < fields.length; j++) {
                        if (j == 0) {
                            params.put(fields[0].getName(), recordId);
                        }else {
                            Cell cell =  row.getCell(j-1);
                            if (cell != null){
                                if (cell.getCellType() != Cell.CELL_TYPE_BLANK ) {
                                    if (j == 1){
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        params.put(fields[j].getName(), value);
                                    }else if (j == 3){
                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        params.put(fields[j].getName(), value);
                                    }else if (j == 5 || j == 6) {
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        if (value.equals("")) {
                                            params.put(fields[j].getName(), null);
                                        }else {
                                            params.put(fields[j].getName(), value);
                                        }
                                    }else if (j == 7) {
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        if (replaceBlank(value).equals("是")) {
                                            params.put(fields[j].getName(), "0");
                                        }else if (replaceBlank(value).equals("否")) {
                                            params.put(fields[j].getName(), "1");
                                        }else{
                                            rowMessage += "第"+(j)+"列数据有问题，请仔细检查；";
                                        }
                                    }else if (j == 13) {
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        if (replaceBlank(value).equals("是")) {
                                            params.put(fields[j].getName(), "1");
                                        }else if (replaceBlank(value).equals("否")) {
                                            params.put(fields[j].getName(), "0");
                                        }else{
                                            rowMessage += "第"+(j)+"列数据有问题，请仔细检查；";
                                        }
                                    }else if (j == 14) {
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        if (replaceBlank(value).equals("是")) {
                                            params.put(fields[j].getName(), "1");
                                        }else if (replaceBlank(value).equals("否")) {
                                            params.put(fields[j].getName(), "0");
                                        }else{
                                            rowMessage += "第"+(j)+"列数据有问题，请仔细检查；";
                                        }
                                    }else if (j == 15 || j == 16 || j == 17 || j == 18 ) {
                                        String value = ImportUtil.getVal(cell, formulaEvaluator);
                                        if (value.equals("")) {
                                            params.put(fields[j].getName(), null);
                                        }else {
                                            Double valueDb = DoubleUtil.mul(Double.valueOf(value), 100d);
                                            params.put(fields[j].getName(), valueDb);
                                        }

                                    }else {
                                        params.put(fields[j].getName(), ImportUtil.getVal(cell, formulaEvaluator));
                                    }
                                }

                            }

                        }

                }
                customerList.add(params);
            }


            //拼接每行的错误提示
            if(!StringUtils.isEmpty(rowMessage)){
                errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
            }
        }
        //全部验证通过才导入到数据库
        if(excelName.equals(errorMsg)){
            for (int i=0;i<customerList.size();i++){
                Map<String, Object> params = customerList.get(i);
                int count = salaryUserInfoMapper.selectUserInfo(params);
                if (count == 0) {
                    salaryUserInfoMapper.insertUserInfo(params);
                }else {
                    salaryUserInfoMapper.updateUserInfo(params);
                }
            }
            //salaryUserInfoMapper.insertBatch(customerList);
            errorMsg = "【"+excelName+"】:导入成功，共"+customerList.size()+"条数据！<br/>";
        }

        return errorMsg;
    }

    /*
      * 去除数据的空格、回车、换行符、制表符
      */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            //空格\t、回车\n、换行符\r、制表符\t
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    @Override
    public PageInfo querySalaryUserInfoPage(SalaryUserInfo salaryUserInfo) {
        Integer pageNum = salaryUserInfo.getPageNumber();
        Integer length = salaryUserInfo.getPageSize();
        PageHelper.startPage(pageNum, length);
        List<SalaryUserInfo> entityList = salaryUserInfoMapper.querySalaryUserInfoPage(salaryUserInfo);
        PageInfo<SalaryUserInfo> pageInfo = new PageInfo<>(entityList);
        return pageInfo;
    }


    /**
     * @desc 测试
     * @param file
     * @param recordId
     * @return
     */
    @Override
    public String ImportExcel(String file, Integer recordId)throws Exception {
        IRowReader rowReader = new RowReader();
        try {
            // ExcelReaderUtil.readExcel(rowReader,
            // "E://2016-07-04-011940a.xls");
            System.out.println("**********************************************");

            Excel2007Reader exceXlsx = new Excel2007Reader();
            exceXlsx.setRowReader(rowReader);
            exceXlsx.setRecordId(recordId);
            exceXlsx.process(file);
            List<Map<String, Object>> customerList = exceXlsx.getOrderList();
            for (int i=1;i<customerList.size();i++){
                Map<String, Object> params = customerList.get(i);
                int count = salaryUserInfoMapper.selectUserInfo(params);
                if (count == 0) {
                    salaryUserInfoMapper.insertUserInfo(params);
                }else {
                    salaryUserInfoMapper.updateUserInfo(params);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return "";
    }

    @Override
    public int updateUserInfo(SalaryUserInfo salaryUserInfo) {
        return salaryUserInfoMapper.editUserInfo(salaryUserInfo);
    }

}
