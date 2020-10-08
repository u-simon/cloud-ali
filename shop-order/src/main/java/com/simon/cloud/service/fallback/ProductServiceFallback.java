package com.simon.cloud.service.fallback;

import com.simon.cloud.model.Product;
import com.simon.cloud.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author simon
 * @date 2020/10/8 10:12 上午
 */
@Service
public class ProductServiceFallback implements ProductService {
    @Override
    public Product findById(Integer pid) {
        Product product = new Product();
        product.setPid(-100);
        return product;
    }
}
