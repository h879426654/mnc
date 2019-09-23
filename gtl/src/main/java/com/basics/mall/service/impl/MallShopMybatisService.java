package com.basics.mall.service.impl;

import com.basics.mall.dao.MallProductDao;
import com.basics.mall.dao.MallShopDao;
import com.basics.mall.entity.MallProduct;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basics.app.dao.AppRoleDao;
import com.basics.app.dao.AppUserDao;
import com.basics.app.dao.AppUserRoleDao;
import com.basics.app.entity.AppRole;
import com.basics.app.entity.AppUser;
import com.basics.app.entity.AppUserRole;
import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.common.Constant;
import com.basics.cu.controller.request.ModifyPassRequest;
import com.basics.mall.entity.MallShop;
import com.basics.mall.service.MallShopService;
import com.basics.support.CommonSupport;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericMybatisService;
import com.basics.support.QueryFilterBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MallShopMybatisService extends GenericMybatisService<MallShop> implements MallShopService {
	
	@Autowired
	private AppUserDao appUserDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AppRoleDao appRoleDao;
	@Autowired
	private AppUserRoleDao appUserRoleDao;
	@Autowired
	private MallProductDao mallProductDao;

	/**
	 * 审核通过
	 */
	@Override
	public FormResultSupport doApplyTrade(MallShop entity) {
		FormResultSupport result = new FormResultSupport();
		MallShop shop = this.dao.get(entity.getId());
		CommonSupport.checkNotNull(shop, "商铺为空, id:"+entity.getId());
		AppUser user = new AppUser();
		user.setId(shop.getId());
		user.setCode(shop.getShopName());
		AppUser appUser = appUserDao.queryOne(new QueryFilterBuilder().put("code", shop.getShopName()).build());
		if(null != appUser) {
			result.onException("店铺已存在，审核失败");
			return result;
		}
		//赋权
		AppRole role = appRoleDao.queryOne(new QueryFilterBuilder().put("code", "SHOP").build());
		if (null == role) {
			result.onException("没有商家角色，暂时无法审核");
			return result;
		}
		user.setComment("商家");
		user.setState(Constant.STATE_YES);
		user.setName(shop.getShopName());
		user.setPassword(passwordEncoder.encode(shop.getShopPass()));
		
		appUserDao.save(user);
		AppUserRole userRole = new AppUserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(role.getId());
		appUserRoleDao.save(userRole);
		
		shop.setApplyStatus(Constant.APPLY_STATUS_2);
		this.dao.save(shop);
		result.onSuccess("审核成功");
		return result;
	}

	/**
	 * 修改商家密码
	 */
	@Override
	@Transactional
	public FormResultSupport modifyShopPass(ModifyPassRequest entity) {
		FormResultSupport result = new FormResultSupport();
		if (StringUtils.isBlank(entity.getOldPassword())) {
			result.onException("原密码不能为空");
			return result;
		}
		if (StringUtils.isBlank(entity.getNewPassword())) {
			result.onException("新密码不能为空");
			return result;
		}
		if (!entity.getNewPassword().equals(entity.getCode())) {
			result.onException("两次输入的密码不一致");
			return result;
		}
		UserSupport user = AppUserUtils.getCurrentUserSupport();
		AppUser appUser = appUserDao.get(user.getId());
		CommonSupport.checkNotNull(appUser, "商家不存在");
		if (!appUser.getPassword().equals(passwordEncoder.encode(entity.getOldPassword()))) {
			result.onException("原密码错误");
			return result;
		}
		appUser.setPassword(passwordEncoder.encode(entity.getNewPassword()));
		MallShop shop = this.dao.get(appUser.getId());
		CommonSupport.checkNotNull(shop, "商铺错误， id:"+shop.getId());
		shop.setShopPass(passwordEncoder.encode(entity.getNewPassword()));
		appUserDao.save(appUser);
		result.onSuccess("操作成功");
		return result;
	}

	/**
	 * 商家营停业
	 */
	@Override
	public FormResultSupport updateShopStatus(MallShop entity) {
		FormResultSupport result = new FormResultSupport();
		MallShop shop = this.dao.get(entity.getId());
		if(null == shop) {
			result.onException("商家不存在");
			return result;
		}
		shop.setShopStatus(Constant.SHOP_STATUS_1 == shop.getShopStatus() ? Constant.SHOP_STATUS_2 : Constant.SHOP_STATUS_1);
		this.dao.save(shop);
		result.onSuccess("修改成功");
		return result;
	}

	/**
	 * 删除商家
	 * @param entity
	 * @return
	 */
    @Override
    public FormResultSupport deleteShop(MallShop entity) {
		FormResultSupport result = new FormResultSupport();
		List<MallProduct> list = mallProductDao.query(new QueryFilterBuilder().put("shopId", entity.getId()).build());
		if(CollectionUtils.isNotEmpty(list)) {
			result.onException("该商家有产品, 不能删除!");
			return result;
		}

		this.deleteByPK(entity.getId());
		appUserDao.delete(entity.getId());
		AppUserRole userRole = appUserRoleDao.queryOne(new QueryFilterBuilder().put("userId", entity.getId()).build());
		appUserRoleDao.delete(userRole);

		result.onSuccess("修改成功");
		return result;
    }

}
