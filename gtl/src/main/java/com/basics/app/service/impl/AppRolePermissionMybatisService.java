package com.basics.app.service.impl;

import org.springframework.stereotype.Service;

import com.basics.app.entity.AppRolePermission;
import com.basics.app.service.AppRolePermissionService;
import com.basics.support.GenericMybatisService;

@Service
public class AppRolePermissionMybatisService extends GenericMybatisService<AppRolePermission> implements AppRolePermissionService {

}
