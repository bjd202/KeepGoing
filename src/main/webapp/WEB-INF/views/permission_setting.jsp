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
					<table class="table table-bordered col-xs-12" style="margin: auto; text-align: center; vertical-align: middle;">
						<thead>
							<tr>
								<th><input type="checkbox" id="all_check"></th>
								<th>번호</th>
								<th>ID</th>
								<th>이름</th>
								<th>닉네임</th>
								<th>현재 권한</th>
								<th>요청 권한</th>
								<th>로그인 타입</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${ member_auth }" var="result" varStatus="status">
									<tr>
										<td style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${ result.nickname }"></td>
										<td style="vertical-align: middle;">${ status.count }</td>
										<td style="vertical-align: middle;">${ result.member_id }</td>
										<td style="vertical-align: middle;">${ result.name }</td>
										<td style="vertical-align: middle;">${ result.nickname }</td>
										<td style="vertical-align: middle;">${ result.getCur_authString() }</td>
										<td style="display: none;"><input type="hidden" name="req_auth" value="${ result.req_auth }"></td>
										<td style="vertical-align: middle;">${ result.getReq_authString() }</td>
										<td style="vertical-align: middle;">${ result.getMember_typeString() }</td>
									</tr>
								</c:forEach>
							</tr>
						</tbody>						
					</table>
					
					<button class="btn btn-primary" type="button" id="checked_member_auth_update">선택 회원 권한 변경</button>
					<button class="btn btn-primary" type="button" id="checked_member_auth_delete">선택 회원 삭제</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	<!-- 전체선택 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#all_check").on("click", function() {
				
				if( $("#all_check").prop("checked") ){
					$("input[name=checkbox]").prop("checked", true);
				}
				else{
					$("input[name=checkbox]").prop("checked", false);
				}
			})
		});
	</script>
	
	<!-- 선택 회원 권한 변경 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#checked_member_auth_update").on("click", function() {
				var checkbox=$("input[name=checkbox]:checked");
				
				if(checkbox.length == 0){
					alert("선택된 회원이 없습니다.");
					return;
				}
				
				var jsonArray=new Array();
				var json;
				
				checkbox.each(function(index){
					var tr=checkbox.parent().parent().eq(index);
					var td=tr.children();
					
					var nickname=td.eq(0).children().val();
					var req_auth=td.eq(6).children().val();
					
					json={"nickname":nickname, "req_auth":req_auth};
					
					jsonArray.push(json);
				})
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/mypage/admin/members_auth_update",
					dataType : "text",
					contentType : "application/json; charset=utf-8",
					data : JSON.stringify(jsonArray),
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
	
	<!-- 선택 회원 삭제 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#checked_member_auth_delete").on("click", function() {
				var checkbox=$("input[name=checkbox]:checked");
				
				if(checkbox.length == 0){
					alert("선택된 회원이 없습니다.");
					return;
				}
				
				var jsonArray=new Array();
				var json;
				
				checkbox.each(function(index){
					var tr=checkbox.parent().parent().eq(index);
					var td=tr.children();
					
					var nickname=td.eq(0).children().val();
					
					json={"nickname":nickname};
					
					jsonArray.push(json);
				})
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/mypage/admin/members_auth_delete",
					dataType : "text",
					contentType : "application/json; charset=utf-8",
					data : JSON.stringify(jsonArray),
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