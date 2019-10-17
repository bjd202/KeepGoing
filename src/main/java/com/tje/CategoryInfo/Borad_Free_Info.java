package com.tje.CategoryInfo;

public class Borad_Free_Info {
	
	public static String getCategoryName(int category_num) {
		
		if( category_num == 1) {
			return "전체 게시글";
		}else if (category_num == 2){
			return "우리동네 운동부";
		}else if (category_num == 3){
			return "건강한 식생활";
		}else if (category_num == 4){
			return "나만의 운동법";
		}else if (category_num == 5){
			return "초보자를 위한 운동 추천";
		}else if (category_num == 6){
			return "컴플랙스 극복";
		}
		
		else {
			return "다시확인해주세요.(게시글 없음)";
		}
	}

}
