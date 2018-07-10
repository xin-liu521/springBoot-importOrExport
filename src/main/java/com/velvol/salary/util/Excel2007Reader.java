package com.velvol.salary.util;

import com.velvol.salary.domain.SalaryOrderDetailTwo;
import com.velvol.salary.domain.SalaryUserInfoTwo;
import com.velvol.salary.service.IRowReader;
import com.velvol.salary.service.impl.RowReader;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/6/5.
 */
public class Excel2007Reader extends DefaultHandler {

    //共享字符串表
    private SharedStringsTable sst;
    //上一次的内容
    private String lastContents;
    private boolean nextIsString;

    private int sheetIndex = -1;
    private List<String> rowlist = new ArrayList<String>();
    //当前行
    private int curRow = 0;
    //当前列
    private int curCol = 0;
    //日期标志
    private boolean dateFlag;
    //数字标志
    private boolean numberFlag;

    private boolean isTElement;


    private final DataFormatter formatter = new DataFormatter();

    private short formatIndex;

    private String formatString;

    // 定义前一个元素和当前元素的位置，用来计算其中空的单元格数量，如A6和A8等
    private String preRef = null, ref = null;

    // 定义该文档一行最大的单元格数，用来补全一行最后可能缺失的单元格
    private String maxRef = null;

    /**
     * 单元格
     */
    private StylesTable stylesTable;

    /**
     * 单元格数据类型，默认为字符串类型
     */
    private CellDataType nextDataType = CellDataType.SSTINDEX;

    private IRowReader rowReader;

    private List<Map<String, Object>> orderList = new ArrayList<>();

