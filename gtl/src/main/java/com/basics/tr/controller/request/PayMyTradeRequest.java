package com.basics.tr.controller.request;

import org.springframework.web.multipart.MultipartFile;

import com.basics.common.TokenIdRequest;

public class PayMyTradeRequest extends TokenIdRequest {
	
	private static final long serialVersionUID = 6589195227577187900L;

	private MultipartFile[] files;
	
	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

}
