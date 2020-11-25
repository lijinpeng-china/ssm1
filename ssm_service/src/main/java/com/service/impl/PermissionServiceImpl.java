package com.service.impl;

import com.dao.IPermissionDao;
import com.domain.Permission;
import com.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "permissionService")
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        List<Permission> list = permissionDao.findAll();
        return list;
    }

    @Override
    public void save(Permission p) {
        permissionDao.save(p);
    }
}
