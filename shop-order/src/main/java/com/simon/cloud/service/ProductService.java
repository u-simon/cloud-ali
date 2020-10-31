package com.simon.cloud.service;

import com.simon.cloud.model.Product;
import com.simon.cloud.service.fallback.ProductServiceFallback;
import com.simon.cloud.service.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author simon
 * @date 2020/10/7 11:03 上午
 */
@FeignClient(value = "service-product",
        //fallback = ProductServiceFallback.class
        fallbackFactory = ProductServiceFallbackFactory.class
)
public interface ProductService {

    @RequestMapping("/product/{pid}")
    Product findById(@PathVariable("pid") Integer pid);

    @RequestMapping("/product/reduceInventory")
    void reduceInventory(@RequestParam("pid") Integer pid,@RequestParam("number") Integer number);
}