    private Integer recordId;



    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public List<Map<String, Object>> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Map<String, Object>> orderList) {
        this.orderList = orderList;
    }

    public void setRowReader(IRowReader rowReader){
        this.rowReader = rowReader;
    }

    /**只遍历一个电子表格，其中sheetId为要遍历的sheet索引，从1开始，1-3
     * @param filename
     * @param sheetId
     * @throws Exception
     */
    public void processOneSheet(String filename,int sheetId) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);

        // 根据 rId# 或 rSheet# 查找sheet
        InputStream sheet2 = r.getSheet("rId"+sheetId);
        sheetIndex++;
        InputSource sheetSource = new InputSource(sheet2);
        parser.parse(sheetSource);
        sheet2.close();
    }

    /**
     * 遍历工作簿中所有的电子表格
     * @param filename
     * @throws Exception
     */
    public void process(String filename) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);
        Iterator<InputStream> sheets = r.getSheetsData();
        while (sheets.hasNext()) {
            curRow = 0;
            sheetIndex++;
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
        }
    }

    public XMLReader fetchSheetParser(SharedStringsTable sst)
            throws SAXException {
        XMLReader parser = XMLReaderFactory
                .createXMLReader("org.apache.xerces.parsers.SAXParser");
        this.sst = sst;
        parser.setContentHandler(this);
        return parser;
    }

    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {

        // c => 单元格
        if ("c".equals(name)) {
            // 前一个单元格的位置
            if (preRef == null) {
                preRef = attributes.getValue("r");
            } else {
                preRef = ref;
            }
            // 当前单元格的位置
            ref = attributes.getValue("r");
            // 设定单元格类型
            //this.setNextDataType(attributes);
            // 如果下一个元素是 SST 的索引，则将nextIsString标记为true
            String cellType = attributes.getValue("t");
            if ("s".equals(cellType)) {
                nextIsString = true;
            } else {
                nextIsString = false;
            }
            //日期格式
            String cellDateType = attributes.getValue("s");
            if ("3".equals(cellDateType)){
                dateFlag = true;
            } else {
                dateFlag = false;
            }
            String cellNumberType = attributes.getValue("s");
            if("2".equals(cellNumberType)){
                numberFlag = true;
            } else {
                numberFlag = false;
            }


        }
        //当元素为t时
        if("t".equals(name)){
            isTElement = true;
        } else {
            isTElement = false;
        }

        // 置空
        lastContents = "";
    }


    public void endElement(String uri, String localName, String name)
            throws SAXException {

        // 根据SST的索引值的到单元格的真正要存储的字符串
        // 这时characters()方法可能会被调用多次
        if (nextIsString) {
            try {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
                        .toString();
            } catch (Exception e) {

            }
        }
        //t元素也包含字符串
        if(isTElement){
            String value = lastContents.trim();
            rowlist.add(curCol, value);
            curCol++;
            isTElement = false;
            // v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
            // 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
        } else if ("v".equals(name)) {
            // v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
            String value = this.getDataValue(lastContents.trim(), "");
            // 补全单元格之间的空单元格
            if (!ref.equals(preRef)) {
                int len = countNullCell(ref, preRef);
                for (int i = 0; i < len; i++) {
                    rowlist.add(curCol, null);
                    curCol++;
                }
            }
            //日期格式处理
//            if(dateFlag){
//                Date date = HSSFDateUtil.getJavaDate(Double.valueOf(value));
//                SimpleDateFormat dateFormat = new SimpleDateFormat(
//                        "yyyy-MM-dd");
//                value = dateFormat.format(date);
//            }
            rowlist.add(curCol, value);
            curCol++;
        }else {
            //如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法
            if (name.equals("row")) {
                // 默认第一行为表头，以该行单元格数目为最大数目
                if (curRow == 0) {
                    maxRef = ref;
                }
                // 补全一行尾部可能缺失的单元格
                if (maxRef != null) {
                    int len = countNullCell(maxRef, ref);
                    for (int i = 0; i <= len; i++) {
                        rowlist.add(curCol, null);
                        curCol++;
                    }
                }
                //rowReader.getRows(sheetIndex,curRow,rowlist);
                //容器
                Map<String, Object> params = new HashMap<>();
                SalaryOrderDetailTwo  salaryOrderDetail = new SalaryOrderDetailTwo();
                Field[] fields = salaryOrderDetail.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    //System.out.print(rowlist.get(i) + " ");
                    if (i == 0){
                        params.put(fields[0].getName(), recordId);
                    }else {
                        params.put(fields[i].getName(), rowlist.get(i-1).equals("")? null : rowlist.get(i-1));
                    }

                }
                orderList.add(params);
                rowlist.clear();
                curRow++;
                curCol = 0;
            }
        }

    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        //得到单元格内容的值
        lastContents += new String(ch, start, length);
    }

    /**
     * 计算两个单元格之间的单元格数目(同一行)
     *
     * @param ref
     * @param preRef
     * @return
     */
    public int countNullCell(String ref, String preRef) {
        // excel2007最大行数是1048576，最大列数是16384，最后一列列名是XFD
        String xfd = ref.replaceAll("\\d+", "");
        String xfd_1 = preRef.replaceAll("\\d+", "");

        xfd = fillChar(xfd, 3, '@', true);
        xfd_1 = fillChar(xfd_1, 3, '@', true);

        char[] letter = xfd.toCharArray();
        char[] letter_1 = xfd_1.toCharArray();
        int res = (letter[0] - letter_1[0]) * 26 * 26 + (letter[1] - letter_1[1]) * 26 + (letter[2] - letter_1[2]);
        return res - 1;
    }

    /**
     * 字符串的填充
     *
     * @param str
     * @param len
     * @param let
     * @param isPre
     * @return
     */
    String fillChar(String str, int len, char let, boolean isPre) {
        int len_1 = str.length();
        if (len_1 < len) {
            if (isPre) {
                for (int i = 0; i < (len - len_1); i++) {
                    str = let + str;
                }
            } else {
                for (int i = 0; i < (len - len_1); i++) {
                    str = str + let;
                }
            }
        }
        return str;
    }




    /*********************************以下代码暂时没调用，看以后扩展使用*****************************************************/



    /**
     * 单元格中的数据可能的数据类型
     */
    enum CellDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL
    }

    /**
     * 处理数据类型
     *
     * @param attributes
     */
    public void setNextDataType(Attributes attributes) {
        nextDataType = CellDataType.NUMBER;
        formatIndex = -1;
        formatString = null;
        String cellType = attributes.getValue("t");
        String cellStyleStr = attributes.getValue("s");
        String columData = attributes.getValue("r");

        if ("b".equals(cellType)) {
            nextDataType = CellDataType.BOOL;
        } else if ("e".equals(cellType)) {
            nextDataType = CellDataType.ERROR;
        } else if ("inlineStr".equals(cellType)) {
            nextDataType = CellDataType.INLINESTR;
        } else if ("s".equals(cellType)) {
            nextDataType = CellDataType.SSTINDEX;
        } else if ("str".equals(cellType)) {
            nextDataType = CellDataType.FORMULA;
        }

        if (cellStyleStr != null) {
            int styleIndex = Integer.parseInt(cellStyleStr);
            XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
            formatIndex = style.getDataFormat();
            formatString = style.getDataFormatString();

            if ("yyy-mm-dd" == formatString) {
                nextDataType = CellDataType.DATE;
                formatString = "yyyy-MM-dd hh:mm:ss";
            }

            if (formatString == null) {
                nextDataType = CellDataType.NULL;
                formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
            }
        }
    }


    /**
     * 对解析出来的数据进行类型处理
     *
     * @param value
     *            单元格的值（这时候是一串数字）
     * @param thisStr
     *            一个空字符串
     * @return
     */
    @SuppressWarnings("deprecation")
    public String getDataValue(String value, String thisStr) {
        switch (nextDataType) {
            // 这几个的顺序不能随便交换，交换了很可能会导致数据错误
            case BOOL:
                char first = value.charAt(0);
                thisStr = first == '0' ? "FALSE" : "TRUE";
                break;
            case ERROR:
                thisStr = "\"ERROR:" + value + '"';
                break;
            case FORMULA:
                thisStr = '"' + value + '"';
                break;
            case INLINESTR:
                XSSFRichTextString rtsi = new XSSFRichTextString(value);

                thisStr = rtsi.toString();
                rtsi = null;
                break;
            case SSTINDEX:
                String sstIndex = value;
                try {
                    int idx = Integer.parseInt(sstIndex);
                    //XSSFRichTextString rtss = new XSSFRichTextString(sst.getEntryAt(idx));
                    thisStr = String.valueOf(idx);
                    //rtss = null;
                } catch (NumberFormatException ex) {
                    thisStr = value;
                }
                break;
            case NUMBER:
                if (formatString != null) {
                    thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString).trim();
                } else {
                    thisStr = value;
                }

                thisStr = thisStr.replace("_", "").trim();
                break;
            case DATE:
                thisStr = formatter.formatRawCellContents(Double.parseDouble(value), formatIndex, formatString);

                // 对日期字符串作特殊处理
                thisStr = thisStr.replace(" ", "T");
                break;
            default:
                thisStr = " ";

                break;
        }

        return thisStr;
    }


    public static void main(String[] args) {
        IRowReader rowReader = new RowReader();
        try {
            // ExcelReaderUtil.readExcel(rowReader,
            // "E://2016-07-04-011940a.xls");
            System.out.println("**********************************************");

            Excel2007Reader exceXlsx = new Excel2007Reader();
            exceXlsx.setRowReader(rowReader);
            exceXlsx.process("E:/unZipFiles/考勤2.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
