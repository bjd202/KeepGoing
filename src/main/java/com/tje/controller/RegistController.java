package com.tje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.model.Member;
import com.tje.service.member.MemberIDCheckService;
import com.tje.service.member.MemberInsertService;
import com.tje.service.member.MemberNickNameCheckService;

@Controller
public class RegistController {
	@Autowired
	private MemberIDCheckService mIDcService;
	@Autowired
	private MemberNickNameCheckService mnncService;
	@Autowired
	private MemberInsertService miService;
	
	@PostMapping(value = "/idCheck", produces = "application/text; charset=utf8")
	@ResponseBody
	public String idCheck(Member member) {
		
		Member idCheckMemeber=(Member) mIDcService.service(member);
		if(idCheckMemeber!=null) {
			// 중복된 상태
			return "idNo";
		} else if( idCheckMemeber==null) {
			// 가능 상태
			return "idOk";
		} 
		return "";
		// 닉네임 체크 서비스. 닉네임 사용 가능 => isNickOk = true 
		
		
//		if( result == true ) {
//			return "가입 가능";
//		} else if ( isIdOk == false ){
//			return "아이디 중복";
//		} else if ( isNickOk == false ) {
//			return "닉네임 중복";
//		}
	}

	@PostMapping(value = "/nickCheck", produces = "application/text; charset=utf8")
	@ResponseBody
	public String nickCheck(Member member) {
		
		Member nickCheckMemeber=(Member) mnncService.service(member);
		if(nickCheckMemeber!=null) {
			// 중복된 상태
			return "nickNo";
		} else if( nickCheckMemeber==null) {
			// 가능 상태
			return "nickOk";
		} 
		return "";
		
	}
	
		
	
	@PostMapping(value = "/regist", produces = "application/text; charset=utf8")
	@ResponseBody
	public String regist(Member member) {
		
		int r=(int) miService.service(member);
		
		if(r==1) {
			return "회원가입을 축하합니다.";
		}
		
		return "회원가입에 실패하였습니다.";
	}
}
