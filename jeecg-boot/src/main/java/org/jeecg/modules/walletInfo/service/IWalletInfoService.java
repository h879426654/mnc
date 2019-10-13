package org.jeecg.modules.walletInfo.service;

import org.jeecg.modules.walletInfo.entity.WalletInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: mnc管理
 * @author： jeecg-boot
 * @date：   2019-10-11
 * @version： V1.0
 */
public interface IWalletInfoService extends IService<WalletInfo> {
    public String httpClient(String url);
}
