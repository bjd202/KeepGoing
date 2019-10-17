package com.tje.webapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.protocol.x.Notice;
import com.tje.homeservice.*;
import com.tje.model.*;
import com.tje.service.*;
import com.tje.service.board_item.AllItemListService;
import com.tje.service.board_item.ItemAddService;
import com.tje.service.board_item.ItemViewCntUpdateService;
import com.tje.service.board_item.ItemViewService;
import com.tje.service.board_item.SimpleBoardItemListCountCriteriaService;
import com.tje.service.board_item.SimpleBoardItemListCriteriaService;
import com.tje.service.common.CommentAddService;
import com.tje.service.common.CommentDeleteService;
import com.tje.service.common.CommentSelectService;
import com.tje.service.common.LikeAndDislikeService;
import com.tje.service.notice.Board_NoticeSelectHeadService;


@Controller
public class HomeController {
	@Autowired
	private AllItemListService ilService;
	
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
	private CommentAddService caService;
	@Autowired
	private CommentSelectService csSercie;


	@Autowired
	private CommentDeleteService cdService;

	@Autowired
	private LikeAndDislikeService ladService;
	@Autowired
	private HomeReviewService hrService;
	@Autowired
	private HomeItemService hiService;
	@Autowired
	private HomeFreeService hfService;
	@Autowired
	private Board_NoticeSelectHeadService b_nshService;
	


	@RequestMapping("/")
	public String home(HttpServletResponse res, HttpServletRequest req) {
		
		try {
			res.sendRedirect(req.getContextPath() + "/home");
		} catch (IOException e) {
		}
		return null;
	}
	
	
	@RequestMapping("/home")
	public String index(Model model) {
		////////////////////////////////////////////////////////////////////////////////////////////
		List<SimpleBoardReviewView> review_list = (List<SimpleBoardReviewView>)hrService.service();
		for(SimpleBoardReviewView b : review_list) {}
		model.addAttribute("reviewList", review_list);
		
		List<SimpleBoardItemView> item_list = (List<SimpleBoardItemView>)hiService.service();
		model.addAttribute("itemList", item_list);
		
		List<DetailBoardFree_View> free_list = (List<DetailBoardFree_View>)hfService.service();
		model.addAttribute("freeList", free_list);
		
		List<Board_Notice> board_noticeheadList = (List<Board_Notice>)b_nshService.service();
		model.addAttribute("board_noticeheadList", board_noticeheadList);
		

		return "home";
	}
	
	@RequestMapping("/cs")
	public String Cs() {
		return "cs";
	}	
	
	@GetMapping("/regist")
	public String registGet(HttpSession session) {
		session.removeAttribute("login_sns_member");
		return "regist";
	}
	@GetMapping("/regist/provision")
	public String registProvision() {
		return "/terms/provision";
	}
	
	@GetMapping("/regist/finance")
	public String registFinance() {
		return "/terms/finance";
	}
	
	@GetMapping("/regist/individual")
	public String registIndividual() {
		return "/terms/individual";
	}
	
	@GetMapping("/regist/thirdparty")
	public String registThirdparty() {
		return "/terms/thirdparty";
	}
	
	@GetMapping("/regist/location")
	public String registLocation() {
		return "/terms/location";
	}
	

	@GetMapping("/regist/individual_option")
	public String registIndividual_option() {
		return "/terms/individual_option";
	}
	

	@GetMapping("/site_footer/service")
	public String service() {
		return "/terms/service";
	}
	

	@GetMapping("/site_footer/about_us")
	public String about_us() {
		return "/terms/about_us";
	}
	

	@GetMapping("/site_footer/company_info")
	public String company_info() {
		return "/terms/company_info";
	}
	

	@GetMapping("/site_footer/privacy_policy")
	public String privacy_policy() {
		return "/terms/privacy_policy";
	}
	
	@GetMapping("/homePopup")
	public String homePopup() {
		return "homePopup";
	}
}
