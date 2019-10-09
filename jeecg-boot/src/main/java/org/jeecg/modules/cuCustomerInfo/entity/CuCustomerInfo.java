package org.jeecg.modules.cuCustomerInfo.entity;

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
 * @Description: 用户表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@Data
@TableName("cu_customer_info")
public class CuCustomerInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**用户ID*/
	@Excel(name = "用户ID", width = 15)
	private java.lang.String customerId;
	/**国家ID*/
	@Excel(name = "国家ID", width = 15)
	private java.lang.String countryId;
	/**用户账号*/
	@Excel(name = "用户账号", width = 15)
	private java.lang.String customerNumber;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
	private java.lang.String customerName;
	/**用户头像的路径*/
	@Excel(name = "用户头像的路径", width = 15)
	private java.lang.String customerHead;
	/**用户手机号*/
	@Excel(name = "用户手机号", width = 15)
	private java.lang.String customerPhone;
	/**支付宝账号*/
	@Excel(name = "支付宝账号", width = 15)
	private java.lang.String customerAlipay;
	/**用户微信账号*/
	@Excel(name = "用户微信账号", width = 15)
	private java.lang.String customerWechat;
	/**邮箱*/
	@Excel(name = "邮箱", width = 15)
	private java.lang.String customerEmail;
	/**用户真实姓名*/
	@Excel(name = "用户真实姓名", width = 15)
	private java.lang.String realName;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
	private java.lang.String customerCard;
	/**银行卡号*/
	@Excel(name = "银行卡号", width = 15)
	private java.lang.String bankCard;
	/**开户行*/
	@Excel(name = "开户行", width = 15)
	private java.lang.String bankName;
	/**是否实名认证(1是 0否)*/
	@Excel(name = "是否实名认证(1是 0否)", width = 15)
	private java.lang.Integer flagAuth;
	/**是否可交易(0否 1是)*/
	@Excel(name = "是否可交易(0否 1是)", width = 15)
	private java.lang.Integer flagTrade;
	/**用户状态 0代表冻结 1代表正常*/
	@Excel(name = "用户状态 0代表冻结 1代表正常", width = 15)
	private java.lang.Integer customerStatus;
	/**用户冻结原因*/
	@Excel(name = "用户冻结原因", width = 15)
	private java.lang.String customerFreezeContext;
	/**注册时间*/
	@Excel(name = "注册时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date registerTime;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
	private java.lang.Integer versionNum;
	/**是否为特殊标记(1.是 0.否)*/
	@Excel(name = "是否为特殊标记(1.是 0.否)", width = 15)
	private java.lang.Integer flagSpecial;
	/**是否删除（1是 0否）*/
	@Excel(name = "是否删除（1是 0否）", width = 15)
	private java.lang.Integer flagDel;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createUser;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	private java.lang.String modifyUser;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date modifyTime;
}
