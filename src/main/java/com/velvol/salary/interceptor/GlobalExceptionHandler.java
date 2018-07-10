package com.velvol.salary.interceptor;/**
 * Created by Admin on 2017/10/27.
 */

import com.alibaba.fastjson.JSON;
import com.velvol.salary.domain.Result;
import com.velvol.salary.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author fy
 * @create 2017-10-27 14:43
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
    public Object jsonErrorHandler(HttpServletRequest request, HttpServletResponse response,Exception e) throws Exception {
        e.printStackTrace();
        String isajax=request.getHeader("x-requested-with");
        if((isajax==null||!"XMLHttpRequest".equals(isajax))){
            return new ModelAndView("error");
        }else{
            response.setCharacterEncoding("UTF-8");
            Result result=(Result) ResultUtil.renderError();
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
            writer.flush();
            return null;
        }
    }


}
