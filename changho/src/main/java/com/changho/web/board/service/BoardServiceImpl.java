package com.changho.web.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.changho.web.board.dao.BoardDAO;

import com.changho.web.board.model.BoardVO;
import com.changho.web.common.Pagination;
import com.changho.web.common.Search;
import com.changho.web.error.controller.NotFoundException;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO boardDAO;
	
	//게시판 리스트보기
	public List<BoardVO> getBoardList(Search search) throws Exception {
		
		return boardDAO.getBoardList(search);
	}
	//게시판 글 작성 
	public void insertBoard(BoardVO boardVO) throws Exception {

		boardDAO.insertBoard(boardVO);

	}
	//게시판 조회수 구현
	@Transactional
	@Override
	public BoardVO getBoardContent(int bid) throws Exception {
		
		BoardVO boardVO = new BoardVO();
		boardDAO.updateViewCnt(bid);
		
		boardVO = boardDAO.getBoardContent(bid);
//		try {
//			
//			boardVO.setBid(bid);
//			boardVO.setCate_cd("1111111111111");
//			boardDAO.updateBoard(boardVO);
//			
//		}catch (RuntimeException e) {
//			throw new NotFoundException();
//		}
		
		
//		boardDAO.updateViewCnt(bid);
//		return boardDAO.getBoardContent(bid);
		
		return boardVO;
	}
	
	//게시글 수정 
	public void updateBoard(BoardVO boardVO) throws Exception {

		boardDAO.updateBoard(boardVO);

	}
	
	//게시글 삭제
	@Override
	public void deleteBoard(int bid) throws Exception {
		
		boardDAO.deleteBoard(bid);
	}
	
	//게시글 검색
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		
		return boardDAO.getBoardListCnt(search);
	}
	
	//댓글 리스트
	@Override
	public List<ReplyVO> getReplyList(int bid) throws Exception {
		
		return boardDAO.getReplyList(bid);
	}
	
	@Override
	public int saveReply(ReplyVO replyVO) throws Exception {
		
		return boardDAO.saveReply(replyVO);
	}
	
	@Override
	public int updateReply(ReplyVO replyVO) throws Exception {
		
		return boardDAO.updateReply(replyVO);
	}
	
	@Override
	public int deleteReply(int rid) throws Exception {
		
		return boardDAO.deleteReply(rid);
	}

}
