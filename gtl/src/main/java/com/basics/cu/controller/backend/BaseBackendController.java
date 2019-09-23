package com.basics.cu.controller.backend;

import com.basics.support.ModelSupport;

public class BaseBackendController<T extends ModelSupport> extends com.basics.cu.support.BackendControllerSupport<T> {

 @Override
 public String getBaseViewDirName() {
 return "com.basics.cu.backend";
 }

 }

