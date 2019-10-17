<%@page import="org.springframework.web.context.support.ServletContextScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Review</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>
</head>
<body>

	<jsp:include page="menu.jsp" flush="false"></jsp:include>

	<jsp:include page="modalLogin.jsp" flush="false"></jsp:include>
	<jsp:include page="reviewSearchModal.jsp" flush="false"></jsp:include>

	<div class="site-blocks-cover inner-page-cover overlay"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/top.jpg);"
		data-aos="fade" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row align-items-center justify-content-center text-center">

				<div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
					<h1
						class="text-white font-weight-light text-uppercase font-weight-bold">리뷰</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<div class="site-section block-13"
		style="padding-bottom: 10; margin-left: 10%; margin-right: 10%; margin-top: 130;">

		<c:if test="${ login_member.auth == '1' }" var="r">
			<a href="<%=request.getContextPath()%>/review/write">리뷰 작성하기</a>
		</c:if>
		<a href="" data-toggle="modal" data-target="#reviewSearchModal">검색</a>
		<div align="center">
			<table class="table">
				<tr>
					<td><a href="<%= request.getContextPath() %>/review/1">전체</a></td>
					<td><a href="<%= request.getContextPath() %>/review/2">상품</a></td>
					<td><a href="<%= request.getContextPath() %>/review/3">피트니스</a></td>
					<td><a href="<%= request.getContextPath() %>/review/4">장소</a></td>
					<td><a href="<%= request.getContextPath() %>/review/5">다이어트</a></td>
					<td><a href="<%= request.getContextPath() %>/review/6">웨이트 트레이닝</a></td>
					<td><a href="<%= request.getContextPath() %>/review/7">레시피</a></td>
				</tr>
			</table>
		</div>
	</div>

	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<div class="site-section block-13" style="padding-top: 5;">
			<blockquote><h2>${ strCategory }의 리뷰</h2></blockquote>
			<div class="row">
				<c:if test="${ pageMaker.totalCount == 0 }" var="r">
					<h1>게시글이 존재하지 않습니다.</h1>
				</c:if>
				<c:if test="${ not r }">
				<c:forEach items="${ simpleBoardReviewViewList }" var="item">
					<a
						href="<%= request.getContextPath() %>/review/detail/${ item.category }_${item.board_id}"
						class="col-md-6 col-lg-4 mb-4 mb-lg-4 unit-1 text-center"
						style="padding: 0;"> <img
						src="<%=request.getContextPath()%>/resources/images/${item.image}"
						alt="${item.image}" class="img-fluid" width="700" height="799">
						<div class="unit-1-text">
							<h3 class="unit-1-heading">${item.title}</h3>
							<p class="px-5">${item.content}</p>
							<p class="px-5" style="text-align: right;">
								작성자 : ${item.nickname}<br> 작성일 : ${item.write_date}<br>
								조회수 : ${item.view_cnt}<br> 좋아요/싫어요 :
								${item.like_cnt}/${item.dislike_cnt}
							</p>
						</div>
					</a>
				</c:forEach>
				</c:if>
			</div>
		</div>
	</div>

	<div class="container text-center pb-5">
		<div class="row">
			<div class="col-12">

				<p class="custom-pagination">
					<c:if test="${pageMaker.prev == true }">
						<a
							href="<%=request.getContextPath()%>/review/${category_Num}/${pageMaker.startPage-1}">&laquo;</a>
					</c:if>
					<c:forEach var="pageNo" begin="${ pageMaker.startPage }"
						end="${ pageMaker.endPage }">
						<c:if test="${curPageNo == pageNo}" var="r">
							<span>${curPageNo}</span>
						</c:if>
						<c:if test="${ not r }">
							<a href="<%=request.getContextPath()%>/review/${category_Num}/${pageNo}">${ pageNo }</a>
						</c:if>
					</c:forEach>
					<c:if test="${pageMaker.next == true and pageMaker.endPage > 0}">
						<a
							href="<%=request.getContextPath()%>/review/${category_Num}/${pageMaker.endPage+1}">&raquo;</a>
					</c:if>
				</p>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptIncludeForReview.jsp" flush="false"></jsp:include>
	
    
 <jsp:include page="site_footer.jsp"></jsp:include>

</body>
</html>