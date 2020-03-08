package com.codepresso.board.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("feedVO")
public class FeedVO {
	
	private int user_id;
	private int followee_id;
	private int post_id;
	private Date created_at;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getFollowee_id() {
		return followee_id;
	}
	public void setFollowee_id(int followee_id) {
		this.followee_id = followee_id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public FeedVO(int user_id, int followee_id, int post_id, Date created_at) {
		super();
		this.user_id = user_id;
		this.followee_id = followee_id;
		this.post_id = post_id;
		this.created_at = created_at;
	}
	public FeedVO() {
		super();
	}
	@Override
	public String toString() {
		return "feedVO [user_id=" + user_id + ", followee_id=" + followee_id + ", post_id=" + post_id + ", created_at="
				+ created_at + "]";
	}
	
	
}
