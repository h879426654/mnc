package org.jeecg.modules.cuCustomerInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 用户表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@Data
@TableName("cu_customer_info")
public class CuPage implements Serializable {
    private static final long serialVersionUID = 1L;

	@Excel(name = "总数", width = 15)
	private Long total;

	@Excel(name = "条数", width = 15)
	private Long size;

	@Excel(name = "页数", width = 15)
	private Long pages;

	@Excel(name = "数据", width = 15)
	private String list;

}
