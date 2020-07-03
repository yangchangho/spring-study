package com.changho.web.board.service;

import java.util.List;

import java.util.Map;



import com.changho.web.board.model.BoardVO;



public interface BoardService {



	public List<BoardVO> getBoardList() throws Exception; //게시판 리스트 보기

	public void insertBoard(BoardVO boardVO) throws Exception; //게시판 글 작성
	
	public BoardVO getBoardContent(int bid) throws Exception; // 게시판 조회수 구현

	public void updateBoard(BoardVO boardVO) throws Exception;
	
	public void deleteBoard(int bid) throws Exception;

}
