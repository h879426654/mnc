package org.jeecg.modules.cuCustomerInfo.service;

import org.jeecg.modules.cuCustomerInfo.entity.CuCustomerInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 用户表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
public interface ICuCustomerInfoService extends IService<CuCustomerInfo> {

    /**
     * 统计管理
     */
    String searchCount();

    String searchCustomer(String phone, String name, Integer pageNo, Integer pageSize);
}
