package com.basics.app.dao;

/**
 * FormSubmitSupport.
 *
 * @param <Form>
 *         the generic type
 * @param <User>
 *         the generic type
 */
public interface FormSubmitSupport<Form, User> {

 /**
  * 提交.
  *
  * @param form
  *         the form
  * @param thisUser
  *         the this user
  */
 public void doSubmit(Form form, User thisUser);
}
