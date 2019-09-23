package com.basics.support;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

 /**
  * Next int.
  *
  * @param from
  *         the from
  * @param to
  *         the to
  * @return the int
  */
 public static int nextInt(int from, int to) {
  return (int) (from + Math.random() * (to - 1 + from));
 }

 public static void sleepRandomSecond(int from, int to) throws InterruptedException {
  int second = nextInt(from, to);
  LogUtils.performance.info("wait for {} second.", second);
  Thread.sleep(TimeUnit.SECONDS.toMillis(second));
 }
}
