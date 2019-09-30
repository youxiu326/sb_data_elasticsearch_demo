package com.youxiu326.es.vo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Deprecated
@Document(indexName = "sdes_blog",type = "article")
public class Article implements Serializable {

    /**
     * 存储 索引index=true
     */
    @Id
    @Field(type = FieldType.Long,store = true)
    private long id;

    /**
     * ik分词器建议是索引时使用ik_max_word将搜索内容进行细粒度分词，
     * 搜索时使用ik_smart提高搜索精确性
     */
    @Field(type = FieldType.Text,store = true,searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text,store = true,searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
