package com.service;

import com.domain.Role;
import com.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> findAll(int page,int size);

    void save(User user) throws Exception;

    User findById(String id) throws Exception;

    List<Role> findOtherRoles(String id) throws Exception;

    void addRoleToUser(String id, String[] roleIds);
}
