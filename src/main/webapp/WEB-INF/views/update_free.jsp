<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>게시글 수정</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">게시글 수정</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="container">

	<!-- enctype="multipart/form-data" 파일을 업로드 할때 필수입력사항 -->
    	<form id="update_free" enctype="multipart/form-data" method="post" action="<%=request.getContextPath() %>/update_free/${searchedFree.board_id}">

    		
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="title" value="${ searchedFree.title }" maxlength="30" required >
						
			</div>
	
			<div class="form-group">
				<label for="image">이미지 업로드</label>
				<input type="file" name="image">
				<p>*미 입력시 이전의 이미지 사용</p>
			</div>
					
			<div class="form-group">
				<label for="content">내용</label>
				<textarea rows="20" cols="" class="form-control" name="content" placeholder="Content" required >${ searchedFree.content }</textarea>
				
			</div>
			<div class="form-group">
				<label for="category">카테고리</label>
				<select name="category">
					<option value="1" ${ searchedFree.category==1 ? 'selected' : '' }>전체</option>
					<option value="2" ${ searchedFree.category==2 ? 'selected' : '' }>우리동네 운동부</option>
					<option value="3" ${ searchedFree.category==3 ? 'selected' : '' }>건강한 식생활	</option>
					<option value="4" ${ searchedFree.category==4 ? 'selected' : '' }>나만의 운동법</option>
					<option value="5" ${ searchedFree.category==5 ? 'selected' : '' }>초보자를 위한 운동</option>
					<option value="6" ${ searchedFree.category==6 ? 'selected' : '' }>컴플랙스 극복</option>
				</select>
			</div>
			
			
			<c:if	test="${ login_member.member_id eq 'admin' or login_member.member_id eq searchedFree.member_id }">

			<button type="submit" class="btn btn-info" >수정</button>

			</c:if>
		</form>

    </div>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>

</body>
</html>