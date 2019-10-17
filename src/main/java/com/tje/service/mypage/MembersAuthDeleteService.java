package com.tje.service.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Member_Auth;
import com.tje.repo.Member_AuthDAO;

@Service
public class MembersAuthDeleteService {
	@Autowired
	private Member_AuthDAO member_authDAO;
	
	@Transactional(rollbackFor = Exception.class)
	public Object service(List<Member_Auth> args) throws Exception {
		Object result=null;
		
		result=member_authDAO.batchDelete(args);
		System.out.println(result);
		
		return result;
	}
}
