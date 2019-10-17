<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>게시글 등록</title>
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
					<h1 class="text-white font-weight-light text-uppercase font-weight-bold">게시글 쓰기</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="container">
	<div class="site-section block-13"> 
    	<form id="add_free"  method="post" action="<%=request.getContextPath() %>/add_free">
    		<input type="hidden" name="member_id" value="${ login_member.member_id }">
			<div class="form-group">
	
				<label for="title">제목 </label>
				<input type="text" class="form-control" name="title" placeholder="Title" maxlength="30" required>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea rows="20" cols="" class="form-control" name="content" placeholder="Content" maxlength="500" required></textarea>
			</div>
			<div class="form-group">
				<label for="category">카테고리</label>
				<select name="category">
					<option value="1">전체 게시판</option>
					<option value="2">우리동네 운동부</option>
					<option value="3">건강한 식생활</option>
					<option value="4">나만의 운동법</option>
					<option value="5">초보자를 위한 운동 추천</option>
					<option value="6">콤플랙스 극복</option>
									
				</select>
			</div>
				
  
    
			
			
			<div class="form-group">
				<label for="image">이미지 업로드</label>
				<input type="file" name="image">
			</div>
			<button type="submit" class="btn btn-info" id="add_free_insert">글 등록</button>
			
		</form>

    </div>
    </div>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>

</body>
</html>