<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>공지 등록</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">공지사항</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="container">
	<div class="site-section block-13">
		<div style="border-bottom: 1px solid;">
		<h4>${ noticeRead.title }</h4>
		</div>
		<br>
		<div style="border-bottom: 1px solid;">
		<textarea rows="20" class="form-control" name="content" placeholder="Content" style="border: none; background-color: white;" readonly>${ noticeRead.content }</textarea>
		</div>
		<br>
		<p align="right">
			<c:if test="${ login_member.auth >= 3 }">
			<a href="<%=request.getContextPath()%>/notice" class="btn btn-info btn-xs" type="button">수정</a>
			<a href="<%=request.getContextPath()%>/notice" class="btn btn-info btn-xs" type="button">삭제</a>
			</c:if>
			<a href="<%=request.getContextPath()%>/notice" class="btn btn-info btn-xs" type="button">목록</a>
		</p>
		
	<!-- 윗글 아랫글 -->
    <div>
		<table class="table">
			<tr>
				<td style="width: 200px;">윗글</td>
				<c:if test="${  noticeReadUp == null }" var="nr">
					<td>윗글이 없습니다.</td>
				</c:if>
				<c:if test="${  nr != null }">
					<td><a href="<%=request.getContextPath()%>/notice/read/${ noticeReadUp.board_id }">${ noticeReadUp.title }</a></td>
				</c:if>
			</tr>
			<tr>
				<td style="width: 200px;">아랫글</td>
				<c:if test="${  noticeReadDown == null }" var="nr">
					<td>아랫글이 없습니다.</td>
				</c:if>
				<c:if test="${  nr != null }">
					<td><a href="<%=request.getContextPath()%>/notice/read/${ noticeReadDown.board_id }">${ noticeReadDown.title }</a></td>
				</c:if>
			</tr>
		</table>    
    </div>
    <!-- /윗글 아랫글 -->
    
    </div>
    </div>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	
     <footer>
 <jsp:include page="site_footer.jsp"></jsp:include>
</footer>

</body>
</html>