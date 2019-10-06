package com.basics.cu.dao.MybatisDao;

import com.basics.cu.dao.CuPictureDao;
import com.basics.cu.dao.CuReatil3Dao;
import com.basics.cu.entity.CuPicture;
import com.basics.cu.entity.CuReatil3;
import com.basics.cu.support.GenericMybatisDaoSupport;
import com.basics.support.DefaultNameMapper;
import com.basics.support.INameMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CuPictureMybatisDao extends GenericMybatisDaoSupport<CuPicture> implements CuPictureDao {

	public CuPictureMybatisDao() {
		super();
		this.setPrimaryKeyFields("id");
	}

	public INameMapper onBuildNameMapper() {
		DefaultNameMapper defaultNameMapper = new DefaultNameMapper();
		defaultNameMapper.configFieldColumnName("id", "ID");
		defaultNameMapper.configFieldColumnName("image", "image");
		defaultNameMapper.configFieldColumnName("type", "type");
		defaultNameMapper.configFieldColumnName("url", "url");
		defaultNameMapper.configFieldColumnName("delFlag", "del_flag");
		return defaultNameMapper;
	}
}
