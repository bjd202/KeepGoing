<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Index</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">고객센터</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div align="left">
	<ul>
		<li>qna</li>
		<li>배송 기간은?</li>
		<li>배송 기간은?</li>
		<li>배송 기간은?</li>
		<li>배송 기간은?</li>
		<li>배송 기간은?</li>
		<li>배송 기간은?</li>
	</ul>
	</div>
	
	<div align="center">
	
	<table>
		<caption>공지사항</caption>
		<tr style="text-align: center;">
			<th>글 번호</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		
		<!-- 상단노출 3개 -->
		
		<!-- 상단노출 3개 -->
		<tr style="text-align: center;">
			<th>0</th>
			<th>첫번째 공지사항입니다.</th>
			<th>2019-07-24</th>
		</tr>
		<tr style="text-align: center;">
			<th>0</th>
			<th>첫번째 공지사항입니다.</th>
			<th>2019-07-24</th>
		</tr>
		<tr style="text-align: center;">
			<th>0</th>
			<th>첫번째 공지사항입니다.</th>
			<th>2019-07-24</th>
		</tr>
		<tr style="text-align: center;">
			<th>0</th>
			<th>첫번째 공지사항입니다.</th>
			<th>2019-07-24</th>
		</tr>
		<tr style="text-align: center;">
			<th>0</th>
			<th>첫번째 공지사항입니다.</th>
			<th>2019-07-24</th>
		</tr>
	</table>
	</div>
	
	

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
</body>
</html>