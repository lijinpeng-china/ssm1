package com.dao;

import com.domain.Member;
import com.domain.Orders;
import com.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "orderCount",column = "orderCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "product",column = "productId",
                    one = @One(select = "com.dao.IProductDao.findById")
            )
    })
    List<Orders> findAll() throws Exception;
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "orderCount",column = "orderCount"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "product",column = "productId",
                    javaType = Product.class,
                    one = @One(select = "com.dao.IProductDao.findById")
            ),
            @Result(property = "member",column = "memberId",
                    javaType = Member.class,
                    one = @One(select = "com.dao.IMemberDao.findById")
            ),
            @Result(property = "travellers",column = "id",
            javaType = List.class,
                    many = @Many(select = "com.dao.ITravellersDao.findById")
            )
    })
    Orders findById(String id) throws Exception;

}
