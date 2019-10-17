package com.tje.board_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;
import com.tje.page.*;
import java.util.*;

@Service
public class SimpleBoardReviewViewService {
	@Autowired
	private SimpleBoardReviewViewDAO simpleBoardReviewViewDAO;
	
	public Object service(Criteria criteria, int category_Num) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if(category_Num == 1) {
			// 전체 카테고리 게시글과 카운팅
			result.put("list", simpleBoardReviewViewDAO.listCriteria(criteria.getPageStart(), criteria.getPerPageNum()));
			result.put("count", simpleBoardReviewViewDAO.listCountCriteria());
		} else if(category_Num == 2) {
			// 상품 카테고리 게시글과 카운팅
			result.put("list", simpleBoardReviewViewDAO.itemlistCriteria(criteria.getPageStart(), criteria.getPerPageNum()));
			result.put("count", simpleBoardReviewViewDAO.itemlistCountCriteria());
		} else if(category_Num == 3) {
			// 피트니스 카테고리 게시글과 카운팅
			result.put("list", simpleBoardReviewViewDAO.fitnesslistCriteria(criteria.getPageStart(), criteria.getPerPageNum()));
			result.put("count", simpleBoardReviewViewDAO.fitnesslistCountCriteria());
		} else if(category_Num == 4) {
			// 장소 카테고리 게시글과 카운팅
			result.put("list", simpleBoardReviewViewDAO.placelistCriteria(criteria.getPageStart(), criteria.getPerPageNum()));
			result.put("count", simpleBoardReviewViewDAO.placelistCountCriteria());
		} else if(category_Num == 5) {
			// 다이어트 카테고리 게시글과 카운팅
			result.put("list", simpleBoardReviewViewDAO.dietlistCriteria(criteria.getPageStart(), criteria.getPerPageNum()));
			result.put("count", simpleBoardReviewViewDAO.dietlistCountCriteria());
		} else if(category_Num == 6) {
			// 웨이트 트레이닝 카테고리 게시글과 카운팅
			result.put("list", simpleBoardReviewViewDAO.weightlistCriteria(criteria.getPageStart(), criteria.getPerPageNum()));
			result.put("count", simpleBoardReviewViewDAO.weightlistCountCriteria());
		} else if(category_Num == 7) {
			// 레시피 카테고리 게시글과 카운팅
			result.put("list", simpleBoardReviewViewDAO.recipelistCriteria(criteria.getPageStart(), criteria.getPerPageNum()));
			result.put("count", simpleBoardReviewViewDAO.recipelistCountCriteria());
		}
		return result;
	}
}
