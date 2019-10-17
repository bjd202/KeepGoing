package com.tje.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Member_Auth;
import com.tje.repo.Member_AuthDAO;

@Service
public class MemberAuthSelectOneService {
	@Autowired
	private Member_AuthDAO member_authDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		result=member_authDAO.selectOneNickName((Member_Auth)args);
		
		return result;
	}
}
