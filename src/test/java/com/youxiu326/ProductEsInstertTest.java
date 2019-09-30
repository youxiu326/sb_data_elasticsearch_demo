package com.youxiu326;

import com.youxiu326.entity.ProductColor;
import com.youxiu326.es.repo.ProductEsRepository;
import com.youxiu326.service.ProductColorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEsInstertTest {


    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ProductEsRepository productEsRepository;

    @Autowired
    private ProductColorService productColorService;

    @Test
    public void testInstert(){

        List<ProductColor> productColors = productColorService.findAll();
        productColors.forEach(it-> System.out.println(it));

    }

} 