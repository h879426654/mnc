package com.basics.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.basics.app.entity.AppOption;
import com.basics.app.entity.Dictionary;
import com.basics.app.service.AppOptionService;
import com.basics.support.GenericMybatisService;
import com.basics.support.QueryFilter;
import com.basics.support.QueryFilterBuilder;

@Service
public class AppOptionMybatisService extends GenericMybatisService<AppOption> implements AppOptionService {

 public List<Dictionary> listDict(String dictGroup) {
  List<Dictionary> dicts = new ArrayList<Dictionary>();
  if (StringUtils.isNotBlank(dictGroup)) {
   QueryFilter filter = new QueryFilterBuilder().put("parentId", dictGroup).put("type", AppOption.TYPE_OPTION_ITEM).put("flag", 1).build();
   List<AppOption> options = this.query(filter);
   for (AppOption option : options) {
    Dictionary dict = new Dictionary();
    dict.setId(option.getCode());
    dict.setText(option.getName());
    dicts.add(dict);
   }
  }
  return dicts;
 }

 public void save(AppOption entity) {
  if (StringUtils.isBlank(entity.getParentId())) {
   entity.setParentId(AppOption.DEFAULT_PARENT);
  }
  super.save(entity);
 }

}
