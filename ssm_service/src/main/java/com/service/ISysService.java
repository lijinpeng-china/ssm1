package com.service;

import com.domain.SysLog;

import java.util.List;

public interface ISysService {
    void save(SysLog log);

    List<SysLog> findAll(Integer page,Integer size);
}
