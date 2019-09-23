package com.basics.sys.controller.backend;

import com.basics.support.ModelSupport;

public class BaseBackendController<T extends ModelSupport> extends com.basics.sys.support.BackendControllerSupport<T> {

 @Override
 public String getBaseViewDirName() {
 return "com.basics.sys.backend";
 }

 }

