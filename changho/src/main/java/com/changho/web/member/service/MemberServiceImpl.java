package com.changho.web.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.changho.web.member.dao.MemberDAO;
import com.changho.web.member.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject MemberDAO dao;
	
	@Override
	public void register(MemberVO memberVO) throws Exception {
		
		dao.register(memberVO);
		
	}
	
	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		return dao.login(memberVO);
	}
	
	// 회원수정 
	//Controller에서 보내는 파라미터들을 memberUpdate(MemberVO vo)로 받고
	@Override
	public void memberUpdate(MemberVO memberVO) throws Exception {
		
		//받은 vo를 DAO로 보내줍니다.
		dao.memberUpdate(memberVO);
		
	}
	
}
