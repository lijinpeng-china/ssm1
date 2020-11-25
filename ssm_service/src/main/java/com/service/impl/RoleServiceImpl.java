package com.service.impl;

import com.dao.IRoleDao;
import com.domain.Permission;
import com.domain.Role;
import com.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll() {
        List<Role> list = roleDao.findAll();
        return list;
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);

    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) throws Exception {
        List<Permission> list = roleDao.findRoleByIdAndAllPermission(id);
        return list;
    }

    @Override
    public void addPermissionToRole(String id, String[] roleIds) {
        for (String roleId:roleIds){
            roleDao.addPermissionToRole(id,roleId);
        }
    }
}
