package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.page.Criteria;
import com.tje.repo.*;

// 페이징 갯수 서비스 /e/
@Service
public class SimpleBeginner_Exercise_ListCriteriaService5 {
	@Autowired
	private SimpleBoardFreeViewDAO simpleBoardFreeViewDAO;
	
	public Object service(Criteria criteria) {
		Object result=null;
		result=simpleBoardFreeViewDAO.Beginner_Exercise_listCriteria(criteria.getPageStart(), criteria.getPerPageNum());
//		result=simpleBoardFreeViewDAO.cartegori
		return result;
	}
	
}