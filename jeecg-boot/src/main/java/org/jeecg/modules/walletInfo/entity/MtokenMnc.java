package org.jeecg.modules.walletInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;

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
	private java.math.BigDecimal mnc;
	private java.math.BigDecimal mToken;
	private BigDecimal recordNum;//记账钱包
	private BigDecimal score;//创业积分
	private BigDecimal rSuperNum;//超级钱包释放
	private BigDecimal moveNum;//流通钱包
	private String state;//增加还是减少
	private String type;//增加减少谁(1:mnc,2:mtoken,3:recordNum,4:score,5:moveNum,6:rSuperNum)
	private BigDecimal money;
}
