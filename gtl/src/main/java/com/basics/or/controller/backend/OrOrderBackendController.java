package com.basics.or.controller.backend;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.basics.app.shiro.AppUserUtils;
import com.basics.common.Constant;
import com.basics.cu.entity.CuCustomerMessage;
import com.basics.cu.service.BaseAccountApiService;
import com.basics.cu.service.CuCustomerMessageService;
import com.basics.mall.service.MallProductService;
import com.basics.or.entity.OrOrder;
import com.basics.or.service.OrOrderService;
import com.basics.support.FormResultSupport;
import com.basics.support.LogUtils;
import com.basics.support.QueryFilterBuilder;
import com.basics.support.ServletUtils;
import com.basics.support.kuaidi.KdApiOrderDistinguishUtil;
@Controller
@RequestMapping("/backend/or/orOrder/")
public class OrOrderBackendController extends BaseBackendController<OrOrder> {
	
	@Autowired
	private OrOrderService orOrderService;
	@Autowired
	private MallProductService mallProductService;
	@Autowired
	private BaseAccountApiService baseAccountApiService;
	@Autowired
	private CuCustomerMessageService cuCustomerMessageService;

	@Override
	public void willQuery(QueryFilterBuilder builder) {
		builder.orderBy("createTime DESC").build();
	}

	@Override
	@RequestMapping(value = "/save")
	public void save(OrOrder entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			// 判断当前订单是否已发货
			OrOrder order = this.service.queryByPK(entity.getId());
			if (null == order || StringUtils.isNotBlank(order.getOrderLogisticsNum())) {
				result.onException("该订单不存在或未支付或已经发货了");
			} else {
				if (order.getOrderStatus() == Constant.ORDER_STATUS_2) {
					String resultStr = KdApiOrderDistinguishUtil.getOrderTracesByJson(entity.getOrderLogisticsNum());
					JSONObject data = JSONObject.parseObject(resultStr);
					if (null != data.getJSONArray("Shippers")&&data.getJSONArray("Shippers").size()>0) {
						String info = data.getJSONArray("Shippers").get(0).toString();
						String shipperName = JSONObject.parseObject(info).getString("ShipperName");
						String shipperCode = JSONObject.parseObject(info).getString("ShipperCode");
						order.orderLogisticsName(shipperName).orderLogisticsCode(shipperCode).orderLogisticsNum(entity.getOrderLogisticsNum()).orderStatus(Constant.ORDER_STATUS_3);
						order.setOrderLogisticsTime(new Date());
						
						CuCustomerMessage message = new CuCustomerMessage();
						message.setCustomerId(order.getCustomerId());
						message.setAppMessageId(order.getId());
						message.setMessageTitle("商家发货");
						message.setMessageContext("您的购物购物订单:"+order.getOrderNumber()+"商家已发货了, 请留意物流信息");
						message.setMessageType(Constant.Message_TYPE_3);
						message.setFlagRead(Constant.STATE_NO);
						message.setCreateTime(new Date());
						cuCustomerMessageService.save(message);
						
						orOrderService.save(order);
						result.onSuccess("操作成功");
					} else {
						result.onException("物流单号错误，请核对");
					}
				} else {
					result.onException("当前订单状态不能发货");
				}
			}
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	/**
	 * 商品列表
	 */
	@RequestMapping(value = "showProductListByOrderId", method = RequestMethod.POST)
	public void showProductListByOrderId(String gridPager, HttpServletRequest request, HttpServletResponse response) {
		orOrderService.selectProductListByOrderId(gridPager, request, response);
	}

	/**
	 * 取消订单
	 */
	@RequestMapping(value = "/cancelOrder")
	public void cancelOrder(OrOrder entity, HttpServletRequest request, HttpServletResponse response) {
		FormResultSupport result = new FormResultSupport();
		try {
			this.beforeSave(result, entity, request);
			if (!result.getErrorMessages().isEmpty()) {
				result.onException("操作失败");
			}
			String name = AppUserUtils.getCurrentUserSupport().getName();
			entity.setModifyUser(name);
			result=baseAccountApiService.cancelOrder(entity);
		} catch (Throwable e) {
			LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
			result.onException(e);
		}
		ServletUtils.renderJsonAsText(response, result);
	}

	@RequestMapping("/admin")
	public String orderAdmin(){
		return getView("admin");
	}

	@RequestMapping("/shopOrder")
	public void shopOrderGrid(String gridPager, HttpServletRequest request, HttpServletResponse response){
		QueryFilterBuilder builder = new QueryFilterBuilder();
		builder.put("shopId", AppUserUtils.getCurrentUserSupport().getId()).build();
		swgrid(gridPager, builder, orOrderService,request, response);
	}
}
