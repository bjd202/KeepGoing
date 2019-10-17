<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Q&A</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>
</head>
<body>

	<jsp:include page="menu.jsp" flush="false"></jsp:include>

	<jsp:include page="modalLogin.jsp" flush="false"></jsp:include>
	
	<div class="site-blocks-cover inner-page-cover overlay"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/top.jpg);"
		data-aos="fade" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row align-items-center justify-content-center text-center">

				<div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
					<h1
						class="text-white font-weight-light text-uppercase font-weight-bold">Q&A</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="site-section block-13">
		<div align="center"  style="margin-left: 15%; margin-right: 15%;">
		<c:if test="${ login_member.auth >= 0 }">
			<p align="right"><a href="<%=request.getContextPath()%>/qna/write" class="btn btn-info btn-xs" type="button">글 등록</a></p>
		</c:if>	
		<table class="table">
			<tr style="text-align: center;">
				<th>카테고리</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
			</tr>
			
			<!-- 상단노출 3개 -->
			
			<c:forEach items="${ board_noticeheadList }" var="head">
				<tr class="table-active" style="text-align: center;">
					<th>중요공지</th>
					<td><b><a href="<%= request.getContextPath() %>/notice/${ head.board_id }">${ head.title }</a></b></td>
					<td>관리자</td>
					<td>${ head.write_date }</td>
				</tr>
			</c:forEach>
			
			<!-- 상단노출 3개 -->
			
			<c:forEach items="${ board_qna }" var="qna">
				<tr style="text-align: center;">
					<c:if test="${ qna.category == 1 }">
						<td>회원관련</td>
					</c:if>
					<c:if test="${ qna.category == 2 }">
						<td>리뷰</td>
					</c:if>
					<c:if test="${ qna.category == 3 }">
						<td>게시판</td>
					</c:if>
					<c:if test="${ qna.category == 4 }">
						<td>상품</td>
					</c:if>
					<c:if test="${ qna.category == 5 }">
						<td>기타</td>
					</c:if>
					<td style="text-align: left;"><a href="<%= request.getContextPath() %>/qna/qnaread/${ qna.board_id }">${ qna.title }</a></td>
					<td>${ qna.nickname }</td>
					<td>${ qna.write_date }</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
	
<!-- 페이지  -->
	<div class="container text-center pb-5">
		<div class="row">
			<div class="col-12">

				<p class="custom-pagination">
					<c:if test="${pageMaker.prev == true }">
						<a
							href="<%=request.getContextPath()%>/qna/${pageMaker.startPage-1}">&laquo;</a>
					</c:if>
					<c:forEach var="pageNo" begin="${ pageMaker.startPage }"
						end="${ pageMaker.endPage }">
						<c:if test="${curPageNo == pageNo}" var="r">
							<span>${curPageNo}</span>
						</c:if>
						<c:if test="${ not r }">
							<a href="<%=request.getContextPath()%>/qna/${pageNo}">${ pageNo }</a>
						</c:if>
					</c:forEach>
					<c:if test="${pageMaker.next == true and pageMaker.endPage > 0}">
						<a
							href="<%=request.getContextPath()%>/qna/${pageMaker.endPage+1}">&raquo;</a>
					</c:if>
				</p>
			</div>
		</div>
	</div>
<!-- /페이지  -->
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	
   
 <jsp:include page="site_footer.jsp"></jsp:include>

</body>
</html>