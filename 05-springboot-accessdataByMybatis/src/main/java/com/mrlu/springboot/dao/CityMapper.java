package com.mrlu.springboot.dao;

import com.mrlu.springboot.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author Mr.Lu
 * @version
 * @email 1802772962@qq.com
 * @createDate 2021-03-16 20:51
 */
//@Mapper
public interface CityMapper {

    @Select("select * from city where id = #{id}")
    City getCity(Integer id);

    /**
     * @Options 可以设置响应的sql映射文件对应标签的值
     * @param city
     * @return
     */
    @Insert("insert into city(name,state,country) values(#{name},#{state},#{country})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    boolean insertCity(City city);
}
