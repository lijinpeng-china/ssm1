package com.service;

import com.domain.Product;

import java.util.List;

public interface IProductService {
    /*查询所有订单信息*/
    List<Product> findAll() throws Exception;
    /*添加订单信息*/
    Integer save(Product product) throws Exception;
}
