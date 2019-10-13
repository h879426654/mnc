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
public class CuCustomerExport implements Serializable {
    private static final long serialVersionUID = 1L;

	/**用户名*/
	@Excel(name = "用户名", width = 15)
	private String name;
	/**用户手机号*/
	@Excel(name = "用户手机号", width = 15)
	private String phone;
	/**mnc*/
	@Excel(name = "mnc", width = 15)
	private BigDecimal mnc;
	/**mtoken*/
	@Excel(name = "mtoken", width = 15)
	private BigDecimal mtoken;
	/**超级钱包*/
	@Excel(name = "超级钱包", width = 15)
	private BigDecimal superNum;
	/**创业积分*/
	@Excel(name = "创业积分", width = 15)
	private BigDecimal scoreNum;

}
