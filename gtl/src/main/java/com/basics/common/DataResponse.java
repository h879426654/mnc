package com.basics.common;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.validation.ObjectError;

import com.basics.support.BusinessException;
import com.basics.support.DataStatusException;
import com.basics.support.LogUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 数据响应.
 * <p>
 * status:状态.0表示成功;其他表示错误.
 * </p>
 * <p>
 * msg:错误消息.成功返回空串,否则返回错误消息.
 * </p>
 * 
 * @author yuwenfeng
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResponse implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 状态.(0表示成功;其他表示错误).
	 */
	@JsonProperty("code")
	private int status = 0;

	/**
	 * 成功返回空串,否则返回错误消息.
	 */
	private String msg = "";

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void onExcepton(DataStatusException dataException) {
		this.status = dataException.getStatus();
		this.msg = dataException.getMsg();
	}

	public void onException(int status, Throwable e) {
		this.status = status;
		// this.msg = ExceptionUtil.buildMessage("服务器开小差了，请稍后再试，或联系我们", e);
		//
		this.msg = "网络繁忙，请稍后重试。";
		LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
	}

	public void onException(Throwable e) {
		LogUtils.performance.error("{}", ExceptionUtils.getFullStackTrace(e));
		// 空指针异常告诉客户端,免得app看到500都心慌
		if (e instanceof NullPointerException) {
			NullPointerException npe = (NullPointerException) e;
			this.onException(1, npe.getMessage() == null ? "数据异常！" : npe.getMessage());
			return;
		}
		// 数据状态异常,告诉客户端状态编码和状态消息.
		if (e instanceof DataStatusException) {
			DataStatusException dataException = (DataStatusException) e;
			this.status = dataException.getStatus();
			this.msg = dataException.getMsg();
			return;
		}
		// IllegalStateException
		if (e instanceof IllegalStateException) {
			IllegalStateException ise = (IllegalStateException) e;
			this.onException(1, ise.getMessage());
			return;
		}
		if (e instanceof JsonProcessingException) {
			this.onException(400111, "json错误");
			return;
		}
		if (e instanceof BusinessException) {
			this.onException(1, e.getMessage());
			return;
		}
		// 其他未处理的异常都是内部的服务错误.
		this.onException(500, e);
	}

	/**
	 * 处理hibernate validator 数据绑定异常信息
	 * 
	 * @param errors
	 */
	public void onBindingError(List<ObjectError> errors) {
		this.msg = ErrorMessageHelper.praseErrorMessage(errors);
		this.status = 1;
	}

	public boolean success() {
		return status == 0;
	}

	public void onException(int status, String cause) {
		this.status = status;
		this.setMsg(cause);
	}

	public void onHandleSuccess() {
		this.status = Constant.RETURN_CODE_SUCCESS;
		this.msg = "操作成功！";
	}

	public void onHandleSuccess(String msg) {
		this.status = Constant.RETURN_CODE_SUCCESS;
		this.msg = msg;
	}

	public void onHandleFail() {
		this.status = Constant.RETURN_CODE_FAIL;
		this.msg = "操作失败！";
	}

	public void onHandleFail(String msg) {
		this.status = Constant.RETURN_CODE_FAIL;
		this.msg = msg;
	}

	public void onHandleSaveOrUpdateFail() {
		this.status = Constant.RETURN_CODE_SUCCESS;
		this.msg = "数据更新失败，请稍后重试！";
	}
}
