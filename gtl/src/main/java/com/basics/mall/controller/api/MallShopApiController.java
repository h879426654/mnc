package com.basics.mall.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.basics.common.DataItemResponse;
import com.basics.common.DataPageComonResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenIdRequest;
import com.basics.common.TokenRequest;
import com.basics.common.TokenTypePageRequest;
import com.basics.cu.controller.request.ModifyPassRequest;
import com.basics.mall.controller.request.PushMallShopRequest;
import com.basics.mall.entity.MallProduct;
import com.basics.mall.entity.MallShop;
import com.basics.mall.service.MallShopApiService;

@RestController
@RequestMapping("/api/shop/")
public class MallShopApiController implements ApplicationContextAware {

    @SuppressWarnings("unused")
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MallShopApiService mallShopApiService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 商家申请
     */
    @RequestMapping("applyMallShop")
    public DataResponse applyMallShop(@Valid PushMallShopRequest request, BindingResult result, HttpServletRequest req) {
        DataResponse response = new DataResponse();
        if (result.hasErrors()) {
            response.onBindingError(result.getAllErrors());
            return response;
        }
        try {
            response = mallShopApiService.doApplyMallShop(request, req);
        } catch (Exception e) {
            response.onException(e);
        }
        return response;
    }

    /**
     * 检查申请
     */
    @RequestMapping("checkApplyStatus")
    public DataItemResponse<JSONObject> checkApplyStatus(@Valid TokenRequest request, BindingResult result, HttpServletRequest req) {
        DataItemResponse<JSONObject> response = new DataItemResponse<>();
        if (result.hasErrors()) {
            response.onBindingError(result.getAllErrors());
            return response;
        }
        try {
            response = mallShopApiService.checkApplyStatus(request, req);
        } catch (Exception e) {
            response.onException(e);
        }
        return response;
    }

    /**
     * 店铺主页
     */
    @RequestMapping("storeHomePage")
    public DataItemResponse<MallShop> storeHomePage(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
        DataItemResponse<MallShop> response = new DataItemResponse<>();
        if (result.hasErrors()) {
            response.onBindingError(result.getAllErrors());
            return response;
        }
        try {
            response = mallShopApiService.storeHomePage(request, req);
        } catch (Exception e) {
            response.onException(e);
        }
        return response;
    }


    /**
     * 商品上下架
     */
    @RequestMapping("changeProductStatus")
    public DataResponse changeProductStatus(@Valid TokenIdRequest request, BindingResult result, HttpServletRequest req) {
        DataResponse response = new DataResponse();
        if (result.hasErrors()) {
            response.onBindingError(result.getAllErrors());
            return response;
        }
        try {
            response = mallShopApiService.changeProductStatus(request, req);
        } catch (Exception e) {
            response.onException(e);
        }
        return response;
    }

    /**
     * 查询商品列表
     */
    @RequestMapping("selectMyShopProduct")
    public DataPageComonResponse<MallProduct> selectMyShopProduct(@Valid TokenTypePageRequest request, BindingResult result, HttpServletRequest req) {
        DataPageComonResponse<MallProduct> response = new DataPageComonResponse<>();
        if (result.hasErrors()) {
            response.onBindingError(result.getAllErrors());
            return response;
        }
        try {
            response = mallShopApiService.selectMyShopProduct(request, req);
        } catch (Exception e) {
            response.onException(e);
        }
        return response;
    }

    /**
     * 商家忘记密码
     */
    @RequestMapping("shopPass")
    public DataResponse shopPass(@Valid ModifyPassRequest request, BindingResult result, HttpServletRequest req) {
        DataResponse response = new DataResponse();
        if (result.hasErrors()) {
            response.onBindingError(result.getAllErrors());
            return response;
        }
        try {
            response = mallShopApiService.updateShopPass(request, req);
        } catch (Exception e) {
            response.onException(e);
        }
        return response;
    }

}
