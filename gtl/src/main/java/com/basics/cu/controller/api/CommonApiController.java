package com.basics.cu.controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.basics.common.DataItemResponse;
import com.basics.common.DataResponse;
import com.basics.common.TokenResponse;
import com.basics.cu.controller.request.EmailRequest;
import com.basics.cu.controller.request.LoginRequest;
import com.basics.cu.controller.request.ModifyCustomerHeadRequest;
import com.basics.cu.controller.request.ModifyPassRequest;
import com.basics.cu.controller.request.RegisterUserRequest;
import com.basics.cu.controller.request.SmsRequest;
import com.basics.cu.service.CommonApiService;
import com.basics.support.FileStoreService;
import com.basics.support.GuidUtils;
import com.basics.support.MD5Util;
import com.basics.sys.entity.SysCountry;
import com.basics.sys.entity.SysVersion;

@RestController
@RequestMapping("/api/common/")
public class CommonApiController implements ApplicationContextAware {

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;

	@Autowired
	private CommonApiService commonApiService;

	@Autowired
	private FileStoreService ftpFileStoreService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 发送短信验证码
	 */
	@RequestMapping("getSmsCode")
	public DataResponse getSmsCode(@Valid SmsRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = commonApiService.doPushSms(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 发送国际短信，手机号码前面加国家编号
	 */
	@RequestMapping("getInternationalSmsCode")
	public DataResponse getInternationalSmsCode(@Valid SmsRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = commonApiService.doInternationalSms(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 发送邮件
	 */
	@RequestMapping("getEmailCode")
	public DataResponse getEmailCode(@Valid EmailRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = commonApiService.doEmailCode(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 注册
	 */
	@RequestMapping("regiestUser")
	public DataItemResponse<Integer> registerUser(@Valid RegisterUserRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<Integer> response = new DataItemResponse<Integer>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			request.setCountry("e84a151ced294a04b45a8f2086fc7157");
			response = commonApiService.doRegiestUser(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	/**
	 * 登录
	 */
	@RequestMapping("login")
	public DataItemResponse<TokenResponse> login(@Valid LoginRequest request, BindingResult result, HttpServletRequest req) {
		DataItemResponse<TokenResponse> response = new DataItemResponse<>();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			//response.onHandleFail("系统维护中....");
			response = commonApiService.doLogin(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 忘记密码
	 */
	@RequestMapping("forgetPass")
	public DataResponse forgetPass(@Valid LoginRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = commonApiService.doForgetPass(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}
	/**
	 * 修改手机号
	 */
	@RequestMapping("modifyPhone")
	public DataResponse modifyPhone(@Valid String phone){
		DataResponse response = new DataResponse();
		String isok = commonApiService.modifyPhone(phone);
		if ("1".equals(isok)) {
			response.setMsg("成功");
			response.setStatus(0);
			return response;
		}
		if ("2".equals(isok)) {
			response.setMsg("手机号不能为空！");
			response.setStatus(1);
			return response;
		}
		if ("3".equals(isok)) {
			response.setMsg("验证码发送失败！");
			response.setStatus(1);
			return response;
		}
		if ("4".equals(isok)) {
			response.setMsg("请1分钟后再尝试发送！");
			response.setStatus(1);
			return response;
		}
		return response;

	}
	@RequestMapping("judgeCode")
	public DataResponse judgeCode(@Valid String phone, String code, String type, String orderPhone){
		DataResponse response = new DataResponse();
		String isok = commonApiService.judgeCode(phone, code, type, orderPhone);
		if ("0".equals(isok)) {
			response.setMsg("成功");
			response.setStatus(0);
			return response;
		} else if ("1".equals(isok)) {
			response.setMsg("验证码输入错误！");
			response.setStatus(1);
			return response;
		} else if ("2".equals(isok)) {
			response.setMsg("验证码失效！");
			response.setStatus(1);
			return response;
		} else if ("3".equals(isok)) {
			response.setMsg("系统异常！");
			response.setStatus(1);
			return response;
		}
		return response;

	}

	/**
	 * 新手机号
	 * @param phone
	 * @return
	 */
	@RequestMapping("newPhone")
	public DataResponse newPhone(@Valid String phone){
		DataResponse response = new DataResponse();
		String success	 = commonApiService.newPhone(phone);
		if ("1".equals(success)) {
			response.setMsg("成功");
			response.setStatus(0);
			return response;
		}
		if ("2".equals(success)) {
			response.setMsg("手机号不能为空！");
			response.setStatus(1);
			return response;
		}
		if ("3".equals(success)) {
			response.setMsg("验证码发送失败！");
			response.setStatus(1);
			return response;
		}
		if ("4".equals(success)) {
			response.setMsg("该手机号已注册！");
			response.setStatus(1);
			return response;
		}
		if ("5".equals(success)) {
			response.setMsg("请1分钟后再尝试发送！");
			response.setStatus(1);
			return response;
		}
		return response;

	}


	/**
	 * 修改登录密码
	 */
	@RequestMapping("modifyLoginPass")
	public DataResponse modifyLoginPass(@Valid ModifyPassRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = commonApiService.doModifyLoginPass(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}


	/**
	 * 修改支付密码
	 */
	@RequestMapping("modifyPayPass")
	public DataResponse modifyPayPass(@Valid ModifyPassRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = commonApiService.doModifyPayPass(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 修改用户头像
	 */
	@RequestMapping("modifyCustomerHead")
	public DataResponse modifyCustomerHead(@Valid ModifyCustomerHeadRequest request, BindingResult result, HttpServletRequest req) {
		DataResponse response = new DataResponse();
		if (result.hasErrors()) {
			response.onBindingError(result.getAllErrors());
			return response;
		}
		try {
			response = commonApiService.doModifyCustomerHead(request, req);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 上传图片
	 */
	@RequestMapping("uploadImage")
	public DataItemResponse<String> uploadImage(@RequestParam("file") MultipartFile file) {
		DataItemResponse<String> response = new DataItemResponse<>();
		try {
			if (!file.isEmpty()) {
				String path = "voucher/";
				String fileName = file.getOriginalFilename();
				path += GuidUtils.generateSimpleGuid() + MD5Util.random(6) + fileName.substring(fileName.lastIndexOf("."));
				ftpFileStoreService.write(path, file.getInputStream());
				String url = ftpFileStoreService.getInternetUrl(path);
				response.setItem(url);
				response.onHandleSuccess();
				return response;
			}
		} catch (Exception e) {
			response.onException(e);
		}
		response.onHandleFail("上传失败");
		return response;
	}

	/**
	 * 上传多张图片
	 */
	@RequestMapping("uploadImages")
	public DataItemResponse<List<String>> uploadImages(@RequestParam("files") MultipartFile[] files) {
		DataItemResponse<List<String>> response = new DataItemResponse<>();
		try {
			if (null != files && files.length > 0) {
				List<String> images = new ArrayList<>();
				for (MultipartFile file : files) {
					if (!file.isEmpty()) {
						String path = "voucher/";
						String fileName = file.getOriginalFilename();
						path += GuidUtils.generateSimpleGuid() + MD5Util.random(6) + fileName.substring(fileName.lastIndexOf("."));
						this.ftpFileStoreService.write(path, file.getInputStream());
						String url = this.ftpFileStoreService.getInternetUrl(path);
						images.add(url);
					}
				}
				response.setItem(images);
				response.onHandleSuccess();
				return response;
			}
		} catch (Exception e) {
			response.onException(e);
		}
		response.onHandleFail("上传失败");
		return response;
	}

	/**
	 * base64文件上传
	 * 
	 * @return
	 */
	@RequestMapping("uploadImagesByBase64")
	public DataItemResponse<List<String>> uploadImagesByBase64(@RequestParam("baseString") String[] baseString) {
		DataItemResponse<List<String>> response = new DataItemResponse<>();
		try {
			if (null != baseString && baseString.length > 0) {
				List<String> images = new ArrayList<>();
				String path = "baseImage/";
				String dataPrix = "";
				String data = "";
				if (baseString[1] == null || "".equals(baseString[1])) {
					response.onHandleFail("上传失败，上传图片数据为空");
					return response;
				} else {
					dataPrix = baseString[0];
					data = baseString[1];
				}
				String suffix = "";
				if ("data:image/jpeg;base64".equalsIgnoreCase(dataPrix)) {// data:image/jpeg;base64,base64编码的jpeg图片数据
					suffix = ".jpg";
				} else if ("data:image/x-icon;base64".equalsIgnoreCase(dataPrix)) {// data:image/x-icon;base64,base64编码的icon图片数据
					suffix = ".ico";
				} else if ("data:image/gif;base64".equalsIgnoreCase(dataPrix)) {// data:image/gif;base64,base64编码的gif图片数据
					suffix = ".gif";
				} else if ("data:image/png;base64".equalsIgnoreCase(dataPrix)) {// data:image/png;base64,base64编码的png图片数据
					suffix = ".png";
				} else {
					response.onHandleFail("上传图片格式不合法");
					return response;
				}
				path += GuidUtils.generateSimpleGuid() + MD5Util.random(6) + suffix;
				byte[] bs = Base64Utils.decodeFromString(data);
				this.ftpFileStoreService.write(path, bs);
				String url = this.ftpFileStoreService.getInternetUrl(path);
				images.add(url);
				response.setItem(images);
				response.onHandleSuccess();
				return response;
			}
		} catch (Exception e) {
			response.onException(e);
		}
		response.onHandleFail("上传失败");
		return response;
	}

	/**
	 * 获取app版本
	 */
	@RequestMapping(value = "getVersion")
	public DataItemResponse<SysVersion> getAppVersion(Integer type) {
		DataItemResponse<SysVersion> response = new DataItemResponse<>();
		try {
			response = commonApiService.getAppVersion(type);
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 获取国家
	 */
	@RequestMapping(value = "getCountry")
	public DataItemResponse<List<SysCountry>> getCountry() {
		DataItemResponse<List<SysCountry>> response = new DataItemResponse<>();
		try {
			response = commonApiService.getCountry();
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	@RequestMapping(value = "doFlagSpecial")
	public DataResponse doFlagSpecial() {
		DataResponse response = new DataResponse();
		try {
			response = commonApiService.doFlagSpecial();
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

	/**
	 * 获取交易所及美拉下载地址
	 */
	@RequestMapping(value = "getIndexUrl")
	public DataItemResponse<Map<String, String>> getIndexUrl() {
		DataItemResponse<Map<String, String>> response = new DataItemResponse<Map<String, String>>();
		try {
			response = commonApiService.getIndexUrl();
		} catch (Exception e) {
			response.onException(e);
		}
		return response;
	}

}
