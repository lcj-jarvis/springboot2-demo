package com.mrlu.springboot.config;

import com.mrlu.springboot.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-17 20:31
 *
 * 1、编写一个拦截器实现HandlerInterceptor接口
 * 2、拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors方法）
 * 3、指定拦截规则[如果是拦截所有，静态资源也会被拦截]
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //拦截所有的请求，包括静态资源和动态资源
                .addPathPatterns("/**")
                //放行的请求.static目录下的静态资源也要放行。
                // 第一种方式直接在这里写。第二种方式在yaml文件配置静态资源的路径
                .excludePathPatterns("/","/login","/fonts/**","/css/**","/images/**","/js/**");
    }
}
