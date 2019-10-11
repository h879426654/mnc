package org.jeecg.modules.cuCustomerInfo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
import net.sf.json.JSONArray;
import org.jeecg.modules.cuCustomerInfo.entity.CuCount;
import org.jeecg.modules.cuCustomerInfo.entity.CuCustomerInfo;
import org.jeecg.modules.cuCustomerInfo.entity.CuCustomerInfo2;
import org.jeecg.modules.cuCustomerInfo.entity.CuPage;
import org.jeecg.modules.cuCustomerInfo.mapper.CuCustomerInfoMapper;
import org.jeecg.modules.cuCustomerInfo.service.ICuCustomerInfoService;
import org.jeecg.modules.walletInfo.entity.WalletInfo;
import org.jeecg.modules.walletInfo.mapper.WalletInfoMapper;
import org.jeecg.modules.walletInfo.service.IWalletInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 用户表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@Service
public class CuCustomerInfoServiceImpl extends ServiceImpl<CuCustomerInfoMapper, CuCustomerInfo> implements ICuCustomerInfoService {

    @Autowired
    private CuCustomerInfoMapper cuCustomerInfoMapper;
    @Autowired
    private WalletInfoMapper walletInfoMapper;
    /**
     * 统计管理
     */
    @Override
    public String searchCount() {
        //平台总id数
        Integer customerCount = cuCustomerInfoMapper.searchCustomerCount();
        //今日新增id数
        Integer customerTodayCount = cuCustomerInfoMapper.searchCustomerTodayCount();
        //平台总记账数
        Integer bookingCount = cuCustomerInfoMapper.searchBookingCount();
        //今日新增记账数
        Integer bookingTodayCount = cuCustomerInfoMapper.searchBookingTodayCount();
        //平台总商家数
        Integer shopCount = cuCustomerInfoMapper.searchShopCount();
        //今日新增商家数
        Integer shopTodayCount =cuCustomerInfoMapper.searchShopTodayCount();

        CuCount cuCount = new CuCount();
        cuCount.setCustomerCount(customerCount);
        cuCount.setCustomerTodayCount(customerTodayCount);
        cuCount.setBookingCount(bookingCount);
        cuCount.setBookingTodayCount(bookingTodayCount);
        cuCount.setShopCount(shopCount);
        cuCount.setShopTodayCount(shopTodayCount);

        JSONObject json = (JSONObject) JSONObject.toJSON(cuCount);
        return json.toString();
    }

    @Override
    public String searchCustomer(String phone, String name, Integer pageNo, Integer pageSize) {
        CuPage cuPage = new CuPage();
        if (null == pageNo) {
            pageNo = 1;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        List<CuCustomerInfo2> list = cuCustomerInfoMapper.searchList(phone, name, (pageNo-1)*10, pageSize);
        for (CuCustomerInfo2 cuCustomerInfo2:list) {
            WalletInfo walletInfo = walletInfoMapper.selectOne(new QueryWrapper<WalletInfo>().eq("user_Id", cuCustomerInfo2.getCustomerId()));
            if (null != walletInfo) {
                cuCustomerInfo2.setMnc(walletInfo.getMncNum());
                cuCustomerInfo2.setScore(walletInfo.getScoreNum());
                cuCustomerInfo2.setSuperNum(walletInfo.getSuperNum());
                cuCustomerInfo2.setMtoken(walletInfo.getMtokenNum());
            }
        }
        JSONArray jsons = JSONArray.fromObject(list);
        Long total = cuCustomerInfoMapper.searchCount();
        cuPage.setTotal(total);
        cuPage.setSize(pageSize.longValue());

        if (pageSize != 0L) {
            long pages = total / pageSize.longValue();
            if (total % pageSize != 0L) {
                ++pages;
            }
            cuPage.setPages(pages);
        }
        cuPage.setList(jsons.toString());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(cuPage);
        return jsonObject.toString();
    }
}
