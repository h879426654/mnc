package com.basics.mall.dao;
import java.util.List;

import com.basics.mall.entity.MallProductKindContrast;
import com.basics.mall.vo.KindValueVo;
import com.basics.mall.vo.MainKindVo;
import com.basics.support.GenericDao;

public interface MallProductKindContrastDao extends GenericDao<MallProductKindContrast> {
	
	List<MainKindVo> findProductMainKinds(String id);

	List<KindValueVo> findProductKindValue(String id, String kindId);

}