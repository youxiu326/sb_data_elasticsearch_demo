package com.youxiu326.service.impl;

import com.youxiu326.entity.Product;
import com.youxiu326.service.AbstractService;
import com.youxiu326.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ProductServiceImpl extends AbstractService<Product, String> implements ProductService {
} 