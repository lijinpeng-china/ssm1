package com.service;

import com.domain.Permission;
import com.domain.Role;

import java.util.List;

public interface IRoleService {
        List<Role> findAll();

    void save(Role role) throws Exception;

    Role findById(String id) throws Exception;

    List<Permission> findRoleByIdAndAllPermission(String id) throws Exception;

    void addPermissionToRole(String id, String[] roleIds);
}
