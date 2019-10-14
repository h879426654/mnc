package org.jeecg.modules.mallGoods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 商品
 * @author： jeecg-boot
 * @date：   2019-10-10
 * @version： V1.0
 */
@Data
@TableName("mall_goods")
public class MallGood2s implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.UUID)
	private Integer id;
	/**商家ID*/
	@Excel(name = "商家ID", width = 15)
	private String advertId;
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
	private String goodsName;
	/**商品描述*/
	@Excel(name = "商品描述", width = 15)
	private Object goodsText;
	/**价格*/
	@Excel(name = "价格", width = 15)
	private java.math.BigDecimal money;
	/**商品图片*/
	@Excel(name = "商品图片", width = 15)
	private String image;
	/**推荐商品,1:代表推荐*/
	@Excel(name = "推荐商品,1:代表推荐", width = 15)
	private String state;
	/**0:未删除 1:删除*/
	@Excel(name = "0:未删除 1:删除", width = 15)
	private String delFlag;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@Excel(name = "审批状态 0:未审批 1:审批通过 2:驳回", width = 15)
	private String status;
	@Excel(name = "商家的用户id", width = 15)
	private String customerId;
	@Excel(name = "商家名称", width = 15)
	private String shopName;

}
