package com.tje.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tje.board_review.service.AllReviewListService;
import com.tje.board_review.service.DetailBoardReviewViewSelectOneService;
import com.tje.board_review.service.ReviewViewCntUpdateService;
import com.tje.model.Board_Item;
import com.tje.model.Cart;
import com.tje.model.CartJsonModel;
import com.tje.model.Comment;
import com.tje.model.DetailBoardItemView;
import com.tje.model.DetailBoardReviewView;
import com.tje.model.LikeAndDislike;
import com.tje.model.Member;
import com.tje.model.SimpleBoardItemView;
import com.tje.model.SimpleBoardReviewView;
import com.tje.model.Sold_item;
import com.tje.service.board_item.AllItemListService;
import com.tje.service.board_item.ItemAddService;
import com.tje.service.board_item.ItemViewCntUpdateService;
import com.tje.service.board_item.ItemViewService;
import com.tje.service.cart.CartAddService;
import com.tje.service.cart.CartDeleteService;
import com.tje.service.cart.CartListService;
import com.tje.service.cart.SoldItemAddAndCartDeleteService;
import com.tje.service.common.CommentAddService;
import com.tje.service.common.CommentSelectOneService;
import com.tje.service.common.CommentSelectService;
import com.tje.service.common.LikeAndDislikeService;
import com.tje.service.member.MemberIDCheckService;
import com.tje.service.member.MemberInsertService;
import com.tje.service.member.MemberNickNameCheckService;

@Controller
public class AndroidController {
	
	@Autowired
	private MemberInsertService miService;
	@Autowired
	private MemberIDCheckService mIDcService;
	@Autowired
	private MemberNickNameCheckService mnncService;
	@Autowired
	private AllItemListService ailService;
	@Autowired
	private ItemViewService ivService;
	@Autowired
	private ItemViewCntUpdateService ivcuService;
	@Autowired
	private CommentSelectService csService;
	@Autowired
	private CommentAddService caService;
	@Autowired
	private CommentSelectOneService csoService;
	@Autowired
	private LikeAndDislikeService ladService;
	@Autowired
	private ItemAddService aiService;
	@Autowired
	private CartListService clService;
	@Autowired
	private SoldItemAddAndCartDeleteService siaService;
	@Autowired
	private CartAddService cartService;
	@Autowired
	private CartDeleteService cdService;
	@Autowired
	private AllReviewListService arlSerview;
	@Autowired
	private ReviewViewCntUpdateService rvcuService;
	@Autowired
	private DetailBoardReviewViewSelectOneService dbrvsoService;
	
	@GetMapping("/android/address_search")
	public String address_search() {
		
		return "address_search";
	}
	
