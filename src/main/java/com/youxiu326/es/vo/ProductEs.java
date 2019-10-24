package com.youxiu326.es.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(indexName = "product_index",type = "product")
public class ProductEs implements Serializable {

    /**
     * 颜色对象
     */
    public static class ProductColorEs{

        @Field(type = FieldType.Keyword)
        private String id;

        @Field(type = FieldType.Keyword)
        private String productId;

        @Field(type = FieldType.Keyword)
        private String colorCode;

        /**
         * ik分词器建议是索引时使用ik_max_word将搜索内容进行细粒度分词，
         * 搜索时使用ik_smart提高搜索精确性
         */
        @Field(type = FieldType.Text,store = true,searchAnalyzer = "ik_max_word",analyzer = "ik_smart")
        private String colorName;

        @Field(type = FieldType.Keyword)
        private String img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getColorCode() {
            return colorCode;
        }

        public void setColorCode(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getColorName() {
            return colorName;
        }

        public void setColorName(String colorName) {
            this.colorName = colorName;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    /**
     * 存储 索引index=true
     */
    @Id
    private String id;


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

    @Field(type = FieldType.Keyword,index = false)
    private String img;

    @Field(type = FieldType.Date,format = DateFormat.custom, pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Field(type = FieldType.Double)
    private double price;

    @Field(type = FieldType.Keyword)
    private String colorId;

    @Field(type = FieldType.Keyword)
    private String colorName;

    @Field(type = FieldType.Keyword)
    private String colorCode;


    /**
     * https://blog.csdn.net/laoyang360/article/details/82950393
     * 嵌套对象
     */
    @Field( type = FieldType.Nested)
    private List<ProductColorEs> colors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
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

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public List<ProductColorEs> getColors() {
        return colors;
    }

    public void setColors(List<ProductColorEs> colors) {
        this.colors = colors;
    }

    public ProductEs() {
    }

    public ProductEs(String id, String code, String keyword, String name, String img, Date createTime, double price, String colorId, String colorName, String colorCode) {
        this.id = id;
        this.code = code;
        this.keyword = keyword;
        this.name = name;
        this.img = img;
        this.createTime = createTime;
        this.price = price;
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorCode = colorCode;
    }

    @Override
    public String toString() {
        return "ProductEs{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", keyword='" + keyword + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", colorId='" + colorId + '\'' +
                ", colorName='" + colorName + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }
}
