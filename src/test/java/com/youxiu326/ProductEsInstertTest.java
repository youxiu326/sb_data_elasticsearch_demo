package com.youxiu326;

import com.youxiu326.es.repo.ProductEsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEsInstertTest {


    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ProductEsRepository productEsRepository;


    @Test
    public void testInstert(){



    }

} 