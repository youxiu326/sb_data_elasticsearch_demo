package com.youxiu326.service;

import com.youxiu326.dao.BaseRepository;
import org.hibernate.SQLQuery;
import org.hibernate.service.spi.ServiceException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础抽象类 实现基础接口类定义方法
 * @param <T>
 * @param <ID>
 */
public class  AbstractService<T, ID extends Serializable> implements BaseService<T, ID> {

    @Autowired
    private EntityManager em;

    @Autowired
    protected BaseRepository<T, ID> dao;

    @Override
    public List<Map<String, Object>> findBySql(String sql){
        Query q = em.createNativeQuery(sql);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> result = q.getResultList();
        return result;
    }

    @Override
    public List<T> findAll(){
        return dao.findAll();
    }

}