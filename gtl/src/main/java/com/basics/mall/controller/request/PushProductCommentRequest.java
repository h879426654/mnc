package com.basics.mall.controller.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.basics.common.TokenRequest;

public class PushProductCommentRequest{
	
	private static final long serialVersionUID = 8480078096078968845L;	
	private String productId;
	private String commentContext;
	private Integer commentType;
	private BigDecimal commentDescribeSart;
	private BigDecimal commentServiceSart;
	private BigDecimal commentLogisticsSart;
	/**
	* 是否匿名(1是 0否)
	*/
	private Integer flagAnonymous;

	/**
	* 评价图片
	*/
	private MultipartFile[] files;

	public String getCommentContext() {
		return commentContext;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public BigDecimal getCommentDescribeSart() {
		return commentDescribeSart;
	}

	public BigDecimal getCommentServiceSart() {
		return commentServiceSart;
	}

	public BigDecimal getCommentLogisticsSart() {
		return commentLogisticsSart;
	}

	public Integer getFlagAnonymous() {
		return flagAnonymous;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	public void setCommentDescribeSart(BigDecimal commentDescribeSart) {
		this.commentDescribeSart = commentDescribeSart;
	}

	public void setCommentServiceSart(BigDecimal commentServiceSart) {
		this.commentServiceSart = commentServiceSart;
	}

	public void setCommentLogisticsSart(BigDecimal commentLogisticsSart) {
		this.commentLogisticsSart = commentLogisticsSart;
	}

	public void setFlagAnonymous(Integer flagAnonymous) {
		this.flagAnonymous = flagAnonymous;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
