package com.basics.mall.service;

import com.basics.mall.entity.MallShopAdvert;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericService;

public interface MallShopAdvertService extends GenericService<MallShopAdvert> {
	
	/**
	 * 删除/恢复
	 */
	String delShopAdvert(MallShopAdvert mallShopAdvertService);

	/**
	 * 
	 * @param entity
	 * @return
	 */
	FormResultSupport doSave(MallShopAdvert entity);

}
