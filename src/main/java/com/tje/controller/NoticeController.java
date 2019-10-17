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

import com.tje.model.*;
import com.tje.page.Criteria;
import com.tje.page.PageMaker;
import com.tje.service.notice.*;

@Controller
public class NoticeController {

	@Autowired
	private Board_NoticeSelectAllByBoardIdDescService b_nsabdService;
	@Autowired
	private Board_NoticeSelectHeadService b_nshService;
	@Autowired
	private Board_NoticeWriteService b_nwService;
	@Autowired
	private Board_NoticeReadService b_nrService;
	@Autowired
	private Board_NoticeReadUpService b_nruService;
	@Autowired
	private Board_NoticeReadDownService b_nrdService;
	@Autowired
	private Board_NoticeListService b_nlcService;

	
	@GetMapping(value = {"/notice", "/notice/{curPageNo}"})
	public String Notice(Model model, Criteria criteria,
			@PathVariable(value = "curPageNo", required = false) Integer curPageNo) {
		if (curPageNo == null) {
			curPageNo = 1;
		}

		// 현재 페이지
		criteria.setPage(curPageNo);
		// 페이지 당 게시물 갯수
		criteria.setPerPageNum(10);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		
		
		List<Board_Notice> board_noticeheadList = (List<Board_Notice>)b_nshService.service();
		model.addAttribute("board_noticeheadList", board_noticeheadList);
		
		
		HashMap<String, Object> result = (HashMap<String, Object>) b_nlcService.service(criteria);
		model.addAttribute("board_noticeList", (List<Board_Notice>) result.get("list"));
		int count = (Integer) result.get("count") == null ? 0 : (Integer) result.get("count");
		pageMaker.setTotalCount(count);
		model.addAttribute("curPageNo", curPageNo);
		model.addAttribute("pageMaker", pageMaker);
		
		return "notice";
	}
	
	@GetMapping("/notice/write")
	public String NoticeWrite() {

		return "noticeWrite";
	}

	
	@PostMapping("/add_notice")
	public String NoticeWrite(Board_Notice board_Notice) {
		int r=(int) b_nwService.service(board_Notice);
		if(r==1) {
			return "redirect:notice";
		}
		
		return "글 등록에 실패하였습니다";
	}
	
	@GetMapping("/notice/read/{board_id}")
	public String NoticeRead(Model model, @PathVariable(value="board_id") Integer board_id) {
		Board_Notice notice = new Board_Notice();
		notice.setBoard_id(board_id);
		
		model.addAttribute("noticeRead", b_nrService.service(notice));
		model.addAttribute("noticeReadUp", b_nruService.service(notice));
		model.addAttribute("noticeReadDown", b_nrdService.service(notice));
		return "notice_read";
	}
	
	
}
