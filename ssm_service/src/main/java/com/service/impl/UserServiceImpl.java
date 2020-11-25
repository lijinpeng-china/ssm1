package com.service.impl;

import com.dao.IUserDao;
import com.domain.Role;
import com.domain.User;
import com.github.pagehelper.PageHelper;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = iUserDao.findByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.getStatus() == 0 ? false:true,true,true,true, getAuthoity(user.getRoles()));
        return user1;
    }

    //返回一个list集合,集合装入的是权限的描述
    private List<SimpleGrantedAuthority> getAuthoity(List<Role> roles) {
        List list = new ArrayList<>();
        for (Role role:roles) {
            System.err.println(role);
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<User> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return iUserDao.findAll();
    }

    @Override
    public void save(User user) throws Exception {
        //对密码进行加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user.getId());
        iUserDao.save(user) ;
    }

    @Override
    public User findById(String id) throws Exception{
        User user = iUserDao.findById(id);
        System.out.println(user);
        return user;
    }

    @Override
    public List<Role> findOtherRoles(String id) throws Exception {
        List<Role> list = iUserDao.findOtherRoles(id);
        return list;
    }

    @Override
    public void addRoleToUser(String id, String[] roleIds) {
        for (String roleid:roleIds){
            iUserDao.addRoleToUser(id,roleid);
        }
    }
}

//在配置文件中定义了登录页面，登录的路径，成功页面，失败页面，还定义额操作的具体的service
//实际,spring-Security实现了controller层的调用，成功以及失败操作时，页面的反馈。
