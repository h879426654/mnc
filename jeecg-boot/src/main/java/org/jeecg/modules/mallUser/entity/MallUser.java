package org.jeecg.modules.mallUser.entity;

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
 * @Description: 商家总后台账号密码
 * @author： jeecg-boot
 * @date：   2019-10-10
 * @version： V1.0
 */
@Data
@TableName("mall_user")
public class MallUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
	private java.lang.String customerId;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
	private java.lang.String userName;
	/**密码*/
	@Excel(name = "密码", width = 15)
	private java.lang.String passWord;
	@Excel(name = "状态0正常 1停用", width = 15)
	private java.lang.String state;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
}
