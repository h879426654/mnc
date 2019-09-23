package com.basics.mall.controller.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.TokenRequest;

public class CreateOrderInfoRequest extends TokenRequest {

	private static final long serialVersionUID = 2954368389385264702L;
	@NotNull(message = "{createOrderInfoRequest.createType}")
	private Integer createType;// 创建类型（1立即购买 2购物车购买）
	private List<String> ids;// 购物车ids

	private String productId;// 商品ID
	private Integer productNum;// 商品数量
	private String combinationId;// 规格ID

	@NotBlank(message = "{createOrderInfoRequest.addressId}")
	private String addressId;
	@NotNull(message = "{createOrderInfoRequest.orderPayType}")
	private Integer orderPayType;
//	@NotBlank(message = "二级密码不能为空！")
	private String payPass;

	private String orderRemark;

	public Integer getCreateType() {
		return createType;
	}

	public void setCreateType(Integer createType) {
		this.createType = createType;
	}



	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public String getCombinationId() {
		return combinationId;
	}

	public void setCombinationId(String combinationId) {
		this.combinationId = combinationId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public Integer getOrderPayType() {
		return orderPayType;
	}

	public void setOrderPayType(Integer orderPayType) {
		this.orderPayType = orderPayType;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public String getPayPass() {
		return payPass;
	}

	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}
	
}
