package com.tje.CategoryInfo;

public class Board_Review_Category {
	public static String returnCategory(int categoryNum) {
		if( categoryNum == 1 ) {
			return "전체";
		} else if ( categoryNum == 2 ) {
			return "상품";
		} else if ( categoryNum == 3 ) {
			return "피트니스";
		} else if ( categoryNum == 4 ) {
			return "장소";
		} else if ( categoryNum == 5) {
			return "다이어트";
		} else if ( categoryNum == 6 ) {
			return "웨이트 트레이닝";
		} else if ( categoryNum == 7 ) {
			return "레시피";
		} else {
			return "카테고리 설정 에러";
		}
	}
}
