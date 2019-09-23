package com.basics.common;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorMessageHelper {

 public static String praseErrorMessage(List<ObjectError> errors) {
  StringBuffer stringBuffer = new StringBuffer();
  for (ObjectError error : errors) {
   if (error instanceof FieldError) {
    FieldError fieldError = (FieldError) error;
//    stringBuffer.append("[" + fieldError.getField() + "]");
    stringBuffer.append(fieldError.getDefaultMessage());
    stringBuffer.append(",");
   }
  }
  return stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
 }

}
