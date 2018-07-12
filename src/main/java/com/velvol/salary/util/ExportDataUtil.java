package com.velvol.salary.util;/**
 * Created by Admin on 2017/5/15.
 */

import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author fy
 * @create 2017-05-15 20:57
 **/
public class ExportDataUtil {
    /**
     * @descrtption绩效明细导出excel
     * @Author fy
     * @Date 2017/1/3 17:49
     **/

//    public  static void  findexportExcel(String[] titles,String name, ServletOutputStream outputStream,
//                                         List<Map<String,Object>> listMap){
////        List<T03PolicyperdayVo> list=detailsMapperDao.findDetailsList(bids,str_date,pid);
//        // 创建一个workbook 对应一个excel应用文件
//        XSSFWorkbook workBook = new XSSFWorkbook();
//        // 在workbook中添加一个sheet,对应Excel文件中的sheet
//        XSSFSheet sheet = workBook.createSheet(name);
//        ExportUtil exportUtil = new ExportUtil(workBook, sheet);
//        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
//        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
//        // 构建表头
//        XSSFRow headRow = sheet.createRow(0);
//        headRow.setHeight((short)600);
//        XSSFCell cell = null;
//        for (int i = 0; i < titles.length; i++)
//        {
//            cell = headRow.createCell(i);
//            cell.setCellStyle(headStyle);
//            cell.setCellValue(titles[i]);
//            sheet.setColumnWidth(i,25*256);
//        }
//
//        // 构建表体数据
//        //计划使用LinkedHashMap可以按照顺序循环map 但是对于null值需要在sql语句中处理
////        Mybatis对Map的解析生成, 如果值(value)为null的话,那么key也不会被加入到map中.
//        if(listMap!=null&&listMap.size()>0){
//            for (int i = 0; i <listMap.size(); i++) {
//                Map<String,Object> map=listMap.get(i);
//                XSSFRow bodyRow = sheet.createRow(i + 1);
//                bodyRow.setHeight((short)500);
//                int j=0;
//                for (Map.Entry<String, Object> entry : map.entrySet()) {
////                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//                    cell = bodyRow.createCell(j);
//                    cell.setCellStyle(bodyStyle);
//                    if(entry.getValue()==null){
//                        cell.setCellValue("");
//                    }else{
//                        cell.setCellValue(entry.getValue()+"");
//                    }
//                    j++;
//                }
//            }
//        }
//        try {
//            workBook.write(outputStream);
//            outputStream.flush();
//            outputStream.close();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
}
