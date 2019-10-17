package com.tje.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tje.model.Board_Item;
import com.tje.model.Cart;
import com.tje.model.Comment;
import com.tje.model.DetailBoardItemView;
import com.tje.model.LikeAndDislike;
import com.tje.model.Member;
import com.tje.page.Criteria;
import com.tje.page.PageMaker;
import com.tje.service.board_item.ItemAddService;
import com.tje.service.board_item.ItemDeleteService;
import com.tje.service.board_item.ItemUpdateService;
import com.tje.service.board_item.ItemViewCntUpdateService;
import com.tje.service.board_item.ItemViewService;
import com.tje.service.board_item.SimpleBoardItemListCountCriteriaService;
import com.tje.service.board_item.SimpleBoardItemListCriteriaService;
import com.tje.service.cart.CartAddService;
import com.tje.service.common.CommentAddService;
import com.tje.service.common.CommentDeleteService;
import com.tje.service.common.CommentSelectService;
import com.tje.service.common.LikeAndDislikeService;

@Controller
public class Board_itemController {
	
//	@Autowired
//	private AllItemListService ilService;
	@Autowired
	private SimpleBoardItemListCriteriaService sbilcService;
	@Autowired
	private SimpleBoardItemListCountCriteriaService sbilccService;
	@Autowired
	private ItemAddService aiService;
	@Autowired
	private ItemViewService ivService;
	@Autowired
	private ItemViewCntUpdateService ivcuService;
	@Autowired
	private ItemUpdateService iuService;
	@Autowired
	private ItemDeleteService idService;
	@Autowired
	private CommentSelectService csSercie;
	@Autowired
	private LikeAndDislikeService ladService;
	@Autowired
	private CartAddService caService;
	
	@RequestMapping(value = {"/item","/item/{curPageNo}"})
	public String Item(Model model,
			Criteria criteria,
			@PathVariable(value="curPageNo", required = false) Integer curPageNo) {
		
		System.out.println(criteria.toString());
		
		if(curPageNo==null)
			curPageNo=1;
		
		// 현재 페이지
		criteria.setPage(curPageNo);
		// 페이지 당 게시물 갯수
		criteria.setPerPageNum(6);
		model.addAttribute("item_list", sbilcService.service(criteria));
		PageMaker pageMaker=new PageMaker();
		
		
		pageMaker.setCri(criteria);
		// 게시물 전체 갯수
		pageMaker.setTotalCount((int)sbilccService.service());
		System.out.println(pageMaker.toString());
		
		model.addAttribute("curPageNo", curPageNo);
		model.addAttribute("pageMaker", pageMaker);  // 게시판 하단의 페이징 관련, 이전페이지, 페이지 링크 , 다음 페이지
		
//		List<SimpleBoardItemView> result =  (List<SimpleBoardItemView>) ilService.service();
//		model.addAttribute("item_list", result);
		return "item";
	}
	
	@GetMapping("/add_item")
	public String add_item() {
		return "add_item";
	}
	
	@PostMapping(value = "/add_item", produces = "application/text; charset=utf8")
	@ResponseBody
	public String add_item(HttpServletRequest request) {
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
			int number=Integer.parseInt(multipartRequest.getParameter("number"));
			String price=multipartRequest.getParameter("price");
			String imageName=multipartRequest.getFilesystemName("image");
			String orginName=multipartRequest.getOriginalFileName("image");
			
			Board_Item item=new Board_Item(0, 3, category, title, content, member_id, number, price, imageName, 0, null);
			
			result=(int) aiService.service(item);
			
			if(result==1)
				return "상품 추가가 완료되었습니다.";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "상품 추가 과정에서 문제가 발생하였습니다.";
	}
	
