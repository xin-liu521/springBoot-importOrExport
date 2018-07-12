package com.velvol.salary.util;/**
 * Created by Admin on 2017/6/13.
 */


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fy
 * @create 2017-06-13 17:29
 **/
public class TimeUtil {
    /**
     *
     * time Y 年 M月 W周 D日
     * @return {Object}
     */
    public static Map<String,String> betweenTime(String time,Integer period) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Map<String,String> map=new HashMap<String,String>();
        Calendar start_date=Calendar.getInstance();
        Calendar end_date=Calendar.getInstance();
        if("Y".equals(time)){
            start_date.add(Calendar.YEAR,-period);
            start_date.set(Calendar.DAY_OF_YEAR,1);
        }else if("M".equals(time)){
            start_date.add(Calendar.MONTH,-period);
            start_date.set(Calendar.DAY_OF_MONTH,1);
        }else if("W".equals(time)){
            start_date.setFirstDayOfWeek(Calendar.MONDAY);
            start_date.add(Calendar.WEEK_OF_YEAR, -period);
            start_date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }else if("D".equals(time)){
            start_date.add(Calendar.DATE,-period);
        }
        map.put("start_date",formatter.format(start_date.getTime()));
        map.put("end_date",formatter.format(end_date.getTime()));
        return map;
    }

    /**
     *
     * 获取当前月之前的月末月初日期数据 type 1 月初  2 月末
     * @return {Object}
     */
    public static List<String> monthStrList(int type) {
        List<String> str=new ArrayList<String>();
        Calendar now=Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month= now.get(Calendar.MONTH) + 1;
            for(int i=1;i<=12;i++){
                if(month>i){
                    String day= monthHelp(type,i,year);
                    str.add(day);
                }
            }
        if(type==2){//取月末的时间的时候添加上当前时间
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            String str_time=sdf.format(now.getTime());
            str.add(str_time);
        }else{//取月初的时间的时候添加上 当前月的月初时间
            String m = month >= 10 ? String.valueOf(month) : ("0"+month);
            str.add(year+m+"01");
        }
        return str;
    }
    /**
     * @return {Object}
     */
    public static String monthHelp(int type,int i,int year) {
        String day="";
        String m = i >= 10 ? String.valueOf(i) : ("0"+i);
        if(type==2){
            String d="";
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                d = "31";
            }
            if (i == 4 || i == 6 || i == 9 || i == 11) {
                d = "30";
            }
            if (i == 2) {
                boolean leap = leapYear(year);
                if (leap) {
                    d = "29";
                }else {
                    d = "28";
                }
            }
            day=year+m+d;
        }else if(type==1){
            day=year+m+"01";
        }
        return day;
    }
    /**
     *
     * 获取当参数月的月末或月初日期数据 type 1 月初  2 月末
     * @return {Object}
     */
    public static String monthStr(int type,int month,int year) {
        String day= monthHelp(type,month,year);
        return day;
    }
    /**
     *
     * 获取当前月之前的所有季初 季末日期数据  type 1 季初  2 季末
     * @return {Object}
     */
    public static List<String> seasonStrList(int type) {
        List<String> str=new ArrayList<String>();
        Calendar now=Calendar.getInstance();
        String dateString = "";
        int year = now.get(Calendar.YEAR);
        int month= now.get(Calendar.MONTH) + 1;
        if(type==2){
            if (month > 3) {
                dateString = year+"03"+"31";
                str.add(dateString);
            }
            if (month >6) {
                dateString = year+"06"+"30";
                str.add(dateString);
            }
            if (month >9) {
                dateString = year+"09"+"30";
                str.add(dateString);
            }
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String str_time=sdf.format(now.getTime());
        str.add(str_time);
        return str;
    }
    /**
     * 功能：判断输入年份是否为闰年<br>
     * @param year
     * @return 是：true  否：false
     * @author pure
     */
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) leap = true;
                else leap = false;
            }
            else leap = true;
        }
        else leap = false;
        return leap;
    }
}
