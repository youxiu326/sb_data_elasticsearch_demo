package com.youxiu326.service.impl;

import com.youxiu326.dao.ProductColorDao;
import com.youxiu326.entity.Product;
import com.youxiu326.entity.ProductColor;
import com.youxiu326.service.AbstractService;
import com.youxiu326.service.ProductColorService;
import com.youxiu326.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ProductColorServiceImpl extends AbstractService<ProductColor, String> implements ProductColorService {

    @Autowired
    private ProductColorDao productColorDao;

    @Override
    public List<ProductColor> findByProductId(String productId) {
        return productColorDao.findByProductId(productId);
    }
}