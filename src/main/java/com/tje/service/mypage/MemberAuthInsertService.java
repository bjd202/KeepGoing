package com.tje.service.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Member_Auth;
import com.tje.repo.Member_AuthDAO;

@Service
public class MemberAuthInsertService {
	@Autowired
	private Member_AuthDAO member_authDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		result=member_authDAO.insert((Member_Auth)args);
		
		return result;
	}
}
