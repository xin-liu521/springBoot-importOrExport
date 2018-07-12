package com.velvol.salary.util;
/**
 * Created by Admin on 2017/6/13.
 */

import com.github.pagehelper.PageInfo;
import com.velvol.salary.domain.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fy
 * @create 2017-06-13 17:29
 **/
public class ResultUtil {
    /**
     * ajax失败
     * @return {Object}
     */
    public static Object renderError() {
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("系统错误");
        return result;
    }
    /**
     * ajax失败
     * @return {Object}
     */
    public static Object renderError(String msg) {
        Result result = new Result();
        result.setCode(-1);
        result.setMsg(msg);
        return result;
    }
    /**
     * ajax成功
     * @return {Object}
     */
    public static Object renderSuccess(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(0);
        return result;
    }
    /**
     * ajax成功
     * @return {Object}
     */
    public static Object renderSuccess() {
        Result result = new Result();
        result.setCode(0);
        return result;
    }
    /**
     * ajax成功
     * @return {Object}
     */
    public static Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setCode(0);
        result.setData(obj);
        return result;
    }

    /**
     * ajax成功
     * @return {Object}
     */
    public static Object renderSuccess(Object obj, String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(obj);
        return result;
    }

    public static Map<String, Object> pageInfo(PageInfo pageInfo) {
        Map<String, Object> map = new HashMap<>();
        if (null != pageInfo){
            map.put("total", pageInfo.getTotal());
            map.put("rows", pageInfo.getList());
        }
        return map;
    }

}
