package com.basics.tr.controller.backend;

import com.basics.support.ModelSupport;

public class BaseBackendController<T extends ModelSupport> extends com.basics.tr.support.BackendControllerSupport<T> {

 @Override
 public String getBaseViewDirName() {
 return "com.basics.tr.backend";
 }

 }

