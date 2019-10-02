package com.youxiu326.controller;

import com.youxiu326.es.repo.ProductEsRepository;
import com.youxiu326.es.service.ProductEsService;
import com.youxiu326.es.vo.ProductEs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
@Api("商品ES搜索api")
public class ProductController {

    @Autowired
    private ProductEsService productEsService;


    @ApiOperation(value="获取商品列表", notes="获取商品列表")
    @RequestMapping(value = "/findAll",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductEs> findAll(){
        return productEsService.findAll();
    }

    /**Content-type常见类型：
     * application/x-www-form-urlencoded
     * application/json
     * multipart/form-data
     * text/xml
     * consumes = "application/x-www-form-urlencoded;charset=utf-8"     指定处理请求的提交内容类型（Content-Type），例如application/json, text/html
     * produces = "application/json;charset=utf-8"     返回json格式数据
     */


    @ApiOperation(value="获取分页商品列表", notes="获取分页商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, dataType = "int",paramType = "query")
    })
    @RequestMapping(value = "/findAllPageable",method = RequestMethod.GET,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductEs> findAllPageable(int page,int size){
        return productEsService.findAllPageable(page,size);
    }


    @ApiOperation(value="关键字搜索", notes="关键字搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", required = false, dataType = "String",paramType = "query"),
    })
    @RequestMapping(value = "/Keyword",method = RequestMethod.GET,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductEs> findByKeyword(@RequestParam(defaultValue = "") String keyword){
        return productEsService.findByKeyword(keyword);
    }

}
