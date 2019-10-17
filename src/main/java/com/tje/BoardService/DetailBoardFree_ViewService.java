package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.DetailBoardFree_View;
import com.tje.repo.DetailBoardFreeViewDAO;

//전체 게시판 등록글 조회 서비스
@Service
public class DetailBoardFree_ViewService {
	@Autowired
	private DetailBoardFreeViewDAO detailBoardFreeViewDAO;
	
	public Object service(DetailBoardFree_View detailBoardFreeView) {
		Object result=null;
		result=detailBoardFreeViewDAO.selectOne(detailBoardFreeView);
		return result;
	}
}
