package com.mrlu.springboot.config;

import com.mrlu.springboot.servlet.MyFilter;
import com.mrlu.springboot.servlet.MyServlet;
import com.mrlu.springboot.servlet.MyServletContextListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-18 19:39
 *
 * proxyBeanMethods = true:保证依赖的组件始终是单实例的
 */
@Configuration(proxyBeanMethods = true)
public class MyRegisterConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        MyServlet myServlet = new MyServlet();
        //第二个参数为拦截的路径
        //return new ServletRegistrationBean(myServlet,"/my","my02");
        ServletRegistrationBean<MyServlet> registrationBean = new ServletRegistrationBean<>(myServlet);
        registrationBean.addUrlMappings("/my","/my02");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        MyFilter myFilter = new MyFilter();
        //过滤上面的Servlet
        //FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>(myFilter,servletRegistrationBean());
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>(myFilter);
        //registrationBean.addUrlPatterns("/my","/css/*");
        registrationBean.addUrlPatterns("/my");
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        MyServletContextListener myServletContextListener = new MyServletContextListener();
        return new ServletListenerRegistrationBean(myServletContextListener);
    }
}
