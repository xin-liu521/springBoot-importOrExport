package com.velvol.salary.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/4/16.
 */
public class ImportUtil {

        //总行数
        private int totalRows = 0;
        //总条数
        private int totalCells = 0;
        //错误信息接收器
        private String errorMsg;
        //构造方法
        public ImportUtil(){}
        //获取总行数
        public int getTotalRows()  { return totalRows;}
        //获取总列数
        public int getTotalCells() {  return totalCells;}
        //获取错误信息
        public String getErrorInfo() { return errorMsg; }

        /**
         * 验证EXCEL文件
         * @param filePath
         * @return
         */
        public boolean validateExcel(String filePath){
            if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))){
                errorMsg = "文件名不是excel格式";
                return false;
            }
            return true;
        }

        /**
         * 读EXCEL文件，创建Workbook
         * @param file
         * @return
         */
        public  Map<String, Object> getExcelInfo(File file){

            //String url = file.getName();
            String fileName = file.getName();
            //初始化输入流
            InputStream is = null;
            Workbook wb = null;
            FormulaEvaluator formulaEvaluator = null;
            try{
                //验证文件名是否合格
                if(!validateExcel(fileName)){
                    return null;
                }
                //根据文件名判断文件是2003版本还是2007版本
                boolean isExcel2003 = true;
                if(isExcel2007(fileName)){
                    isExcel2003 = false;
                }
                //根据excel文件实例化输入流
                is = new FileInputStream(file);
                /** 根据版本选择创建Workbook的方式 */

                //当excel是2003时
                if(isExcel2003){
                    wb = new HSSFWorkbook(is);
                    formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) wb);
                }
                else{//当excel是2007时
                    wb = new XSSFWorkbook(is);
                    formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
                }

                is.close();
            }catch(Exception e){
                e.printStackTrace();
            } finally{
                if(is !=null)
                {
                    try{
                        is.close();
                    }catch(IOException e){
                        is = null;
                        e.printStackTrace();
                    }
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("formulaEvaluator", formulaEvaluator);
            map.put("wb", wb);
            return map;
        }

        /**
         * 读取Excel里面信息列数
         * @param wb
         * @return
         */
        public Map<String, Object> readExcelValue(Workbook wb){
            //得到第一个shell
            Sheet sheet=wb.getSheetAt(0);
            //得到Excel的行数
            this.totalRows=sheet.getPhysicalNumberOfRows();

            //得到Excel的列数(前提是有行数)
            if(totalRows>=1 && sheet.getRow(0) != null){
                this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
            }

            Map<String, Object> map = new HashMap<>();
            map.put("totalRows", totalRows);
            map.put("totalCells", totalCells);
            map.put("sheet", sheet);
            return map;
        }

    /**
     * 处理val（暂时只处理string和number，可以自己添加自己需要的val类型）
     * @param cell
     * @return
     */
    public static String getVal(Cell cell, FormulaEvaluator formulaEvaluator) {
        String value = "";
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
        df2.setRoundingMode(RoundingMode.DOWN);
        if(cell != null){
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if("General".equals(cell.getCellStyle().getDataFormatString())){
                        value = df.format(cell.getNumericCellValue());
                    }else if(HSSFDateUtil.isCellDateFormatted(cell)){
                        value = sdf.format(cell.getDateCellValue());
                    }else{
                        value = df2.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:                        //公式型
                    try {
                        CellValue cellValue;
                        cellValue = formulaEvaluator.evaluate(cell);
                        switch (cellValue.getCellType()) {              //判断公式类型
                            case Cell.CELL_TYPE_BOOLEAN:
                                value  = String.valueOf(cellValue.getBooleanValue());
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if("General".equals(cell.getCellStyle().getDataFormatString())){
                                    value = df.format(cell.getNumericCellValue());
                                }else if(HSSFDateUtil.isCellDateFormatted(cell)){
                                    value = sdf.format(cell.getDateCellValue());
                                }else{
                                    value = df2.format(cell.getNumericCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_STRING:
                                value  = cellValue.getStringValue();
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                value = "";
                                break;
                            case Cell.CELL_TYPE_ERROR:
                                value = "";
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                value = "";
                                break;
                        }
                    } catch (Exception e) {
                        value = cell.getStringCellValue().toString();
                        cell.getCellFormula();
                    }
                    break;
                default:
                    break;
            }
        }

        return value;
    }

    // @描述：是否是2003的excel，返回true是2003
        public static boolean isExcel2003(String filePath)  {
            return filePath.matches("^.+\\.(?i)(xls)$");
        }

        //@描述：是否是2007的excel，返回true是2007
        public static boolean isExcel2007(String filePath)  {
            return filePath.matches("^.+\\.(?i)(xlsx)$");
        }


    /**
     * @desc 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    public static File uploadService(MultipartFile file) throws IOException {
        String path = "E:/zipUpload/"+ System.currentTimeMillis()+"/";

        //名称
        String fileFileName = file.getOriginalFilename();
        //String type = fileFileName.indexOf(".") != -1 ? fileFileName.substring(fileFileName.lastIndexOf(".") + 1, fileFileName.length()) : null;
        //判断是否存在目录
        File targetFile = new File(path, fileFileName);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        //上传
        file.transferTo(targetFile);
        return targetFile;
    }
}
