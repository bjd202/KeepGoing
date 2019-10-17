package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.*;
import com.tje.repo.*;

@Service
public class FreeViewService {

	@Autowired
	private DetailBoardFreeViewDAO detailBoardFreeViewDAO;

	public Object service(Object args) {
		Object result = null;

		result = detailBoardFreeViewDAO.selectOne((DetailBoardFree_View)args);

		return result;
	}
}
