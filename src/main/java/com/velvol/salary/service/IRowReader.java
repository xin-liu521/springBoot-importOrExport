package com.velvol.salary.service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/5.
 */
public interface IRowReader {

    /**业务逻辑实现方法
     * @param sheetIndex
     * @param curRow
     * @param rowlist
     */
    public  void getRows(int sheetIndex,int curRow, List<String> rowlist);

}
