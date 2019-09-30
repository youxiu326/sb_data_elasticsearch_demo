package com.youxiu326.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础接口类
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T,ID extends Serializable> {

    public List<Map<String, Object>> findBySql(String sql);

    public  List<T> findAll();
} 