package com.codepresso.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component("FollowVO")
public class FollowVO {
	
	int followId;
	int FolloweeId;
}
