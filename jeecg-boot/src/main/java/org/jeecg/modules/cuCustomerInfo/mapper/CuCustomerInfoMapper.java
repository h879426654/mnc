package org.jeecg.modules.cuCustomerInfo.mapper;


import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.cuCustomerInfo.entity.CuCustomerInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.cuCustomerInfo.entity.CuCustomerInfo2;

import java.util.List;

/**
 * @Description: 用户表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
public interface CuCustomerInfoMapper extends BaseMapper<CuCustomerInfo> {

    Integer searchCustomerCount();

    Integer searchCustomerTodayCount();

    Integer searchBookingCount();

    Integer searchBookingTodayCount();

    Integer searchShopCount();

    Integer searchShopTodayCount();

    List<CuCustomerInfo2> searchList(@Param("phone")String phone, @Param("name")String name, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    Long searchCount();
}
