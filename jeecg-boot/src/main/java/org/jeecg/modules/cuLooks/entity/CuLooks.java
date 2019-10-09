package org.jeecg.modules.cuLooks.entity;

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
 * @Description: 看看
 * @author： jeecg-boot
 * @date：   2019-08-01
 * @version： V1.0
 */
@Data
@TableName("cu_looks")
public class CuLooks implements Serializable {
    private static final long serialVersionUID = 1L;

	/**看看ID*/
	@TableId(type = IdType.UUID)
	@Excel(name = "看看ID", width = 15)
	private java.lang.String looksId;

	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createUser;
	/**发布人ACCID*/
	@Excel(name = "发布人ACCID", width = 15)
	private java.lang.String customerAccid;
	/**发布人ID*/
	@Excel(name = "发布人ID", width = 15)
	private java.lang.String customerId;
	/**是否删除（1是 0否）*/
	@Excel(name = "是否删除（1是 0否）", width = 15)
	private java.lang.Integer flagDel;
	/**点赞数*/
	@Excel(name = "点赞数", width = 15)
	private java.lang.Integer goodCount;
	/**看看内容*/
	@Excel(name = "看看内容", width = 15)
	private java.lang.Object looksContext;
	/**朋友圈精准定位xy轴*/
	@Excel(name = "朋友圈精准定位xy轴", width = 15)
	private java.lang.String looksCoordinate;
	/**图片地址*/
	@Excel(name = "图片地址", width = 15)
	private java.lang.String looksImgs;
	/**所处位置*/
	@Excel(name = "所处位置", width = 15)
	private java.lang.String looksLocation;
	/**视频地址*/
	@Excel(name = "视频地址", width = 15)
	private java.lang.String looksVideo;
	/**修改时间*/
	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date modifyTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
	private java.lang.String modifyUser;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
	private java.lang.Integer versionNum;

	public Serializable getId() {
		return looksId;
	}
}
