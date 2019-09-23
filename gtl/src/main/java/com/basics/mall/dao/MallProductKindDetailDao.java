package com.basics.mall.dao;
import java.util.List;

import com.basics.mall.entity.MallProductKindDetail;
import com.basics.mall.vo.KindVo;
import com.basics.support.GenericDao;

public interface MallProductKindDetailDao extends GenericDao<MallProductKindDetail> {

	List<KindVo> listAllKinInDetailId(String[] split);

}