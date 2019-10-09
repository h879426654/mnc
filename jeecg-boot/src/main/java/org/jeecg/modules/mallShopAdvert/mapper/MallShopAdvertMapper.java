package org.jeecg.modules.mallShopAdvert.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.mallShopAdvert.entity.MallShopAdvert;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商家表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
public interface MallShopAdvertMapper extends BaseMapper<MallShopAdvert> {

    void updateByIdAndApplyStatus(@Param("advertId")String advertId, @Param("applyStatus")String applyStatus);

    void updateByIdAndHot(@Param("advertId")String advertId, @Param("hot")String hot);
}
