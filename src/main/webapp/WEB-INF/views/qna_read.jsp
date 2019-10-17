<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>QnA</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">Q&A</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="container">
	<div class="site-section block-13">
		<div style="border-bottom: 1px solid;">
		<h4>${ qnaRead.title }</h4>
		</div>
		<br>
		<div style="border-bottom: 1px solid;">
		<textarea rows="20" class="form-control" name="content" placeholder="Content" style="border: none; background-color: white;" readonly>${ qnaRead.content }</textarea>
		</div>
		<br>
		<p align="right">
			<c:if test="${ login_member.auth >= 2 }">
			<a href="<%=request.getContextPath()%>/qna/rewrite" class="btn btn-info btn-xs" type="button">답변</a>
			</c:if>
			<c:if test="${ login_member.auth >= 0 }">
			<a href="<%=request.getContextPath()%>/qna" class="btn btn-info btn-xs" type="button">수정</a>
			<a href="<%=request.getContextPath()%>/qna" class="btn btn-info btn-xs" type="button">삭제</a>
			</c:if>
			<a href="<%=request.getContextPath()%>/qna" class="btn btn-info btn-xs" type="button">목록</a>
		</p>
		
<!-- 댓글 -->
	<div class="site-section" style="padding: 0;">
			<ul id="myTab" class="nav nav-tabs" role="tablist">
				<li role="presentation" class="">
					<a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home"
						aria-expanded="true">댓글[${ commentCount.size() == null ? 0 : commentCount.size() }]</a>
				</li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div role="tabpanel" class="tab-pane fade active in" id="home"
					aria-labelledby="home-tab">

					<c:if test="${ login_member != null }">
						<form action="" method="post" id="comment_form">
							<input type="hidden" name="member_id" value="${ login_member.member_id }">
							<input type="hidden" name="nickname" value="${ login_member.nickname }">
							<input type="hidden" name="board_id" value="${ qnaRead.board_id }">
							<input type="hidden" name="topic" value="6">
							<textarea rows="5" cols="" class="form-control" name="content"
								placeholder="Comment"></textarea>
							<button class="btn btn-default" id="add_comment" type="button">작성</button>
						</form>
					</c:if>
					
					<!-- 댓글 -->
					<c:forEach items="${ commentCount }" var="comment">
						<div class="rw">
							<div class="bpd">
								<div class="bpb">
									<h6>${ comment.nickname }</h6>
									<small class="acx axc">${ comment.write_date }</small>
								</div>
								<p>${ comment.content }</p>
							</div>
							<c:if
								test="${ comment.member_id == login_member.member_id or login_member.auth >= 0 }">
								<button class="" name="comment_delete_btn" type="button"
									value="${ comment.comment_id }">댓글 삭제</button>
							</c:if>
						</div>
					</c:forEach>

				</div>
			</div>
	</div>
<!-- /댓글 -->	
		
		
		
		
		
	<!-- 윗글 아랫글 -->
    <div>
		<table class="table">
			<tr>
				<td style="width: 200px;">윗글</td>
				<c:if test="${  qnaReadUp == null }" var="nr">
					<td>윗글이 없습니다.</td>
				</c:if>
				<c:if test="${  nr != null }">
					<td><a href="<%=request.getContextPath()%>/qna/qnaread/${ qnaReadUp.board_id }">${ qnaReadUp.title }</a></td>
				</c:if>
			</tr>
			<tr>
				<td style="width: 200px;">아랫글</td>
				<c:if test="${  qnaReadDown == null }" var="nr">
					<td>아랫글이 없습니다.</td>
				</c:if>
				<c:if test="${  nr != null }">
					<td><a href="<%=request.getContextPath()%>/qna/qnaread/${ qnaReadDown.board_id }">${ qnaReadDown.title }</a></td>
				</c:if>
			</tr>
		</table>    
    </div>
    <!-- 윗글 아랫글 마지막줄-->
    
	</div>
</div>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	
	<!-- 댓글, 리뷰 숨기기 보이기 -->
		<script type="text/javascript">
			$(document).ready(function() {
				$("#home-tab").on("click", function() {
					$("#home").show();
					$("#profile").hide();
				})
				
				$("#profile-tab").on("click", function() {
					$("#profile").show();
					$("#home").hide();
				})
			})
			
			$("#home").hide();
			$("#profile").hide();
		</script>
	
		<!-- 댓글 작성 -->
		<script type="text/javascript">
			$(document).ready(function() {
				$("#add_comment").on("click", function() {
					var params=$("#comment_form").serialize();
					console.log(params);
	
					$.ajax({
			            url: "<%=request.getContextPath()%>/comment_add",
			            type: "post",
			            data: params,
			            success: function(data){
			                console.log(data);
			                alert("댓글 작성을 완료했습니다.");
			                
			                var Now = new Date();
			                var NowTime = Now.getFullYear();
			                
			                if((Now.getMonth() + 1)>10)
			                	NowTime += '-' + (Now.getMonth() + 1);
			                else
			                	NowTime += '-' + '0' + (Now.getMonth() + 1);
			                
			                if(Now.getDate()>10)
			                	NowTime += '-' + Now.getDate();
			                else
			                	NowTime += '-' + '0' + Now.getDate();
			                
			                if(Now.getHours()>10)
			                	NowTime += ' ' + Now.getHours();
			                else
			                	NowTime += ' ' + '0' + Now.getHours();
			                
			                if(Now.getMinutes()>10)
			                	NowTime += ':' + Now.getMinutes();
			                else
			                	NowTime += ':' + '0' + Now.getMinutes();
			                
			                if(Now.getSeconds()>10)
			                	NowTime += ':' + Now.getSeconds();
			                else
			                	NowTime += ':' + '0' + Now.getSeconds();
			                
			                NowTime += '.0';
			                
							var tag="";
							tag+='<div class="rw">';
							tag+='<div class="bpd">';
							tag+='<div class="bpb">';
							tag+='<h6>'+data.nickname+'</h6>';
							tag+='<small class="acx axc">'+NowTime+'</small>';
							tag+='</div>';
							tag+='<p>'+data.content+'</p>';
							tag+='</div>';
							if( (data.member_id == "${login_member.member_id}" ) || "${login_member.auth}" >= 0)
								tag+='<button class="" name="comment_delete_btn" type="button" value="'+data.comment_id+'">댓글 삭제</button>';
							tag+='</div>';
							
							$("#home").append(tag);
			            },
			            error: function(){
			                alert("댓글 작성을 실패했습니다.");
			            }
			        });
				});
			});
		</script>
	
		<!-- 댓글 삭제 -->
		<script type="text/javascript">
			$(document).ready(function() {
				$(document).on("click", "button[name=comment_delete_btn]", function() {
					var rw=$(this).parent(".rw");
					var comment_id=$(this).val();
					console.log(comment_id);
					
					$.ajax({
			            url: "<%=request.getContextPath()%>/comment_delete",
			            type: "post",
			            data: "comment_id="+comment_id,
			            success: function(data){
			                alert(data);
			                
			                rw.empty();
							rw.remove();
			            },
			            error: function(){
			                alert("err");
			            }
			        });
				})
			})
		</script>
	
	
<footer>
 <jsp:include page="site_footer.jsp"></jsp:include>
</footer>

</body>
</html>