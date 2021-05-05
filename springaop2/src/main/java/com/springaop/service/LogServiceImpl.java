package com.springaop.service;

import com.springaop.dao.LogMapper;
import com.springaop.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements ILogService{

    @Autowired
    LogMapper logMapper;

    public void addLog(Log log) {
        logMapper.addLog(log);
    }
}
