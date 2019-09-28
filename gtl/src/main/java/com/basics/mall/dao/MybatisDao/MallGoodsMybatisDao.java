package com.basics.mall.dao.MybatisDao;

import com.basics.mall.dao.MallAdvertHotDao;
import com.basics.mall.dao.MallGoodsDao;
import com.basics.mall.entity.MallAdvertHot;
import com.basics.mall.entity.MallGoods;
import com.basics.mall.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MallGoodsMybatisDao extends GenericMybatisDaoSupport<MallGoods> implements MallGoodsDao {

public MallGoodsMybatisDao() {
super();
this.setPrimaryKeyFields("id");
}

public INameMapper onBuildNameMapper() {
DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
 defaultNameMapper.configFieldColumnName("id","ID");
 defaultNameMapper.configFieldColumnName("advertId","ADVERT_ID");
 defaultNameMapper.configFieldColumnName("goodsName","goods_name");
 defaultNameMapper.configFieldColumnName("goodsText","goods_text");
 defaultNameMapper.configFieldColumnName("money","money");
 defaultNameMapper.configFieldColumnName("image","image");
 defaultNameMapper.configFieldColumnName("state","state");
 defaultNameMapper.configFieldColumnName("delFlag","del_flag");
 defaultNameMapper.configFieldColumnName("createTime", "create_time");
return defaultNameMapper;
}
}
