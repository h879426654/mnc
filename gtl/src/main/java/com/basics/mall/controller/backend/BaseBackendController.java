package com.basics.mall.controller.backend;

import com.basics.support.ModelSupport;

public class BaseBackendController<T extends ModelSupport> extends com.basics.mall.support.BackendControllerSupport<T> {

 @Override
 public String getBaseViewDirName() {
 return "com.basics.mall.backend";
 }

 }

