package com.mrlu.es.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * @author 简单de快乐
 * @date 2021-08-26 20:46
 * indexName 表示索引名称
 * shards 配置主分片的数量，replicas配置副本名称
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "shopping")
@Setting(shards = 1,replicas = 1)
public class Product {
    /**
     * 商品唯一标识
     * @Id 必须有id,这里的id是全局唯一的标识，等同于 es 中的"_id"
     */
    @Id
    private Long id;

    /**
     * type : 字段数据类型
     * analyzer : 分词器类型
     * index : 是否索引(默认:true)
     * Keyword : 短语,不进行分词
     */

    /**
     *  商品名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    /**
     * 分类名称
     */
    @Field(type = FieldType.Keyword)
    private String category;

    /**
     * 商品价格
     */
    @Field(type = FieldType.Double)
    private Double price;
    /**
     * 图片地址
     *
     * index = false 表示不被索引
     */
    @Field(type = FieldType.Keyword, index = false)
    private String images;
}
