package com.basics.mall.service.impl;

import com.basics.app.dao.AppImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basics.common.Constant;
import com.basics.mall.entity.MallShopAdvert;
import com.basics.mall.service.MallShopAdvertService;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericMybatisService;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class MallShopAdvertMybatisService extends GenericMybatisService<MallShopAdvert> implements MallShopAdvertService {

	@Autowired
	private AppImageDao appImageDao;

	
	/**
	 * 删除
	 */
	@Override
	public String delShopAdvert(MallShopAdvert mallShopAdvertService) {
		MallShopAdvert advert = this.dao.get(mallShopAdvertService.getId());
		if(null != advert) {
			this.dao.delete(advert);
			return "操作成功";
		}
		return "操作失败";
	}

	
	@Override
	public FormResultSupport doSave(MallShopAdvert entity) {
		FormResultSupport result = new FormResultSupport();
		MallShopAdvert advert = this.dao.get(entity.getId());
		if(Constant.APPLY_STATUS_1 != advert.getApplyStatus()) {
			result.onException("已审核");
		}
		dao.save(entity);
		if(Constant.APPLY_STATUS_3 == entity.getApplyStatus()) {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("ownerId", advert.getId());
			paramMap.put("ownerClass", "MallShopAdvert");
			appImageDao.deleteAll(paramMap);
		}
		result.onSuccess("操作成功");
		return result;
	}
	

 }
