package com.basics.cu.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basics.common.Constant;
import com.basics.cu.dao.CuCustomerCountDao;
import com.basics.cu.dao.CuCustomerLoginDao;
import com.basics.cu.entity.CuCustomerCount;
import com.basics.cu.entity.CuCustomerInfo;
import com.basics.cu.entity.CuCustomerLogin;
import com.basics.cu.service.CuCustomerInfoService;
import com.basics.support.CommonSupport;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericMybatisService;
import com.basics.support.QueryFilterBuilder;
import com.basics.sys.entity.SysCustomerLevel;

@Service
@Transactional
public class CuCustomerInfoMybatisService extends GenericMybatisService<CuCustomerInfo> implements CuCustomerInfoService {

	@Autowired
	private CuCustomerLoginDao cuCustomerLoginDao;
	@Autowired
	private CuCustomerCountDao cuCustomerCountDao;
	
	@Override
	@Transactional
	public void updateCustomerStatus(CuCustomerInfo customerInfo) {
		if (customerInfo.getCustomerStatus().intValue() == 1) {
			customerInfo.setCustomerStatus(Constant.STATE_NO);
			this.dao.save(customerInfo);
			CuCustomerLogin login = new CuCustomerLogin();
			login.setId(customerInfo.getId());
			login.setCustomerStatus(Constant.STATE_NO);
			cuCustomerLoginDao.save(login);
		} else {
			customerInfo.setCustomerStatus(Constant.STATE_YES);
			this.dao.save(customerInfo);
			CuCustomerLogin login = new CuCustomerLogin();
			login.setId(customerInfo.getId());
			login.setCustomerStatus(Constant.STATE_YES);
			cuCustomerLoginDao.save(login);
		}
	}

	/**
	 * 禁止交易
	 */
	@Override
	public String banCustomerFlagTrade(String id) {
		if(StringUtils.isNotBlank(id)) {
			CuCustomerInfo info = this.dao.get(id);
			CommonSupport.checkNotNull(info, "用户信息错误， id:"+id);
			if(Constant.STATE_YES == info.getFlagTrade()) {
				info.setFlagTrade(Constant.STATE_NO);
			} else {
				info.setFlagTrade(Constant.STATE_YES);
			}
			this.dao.save(info);
			return "操作成功";
		}
		return "id不能为空";
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public FormResultSupport updateCustomerInfo(CuCustomerInfo entity) {
		FormResultSupport result = new FormResultSupport();
		CuCustomerInfo customer = this.dao.get(entity.getId());
		Map<String, Object> map = new HashMap<>(); 
		map.put("customerPhone", entity.getCustomerPhone());
		long num = this.dao.count(map);
		if(Constant.STATE_YES < num) {
			result.onException("手机号已存在");
			return result;
		} else if(Constant.STATE_YES == num && ! customer.getCustomerPhone().equals(entity.getCustomerPhone())) {
			result.onException("手机号已存在");
			return result;
		}
		this.dao.save(entity);
		CuCustomerLogin login = cuCustomerLoginDao.get(entity.getId());
		login.setCustomerPhone(entity.getCustomerPhone());
		cuCustomerLoginDao.save(login);
		result.onSuccess("修改成功");
		return result;
	}

	/**
	 * 设置等级
	 */
	@Override
	public FormResultSupport doSetLevel(CuCustomerCount entity) {
		FormResultSupport result = new FormResultSupport();
		entity.setFlagLevelAuto(Constant.STATE_NO);
		cuCustomerCountDao.save(entity);
		result.onSuccess("设置成功");
		return result;
	}

 }
