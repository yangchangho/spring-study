package com.changho.web.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.changho.web.member.model.MemberVO;
import com.changho.web.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	// 회원가입 get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}
	
	// 회원가입 post
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO memberVO) throws Exception {
		logger.info("post register");
		
		int result = service.idChk(memberVO);
		try {
			if(result == 1) {
				return "/member/register";
			}else if(result == 0) {
				
				String inputPass = memberVO.getUserPass();
				String pwd = pwdEncoder.encode(inputPass);
				memberVO.setUserPass(pwd);
				service.register(memberVO);
			}
			// 요기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
			// 존재하지 않는다면 -> register
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return "redirect:/";
	}
	
	
	
	//로그인 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void getLogin() throws Exception {
		logger.info("get register");
	}
	
	//로그인 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin(MemberVO memberVO, HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("post login");
		
		session.getAttribute("member");
		MemberVO login = service.login(memberVO);
		boolean pwdMatch = pwdEncoder.matches(memberVO.getUserPass(), login.getUserPass());

		if(login != null && pwdMatch == true) {
			session.setAttribute("member", login);
		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}
		
		
		return "redirect:/";
	}
	
	// 로그아웃 
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원 수정 get
	@RequestMapping(value="/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		
		return "member/memberUpdateView";
	}

	// 회원 수정 post
	@RequestMapping(value="/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO memberVO, HttpSession session) throws Exception{
		
		
		String inputPass = memberVO.getUserPass();
		String pwd = pwdEncoder.encode(inputPass);
		memberVO.setUserPass(pwd);
		
		service.memberUpdate(memberVO);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	// 회원 탈퇴 get
	@RequestMapping(value="/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		
		return "member/memberDeleteView";
	}
	
	// 회원 탈퇴 post
	@RequestMapping(value="/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO memberVO, HttpSession session, RedirectAttributes rttr) throws Exception{
		
//		// 세션에 있는 member를 가져와 member변수에 넣어줍니다.
//		MemberVO member = (MemberVO) session.getAttribute("member");
//		// 세션에있는 비밀번호
//		String sessionPass = member.getUserPass();
//		// vo로 들어오는 비밀번호
//		String voPass = memberVO.getUserPass();
//		if(!(sessionPass.equals(voPass))) {
//
//			rttr.addFlashAttribute("msg", false);
//			return "redirect:/member/memberDeleteView";
//		}
		service.memberDelete(memberVO);
		session.invalidate();
		return "redirect:/";
	}
	
	// 패스워드 체크
	@ResponseBody
	@RequestMapping(value="/passChk", method = RequestMethod.POST)
	public boolean passChk(MemberVO memberVO) throws Exception {
		
		MemberVO login = service.login(memberVO);
		boolean pwdChk = pwdEncoder.matches(memberVO.getUserPass(), login.getUserPass());
		return pwdChk;
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/idChk", method = RequestMethod.POST)
	public int idChk(MemberVO memberVO) throws Exception {
		int result = service.idChk(memberVO);
		return result;
	}
}