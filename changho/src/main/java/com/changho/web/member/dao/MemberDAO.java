package com.changho.web.member.dao;

import com.changho.web.member.model.MemberVO;

public interface MemberDAO {
	
	// 회원가입
	public void register(MemberVO memeberVO) throws Exception;
	
	// 로그인 
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	// 회원정보 수정
	public void memberUpdate(MemberVO memberVO)throws Exception;

}
