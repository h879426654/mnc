package org.jeecg.modules.cuReatilMoney.entity;

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
 * @Description: mp制度规则
 * @author： jeecg-boot
 * @date：   2019-10-03
 * @version： V1.0
 */
@Data
@TableName("cu_reatil_money")
public class CuReatilMoney implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**ID*/
	@TableId(type = IdType.UUID)
	private java.lang.Integer id;
	/**分销类型*/
	@Excel(name = "分销类型", width = 15)
	private java.lang.String reatilType;
	/**钱*/
	@Excel(name = "钱", width = 15)
	private java.math.BigDecimal money;
	/**是否删除0否，1删除*/
	@Excel(name = "是否删除0否，1删除", width = 15)
	private java.lang.String delFlag;
}
