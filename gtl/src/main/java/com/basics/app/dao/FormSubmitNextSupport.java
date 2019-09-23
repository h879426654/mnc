package com.basics.app.dao;

/**
 * FormSubmitNextSupport.
 *
 * @param <Form>
 *         the generic type
 * @param <User>
 *         the generic type
 */
public interface FormSubmitNextSupport<Form, User> {

 /**
  * 提交.
  *
  * @param form
  *         the form
  * @param thisUser
  *         the this user
  */
 public void doSubmitNext(Form form, User thisUser);
}
