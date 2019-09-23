package com.basics.mall.service.impl;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.common.Constant;
import com.basics.support.FormResultSupport;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.basics.mall.entity.MallProductClassify;
import com.basics.mall.service.MallProductClassifyService;
import com.basics.support.GenericMybatisService;

import java.util.Date;

@Service
public class MallProductClassifyMybatisService extends GenericMybatisService<MallProductClassify> implements MallProductClassifyService {

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @Override
    public FormResultSupport doSave(MallProductClassify entity) {
        FormResultSupport result = new FormResultSupport();
        if(StringUtils.isNotBlank(entity.getId())) {
            MallProductClassify classify = this.dao.get(entity.getId());
            if(null != classify) {
                entity.setClassifyParentId(classify.getClassifyParentId());
                entity.setCountryId(classify.getCountryId());
            } else {
                if(StringUtils.isBlank(entity.getClassifyParentId())) {
                    entity.setClassifyParentId(null);
                }
            }
        }
        if(StringUtils.isNotBlank(entity.getClassifyParentId()) && ! Constant.DEFAULT_REFEREE.equals(entity.getClassifyParentId())) {
            MallProductClassify parent = this.dao.get(entity.getClassifyParentId());
            if(null == parent) {
                result.onException("父分类不能为空");
                return result;
            }
            entity.setCountryId(parent.getCountryId());
        }

        if(StringUtils.isBlank(entity.getCountryId())) {
            result.onException("国家不能为空");
            return result;
        }

        UserSupport userSupport = AppUserUtils.getCurrentUserSupport();
        entity.createTime(new Date()).createUser(userSupport.getId()).modifyDate(new Date()).modifyUser(userSupport.getId());
        this.dao.save(entity);
        result.onSuccess("操作成功");
        return result;
    }
}
