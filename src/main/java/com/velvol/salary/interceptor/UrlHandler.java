package com.velvol.salary.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017-08-20.
 */
@Component
public class UrlHandler extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            // 请求的uri
            String url = request.getRequestURI();
            if(url.indexOf("mobile") != -1){
                return true;
            }
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int port = request.getServerPort();
            String path = "";//request.getContextPath();
            String basePath = scheme + "://" + serverName + ":" + port + path;
            request.setAttribute("basePath", basePath);
            return true;
        }
}
