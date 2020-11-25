package com.service.impl;

import com.dao.IOrdersDao;
import com.domain.Orders;
import com.github.pagehelper.PageHelper;
import com.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page,int size) throws Exception{
        //参数pageNum是页码值，参数pageSize是每页显示条数，必须写在调用分页代码之前
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersid) throws Exception {
        Orders ors = ordersDao.findById(ordersid);
        return ors;
    }

}
