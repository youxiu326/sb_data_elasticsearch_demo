package com.youxiu326.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 商品颜色表<br>
 * 例如:
 *      一双帆布鞋【product】
 * 有     红 白 黄【color】
 * 那么    一双红帆布鞋，一双白帆布鞋，一双黄帆布鞋【product】
 */
@Entity
@Table(name = "product_color")
public class ProductColor extends BaseEntity {

    private Product product;

    private Color color;

    private String img;

    /**
     * 对应商品
     * @return
     */
    @OneToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    /**
     * 对应颜色
     * @return
     */
    @OneToOne
    @JoinColumn(name = "color_id")
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * 封面图
     * @return
     */
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "ProductColor{" +
                "product=" + product +
                ", color=" + color +
                ", img='" + img + '\'' +
                '}';
    }
}