package com.ssm.controller;

import com.domain.Orders;
import com.github.pagehelper.PageInfo;
import com.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrderService service;
    @RequestMapping("/findAll")
    //required:强制必须要有这个属性传过来
    public ModelAndView findAll(@RequestParam(value = "page",required = true,defaultValue = "1")  Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Orders> list = service.findAll(page,size);
        //pageInfo就是一个分页的bean
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    //订单详情查询
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(value = "id",required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders ors = service.findById(id);
        mv.addObject("orders",ors);
        mv.setViewName("orders");
        return mv;
    }
}
