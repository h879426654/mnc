package com.basics.app.service.impl;

import org.springframework.stereotype.Service;

import com.basics.app.entity.AppLog;
import com.basics.app.service.AppLogService;
import com.basics.support.GenericMybatisService;

@Service
public class AppLogMybatisService extends GenericMybatisService<AppLog> implements AppLogService {

 }
