package com.velvol.salary.controller;

import com.alibaba.fastjson.JSONObject;
import com.velvol.salary.domain.Tipinfo;
import com.velvol.salary.domain.User;
import com.velvol.salary.domain.Worker;
import com.velvol.salary.service.MobileInterfaceService;
import com.velvol.salary.smsutils.SmsUtils;
import com.velvol.salary.util.ResultUtil;
import com.velvol.salary.util.wx.HttpUtil;
import com.velvol.salary.util.wx.MaterialTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/7/6.
 */
@Controller
@RequestMapping("/register")
public class RegisterInterfaceController {

    @Autowired
    MobileInterfaceService mobileInterfaceService;

    /**
     * @DESC 注册
     * @param worker
     * @return
     */
    @RequestMapping(value = "/registerWorker", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object registerWorker(Worker worker, String code){
        int count = mobileInterfaceService.registerWorker(worker);
        if (count > 0){
            return  ResultUtil.renderSuccess("注册成功");
        }else {
            return ResultUtil.renderError("注册失败");
        }

    }


    public void wxSend(String code, Worker worker){
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
        String access_token = MaterialTypes.getAccessToken();
        Tipinfo tipinfo = mobileInterfaceService.queryTipinfo(worker);
        try {
            //此处发送短信消息给骑手
            SmsUtils.smsSend(""+worker.getTelephone()+"", "请于"+worker.getPredate()+"到"+tipinfo.getName()+""+tipinfo.getAddr()+"面试！联系人："+tipinfo.getLinkman()+"，电话："+tipinfo.getLinkmantel()+"【威沃科技】");
            //给微信自动推送消息
            String content = "请于"+worker.getPredate()+"到"+tipinfo.getName()+""+tipinfo.getAddr()+"面试！联系人："+tipinfo.getLinkman()+"，电话："+tipinfo.getLinkmantel()+", 报到时请务必携带3张一寸照片,身份证原件【威沃科技】";
            String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?&body=0&access_token="+ access_token;
            String strJson = "{\"touser\" :\""+openid+"\",";
            strJson += "\"msgtype\":\"text\",";
            strJson += "\"text\":{";
            strJson += "\"content\":\""+content+"\"";
            strJson += "}}";
            //WechatMessageUtil.post(tmpurl, strJson);
            JSONObject result = HttpUtil.httpRequest(tmpurl, "POST", strJson);
            System.out.println("发送微信json信息："+strJson);
            System.out.println("发送微信消息返回信息："+result.get("errcode"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //此处发送短信消息给站长或人事
        try {
            SmsUtils.smsSend(""+tipinfo.getLinkmantel()+"", "新人报到提醒："+worker.getName()+"，电话："+worker.getTelephone()+"，将于"+worker.getPredate()+"到贵处报到面试【威沃科技】");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @desc 查询地区
     * @return
     */
    @RequestMapping(value = "/queryRegion", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object queryRegion(){
        List<Map<String, Object>> regionList = mobileInterfaceService.queryRegion();
        return  ResultUtil.renderSuccess(regionList, "请求成功");
    }

    /**
     * @desc 查询站点
     * @return
     */
    @RequestMapping(value = "/queryStation", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object queryStation(){
        List<Map<String, Object>> stationList = mobileInterfaceService.queryStation();
        return  ResultUtil.renderSuccess(stationList, "请求成功");
    }

    /**
     * @desc 校验手机号
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/checkoutTelephone", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object checkoutTelephone(String telephone){
        int count = mobileInterfaceService.checkoutTelephone(telephone);
        if (count > 0){
            return  ResultUtil.renderSuccess("手机号已注册");
        }else {
            return ResultUtil.renderError("手机号未注册");
        }
    }

    /**
     * @desc 获取短信验证码
     * @param telephone
     * @param validateCode
     * @return
     */
    @RequestMapping(value = "/queryTelephoneCode", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object queryTelephoneCode(String telephone, String validateCode){
        if (!telephone.equals("") && !validateCode.equals("")){
            //此处发送短信消息
            try {
                SmsUtils.smsSend(telephone, "您的验证码是"+validateCode+"【威沃科技】");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  ResultUtil.renderSuccess("获取验证码成功");
        }else {
            return  ResultUtil.renderError("获取验证码失败");
        }


    }
}
