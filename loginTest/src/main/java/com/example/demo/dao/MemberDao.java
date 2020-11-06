package com.example.demo.dao;

import com.example.demo.vo.MemberVo;

public interface MemberDao {
	//회원가입
	public void register(MemberVo memberVo) throws Exception;

	//로그인
	public MemberVo login(MemberVo memberVo) throws Exception;
}
