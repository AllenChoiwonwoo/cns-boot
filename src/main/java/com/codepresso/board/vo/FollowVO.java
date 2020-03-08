package com.codepresso.board.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("followVO")
public class FollowVO {
	private int followee_id;
	private int follower_id;
	
	
	@Override
	public String toString() {
		return "followVO [followee_id=" + followee_id + ", follower_id=" + follower_id + ", created_at=" + created_at
				+ "]";
	}
	public FollowVO() {
		super();
	}
	public FollowVO(int followee_id, int follower_id, Date created_at) {
		super();
		this.followee_id = followee_id;
		this.follower_id = follower_id;
		this.created_at = created_at;
	}
	public int getFollowee_id() {
		return followee_id;
	}
	public void setFollowee_id(int followee_id) {
		this.followee_id = followee_id;
	}
	public int getFollower_id() {
		return follower_id;
	}
	public void setFollower_id(int follower_id) {
		this.follower_id = follower_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	private Date created_at;
}
