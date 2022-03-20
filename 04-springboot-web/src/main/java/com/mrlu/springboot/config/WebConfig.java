package com.mrlu.springboot.config;

import com.mrlu.springboot.bean.Pet;
import com.mrlu.springboot.converter.GuiGuMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.*;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-12 14:10
 */
@Configuration
public class WebConfig {
        //implements WebMvcConfigurer {

    /**
     * 自定义HiddenHttpMethodFilter
     * 修改隐藏域的MethodParam为“_m”.即发送delete或者put请求，不发送_method请求参数
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_m");
        return hiddenHttpMethodFilter;
    }

    /**
     *
     * 第二种方式，自定义UrlPathHelper组件
     *
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){

        return new WebMvcConfigurer() {
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            /**
             * 自定义转换器
             * @param registry
             */
            @Override
            public void addFormatters(FormatterRegistry registry) {
                //注意，这里不要用lambda表达式，不然会发生异常。
                registry.addConverter(new Converter<String, Pet>() {
                    //啊猫,3
                    @Override
                    public Pet convert(String source) {
                        //Spring的工具类。判断字符串是否为空
                        if (StringUtils.hasText(source)) {
                            Pet pet = new Pet();
                            String[] strings = source.split(",");
                            pet.setName(strings[0]);
                            pet.setAge(strings[1]);
                            return pet;
                        }
                        return null;
                    }
                });
            }

            /**
             * 添加自定义的消息转换器到默认的转换器中
             * @param converters
             */
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new GuiGuMessageConverter());
            }

            /**
             * 自定义的内容协商管理器的媒体策略
             * @param configurer
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                //添加自定义的内容协商管理器的媒体策略
                Map<String, MediaType> mediaTypeMap = new HashMap<>();
                mediaTypeMap.put("json",MediaType.APPLICATION_JSON);
                mediaTypeMap.put("xml",MediaType.APPLICATION_ATOM_XML);
                mediaTypeMap.put("mytype",MediaType.parseMediaType("application/x-guigu"));
                //指定支持解析哪些参数对应的媒体类型
                ParameterContentNegotiationStrategy parameterContentNegotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypeMap);
                //还可以修改请求参数的名字不为format
                //parameterContentNegotiationStrategy.setParameterName("ff");

                /*
                【注意】注意如果不添加请求头内容的协商策略。
                 此时就没有HeaderContentNegotiationStrategy请求头内容的协商策略.
                 此时不以请求参数发生请求，直接发送请求。
                  Accept application/xml
                  Accept applicaaa
                  Accept请求头收到的内容都是json格式。
                  原因：
                    因为此时浏览器支持的媒体类型是任意的类型。
                    而服务器所有的媒体类型都可以和浏览器匹配，然后选出权重最大的（此时权重最大的是json类型）
                    所以都是响应json类型。

                  所以在配置自定义类型的媒体类型处理器的策略的时候。
                  springmvc原来的HeaderContentNegotiationStrategy请求头内容的协商策略也要加上
                 */
                //原来的也要加上
                HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(parameterContentNegotiationStrategy,headerContentNegotiationStrategy));
            }
        };
    }

    /**
     * 第一种方式：让SpringMVC的配置类实现WebMvcConfigurer接口，
     *          重写configurePathMatch方法添加UrlPathHelper组件
     *          设置removeSemicolonContent为false，就开启矩阵变量发送请求了。
     *
     */
    /*@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        //设置removeSemicolonContent为false。不移除分号的内容
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }*/


}
