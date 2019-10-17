package com.tje.service.mypage;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Board_Qna;
import com.tje.model.Cart;
import com.tje.model.DetailBoardItemView;
import com.tje.model.Member;
import com.tje.model.Sold_item;
import com.tje.repo.Board_FaqDAO;
import com.tje.repo.Board_NoticeDAO;
import com.tje.repo.Board_qnaDAO;
import com.tje.repo.CartDAO;
import com.tje.repo.DetailBoardItemViewDAO;
import com.tje.repo.MemberDAO;
import com.tje.repo.SimpleBoardFreeViewDAO;
import com.tje.repo.SimpleBoardItemViewDAO;
import com.tje.repo.SimpleBoardReviewViewDAO;
import com.tje.repo.Sold_itemDAO;

@Service
public class BoardsSelectAllService {
	@Autowired
	private SimpleBoardReviewViewDAO ssbrvDAO;
	@Autowired
	private SimpleBoardFreeViewDAO sbfvDAO;
	@Autowired
	private SimpleBoardItemViewDAO sbivDAO;
	@Autowired
	private Board_NoticeDAO board_NoticeDAO;
	@Autowired
	private Board_FaqDAO board_FaqDAO;
	@Autowired
	private Board_qnaDAO board_qnaDAO;
	
	@Transactional(rollbackFor = Exception.class)
	public Object service() throws Exception {
		HashMap<String, Object> result=new HashMap<String, Object>();
		
		result.put("review_list", ssbrvDAO.selectAll());
		result.put("free_list", sbfvDAO.selectAll());
		result.put("item_list", sbivDAO.selectAll());
		result.put("notice_list", board_NoticeDAO.selectAll());
		result.put("faq_list", board_FaqDAO.selectAll());
		result.put("qna_list", board_qnaDAO.selectAll());
		
		return result;
	}
}
