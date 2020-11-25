package com.dao;

import com.domain.Permission;
import com.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    //根据用户id查询出所有对应角色
    @Select("select * from role where id in (select roleId from user_role where userId=#{userId})")
    List<Role>  findRoleById(String id) throws Exception;


    @Select("select * from role where id in (select roleId from user_role where userId=#{id})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",javaType = List.class,column = "id",
                    many = @Many(select = "com.dao.IPermissionDao.findByRoleId")
            )
    })
    List<Role>  findRoleById1(String id) throws Exception;
    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id =#{id}")
    Role findById(String id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findRoleByIdAndAllPermission(String id);

    @Insert("insert into role_permission values(#{roleId},#{id})")
    void addPermissionToRole(@Param("id")String id,@Param("roleId")String roleId);
}
