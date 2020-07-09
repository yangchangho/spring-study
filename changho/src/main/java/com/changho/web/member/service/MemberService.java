package com.changho.web.member.service;

import com.changho.web.member.model.MemberVO;

public interface MemberService {
	
	// 회원가입
	public void register(MemberVO memberVO) throws Exception;
	
	// 로그인 
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	// 회원수정
	public void memberUpdate(MemberVO memberVO) throws Exception;

}
