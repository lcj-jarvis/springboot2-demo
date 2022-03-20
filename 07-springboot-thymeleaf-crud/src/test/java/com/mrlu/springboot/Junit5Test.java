package com.mrlu.springboot;

import com.mrlu.springboot.bean.User;
import com.mrlu.springboot.dao.UserMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 11:40
 *
 * @ExtendWith :为测试类或测试方法提供扩展类引用
 *
 * @BootstrapWith(SpringBootTestContextBootstrapper.class)
 * @ExtendWith(SpringExtension.class)
 * public @interface SpringBootTest {
 *    ....
 * }
 */
@SpringBootTest
@DisplayName("Junit5单元测试")
public class Junit5Test {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


    @DisplayName("测试DisplayName注解")
    @Test
    void testDisplayName(){
       System.out.println(1);
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("单元测试就要开始了。。。");
    }

    @DisplayName("测试方法2")
    @Test
    void test2(){
        System.out.println(2);
    }

    @Disabled
    @DisplayName("测试方法3")
    @Test
    void test3(){
        System.out.println(3);
    }

    //超时就会停止测试
    /*@Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
    @Test
    void test4() throws InterruptedException {
        Thread.sleep(600);
    }*/

    @AfterEach
    void afterEach(){
        System.out.println("单元测试结束。。。");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("所有的单元测试就要开始了。。。");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("所有的单元测试结束。。。");
    }

    /**
     * 断言：前面断言失败，后面的代码不会执行
     */
    @DisplayName("测试简单的断言")
    @Test
    void testSimpleAssert(){
        int result = sum(3, 2);
        assertEquals(5,result,"业务逻辑计算失败");
        Object o = new Object();
        Object o1 = new Object();
        assertSame(o,o1,"两个对象不相等");

    }
    int sum(int i,int j){
        return  i + j;
    }

    @Test
    @DisplayName("array assertion")
    public void array() {
        assertArrayEquals(new int[]{1, 2}, new int[] {1, 2},"两个数组不相等");
    }

    /**
     * 组合断言全部需要成功
     */
    @Test
    @DisplayName("组合断言")
    void all(){
        assertAll("test",()-> assertArrayEquals(new int[]{1, 2}, new int[] {1, 2},"两个数组不相等"),
                ()->assertEquals(0,1));
    }


    @Test
    @DisplayName("异常断言")
    void testException(){
        //断定业务逻辑一定会发生异常
        assertThrows(ArithmeticException.class,()->{int i = 10 / 1;},"业务逻辑竟然没有发生异常?");
    }

    @Test
    @DisplayName("快速失败")
    void testFail(){
        fail("快速失败");
    }

    private final String environment = "DEV";
    //private final String environment = "PROD";

    /**
     * JUnit 5 中的前置条件（assumptions【假设】）类似于断言，
     * 不同之处在于不满足的断言会使得测试方法失败，而不满足的前置条件只会使得测试方法的执行终止。
     * 前置条件可以看成是测试方法执行的前提，当该前提不满足时，就没有继续执行的必要。
     *
     * assumeTrue 和 assumFalse 确保给定的条件为 true 或 false，不满足条件会使得测试执行终止。
     *
     */
    @Test
    @DisplayName("simple")
    public void simpleAssume() {
        Assumptions.assumeTrue(Objects.equals(this.environment, "DEV"));
        System.out.println(1111111);
        Assumptions.assumeFalse(() -> Objects.equals(this.environment, "PROD"));
    }

    /**
     * assumingThat 的参数是表示条件的布尔值和对应的 Executable 接口的实现对象。
     *   只有条件满足时，Executable 对象才会被执行；当条件不满足时，测试执行并不会终止。
     */
    @Test
    @DisplayName("assume then do")
    public void assumeThenDo() {
        Assumptions.assumingThat(
                Objects.equals(this.environment, "DEV"),
                () -> System.out.println("In DEV")
        );
    }

}
