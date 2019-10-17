<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Index</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>
</head>
<body>

	<jsp:include page="menu.jsp" flush="false"></jsp:include>

	<jsp:include page="modalLogin.jsp" flush="false"></jsp:include>
	<jsp:include page="reviewSearchModal.jsp" flush="false"></jsp:include>

	<div class="site-blocks-cover inner-page-cover overlay"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/top.jpg);"
		data-aos="fade" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row align-items-center justify-content-center text-center">

				<div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
					<h1
						class="text-white font-weight-light text-uppercase font-weight-bold">리뷰</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<div class="site-section block-13"
		style="padding-bottom: 10; margin-left: 10%; margin-right: 10%; margin-top: 130;">
		<c:if test="${ login_member.auth == '99' }" var="r">
			<a href="<%=request.getContextPath()%>/review/write">리뷰 작성하기</a>
		</c:if>
		<div align="center">
			<table class="table">
				<tr>
					<td><a href="<%= request.getContextPath() %>/review/1">전체</a></td>
					<td><a href="<%= request.getContextPath() %>/review/2">상품</a></td>
					<td><a href="<%= request.getContextPath() %>/review/3">피트니스</a></td>
					<td><a href="<%= request.getContextPath() %>/review/4">장소</a></td>
					<td><a href="<%= request.getContextPath() %>/review/5">다이어트</a></td>
					<td><a href="<%= request.getContextPath() %>/review/6">웨이트 트레이닝</a></td>
					<td><a href="<%= request.getContextPath() %>/review/7">레시피</a></td>
				</tr>
			</table>

		</div>
	</div>

	<div align="center" style="margin-left: 10%; margin-right: 10%;">
		<div class="site-section block-13" style="padding-top: 5; padding-bottom: 0;">
			<div class="row">


				<table class="table">
					<tr align="center">
						<td colspan="3"><b style="color: black; font-size: 30px;">리뷰
								제목 : ${ detailReview.title }</b></td>
					</tr>

					<tr align="center">
						<td colspan="3"><div class="jumbotron">
								<p><img src="<%= request.getContextPath() %>/resources/images/${ detailReview.image }"></p>
								<a style="color: black; font-size: 15px;">${ detailReview.content }</a>
								</div>
						</td>
					</tr>

					<tr>
						<td>
							<div class="mb-3" align="left">
								<label for="address">주소 </label><br> <input type="text"
									class="form-control" id="sample6_address" style="width: 70%"
									name="address_basic" readonly="readonly"
									value="${ detailReview.selectedAddress }">
							</div>
							<div id="map" style="width: 500px; height: 400px;" align="left">
								<!-- 지도 첨부 영역입니다. -->
							</div>
						</td>
					</tr>
					<tr>
						<td><b style="color: black; font-size: 15px;">작성 시간 : ${ detailReview.write_date }</b></td>
						<td><b style="color: black; font-size: 15px;">카테고리 : ${ strCategory }</b></td>
						<td><b style="color: black; font-size: 15px;">작성자 : ${ detailReview.nickname }</b></td>

					</tr>
					<tr>
						<td>
						<div>
						<c:if test="${ login_member.member_id eq 'admin' or login_member.member_id eq detailReview.member_id }">
	          				<a class="btn btn-default" type="submit" href="<%=request.getContextPath()%>/update_review/${ detailReview.board_id}"	id = "update_free" >수정</a>
	          				<a class="btn btn-default" type="submit" href="<%=request.getContextPath()%>/delete_review/${ detailReview.board_id}"	id = "delete_free" >삭제</a>
	          			</c:if>
							<!-- 좋아요, 싫어요 -->
							<button type="button" name="like_and_dislike" id="like" class="${ btn_status==1 ? 'btn btn-info' : 'btn btn-light'}">좋아요[<span>${detailReview.like_cnt}</span>]</button>
							<button type="button" name="like_and_dislike" id="dislike" class="${ btn_status==2 ? 'btn btn-danger' : 'btn btn-light'}">싫어요[<span>${detailReview.dislike_cnt}</span>]</button>
						</div>
						</td>
					</tr>
				</table>

				<div>
					<form method="post" id="form6">
						<input type="hidden" name="board_id"
							value="${ detailReview.board_id }"> <input type="hidden"
							name="title" value="${ detailReview.title }"> <input
							type="hidden" name="content" value="${ detailReview.content }">
						<input type="hidden" name="category"
							value="${ detailReview.category }">
					</form>
				</div>
			</div>
		</div>
		
		<div class="site-section" style="padding: 0;">
			<ul id="myTab" class="nav nav-tabs" role="tablist">
				<li role="presentation" class=""><a href="#home" id="home-tab"
					role="tab" data-toggle="tab" aria-controls="home"
					aria-expanded="true">댓글[${ detailReview.comment_cnt }]</a></li>
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
								value="${ detailReview.board_id }"> <input type="hidden"
								name="topic" value="1">
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
				<div role="tabpanel" class="tab-pane fade" id="profile"
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
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptIncludeForReview.jsp" flush="false"></jsp:include>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=11a43cb9e206654a596d1b017c4932ec&libraries=services,clustere"></script>
	<script type="text/javascript">
		var geocoder = new kakao.maps.services.Geocoder();
		var selectedAddress = '${ detailReview.selectedAddress }';
		var selectedLat = ${ detailReview.selectedLat };
		var selectedLng = ${ detailReview.selectedLng };
		if (selectedAddress == '' && selectedLat == '') {
			$("#sample6_address").val("위지 정보를 첨부하지 않은 리뷰입니다.");
		} else {
			if(selectedAddress == '') {
				selectedAddress = '지명이 없는 주소';
			}
			$("#sample6_address").val(selectedAddress);
			var container = document.getElementById('map');
			// 아래 options에서 center는 지도를 생성하는데 필수이다.
			var options = {
				center : new kakao.maps.LatLng(selectedLat, selectedLng),
				level : 3
			// 지도의 레벨(확대, 축소의 정도)
			};
			var map = new kakao.maps.Map(container, options); // 지도의 생성 및 객체 리턴
			// 지도를 클릭한 위치에 마커를 생성
			var marker = new kakao.maps.Marker({
				// 중심 좌표에 마커를 생성
				position : map.getCenter()
			});
			marker.setMap(map);
			// 마커에 인포 윈도우를 표시하기.
			// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
			var iwContent = '<div style="padding:5px; font-size:11px;">'
					+ selectedAddress
					+ ' <br><a href="https://map.kakao.com/link/map/'+selectedAddress+','+selectedLat+','+
			selectedLng+'" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/'+selectedAddress+','+selectedLat+','+selectedLng+'" style="color:blue" target="_blank">길찾기</a></div>', iwPosition = new kakao.maps.LatLng(
					selectedLat, selectedLng); //인포윈도우 표시 위치입니다
			// 1. 인포 윈도우 생성
			var infowindow = new kakao.maps.InfoWindow({
				position : iwPosition,
				content : iwContent
			});
			// 2. 마커 위에 인포윈도우를 표시. 두번째 파라메터인 marker를 넣어주지 않으면 그냥 지도위에 표시됨.
			infowindow.open(map, marker);
		}
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
		            data: JSON.stringify({"board_id": ${detailReview.board_id}, "topic": 1, "status": status}),
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
							if( (data.member_id == "${login_member.member_id}" ) || "${login_member.auth}" >= 2)
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
		
		
		
	
	
</body>
</html>