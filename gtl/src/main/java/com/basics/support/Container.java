package com.basics.support;

import java.util.Map;

public abstract interface Container {
 public abstract void init(Object params);

 public abstract Object getComponent(Object key);

 public abstract <T> Map<String, T> getComponents(Class<T> clasz);

 public abstract void reload();

 public abstract void destroy();
}