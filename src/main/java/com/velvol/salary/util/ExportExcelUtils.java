package com.velvol.salary.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.tomcat.jni.Mmap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2018/4/3.
 */
public class ExportExcelUtils {

    /*
     * 导出数据
     * */
    public static void export(String title, String[] rowName, List<Map<String, Object>> dataList,String[] names,OutputStream out) throws Exception {
        try {
            Workbook workbook = new SXSSFWorkbook(100);                   // 创建工作簿对象
            Sheet sheet = workbook.createSheet(title);                  // 创建工作表
            // 产生表格标题行
            Row rowm = sheet.createRow(0);
            Cell cellTiltle = rowm.createCell(0);

            //sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面  - 可扩展】
            CellStyle columnTopStyle = ExportExcelUtils.getColumnTopStyle(workbook);//获取列头样式对象
            CellStyle style = ExportExcelUtils.getStyle(workbook);                  //单元格样式对象

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));//合并单元格
            cellTiltle.setCellStyle(columnTopStyle);
            //String titleStr = title.substring(0, title.lastIndexOf("."));
            cellTiltle.setCellValue(title);

            // 定义所需列数
            int columnNum = rowName.length;
            Row rowRowName = sheet.createRow(2);                // 在索引2的位置创建行(最顶端的行开始的第二行)

            // 将列头设置到sheet的单元格中
            for (int n = 0; n < columnNum; n++) {
                Cell cellRowName = rowRowName.createCell(n);               //创建列头对应个数的单元格
                cellRowName.setCellType(Cell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
                XSSFRichTextString text = new XSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);                                 //设置列头单元格的值
                cellRowName.setCellStyle(columnTopStyle);                       //设置列头单元格样式
            }

