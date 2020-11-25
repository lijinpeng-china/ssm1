package com.service.impl;

import com.dao.IProductDao;
import com.domain.Product;
import com.service.IProductService;
import com.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = productDao.findAll();
        for (Product p:list){
            p.setDepartureTimeStr(DateUtils.date2String(p.getDepartureTime(),"yyyy-MM-dd HH:mm:ss"));
        }
        return list;
    }

    @Override
    public Integer save(Product product) throws Exception {
        Integer save = productDao.save(product);
        return save;
    }
}
