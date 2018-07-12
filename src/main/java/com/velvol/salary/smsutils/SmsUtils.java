package com.velvol.salary.smsutils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SmsUtils {
	public static void smsSend(String mobile,String content) throws UnsupportedEncodingException {
        String userName = "xawwkj";
        String password = "kr4wwznd";
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String hms = sdf.format(new Timestamp(System.currentTimeMillis()));
          
        Client client = new Client(userName, password);
        String result = client.mt(URLEncoder.encode(content, "UTF-8"), mobile, "", "", "", "");
        System.out.println(result);
        String strCode = result.split("\n")[0];
        long code = 0;
        code = Long.valueOf(strCode);
        String Info = null;
        if (code > 0) {//成功提交
            Info = "发送成功";
        } else if (code == 0) {
            Info = "发送失败";
        } else if (code == -1) { // 用户名密码不正确
            Info = "用户名密码不正确";
        } else if (code == -2) { // 必填选项为空
            Info = "必填选项为空";
        } else if (code == -3) { // 短信内容0个字节
            Info = "短信内容0个字节";
        } else if (code == -4) { // 0个有效号码
            Info = "0个有效号码";
        } else if (code == -5) { // 余额不够
            Info = "余额不够";
        } else if (code == -10) { // 用户被禁用
            Info = "用户被禁用";
        } else if (code == -11) { // 短信内容过长
            Info = "短信内容过长";
        } else if(code == -12){	 //用户无扩展权限
        	Info = "无扩展权限";
        } else if(code == -13){  //IP地址校验错
        	Info = "IP校验错误";
        } else if(code == -14){  //内容解析异常
        	Info = "内容解析异常";
        } else {
            Info = "未知错误";
        }
        System.out.println("返回信息：" + content);
    }
	
}
