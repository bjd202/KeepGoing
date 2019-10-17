<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>상품 보기</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">상품
						보기</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<div class="container">
		<div class="site-section">
			<div class="container">
				<div class="row mb-5">

					<div class="col-md-5 ml-auto mb-5 order-md-2 aos-init aos-animate"
						data-aos="fade">
						<img
							src="<%=request.getContextPath() %>/resources/images/${ searchedItem.image }"
							alt="Image" class="img-fluid rounded" style="min-width: 100%; height: 400px;">
					</div>
					<div class="col-md-6 order-md-1 aos-init aos-animate"
						data-aos="fade">
						<div class="text-left pb-1 border-primary mb-4">
							<h2 class="text-primary">${ searchedItem.title }</h2>
						</div>
						<h4>
							<span class="text-muted">작성자 : ${ searchedItem.nickname }</span>
						</h4>
						<h4>
							<span class="text-muted">작성 시간 : ${ searchedItem.write_date }</span>
						</h4>
						<h4>
							<span class="text-muted">카테고리 : ${ searchedItem.getCategoryString() }</span>
						</h4>
						<h4>
							<span class="text-muted">가격 : ${ searchedItem.price }</span>
						</h4>
						<h4>
							<span class="text-muted">개수 : <span id="number">${ searchedItem.number }</span></span>
						</h4>
						<p>${ searchedItem.content }</p>

						<div class="row">
							<div class="col-md-12 mb-md-5 mb-0 col-lg-6">
								<div class="unit-4">
									<div>
										<c:if
											test="${ login_member.member_id eq 'admin' or login_member.member_id eq searchedItem.member_id }">
											<a href="<%=request.getContextPath()%>/item_update/${searchedItem.board_id}" class="btn btn-primary" type="button">수정</a>
											<a class="btn btn-primary" id="item_delete" type="button">삭제</a>
										</c:if>
										
										<button class="btn btn-primary" id="item_buy" type="button">구매</button>
										<button class="btn btn-primary" id="add_cart" type="button">장바구니에
											추가</button>
									</div>
								</div>
								
								<!-- 좋아요, 싫어요 -->
								<button type="button" name="like_and_dislike" id="like" class="${ btn_status==1 ? 'btn btn-info' : 'btn btn-light'}">좋아요[<span>${searchedItem.like_cnt}</span>]</button>
								<button type="button" name="like_and_dislike" id="dislike" class="${ btn_status==2 ? 'btn btn-danger' : 'btn btn-light'}">싫어요[<span>${searchedItem.dislike_cnt}</span>]</button>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="site-section">
			<ul id="myTab" class="nav nav-tabs" role="tablist">
				<li role="presentation" class=""><a href="#home" id="home-tab"
					role="tab" data-toggle="tab" aria-controls="home"
					aria-expanded="true">댓글[${ searchedItem.comment_cnt }]</a></li>
				<li role="presentation" class=""><a href="#profile" role="tab"
					id="profile-tab" data-toggle="tab" aria-controls="profile"
					aria-expanded="false">리뷰</a></li>
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
								value="${ searchedItem.board_id }"> <input type="hidden"
								name="topic" value="${ searchedItem.topic }">
							<textarea rows="5" cols="" class="form-control" name="content"
								placeholder="Comment"></textarea>
							<button class="btn btn-default" id="add_comment" type="button">댓글
								작성</button>
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
				<!-- <div role="tabpanel" class="tab-pane fade" id="profile"
					aria-labelledby="profile-tab">
					<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla
						single-origin coffee squid. Exercitation +1 labore velit, blog
						sartorial PBR leggings next level wes anderson artisan four loko
						farm-to-table craft beer twee. Qui photo booth letterpress,
						commodo enim craft beer mlkshk aliquip jean shorts ullamco ad
						vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic
						magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna
						velit sapiente labore stumptown. Vegan fanny pack odio cillum wes
						anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa
						terry richardson biodiesel. Art party scenester stumptown, tumblr
						butcher vero sint qui sapiente accusamus tattooed echo park.</p>
				</div> -->
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
							// like 비활성, dislike 활성 == update is_like=2
							status=4;
						}else{
							$("#dislike").attr("class", "btn btn-danger");
							// 둘다 비활성 == insert is_like=2
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
		            data: JSON.stringify({"board_id": ${searchedItem.board_id}, "topic": ${searchedItem.topic}, "status": status}),
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
	<!-- 상품 삭제 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#item_delete").on("click", function() {
				
				var board_id=${searchedItem.board_id};
				
				$.ajax({
		            url: "<%=request.getContextPath()%>/item_delete/${searchedItem.board_id}",
		            type: "post",
		            data: "board_id="+board_id,
		            success: function(data){
		                alert(data);
		                
		                location.href="<%=request.getContextPath()%>/item";
		            },
		            error: function(){
		                alert("err");
		            }
		        });
			})
		})
	</script>
	<!-- 상품 구매 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#item_buy").on("click", function() {
				
				var number=parseInt( $("#number").text() );
				
				if(number<=0){
					alert("품절된 상품 입니다.");
					return;
				}
				
				var board_id=${searchedItem.board_id};
				
				$.ajax({
		            url: "<%=request.getContextPath()%>/add_cart/${searchedItem.board_id}",
		            type: "post",
		            data: "board_id="+board_id,
		            success: function(data){
		                alert(data);
		                
		                location.href="<%=request.getContextPath()%>/cart";
		            },
		            error: function(){
		                alert("err");
		            }
		        });
			})
		})
	</script>
	<!-- 장바구니 추가 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#add_cart").on("click", function() {
				
				var number=parseInt( $("#number").text() );
				
				if(number<=0){
					alert("품절된 상품 입니다.");
					return;
				}
				
				var board_id=${searchedItem.board_id};
				
				$.ajax({
		            url: "<%=request.getContextPath()%>/add_cart/${searchedItem.board_id}",
		            type: "post",
		            data: "board_id="+board_id,
		            success: function(data){
		            	if(data=="success")
		                	alert("장바구니에 추가되었습니다.");
		            	else
		            		alert("장바구니 추가가 실패했습니다.");
		            },
		            error: function(){
		                alert("err");
		            }
		        });
			})
		})
	</script>
</body>
</html>