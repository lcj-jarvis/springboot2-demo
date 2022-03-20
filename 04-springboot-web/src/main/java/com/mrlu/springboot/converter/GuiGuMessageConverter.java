package com.mrlu.springboot.converter;

import com.mrlu.springboot.bean.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-15 15:54
 *
 * 自定义消息转换器
 */
public class GuiGuMessageConverter implements HttpMessageConverter<Person> {
    /**
     *  设置为false。不支持读
     */
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    /**
     * 如果是Person类型就可以写
     */
    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Person.class);
    }

    /**
     * 服务器要统计所有的MessageConverter都能写出哪些媒体类型
     *
     * application/x-guigu为自定义的媒体类型
     * @return 返回自定义的媒体类型的集合
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/x-guigu");
    }

    @Override
    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        //自定义协议数据的写法
        String str = person.getUserName()+";"+person.getAge()+";"+person.getBirth();
        //将数据返回给客户端
        OutputStream body = outputMessage.getBody();
        body.write(str.getBytes());
    }
}
