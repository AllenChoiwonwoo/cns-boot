package com.codepresso.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("TokenVO")
public class TokenVO {
	private String token;
	private int userId;
	private Date created_at;
	
	public TokenVO() {
		
	}
	
	public TokenVO(String token, int user_id, Date created_at) {
		super();
		this.token = token;
		this.userId = user_id;
		this.created_at = created_at;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int user_id) {
		this.userId = user_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " "+token+" , "+userId;
	}

}
