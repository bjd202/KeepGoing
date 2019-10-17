package com.tje.service.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Member;
import com.tje.model.Member_Auth;
import com.tje.repo.MemberDAO;
import com.tje.repo.Member_AuthDAO;

@Service
public class MembersAuthUpdateAndDeleteService {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private Member_AuthDAO member_authDAO;
	
	@Transactional(rollbackFor = Exception.class)
	public Object service(List<Member> args1, List<Member_Auth> args2) throws Exception {
		Object result=null;
		
		result=memberDAO.batchUpdate(args1);
		System.out.println(result);
		
		result=member_authDAO.batchDelete(args2);
		System.out.println(result);
		
		return result;
	}
}
