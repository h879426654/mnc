package org.jeecg.modules.walletInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: mnc管理
 * @author： jeecg-boot
 * @date：   2019-10-11
 * @version： V1.0
 */
@Data
@TableName("wallet_info")
public class MtokenMnc implements Serializable {
    private static final long serialVersionUID = 1L;


	@Excel(name = "userId", width = 15)
	private String userId;
	@Excel(name = "新MNC", width = 15)
	private java.math.BigDecimal mnc;
	@Excel(name = "新MNC", width = 15)
	private java.math.BigDecimal mToken;
	@Excel(name = "新MNC", width = 15)
	private String state;
}
