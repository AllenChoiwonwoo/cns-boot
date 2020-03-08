package com.codepresso;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codepresso.member.dao.MemberDAO;
import com.codepresso.member.vo.MemberVO;
import com.codepresso.member.vo.RestReturnMemberVO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	MemberDAO memberdao;
	@Autowired
	MemberVO membervo;
	@Autowired
	RestReturnMemberVO rrmvo;
	
//	Cookie cookie;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

//		membervo = memberdao.testConnection();// 연결확인용 = 연결잘됨
//		membervo.setId(1);
//		membervo.setUsername("allen");
//		membervo.setPassword("1212");
//		membervo.setCreated_at(date);
//		MemberVO newMemberVO = memberdao.addNewMember(membervo);
//		
//		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("home");
//		mav.addObject("testV", "testValue");
//		mav.addObject("membervo", membervo);

		return "login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(
//			@RequestParam MemberVO memberVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("signup method 실생됨");
		logger.info("signup method 실생됨");
//		ModelAndView mav = new ModelAndView();
		return "signup";
	}
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public ModelAndView index(
//			@RequestParam MemberVO memberVO,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("index method 실생됨");
		logger.info("index method 실생됨");
//		String token = request.getHeader("accesstoken");
//		request.getCookies();
		Cookie[] getCookie = request.getCookies();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		if(getCookie != null){
			logger.info("cookie 값 있음");
			for(int i=0; i<getCookie.length; i++){
				Cookie c = getCookie[i];
				String name = c.getName(); // 쿠키 이름 가져오기
				if (name.equals("accesstoken")) {
					String value = c.getValue(); // 쿠키 값 가져오기
					logger.info("index - token 에 값 있음 = "+value);
					mav.addObject("user", true);
				}else {
					logger.info("index  - token 에 값 없음");
				}
				
				
			}

		}else {
			logger.info("cookie 값 없음");
		}
		return mav;
	}
	
	@RequestMapping(value = "/page/detail/{id}", method=RequestMethod.GET)
	public ModelAndView getOnePostById(
			@PathVariable("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("/page/detail/{id}  호출됨" );
		ModelAndView mav = new ModelAndView();
		mav.setViewName("detail");
		mav.addObject("id", id);
		return mav;
	}
	// put , /post/detail/{id} 부터 하면 된다.

}
