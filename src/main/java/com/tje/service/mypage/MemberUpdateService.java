package com.tje.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Member;
import com.tje.repo.MemberDAO;

@Service
public class MemberUpdateService {
	@Autowired
	private MemberDAO memberDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		result=memberDAO.update((Member)args);
		
		return result;
	}
}
