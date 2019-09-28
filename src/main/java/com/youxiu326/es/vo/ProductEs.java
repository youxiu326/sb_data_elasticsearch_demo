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



}
