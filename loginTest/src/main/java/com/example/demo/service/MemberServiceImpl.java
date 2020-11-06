package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;
	
	//회원가입
	@Override
	public void register(MemberVo memberVo) throws Exception{
		memberDao.register(memberVo);
	}
	
	//로그인
	@Override
	public MemberVo login(MemberVo memberVo) throws Exception{
		return memberDao.login(memberVo);
	}
}
