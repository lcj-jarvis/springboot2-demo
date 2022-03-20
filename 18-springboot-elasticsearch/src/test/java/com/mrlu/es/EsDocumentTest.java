package com.mrlu.es;

import com.mrlu.es.bean.Product;
import com.mrlu.es.dao.ProductDao;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 简单de快乐
 * @date 2021-08-26 21:18
 */
@SpringBootTest
@Slf4j
public class EsDocumentTest {
    @Autowired
    private ProductDao productDao;

    // 新增
    @Test
    void addDocument() {
        // 发送get请求查看 http://localhost:9200/shopping/_doc/2

        // 添加文档数据
        Product product = new Product();
        product.setId(2L);
        product.setTitle("华为手机");
        product.setCategory("手机");
        product.setPrice(2999.0);
        product.setImages("http://www.atguigu/hw.jpg");
        productDao.save(product);
    }


    //修改
    @Test
    public void update(){
        Product product = new Product();
        product.setId(2L);
        product.setTitle("小米 2 手机");
        product.setCategory("手机");
        product.setPrice(9999.0);
        product.setImages("http://www.atguigu/xm.jpg");
        // 修改也还是save方法。第一次执行是新增，第二次执行是修改
        productDao.save(product);
    }

    //根据 id 查询
    @Test
    public void findById(){
        Product product = productDao.findById(1L).get();
        System.out.println(product);
    }

    // 查询所有
    @Test
    public void findAll() {
        Iterable<Product> products = productDao.findAll();
        products.forEach(System.out::println);
    }

    //删除
    @Test
    public void delete(){
        // 方式一
        /*Product product = new Product();
        product.setId(1L);
        productDao.delete(product);*/

        // 根据id删除
        productDao.deleteById(1L);
    }

    //批量新增
    @Test
    public void saveAll(){
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setTitle("["+i+"]小米手机");
            product.setCategory("手机");
            product.setPrice(1999.0+i);
            product.setImages("http://www.atguigu/xm.jpg");
            productList.add(product);
        }
        productDao.saveAll(productList);
    }

    // 批量删除——方式一
    @Test
    public void deleteAll01() {
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            idList.add((long) i);
        }
        productDao.deleteAllById(idList);
    }

    // 批量删除——方式二
    @Test
    public void deleteAll02() {
        // 参数用传包含商品的List，也可以不传
        productDao.deleteAll();
    }

    // 分页查询
    @Test
    public void findByPageable() {
        // 设置排序（排序方式，正序还是倒序，排序的id）
        // 先按照id降序排序,后按照价格升序
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Sort priceSort = Sort.by(Sort.Direction.ASC, "price");
        sort.and(priceSort);
        int from = 0;
        int limit = 5;
        PageRequest pageRequest = PageRequest.of(from, limit, sort);
        // 分页查询
        productDao.findAll(pageRequest).forEach(System.out::println);
    }

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    /**
     * 条件查询查询 + 分页 + 排序
     * 调用搜索方法，参数查询构建器对象
     */
    @Test
    public void termQuery(){
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("title", " 小米"))
                .withPageable(PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "id"))).build();
        SearchHits<Product> searchHits = elasticsearchTemplate.search(query, Product.class);
        searchHits.forEach(System.out::println);
    }
}
