package com.codepresso.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codepresso.board.service.BoardService;
import com.codepresso.board.vo.BoardVO;
import com.codepresso.board.vo.IsfollowBoardVO;
import com.codepresso.member.dao.MemberDAO;
import com.codepresso.member.vo.MemberVO;
import com.codepresso.member.vo.RestReturnMemberVO;
import com.codepresso.member.vo.TokenVO;

@RestController
@RequestMapping
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardVO myBoardVO;
	@Autowired
	BoardService boardService;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	MemberVO memberVO;
	@Autowired
	TokenVO tokenVO;
	@Autowired
	RestReturnMemberVO restReturnMemberVO;
	
	
	@RequestMapping(value = "/post", method=RequestMethod.POST)
		public RestReturnMemberVO writePost(
				@RequestBody BoardVO boardVO,
				@CookieValue(value="accesstoken", required = false) String accesstoken,
				HttpServletRequest request, HttpServletResponse response) {
		logger.info("boardVO = "+boardVO);
		//1.토큰을 받아온다.
//		String accessToken = request.getHeader("accesstoken");
		logger.info("accessToken = "+accesstoken);
//		System.out.println("accessToken = "+accessToken);
		//2. 토큰과 매칭되는 아이디를 받아온다.
		
		
		boardVO = boardService.addNewPost(accesstoken, boardVO);
		//3. db.post 에 데이터를 넣는다. 그리고 post의 id를 받아온다.
		
		// 4. post. id 를 통해서 해당 low의 데이터를 전부 받아온다.
		
		// 5. api 요구사항에 맞게 객체에 데이터들을 담아 return 한다.
		restReturnMemberVO.setCode(200);
		restReturnMemberVO.setMessage("Success");
		restReturnMemberVO.setData(boardVO);
				
		return restReturnMemberVO;
	}
	// 모든 포스트 다 보여주기
	@RequestMapping(value = "/post", method=RequestMethod.GET)
	public RestReturnMemberVO getAllPost(
//			@RequestBody BoardVO boardVO,
			@CookieValue(value="accesstoken", required = false) String accesstoken,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("getAllPost");
		/***
		 * 1. 요청을 받는다.
		 * 2. 데이터베이스 에서 모든 글들을 id의 내림차순으로 가져온다.
		 * 3. 화면에 뿌려준다.
		 */
//		만약 토큰이 함께 온다면 , 새로 만든 메서드로 해서 isfollow 를 넣어 보내주도록한다.
		if(accesstoken == null) {
			logger.info("getAllPost #@@- 토큰이 같이 호지 않았다.");
		}else {
			logger.info("getAllPost ###- 토큰이 왔다.");
			List<IsfollowBoardVO> boardvoList = boardService.getAllPostsWithIsfollow(accesstoken);
			restReturnMemberVO.setData(boardvoList);
			return restReturnMemberVO;
		}
		
		List<BoardVO> boardvoList = boardService.getAllPost();
		logger.info("getAllPost" +boardvoList.toString());
		restReturnMemberVO.setData(boardvoList);

		return restReturnMemberVO;
	}
	
	

	@RequestMapping(value = "/post/{id}", method=RequestMethod.GET)
	public RestReturnMemberVO getOnePostById(
			@PathVariable("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
			
		myBoardVO = boardService.getOnePostById(id);
		restReturnMemberVO.setData(myBoardVO);
		return restReturnMemberVO;
	}
	
	@RequestMapping(value = "/post/{id}", method=RequestMethod.DELETE)
	public RestReturnMemberVO deleteOnePostById(
			@PathVariable("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
			
		int result = boardService.deleteOnePostById(id);
		
		restReturnMemberVO.setData(result);
		return restReturnMemberVO;
	}

	@RequestMapping(value = "/post", method=RequestMethod.PUT)
	public RestReturnMemberVO editOnePostById(
			@RequestBody BoardVO boardVO,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("/post, put 호출됨 " );
		int result = boardService.editOnePostById(boardVO);
		restReturnMemberVO.setData(result);
		return restReturnMemberVO;
	}
	// put , /post/detail/{id} 부터 하면 된다.
	
	@RequestMapping(value = "/post/my", method=RequestMethod.GET)
	public RestReturnMemberVO getMyAllPost(
			@CookieValue(value="accesstoken", required = false) String accesstoken,
			HttpServletRequest request, HttpServletResponse response) {
			logger.info("getMyAllPost");
			
//			String accessToken = request.getHeader("accesstoken");
			logger.info("accessToken = "+accesstoken);
		
			List<BoardVO> myPostList = boardService.getMyAllPost(accesstoken);
			restReturnMemberVO.setData(myPostList);
			
		return restReturnMemberVO;
	}
	
	// Feed(내가 쓴글 + 내가 follow 하는 사용자 글 ) 조회
	@RequestMapping(value = "/post/feed", method = RequestMethod.GET)
	public RestReturnMemberVO getMyFeeds(
			@RequestHeader(value="accesstoken", required = false) String accesstoken
//			,@RequestParam("followeeId") int followeeId
			) 
	{	logger.info( "get my feeds ");
		/*- 내가 글을 쓰면 나를 팔로우 하고 있는 사람들의 feed 정보를 feed 테이블에 넣어주면 됨
		 - 나의 글은 default로 feed에 입력 됨 --> 내 자신을 팔로우 하고 있다고 가정
		 - 팔로우 하는 시점 부터 feed에 정보 입력
		 - 팔로우를 끊어도 기존 feed 정보는 삭제 하지 않음
		 - 기존 My Post 탭이 My Feed로 변경 되었고 이 탭에는 나의 글과 내가 팔로우 하는 사람들이 쓴 글 목록 조회 됨
		 - followee가 follow를 당하는 사람
		 - follower가 follow를 하는 사람
		 
		 1. 토큰은 통해 로그인한 userid를 받아온다.
		 2. userid를 통해 follow테이블에서 userid가 누굴 팔로우 하고 있는야 followee_id[] 를 받아온다.
		 3. followee_id[] 에 있는 모든 값을 조건으로 해서 feed테이블에서 postid를 조회한다.
		 4. 해당 postid[] 에 담긴 모든 값을 통해 post테이블에서 조회해서 데이터를 넣는다.
		 순서는 feed.createdAt 을 최신순(desc)으로 한다.
		 -여기서 isFollow를 boolen값으로 넣어줘야하는데
	     -이건 내가 팔로우 중인 사람들의 글만 불러오는 부분이니
	     -쿼리내의 연산으로 "isFollow : true" 값이 나올 수 있게 해야겠다.
	     -그리고 결론적으로는 mybatis 에서 resultMap을 새로 설정해 postVO가 user를 품을 수 있게 해야겠다.
		 */
//			if(accesstoken != null) { // 로그인 하고 로그인하면.
//				boardService.get
//			}
			List<IsfollowBoardVO> myFeedBVOs = boardService.getMyFeeds(accesstoken);//내가 팔로우 하는사람 글 가져오기
		
		

			
			// 여기에 이제 feed, follow 테이블에 데이터를 저장할 수 있는 메서드를 만들어야한다.
			restReturnMemberVO.setCode(200);
			restReturnMemberVO.setMessage("Success");
			restReturnMemberVO.setData(myFeedBVOs);
		
		return restReturnMemberVO;
	}

}
