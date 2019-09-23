package com.basics.app.dao;

/**
 * FormRejectSupport.
 *
 * @author yuwenfeng.
 * @param <Form>
 *         the generic type
 * @param <User>
 *         the generic type
 */
public interface FormRejectSupport<Form, User> {

 /**
  * 审核通过.
  *
  * @param form
  *         the form
  * @param thisUser
  *         the this user
  */
 public void doReject(Form form, User thisUser);
}
