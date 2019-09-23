package com.basics.mall.service;

import com.basics.mall.entity.MallProductClassify;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericService;

public interface MallProductClassifyService extends GenericService<MallProductClassify> {

    /**
     * 保存
     * @param entity
     * @return
     */
    FormResultSupport doSave(MallProductClassify entity);
}
