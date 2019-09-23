package com.basics.support;

import java.util.ArrayList;
import java.util.List;

public class GroupDescriptor extends SortDescriptor {
 private List<AggregateDescriptor> aggregates;

 public GroupDescriptor() {
  aggregates = new ArrayList<AggregateDescriptor>();
 }

 public List<AggregateDescriptor> getAggregates() {
  return aggregates;
 }
}
