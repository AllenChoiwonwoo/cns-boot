package com.codepresso.member.vo;

import org.springframework.stereotype.Component;

@Component("RestReturnMemberVO")
public class RestReturnMemberVO {
	
//	int id;
//	String username;
//	String createdAt;
//	String isFollow;
	
	int code = 200;
	String message ="Success";
	Object data;
	
	
	public RestReturnMemberVO() {
		super();
	}
	public RestReturnMemberVO(int code, String message, MemberVO data) {
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
	public Object getData() {
		return data;
	}
	public void setData(Object object) {
		this.data = object;
	}
	
	
	
}
