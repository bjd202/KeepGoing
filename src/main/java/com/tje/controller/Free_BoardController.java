package com.tje.controller;



import java.io.File;
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


import com.tje.page.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tje.BoardService.*;
import com.tje.model.*;
import com.tje.service.*;
import com.tje.service.board_item.ItemViewCntUpdateService;
import com.tje.service.common.CommentSelectService;
import com.tje.service.common.LikeAndDislikeService;
import com.tje.service.notice.Board_NoticeSelectHeadService;
import com.tje.CategoryInfo.*;

@Controller
public class Free_BoardController {
	
	//젠장... 머리 아파
//////////////////////////////////////////
//										//
//				게시판					//
//										//
//////////////////////////////////////////
	
	
	@Autowired
	private DetailBoardFree_ViewService dbf_vService; // 자유게시판 상세내역 서비스
	@Autowired
	private DetailBoardFreeView_UpdateService dbfv_uService; // 게시판 수정 서비스
	@Autowired
	private DetailBoardFreeView_DeleteService dbfv_dService; // 게시판 삭제 서비스
	@Autowired
	private Board_freeService b_fService; // 게시판 조회 서비스
	@Autowired
	private CommentSelectService csSercie; // 댓글 조회 서비스
	@Autowired
	private LikeAndDislikeService ladService; // 좋아요 싫어요
	@Autowired
	private SimpleBoardFree_ListCountCriteriaService sbf_lccService; //	전체게시판 첫화면 _목록 개수 기준 서비스
	@Autowired
	private SimpleBoardFree_ListCriteriaService sbf_lcService; // 전체게시판 _리스트 기준 서비스
	@Autowired
	private SimpleHometownMotionPart_ListCountCriteriaService2 shmp_lccService; //	우리동네 운동부 게시판 첫화면 _목록 개수 기준 서비스
	@Autowired
	private SimpleHometownMotionPart_ListCriteriaService2 shmp_lcService; // 우리동네 운동부 게시판 첫화면 _리스트 기준 서비스
	@Autowired
	private SimpleHealthy_Diet_ListCountCriteriaService3 shd_lccservice; //건강한 식생활 게시글 게시판 첫화면 _목록 개수 기준 서비스
	@Autowired
	private SimpleHealthy_Diet_ListCriteriaService3 shd_lcService; // 건강한 식생활 게시판 첫화면 _리스트 기준 서비스
	@Autowired 
	private SimpleMy_Own_Exercise_ListCountCriteriaService4 smoe_lccSerivce; // 나만의 운동법 게시판 첫화면 _목록 개수 기준 서비스
	@Autowired
	private SimpleMy_Own_Exercise_ListCriteriaService4 smoe_lcSerivce; // 나만의 운동법 게시판 첫화면 _리스트 기준 서비스
	@Autowired 
	private SimpleBeginner_Exercise_ListCountCriteriaService5 sbe_lccSerivce; // 나만의 운동법 게시판 첫화면 _목록 개수 기준 서비스
	@Autowired
	private SimpleBeginner_Exercise_ListCriteriaService5 sbe_lcSerivce; // 나만의 운동법 게시판 첫화면 _리스트 기준 서비스
	@Autowired
	private SimpleComplex_ListCountCriteriaService6 sc_lccSerivce; //컴플랙스 극복 게시판 첫화면 _목록 개수 기준 서비스
	@Autowired
	private SimpleComplex_ListCriteriaService6 sc_lcSerivce; // 컴플랙스 극복 게시판 첫화면 _리스트 기준 서비스
	@Autowired
	private FreeViewService fvService;
	@Autowired
	private FreeViewCntUpdateService fvcuSErvice;
	@Autowired
	private Board_NoticeSelectHeadService b_nshService; //상단 공지 사항
	@Autowired
	private SimpleBoardFree_ViewSearchService sbf_vsService; // 게시글 검색 서비스
	
	
	
	
	////////////////////// 게시글 등록////////////////////  <글쓰기 버튼에 주소값 입력  게시판 안에서 공통적으로 사용 

	@GetMapping("/add_free")
	public String Add_free() {
		return "add_free";
	}
	
