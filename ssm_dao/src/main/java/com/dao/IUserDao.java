package com.dao;

import com.domain.Role;
import com.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.dao.IRoleDao.findRoleById")
            ),
    })
    User findByUserName(String username) throws Exception;

    @Select("select * from users")
    List<User> findAll();

    @Insert("insert into users(id,email,username,password,phoneNum,status) values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(User user) throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.dao.IRoleDao.findRoleById1")
            )
    })
    User findById(String id) throws Exception;

                                                                //in中只能写一个字段
    @Select("select * from role where id not in(select roleId from user_role where userId = #{id})")
    List<Role> findOtherRoles(String id);

    @Insert("insert into user_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);

}
