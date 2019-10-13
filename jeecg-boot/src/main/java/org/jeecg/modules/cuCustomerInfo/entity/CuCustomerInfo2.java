package org.jeecg.modules.cuCustomerInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 用户表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@Data
@TableName("cu_customer_info")
public class CuCustomerInfo2 implements Serializable {
    private static final long serialVersionUID = 1L;

	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
	private String customerId;
	/**用户账号*/
	@Excel(name = "用户账号", width = 15)
	private String customerNumber;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
	private String customerName;
	/**用户头像的路径*/
	@Excel(name = "用户头像的路径", width = 15)
	private String customerHead;
	/**用户手机号*/
	@Excel(name = "用户手机号", width = 15)
	private String customerPhone;
	@Excel(name = "用户状态 0代表冻结 1代表正常", width = 15)
	private Integer customerStatus;
	/**注册时间*/
	@Excel(name = "注册时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date registerTime;
	@Excel(name = "mnc", width = 15)
	private BigDecimal mnc;
	@Excel(name = "mtoken", width = 15)
	private BigDecimal mtoken;
	@Excel(name = "score", width = 15)
	private BigDecimal score;
	@Excel(name = "superNum", width = 15)
	private BigDecimal superNum;
	@Excel(name ="userId", width = 15)
	private String userid;
}
