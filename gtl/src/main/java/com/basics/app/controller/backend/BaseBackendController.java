package com.basics.app.controller.backend;

import com.basics.support.ModelSupport;

public class BaseBackendController<T extends ModelSupport> extends com.basics.app.support.BackendControllerSupport<T> {

 @Override
 public String getBaseViewDirName() {
        return "com.basics.app.backend";
 }

}
