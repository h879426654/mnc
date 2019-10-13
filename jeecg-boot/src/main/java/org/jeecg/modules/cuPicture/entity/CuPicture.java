package org.jeecg.modules.cuPicture.entity;

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
 * @Description: 轮播图背景图表
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@Data
@TableName("cu_picture")
public class CuPicture implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	private java.lang.String id;
	/**图片*/
	@Excel(name = "图片", width = 15)
	private java.lang.String image;
	/**0:轮播图，1:背景图*/
	@Excel(name = "0:轮播图，1:背景图", width = 15)
	private java.lang.String type;
	/**路径*/
	@Excel(name = "路径", width = 15)
	private java.lang.String url;
	/**0:未删除,1:删除*/
	@Excel(name = "0:未删除,1:删除", width = 15)
	private java.lang.String delFlag;
}
