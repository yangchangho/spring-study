package com.changho.web.member.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.changho.web.member.model.MemberVO;


@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject SqlSession sql;
	
	// 회원가입
	@Override
	public void register(MemberVO memberVO) throws Exception {
		
		 sql.insert("com.changho.web.member.memberMapper.register", memberVO);
	}
	
	// 로그인
	@Override
	public MemberVO login(MemberVO memberVO) throws Exception {
		
		return sql.selectOne("com.changho.web.member.memberMapper.login", memberVO);
	}
	
	//  회원 수정
	//서비스에서 보낸 파라미터들을 memberUpdate(MemberVO vo)에 담습니다.
	@Override
	public void memberUpdate(MemberVO memberVO) throws Exception {
		// vo에 담긴 파라미터들은 memberMapper.xml에 memberMapper라는 namespace에 
		// 아이디가 memberUpdate인 쿼리에 파라미터들을 넣어줍니다.
		sql.update("com.changho.web.member.memberMapper.memberUpdate", memberVO); 
	}
	
	// 회원 탈퇴 
	@Override
	public void memberDelete(MemberVO memberVO) throws Exception {
		// MemberVO에 담긴 값들을 보내줍니다.
		// 그럼 xml에서 memberMapper.memberDelete에 보시면
		//  #{userId}, #{userPass}에 파라미터값이 매칭이 되겠지요.
		sql.delete("com.changho.web.member.memberMapper.memberDelete", memberVO);
		
	}
	
	// 패스워드 체크
	@Override
	public int passChk(MemberVO memberVO) throws Exception {
		int result = sql.selectOne("com.changho.web.member.memberMapper.passChk", memberVO);
		return result;
	}
	
	// 아이디 중복 체크
	@Override
	public int idChk(MemberVO memberVO) throws Exception {
		int result = sql.selectOne("com.changho.web.member.memberMapper.idChk", memberVO);
		return result;
	}
}
