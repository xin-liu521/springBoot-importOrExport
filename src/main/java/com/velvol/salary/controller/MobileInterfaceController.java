package com.velvol.salary.controller;

import com.alibaba.fastjson.JSONObject;
import com.velvol.salary.domain.Bankcardinfo;
import com.velvol.salary.domain.User;
import com.velvol.salary.domain.Worker;
import com.velvol.salary.service.MobileInterfaceService;
import com.velvol.salary.smsutils.SmsUtils;
import com.velvol.salary.util.JwtHelper;
import com.velvol.salary.util.ResultUtil;
import com.velvol.salary.util.wx.HttpUtil;
import com.velvol.salary.util.wx.MaterialTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/29.
 */
@Controller
@RequestMapping("/mobile")
public class MobileInterfaceController {

    @Autowired
    private Environment env;

    @Autowired
    MobileInterfaceService mobileInterfaceService;

    /**
     * @desc 登录验证
     * @param userName
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
    public Object login(String userName, String password, String code) {
        User user = mobileInterfaceService.selectUserByName(userName);
        if (user != null){
            if (user.getPassword().equals(password)){
                Map<String, Object> params = new HashMap<>();
                String roleId = mobileInterfaceService.selectUserRole(user.getId());
                params.put("user", user);
                params.put("roleId", roleId);
                String token = JwtHelper.createJWT(user.getUsername(),user.getId(), user.getWorkerid(), -1L, this.env.getProperty("base64Security"));
                if (token != null) {
                    params.put("token", token);
                }
                return ResultUtil.renderSuccess(params, "登录成功！");

            }else {
                return ResultUtil.renderError("密码错误！");
            }

        }else {
            return ResultUtil.renderError("用户名错误！");
        }
    }

    public String wxSend(String code, User user){
        //得到code
        String CODE = code;
        System.out.print("CODE========"+CODE);
        String APPID = MaterialTypes.APP_ID;
        String SECRET = MaterialTypes.APP_SECRET;
        //换取access_token 其中包含了openid
        String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code".replace("APPID", APPID).replace("SECRET", SECRET).replace("CODE", CODE);
        JSONObject json = JSONObject.parseObject(HttpUtil.getJsonObjectByUrl(URL));
        String openid = json.get("openid")+"";
        System.out.print("openid========"+openid);
        return openid;
    }

    /**
     * @desc 统计人员信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/statisticData", method = {RequestMethod.POST,RequestMethod.GET})
    public Object statisticData(Integer regionId, HttpServletRequest request){
        String userName = (String) request.getAttribute("userName");
        //查询在职人员
        int personCount = mobileInterfaceService.selectPersonCount(regionId);
        //昨天入职人数
        int entryCount = mobileInterfaceService.selectEntryCount(regionId);
        //昨天离职人数
        int leaveCount = mobileInterfaceService.selectLeaveCount(regionId);
        //当月订单数
        int orderCount = mobileInterfaceService.selectOrderCount(regionId);
        Map<String, Object> params = new HashMap<>();
        params.put("personCount", personCount);
        params.put("entryCount", entryCount);
        params.put("leaveCount", leaveCount);
        params.put("orderCount", orderCount);
        return ResultUtil.renderSuccess(params, "请求成功");
    }

    /**
     * @desc 统计个地区的人员数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryRegionPersonCount", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryRegionPersonCount(){
        List<Map<String, Object>> regionCountList = mobileInterfaceService.queryRegionPersonCount();
        return ResultUtil.renderSuccess(regionCountList, "请求成功");
    }

    /**
     * @desc 统计所有当前月每天的人员数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDayPersonCount", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryDayPersonCount() {
        Map<Object, Object> personCount = mobileInterfaceService.queryDayPersonCount();
        return ResultUtil.renderSuccess(personCount, "请求成功");
    }

    /**
     * @desc 统计当前月个地区的订单数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryRegionOrderCount", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryRegionOrderCount(){
        List<Map<String, Object>> orderCountList = mobileInterfaceService.queryRegionOrderCount();
        return ResultUtil.renderSuccess(orderCountList, "请求成功");
    }

    /**
     * @desc 统计所有当前月每天的订单数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDayOrderCount", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryDayOrderCount() {
        Map<Object, Object> orderCount = mobileInterfaceService.queryDayOrderCount();
        return ResultUtil.renderSuccess(orderCount, "请求成功");
    }

    /***********************************************************************************************/


