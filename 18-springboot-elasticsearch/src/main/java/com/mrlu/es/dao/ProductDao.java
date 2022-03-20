package com.mrlu.es.dao;

import com.mrlu.es.bean.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 简单de快乐
 * @date 2021-08-26 20:51
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {
}
