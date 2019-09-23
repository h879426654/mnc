package com.basics.support.log4j;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.basics.support.Application;

/**
 * AppLogAppender.
 * 
 * @author yuwenfeng
 */
public class AppLogAppender extends AppenderSkeleton implements Appender {

 public static AppLogAppender INSTANCE;
 /** The buffer size. */
 protected int bufferSize = 10;

 protected String loggerLevels;

 public String getLoggerLevels() {
  return loggerLevels;
 }

 public void setLoggerLevels(String loggerLevels) {
  this.loggerLevels = loggerLevels;
 }

 /** The buffer. */
 protected List<LoggingEvent> buffer;

 /**
  * The Constructor.
  */
 public AppLogAppender() {
  buffer = new CopyOnWriteArrayList<LoggingEvent>();
  INSTANCE = this;
 }

 /*
  * (non-Javadoc)
  * 
  * @see
  * org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.LoggingEvent)
  */
 public void append(LoggingEvent event) {
  String loggerLevel = event.getLevel().toString();
  if (!StringUtils.containsIgnoreCase(this.loggerLevels, loggerLevel)) {
   return;
  }
  buffer.add(event);
  if (buffer.size() >= bufferSize) {
   flushBuffer();
  }
 }

 /*
  * (non-Javadoc)
  * 
  * @see org.apache.log4j.AppenderSkeleton#close()
  */
 public void close() {
  flushBuffer();
 }

 /**
  * Flush buffer.
  */
 public void flushBuffer() {
  if (buffer.isEmpty()) {
   return;
  }
  if (!Application.isContainerAvailable()) {
   return;
  }
  LoggingEvent[] logEventArray = new LoggingEvent[buffer.size()];
  buffer.toArray(logEventArray);
  flush(logEventArray);
  for (LoggingEvent logEvent : logEventArray) {
   buffer.remove(logEvent);
  }
 }

 List<AppLogSupportAware> awares = null;

 /**
  * Flush.
  *
  * @param logEvent
  *         the log event
  * @throws SQLException
  *          the SQL exception
  */
 public void flush(LoggingEvent... logEventArray) {
  try {
   AppLogSupport[] appLogArray = new AppLogSupport[logEventArray.length];
   for (int i = 0; i < logEventArray.length; i++) {
    appLogArray[i] = this.buildAppLog(logEventArray[i]);
   }
   if (awares == null) {
    awares = Application.getInstance().getServices(AppLogSupportAware.class);
   }
   if (awares.isEmpty()) {
    return;
   }
   for (AppLogSupportAware aware : this.awares) {
    aware.doAppend(appLogArray);
   }
  } catch (Throwable e) {
   e.printStackTrace();
   // ignore
  }
 }

 /*
  * (non-Javadoc)
  * 
  * @see org.apache.log4j.AppenderSkeleton#finalize()
  */
 public void finalize() {
  close();
 }

 /*
  * (non-Javadoc)
  * 
  * @see org.apache.log4j.AppenderSkeleton#requiresLayout()
  */
 public boolean requiresLayout() {
  return true;
 }

 /**
  * Sets the buffer size.
  *
  * @param newBufferSize
  *         the buffer size
  */
 public void setBufferSize(int newBufferSize) {
  bufferSize = newBufferSize;
 }

 /**
  * Gets the buffer size.
  *
  * @return the buffer size
  */
 public int getBufferSize() {
  return bufferSize;
 }

 /** The app id. */
 private String appId = "";

 /**
  * Gets the app id.
  *
  * @return the app id
  */
 public String getAppId() {
  return appId;
 }

 /**
  * Sets the app id.
  *
  * @param appId
  *         the app id
  */
 public void setAppId(String appId) {
  this.appId = appId;
 }

 /**
  * Builds the app log.
  *
  * @param event
  *         the event
  * @return the app log support
  */
 public AppLogSupport buildAppLog(LoggingEvent event) {
  AppLogSupport log = new AppLogSupport();
  try {
   log.setAppId(this.appId);
   String message = this.getLayout().format(event);
   log.setMessage(message);
   String stackTrace = "";
   if (event.getThrowableInformation() != null && event.getThrowableInformation().getThrowable() != null) {
    stackTrace = ExceptionUtils.getFullStackTrace(event.getThrowableInformation().getThrowable());
   }
   log.setStacktrace(stackTrace);
   String className = event.getLocationInformation().getClassName();
   log.setClassName(className);
   String fileName = event.getLocationInformation().getFileName();
   log.setFileName(fileName);
   String lineNumber = event.getLocationInformation().getLineNumber();
   log.setLineNumber(lineNumber);
   String methodName = event.getLocationInformation().getMethodName();
   log.setMethodName(methodName);
   String loggerName = event.getLoggerName();
   log.setName(loggerName);
   String loggerLevel = event.getLevel().toString();
   log.setLevel(loggerLevel);
   log.setCreateTime(new Date(event.timeStamp));
   log.setUpdateTime(log.getCreateTime());
  } catch (Throwable e) {

  }
  return log;
 }
}
