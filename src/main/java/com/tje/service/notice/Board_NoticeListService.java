package com.tje.service.notice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.page.Criteria;
import com.tje.repo.*;

@Service
public class Board_NoticeListService {
	@Autowired
	private Board_NoticeDAO board_noticeDAO;
	
	public Object service(Criteria criteria) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try {
			result.put("list", board_noticeDAO.selectAllOrdByDateDesc(criteria.getPageStart(), criteria.getPerPageNum()));
			result.put("count", board_noticeDAO.listCountCriteria());
		} catch (Exception e) {
			result = null;
		}
		
		return result;
	}
}
