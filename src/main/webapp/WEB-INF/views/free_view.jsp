<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>자유게시글</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">자유게시글</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	
	
	<div class="container"  >
		<div class="site-section block-13" >
		<br>
     
       
          
          
				
		
          <table class="table">
          	<tr align="center">
          		<td colspan="3"><b style="color: black; font-size: 30px; ">게시글 제목  : ${ searchedFree.title }</b></td>
          	</tr>
          	<tr>
          		<td>
          			<img src="<%=request.getContextPath() %>/resources/images/${ searchedFree.image }" alt="Image" >
				</td>
			</tr>
	        <tr align="center">
	          	<td colspan="3"><div class="jumbotron"><a style="color: black; font-size: 15px; ">${ searchedFree.content }</a></div></td>
	        </tr><!-- /${ searchedFree.image } -->
          	
			<tr>
			<td><b style="color: black; font-size: 15px; ">작성 시간 : ${ searchedFree.write_date }</b></td>
			<!-- 디테일 모델에서 카테고리 이름 변환 -->
			<td><b style="color: black; font-size: 15px; ">관심사 : ${ searchedFree.getCategoryString() }</b></td>
			<td><b style="color: black; font-size: 15px; ">작성자 : ${ searchedFree.nickname }</b></td>
			
			</tr>
		</table>


         
   
          
          <div>
          <!-- 해당 아이디 가 접근시 수정및 삭제 가능 -->
	          <c:if	test="${ login_member.member_id eq 'admin' or login_member.member_id eq searchedFree.member_id }">
	          	<a class="btn btn-default" type="submit" href="<%=request.getContextPath()%>/update_free/${ searchedFree.board_id}"	id = "update_free" >수정</a>
	          	<a class="btn btn-default" type="submit" href="<%=request.getContextPath()%>/delete_free/${ searchedFree.board_id}"	id = "delete_free" >삭제</a>
	        	<button type="button" name="like_and_dislike" id="like" class="${ btn_status==1 ? 'btn btn-info' : 'btn btn-light'}">좋아요[<span>${searchedItem.like_cnt}</span>]</button>
				<button type="button" name="like_and_dislike" id="dislike" class="${ btn_status==2 ? 'btn btn-danger' : 'btn btn-light'}">싫어요[<span>${searchedItem.dislike_cnt}</span>]</button>
	          </c:if>
	         <p align="right"><a class="btn btn-default" style="color : black;" type="submit" href="<%=request.getContextPath()%>/free/1">자유게시판으로 이동</a></p>
	         <p align="right"><a class="btn btn-default" style="color : black;" type="submit" href="<%=request.getContextPath()%>/home">홈페이지로 이동</a></p>
	          <!-- 해당 값을 가져와 출력 -->
	          <form method="post" id="form6">
	          	<input type="hidden" name="board_id" value="${ searchedFree.board_id }">
	          	<input type="hidden" name="title" value="${ searchedFree.title }">
	          	<input type="hidden" name="content" value="${ searchedFree.content }">
	          	<input type="hidden" name="category" value="${ searchedFree.category }" >
	          </form>
	        
          </div>
        </div>
        <div class="site-section">
			<ul id="myTab" class="nav nav-tabs" role="tablist">
				<li role="presentation" class=""><a href="#home" id="home-tab"
					role="tab" data-toggle="tab" aria-controls="home"
					aria-expanded="true">댓글[${ searchedFree.comment_cnt }]</a></li>
				
			</ul>
			<div id="myTabContent" class="tab-content">
				<div role="tabpanel" class="tab-pane fade active in" id="home"
					aria-labelledby="home-tab">

					<c:if test="${ login_member != null }">
						<form action="" method="post" id="comment_form">
							<input type="hidden" name="member_id"
								value="${ login_member.member_id }"> <input
								type="hidden" name="nickname" value="${ login_member.nickname }">
							<input type="hidden" name="board_id"
								value="${ searchedFree.board_id }"> <input type="hidden"
								name="topic" value="${ searchedFree.topic }">
							<textarea rows="5" cols="" class="form-control" name="content"
								placeholder="Comment"></textarea>
							<button class="btn btn-default" id="add_comment" type="button">댓글작성</button>
						</form>
					</c:if>
					
					<!-- 댓글 -->
					<c:forEach items="${ commentList }" var="comment">
						<div class="rw">
							<div class="bpd">
								<div class="bpb">
									<h6>${ comment.nickname }</h6>
									<small class="acx axc">${ comment.write_date }</small>
								</div>
								<p>${ comment.content }</p>
							</div>
							<c:if
								test="${ comment.member_id == login_member.member_id or login_member.auth >= 2 }">
								<button class="" name="comment_delete_btn" type="button"
									value="${ comment.comment_id }">댓글 삭제</button>
							</c:if>
						</div>
					</c:forEach>

				</div>
				<!-- 리뷰 -->
				
			</div>
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
						if( (data.member_id == "${login_member.member_id}" ) || ${login_member.auth >= 2})
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
			$("button[name=comment_delete_btn]").on("click", function() {
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
	<!-- 좋아요, 싫어요 -->
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("button[name=like_and_dislike]").on("click", function() {
				var id_val=$(this).attr("id");
				var class_val=$(this).attr("class");
				var status=0;
				
				if( "${login_member.member_id}" == "" ){
					alert("로그인이 필요한 기능입니다.");
					return;
				}
				
				if(id_val=="like"){
					if(class_val=="btn btn-light"){
						if( $("#dislike").attr("class")=="btn btn-danger" ){
							$(this).attr("class", "btn btn-info");
							$("#dislike").attr("class", "btn btn-light");
							// dislike 비활성, like 활성 == update is_like=1
							status=1;
						}else{
							$(this).attr("class", "btn btn-info");
							// 둘다 비활성 == insert is_like=1
							status=2;
						}
					}else{
						$(this).attr("class", "btn btn-light");
						// like 활성 -> 비활성 == delete
						status=3;
					}
				}else{
					if(class_val=="btn btn-light"){
						if( $("#like").attr("class")=="btn btn-info" ){
							$(this).attr("class", "btn btn-danger");
							$("#like").attr("class", "btn btn-light");
							// like 비활성, dislike 활성 == update is_like=0
							status=4;
						}else{
							$("#dislike").attr("class", "btn btn-danger");
							// 둘다 비활성 == insert is_like=0
							status=5;
						}
					}else{
						$(this).attr("class", "btn btn-light");
						// dislike 활성 -> 비활성 == delete
						status=6;
					}
				}
				
				console.log(status);
				
				$.ajax({
		            url: "<%=request.getContextPath()%>/like_and_dislike",
		            type: "post",
		            data: JSON.stringify({"board_id": ${searchedFree.board_id}, "topic": ${searchedFree.topic}, "status": status}),
		            dataType: 'text',
		            contentType: 'application/json; charset=utf-8',
		            success: function(data){
		                console.log(data);
		                
		                var like_cnt=Number( $("#like").children("span").text() );
		                var dislike_cnt=Number( $("#dislike").children("span").text() );
		                
		                
		                if(status==1){
		                	$("#like").children("span").text(like_cnt+1);
		                	$("#dislike").children("span").text(dislike_cnt-1);
		                }else if(status==2){
		                	$("#like").children("span").text(like_cnt+1);
		                }else if(status==3){
		                	$("#like").children("span").text(like_cnt-1);
		                }else if(status==4){
		                	$("#like").children("span").text(like_cnt-1);
		                	$("#dislike").children("span").text(dislike_cnt+1);
		                }else if(status==5){
		                	$("#dislike").children("span").text(dislike_cnt+1);
		                }else{
		                	$("#dislike").children("span").text(dislike_cnt-1);
		                }
		                
		                console.log(like_cnt);
		                console.log(dislike_cnt);
		            },
		            error: function(request,status,error){
		                alert("err");
		                alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		            }
		        });
			})
		});
	</script>
	
	
</body>
</html>