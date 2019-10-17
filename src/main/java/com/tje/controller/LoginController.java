package com.tje.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.google.connect.GoogleOAuth2Template;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.tje.api.AuthInfo;
import com.tje.api.NaverLoginBO;
import com.tje.api.Kakao_restapi;
import com.tje.model.Member;
import com.tje.service.member.MemberIDCheckService;

@Controller
public class LoginController {
	// services
	@Autowired
	private MemberIDCheckService mIDcService;
	
	// google 로그인
	@Inject
    private AuthInfo authInfo;
    @Autowired
    private GoogleOAuth2Template googleOAuth2Template;
    @Autowired
    private OAuth2Parameters googleOAuth2Parameters;
    @Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
    // naver 로그인
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	@PostMapping(value = "/login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String login(Member member, HttpSession session) {
		
		Member result=(Member) mIDcService.service(member);
		if(result==null)		
			return String.format("존재하지 않는 ID 입니다.");
			
		if(result.getPassword().equals(member.getPassword())) {
			session.setAttribute("login_member", result);
			return String.format("%s(%s) 님 환영합니다.", result.getNickname(), result.getName());
		}
		
		return "정보가 일치하지 않습니다.";
	}

	@RequestMapping(value = "/naver_login")
	public String naver_login(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		System.out.println("네이버:" + naverAuthUrl);
		// 네이버
		//model.addAttribute("url", naverAuthUrl);

		return "redirect:"+naverAuthUrl;
	}

	@RequestMapping(value = "/naver_callback")
	public String naver_callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws Exception {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); // String형식의 json데이터
		/**
		 * apiResult json 구조 { "resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"shinn0608@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// response의 nickname값 파싱
		String id = (String) response_obj.get("id");
		String nickname = (String) response_obj.get("nickname");
		String email = (String) response_obj.get("email");

		System.out.println(id);
		System.out.println(nickname);
		System.out.println(email);
		// 4.파싱 닉네임 세션으로 저장
		
		// naver로그인 한 member
		Member member=new Member();
		member.setMember_id(id);
		member.setPassword(String.valueOf(new Random().nextInt()));
		member.setMember_type(1);
		
		// member가 db에 있으면 로그인
		Member result=(Member) mIDcService.service(member);
		if(result!=null) {
			session.setAttribute("login_member", result); // 세션 생성
			model.addAttribute("result", apiResult);
			return "home";
		}
		
		// member가 db에 없으면 회원가입으로
		session.setAttribute("login_sns_member", member);
		return "regist";
	}
	
	@RequestMapping(value = "/google_login", method = { RequestMethod.GET, RequestMethod.POST })
    public String google_login(HttpServletResponse response, Model model) {
 
        //URL을 생성한다.
        String url = googleOAuth2Template.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
        System.out.println("/googleLogin, url : " + url);
        model.addAttribute("google_url", url);
 
        return "redirect:"+url;
    }
	
	@RequestMapping(value = "/google_callback")
    public String doSessionAssignActionPage(HttpServletRequest request, HttpSession session) throws Exception {
 
        String code = request.getParameter("code");
        System.out.println(code);
        
        //RestTemplate을 사용하여 Access Token 및 profile을 요청한다.
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("code", code);
        parameters.add("client_id", authInfo.getClientId());
        parameters.add("client_secret", authInfo.getClientSecret());
        parameters.add("redirect_uri", googleOAuth2Parameters.getRedirectUri());
        parameters.add("grant_type", "authorization_code");
 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
        ResponseEntity<Map> responseEntity = restTemplate.exchange("https://www.googleapis.com/oauth2/v4/token", HttpMethod.POST, requestEntity, Map.class);
        Map<String, Object> responseMap = responseEntity.getBody();
 
        // id_token 라는 키에 사용자가 정보가 존재한다.
        // 받아온 결과는 JWT (Json Web Token) 형식으로 받아온다. 콤마 단위로 끊어서 첫 번째는 현 토큰에 대한 메타 정보, 두 번째는 우리가 필요한 내용이 존재한다.
        // 세번째 부분에는 위변조를 방지하기 위한 특정 알고리즘으로 암호화되어 사이닝에 사용한다.
        //Base 64로 인코딩 되어 있으므로 디코딩한다.
 
        String[] tokens = ((String)responseMap.get("id_token")).split("\\.");
        Base64 base64 = new Base64(true);
        String body = new String(base64.decode(tokens[1]));
        
        System.out.println(tokens.length);
        System.out.println(new String(Base64.decodeBase64(tokens[0]), "utf-8"));
        System.out.println(new String(Base64.decodeBase64(tokens[1]), "utf-8"));
 
        //Jackson을 사용한 JSON을 자바 Map 형식으로 변환
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(body, Map.class);
        System.out.println(map.get("email"));
        System.out.println(map.get("name"));
        System.out.println(map.get("sub"));
        
        String sub=map.get("sub");
        String name=map.get("name");
        String email=map.get("email");
        
        //session.setAttribute("email", map.get("email"));
        //session.setAttribute("name", map.get("name"));
        //session.setAttribute("sub", map.get("sub"));
        
        Member member=new Member();
		member.setMember_id(sub);
		member.setPassword(String.valueOf(new Random().nextInt()));
		member.setMember_type(2);
		
		// member가 db에 있으면 로그인
		Member result=(Member) mIDcService.service(member);
		if(result!=null) {
			session.setAttribute("login_member", result); // 세션 생성
			return "home";
		}
		
		// member가 db에 없으면 회원가입으로
		session.setAttribute("login_sns_member", member);
		return "regist";
 
    }
	
	@RequestMapping(value = "/loginResult", produces = "application/json")
	public String loginResult(@RequestParam("code") String code, Model model, HttpSession session){
		System.out.println(code);
		
		Kakao_restapi kr = new Kakao_restapi();
		//결과 값을 node에 담는다.
		JsonNode node = kr.getAccessToken(code);
		// 결과 출력
		System.out.println(node);
		// 노드 안에 있는 access_token 꺼내 문자로 반환
		String token = node.get("access_token").toString();
		// 세션에 담는다.
		session.setAttribute("token", token);
		
		HashMap<String, Object> userInfo = kr.getUserInfo(token);
		
		System.out.println(userInfo.get("nickname"));
		System.out.println(userInfo.get("email"));
		System.out.println(userInfo.get("id"));
		
		String id=(String) userInfo.get("id");
		
		//session.setAttribute("name", userInfo.get("nickname"));
		//session.setAttribute("email", userInfo.get("email"));
		//session.setAttribute("id", userInfo.get("id"));
		
		// kakao로그인 한 member
		Member member=new Member();
		member.setMember_id(id);
		member.setPassword(String.valueOf(new Random().nextInt()));
		member.setMember_type(3);
				
		// member가 db에 있으면 로그인
		Member result=(Member) mIDcService.service(member);
		if(result!=null) {
			session.setAttribute("login_member", result); // 세션 생성
			model.addAttribute("result", apiResult);
			return "home";
		}
				
		// member가 db에 없으면 회원가입으로
		session.setAttribute("login_sns_member", member);
		return "regist";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) throws IOException {
		System.out.println("여기는 logout");
		session.invalidate();
		return "redirect:home";
	}
	
	@RequestMapping(value = "/needLogin")
	public ModelAndView needLogin() {
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("needLogin");
		mav.addObject("msg", "로그인이 필요한 기능입니다.");
		
		return mav;
	}
}
