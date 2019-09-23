package com.basics.support.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.basics.support.GuidUtils;
import com.basics.support.MD5Util;

public class UploadUtil {
	public static final String BASE_URL = "https://www.sdsjlkq.com/upload/";
	public static final String BASE_PATH = "/usr/local/nginx/html/upload/";

	public List<String> upload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
		List<String> imageList = new ArrayList<String>();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String fileName = GuidUtils.generateSimpleGuid() + MD5Util.random(6) + file.getOriginalFilename();
						// 定义上传路径
						String path = BASE_PATH + fileName;
						File localFile = new File(path);
						file.transferTo(localFile);
						imageList.add(BASE_URL + fileName);
					}
				}
			}
		}
		return imageList;
	}
}
