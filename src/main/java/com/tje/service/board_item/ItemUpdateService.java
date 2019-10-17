package com.tje.service.board_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.DetailBoardItemView;
import com.tje.repo.DetailBoardItemViewDAO;

@Service
public class ItemUpdateService {

	@Autowired
	private DetailBoardItemViewDAO detailBoardItemViewDAO;

	public Object service(Object args) {
		Object result = null;

		result = detailBoardItemViewDAO.update((DetailBoardItemView)args);

		return result;
	}
}
