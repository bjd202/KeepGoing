package com.tje.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tje.CategoryInfo.Board_Review_Category;
import com.tje.board_review.service.Board_ReviewInsertService;
import com.tje.board_review.service.Board_ReviewUpdateService;
import com.tje.board_review.service.DetailBoardReviewViewSelectOneService;
import com.tje.board_review.service.ReviewDeleteTransactionService;
import com.tje.board_review.service.ReviewViewCntUpdateService;
import com.tje.board_review.service.ReviewWriteTransactionService;
import com.tje.board_review.service.SimpleBoardReviewViewSearchService;
import com.tje.board_review.service.SimpleBoardReviewViewSelectByDateDescService;
import com.tje.board_review.service.SimpleBoardReviewViewService;
import com.tje.model.Board_Review;
import com.tje.model.Comment;
import com.tje.model.DetailBoardReviewView;
import com.tje.model.LikeAndDislike;
import com.tje.model.Member;
import com.tje.model.Review_Map;
import com.tje.model.SimpleBoardReviewView;
import com.tje.page.Criteria;
import com.tje.page.PageMaker;
import com.tje.service.common.LikeAndDislikeService;
import com.tje.service.member.MemberIDCheckService;
import com.tje.service.member.MemberInsertService;
import com.tje.service.member.MemberNickNameCheckService;

@Controller
public class AndroidReviewController {
	@Autowired
	private SimpleBoardReviewViewSelectByDateDescService sbrvsbddService;
	@Autowired
	private Board_ReviewInsertService b_riService;
	@Autowired
	private Board_ReviewUpdateService b_ruService;
	@Autowired
	private DetailBoardReviewViewSelectOneService dbrvsoService;
	@Autowired
	private ReviewWriteTransactionService rwtService;
	@Autowired
	private ReviewDeleteTransactionService rdtService;

