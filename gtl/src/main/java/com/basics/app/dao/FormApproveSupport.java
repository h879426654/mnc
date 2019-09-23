package com.basics.app.dao;

/**
 * FormApproveSupport.
 *
 * @author yuwenfeng.
 * @param <Form>
 *         the generic type
 * @param <User>
 *         the generic type
 */
public interface FormApproveSupport<Form, User> {

 /**
  * 审核通过.
  *
  * @param form
  *         the form
  * @param thisUser
  *         the this user
  */
 public void doApprove(Form form, User thisUser);
}
