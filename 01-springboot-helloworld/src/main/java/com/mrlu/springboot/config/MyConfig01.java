package com.mrlu.springboot.config;

import com.mrlu.springboot.bean.Car;
import com.mrlu.springboot.bean.Pet;
import com.mrlu.springboot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

import java.util.Date;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-10 23:14
 *
 * 和spring的配置类是一样的。
 * 1、配置类型里面使用@Bean注解在方法上给容器注册组件。默认也是单实例的
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods:代理bean的方法
 *   Full模式：proxyBeanMethods = true 【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *   Lite模式：proxyBeanMethods = false【每个@Bean方法被调用多少次都是返回的组件都是新创建的】
 *
 *   最佳实战：
 *    （1）配置类罪案之间无依赖关系用Lite模式，加速容器启动过程，减少判断
 *    （2）配置类组件之间有依赖关系，方法会被调用得到之前单实例组件，使用Full模式。
 *
 *  @Import类型的注解和spring里用法一样
 *
 *  @Conditional的子注解：【SpringBoot特有】
 *
 *  @ConditionalOnJava 根据应用程序所运行的JVM版本进行匹配的
 *  @ConditionalOnClass 仅在指定的类位于类路径上时才匹配
 *  @ConditionalOnNotWebApplication 仅当应用程序上下文不是Web应用程序上下文时才匹配
 *  @ConditionalOnMissingClass 仅当指定的类不在类路径上时才匹配
 *  @ConditionalOnWebApplication 当应用程序是Web应用程序时匹配。
 *                              默认情况下，任何Web应用程序都会匹配，
 *                              但是可以使用{@link #type（）}属性来缩小它的范围
 *  @ConditionalOnBean  BeanFactory中已经包含满足所有指定要求的bean时才匹配。
 *                      为了满足条件，必须满足所有需求，但是相同的bean不一定要满足它们。
 *  @ConditionalOnProperty  检查指定的属性(配置文件里的)是否具有特定值。默认情况下，
 *                          属性必须出现在{@link Environment} 中，
 *                          并且<strong>不</ strong>等于{@code false}。
 *                            {@link #havingValue（）}和{@link #matchIfMissing（）}
 *                            属性允许进行进一步的自定义。
 *  @ConditionalOnSingleCandidate 仅当BeanFactory中已经包含指定类的bean并且可以确定单个候选对象时，该匹配项才匹配。
 *  @ConditionalOnMissingBean 仅当{@link BeanFactory}中没有包含满足指定要求的bean时才匹配。
 *                            不需要满足任何条件以使条件匹配，并且同一bean不必满足这些条件。
 *  @ConditionalOnResource 仅当指定资源位于类路径上时才匹配
 *
 *  @ImportResource("classpath:beans.xml")导入spring的配置文件。加载里面的内容
 */
@ImportResource("classpath:beans.xml")
@Import(value = {User.class, Date.class})
@Configuration(value = "myConfig",proxyBeanMethods = true)
@EnableConfigurationProperties(value = {Car.class})
public class MyConfig01 {
    /**
     * Full:外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
     * @return 给容器中添加组件。以方法名作为组件的id。
     *         返回类型就是组件类型。返回的值，就是组件在容器中的实例
     */
    @Bean
    public User user(){
        User user = new User("jack", 23);
        user.setPet(tom());
        return user;
    }

    @Bean
    public Pet tom(){
        return new Pet("tomcat");
    }

    /**
     * 当bean工厂中包含名字为pig，而且类型为Pet.class类型的bean时候，才创建user01
     * @return
     */
    @ConditionalOnBean(name = "pig",value = {Pet.class})
    @Bean
    public User user01(){
        User user = new User("jack", 23);
        user.setPet(pig());
        return user;
    }

    public Pet pig(){
        return new Pet("pig");
    }
}

