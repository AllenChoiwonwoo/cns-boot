package com.codepresso.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.member.service.MemberService;
import com.codepresso.member.vo.FollowVO;
import com.codepresso.member.vo.MemberVO;
import com.codepresso.member.vo.RestReturnMemberVO;
import com.codepresso.member.vo.RestReturnTokenVO;
import com.codepresso.member.vo.TokenVO;
import com.codepresso.member.vo.TokenVO2;


@RestController
//@RequestMapping(value="/rest/*")
@RequestMapping
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	@Autowired
	MemberVO membervo;
	@Autowired
	TokenVO tokenVO;
	@Autowired
	RestReturnMemberVO restReturnMemberVO;
	@Autowired
	RestReturnTokenVO restReturnTokenVO;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public RestReturnMemberVO signup(
			@RequestBody TokenVO2 tokenvo2,
//			@RequestBody TokenVO tokenvo2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		membervo.setUsername(tokenvo2.getUsername());
		membervo.setPassword(tokenvo2.getPassword());

		logger.info("username , password : "+tokenvo2.getUsername()+", "+tokenvo2.getPassword());
		try {
			membervo = memberService.addMember(membervo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			membervo.setPassword(null);
			restReturnMemberVO.setCode(500);
			restReturnMemberVO.setMessage("error");
			restReturnMemberVO.setData("중복된 이메일 입니다");
		}
		membervo.setPassword(null);
		restReturnMemberVO.setCode(200);
		restReturnMemberVO.setMessage("Success");
		restReturnMemberVO.setData(membervo);
		return restReturnMemberVO;
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public RestReturnMemberVO selectUserInfo(
//			@RequestBody TokenVO2 tokenvo2,
			@RequestHeader(value="accesstoken", required = false) String accesstoken2,
			@CookieValue(value="accesstoken", required = false) String accesstoken,
			@RequestParam("id") String id,
//			@Request
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(accesstoken == null || accesstoken.equals("undefined")) {
			System.out.println("Accesstoken2= "+accesstoken2);
			if(accesstoken2 == null || accesstoken2.equals("undefined")) {
				restReturnMemberVO.setData("token 값이 오지 않음");
				return restReturnMemberVO;
			}
			accesstoken = accesstoken2;
		}
		logger.info(" user - get. selectUserInfo 메서도 호출 됨 ");
//		String id = request.getParameter("id");
//		Long long_id = Long.parseUnsignedLong(id);
		
		MemberVO memVO = memberService.getUserInfoById(id);
		
		
		restReturnMemberVO.setData(memVO);
		restReturnMemberVO.setCode(200);
		restReturnMemberVO.setMessage("Success");
		return restReturnMemberVO;

	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public RestReturnTokenVO checkUserInfo(
			@RequestBody TokenVO2 tokenvo2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
//			System.out.println("/auth 요청이 컨으로 들어옴");
			logger.info("/auth 요청이 컨으로 들어옴");
			// 여기까지는 들어온다. 이걸 db에 넣고 빼고 하면 된다.
			membervo.setUsername(tokenvo2.getUsername());
			membervo.setPassword(tokenvo2.getPassword());
//			System.out.println(tokenvo2.getUsername());
			logger.info(tokenvo2.getUsername());
//			String token = tokenvo2.getUsername()+randomSM();
			
			tokenVO = memberService.authenticationMember(membervo);
		
			logger.info( tokenVO.toString());
			logger.info( membervo.toString());

			restReturnTokenVO.setData(tokenVO);
			restReturnTokenVO.setCode(200);
			restReturnTokenVO.setMessage("Success");

			return restReturnTokenVO;
	}
	
	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public RestReturnMemberVO dofollow(
			@RequestHeader(value="accesstoken", required = false) String accesstoken2,
			@CookieValue(value="accesstoken", required = false) String accesstoken,
			@RequestBody Map<String,Integer> requestMap)
//			@RequestBody("followeeId") int followeeId) 
	{		
		if(accesstoken == null || accesstoken.equals("undefined")) {
			System.out.println("Accesstoken2= "+accesstoken2);
			if(accesstoken2 == null || accesstoken2.equals("undefined")) {
				restReturnMemberVO.setData("token 값이 오지 않음");
				return restReturnMemberVO;
			}
			accesstoken = accesstoken2;
		}
			int followee_id = requestMap.get("followeeId");
			logger.info(" doFollow - 호출됨, followee_id = "+followee_id);
			
			// 여기에 이제 feed, follow 테이블에 데이터를 저장할 수 있는 메서드를 만들어야한다.
		/*
		 * follow 버튼을 누르면 이 메서드가 실행된다.
		 * 1. 서비서의 addfollow(토큰) 실행
		 * 2. 서비스에서 토큰을 통해 userid를 알아냄(tokenvo, folloeeeid)
		 * 3. follow 테이블에 userid, followeeid 추가
		 * 4. 잘 추가되면 클라로 success 를 return 한다.
		 *  */
			int result = memberService.doFollow(accesstoken, followee_id);
		
		return setRestReturnMemberVO(result);
	}
	
	@RequestMapping(value = "/follow", method = RequestMethod.DELETE)
	public RestReturnMemberVO undofollow(
			@RequestHeader(value="accesstoken", required = false) String accesstoken2,
			@CookieValue(value="accesstoken", required = false) String accesstoken,
//			@RequestBody FollowVO followvo) {
			@RequestBody Map<String,Integer> requestMap) {
		if(accesstoken == null || accesstoken.equals("undefined")) {
			System.out.println("Accesstoken2= "+accesstoken2);
			if(accesstoken2 == null || accesstoken2.equals("undefined")) {
				restReturnMemberVO.setData("token 값이 오지 않음");
				return restReturnMemberVO;
			}
			accesstoken = accesstoken2;
		}
	
			
			// 여기에 이제 feed, follow 테이블에 데이터를 저장할 수 있는 메서드를 만들어야한다.
		/* ㅣ
		 * unfollow 버튼을 누러면 이 메서드가 실행된다.
		 * 1. 서비서의 undofollow 를 실행
		 * 2. follow 테이블에서 followee_id, followr_id 를 조건으로 넣어 해당 row 삭제 
		 * 3. 잘 되면 succes return
		 * 4. */
			logger.info("undo follow, accesstoken = "+accesstoken);
			int followee_id = requestMap.get("followeeId");
			logger.info(" undoFollow - 호출됨, followee_id = "+followee_id);
			int result = memberService.undoFollow(accesstoken,followee_id);
		
		return setRestReturnMemberVO(result);
	}
	
	private RestReturnMemberVO setRestReturnMemberVO(int result) {
		if(result != 1) {
			restReturnMemberVO.setMessage("Error");
			restReturnMemberVO.setCode(500);
			restReturnMemberVO.setData("follow sql is fail!");
			return restReturnMemberVO;
		}
		restReturnMemberVO.setData("Success");
	
		return restReturnMemberVO;
	}
	
	

	
	
	
	
}
 