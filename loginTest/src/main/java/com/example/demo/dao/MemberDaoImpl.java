package com.example.demo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.MemberVo;

@Qualifier("sqlSessionTemplate")
@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession sql;
	
	private String namespace = "memberMapper.postgre.";
	
	//회원가입
	@Override
	public void register(MemberVo memberVo) throws Exception {
		sql.insert(namespace+"register",memberVo);
	}
	
	//로그인
	@Override
	public MemberVo login(MemberVo memberVo) throws Exception{
		return sql.selectOne(namespace+"login",memberVo);
	}

}
