package com.tje.service.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Notice;
import com.tje.model.SimpleBoardFreeView;
import com.tje.repo.Board_NoticeDAO;
import com.tje.repo.SimpleBoardFreeViewDAO;

@Service
public class Board_NoticeReadService {

	@Autowired
	private Board_NoticeDAO board_NoticeDAO;

	public Object service(Object args) {
		Object result = null;

		result = board_NoticeDAO.selectOne((Board_Notice) args);

		return result;
	}
}
