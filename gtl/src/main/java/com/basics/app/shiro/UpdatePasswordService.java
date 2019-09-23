package com.basics.app.shiro;

/**
 * 修改密码服务.
 * 
 * @author yuwenfeng
 *
 */
public interface UpdatePasswordService {

 public boolean accept(Object loginUser);

 public UpdatePasswordResponse updatePassword(Object loginUser, UpdatePasswordRequest request);
}
