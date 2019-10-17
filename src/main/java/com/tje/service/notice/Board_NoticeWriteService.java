package com.tje.service.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Notice;
import com.tje.repo.Board_NoticeDAO;


@Service
public class Board_NoticeWriteService {
	@Autowired
	private Board_NoticeDAO board_noticeDAO;
	
	public Object service(Object args) {
		Object result=null;
		result=board_noticeDAO.insert((Board_Notice)args);
		return result;
	}

}
