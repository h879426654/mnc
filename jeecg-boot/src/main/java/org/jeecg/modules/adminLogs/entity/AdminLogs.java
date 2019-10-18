package org.jeecg.modules.adminLogs.entity;

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
 * @Description: 后台操作人员日志
 * @author： jeecg-boot
 * @date：   2019-10-16
 * @version： V1.0
 */
@Data
@TableName("admin_logs")
public class AdminLogs implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**0:mnc，1:mtoken*/
	@Excel(name = "0:mnc，1:mtoken", width = 15)
	private java.lang.String type;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
	private java.lang.String customerId;
	/**操作人*/
	@Excel(name = "操作人", width = 15)
	private java.lang.String man;
	/**操作内容*/
	@Excel(name = "操作内容", width = 15)
	private java.lang.String remark;
	/**操作时间*/
	@Excel(name = "操作时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
}
