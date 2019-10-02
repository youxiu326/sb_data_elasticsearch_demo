package com.youxiu326.es.service;

import com.google.common.collect.Lists;
import com.youxiu326.es.repo.ProductEsRepository;
import com.youxiu326.es.vo.ProductEs;
import com.youxiu326.service.ProductColorService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductEsService {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ProductEsRepository productEsRepository;

    @Autowired
    private ProductColorService productColorService;

    public List<ProductEs> findAll(){
        ArrayList<ProductEs> list = Lists.newArrayList(productEsRepository.findAll());
        return list;
    }

    /**
     * 分页查询
     * @param page 第几页
     * @param size 条数
     * @return
     */
    public List<ProductEs> findAllPageable(int page,int size){

        Pageable pageable = PageRequest.of(page, size);
        ArrayList<ProductEs> list = Lists.newArrayList(productEsRepository.findAll(pageable));

        /* NativeSearchQuery查询 */
        // 1.创建一个查询对象
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withPageable(PageRequest.of(0,15))
                .build();
        // 2.执行查询
        List<ProductEs> productEsList = template.queryForList(query, ProductEs.class);
        productEsList.forEach(it-> System.out.println(it));

        return list;
    }

    public ProductEs findById(String id){
        return productEsRepository.findById(id).orElse(null);
    }


}
