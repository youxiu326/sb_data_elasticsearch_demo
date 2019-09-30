package com.youxiu326.dao;

import com.youxiu326.entity.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductDao extends BaseRepository<Product,String>{

}