package com.velvol.salary.util;

import java.io.Serializable;
import java.util.Map;

/**
 * 
  * @ClassName: Result
  * @Description: AJAX返回状态
  * @author kpg
  * @date 2016年7月15日 下午2:06:22
 */
public class CallResult implements Serializable {
    private static final long serialVersionUID = 5576237395711742681L;

    private Integer code;
    private String msg;
    private Map<String,Object> result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
