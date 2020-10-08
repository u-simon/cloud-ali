package com.simon.cloud.service.fallback;

import com.simon.cloud.model.Product;
import com.simon.cloud.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author simon
 * @date 2020/10/8 10:26 上午
 */
@Slf4j
@Component
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable cause) {
        return pid -> {
            log.error("{}", cause);
            Product product = new Product();
            product.setPid(-100);
            return product;
        };
    }
}
