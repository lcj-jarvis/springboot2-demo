package com.mrlu.springboot.service;

import com.mrlu.springboot.bean.City;
import com.mrlu.springboot.dao.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-16 20:53
 */
@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;


    public City getCity(Integer id){
        return cityMapper.getCity(id);
    }


    public boolean saveCity(City city){
        return cityMapper.insertCity(city);
    }
}
