package com.youxiu326;

import com.youxiu326.entity.ProductColor;
import com.youxiu326.es.repo.ProductEsRepository;
import com.youxiu326.es.vo.ProductEs;
import com.youxiu326.es.vo.ProductEs.ProductColorEs;
import com.youxiu326.service.ProductColorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEsInstertTest {


    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ProductEsRepository productEsRepository;

    @Autowired
    private ProductColorService productColorService;

    /**
     * 创建Mapping
     * 查看Mapping：   http://49.235.105.251:9200/product_index/_mapping?pretty
     */
    @Test
    public void testPutMapping(){

        boolean b = template.putMapping(ProductEs.class);
        System.out.println(b);

    }

    /**
     * 新增数据
     */
    @Test
    public void testInstert(){

        List<ProductColor> productColors = productColorService.findAll();
        List<ProductEs> esList = new ArrayList<>();
        productColors.stream().forEach(it->{

            ProductEs es = new ProductEs();
            es.setId(it.getId());//商品颜色 id
            es.setImg(it.getImg()); // 商品图片地址
            es.setName(it.getProduct().getName());// 商品名称
            es.setKeyword(it.getProduct().getKeyword());// 商品关键词
            es.setPrice(it.getProduct().getPrice()); // 商品价格
            es.setCode(it.getProduct().getCode()); // 商品编号
            es.setColorId(it.getColor().getId()); // 颜色id
            es.setColorName(it.getColor().getName());// 颜色名称
            es.setColorCode(it.getColor().getCode()); // 颜色编号
            es.setCreateTime(it.getCreateTime()); // 创建时间
            // 设置其他颜色集合
            // 例如: 帆布鞋 有红 白 黄三种颜色 ，红帆布鞋(ProductEs) 其他颜色对象有红帆布鞋，白帆布鞋，黄帆布鞋(ProductColorEs)
            es.setColors(getColors(it.getProduct().getId()));
            esList.add(es);
        });

        productEsRepository.saveAll(esList);

    }

    private List<ProductColorEs> getColors(String productId){
        List<ProductColorEs> productColorEsList = new ArrayList<>();
        List<ProductColor> productColors = productColorService.findByProductId(productId);
        if (productColors.isEmpty())
            return null;
        productColors.stream().forEach(it->{
            ProductColorEs productColorEs = new ProductColorEs();
            productColorEs.setId(it.getId());//商品颜色id
            productColorEs.setColorCode(it.getColor().getCode());//颜色编号
            productColorEs.setColorName(it.getColor().getName());//颜色名称
            productColorEs.setImg(it.getImg());//展示图
            productColorEs.setProductId(it.getProduct().getId());//商品id
            productColorEsList.add(productColorEs);
        });

        return productColorEsList;

    }

} 