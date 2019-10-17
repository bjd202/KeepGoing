package com.tje.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Member;
import com.tje.repo.MemberDAO;

@Service
public class MemberIDCheckService {
	@Autowired
	private MemberDAO memberDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		try {
			result=memberDAO.selectOne((Member)args);
		} catch (Exception e) {
			result=null;
		}
		
		return result;
	}
}