    /**
     * @desc 统计个站点的人员数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStationPersonCount", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryStationPersonCount(@RequestParam Integer regionId){
        List<Map<String, Object>> regionCountList = mobileInterfaceService.queryStationPersonCount(regionId);
        return ResultUtil.renderSuccess(regionCountList, "请求成功");
    }

    /**
     * @desc 统计地区当前月每天的人员数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDayStationPersonCount", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryDayStationPersonCount(@RequestParam Integer regionId) {
        Map<Object, Object> personCount = mobileInterfaceService.queryDayStationPersonCount(regionId);
        return ResultUtil.renderSuccess(personCount, "请求成功");
    }


    /**
     * @desc 统计当前月个站点的订单数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStationOrderCount", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryStationOrderCount(@RequestParam Integer regionId){
        List<Map<String, Object>> orderCountList = mobileInterfaceService.queryStationOrderCount(regionId);
        return ResultUtil.renderSuccess(orderCountList, "请求成功");
    }

    /**
     * @desc 统计当前月地区每天的订单数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDayStationOrderCount", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryDayStationOrderCount(@RequestParam Integer regionId) {
        Map<Object, Object> orderCount = mobileInterfaceService.queryDayStationOrderCount(regionId);
        return ResultUtil.renderSuccess(orderCount, "请求成功");
    }

    /**
     * @desc 未登录验证
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/noLogin", method = {RequestMethod.POST,RequestMethod.GET})
    public Object noLogin(){
        return ResultUtil.renderError("未授权！");
    }

    /********************************************* 骑手接口***************************************************************/

    /**
     * @desc 查询基本信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryWorker", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryWorker(HttpServletRequest request){
        String workerId = (String) request.getAttribute("workerId");
        Worker worker = mobileInterfaceService.queryWorker(workerId);
        return ResultUtil.renderSuccess(worker, "请求成功");
    }

    /**
     * @desc 修改基本信息
     * @param worker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateWorker", method = {RequestMethod.POST,RequestMethod.GET})
    public Object updateWorker (Worker worker){
        int count = mobileInterfaceService.updateWorker(worker);
        if (count > 0){
            return ResultUtil.renderSuccess("修改成功");
        }else {
            return ResultUtil.renderError("修改失败");
        }
    }

    /**
     * @desc 查询银行卡信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryBankInfo", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryBankInfo(HttpServletRequest request){
        String workerId = (String) request.getAttribute("workerId");
        List<Bankcardinfo> bankcardinfos = mobileInterfaceService.queryBankInfo(workerId);
        return ResultUtil.renderSuccess(bankcardinfos, "请求成功");
    }


    /**
     * @desc 新增修改银行卡信息
     * @param bankcardinfo
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addOrUpdateBankInfo", method = {RequestMethod.POST,RequestMethod.GET})
    public Object addOrUpdateBankInfo(Bankcardinfo bankcardinfo, HttpServletRequest request){
        String workerId = (String) request.getAttribute("workerId");
        int count = mobileInterfaceService.addOrUpdateBankInfo(bankcardinfo, workerId);
        if (count > 0){
            return ResultUtil.renderSuccess("操作成功");
        }else {
            return ResultUtil.renderError("操作失败");
        }
    }

    /**
     * @desc 修改密码
     * @param request
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = {RequestMethod.POST,RequestMethod.GET})
    public Object updatePassword(HttpServletRequest request, String oldPwd, String newPwd){
        String userId = (String) request.getAttribute("userId");
        User user = mobileInterfaceService.selectUserById(userId);
        if (!oldPwd.equals(user.getPassword())) {
            return ResultUtil.renderError("旧密码错误！");
        }else {
            user.setPassword(newPwd);
            mobileInterfaceService.updatePassword(user);
            return ResultUtil.renderSuccess("密码修改成功！");
        }
    }

    /**
     * @desc 骑手当月累计单量
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryOrderCount", method = {RequestMethod.POST,RequestMethod.GET})
    public Object queryOrderCount(HttpServletRequest request){
        String workerId = (String) request.getAttribute("workerId");
        Worker worker = mobileInterfaceService.queryWorker(workerId);
        int count = mobileInterfaceService.queryOrderCount(worker.getMeituanid());
        return ResultUtil.renderSuccess(count, "请求成功！");
    }
}
