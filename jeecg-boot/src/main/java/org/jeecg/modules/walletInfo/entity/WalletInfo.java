package org.jeecg.modules.walletInfo.entity;

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
 * @Description: mnc管理
 * @author： jeecg-boot
 * @date：   2019-10-10
 * @version： V1.0
 */
@Data
@TableName("wallet_info")
public class WalletInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**userId*/
	@Excel(name = "userId", width = 15)
	private java.lang.String userId;
	/**新MNC*/
	@Excel(name = "新MNC", width = 15)
	private java.math.BigDecimal mncNum;
	/**流通钱包*/
	@Excel(name = "流通钱包", width = 15)
	private java.math.BigDecimal moveNum;
	/**超级钱包*/
	@Excel(name = "超级钱包", width = 15)
	private java.math.BigDecimal superNum;
	/**记账奖励*/
	@Excel(name = "记账奖励", width = 15)
	private java.math.BigDecimal recordNum;
	/**MTOKEN*/
	@Excel(name = "MTOKEN", width = 15)
	private java.math.BigDecimal mtokenNum;
	/**创业积分*/
	@Excel(name = "创业积分", width = 15)
	private java.math.BigDecimal scoreNum;
	/**已释放的超级钱包的数量*/
	@Excel(name = "已释放的超级钱包的数量", width = 15)
	private java.math.BigDecimal releasedSuperNum;
	/**钱包地址*/
	@Excel(name = "钱包地址", width = 15)
	private java.lang.String walletAddress;
	/**Mtoken释放的mnc*/
	@Excel(name = "Mtoken释放的mnc", width = 15)
	private java.math.BigDecimal releasedMncNum;
	/**当前读取mnc数量的区块高度*/
	@Excel(name = "当前读取mnc数量的区块高度", width = 15)
	private java.math.BigDecimal blockNum;
	/**钱包是否激活，0未激活；1已激活*/
	@Excel(name = "钱包是否激活，0未激活；1已激活", width = 15)
	private java.lang.Integer walletFrozen;
}
