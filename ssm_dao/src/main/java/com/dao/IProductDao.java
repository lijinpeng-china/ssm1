package com.dao;

import com.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    //查询产品表所有信息
    @Select("select * from product")
    List<Product> findAll() throws Exception;
    //添加产品信息
    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    Integer save(Product product) throws Exception;
    //根据id查询商品
    @Select("select * from product where id = #{id}")
    Product findById(String id);
}
