package com.basics.mall.service;

import com.basics.cu.controller.request.ModifyPassRequest;
import com.basics.mall.entity.MallShop;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericService;

public interface MallShopService extends GenericService<MallShop> {

	//审核通过
	FormResultSupport doApplyTrade(MallShop entity);

	/**
	 * 修改商家密码
	 */
	FormResultSupport modifyShopPass(ModifyPassRequest entity);

	/**
	 * 商家营停业
	 */
	FormResultSupport updateShopStatus(MallShop entity);

	/**
	 * 删除商家
	 * @param entity
	 * @return
	 */
    FormResultSupport deleteShop(MallShop entity);
}
