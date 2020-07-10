package com.changho.web.member.dao;

import com.changho.web.member.model.MemberVO;

public interface MemberDAO {
	
	// 회원가입
	public void register(MemberVO memeberVO) throws Exception;
	
	// 로그인 
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	// 회원정보 수정
	public void memberUpdate(MemberVO memberVO)throws Exception;
	
	// 회원 탈퇴
	public void memberDelete(MemberVO memberVO)throws Exception;
	
	// 패스워드 체크
	public int passChk(MemberVO memberVO) throws Exception;
	
	// 아이디 중복체크
	public int idChk(MemberVO memberVO) throws Exception;


}