	@PostMapping(value = "/android/regist", produces = "application/text; charset=utf8")
	@ResponseBody
	public String regist(Member member) {
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		
		Member nickCheckMemeber=(Member) mnncService.service(member);
		if(nickCheckMemeber!=null) {
			map.put("msg", "중복된 닉네임 입니다.");
			String json=gson.toJson(map);
			return json;
		}
		Member idCheckMemeber=(Member) mIDcService.service(member);
		if(idCheckMemeber!=null) {
			map.put("msg", "중복된 아이디 입니다.");
			String json=gson.toJson(map);
			return json;
		}
		
		int r=(int) miService.service(member);
		
		if(r==1) {
			map.put("msg", "회원가입을 축하합니다.");
		}else {
			map.put("msg", "회원가입에 실패하였습니다.");
		}
		
		String json=gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/sns_regist", produces = "application/text; charset=utf8")
	@ResponseBody
	public String sns_regist(Member member) {
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		
		Member nickCheckMemeber=(Member) mnncService.service(member);
		if(nickCheckMemeber!=null) {
			map.put("msg", "중복된 닉네임 입니다.");
			String json=gson.toJson(map);
			return json;
		}
		Member idCheckMemeber=(Member) mIDcService.service(member);
		if(idCheckMemeber!=null) {
			map.put("msg", "중복된 아이디 입니다.");
			String json=gson.toJson(map);
			return json;
		}
		
		int r=(int) miService.service(member);
		
		if(r==1) {
			map.put("msg", "회원가입을 축하합니다.");
		}else {
			map.put("msg", "회원가입에 실패하였습니다.");
		}
		
		String json=gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String login(Member member, HttpSession session) {
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean login_result=false;
		String json="";
		
		Member result=(Member) mIDcService.service(member);
		if(result==null) {
			map.put("login_result", login_result.toString());
			map.put("login_msg", "존재하지 않는 ID 입니다.");
			json=gson.toJson(map);
			return json;
		}
		
		if(result.getPassword().equals(member.getPassword())) {
			login_result=true;
			session.setAttribute("login_member", result);
			map.put("login_result", login_result.toString());
			map.put("login_msg", String.format("%s 님 환영합니다.", result.getNickname()));
			map.put("login_nickname", member.getNickname());
			json=gson.toJson(map);
			return json;
		}
		
		map.put("login_result", login_result.toString());
		map.put("login_msg", "정보가 일치하지 않습니다.");
		json=gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/naver_login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String naver_login(Member member, HttpSession session) {
		
		System.out.println(member.getMember_id());
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean login_result=false;
		String json="";
		
		Member result=(Member) mIDcService.service(member);
		if(result==null) {
			map.put("login_result", login_result.toString());
			map.put("login_msg", "");
			json=gson.toJson(map);
			return json;
		}
		
		login_result = true;
		session.setAttribute("login_member", result);
		map.put("login_result", login_result.toString());
		map.put("login_msg", String.format("%s 님 환영합니다.", result.getNickname()));
		map.put("login_nickname", member.getNickname());
		json = gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/kakao_login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String kakao_login(Member member, HttpSession session) {
		
		System.out.println(member.getMember_id());
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean login_result=false;
		String json="";
		
		Member result=(Member) mIDcService.service(member);
		if(result==null) {
			map.put("login_result", login_result.toString());
			map.put("login_msg", "");
			json=gson.toJson(map);
			return json;
		}
		
		login_result = true;
		session.setAttribute("login_member", result);
		map.put("login_result", login_result.toString());
		map.put("login_msg", String.format("%s 님 환영합니다.", result.getNickname()));
		map.put("login_nickname", member.getNickname());
		json = gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/google_login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String google_login(Member member, HttpSession session) {
		
		System.out.println(member.getMember_id());
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean login_result=false;
		String json="";
		
		Member result=(Member) mIDcService.service(member);
		if(result==null) {
			map.put("login_result", login_result.toString());
			map.put("login_msg", "");
			json=gson.toJson(map);
			return json;
		}
		
		login_result = true;
		session.setAttribute("login_member", result);
		map.put("login_result", login_result.toString());
		map.put("login_msg", String.format("%s 님 환영합니다.", result.getNickname()));
		map.put("login_nickname", member.getNickname());
		json = gson.toJson(map);
		
		return json;
	}
	
	@GetMapping(value = "android/logout", produces = "application/text; charset=utf8")
	@ResponseBody
	public String logout(HttpSession session) {
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean logout_result=false;
		String json="";
		
		Member login_member=(Member) session.getAttribute("login_member");
		
		if(login_member!=null) {
			logout_result=true;
			
			map.put("logout_result", logout_result.toString());
			map.put("logout_msg", "로그아웃을 성공했습니다.");
			
			session.removeAttribute("login_member");
			
			json=gson.toJson(map);
			return json;
		}
		
		map.put("logout_result", logout_result.toString());
		map.put("logout_msg", "로그아웃을 실패했습니다.");
		
		json=gson.toJson(map);
		
		return json;
	}
	
	@GetMapping(value = "android/is_login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String is_login(HttpSession session) {
		
		String json="";
		Gson gson=new Gson();
		
		Member login_member=(Member) session.getAttribute("login_member");
		Boolean is_login=false;
		
		
		if(login_member != null && login_member.getAuth()>=2) {
			is_login=true;
			json=gson.toJson(is_login.toString());
			return json;
		}
		
		json=gson.toJson(is_login.toString());
		
		return json;
	}
	
	@GetMapping(value = "android/simpleItem_selectAll", produces = "application/text; charset=utf8")
	@ResponseBody
	public String simpleItem_selectAll() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				
		List<SimpleBoardItemView> itemList=(List<SimpleBoardItemView>) ailService.service();
		ArrayList<SimpleBoardItemView> convertList=new ArrayList<SimpleBoardItemView>();
		
		for (SimpleBoardItemView item : itemList) {
			convertList.add(item);
		}
		
		String json="";
		
		json=gson.toJson(convertList);
		
		System.out.println(json);
		
		return json;
	}
	
	@GetMapping(value = "android/detailBoardItemView/{board_id}", produces = "application/text; charset=utf8")
	@ResponseBody
	public String detailBoardItemView(@PathVariable("board_id") Integer board_id,
			HttpSession session){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		String json="";
		
		Member login_member=(Member) session.getAttribute("login_member");
		Boolean login_result=false;
		
		if(login_member != null) {
			login_result=true;
			map.put("login_result", login_result.toString());
		}
		
		DetailBoardItemView model=new DetailBoardItemView();
		model.setBoard_id(board_id);
		
		if( (Integer)ivcuService.service(model)==0 ) {
			map.put("view_cnt_update_fail", "조회수 업데이트 실패");
			json=gson.toJson(map);
			return json;
		}
		
		DetailBoardItemView item=(DetailBoardItemView) ivService.service(model);
		Comment comment=new Comment();
		comment.setBoard_id(item.getBoard_id());
		comment.setTopic(item.getTopic());
		
		List<Comment> commentList=(List<Comment>) csService.service(comment);
		ArrayList<Comment> convertList=new ArrayList<Comment>();
		
		if( commentList!=null ) {
			for (Comment c : commentList) {
				convertList.add(c);
			}
			
			map.put("comment_list", convertList);
		}
		
		map.put("detail_item", item);
		map.put("login_result", login_result.toString());
		json=gson.toJson(map);
		
		System.out.println(json);
		
		return json;
	}
	
	@PostMapping(value = "/android/add_comment", produces = "application/text; charset=utf8")
	@ResponseBody
	public String add_comment(Comment comment,
			HttpSession session) {
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		HashMap<String, Object> map=new HashMap<String, Object>();
		String json="";
		
		Member login_member=(Member) session.getAttribute("login_member");
		comment.setMember_id(login_member.getMember_id());
		comment.setNickname(login_member.getNickname());
		
		int comment_id=(int)caService.service(comment);
		if(comment_id != 0) {
			Comment c=new Comment();
			c.setComment_id(comment_id);
			Comment searched_comment=(Comment) csoService.service(c);
			map.put("comment", searched_comment);
			map.put("comment_result", "댓글 작성 성공");
		}else {
			map.put("comment_result", "댓글 작성 실패");
		}		
		
		json=gson.toJson(map);
		System.out.println(json);
		
		return json;
	}
	
	@PostMapping(value = "/android/like_and_dislike", produces = "application/text; charset=utf8")
	@ResponseBody
	public String like_and_dislike(LikeAndDislike likeAndDislike,
			HttpSession session) {
		
		Member login_member=(Member) session.getAttribute("login_member");
		likeAndDislike.setMember_id(login_member.getMember_id());
		
		Gson gson=new Gson();
		String json="";
		HashMap<String, Object> map=new HashMap<String, Object>();
		
		if(ladService.selectOne(likeAndDislike) == null) {
			int r=(int) ladService.insert(likeAndDislike);
			
			if(r==0) {
				System.out.println("insert 실패");
				map.put("fail", "insert fail");
				json=gson.toJson(map);
				return json;
			}
			
			map.put("like_cnt", (int)ladService.like_cnt(likeAndDislike));
			map.put("dislike_cnt", (int)ladService.dislike_cnt(likeAndDislike));
			json=gson.toJson(map);
			return json;
		}
		
		if(ladService.selectOneIsLike(likeAndDislike) != null) {
			int r=(int) ladService.delete(likeAndDislike);
			
			if(r==0) {
				System.out.println("delete 실패");
				map.put("fail", "delete fail");
				json=gson.toJson(map);
				return json;
			}
			
			map.put("like_cnt", (int)ladService.like_cnt(likeAndDislike));
			map.put("dislike_cnt", (int)ladService.dislike_cnt(likeAndDislike));
			json=gson.toJson(map);
			return json;
		}else {
			int r=(int) ladService.update(likeAndDislike);
			
			if(r==0) {
				System.out.println("update 실패");
				map.put("fail", "update fail");
				json=gson.toJson(map);
				return json;
			}
			
			map.put("like_cnt", (int)ladService.like_cnt(likeAndDislike));
			map.put("dislike_cnt", (int)ladService.dislike_cnt(likeAndDislike));
			json=gson.toJson(map);
			return json;
		}
	}
	
	@PostMapping(value = "/android/add_item", produces = "application/text; charset=utf8")
	@ResponseBody
	public String add_item(HttpServletRequest request,
			HttpSession session) {
		
		Member login_member=(Member) session.getAttribute("login_member");
		
		HashMap<String, String> map=new HashMap<String, String>();
		Gson gson=new Gson();
		String json="";
		Boolean add_item_result=false;
		
		String dirPath=request.getSession().getServletContext().getRealPath("/resources/images");
		System.out.println(dirPath);
		File dir=new File(dirPath);
		
		if(!dir.exists())
			dir.mkdirs();
		
		int size=100*1024*1024;
		
		MultipartRequest multipartRequest=null;
		int result=0;
		
		try {
			multipartRequest=new MultipartRequest(request, dirPath, size, "utf-8", new DefaultFileRenamePolicy());
			//String member_id=multipartRequest.getParameter("member_id");
			String title=multipartRequest.getParameter("title");
			String content=multipartRequest.getParameter("content");
			String strCategory=multipartRequest.getParameter("category");
			int category=Integer.parseInt(strCategory);
			int number=Integer.parseInt(multipartRequest.getParameter("number"));
			String price=multipartRequest.getParameter("price");
			String imageName=multipartRequest.getFilesystemName("image");
			String orginName=multipartRequest.getOriginalFileName("image");
			
			Board_Item item=new Board_Item(0, 3, category, title, content, login_member.getMember_id(), number, price, imageName, 0, null);
			
			result=(int) aiService.service(item);
			
			if(result==1) {
				add_item_result=true;
				map.put("add_item_result", add_item_result.toString());
				
				json=gson.toJson(map);
				return json;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("add_item_result", add_item_result.toString());
		json=gson.toJson(map);
		
		return json;
	}
	
	@GetMapping(value = "android/cart_list", produces = "application/text; charset=utf8")
	@ResponseBody
	public String cart_list(HttpSession session) {
		
		Member login_member=(Member) session.getAttribute("login_member");
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		Cart cart=new Cart();
		cart.setMember_id(login_member.getMember_id());
		List<Cart> cartList=(List<Cart>) clService.service(cart);
		ArrayList<Cart> convertList=new ArrayList<Cart>();
		
		for (Cart item : cartList) {
			convertList.add(item);
		}
		
		String json="";
		
		json=gson.toJson(convertList);
		
		System.out.println(json);
		
		return json;
	}
	
	@PostMapping(value = "/android/add_cart/{board_id}", produces = "application/text; charset=utf8")
	@ResponseBody
	public String add_cart(
			@PathVariable(value = "board_id", required = true) Integer board_id,
			HttpSession session) {
		
		Gson gson=new Gson();
		String json="";
		HashMap<String, String> map=new HashMap<String, String>();
		
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
		
		int r=(int) cartService.service(cart);
		
		if(r==1) {
			map.put("result", "true");
			json=gson.toJson(map);
			return json;
		}
		
		map.put("result", "false");
		json=gson.toJson(map);
		return json;
	}
	
	@PostMapping(value = "/android/item_buy", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String item_buy(@RequestBody List<CartJsonModel> list) {
		Gson gson=new Gson();
		String json="";
		HashMap<String, String> map=new HashMap<String, String>();
		
		List<Sold_item> itemList=new ArrayList<Sold_item>();
		List<Cart> cartList=new ArrayList<Cart>();
		List<DetailBoardItemView> de_itemList=new ArrayList<DetailBoardItemView>();
		
		for (CartJsonModel model : list) {
			Sold_item item=new Sold_item(0, model.getBoard_id(), model.getCategory(),
					model.getMember_id(), model.getName(), model.getAddress_post(),
					model.getAddress_basic(), model.getAddress_detail(), model.getTitle(),
					model.getNumber(), model.getPrice(), null);
			
			itemList.add(item);
			
			Cart cart=new Cart();
			cart.setCart_id(model.getCart_id());
			
			cartList.add(cart);
			
			DetailBoardItemView de_item=new DetailBoardItemView();
			de_item.setBoard_id(model.getBoard_id());
			de_item.setNumber(model.getNumber());
			
			de_itemList.add(de_item);
		}
		
		try {
			siaService.service(itemList, cartList, de_itemList);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "false");
			json=gson.toJson(map);
			return json;
		}
		
		map.put("result", "true");
		json=gson.toJson(map);
		return json;
	}
	
	@PostMapping(value = "/android/cart_delete", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String cart_delete(@RequestBody List<CartJsonModel> list) {
		
		Gson gson=new Gson();
		String json="";
		HashMap<String, String> map=new HashMap<String, String>();	
		
		List<Cart> cartList=new ArrayList<Cart>();
		
		for (CartJsonModel model : list) {

			Cart cart=new Cart();
			cart.setCart_id(model.getCart_id());
			
			cartList.add(cart);
		}
		
		try {
			cdService.service(cartList);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "false");
			json=gson.toJson(map);
			return json;
		}
		
		map.put("result", "true");
		json=gson.toJson(map);
		return json;
	}
	
	@GetMapping(value = "android/simpleReview_selectAll", produces = "application/text; charset=utf8")
	@ResponseBody
	public String simpleReview_selectAll() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				
		List<SimpleBoardReviewView> itemList=(List<SimpleBoardReviewView>) arlSerview.service();
		ArrayList<SimpleBoardReviewView> convertList=new ArrayList<SimpleBoardReviewView>();
		
		for (SimpleBoardReviewView item : itemList) {
			convertList.add(item);
		}
		
		String json="";
		
		json=gson.toJson(convertList);
		
		System.out.println(json);
		
		return json;
	}
	
	@GetMapping(value = "android/detailReview/{board_id}", produces = "application/text; charset=utf8")
	@ResponseBody
	public String detailReview(@PathVariable("board_id") Integer board_id,
			HttpSession session){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		String json="";
		
		Member login_member=(Member) session.getAttribute("login_member");
		Boolean login_result=false;
		
		if(login_member != null) {
			login_result=true;
			map.put("login_result", login_result.toString());
		}
		 
		DetailBoardReviewView model=new DetailBoardReviewView();
		model.setBoard_id(board_id);
		
		if( (Integer)rvcuService.service(model)==0 ) {
			map.put("view_cnt_update_fail", "조회수 업데이트 실패");
			json=gson.toJson(map);
			return json;
		}
		
		DetailBoardReviewView item=(DetailBoardReviewView) dbrvsoService.service(model);
		Comment comment=new Comment();
		comment.setBoard_id(board_id);
		comment.setTopic(1);
		
		List<Comment> commentList=(List<Comment>) csService.service(comment);
		ArrayList<Comment> convertList=new ArrayList<Comment>();
		
		if( commentList!=null ) {
			for (Comment c : commentList) {
				convertList.add(c);
			}
			
			map.put("comment_list", convertList);
		}
		
		map.put("detail_review", item);
		map.put("login_result", login_result.toString());
		json=gson.toJson(map);
		
		System.out.println(json);
		
		return json;
	}
	
}
