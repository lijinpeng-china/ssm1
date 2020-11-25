package com.ssm.controller;

import com.domain.Product;
import com.service.IProductService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//查询所有商品
@Controller
@RequestMapping("/product")
@RolesAllowed("ADMIN")
public class ProductController {
    @Autowired
    private IProductService productService;
    @RequestMapping("/findAll")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList",ps);
        mv.setViewName("product-list");
        return mv;
    }

    @RequestMapping("/save")
    public void save(Product product, HttpServletResponse response) throws Exception{
        System.out.println(product);
        product.setId(RandomStringUtils.randomAlphanumeric(32).toUpperCase());
        System.out.println(product.getId());
        Integer save = productService.save(product);
        if(save==1){
            response.getWriter().write("1");
        }else{
            response.getWriter().write("0");
        }
    }
}
