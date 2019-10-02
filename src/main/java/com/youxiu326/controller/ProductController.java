package com.youxiu326.controller;

import com.youxiu326.es.repo.ProductEsRepository;
import com.youxiu326.es.service.ProductEsService;
import com.youxiu326.es.vo.ProductEs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
@Api("商品ES搜索api")
public class ProductController {

    @Autowired
    private ProductEsService productEsService;


    @ApiOperation(value="获取商品列表", notes="获取商品列表")
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<ProductEs> findAll(){
        return productEsService.findAll();
    }

    @ApiOperation(value="获取分页商品列表", notes="获取分页商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "条数", required = true, dataType = "int")
    })
    @RequestMapping(value = "/findAllPageable",method = RequestMethod.GET)
    public List<ProductEs> findAllPageable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20")int size){
        return productEsService.findAllPageable(page,size);
    }


}
