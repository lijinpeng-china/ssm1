package com.ssm.controller;

import com.domain.Role;
import com.domain.User;
import com.github.pagehelper.PageInfo;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;
    //添加用户
    @RequestMapping("/save")
    @PreAuthorize("authentication.principal.username == 'admin'")
    public String save(User user) throws Exception {
        Random random = new Random();
        int a = random.nextInt(10);
        int b = random.nextInt(10);
        String s = ""+a+a+a;
        String s1 = ""+b+b+b;
        user.setId(s+"-"+s1);
        System.out.println(user.getId());
        userService.save(user);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(@RequestParam(value = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size){
        ModelAndView mo = new ModelAndView();
        List<User> list = userService.findAll(page,size);
        PageInfo info = new PageInfo(list);
        mo.addObject("pageInfo",info);
        mo.setViewName("user-list");
        return mo;
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mo = new ModelAndView();
        User user = userService.findById(id);
        mo.addObject("user",user);
        mo.setViewName("user-show");
        return mo;
    }

    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户
        User user = userService.findById(id);
        //根据用户id查询可以添加的用户
        List<Role> list = userService.findOtherRoles(id);
        System.out.println(list);
        mv.addObject("user",user);
        mv.addObject("roleList",list);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId")String id,@RequestParam("ids")String[] roleIds) throws Exception{
        ModelAndView mv = new ModelAndView();
        userService.addRoleToUser(id,roleIds);

        return "redirect:/user/findAll";
    }

}
