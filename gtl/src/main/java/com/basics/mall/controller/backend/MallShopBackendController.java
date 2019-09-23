package com.basics.mall.controller.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basics.app.shiro.AppUserUtils;
import com.basics.app.shiro.UserSupport;
import com.basics.common.Constant;
import com.basics.cu.controller.request.ModifyPassRequest;
import com.basics.cu.service.BaseAccountApiService;
import com.basics.mall.entity.MallShop;
import com.basics.mall.service.MallShopService;
import com.basics.support.DataSourceRequest;
import com.basics.support.DataSourceResponse;
import com.basics.support.FilterOperatorEnum;
import com.basics.support.FormResultSupport;
import com.basics.support.ItemResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;

@Controller
@RequestMapping("/backend/mall/mallShop/")
public class MallShopBackendController extends BaseBackendController<MallShop> {
	
	@Autowired
	private MallShopService mallShopService;
	@Autowired
    private BaseAccountApiService baseAccountApiService;

    /**
     * 商家申请记录
     */
    @RequestMapping(value = "record", method = RequestMethod.GET)
    public String recordView() {
        return getView("record");
    }
    
    @RequestMapping(value = "/pass")
	public String passView() {
		return getView("pass");
	}
    
    
    @Override
    public void willQuery(QueryFilterBuilder builder) {
    	builder.put("applyStatus", Constant.APPLY_STATUS_1).orderBy("mallShop.CREATE_TIME DESC");
    }
    
    /**
	 * 商家审核
	 */
	@Override
    @RequestMapping(value = "/save")
	public void save(MallShop entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			} else {
                // 审核成功，将交易返回
				if (Constant.APPLY_STATUS_2 == entity.getApplyStatus()) {
					result = baseAccountApiService.doApplyShop(entity);
				} else if(Constant.APPLY_STATUS_3 == entity.getApplyStatus()){
					result = baseAccountApiService.doApplyShopByRefuse(entity);
				}
				result.onSuccess("操作成功");
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}
    
    
    /**
     * 商家列表
     */
    @RequestMapping(value = "showShopRecord", method = RequestMethod.POST)
    public void showShopRecord(String gridPager, HttpServletRequest request, HttpServletResponse response) {
        QueryFilterBuilder builder = new QueryFilterBuilder();
        builder.put("applyStatus", Constant.APPLY_STATUS_2).put("flagDel", Constant.STATE_NO).orderBy("mallShop.SHOP_STATUS ASC, mallShop.CREATE_TIME DESC");
        swgrid(gridPager, builder, this.service, "query", "count", request, response);
    }
    
    /**
     * 商家个人管理
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String addView() {
        return getView("info");
    }

    /**
     * 加载规则
     */
    @RequestMapping(value = "/load")
    public void load(HttpServletResponse response) {
        ItemResultSupport<MallShop> result = new ItemResultSupport<>();
        UserSupport user = AppUserUtils.getCurrentUserSupport();
        try {
            MallShop shop = this.service.queryOne(new QueryFilterBuilder().put("id", user.getId()).build());
            if (null != shop) {
                result.setItem(shop);
            }
        } catch (Throwable e) {
            LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
            result.onException(e);
        }
        ServletUtils.renderJsonAsText(response, result);
    }

    /**
     * 修改规则
     */
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public void modify(MallShop entity, HttpServletResponse response, BindingResult bindingResult) {
        FormResultSupport formResultSupport = new FormResultSupport();
        if (bindingResult.hasErrors()) {
            formResultSupport.onException("请检查填写字段！");
        } else {
            try {
                this.service.save(entity);
                formResultSupport.onSuccess("编辑成功！");
            } catch (Throwable t) {
                formResultSupport.onException(t);
            }
        }
        ServletUtils.renderJsonAsText(response, formResultSupport);
    }

    /**
     * 商家列表
     */
    @RequestMapping(value = "showBusinessById", method = RequestMethod.POST)
    public void showProductBusinessById(String gridPager, HttpServletRequest request, HttpServletResponse response) {
        UserSupport user = AppUserUtils.getCurrentUserSupport();
        QueryFilterBuilder builder = new QueryFilterBuilder();
        builder.put("id", user.getId());
        swgrid(gridPager, builder, this.service, "query", "count", request, response);
    }
    
    
    /**
	 * 修改商家密码
	 */
	@RequestMapping(value = "modifyShopPass", method = RequestMethod.POST)
	public void modifyShopPass(ModifyPassRequest entity, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) {
		FormResultSupport formResultSupport = new FormResultSupport();
		if (bindingResult.hasErrors()) {
			formResultSupport.onException("请检查填写字段！");
		} else {
			try {
				formResultSupport = mallShopService.modifyShopPass(entity);
			} catch (Throwable t) {
				formResultSupport.onException(t);
			}
		}
		ServletUtils.renderJsonAsText(response, formResultSupport);
	}
	
	
	/**
	 * 查看凭证
	 */
	@RequestMapping(value = "showShopAptitude/{id}")
	@ResponseBody
	public DataSourceResponse showShopAptitude(@PathVariable("id") String id, HttpServletResponse response) {
		DataSourceRequest request = new DataSourceRequest();
		request.and("ownerId", FilterOperatorEnum.EQ, id).and("ownerClass", FilterOperatorEnum.EQ, "MallShop");
		DataSourceResponse dataSource = appImageService.getDataSource(request);
		return dataSource;
	}
	
	/**
	 * 商家营停业
	 */
    @RequestMapping(value = "/updateShopStatus")
	public void updateShopStatus(MallShop entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			} else {
                // 
				result = mallShopService.updateShopStatus(entity);
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 删除商家
	 */
    @RequestMapping(value = "/deleteShop")
	public void deleteShop(MallShop entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			} else {
                //
				result = mallShopService.deleteShop(entity);
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}
    
    
    
}
