package com.ssm.controller;

import com.domain.Permission;
import com.domain.Role;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/save")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:/role/findAll";
    }


    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mo = new ModelAndView();
        List<Role> list =   roleService.findAll();
        mo.addObject("roleList",list);
        mo.setViewName("role-list");
        return mo;
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据roleId查询role
        Role role = roleService.findById(id);
        //根据roleId查询可以添加的权限
        List<Permission> list = roleService.findRoleByIdAndAllPermission(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",list);
        mv.setViewName("role-permission-add");
        return mv;
    }
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId")String id, @RequestParam("ids")String[] roleIds) throws Exception{
        ModelAndView mv = new ModelAndView();
        roleService.addPermissionToRole(id,roleIds);

        return "redirect:/role/findAll";
    }
}
