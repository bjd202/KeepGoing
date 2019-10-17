package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Free;
import com.tje.model.DetailBoardFree_View;
import com.tje.repo.Board_freeDAO;
import com.tje.repo.DetailBoardFreeViewDAO;

//게시판 삭제 서비스
@Service

public class DetailBoardFreeView_DeleteService {
	@Autowired
	private DetailBoardFreeViewDAO detailBoardFreeViewDAO;
	
	public Object service(DetailBoardFree_View detailBoardFreeView) {
		int result=0;
		result=detailBoardFreeViewDAO.delete(detailBoardFreeView);
		return result;
	}
}
