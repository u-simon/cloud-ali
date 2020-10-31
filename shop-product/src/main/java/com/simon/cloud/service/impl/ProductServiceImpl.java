package com.simon.cloud.service.impl;

import com.simon.cloud.dao.ProductDao;
import com.simon.cloud.service.ProductService;
import com.simon.cloud.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengyue
 * @Date 2020/9/28 7:15 下午
 * @Describe
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    public void reduceInventory(Integer pid, Integer number) {
        Product product = productDao.findById(pid).get();
        product.setStock(product.getStock() - number);
        productDao.save(product);
    }
}
