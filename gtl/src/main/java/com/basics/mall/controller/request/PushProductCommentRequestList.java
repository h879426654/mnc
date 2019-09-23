package com.basics.mall.controller.request;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

import com.basics.common.TokenRequest;

public class PushProductCommentRequestList extends TokenRequest{

	private static final long serialVersionUID = 7423465151435191800L;

	@NotBlank(message = "{pushProductCommentRequestList.orderId}")
	private String orderId;
	
	private String list;
	
	private BigDecimal commentDescribeSart;
	private BigDecimal commentServiceSart;
	private BigDecimal commentLogisticsSart;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public BigDecimal getCommentDescribeSart() {
		return commentDescribeSart;
	}

	public void setCommentDescribeSart(BigDecimal commentDescribeSart) {
		this.commentDescribeSart = commentDescribeSart;
	}

	public BigDecimal getCommentServiceSart() {
		return commentServiceSart;
	}

	public void setCommentServiceSart(BigDecimal commentServiceSart) {
		this.commentServiceSart = commentServiceSart;
	}

	public BigDecimal getCommentLogisticsSart() {
		return commentLogisticsSart;
	}

	public void setCommentLogisticsSart(BigDecimal commentLogisticsSart) {
		this.commentLogisticsSart = commentLogisticsSart;
	}
	
	
}
