package com.simon.cloud.service;

import com.simon.cloud.model.Product;

/**
 * @author fengyue
 * @Date 2020/9/28 7:15 下午
 * @Describe
 */
public interface ProductService {
    /**
     *
     * @param pid
     * @return
     */
    Product findByPid(Integer pid);
}
