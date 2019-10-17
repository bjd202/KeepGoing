package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.*;
import com.tje.repo.*;

@Service
public class FreeUpdateService {

	@Autowired
	private DetailBoardFreeViewDAO detailBoardFreeViewDAO;

	public Object service(Object args) throws Exception {
		Object result = null;

		result = detailBoardFreeViewDAO.update((DetailBoardFree_View)args);

		return result;
	}
}
