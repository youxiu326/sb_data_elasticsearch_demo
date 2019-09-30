package com.youxiu326.es.repo;

import com.youxiu326.es.vo.Article;
import com.youxiu326.es.vo.ProductEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductEsRepository extends ElasticsearchRepository<ProductEs,String> {

} 