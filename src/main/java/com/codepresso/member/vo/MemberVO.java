package com.codepresso.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component("MemberVO")
public class MemberVO {
	
	private int id=0;
	private String username=null;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String password=null;
//	private String created_at;
//	private String ;
	private Date createdAt=null;
//	@JsonInclude(JsonInclude.Include.)
//	private boolean isFollow;
	@JsonIgnore
	private String message;
	
	
//	
//	public int getId() {
//		return id;
//	}
//	@Override
//	public String toString() {
//		return "MemberVO [id=" + id + ", username=" + username + ", password=" + password + ", createdAt=" + createdAt
//				+ "]";
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//	public void setCreatedAt(Date date) {
//		this.createdAt = date;
//	}
//	
	

}
