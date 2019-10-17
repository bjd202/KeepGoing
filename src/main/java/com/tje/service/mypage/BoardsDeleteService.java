package com.tje.service.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Board_Free;
import com.tje.model.Board_Item;
import com.tje.model.Board_Review;
import com.tje.model.BoardsJosnModel;
import com.tje.model.Cart;
import com.tje.model.DetailBoardItemView;
import com.tje.model.Member;
import com.tje.model.Sold_item;
import com.tje.repo.Board_FaqDAO;
import com.tje.repo.Board_NoticeDAO;
import com.tje.repo.Board_ReviewDAO;
import com.tje.repo.Board_freeDAO;
import com.tje.repo.Board_itemDAO;
import com.tje.repo.Board_qnaDAO;
import com.tje.repo.CartDAO;
import com.tje.repo.DetailBoardItemViewDAO;
import com.tje.repo.MemberDAO;
import com.tje.repo.Sold_itemDAO;

@Service
public class BoardsDeleteService {
	@Autowired
	private Board_ReviewDAO reviewDAO;
	@Autowired
	private Board_freeDAO freeDAO;
	@Autowired
	private Board_itemDAO itemDAO;
	@Autowired
	private Board_NoticeDAO noticeDAO;
	@Autowired
	private Board_FaqDAO faqDAO;
	@Autowired
	private Board_qnaDAO qnaDAO;
	
	@Transactional(rollbackFor = Exception.class)
	public Object service(List<BoardsJosnModel> args) throws Exception {
		Object result=null;
		
		switch (args.get(0).getTopic()) {
		case 1:	
			result=reviewDAO.batchDelete(args);
			break;
		case 2:	
			result=freeDAO.batchDelete(args);
			break;
		case 3:	
			result=itemDAO.batchDelete(args);
			break;
		case 4:	
			result=noticeDAO.batchDelete(args);
			break;
		case 5:	
			result=faqDAO.batchDelete(args);
			break;
		case 6:	
			result=qnaDAO.batchDelete(args);
			break;

		default:
			break;
		}
		
		System.out.println(result);
		
		return result;
	}
}
