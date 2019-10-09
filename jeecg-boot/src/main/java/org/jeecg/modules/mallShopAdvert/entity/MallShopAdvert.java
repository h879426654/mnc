package org.jeecg.modules.mallShopAdvert.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 商家表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@Data
@TableName("mall_shop_advert")
public class MallShopAdvert implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**商家ID*/
	@Excel(name = "商家ID", width = 15)
	private java.lang.String advertId;
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
	private java.lang.String customerId;
	/**商家名称*/
	@Excel(name = "商家名称", width = 15)
	private java.lang.String advertName;
	/**商家介绍*/
	@Excel(name = "商家介绍", width = 15)
	private java.lang.Object advertContext;
	/**商家封面图片*/
	@Excel(name = "商家封面图片", width = 15)
	private java.lang.String advertImage;
	/**店铺营业执照*/
	@Excel(name = "店铺营业执照", width = 15)
	private java.lang.String shopLicence;
	/**视频地址*/
	@Excel(name = "视频地址", width = 15)
	private java.lang.String shopVideo;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
	private java.lang.String advertPhone;
	/**地址*/
	@Excel(name = "地址", width = 15)
	private java.lang.String advertAddress;
	/**经度*/
	@Excel(name = "经度", width = 15)
	private java.lang.String advertLongitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
	private java.lang.String advertLatitude;
	/**审核状态(1.待审核 2.审核通过， 3.审核拒绝)*/
	@Excel(name = "审核状态(1.待审核 2.审核通过， 3.审核拒绝)", width = 15)
	private java.lang.String applyStatus;
	/**审核原因*/
	@Excel(name = "审核原因", width = 15)
	private java.lang.String applyContext;
	/**是否删除（1是 0否）*/
	@Excel(name = "是否删除（1是 0否）", width = 15)
	private java.lang.String flagDel;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**销量*/
	@Excel(name = "销量", width = 15)
	private java.lang.Integer advertSale;
	/**市*/
	@Excel(name = "市", width = 15)
	private java.lang.String city;
	/**区*/
	@Excel(name = "区", width = 15)
	private java.lang.String region;
	/**商家类型ID*/
	@Excel(name = "商家类型ID", width = 15)
	private java.lang.String classifyId;
	/**负责人*/
	@Excel(name = "负责人", width = 15)
	private java.lang.String person;
	/**0:非热门，1热门*/
	@Excel(name = "0:非热门，1热门", width = 15)
	private java.lang.String hot;
}
