package com.tje.service.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;

@Service
public class Board_NoticeSelectAllByBoardIdDescService {
	@Autowired
	private Board_NoticeDAO board_noticeDAO;
	
	public Object service() {
		Object result=null;
		
		try {
			result=board_noticeDAO.selectAllOrdByBoard_IdDesc();
		} catch (Exception e) {
			result=null;
		}
		
		return result;
	}
}
