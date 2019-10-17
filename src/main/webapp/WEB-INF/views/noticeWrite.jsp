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

    	<form id="add_notice"  method="post" action="<%=request.getContextPath() %>/add_notice">
			<div class="form-group">
				<select name="head">
					<option value="1">일반 공지</option>
					<option value="2">중요 공지</option>
				</select>
			</div>
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="title" placeholder="Title" required>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea rows="20" cols="" class="form-control" name="content" placeholder="Content" required></textarea>
			</div>

			<button type="submit" class="btn btn-info" id="add_notice_insert">등록</button>
		</form>

    </div>
    </div>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	<jsp:include page="site_footer.jsp"></jsp:include>

</body>
</html>