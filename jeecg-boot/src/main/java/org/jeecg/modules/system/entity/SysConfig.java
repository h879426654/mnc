package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 系统配置
 * @author： jeecg-boot
 * @date：   2019-06-12
 * @version： V1.0
 */
@Data
@TableName("sys_config")
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**配置ID*/
	@Excel(name = "配置ID", width = 15)
	@TableId(type = IdType.UUID)
	private String configId;
	/**配置*/
	@Excel(name = "配置", width = 15)
	private String configCode;
	/**配置名*/
	@Excel(name = "配置名", width = 15)
	private String configName;
	/**配置值*/
	@Excel(name = "配置值", width = 15)
	private java.math.BigDecimal configValue;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
	private Integer configFlag;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
	private Integer versionNum;
	/**是否删除（1是 0否）*/
	@Excel(name = "是否删除（1是 0否）", width = 15)
	private Integer flagDel;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private String createUser;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	private String modifyUser;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;

	public Serializable getId() {
		return configId;
	}
}
