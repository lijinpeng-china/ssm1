package com.service.impl;

import com.dao.ISysLogDao;
import com.domain.SysLog;
import com.github.pagehelper.PageHelper;
import com.service.ISysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysServiceImpl implements ISysService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(SysLog log) {
        sysLogDao.save(log);
    }

    @Override
    public List<SysLog> findAll(Integer page,Integer size) {
        //参数pageNum是页码值，参数pageSize是每页显示条数，必须写在调用分页代码之前
        PageHelper.startPage(page,size);
        return  sysLogDao.findAll();
    }
}
