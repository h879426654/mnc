package com.basics.mall.service;

import com.basics.mall.controller.request.AddProductRequest;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.vo.ProductCombinationVo;
import com.basics.support.FormResultSupport;
import com.basics.support.GenericService;
import com.basics.support.dlshouwen.Pager;

public interface MallProductService extends GenericService<MallProduct> {

	FormResultSupport doAddGoods2(AddProductRequest vo);

    Pager getProductDetail(String gridPager);

    FormResultSupport doProductModify(ProductCombinationVo combinationVo);
}
