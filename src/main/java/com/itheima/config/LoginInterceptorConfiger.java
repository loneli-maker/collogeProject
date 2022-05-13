package com.itheima.config;


import com.itheima.interceptor.LoginInterceptorConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceptorConfiger implements WebMvcConfigurer {
//    HandlerInterceptor interceptor=new LoginInterceptorConfiger;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptorConfigurer();
        // 通过注册工具添加拦截器
        // 白名单
        List<String> patterns = new ArrayList<String>();
        patterns.add("/common/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/login");
        patterns.add("/loginT");
        patterns.add("/regJson");
        patterns.add("/loginServelt");//Thymeleaf模板要排除登录操作（即控制器中的@Requestmapping方法）
        //给出白名单外的文件添加拦截，如果登录拦截失效就注释下面这行代码
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }
}
