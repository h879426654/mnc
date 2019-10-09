package org.jeecg.modules.mallShopAdvert.service;

import org.jeecg.modules.mallShopAdvert.entity.MallShopAdvert;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 商家表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
public interface IMallShopAdvertService extends IService<MallShopAdvert> {

    String searchOperate(String id);
}
