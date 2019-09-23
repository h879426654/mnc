package com.basics.mall.dao.MybatisDao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallProductKindContrastDao;
import com.basics.mall.entity.MallProductKindContrast;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.mall.vo.KindValueVo;
import com.basics.mall.vo.MainKindVo;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.support.QueryFilterBuilder;

@Repository
public class MallProductKindContrastMybatisDao extends GenericMybatisDaoSupport<MallProductKindContrast>
		implements MallProductKindContrastDao {

	public MallProductKindContrastMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "CONTRAST_ID");
		defaultNameMapper.configFieldColumnName("kindId", "KIND_ID");
		defaultNameMapper.configFieldColumnName("kindDetailId", "KIND_DETAIL_ID");
		defaultNameMapper.configFieldColumnName("productId", "PRODUCT_ID");
		return defaultNameMapper;
	}

	@Override
	public List<MainKindVo> findProductMainKinds(String id) {
		QueryFilterBuilder queryFilterBuilder = new QueryFilterBuilder();
		queryFilterBuilder.put("productId", id);
		return this.queryExtend(queryFilterBuilder.build(), "queryProductMainKind");
	}

	@Override
	public List<KindValueVo> findProductKindValue(String id, String kindId) {
		QueryFilterBuilder queryFilterBuilder = new QueryFilterBuilder();
		queryFilterBuilder.put("productId", id);
		queryFilterBuilder.put("kindId", kindId);
		return this.queryExtend(queryFilterBuilder.build(), "queryProductKindValue");
	}

}
