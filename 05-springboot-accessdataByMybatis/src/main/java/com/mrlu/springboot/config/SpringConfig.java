package com.mrlu.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-16 16:34
 *
 * 在pom文件中引入druid-spring-boot-starter。就不用下面的了
 */
//@Configuration
public class SpringConfig {

    /**
     * 配置druid数据库连接池
     * 这里面调用setXxx方法的都可以在配置文件中配置
     * @return
     * @throws SQLException
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        //打开Druid的监控统计功能:stat
        //配置防御SQL注入攻击:wall
        dataSource.setFilters("stat,wall");

        return dataSource;
    }

    /**
     * 参考这里
     * https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98
     * 配置druid的监控页功能
     * 根据配置中的url-pattern来访问内置监控页面，
     * 如果是下面的配置，内置监控页面的首页是/druid/index.html
     * 如：
     * http://localhost:8080/druid/sql.html
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
                new ServletRegistrationBean<>(statViewServlet,"/druid/*");

        //配置druid的监控页的账号和密码
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","admin");
        return servletRegistrationBean;
    }

    /**
     * WebStatFilter用于采集web-jdbc关联监控的数据。
     * 在这里就可以看到监控
     *  http://localhost:8080/druid/weburi.html
     */
    @Bean
    public FilterRegistrationBean  webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>(webStatFilter);
        filterFilterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        /**
         * 处理器这些不关联监控
         */
        filterFilterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterFilterRegistrationBean;
    }
}
