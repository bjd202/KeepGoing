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
					<ul id="myTab" class="nav nav-tabs" role="tablist">
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs1" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/admin/board_management?tab=1'"><a href="#">리뷰</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs2" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/admin/board_management?tab=2'"><a href="#">자유 게시판</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs3" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/admin/board_management?tab=3'"><a href="#">상품</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs4" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/admin/board_management?tab=4'"><a href="#">공지사항</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs5" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/admin/board_management?tab=5'"><a href="#">FAQ</a></li>
						<li style="margin: 15px; margin-top: 0px;" role="presentation" class="tabcontent" id="tabs6" data-toggle="tab" onclick="location.href='<%=request.getContextPath()%>/mypage/admin/board_management?tab=6'"><a href="#">Q&A</a></li>
					</ul>
					
					
						<table class="table table-bordered col-xs-12" style="margin: auto; text-align: center; vertical-align: middle;">
						<thead>
							<tr>
								<c:if test="${ param.tab==1 or param.tab==null or param.tab==2 or param.tab==3 }">
									<th><input type="checkbox" id="all_check"></th>
									<th>번호</th>
									<th>제목</th>
									<th>카테고리</th>
									<th>작성자</th>
									<th>조회수</th>
									<th>댓글수</th>
									<th>좋아요|싫어요</th>
									<th>작성일</th>
								</c:if>
								
								<c:if test="${ param.tab==4 }">
									<th><input type="checkbox" id="all_check"></th>
									<th>번호</th>
									<th>제목</th>
									<th>작성일</th>
								</c:if>
								
								<c:if test="${ param.tab==5 or param.tab==6 }">
									<th><input type="checkbox" id="all_check"></th>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:if test="${ param.tab==1 or param.tab==null }">
									<c:forEach items="${ review_list }" var="result" varStatus="status">
										<tr>
											<td style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${ result.board_id }"></td>
											<td style="display: none;"><input type="hidden" name="topic" value="1"></td>
											<td style="vertical-align: middle;">${ result.board_id }</td>
											<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/review/detail/${ result.category }_${ result.board_id }">${ result.title }</a></td>
											<td style="vertical-align: middle;">${ result.category }</td>
											<td style="vertical-align: middle;">${ result.member_id }</td>
											<td style="vertical-align: middle;">${ result.view_cnt }</td>
											<td style="vertical-align: middle;">${ result.comment_cnt }</td>
											<td style="vertical-align: middle;">${ result.like_cnt }|${ result.dislike_cnt }</td>
											<td style="vertical-align: middle;">${ result.write_date }</td>
										</tr>
									</c:forEach>
								</c:if>
								
								<c:if test="${ param.tab==2 }">
									<c:forEach items="${ free_list }" var="result" varStatus="status">
										<tr>
											<td style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${ result.board_id }"></td>
											<td style="display: none;"><input type="hidden" name="topic" value="2"></td>
											<td style="vertical-align: middle;">${ result.board_id }</td>
											<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/free_view/${ result.board_id }">${ result.title }</a></td>
											<td style="vertical-align: middle;">${ result.getCategoryString() }</td>
											<td style="vertical-align: middle;">${ result.member_id }</td>
											<td style="vertical-align: middle;">${ result.view_cnt }</td>
											<td style="vertical-align: middle;">${ result.comment_cnt }</td>
											<td style="vertical-align: middle;">${ result.like_cnt }|${ result.dislike_cnt }</td>
											<td style="vertical-align: middle;">${ result.write_date }</td>
										</tr>
									</c:forEach>
								</c:if>
							
								<c:if test="${ param.tab==3 }">
									<c:forEach items="${ item_list }" var="result" varStatus="status">
										<tr>
											<td style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${ result.board_id }"></td>
											<td style="display: none;"><input type="hidden" name="topic" value="${ result.topic }"></td>
											<td style="vertical-align: middle;">${ result.board_id }</td>
											<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/item_view/${ result.board_id }">${ result.title }</a></td>
											<td style="vertical-align: middle;">${ result.getCategoryString() }</td>
											<td style="vertical-align: middle;">${ result.member_id }</td>
											<td style="vertical-align: middle;">${ result.view_cnt }</td>
											<td style="vertical-align: middle;">${ result.comment_cnt }</td>
											<td style="vertical-align: middle;">${ result.like_cnt }|${ result.dislike_cnt }</td>
											<td style="vertical-align: middle;">${ result.write_date }</td>
										</tr>
									</c:forEach>
								</c:if>
								
								<c:if test="${ param.tab==4 }">
									<c:forEach items="${ notice_list }" var="result" varStatus="status">
										<tr>
											<td style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${ result.board_id }"></td>
											<td style="display: none;"><input type="hidden" name="topic" value="4"></td>
											<td style="vertical-align: middle;">${ result.board_id }</td>
											<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/notice/${ result.board_id }">${ result.title }</a></td>
											<td style="vertical-align: middle;">${ result.write_date }</td>
										</tr>
									</c:forEach>
								</c:if>
								
								<c:if test="${ param.tab==5 }">
									<c:forEach items="${ faq_list }" var="result" varStatus="status">
										<tr>
											<td style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${ result.board_id }"></td>
											<td style="display: none;"><input type="hidden" name="topic" value="5"></td>
											<td style="vertical-align: middle;">${ result.board_id }</td>
											<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/qna/${result.board_id}">${ result.title }</a></td>
											<td style="vertical-align: middle;">${ result.member_id }</td>
											<td style="vertical-align: middle;">${ result.write_date }</td>
										</tr>
									</c:forEach>
								</c:if>
								
								<c:if test="${ param.tab==6 }">
									<c:forEach items="${ qna_list }" var="result" varStatus="status">
										<tr>
											<td style="vertical-align: middle;"><input type="checkbox" name="checkbox" value="${ result.board_id }"></td>
											<td style="display: none;"><input type="hidden" name="topic" value="6"></td>
											<td style="vertical-align: middle;">${ result.board_id }</td>
											<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/qna/${result.board_id}">${ result.title }</a></td>
											<td style="vertical-align: middle;">${ result.member_id }</td>
											<td style="vertical-align: middle;">${ result.write_date }</td>
										</tr>
									</c:forEach>
								</c:if>
							</tr>
						</tbody>						
					</table>
					
					<button class="btn btn-primary" type="button" id="checked_board_delete">선택 게시물 삭제</button>
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
	
	<!-- 선택 게시물 삭제 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#checked_board_delete").on("click", function() {
				var checkbox=$("input[name=checkbox]:checked");
				
				if(checkbox.length == 0){
					alert("선택된 게시물이 없습니다.");
					return;
				}
				
				var jsonArray=new Array();
				var json;
				
				checkbox.each(function(index){
					var tr=checkbox.parent().parent().eq(index);
					var td=tr.children();
					
					var board_id=td.eq(0).children().val();
					var topic=td.eq(1).children().val();
					
					json={"board_id":board_id,"topic":topic};
					
					jsonArray.push(json);
				})
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/mypage/admin/boards_delete",
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