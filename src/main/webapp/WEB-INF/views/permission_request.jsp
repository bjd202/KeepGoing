<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>마이페이지</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">마이페이지</h1>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="site-section block-13">
		<div class="container">
			<div class="row">
			
				<jsp:include page="mypageMenu.jsp" flush="false"></jsp:include>
				
				<div class="col-md-10">
					현재 권한 : <strong>${ login_member.getAuthString() }</strong> <hr>
					<p><strong>일반 회원</strong> : 채팅, 자유 게시판 이용, 게시물에 대한 좋아요와 댓글, 상품 구매 등을 사용 가능</p>
					<p><strong>리뷰어</strong> : 일반 회원의 기능과 리뷰 게시판에 글 작성 가능</p>
					<p><strong>판매자</strong> : 일반 회원의 기능과 상품 게시판에 글 작성 가능</p>
					<p><strong>매니저</strong> : 각 게시판의 게시물 수정, 삭제 가능</p>
					
					<select id="auth">
						<option value="1">리뷰어</option>
						<option value="2">판매자</option>
						<option value="3">매니저</option>
					</select>
					
					<button type="button" id="permission_request">권한 신청</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#permission_request").on("click", function() {
				var auth=$("#auth option:selected").val();
				
				console.log(auth);
				
				$.ajax({
		            url: "<%=request.getContextPath()%>/mypage/permission_request",
		            type: "POST",
		            data: "auth="+auth,
		            success: function(data){
		                alert(data);
					},
					error : function() {
						alert("err");
					}
				});
			})
		})
	</script>
</body>
</html>