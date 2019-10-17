package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;



// 자유게시판 리스트 카운트 서비스
@Service
public class SimpleHealthy_Diet_ListCountCriteriaService3 {
	@Autowired
	private SimpleBoardFreeViewDAO simpleBoardFreeViewDAO;
	
	public Object service() {
		Object result=null;
		// result는 심플 보드DAO. listconuntcriteria 을 sql에서 조건식으로 검색해 값을 가져와 result로 값을 변환
		result=simpleBoardFreeViewDAO.Healthy_Diet_ListCountCriteria();
		return result;
	}
}
