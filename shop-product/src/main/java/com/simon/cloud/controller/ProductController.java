package com.simon.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.simon.cloud.service.ProductService;
import com.simon.cloud.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/api1/demo1")
    public String demo1(){
        return "demo";
    }

    @RequestMapping("/api1/demo2")
    public String demo2(){
        return "demo";
    }

    @RequestMapping("/api2/demo1")
    public String demo3(){
        return "demo";
    }

    @RequestMapping("/api2/demo2")
    public String demo4(){
        return "demo";
    }

    @RequestMapping("/{pid}")
    public Product query(@PathVariable("pid") Integer pid) {
        log.info("接下来要进行{}号商品的查询", pid);

        Product product = productService.findByPid(pid);
        log.info("商品信息查询成功,内容为:{}", JSON.toJSONString(product));

        return product;
    }
}
