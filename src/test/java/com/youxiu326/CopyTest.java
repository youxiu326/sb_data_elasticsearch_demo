package com.youxiu326;

import com.youxiu326.es.repo.ProductEsRepository;
import com.youxiu326.es.vo.ProductEs;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


/**
 * @Link https://blog.csdn.net/weixin_42295717/article/details/86293592
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopyTest {

    //复杂查询用
    @Autowired
    ElasticsearchTemplate template;
    
    //做增删改查用
    @Autowired
    ProductEsRepository repository;
    
    @org.junit.Test
    public void TestCreat(){
        //创建索引库
        template.createIndex(ProductEs.class);
        //创建映射
        template.putMapping(ProductEs.class);
    }

    @org.junit.Test
    public void getIndex() {
        //查询所有
        Iterable<ProductEs> all = repository.findAll();
        for (ProductEs ProductEs : all) {
            System.out.println(ProductEs);
        }
    }
    @org.junit.Test
    public void getIndex2() {
        //自定义条件查询
        Iterable<ProductEs> all = repository.findAll();
        for (ProductEs ProductEs : all) {
            System.out.println(ProductEs);
        }
    }
    @org.junit.Test
    public void getIndex3() {
        //创建原生查询器
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //创建查询条件
        builder.withQuery(QueryBuilders.matchQuery("title", "小米手机"));
        //创建过滤条件
        builder.withSourceFilter(new FetchSourceFilter(new String[]{"title","price"},null));
        //创建排序条件
        builder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        //分页(从0开始)
        builder.withPageable(PageRequest.of(0,2 ));
        //建立查询
        SearchQuery query = builder.build();
        Page<ProductEs> ProductEss = repository.search(query);
        System.out.println("总条数："+ProductEss.getTotalElements());
        System.out.println("总页数："+ProductEss.getTotalPages());
        List<ProductEs> content = ProductEss.getContent();
        for (ProductEs ProductEs : content) {
            System.out.println("内容："+ProductEs);
        }
    }

    /**
     * 测试聚合查询
     */
    @Test
    public void testAggs(){

        //创建原生查询器
        NativeSearchQueryBuilder queryBuilder  = new NativeSearchQueryBuilder();
        // 聚合 聚合 keyword类型，text类型会报错
        // http://youxiu326.com:9200/product_index/_mapping?pretty
        queryBuilder.addAggregation(AggregationBuilders.terms("colorNameTerm").field("colorName"));
        //查询并返回带聚合结果
        AggregatedPage<ProductEs> result = template.queryForPage(queryBuilder.build(), ProductEs.class);
        //解析聚合
        Aggregations aggs = result.getAggregations();
        //获取指定名称的聚合
        StringTerms terms =aggs.get("colorNameTerm");
        //获取桶
        List<StringTerms.Bucket> buckets = terms.getBuckets();
        //获取数据
        for (StringTerms.Bucket bucket : buckets) {
            System.out.println("key:"+bucket.getKey());
            System.out.println("docCount:"+bucket.getDocCount());
        }

    }


}