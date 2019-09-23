package com.basics.app.support;

import java.util.Comparator;
import java.util.Date;

public class TimeSortClass implements Comparator<Date> {
 public int compare(Date o1, Date o2) {
  return o1.compareTo(o2);
 }

}
