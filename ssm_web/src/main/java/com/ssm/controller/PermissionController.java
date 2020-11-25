package com.ssm.controller;

import com.domain.Permission;
import com.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mo = new ModelAndView();
        List<Permission> list = permissionService.findAll();
        mo.addObject("permissionList",list);
        mo.setViewName("permission-list");
        return mo;
    }

    @RequestMapping("/save")
    public String save(Permission p) throws Exception {
        permissionService.save(p);
        return "redirect:/permission/findAll";
    }
}
