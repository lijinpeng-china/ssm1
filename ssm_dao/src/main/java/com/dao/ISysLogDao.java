package com.dao;

import com.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {
    @Insert("insert into syslog values(null,#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog log);
    @Select("select * from syslog")
    List<SysLog> findAll();
}
