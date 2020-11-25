package com.service;


import com.domain.Orders;

import java.util.List;
public interface IOrderService {
    //查询所有商品
    List<Orders> findAll(int page,int size)  throws Exception;
    //
    Orders findById(String id) throws Exception;
}
