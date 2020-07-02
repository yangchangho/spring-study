package com.changho.web.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

import com.changho.web.board.model.BoardVO;


@Repository

public class BoardDAOImpl implements BoardDAO {

	

	@Inject

	private SqlSession sqlSession;



	@Override

	public List<BoardVO> getBoardList() throws Exception {

		return sqlSession.selectList("com.changho.web.board.boardMapper.getBoardList");

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

	

}