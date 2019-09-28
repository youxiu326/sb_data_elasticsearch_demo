package com.youxiu326.es.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "product_index",type = "product")
public class ProductEs implements Serializable {

    /**
     * 存储 索引index=true
     */
    @Id
    @Field(type = FieldType.Long,store = true)
    private long id;


    /**
     * keyword：不分词，没有把es中的对象进行分词处理，而是存入了整个对象
     */
    @Field(type = FieldType.Keyword)
    private String code;

    /**
     * ik分词器建议是索引时使用ik_max_word将搜索内容进行细粒度分词，
     * 搜索时使用ik_smart提高搜索精确性
     */
    @Field(type = FieldType.Text,store = true,searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
    private String keyword;

    @Field(type = FieldType.Text,store = true,searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
    private String name;

    @Field(type = FieldType.Date,format = DateFormat.custom, pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Field(type = FieldType.Double)
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductEs() {
    }

    public ProductEs(long id, String code, String keyword, String name, Date createTime, double price) {
        this.id = id;
        this.code = code;
        this.keyword = keyword;
        this.name = name;
        this.createTime = createTime;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductEs{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", keyword='" + keyword + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                '}';
    }
}
