package com.codepresso.member.vo;

import org.springframework.stereotype.Component;

@Component("RestReturnTokenVO")
public class RestReturnTokenVO {
	int code;
	String message;
	TokenVO data;
	
	
	public RestReturnTokenVO() {
		super();
	}
	public RestReturnTokenVO(int code, String message, TokenVO data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TokenVO getData() {
		return data;
	}
	public void setData(TokenVO data) {
		this.data = data;
	}
	
	

}
