package com.youxiu326.service;

import com.youxiu326.entity.ProductColor;

import java.util.List;

public interface ProductColorService extends BaseService<ProductColor, String>{

    public List<ProductColor> findByProductId(String productId);

}
