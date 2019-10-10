package org.jeecg.modules.mallGoods.entity;

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
 * @Description: 商品
 * @author： jeecg-boot
 * @date：   2019-10-10
 * @version： V1.0
 */
@Data
public class MallGoodsLimit implements Serializable {
    private static final long serialVersionUID = 1L;
    private String list;
	//总数
	private Long total;
	//页数
	private Long pages;
}
