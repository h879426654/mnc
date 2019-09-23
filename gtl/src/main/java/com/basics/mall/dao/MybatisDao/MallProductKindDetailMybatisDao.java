package com.basics.mall.dao.MybatisDao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.basics.mall.dao.MallProductKindDetailDao;
import com.basics.mall.entity.MallProductKindDetail;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.mall.vo.KindVo;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import com.basics.support.QueryFilterBuilder;

@Repository
public class MallProductKindDetailMybatisDao extends GenericMybatisDaoSupport<MallProductKindDetail>
		implements MallProductKindDetailDao {

	public MallProductKindDetailMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "DETAIL_ID");
		defaultNameMapper.configFieldColumnName("detailKindId", "DETAIL_KIND_ID");
		defaultNameMapper.configFieldColumnName("detailName", "DETAIL_NAME");
		defaultNameMapper.configFieldColumnName("detailDescription", "DETAIL_DESCRIPTION");
		defaultNameMapper.configFieldColumnName("detailKindValue", "DETAIL_KIND_VALUE");
		return defaultNameMapper;
	}

	@Override
	public List<KindVo> listAllKinInDetailId(String[] kindDetailIds) {
		QueryFilterBuilder queryFilterBuilder = new QueryFilterBuilder();
		queryFilterBuilder.put("kinds", kindDetailIds);
		return this.queryExtend(queryFilterBuilder.build(), "findKindByDetailsForVo");
	}

}
