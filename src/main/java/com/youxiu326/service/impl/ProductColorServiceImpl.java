package com.youxiu326.service.impl;

import com.youxiu326.entity.Product;
import com.youxiu326.entity.ProductColor;
import com.youxiu326.service.AbstractService;
import com.youxiu326.service.ProductColorService;
import com.youxiu326.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ProductColorServiceImpl extends AbstractService<ProductColor, String> implements ProductColorService {
} 