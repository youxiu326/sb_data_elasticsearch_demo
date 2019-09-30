package com.youxiu326.service.impl;

import com.youxiu326.entity.Color;
import com.youxiu326.entity.Product;
import com.youxiu326.service.AbstractService;
import com.youxiu326.service.ColorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class ColorServiceImpl extends AbstractService<Color, String> implements ColorService {

}