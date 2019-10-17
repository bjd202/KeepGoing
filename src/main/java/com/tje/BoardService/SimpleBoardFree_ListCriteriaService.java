package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.page.Criteria;
import com.tje.repo.*;

// 페이징 갯수 서비ㅣ스
@Service
public class SimpleBoardFree_ListCriteriaService {
	@Autowired
	private SimpleBoardFreeViewDAO simpleBoardFreeViewDAO;
	
	public Object service(Criteria criteria) {
		Object result=null;
		result=simpleBoardFreeViewDAO.listCriteria(criteria.getPageStart(), criteria.getPerPageNum());
//		result=simpleBoardFreeViewDAO.cartegori
		return result;
	}
	
}