package com.basics.gty.controller.backend;

import com.basics.gty.support.BackendControllerSupport;
import com.basics.support.ModelSupport;

public class BaseBackendController<T extends ModelSupport> extends BackendControllerSupport<T> {

 @Override
 public String getBaseViewDirName() {
 return "com.basics.gty.backend";
 }

 }

