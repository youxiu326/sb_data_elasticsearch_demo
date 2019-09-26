package com.youxiu326.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 基础颜色
 */
@Entity
@Table(name = "color")
public class Color extends BaseEntity{

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}