package org.jeecg.modules.mallGoods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.json.JSONArray;
import org.jeecg.modules.mallGoods.entity.MallGoods;
import org.jeecg.modules.mallGoods.entity.MallGoodsLimit;
import org.jeecg.modules.mallGoods.mapper.MallGoodsMapper;
import org.jeecg.modules.mallGoods.service.IMallGoodsService;
import org.jeecg.modules.mallShopAdvert.entity.MallShopAdvert;
import org.jeecg.modules.mallShopAdvert.mapper.MallShopAdvertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 商品
 * @author： jeecg-boot
 * @date：   2019-10-10
 * @version： V1.0
 */
@Service
public class MallGoodsServiceImpl extends ServiceImpl<MallGoodsMapper, MallGoods> implements IMallGoodsService {

    @Autowired
    private MallGoodsMapper mallGoodsMapper;
    @Autowired
    private MallShopAdvertMapper mallShopAdvertMapper;
    @Override
    public String searchGoods(String customerId, Integer pageNum, Integer pageSize) {
        String advertId = mallShopAdvertMapper.selectOne(new QueryWrapper<MallShopAdvert>().eq("customer_id", customerId)).getAdvertId();
        List<MallGoods> list = mallGoodsMapper.searchGoods(advertId, pageNum, pageSize);
        JSONArray jsons = JSONArray.fromObject(list);
        long total = mallGoodsMapper.searchTotal(advertId);
        long pages = total / pageSize;
        if (total % pageSize != 0L) {
            ++pages;
        }
        MallGoodsLimit mallGoodsLimit = new MallGoodsLimit();
        mallGoodsLimit.setList(jsons.toString());
        mallGoodsLimit.setTotal(total);
        mallGoodsLimit.setPages(pages);
        JSONObject json = (JSONObject) JSONObject.toJSON(mallGoodsLimit);
        return json.toString();
    }
}
