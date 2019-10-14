package org.jeecg.modules.mallGoods.service;

import org.jeecg.modules.mallGoods.entity.MallGoods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 商品
 * @author： jeecg-boot
 * @date：   2019-10-10
 * @version： V1.0
 */
public interface IMallGoodsService extends IService<MallGoods> {

    String searchGoods(String customerId, String name ,Integer pageNum, Integer pageSize);
}
