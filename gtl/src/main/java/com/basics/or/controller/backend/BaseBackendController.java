package com.basics.or.controller.backend;

import com.basics.support.ModelSupport;

public class BaseBackendController<T extends ModelSupport> extends com.basics.or.support.BackendControllerSupport<T> {

 @Override
 public String getBaseViewDirName() {
 return "com.basics.or.backend";
 }

 }

