package com.velvol.salary.controller;

import com.github.pagehelper.PageInfo;
import com.velvol.salary.service.IndexService;
import com.velvol.salary.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * RESTFUT    POST,GET,DELETE,PUT
 */
@RequestMapping(value="/index")
@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 返回json 数据举例
     * @param pageNum
     * @param session
     * @param modelMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/indexQuery")
    public Object index(Integer pageNum,HttpSession session, ModelMap modelMap){
        PageInfo page=indexService.queryUser(10);
        if(page!=null){
             return  ResultUtil.renderSuccess(page);
        }else{
             return ResultUtil.renderError();
        }
    }
    /**
     * 跳转页面
     * @return
     */
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
