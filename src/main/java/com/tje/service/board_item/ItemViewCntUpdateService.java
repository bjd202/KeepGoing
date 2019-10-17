package com.tje.service.board_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.DetailBoardItemView;
import com.tje.repo.DetailBoardItemViewDAO;

@Service
public class ItemViewCntUpdateService {

	@Autowired
	private DetailBoardItemViewDAO detailBoardItemViewDAO;

	public Object service(Object args) {
		Object result = null;

		result = detailBoardItemViewDAO.update_view_cnt((DetailBoardItemView)args);

		return result;
	}
}
