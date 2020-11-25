package com.dao;

import com.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findByRoleId(String id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll();
    @Insert("insert into permission values(#{id},#{permissionName},#{url})")
    void save(Permission p);
}
