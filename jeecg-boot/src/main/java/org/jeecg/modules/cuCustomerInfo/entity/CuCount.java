package org.jeecg.modules.cuCustomerInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@Data
@TableName("cu_customer_info")
public class CuCount implements Serializable {
    private static final long serialVersionUID = 1L;

	@Excel(name = "所有用户数量", width = 15)
	private Integer customerCount;

	@Excel(name = "今日用户数量", width = 15)
	private Integer customerTodayCount;

	@Excel(name = "所有记账数量", width = 15)
	private Integer bookingCount;

	@Excel(name = "今日记账数量", width = 15)
	private Integer bookingTodayCount;

	@Excel(name = "所有商家数量", width = 15)
	private Integer shopCount;

	@Excel(name = "今日商家数量", width = 15)
	private Integer shopTodayCount;

}
