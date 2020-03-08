package com.codepresso.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codepresso.board.vo.BoardVO;
import com.codepresso.board.vo.FeedVO;
import com.codepresso.board.vo.IsfollowBoardVO;
import com.codepresso.member.vo.TokenVO;



@Repository("BoardDAO")
public class BoardDAO {
	
	private  Logger logger = LoggerFactory.getLogger(BoardDAO.class);
	private String mybatisRocation = "mapper.board.";
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private BoardVO boardVO;
	
	public void selectUserIdByToken(String accessToken) {
		// TODO Auto-generated method stub
		
		
	}

	public int insertPost(BoardVO boardVO2) {
		// TODO Auto-generated method stub
		sqlSession.insert(mybatisRocation+"insertOnePost", boardVO2);
		return boardVO2.getId();
	}

	public BoardVO selectOnePostById(int id) {
		// TODO Auto-generated method stub
		logger.info("id = "+id);
		boardVO = sqlSession.selectOne(mybatisRocation+"selectOnePostById", id); // db 의 col 과 데이터 객체의 이름이 다르다.
		logger.info("boardVO = "+boardVO);
		
		return boardVO;
	}
	
	public List<BoardVO> selectAllPost() {
		List<BoardVO> resultList = sqlSession.selectList(mybatisRocation+"selectAllPost");
		return resultList;
	}


	public List<BoardVO> getMyAllPost(TokenVO tokenVO) {
		List<BoardVO> resultList = sqlSession.selectList(mybatisRocation+ "selectMyAllPost", tokenVO);
		return resultList;
	}

	public int deleteOnePostById(int id) {
		int result = sqlSession.delete(mybatisRocation+"deleteOnePostById", id);
		return result;
	}

	public int updateOnePostById(BoardVO boardVO2) {
		return sqlSession.update(mybatisRocation+"updateOnePostById", boardVO2);
	}

	public List<FeedVO> getFollowerIdListById(int userId) {
		logger.info("getFollowerIdListById. userid = "+userId);
		List<FeedVO> follewerIdList = sqlSession.selectList(mybatisRocation+"getFollowerIdListById", userId);
		logger.info("getFollowerIdListById, list = "+follewerIdList.toString());
		return follewerIdList;
	}

	public int insertFeedData(List<FeedVO> followersId, int userId, int boardId) {
		// TODO Auto-generated method stub
		for (FeedVO feedVO : followersId) {
//			feedVO.setUser_id(userId);
			feedVO.setPost_id(boardId);
			feedVO.setFollowee_id(userId);
//			followee id 는 이미 있음
			logger.info("insertFeedData , feedvo = "+feedVO.toString());
		}
		int result = sqlSession.insert(mybatisRocation+"insertFeedData", followersId);
		logger.info("insertFeedData , result = "+result);
		return 0;
	}

	public List<IsfollowBoardVO> selectFolloweesPosts(int userId) {
		// TODO Auto-generated method stub
		logger.info("-selectFolloweesPosts, userId = "+userId);
		List<IsfollowBoardVO> result = sqlSession.selectList(mybatisRocation+"selectFolloweesPosts", userId);
		for (int i = 0; i < result.size(); i++) {
			logger.info("-selectFolloweesPosts , result"+i+" = "+result.get(i).toString());
		}
		return result;
	}

	public List<IsfollowBoardVO> selectAllPostsWithIsFollow(int userId) {
		// TODO Auto-generated method stub
		List<IsfollowBoardVO> result = sqlSession.selectList(mybatisRocation+"selectAllPostsWithIsFollow", userId);
		for (int i = 0; i < result.size(); i++) {
			logger.info("-selectAllPostsWithIsFollow , result"+i+" = "+result.get(i).toString());
		}
		return result;
	}
	
}
