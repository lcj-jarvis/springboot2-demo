package com.mrlu.es;

import com.mrlu.es.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;

/**
 * @author 简单de快乐
 * @date 2021-08-26 20:58
 */
@SpringBootTest
@Slf4j
public class EsIndexTest {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Test
    void createIndex() {
        // springboot启动完成会创建好索引
        // 发送get 请求：http://localhost:9200/_cat/indices?v 查看就知道了。
        System.out.println("创建索引成功");
    }

    @Test
    void deleteIndex() {

    }
}
