package com.tje.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Member;
import com.tje.repo.MemberDAO;

@Service
public class MemberNickNameCheckService {
	@Autowired
	private MemberDAO memberDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		result=memberDAO.selectOneNickName((Member)args);
		
		return result;
	}
}
