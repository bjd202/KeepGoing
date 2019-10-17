package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.*;
import com.tje.repo.*;

@Service
public class FreeViewCntUpdateService {

	@Autowired
	private DetailBoardFreeViewDAO detailBoardFreeViewDAO;

	public Object service(Object args) {
		Object result = null;

		result = detailBoardFreeViewDAO.update_view_cnt((DetailBoardFree_View)args);

		return result;
	}
}
