package com.mrlu.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import java.util.stream.Stream;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-19 13:46
 *
 * 参数化测试
 *
 * @ValueSource: 为参数化测试指定入参来源，支持八大基础类以及String类型,Class类型
 * @NullSource: 表示为参数化测试提供一个null的入参
 * @EnumSource: 表示为参数化测试提供一个枚举入参
 * @CsvFileSource：表示读取指定CSV文件内容作为参数化测试入参
 * @MethodSource：表示读取指定方法的返回值作为参数化测试入参(注意方法返回需要是一个流)
 */
public class ParameterTest {

    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    @DisplayName("参数化测试1")
    public void parameterizedTest1(String string) {
        System.out.println(string);
        Assertions.assertTrue(StringUtils.isNotBlank(string));
    }


    @ParameterizedTest
    @MethodSource("method")    //指定方法名，注意方法返回需要是一个流
    @DisplayName("方法来源参数")
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println(name);
        Assertions.assertNotNull(name);
    }

    static Stream<String> method() {
        return Stream.of("apple", "banana");
    }
}
