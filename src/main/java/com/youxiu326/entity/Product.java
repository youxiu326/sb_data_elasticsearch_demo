package com.youxiu326.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 商品实体类
 */
@Entity
@Table(name = "product")
public class Product extends BaseEntity{

    /**
     * ORDINARY 普通商品<br>
     * PRESELL 预售商品
     */
    public static enum Type{
        ORDINARY,PRESELL
    }

    private String keyword;

    private String code;

    private String name;

    private double price;

    private Type type = Type.ORDINARY;

    /**
     * 商品被搜索 keyword
     * @return
     */
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 商品编号
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 商品名称
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 商品价格
     * @return
     */
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 商品类型 【常规 / 预售】
     * @return
     */
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}