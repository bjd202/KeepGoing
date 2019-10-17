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
					<form action="<%= request.getContextPath() %>/mypage/buy_list_search" method="get">
						<table style="width: 100%;">
							<tr>
								<td>조회 기간</td>
								<td><input type="date" name="from"> ~ <input type="date" name="to"></td>
							</tr>
						</table>
						
						<input type="submit" value="조회하기">
					</form>
					
					<table class="table table-bordered" style="width: 100%; margin: auto; text-align: center;">
						<thead>
							<tr>
								<th>상품 번호</th>
								<th>카테고리</th>
								<th>제목</th>
								<th>수량</th>
								<th>가격</th>
								<th>구매 날짜</th>
							</tr>
						</thead>
						
						<c:if test="${ searched_result != null }">
							<tbody>
								<c:forEach items="${searched_result}" var="result">
									<tr>
										<td>${ result.board_id }</td>
										<td>${ result.getCategoryString() }</td>
										<td>${ result.title }</td>
										<td>${ result.number }</td>
										<td>${ result.price*result.number }원</td>
										<td>${ result.sold_date }</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>
					</table>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btn_search").on("click", function() {
				var from=$("input[name=from]").val();
				var to=$("input[name=to]").val();
				
				var json={
						"from":from, "to":to
				}
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/mypage/buy_list",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					data : JSON.stringify(json),
					success : function(data) {
						
						if(data=="success"){
							alert(data);
							
							location.reload();
							return;
						}
						
						alert(data);
						
					},
					error : function() {
						alert("error");
					}
				});
			})
		})
	</script>
</body>
</html>