package com.velvol.salary.service.impl;

import com.velvol.salary.service.IRowReader;

import java.util.List;

/**
 * Created by Administrator on 2018/6/5.
 */
public class RowReader implements IRowReader {
    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowlist) {
        // TODO Auto-generated method stub
        System.out.print(curRow+" ");
        for (int i = 0; i < rowlist.size(); i++) {
            System.out.print(rowlist.get(i) + " ");
        }
        System.out.println();
    }

}