            //将查询出的数据设置到sheet对应的单元格中
            for (int i = 0; i < dataList.size(); i++) {

                Object[] obj = new Object[names.length];
                Map map = dataList.get(i);//遍历每个对象
                for (int a= 0; a<names.length; a++){
                    for(Object key : map.keySet()){
                        if (names[a].equals(key.toString())) {
                            if(key.toString().equals("base_price")||key.toString().equals("interval_price")||key.toString().equals("night_price")
                                    ||key.toString().equals("one_two_price")||key.toString().equals("greater_two_price")||key.toString().equals("distance_price")
                                    ||key.toString().equals("season_price")||key.toString().equals("vehicle_price")||key.toString().equals("meal_price")
                                    ||key.toString().equals("telephone_price")||key.toString().equals("live_price")||key.toString().equals("present_price")
                                    ||key.toString().equals("cmmon_overtime_price")||key.toString().equals("serious_overtime_price")||key.toString().equals("belate_price")
                                    ||key.toString().equals("absent_price")||key.toString().equals("adjust_price")||key.toString().equals("amount_price")
                                    ||key.toString().equals("insurance")||key.toString().equals("equip_use_price")||key.toString().equals("equip_buy_price")
                                    ||key.toString().equals("equip_price")||key.toString().equals("no_standard_price")||key.toString().equals("discontent_price")
                                    ||key.toString().equals("over_task_price")||key.toString().equals("vehicle_deduction_price")||key.toString().equals("no_task_base_price")
                                    ||key.toString().equals("train_price")||key.toString().equals("temperature_price")
                                    ||key.toString().equals("smile_action_price")||key.toString().equals("subsidy_price")
                                    ||key.toString().equals("less_task_price")||key.toString().equals("charge_price")||key.toString().equals("live_deduction_price")
                                    ||key.toString().equals("interval_two_price")||key.toString().equals("interval_three_price")||key.toString().equals("interval_four_price")
                                    ||key.toString().equals("interval_five_price")||key.toString().equals("diatance_one_price")||key.toString().equals("distance_two_price")
                                    ||key.toString().equals("insurance_price")||key.toString().equals("outside_price")||key.toString().equals("outside_distance_price")
                                    ||key.toString().equals("outside_night_price")||key.toString().equals("outside_noon_price")||key.toString().equals("introduction_fee_deduct")
                                    ||key.toString().equals("no_service_price")||key.toString().equals("one_star_price")||key.toString().equals("two_star_price")
                                    ||key.toString().equals("complain_price")||key.toString().equals("class_ii_complain_price")||key.toString().equals("interval_one_price")
                                    ||key.toString().equals("personal_tax")||key.toString().equals("user_equip_price")||key.toString().equals("five_star_general")
                                    ||key.toString().equals("group_leader")||key.toString().equals("royal_knight")||key.toString().equals("big_night_price")
                                     ||key.toString().equals("social_security")||key.toString().equals("station_star_price")||key.toString().equals("month_punch_price")
                                    ||key.toString().equals("lastmonth_price")
                            ){
                                if (map.get(key) != null) {
                                    String value = String.valueOf(map.get(key));
                                    Double valueDb = DoubleUtil.divide(Double.valueOf(value), 100d);
                                    obj[a] =  valueDb;
                                }else {
                                    obj[a] = "";
                                }
                            }else if(key.toString().equals("state")){
                                if (map.get("resign_time") != null) {
                                        obj[a] = "离职";
                                    }else {
                                        obj[a] = "在职";
                                    }
                            }else if(key.toString().equals("vehicle")){
                                if (map.get(key) != null) {
                                    String value = String.valueOf(map.get(key));
                                    if (value.equals("1")) {
                                        obj[a] = "是";
                                    }
                                    if (value.equals("0")) {
                                        obj[a] = "否";
                                    }
                                }else {
                                    obj[a] = "";
                                }
                            }else if(key.toString().equals("status")){
                                if (map.get(key) != null) {
                                    String value = String.valueOf(map.get(key));
                                    if (value.equals("0")) {
                                        obj[a] = "发放";
                                    }
                                    if (value.equals("1")) {
                                        obj[a] = "暂扣";
                                    }
                                    if (value.equals("2")) {
                                        obj[a] = "不发放";
                                    }
                                }else {
                                    obj[a] = "";
                                }
                            }else if(key.toString().equals("is_new_rider")){
                                if (map.get(key) != null) {
                                    String value = String.valueOf(map.get(key));
                                    if (value.equals("1")) {
                                        obj[a] = "老骑手";
                                    }
                                    if (value.equals("0")) {
                                        obj[a] = "新骑手";
                                    }
                                }else {
                                    obj[a] = "";
                                }
                            }else if(key.toString().equals("entry_time") || key.toString().equals("resign_time")){
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //日期格式化
                                if (map.get(key) != null) {
                                    String value = String.valueOf(map.get(key));
                                    String date = sdf.format(DateFormat.getDateInstance().parse(value));
                                    obj[a] = date;
                                }else {
                                    obj[a] = "";
                                }
                            }else {
                                if (map.get(key) != null) {
                                    obj[a] =  String.valueOf(map.get(key));
                                }else {
                                    obj[a] = "";
                                }
                            }
                        }
                    }
                }

                Row row = sheet.createRow(i + 3);//创建所需的行数（从第二行开始写数据）
                for (int j = 0; j < obj.length; j++) {
                    Cell cell = null;   //设置单元格的数据类型
                    if (j == 0) {
                        cell = row.createCell(j, Cell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(i + 1);
                    } else {
                        cell = row.createCell(j, Cell.CELL_TYPE_NUMERIC);
                        if ( obj[j] != null) {
                            cell.setCellValue(obj[j].toString());                       //设置单元格的值
                        }
                    }
                    cell.setCellStyle(style);                                   //设置单元格样式
                }
            }
            //让列宽随着导出的列长自动适应
//            for (int colNum = 0; colNum < columnNum; colNum++) {
//                int columnWidth = sheet.getColumnWidth(colNum) / 256;
//                for (int rowNum = 2; rowNum < sheet.getLastRowNum(); rowNum++) {
//                    Row currentRow;
//                    //当前行未被使用过
//                    if (sheet.getRow(rowNum) == null) {
//                        currentRow = sheet.createRow(rowNum);
//                    } else {
//                        currentRow = sheet.getRow(rowNum);
//                    }
//
//                    if (currentRow.getCell(colNum) != null) {
//                        Cell currentCell = currentRow.getCell(colNum);
//                        if (currentCell.getCellType() == Cell.CELL_TYPE_STRING ) {
//                            int length = 0;
//                            try {
//                                if (currentCell.getStringCellValue() != null){
//                                    length = currentCell.getStringCellValue().getBytes().length;
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            if (columnWidth < length) {
//                                columnWidth = length;
//                            }
//                        }
//                    }
//
//                }
//                if (colNum == 0) {
//                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
//                } else {
//                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
//                }
//            }
            if (workbook != null) {
                try {
                    workbook.write(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }

    /*
     * 列头单元格样式
     */
    public static CellStyle getColumnTopStyle(Workbook workbook) {

        // 设置字体
        Font font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 13);
        //字体加粗
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        CellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /*
     * 列数据信息单元格样式
     */
    public static CellStyle getStyle(Workbook workbook) {
        // 设置字体
        Font font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        CellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }


    /**
     * 导出Excel的方法
     *
     * @param headers 表头
     * @param result  结果集
     * @throws Exception
     * @paramexcel中的sheet名称
     */
    public static void exportExcelTwo(String title,
                                   String[] headers,
                                   List<Map<String, Object>> result,
                                   String[] names,
                                   HttpServletResponse response) throws Exception {

        File file = new File("E:/exportExcel/" + title + ".xls");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        //文件流用于转存文件
        FileOutputStream o = new FileOutputStream(file);
//        ExportExcelUtils ex = new ExportExcelUtils(title, headers, result, names);
       export(title, headers, result, names,o);
       downloadFile(file, response, true);
    }


    /**
     * 导出Excel的方法
     *
     * @param headers 表头
     * @param result  结果集
     * @throws Exception
     * @paramexcel中的sheet名称
     */
    public static void exportExcel(String title,
                            String[] headers,
                            List<List<Map<String, Object>>> result,
                            String[] names,
                            HttpServletResponse response,
                            List<Map<String, Object>> stationList) throws Exception {

        File zip = new File("E:/exportExcel/" + title + ".zip");// 压缩文件
        if (!zip.getParentFile().exists()) {
            zip.getParentFile().mkdirs();
        }

        List<String> fileNames = new ArrayList();// 用于存放生成的文件名称s
        //文件流用于转存文件

        for (int j = 0; j < result.size(); j++) {
            String stationName = (String) stationList.get(j).get("name");
            String file = "E:/exportExcel/" + stationName + ".xls";
            fileNames.add(file);
            FileOutputStream o = new FileOutputStream(file);
//            ExportExcelUtils ex = new ExportExcelUtils(stationName, headers, result.get(j), names);
            ExportExcelUtils.export(stationName, headers, result.get(j), names,o);
        }
        File srcfile[] = new File[fileNames.size()];
        for (int i = 0, n1 = fileNames.size(); i < n1; i++) {
            srcfile[i] = new File(fileNames.get(i));
        }
        ZipFiles(srcfile, zip);
        downloadFile(zip, response, true);
        for(int i=0;i<srcfile.length;i++) {
            File f=srcfile[i];
            f.delete();
        }
    }

    //压缩文件
    public static void ZipFiles(java.io.File[] srcfile, java.io.File zipfile) {
        byte[] buf = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                    zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void downloadFile(File file, HttpServletResponse response, boolean isDelete) {
        try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            if(isDelete)
            {
                file.delete();        //是否将生成的服务器端文件删除
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}