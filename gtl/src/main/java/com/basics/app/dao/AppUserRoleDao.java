package com.basics.app.dao;

import com.basics.app.entity.AppUserRole;
import com.basics.support.GenericDao;

public interface AppUserRoleDao extends GenericDao<AppUserRole> {

 /**
  * Save user roles.
  *
  * @param userId
  *         the user id
  * @param roleIds
  *         the role ids
  */
 public void saveUserRoles(String userId, String... roleIds);
}
