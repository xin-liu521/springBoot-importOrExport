package com.velvol.salary.domain;

import java.io.Serializable;

/**
 * 
  * @ClassName: Result
  * @Description: AJAX返回状态
  * @author fy
  * @date 2016年7月15日 下午2:06:22
 */
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
    public Result(){
    }
    public Result(Integer code, T obj){
        this.code=code;
        this.data=obj;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
