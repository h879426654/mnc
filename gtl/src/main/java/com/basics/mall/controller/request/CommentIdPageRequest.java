package com.basics.mall.controller.request;

import org.hibernate.validator.constraints.NotBlank;
import com.basics.common.DataPageRequest;

public class CommentIdPageRequest extends DataPageRequest{
	@NotBlank(message = "{commentIdPageRequest.productId}")
	private String prodectId;

	public String getProdectId() {
		return prodectId;
	}

	public void setProdectId(String prodectId) {
		this.prodectId = prodectId;
	}
	
}
