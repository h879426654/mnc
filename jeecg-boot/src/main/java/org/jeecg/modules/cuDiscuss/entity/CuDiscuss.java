package org.jeecg.modules.cuDiscuss.entity;

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
 * @Description: 评论
 * @author： jeecg-boot
 * @date：   2019-10-13
 * @version： V1.0
 */
@Data
@TableName("cu_discuss")
public class CuDiscuss implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	private java.lang.Integer id;
	/**商家id*/
	@Excel(name = "商家id", width = 15)
	private java.lang.String shopId;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
	private java.lang.String customerId;
	/**0:可见，1:删除*/
	@Excel(name = "0:可见，1:删除", width = 15)
	private java.lang.String state;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**评论内容*/
	@Excel(name = "评论内容", width = 15)
	private java.lang.String remark;
	/**0：未评价，1：评价过了*/
	@Excel(name = "0：未评价，1：评价过了", width = 15)
	private java.lang.String flag;
	private java.lang.String shopName;
	private java.lang.String userName;
}