	@Autowired
	private LikeAndDislikeService ladService;
	@Autowired
	private ReviewViewCntUpdateService rvcuService;
	@Autowired
	private SimpleBoardReviewViewService sbrvService;
	@Autowired
	private SimpleBoardReviewViewSearchService sbrvsService;

	
	@RequestMapping(value = { "/android/review/{category_Num}", "/android/review/{category_Num}/{curPageNo}"})
	@ResponseBody
	public Object reviewAll(Criteria criteria,
			@PathVariable(value = "category_Num", required = false) Integer category_Num,
			@PathVariable(value = "curPageNo", required = false) Integer curPageNo) {
		System.out.println("Android Review Controller 진입");
		if (curPageNo == null) {
			curPageNo = 1;
		}
		if (category_Num == null) {
			category_Num = 1;
		}
		HashMap<String, Object> listAndCount = (HashMap<String, Object>) sbrvService.service(criteria, category_Num);
		int count = (Integer) listAndCount.get("count") == null ? 0 : (Integer) listAndCount.get("count");
		HashMap<String, Object> result = new HashMap<String, Object>();
		// ArrayList<SimpleBoardReviewView> arrList = (ArrayList<SimpleBoardReviewView>) result.get("list");
		List<SimpleBoardReviewView> list = (List<SimpleBoardReviewView>) listAndCount.get("list");
		// 현재 페이지
		criteria.setPage(curPageNo);
		// 페이지 당 게시물 갯수
		criteria.setPerPageNum(3);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		
		
		pageMaker.setTotalCount(count);
		result.put("curPageNo", curPageNo);
		result.put("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		result.put("strCategory", Board_Review_Category.returnCategory(category_Num));
		result.put("category_Num", category_Num);
		
		
		result.put("list", list);
		result.put("count", count);
		
		System.out.println(result);
		
		return result;
	}
	
	
	/*
	// 전체 게시글 컨트롤러
	@RequestMapping(value = { "/review/{category_Num}", "/review/{category_Num}/{curPageNo}" })
	public String reviewAll(Model model, Criteria criteria,
			@PathVariable(value = "category_Num", required = false) Integer category_Num,
			@PathVariable(value = "curPageNo", required = false) Integer curPageNo) {
		System.out.println(criteria.toString());
		if (curPageNo == null) {
			curPageNo = 1;
		}
		if (category_Num == null) {
			category_Num = 1;
		}
		// 현재 페이지
		criteria.setPage(curPageNo);
		// 페이지 당 게시물 갯수
		criteria.setPerPageNum(3);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);

		HashMap<String, Object> result = (HashMap<String, Object>) sbrvService.service(criteria, category_Num);
		model.addAttribute("simpleBoardReviewViewList", (List<SimpleBoardReviewView>) result.get("list"));
		int count = (Integer) result.get("count") == null ? 0 : (Integer) result.get("count");
		pageMaker.setTotalCount(count);
		model.addAttribute("curPageNo", curPageNo);
		model.addAttribute("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		model.addAttribute("strCategory", Board_Review_Category.returnCategory(category_Num));
		model.addAttribute("category_Num", category_Num);
		System.out.println(pageMaker.toString());
		return "review";
	}

	@GetMapping("/review/write")
	public String ReviewWrite(Model model) {
		List<SimpleBoardReviewView> result = (List<SimpleBoardReviewView>) sbrvsbddService.service();
		model.addAttribute("simpleBoardReviewViewList", result);
		return "reviewWrite";
	}

	@PostMapping("/review/write")
	public String ReviewWritePost(Board_Review b_r, Review_Map r_m, Model model, HttpServletRequest request) {
		// Multipart Request
		/*
		 * UUID uid = UUID.randomUUID(); String oriName = file.getOriginalFilename(); //
		 * String savedName = uid.toString() + "_" + oriName; String savedName =
		 * oriName; String dirPath=request.getSession().getServletContext().getRealPath(
		 * "/resources/images"); try { FileCopyUtils.copy(file.getBytes(), new
		 * File(dirPath,savedName) ); } catch (IOException e2) { e2.printStackTrace();
		 * System.out.println("FileCopyUtils에러"); } b_r.setImage(savedName);
		 
		String dirPath = request.getSession().getServletContext().getRealPath("/resources/images");
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		int size = 10 * 1024 * 1024;
		MultipartRequest multipartRequest = null;
		try {
			multipartRequest = new MultipartRequest(request, dirPath, size, "utf-8", new DefaultFileRenamePolicy());
			r_m.setSelectedAddress(multipartRequest.getParameter("selectedAddress"));
			r_m.setSelectedLat(Double.parseDouble(multipartRequest.getParameter("selectedLat")));
			r_m.setSelectedLng(Double.parseDouble(multipartRequest.getParameter("selectedLng")));
			b_r.setImage(multipartRequest.getFilesystemName("file"));
			b_r.setCategory(Integer.parseInt(multipartRequest.getParameter("category")));
			b_r.setTitle(multipartRequest.getParameter("title"));
			b_r.setContent(multipartRequest.getParameter("content"));
			b_r.setMember_id(multipartRequest.getParameter("member_id"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (r_m.getSelectedAddress().equals("0")) { // 주소 입력 안한 경우
			int b_rInsert = (Integer) b_riService.service(b_r);
			if (b_rInsert == 1) {
				model.addAttribute("resultMsg", "글 등록 완료 (주소 관련 정보 미 기재 등록)");
			} else {
				model.addAttribute("resultMsg", "글 등록 실패 (ReviewWritePost메소드 부분)");
			}
			return "reviewWriteResult";
		} else { // 주소 입력 한경우
			System.out.println(r_m.getSelectedAddress());
			System.out.println(r_m.getSelectedLat());
			System.out.println(r_m.getSelectedLng());
			System.out.println("-------------------");
			try {
				boolean transacResult = rwtService.service(b_r, r_m);
				if (transacResult) {
					model.addAttribute("resultMsg", "글 등록 완료 (주소 관련 정보 포함 등록)");
					return "reviewWriteResult";
				}
			} catch (Exception e) {
				model.addAttribute("resultMsg", "글 등록 실패 (ReviewWritePost메소드 부분)");
				e.printStackTrace();
			}
			return "reviewWriteResult";
		}
	}

	@GetMapping("/review/detail/{category_Num}_{board_id}")
	public String detatilReview(Model model,
			@PathVariable(value = "category_Num", required = false) Integer category_Num,
			@PathVariable(value = "board_id") Integer board_id, HttpSession session) {
		System.out.println("detail 진입");

		DetailBoardReviewView dbrv = new DetailBoardReviewView();
		dbrv.setBoard_id(board_id);

		Comment comment = new Comment();
		comment.setBoard_id(board_id);
		comment.setTopic(1);

		// 로그인 여부에 따른 버튼 상태
		Member login_member = (Member) session.getAttribute("login_member");
		if (login_member == null)
			model.addAttribute("btn_status", 0);
		else {
			// 로그인 한 상태에서 게시물에 좋아요 or 싫어요를 눌렀었는지 체크
			String member_id = login_member.getMember_id();

			LikeAndDislike lad = new LikeAndDislike();
			lad.setMember_id(member_id);
			lad.setBoard_id(board_id);
			lad.setTopic(1);

			LikeAndDislike result = (LikeAndDislike) ladService.selectOne(lad);
			if (result == null)
				model.addAttribute("btn_status", 0);
			else
				model.addAttribute("btn_status", result.getIs_like());
		}

		if ((int) rvcuService.service(dbrv) != 1)
			return "redirect:error/reviewDetail";

		DetailBoardReviewView result = (DetailBoardReviewView) dbrvsoService.service(dbrv);
		model.addAttribute("detailReview", result);
		model.addAttribute("strCategory", Board_Review_Category.returnCategory(result.getCategory()));
		model.addAttribute("category_Num", category_Num);
		return "reviewDetail";
	}

	@PostMapping(value = { "/review/search", "/review/search/{curPageNo" })
	public String searchReview(Model model, HttpServletRequest req, Criteria criteria,
			@PathVariable(value = "curPageNo", required = false) Integer curPageNo) {
		if (curPageNo == null) {
			curPageNo = 1;
		}
		// 현재 페이지
		criteria.setPage(curPageNo);
		// 페이지 당 게시물 갯수
		criteria.setPerPageNum(3);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);

		int category_Num = Integer.parseInt(req.getParameter("category_Num"));
		int search_Type = Integer.parseInt(req.getParameter("search_Type"));
		String keyword = req.getParameter("keyword");
		System.out.println(category_Num + "" + search_Type + keyword);

		HashMap<String, Object> result = (HashMap<String, Object>) sbrvsService.service(search_Type, category_Num,
				keyword, criteria);
		model.addAttribute("simpleBoardReviewViewList", (List<SimpleBoardReviewView>) result.get("list"));
		int count = (Integer) result.get("count") == null ? 0 : (Integer) result.get("count");
		pageMaker.setTotalCount(count);

		model.addAttribute("curPageNo", curPageNo);
		model.addAttribute("pageMaker", pageMaker); // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		model.addAttribute("strCategory", Board_Review_Category.returnCategory(category_Num));
		model.addAttribute("category_Num", category_Num);
		return "review";
	}
	
	
	

	@GetMapping("/update_review/{board_id}")
	public String item_update(@PathVariable(value = "board_id", required = true) Integer board_id, Model model) {

		DetailBoardReviewView dbrv = new DetailBoardReviewView();
		dbrv.setBoard_id(board_id);

		DetailBoardReviewView result = (DetailBoardReviewView) dbrvsoService.service(dbrv);

		if (result == null) {
			model.addAttribute("resultMsg", "글 수정 실패(해당 글이 존재하지 않습니다.");
			return "update_review";
		}

		model.addAttribute("searchedReview", result);

		return "reviewUpdate";
	}

	@PostMapping(value = "/update_review/{board_id}")
	public String review_update(HttpServletRequest request,
			@PathVariable(value = "board_id", required = true) Integer board_id, Model model) {

		DetailBoardReviewView dbrv = new DetailBoardReviewView();
		dbrv.setBoard_id(board_id);

		DetailBoardReviewView before = (DetailBoardReviewView) dbrvsoService.service(dbrv);

		String beforeImage = before.getImage();//
		
		Board_Review after = new Board_Review();
		after.setBoard_id(board_id);
		Review_Map afterMap = new Review_Map();

		String dirPath = request.getSession().getServletContext().getRealPath("/resources/images");
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		int size = 10 * 1024 * 1024;
		MultipartRequest multipartRequest = null;
		try {
			multipartRequest = new MultipartRequest(request, dirPath, size, "utf-8", new DefaultFileRenamePolicy());
			afterMap.setSelectedAddress(multipartRequest.getParameter("selectedAddress"));
			afterMap.setSelectedLat(Double.parseDouble(multipartRequest.getParameter("selectedLat")));
			afterMap.setSelectedLng(Double.parseDouble(multipartRequest.getParameter("selectedLng")));
			after.setImage(multipartRequest.getFilesystemName("file"));
			after.setCategory(Integer.parseInt(multipartRequest.getParameter("category")));
			after.setTitle(multipartRequest.getParameter("title"));
			after.setContent(multipartRequest.getParameter("content"));
			after.setMember_id(multipartRequest.getParameter("member_id"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (afterMap.getSelectedAddress().equals("0")) { // 주소 입력 안한 경우
			int afterInsert = (Integer) b_ruService.service(after);
			if (afterInsert == 1) {
				model.addAttribute("resultMsg", "글 수정 완료 (주소 관련 정보 미 기재 등록)");
			} else {
				model.addAttribute("resultMsg", "글 수정 실패 (ReviewWritePost메소드 부분)");
			}
			return "reviewUpdateResult";
		} else { // 주소 입력 한경우
			System.out.println(afterMap.getSelectedAddress());
			System.out.println(afterMap.getSelectedLat());
			System.out.println(afterMap.getSelectedLng());
			System.out.println("-------------------");
			try {
				boolean transacResult = rwtService.service(after, afterMap);
				if (transacResult) {
					model.addAttribute("resultMsg", "글 수정 완료 (주소 관련 정보 포함 등록)");
					return "reviewUpdateResult";
				}
			} catch (Exception e) {
				model.addAttribute("resultMsg", "글 수정 실패 (ReviewUpdatePost메소드 부분)");
				e.printStackTrace();
			}
		}
		return "reviewUpdateResult";
	}

	@GetMapping("/delete_review/{board_id}")
	public String Delete_review(@PathVariable(value = "board_id") Integer board_id, Model model) {
		Board_Review b_r = new Board_Review();
		b_r.setBoard_id(board_id);
		Review_Map r_m = new Review_Map();
		r_m.setBoard_id(board_id);
		try {
			rdtService.service(b_r, r_m);
			model.addAttribute("resultMsg", "삭제 성공");
		} catch (Exception e) {
			model.addAttribute("resultMsg", "삭제 실패");
			e.printStackTrace();
		}

		return "reviewDeleteResult";

	}
	 * 
	 */
}
