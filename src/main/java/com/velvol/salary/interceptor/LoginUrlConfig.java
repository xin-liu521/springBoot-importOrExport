package com.velvol.salary.interceptor;

import com.velvol.salary.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017-08-20.
 */
@Configuration
public class LoginUrlConfig extends WebMvcConfigurerAdapter {
    /**
     * 登录session key
     */
    @Autowired
    private Environment env;
    @Autowired
    private UrlHandler urlHandler;
    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        registry.addInterceptor(urlHandler);
        // 排除配置
        // 拦截配置
       addInterceptor.addPathPatterns("/**");
}

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            // 请求的uri
            String url = request.getRequestURI();
            if(url.indexOf("mobile") == -1){
                return true;
            }
            System.out.println(url);
            // 不过滤的uri
            String[] notFilter = new String[]{
                    "/login","/noLogin"
            };
            // 是否过滤
            boolean doFilter = true;
            for (String s : notFilter) {
                if (url.indexOf(s) != -1) {
                    // 如果uri中包含不过滤的uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }

            if (doFilter) {
                String auth = request.getHeader("Authorization");

                if(auth == null){
                    response.sendRedirect("noLogin");
                    return false;
                }
                auth = LoginUrlConfig.extractJwtTokenFromAuthorizationHeader(auth);
                String base64Security = env.getProperty("base64Security");
                Claims claims = JwtHelper.parseJWT(auth, base64Security);
                if (claims == null) {
                    response.sendRedirect("noLogin");
                    return false;
                }

                request.setAttribute("userName", claims.get("user_name") + "");
                request.setAttribute("userId", claims.get("user_id") + "");
                request.setAttribute("workerId", claims.get("worker_id") + "");
                return true;
            } else {
                return true;
            }
        }
    }

    public static String extractJwtTokenFromAuthorizationHeader(String auth) {
        return auth.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r] ", "").replace(" ", "");
    }
}
