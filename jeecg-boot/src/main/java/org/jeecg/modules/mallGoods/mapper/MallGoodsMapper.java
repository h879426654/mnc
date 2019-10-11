package org.jeecg.modules.mallGoods.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.mallGoods.entity.MallGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商品
 * @author： jeecg-boot
 * @date：   2019-10-10
 * @version： V1.0
 */
public interface MallGoodsMapper extends BaseMapper<MallGoods> {
    List<MallGoods> searchGoods(@Param("advertId")String advertId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    long searchTotal(@Param("advertId")String advertId);
}
