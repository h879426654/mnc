package org.jeecg.modules.cuConsume.entity;

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
 * @Description: 记账
 * @author： jeecg-boot
 * @date：   2019-10-05
 * @version： V1.0
 */
@Data
@TableName("cu_consume")
public class CuConsume implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**Id*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**商家id*/
	@Excel(name = "商家id", width = 15)
	private java.lang.String shopId;
	/**用户*/
	@Excel(name = "用户", width = 15)
	private java.lang.String customerId;
	/**商家名*/
	@Excel(name = "商家名", width = 15)
	private java.lang.String shopName;
	/**记账金额*/
	@Excel(name = "记账金额", width = 15)
	private java.math.BigDecimal money;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
	private java.lang.String phone;
	/**0:待记账1:已记账2:取消*/
	@Excel(name = "0:待记账1:已记账2:取消", width = 15)
	private java.lang.String state;
	/**商家图片路径*/
	@Excel(name = "商家图片路径", width = 15)
	private java.lang.String image;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**0:未评价，1:已评价*/
	@Excel(name = "0:未评价，1:已评价", width = 15)
	private java.lang.String appraise;
}