	@GetMapping("/item_view/{board_id}")
	public String item_view(Model model, 
			@PathVariable(value = "board_id") Integer board_id,
			HttpSession session) {
		
		DetailBoardItemView item=new DetailBoardItemView();
		item.setBoard_id(board_id);
		
		Comment comment=new Comment();
		comment.setBoard_id(board_id);
		comment.setTopic(3);
		
		// 로그인 여부에 따른 버튼 상태
		Member login_member=(Member) session.getAttribute("login_member");
		if(login_member==null)
			model.addAttribute("btn_status", 0);
		else {
			// 로그인 한 상태에서 게시물에 좋아요 or 싫어요를 눌렀었는지 체크
			String member_id=login_member.getMember_id();
			
			LikeAndDislike lad=new LikeAndDislike();
			lad.setMember_id(member_id);
			lad.setBoard_id(board_id);
			lad.setTopic(3);
			
			LikeAndDislike result=(LikeAndDislike) ladService.selectOne(lad);
			if(result==null)
				model.addAttribute("btn_status", 0);
			else
				model.addAttribute("btn_status", result.getIs_like());
		}
		
		
		if((int)ivcuService.service(item)!=1)
			return "redirect:error/item_view";
		
		DetailBoardItemView searchedItem=(DetailBoardItemView) ivService.service(item);
		model.addAttribute("searchedItem", searchedItem);
		model.addAttribute("commentList", csSercie.service(comment));
		
		ArrayList<DetailBoardItemView> r_s_list=(ArrayList<DetailBoardItemView>) session.getAttribute("r_s_list");
		if(r_s_list==null) {
			r_s_list=new ArrayList<DetailBoardItemView>();
			
			if( !r_s_list.contains(searchedItem) )
				r_s_list.add(searchedItem);
		}else {
			if(r_s_list.size()>=8)
				r_s_list.remove(0);
			
			if( !r_s_list.contains(searchedItem) )
				r_s_list.add(searchedItem);
		}
		
		session.setAttribute("r_s_list", r_s_list);
		
		return "item_view";
	}
	
	@GetMapping("/item_update/{board_id}")
	public String item_update(
			@PathVariable(value = "board_id", required = true) Integer board_id,
			Model model) {
		
		DetailBoardItemView item=new DetailBoardItemView();
		item.setBoard_id(board_id);
		
		DetailBoardItemView result=(DetailBoardItemView) ivService.service(item);
		
		if(result==null) {
			return "redirect:/error/update_item";
		}
		
		model.addAttribute("searchedItem", result);
		
		return "update_item";
	}
	
	@PostMapping(value = "/item_update/{board_id}", produces = "application/text; charset=utf8")
	@ResponseBody
	public String item_update(
			HttpServletRequest request,
			@PathVariable(value = "board_id", required = true) Integer board_id) {
		
		DetailBoardItemView d=new DetailBoardItemView();
		d.setBoard_id(board_id);
		
		DetailBoardItemView before=(DetailBoardItemView) ivService.service(d);
		
		String beforeImage=before.getImage();
		
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
			int number=Integer.parseInt(multipartRequest.getParameter("number"));
			String price=multipartRequest.getParameter("price");
			String imageName=multipartRequest.getFilesystemName("image");
			if(imageName==null)
				imageName=beforeImage;
			String orginName=multipartRequest.getOriginalFileName("image");
			
			DetailBoardItemView item=new DetailBoardItemView(board_id, 3, category, title, content, price, number, imageName, 0, member_id, null, 0, 0, 0, null);
			
			result=(int) iuService.service(item);
			
			if(result==1)
				return "상품 수정이 완료되었습니다.";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "상품 수정 과정에서 문제가 발생하였습니다.";
	}
	
	@PostMapping(value = "/item_delete/{board_id}", produces = "application/text; charset=utf8")
	@ResponseBody
	public String item_delete(
			@PathVariable(value = "board_id", required = true) Integer board_id) {
		
		DetailBoardItemView item=new DetailBoardItemView();
		item.setBoard_id(board_id);
		
		int r = (int) idService.service(item);
		
		if(r==1) {
			return "상품 삭제를 완료했습니다.";
		}
		
		return "상품 삭제를 실패했습니다.";
	}
	
	@PostMapping(value = "/add_cart/{board_id}", produces = "application/text; charset=utf8")
	@ResponseBody
	public String add_cart(
			@PathVariable(value = "board_id", required = true) Integer board_id,
			HttpSession session,
			Model model) {
		
		DetailBoardItemView item=new DetailBoardItemView();
		item.setBoard_id(board_id);
		
		Member login_member=(Member) session.getAttribute("login_member");
		String member_id=null;
		try {
			member_id=login_member.getMember_id();
		} catch (Exception e) {

		}
			
		DetailBoardItemView result = (DetailBoardItemView) ivService.service(item);
		Cart cart=new Cart(0, board_id, member_id, result.getImage(), result.getTitle(), result.getCategory(), result.getPrice(), null);
		
		if(login_member==null) {
			ArrayList<Cart> cartList=(ArrayList<Cart>)session.getAttribute("cartList");
			
			if(cartList==null) {
				cartList=new ArrayList<Cart>();
				cart.setAdd_time(new Date());
				cartList.add(cart);
			}else {
				cart.setAdd_time(new Date());
				cartList.add(cart);
			}
			
			session.setAttribute("cartList", cartList);
			return "success";
		}
		
		int r=(int) caService.service(cart);
		
		if(r==1) {
			return "success";
		}
		
		return "fail";
	}
}
