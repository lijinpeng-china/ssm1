package com.service;

import com.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll();

    void save(Permission p) throws Exception;
}
