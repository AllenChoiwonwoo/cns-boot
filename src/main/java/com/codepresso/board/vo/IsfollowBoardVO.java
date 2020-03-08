package com.codepresso.board.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import com.codepresso.member.vo.MemberVOwithIsFollow;
import com.fasterxml.jackson.annotation.JsonInclude;


@Component("IsfollowBoardVO")
public class IsfollowBoardVO {
	
	private int id;
	private int userId;
	private String title;
	private String content;
	private Date createdAt;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MemberVOwithIsFollow user;


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(user != null) {
			return "BoardVO : "+id+","+userId+","+title+","+content+","+createdAt+", "+user;
		}
		return "BoardVO : "+id+","+userId+","+title+","+content+","+createdAt;
	}
	
	
	public IsfollowBoardVO() {
		super();
	}
	
//	public IsfollowBoardVO(int id, int userId, String title, String content, Date createdAt) {
//		super();
//		this.id = id;
//		this.userId = userId;
//		this.title = title;
//		this.content = content;
//		this.createdAt = createdAt;
//	}
	public IsfollowBoardVO(int id, int userId, String title, String content, Date createdAt, MemberVOwithIsFollow user) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.user = user;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public MemberVOwithIsFollow getUser() {
		return user;
	}


	public void setUser(MemberVOwithIsFollow user) {
		this.user = user;
	}



	
	
}
