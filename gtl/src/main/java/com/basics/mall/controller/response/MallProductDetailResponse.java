package com.basics.mall.controller.response;

import java.math.BigDecimal;
import java.util.List;

import com.basics.mall.entity.MallProduct;
import com.basics.mall.vo.CommentInfoVo;
import com.basics.mall.vo.MainKindVo;
import com.basics.mall.vo.ProductCombinationVo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MallProductDetailResponse extends MallProduct {
	
	private static final long serialVersionUID = -6654410712641824695L;
	
	@JsonProperty(value = "combinations")
	private List<ProductCombinationVo> combinationsStock;
	
	/**
	 * 商家信息
	 */
	private String shopName;
	/**
	 * 商家等级
	 */
	private String shopLogo;
	
	private BigDecimal commentDescribeSart; // 描述星级评价
	private BigDecimal commentServiceSart; // 服务星级评价
	private BigDecimal commentLogisticsSart;// 物流星级评价
	
	/**
	 * 商品轮播图
	 */
	private List<String> images;
	
	/**
	 * 规格列表
	 */
	private List<MainKindVo> kinds;
	
	/**
	 * 最新评论
	 */
	private CommentInfoVo comment;
	
	private List<String> commentImages;
	
	/**
	 * 评论条数
	 */
	private Integer commentNum;
	
	private Integer flagCollection;// 是否收藏

	private String shopPhone;

	public List<ProductCombinationVo> getCombinationsStock() {
		return combinationsStock;
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

	public BigDecimal getCommentLogisticsSart() {
		return commentLogisticsSart;
	}

	public List<String> getImages() {
		return images;
	}

	public List<MainKindVo> getKinds() {
		return kinds;
	}

	public CommentInfoVo getComment() {
		return comment;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public Integer getFlagCollection() {
		return flagCollection;
	}

	public void setCombinationsStock(List<ProductCombinationVo> combinationsStock) {
		this.combinationsStock = combinationsStock;
	}
	
	public void setCommentServiceSart(BigDecimal commentServiceSart) {
		this.commentServiceSart = commentServiceSart;
	}

	public void setCommentLogisticsSart(BigDecimal commentLogisticsSart) {
		this.commentLogisticsSart = commentLogisticsSart;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public void setKinds(List<MainKindVo> kinds) {
		this.kinds = kinds;
	}

	public void setComment(CommentInfoVo comment) {
		this.comment = comment;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public void setFlagCollection(Integer flagCollection) {
		this.flagCollection = flagCollection;
	}

	public List<String> getCommentImages() {
		return commentImages;
	}

	public void setCommentImages(List<String> commentImages) {
		this.commentImages = commentImages;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
}
