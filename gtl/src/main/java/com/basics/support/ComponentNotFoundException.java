package com.basics.support;

public class ComponentNotFoundException extends RuntimeException {
 private static final long serialVersionUID = 2632596697722185604L;

 public ComponentNotFoundException(String message) {
  super(message);
 }

 public ComponentNotFoundException(String message, Throwable cause) {
  super(message, cause);
 }
}