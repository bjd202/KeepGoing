package com.tje.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Member;
import com.tje.repo.MemberDAO;
import com.tje.repo.Member_AuthDAO;

@Service
public class AllMemberAuthService {
	@Autowired
	private Member_AuthDAO member_authDAO;
	
	public Object service() {
		Object result=null;
		
		try {
			result=member_authDAO.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
			result=null;
		}
		
		return result;
	}
}
