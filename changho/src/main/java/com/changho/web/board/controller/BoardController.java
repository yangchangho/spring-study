package com.changho.web.board.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.changho.web.board.model.BoardVO;
import com.changho.web.board.model.ReplyVO;
import com.changho.web.board.service.BoardService;
import com.changho.web.common.Pagination;
import com.changho.web.common.Search;
import com.changho.web.error.controller.CommonExceptionAdvice;


@Controller

@RequestMapping(value = "/board")

public class BoardController {

	@Inject
	
	private BoardService boardService;

	@RequestMapping(value = "/getBoardList", method = RequestMethod.GET)

	public String getBoardList(Model model,
							   @RequestParam(required = false, defaultValue = "1")int page,
							   @RequestParam(required = false, defaultValue = "1")int range,
							   @RequestParam(required = false, defaultValue = "title")String searchType,
							   @RequestParam(required = false) String keyword ,
							   @ModelAttribute("search")Search search
							   ) throws Exception {
		
		model.addAttribute("search",search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// 전체 게시글 개수
		
		int listCnt = boardService.getBoardListCnt(search);
		
		// 검색
		search.pageInfo(page, range, listCnt);
		
		// Paginaton 객체생성 
		Pagination pagination = new Pagination();
		pagination.pageInfo(page, range, listCnt);
		
		// 페이징
		model.addAttribute("pagination", search);	
		// 게시글 화면 출력 
		model.addAttribute("boardList", boardService.getBoardList(search));

		return "board/index";

	}
	
	@RequestMapping("/boardForm")
	public String boardForm(@ModelAttribute("boardVO") BoardVO boardVO, Model model) {
		

		return "board/boardForm";
	}
	
	
	//글 저장
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)

	public String saveBoard(@ModelAttribute("boardVO") BoardVO boardVO, 

		@RequestParam("mode") String mode, RedirectAttributes rttr) throws Exception {

		if(mode.contentEquals("edit")) {
			
			boardService.updateBoard(boardVO);
			
			
		}else {
			
			boardService.insertBoard(boardVO);
			
			
		}
		return "redirect:/board/getBoardList";
		

	}
	
	//게시글 조회
	@RequestMapping(value = "/getBoardContent", method = RequestMethod.GET)
	public String getBoardContent(Model model, 
			@RequestParam("bid")int bid,
			 @ModelAttribute("search")Search search) throws Exception {
		
		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		model.addAttribute("replyVO", new ReplyVO()); // 댓글관련
		model.addAttribute("search",search);
		return "board/boardContent";
	}
	
	
	// 게시글 수정
	@RequestMapping(value = "/editForm", method = RequestMethod.GET)

	public String editForm(@RequestParam("bid") int bid , 
			@RequestParam("mode") String mode,
			@ModelAttribute("search") Search search, RedirectAttributes rttr, Model model) throws Exception {

		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		model.addAttribute("mode", mode);
		model.addAttribute("boardVO", new BoardVO());
		
		
		rttr.addAttribute("page", search.getPage());
		rttr.addAttribute("range", search.getRange());
		rttr.addAttribute("searchType", search.getSearchType());
		rttr.addAttribute("keyword", search.getKeyword());

		return "board/boardForm";

	}
	
	//게시글 삭제 
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(RedirectAttributes rttr, 
							 @RequestParam("bid")int bid,
							 @ModelAttribute("search") Search search) throws Exception {
		boardService.deleteBoard(bid);
		
		rttr.addAttribute("page", search.getPage());
		rttr.addAttribute("range", search.getRange());
		rttr.addAttribute("searchType", search.getSearchType());
		rttr.addAttribute("keyword", search.getKeyword());
		
		return "redirect:/board/getBoardList";
		
	}
	
	// 예외 처리 
	@ExceptionHandler(RuntimeException.class)

	public String exceptionHandler(Model model, Exception e){
		
		final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

		logger.info("exception : " + e.getMessage());

		model.addAttribute("exception", e);

		return "error/exception";

	}


}