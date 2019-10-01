package com.youxiu326.dao;

import com.youxiu326.entity.ProductColor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductColorDao extends BaseRepository<ProductColor,String>{

    public List<ProductColor> findByProductId(String productId);

}