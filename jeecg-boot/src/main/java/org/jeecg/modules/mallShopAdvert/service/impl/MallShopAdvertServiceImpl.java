package org.jeecg.modules.mallShopAdvert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.mallShopAdvert.entity.MallShopAdvert;
import org.jeecg.modules.mallShopAdvert.mapper.MallShopAdvertMapper;
import org.jeecg.modules.mallShopAdvert.service.IMallShopAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 商家表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@Service
public class MallShopAdvertServiceImpl extends ServiceImpl<MallShopAdvertMapper, MallShopAdvert> implements IMallShopAdvertService {

    @Autowired MallShopAdvertMapper mallShopAdvertMapper;
    @Override
    public String searchOperate(String id) {
        MallShopAdvert mallShopAdvert = mallShopAdvertMapper.selectOne(new QueryWrapper<MallShopAdvert>().eq("ADVERT_ID", id).eq("APPLY_STATUS", 1).eq("FLAG_DEL", 0));
        if (null != mallShopAdvert) {
            return "1";
        }
        return "0";
    }
}