	@PostMapping("/add_free")
	public String Add_free(Board_Free board_Free ,Model model ) {
		
		
		Integer board_id = (Integer)b_fService.service(board_Free);
		DetailBoardFree_View dbfv = new DetailBoardFree_View();
		dbfv.setBoard_id(board_id);
		if(board_id != null) {
			model.addAttribute("searchedFree", (DetailBoardFree_View)dbf_vService.service(dbfv));
			return "free_view";
		}else {
			
		}
		
		
		return "글 등록에 실패하였습니다";
		
		
	}
////////////////////// 게시글 등록////////////////////
//		
//		
//		
////////////////////// 게시글 수정//////////////////// <수정 버튼에 주소값 입력  게시판 안에서 공통적으로 사용 

		
		@GetMapping("/update_free/{board_id}")
		public String item_update(
				@PathVariable(value = "board_id", required = true) Integer board_id,
				Model model) {
			
			DetailBoardFree_View free = new DetailBoardFree_View();
			free.setBoard_id(board_id);
			
			DetailBoardFree_View result=(DetailBoardFree_View) fvService.service(free);
			
			if(result==null) {
				return "redirect:/error/update_free";
			}
			
			model.addAttribute("searchedFree", result);
			
			return "update_free";
		}
		
		
		@PostMapping(value = "/update_free/{board_id}")
		public String free_update(
				HttpServletRequest request,
				@PathVariable(value = "board_id", required = true) Integer board_id) {
			
			DetailBoardFree_View detailBoardFreeView=new DetailBoardFree_View();
			detailBoardFreeView.setBoard_id(board_id);
			
			DetailBoardFree_View before=(DetailBoardFree_View) fvService.service(detailBoardFreeView);
			
			String beforeImage=before.getImage();//
			
			String dirPath=request.getSession().getServletContext().getRealPath("/resources/images");
			System.out.println(dirPath);
			File dir=new File(dirPath);
			
			if(!dir.exists())
				dir.mkdirs();
			
			int size=10*1024*1024;
			
			MultipartRequest multipartRequest=null;
			int result=0;
			
			try {
				multipartRequest=new MultipartRequest(request, dirPath, size, "utf-8", new DefaultFileRenamePolicy());
				String member_id=multipartRequest.getParameter("member_id");
				String title=multipartRequest.getParameter("title");
				String content=multipartRequest.getParameter("content");
				String strCategory=multipartRequest.getParameter("category");
				int category=Integer.parseInt(strCategory);
				String imageName=multipartRequest.getFilesystemName("image");
				if(imageName==null)
					imageName=beforeImage;
				String orginName=multipartRequest.getOriginalFileName("image");
				
				DetailBoardFree_View free=new DetailBoardFree_View(board_id, 2, category, title, content, imageName, 0, member_id, null, 0, 0, 0, null);
				
				result=(int) dbfv_uService.service(free);
				
				if(result==1)
					return "update_free_view";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "update_free_view_err";
		}

	////////////////////// 게시글 수정////////////////////
		
	////////////////////// 게시글 삭제//////////////////// <삭제 버튼에 주소값 입력  게시판 안에서 공통적으로 사용
	@GetMapping("/delete_free/{board_id}")
	public String Delete_free(DetailBoardFree_View detailBoardFreeView, @PathVariable(value = "board_id") Integer board_id, Model model) {
		System.out.println(detailBoardFreeView.getBoard_id());
		int r=(int) dbfv_dService.service(detailBoardFreeView);
		if(r==1) {
			model.addAttribute("resultMsg", "삭제 성공");
			return "delete_free";
		}
		
		model.addAttribute("resultMsg", "삭제 실패");
		return "delete_free";

	}
	//////////////////////게시글 삭제////////////////////
//	
//	
//	
	//////////////////////페이징 처리//////////////////// 
	
	@RequestMapping(value = {"/free/{category_num}","/free/{category_num}/{curPageNo}"})
	public String free(Model model,Criteria criteria, DetailBoardFree_View detailBoardFreeView,
			@PathVariable(value="category_num", required = false) Integer category_num,
			@PathVariable(value="curPageNo", required = false) Integer curPageNo) {
		
		// 상단 공지 사항
		List<Board_Notice> board_noticeheadList = (List<Board_Notice>)b_nshService.service();
		model.addAttribute("board_noticeheadList", board_noticeheadList);
		
		if(curPageNo==null) {
			curPageNo=1;
		}
		
		
		criteria.setPage(curPageNo);
		criteria.setPerPageNum(3);
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(criteria);
		
		// 전체 게시글
		if( category_num == 1 ) {
			model.addAttribute("free_list", sbf_lcService.service(criteria));
			pageMaker.setTotalCount((int)sbf_lccService.service());
			model.addAttribute("curPageNo", curPageNo);	 // 기준
			model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
//			model.addAttribute("category_name", Borad_Free_Info.getCategoryName(category_num)); // 카테고리의 값을 변환 시켜준다.
			model.addAttribute("category_num", category_num);
			System.out.println(pageMaker.toString());
		}
		// 우리동네 운동부 게시글 /e/
		else if (category_num == 2) {			
			model.addAttribute("free_list", shmp_lcService.service(criteria));
			pageMaker.setTotalCount((int)shmp_lccService.service());
			model.addAttribute("curPageNo", curPageNo);	 // 기준
			model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
			model.addAttribute("category_name", Borad_Free_Info.getCategoryName(category_num)); // 카테고리의 값을 변환 시켜준다.
			model.addAttribute("category_num", category_num);
			System.out.println(pageMaker.toString());
		}
		// 건강한 식생활	 게시글  healthy_diet  /e/
		else if (category_num == 3) {			
			model.addAttribute("free_list", shd_lcService.service(criteria));
			pageMaker.setTotalCount((int)shd_lccservice.service());
			model.addAttribute("curPageNo", curPageNo);	 // 기준
			model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
			model.addAttribute("category_name", Borad_Free_Info.getCategoryName(category_num)); // 카테고리의 값을 변환 시켜준다.
			model.addAttribute("category_num", category_num);
			System.out.println(pageMaker.toString());
		}
		// 나만의 운동법  게시글 My_own_exercise /e/
		else if (category_num == 4) {			
			model.addAttribute("free_list", smoe_lcSerivce.service(criteria));
			pageMaker.setTotalCount((int)smoe_lccSerivce.service());
			model.addAttribute("curPageNo", curPageNo);	 // 기준
			model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
			model.addAttribute("category_name", Borad_Free_Info.getCategoryName(category_num)); // 카테고리의 값을 변환 시켜준다.
			System.out.println(pageMaker.toString());
		}
		// 초보자를 위한 운동 추천 게시글 Beginner_exercise /e/
		else if (category_num == 5) {			
			model.addAttribute("free_list", sbe_lcSerivce.service(criteria));
			pageMaker.setTotalCount((int)sbe_lccSerivce.service());
			model.addAttribute("curPageNo", curPageNo);	 // 기준
			model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
			model.addAttribute("category_name", Borad_Free_Info.getCategoryName(category_num)); // 카테고리의 값을 변환 시켜준다.
			model.addAttribute("category_num", category_num);
			System.out.println(pageMaker.toString());
		}
		// 콤플랙스 극복 게시글 Complex /e/
		else if (category_num == 6) {
			model.addAttribute("free_list", sc_lcSerivce.service(criteria));
			pageMaker.setTotalCount((int)sc_lccSerivce.service());
			model.addAttribute("curPageNo", curPageNo);	 // 기준
			model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
			model.addAttribute("category_name", Borad_Free_Info.getCategoryName(category_num)); // 카테고리의 값을 변환 시켜준다.
			model.addAttribute("category_num", category_num);
			System.out.println(pageMaker.toString());
		}
		return "free";
	}
	//////////////////////페이징 처리////////////////////  <페이징 처리, 게시판 안에서 공통적으로 사용
//	
//	
//			
	/////////////////////자유게시판 조회 ////////////////	
	
	@GetMapping("/free_view/{board_id}")
	public String free_view(Model model, 
			@PathVariable(value = "board_id") Integer board_id,
			HttpSession session) {
		
		
		DetailBoardFree_View free =new DetailBoardFree_View();
		free.setBoard_id(board_id);
		
		model.addAttribute("searchedFree", (DetailBoardFree_View)dbf_vService.service(free));		
	
		Comment comment=new Comment();
		comment.setBoard_id(board_id);
		comment.setTopic(2);
		
		/////////// 좋아요 싫어요
		Member login_member=(Member) session.getAttribute("login_member");
		if(login_member==null)
			model.addAttribute("btn_status", 0);
		else {
			// 로그인 한 상태에서 게시물에 좋아요 or 싫어요를 눌렀었는지 체크
			String member_id=login_member.getMember_id();
			
			LikeAndDislike lad=new LikeAndDislike();
			lad.setMember_id(member_id);
			lad.setBoard_id(board_id);
			lad.setTopic(2);
			
			LikeAndDislike result=(LikeAndDislike) ladService.selectOne(lad);
			if(result==null)
				model.addAttribute("btn_status", 0);
			else
				model.addAttribute("btn_status", result.getIs_like());
		}
		
		
		if((int)fvcuSErvice.service(free)!=1)
			return "redirect:error/free_view";
		
		model.addAttribute("searchedFree", fvService.service(free));
		model.addAttribute("commentList", csSercie.service(comment));
		
		return "free_view";
	}
///////////////////// 자유게시판 조회 ////////////////	
//	
//	
//			
///////////////////// 게시글 검색 ////////////////	수정 해야할 구간... 위 free 조회에서 어떻게 합칠지
	@PostMapping(value= {"/free/search","/free/search/{curPageNo"})
	public String searchFree(Model model, HttpServletRequest req, Criteria criteria,
			@PathVariable(value="curPageNo", required = false) Integer curPageNo) {
		if(curPageNo==null) {
			curPageNo=1;
		}
		// 현재 페이지
		criteria.setPage(curPageNo);
		// 페이지 당 게시물 갯수
		criteria.setPerPageNum(3);
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(criteria);
		
		int category_Num = Integer.parseInt(req.getParameter("category_Num"));
		System.out.println(category_Num);
		int search_Type = Integer.parseInt(req.getParameter("search_Type"));
		String keyword = req.getParameter("keyword");
		System.out.println(category_Num + "" + search_Type + keyword);
		
		HashMap<String, Object> result = (HashMap<String, Object>)sbf_vsService.service(search_Type, category_Num, keyword, criteria);
		model.addAttribute("free_list", (List<SimpleBoardFreeView>)result.get("list"));
		int count = (Integer)result.get("count") == null ? 0 : (Integer)result.get("count");
		pageMaker.setTotalCount(count);
		
		model.addAttribute("curPageNo", curPageNo);
		model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		model.addAttribute("strCategory", Borad_Free_Info.getCategoryName(category_Num));
		model.addAttribute("category_Num", category_Num);
		return "free";
	}


	
///////////////////// 게시글 검색 ////////////////	
}



