package com.dtdream.spidernet.exception;

import org.springframework.http.HttpStatus;

public class ErrorInfo {
	private HttpStatus err_code;
	private String err_msg;
	
	public HttpStatus getCode() {
		return err_code;
	}
	public void setCode(HttpStatus code) {
		this.err_code = code;
	}
	public String getErrMsg() {
		return err_msg;
	}
	public void setErrMsg(String errMsg) {
		this.err_msg = errMsg;
	}
}
