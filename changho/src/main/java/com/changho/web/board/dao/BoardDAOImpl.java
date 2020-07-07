package com.changho.web.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

import com.changho.web.board.model.BoardVO;
import com.changho.web.board.model.ReplyVO;
import com.changho.web.common.Pagination;
import com.changho.web.common.Search;


@Repository

public class BoardDAOImpl implements BoardDAO {

	

	@Inject

	private SqlSession sqlSession;


	//게시글 리스트 
	@Override

	public List<BoardVO> getBoardList(Search search) throws Exception {

		return sqlSession.selectList("com.changho.web.board.boardMapper.getBoardList", search);

	}



	@Override

	public BoardVO getBoardContent(int bid) throws Exception {

		return sqlSession.selectOne("com.changho.web.board.boardMapper.getBoardContent", bid);

	}



	@Override

	public int insertBoard(BoardVO boardVO) throws Exception {

		return sqlSession.insert("com.changho.web.board.boardMapper.insertBoard", boardVO);

	}



	@Override

	public int updateBoard(BoardVO boardVO) throws Exception {

		return sqlSession.update("com.changho.web.board.boardMapper.updateBoard", boardVO);

	}



	@Override

	public int deleteBoard(int bid) throws Exception {

		return sqlSession.insert("com.changho.web.board.boardMapper.deleteBoard", bid);

	}



	@Override

	public int updateViewCnt(int bid) throws Exception {

		return sqlSession.update("com.changho.web.board.boardMapper.updateViewCnt", bid);

	}
	
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		
		return sqlSession.selectOne("com.changho.web.board.boardMapper.getBoardListCnt",search);
	}
	
	//댓글 리스트 
	
	@Override
	public List<ReplyVO> getReplyList(int bid) throws Exception {
		
		return sqlSession.selectList("com.changho.web.board.replyMapper.getReplyList", bid);
	}
	
	@Override
	public int saveReply(ReplyVO replyVO) throws Exception {
		
		return sqlSession.insert("com.changho.web.board.replyMapper.saveReply", replyVO);
	}
	
	@Override
	public int updateReply(ReplyVO replyVO) throws Exception {
		
		return sqlSession.update("com.changho.web.board.replyMapper.updateReply", replyVO);
	}
	
	@Override
	public int deleteReply(int rid) throws Exception {
		
		return sqlSession.delete("com.changho.web.board.replyMapper.deleteReply", rid);
	}

	

}