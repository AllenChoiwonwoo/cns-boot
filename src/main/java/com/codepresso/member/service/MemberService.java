package com.codepresso.member.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codepresso.member.dao.MemberDAO;
import com.codepresso.member.vo.MemberVO;
import com.codepresso.member.vo.TokenVO;



@Service("memberService")
public class MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private TokenVO tokenVO;
	@Autowired
	private MemberVO memberVO;
	
	/*
	 * 인증(Authentication), 권한부여(Authorization), 접근제어(Access Control) 인증(authentication)
	 * 은 자신이 누구라고 주장하는 사람을 확인하는 절차이다.
	 */
	public TokenVO authenticationMember(MemberVO membervo) {
		MemberVO resultVO = memberDAO.selectOneUserInfoByUsernamePassword(membervo);
		tokenVO = memberDAO.getUserToken(resultVO.getId());
		logger.info(""+resultVO.getId()+"");

//		System.out.println(tokenVO.toString());
		if(tokenVO.getToken() == null) {
			tokenVO = new TokenVO();
			logger.info("아직 auth 안한 회원");
//			System.out.println("아직 auth 안한 회원");

			String token = resultVO.getUsername()+randomSM();
			logger.info("author , token = "+token);
			tokenVO.setToken(token);
			tokenVO.setUserId(resultVO.getId());
			logger.info("authorizeMembver, tokenVO = "+tokenVO.toString());
			// ㅎget user token?  ? 아직 없는데?
			memberDAO.insertToken(tokenVO);
			tokenVO = memberDAO.getUserToken(tokenVO.getUserId());
			logger.info("-authorizeMembver , tokenVO = "+tokenVO.toString());
			
			int result = memberDAO.insertFollowInfo(tokenVO.getUserId(), tokenVO.getUserId());
			logger.info(" 내가 나 자신을 팔로우 한다." + result );
			
		}else {
			logger.info("auth 한 회원");
//			
			tokenVO.getUserId();
			
		}
		
		return tokenVO;
	}
	
	public MemberVO addMember(MemberVO membervo) {
		memberDAO.insertMember(membervo);
		memberVO = memberDAO.selectOneUserInfoByUsernamePassword(membervo);
		return memberVO;
	}

	

	public MemberVO getUserInfoById(String id) {
		// TODO Auto-generated method stub
		
		
		return memberDAO.getUserInfoById(id);
	}
	
	
public static String randomSM() {
		
		int length = 15;
		StringBuffer buffer = new StringBuffer();
		  Random random = new Random();
		 
		  String[] charArray = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
		 
		  for (int i=0 ; i<length ; i++)
		  {
		    buffer.append(charArray[random.nextInt(charArray.length)]);
		  }
//		  System.out.println(buffer.toString());
		  return buffer.toString();
		
	}

public int doFollow(String accesstoken, int followeeId) {
	// TODO Auto-generated method stub
	tokenVO = memberDAO.selectUserIdByToken(accesstoken);
	int result = memberDAO.insertFollowInfo(tokenVO.getUserId(), followeeId);
	return result;
}

public int undoFollow(String accesstoken, int followeeid) {
	// TODO Auto-generated method stub
	tokenVO = memberDAO.selectUserIdByToken(accesstoken);
	int result = memberDAO.deleteFollowInfo(tokenVO.getUserId(), followeeid);
	return result;
}


	
	

}
