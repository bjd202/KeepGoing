<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>FAQ 등록</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>
</head>
<body>

	<jsp:include page="menu.jsp" flush="false"></jsp:include>

	<jsp:include page="modalLogin.jsp" flush="false"></jsp:include>
	
	<div class="site-blocks-cover inner-page-cover overlay"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/hero_bg_1.jpg);"
		data-aos="fade" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row align-items-center justify-content-center text-center">

				<div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
					<h1
						class="text-white font-weight-light text-uppercase font-weight-bold">자주 묻는 질문(FAQ)</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="container">
	<div class="site-section block-13">

    	<form id="faqRev"  method="post" action="<%=request.getContextPath() %>/faq/rev">
			<div class="form-group">
				<input type="hidden" class="form-control" name="board_id" value="${ faq.board_id }">
				<label>분류</label>
				<select name="head">
					<option value="1">분류 1</option>
					<option value="2">분류 2</option>
					<option value="3">분류 3</option>
				</select>
				<select name="category">
					<option value="1">회원가입</option>
					<option value="2">상품</option>
					<option value="3">기타</option>
				</select>
			</div>
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="title" placeholder="Title" value="${ faq.title }" required>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea rows="20" cols="" class="form-control" name="content" placeholder="Content" value="${ faq.content }" required></textarea>
			</div>

			<button type="submit" class="btn btn-info" id="add_faq_update">수정</button>
		</form>

    </div>
    </div>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>

</body>
</html>