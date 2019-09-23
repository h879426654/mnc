package com.basics.mall.vo;

import java.io.Serializable;
import java.util.List;

public class CommentInfoVo implements Serializable {

	private static final long serialVersionUID = 97637223546129316L;
	/**
	    * 商品评价ID
	    */
	private String id;

	/**
	    * 会员ID
	    */
	private String customerId;
	/**
	* 用户名
	*/
	private String customerName;
	/**
	* 用户头像的路径
	*/
	private String customerHead;

	/**
	    * 评价类型(1好评 2中评 3差评)
	    */
	private Integer commentType;

	/**
	* 评价内容
	*/
	private String commentContext;
	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 回复内容
	 */
	private String replyContext;

	/**
	* 回复时间
	*/
	private String replyTime;
	
	private Integer flagAnonymous;

	private List<String> images;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerHead() {
		return customerHead;
	}

	public void setCustomerHead(String customerHead) {
		this.customerHead = customerHead;
	}

	public String getCommentContext() {
		return commentContext;
	}

	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getReplyContext() {
		return replyContext;
	}

	public void setReplyContext(String replyContext) {
		this.replyContext = replyContext;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Integer getFlagAnonymous() {
		return flagAnonymous;
	}

	public void setFlagAnonymous(Integer flagAnonymous) {
		this.flagAnonymous = flagAnonymous;
	}
	
	
}
