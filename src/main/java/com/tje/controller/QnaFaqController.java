package com.tje.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tje.BoardService.SimpleBoardFreeViewSelectByDateDescService;
import com.tje.CategoryInfo.Board_Review_Category;
import com.tje.model.*;
import com.tje.page.*;
import com.tje.service.faqQna.*;
import com.tje.service.notice.*;
import com.tje.service.common.*;

@Controller
public class QnaFaqController {
	@Autowired
	private Board_qnaService b_qService;
	@Autowired
	private Board_NoticeSelectHeadService b_nshService;
	@Autowired
	private Board_FaqSelectAllByBoardIdDescService b_faqService;
	@Autowired
	private Board_FaqWriteService b_faqwService;
	@Autowired
	private Board_QnaReadService b_qrService;
	@Autowired
	private Board_QnaListService b_qlServaice;
	@Autowired
	private Board_QnaReadUpService b_qruService;
	@Autowired
	private Board_QnaReadDownService b_qrdService;
	@Autowired
	private CommentSelectService csService;
	
	@RequestMapping(value = { "/qna", "/qna/{curPageNo}" })
	// @RequestMapping("/qna")
	public String Qna(Model model, Criteria criteria,
			@PathVariable(value = "curPageNo", required = false) Integer curPageNo) {

		List<Board_Notice> board_noticeheadList = (List<Board_Notice>)b_nshService.service();
		model.addAttribute("board_noticeheadList", board_noticeheadList);
		
		if (curPageNo == null) {
			curPageNo = 1;
		}

		// 현재 페이지
		criteria.setPage(curPageNo);
		// 페이지 당 게시물 갯수
		criteria.setPerPageNum(10);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		
		
		HashMap<String, Object> result = (HashMap<String, Object>) b_qlServaice.service(criteria);
		model.addAttribute("board_qna", (List<Board_Qna>) result.get("list"));
		int count = (Integer) result.get("count") == null ? 0 : (Integer) result.get("count");
		pageMaker.setTotalCount(count);
		model.addAttribute("curPageNo", curPageNo);
		model.addAttribute("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		
		return "qna";
	}
	
	
	
	@GetMapping("/qna/write")
	public String QnaWrite() {
		return "add_qna";
	}
	
	@PostMapping("/qna/write")
	public String QnaWrite(Board_Qna board_qna) {
		
		int r=(int) b_qService.service(board_qna);
		if(r==1) {
			return "redirect:qna";
		}
		
		return "글 등록에 실패하였습니다";
	}
	
	@GetMapping("/qna/qnaread/{board_id}")
	public String NoticeRead(Model model, @PathVariable(value="board_id") Integer board_id, HttpSession session) {
		Board_Qna qna = new Board_Qna();
		qna.setBoard_id(board_id);
		
		Comment comment = new Comment();
		comment.setBoard_id(board_id);
		comment.setTopic(6);
		
		
		model.addAttribute("commentCount", csService.service(comment));
		
		model.addAttribute("qnaRead", b_qrService.service(qna));
		model.addAttribute("qnaReadUp", b_qruService.service(qna));
		model.addAttribute("qnaReadDown", b_qrdService.service(qna));
		return "qna_read";
	}
	
	
	@RequestMapping("/faq")
	public String Faq(Model model) {
		List<Board_Faq> faq = (List<Board_Faq>)b_faqService.service();
		model.addAttribute("faq", faq);
		return "faq";
	}
	
	@GetMapping("/faq/write")
	public String FaqWrite() {
		return "faqWrite";
	}
	
	@RequestMapping("/faq/write")
	public String FaqWrite(Board_Faq board_faq ) {
		int r=(int) b_faqwService.service(board_faq);
		if(r==1) {
			return "redirect:faq";
		}
		
		return "글 등록에 실패하였습니다";		
	}
	
	@GetMapping("/faq/rev")
	public String FaqRevice() {
		
		return "faqWrite";
	}
	
	

}
