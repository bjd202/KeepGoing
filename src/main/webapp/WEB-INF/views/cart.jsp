<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<title>장바구니</title>

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
						class="text-white font-weight-light text-uppercase font-weight-bold">장바구니</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<div class="site-section">
		<div class="container">
			<table class="table table-bordered col-xs-12" style="margin: auto; text-align: center; vertical-align: middle;">
				<thead>
					<tr>
						<th><input type="checkbox" id="all_check"></th>
						<th>번호</th>
						<th>카테고리</th>
						<th>이미지</th>
						<th>제목</th>
						<th>가격</th>
						<th>수량</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${ login_member eq null }" var="r">
						<c:forEach items="${ sessionScope.cartList }" var="result"
							varStatus="status">
							<tr>
								<td style="vertical-align: middle;"><input type="checkbox" name="checkbox"
									value="${ result.board_id }"></td>
								<td style="vertical-align: middle;">${ status.count }</td>
								<td style="display: none;"><input type="hidden" value="${ result.category }"></td>
								<td style="vertical-align: middle;">${ result.getCategoryString() }</td>
								<td style="vertical-align: middle;"><img style="width: 100px; height: 100px;" alt="No Image"
									src="<%=request.getContextPath()%>/resources/images/${result.image}"></td>
								<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/item_view/${result.board_id}">${ result.title }</a></td>
								<td style="vertical-align: middle;">${ result.price }</td>
								<td style="vertical-align: middle;"><input style="border: 1px solid; text-align: center;" type="number"
									name="number" min="1" max="100" value="1"></td>
								<td style="display: none;">${ result.cart_id }</td>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${ not r }">
						<c:forEach items="${ requestScope.cartList }" var="result"
							varStatus="status">
							<tr>
								<td style="vertical-align: middle;"><input type="checkbox" name="checkbox"
									value="${ result.board_id }"></td>
								<td style="vertical-align: middle;">${ status.count }</td>
								<td style="display: none;"><input type="hidden" value="${ result.category }"></td>
								<td style="vertical-align: middle;">${ result.getCategoryString() }</td>
								<td style="vertical-align: middle;"><img style="width: 100px; height: 100px;" alt="No Image"
									src="<%=request.getContextPath()%>/resources/images/${result.image}"></td>
								<td style="vertical-align: middle;"><a href="<%=request.getContextPath()%>/item_view/${result.board_id}">${ result.title }</a></td>
								<td style="vertical-align: middle;">${ result.price }</td>
								<td style="vertical-align: middle;"><input style="border: 1px solid; text-align: center;" type="number"
									name="number" min="1" max="100" value="1"></td>
								<td style="display: none;">${ result.cart_id }</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="3">총계</td>
						<td colspan="4" id="total">0</td>
					</tr>
				</tfoot>
			</table>
			
			<button class="btn btn-primary" type="button" id="checked_item_buy">선택 상품 구매</button>
			<button class="btn btn-primary" type="button" id="checked_item_delete">선택 상품 삭제</button>
			
		</div>
	</div>
	
	<div class="site-section">
		<div class="container">
			<div class="mb-3" align="left" style="width: 50%; margin: auto;">
				<label for="nickname">*받는 사람</label> 
				<input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${ login_member.name }">
				<div class="invalid-feedback">X</div>
			</div>

			<div class="mb-3" align="left" style="width: 50%; margin: auto;">
				<label for="address">*주소</label><br>
				<input type="button" class="btn btn-primary" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" class="form-control" id="sample6_postcode" name="address_post" placeholder="우편번호" value="${ login_member.address_post }" readonly="readonly">
				<input type="text" class="form-control" id="sample6_address" name="address_basic" placeholder="주소" value="${ login_member.address_basic }" readonly="readonly">
				<input type="text" class="form-control"	id="sample6_detailAddress" name="address_detail" placeholder="상세주소" value="${ login_member.address_detail }"><br>
				<input type="text" class="form-control" id="sample6_extraAddress" placeholder="참고항목" readonly="readonly">
				<div class="invalid-feedback">X</div>
			</div>
		</div>
	</div>
	
	

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>

	
	
 <jsp:include page="site_footer.jsp"></jsp:include>

	
	<!-- 전체선택 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#all_check").on("click", function() {
				var total = 0;
				
				if( $("#all_check").prop("checked") ){
					$("input[name=checkbox]").prop("checked", true);
					
					var checkbox=$("input[name=checkbox]:checked");
					
					checkbox.each(function(index){
						var tr=checkbox.parent().parent().eq(index);
						var td=tr.children();
						
						var price=parseInt( td.eq(6).text() );
						var number=parseInt( td.eq(7).children().val() );
						
						total+=price*number;

					});
					
					$("#total").text(total);
				}
				else{
					$("input[name=checkbox]").prop("checked", false);
					
					$("#total").text(total);
				}
			})
		});
	</script>
	
	<!-- 총계 -->
	<script type="text/javascript">
		
		$(document).ready(function() {
			$("input[name=checkbox], input[name=number]").change(function (){
				var total = 0;
				var checkbox=$("input[name=checkbox]:checked");
				
				checkbox.each(function(index){
					var tr=checkbox.parent().parent().eq(index);
					var td=tr.children();
					
					var price=parseInt( td.eq(6).text() );
					var number=parseInt( td.eq(7).children().val() );
					
					total+=price*number;
				})
				
				$("#total").text(total);
			});
		})
	</script>
	
	<!-- 선택 상품 구매 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#checked_item_buy").on("click", function() {
				
				var member_id = "${login_member.member_id}";
				var name = $("#name").val();
				var address_post = $("#sample6_postcode").val();
				var address_basic = $("#sample6_address").val();
				var address_detail = $("#sample6_detailAddress").val();
				var checkbox=$("input[name=checkbox]:checked");
				
				if(member_id.length == 0){
					alert("로그인이 필요한 기능입니다.");
					return;
				}else if(name.length == 0){
					alert("받는 사람을 입력해주세요.");
					return;
				}else if(address_post.length == 0){
					alert("주소를 입력해주세요.");
					return;
				}else if(address_basic == 0){
					alert("주소를 입력해주세요.");
					return;
				}else if(address_detail == 0){
					alert("주소를 입력해주세요.");
					return;
				}else if(checkbox.length == 0){
					alert("선택된 상품이 없습니다.");
					return;
				}
				
				var jsonArray=new Array();
				var json;
				
				checkbox.each(function(index){
					var tr=checkbox.parent().parent().eq(index);
					var td=tr.children();
					
					var board_id=td.eq(0).children().val();
					var category=td.eq(2).children().val();
					var title=td.eq(5).text();
					var price=td.eq(6).text();
					var number=td.eq(7).children().val();
					var cart_id=td.eq(8).text();
					
					json={"board_id":board_id, "category":category,
							"member_id":member_id, "name":name,
							"address_post":address_post, "address_basic":address_basic,
							"address_detail":address_detail, "title":title,
							"number":number, "price":price, "cart_id":cart_id};
					
					jsonArray.push(json);
				})
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/item_buy",
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
	
	<!-- 선택 상품 삭제 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#checked_item_delete").on("click", function() {
				var checkbox=$("input[name=checkbox]:checked");
				
				if(checkbox.length == 0){
					alert("선택된 상품이 없습니다.");
					return;
				}
				
				var jsonArray=new Array();
				var json;
				
				checkbox.each(function(index){
					var tr=checkbox.parent().parent().eq(index);
					var td=tr.children();
					
					var index=td.eq(1).text();
					var cart_id=td.eq(8).text();
					
					json={"index":index, "cart_id":cart_id};
					
					jsonArray.push(json);
				})
				
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()%>/cart_delete",
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
	
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
		function sample6_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								// 조합된 참고항목을 해당 필드에 넣는다.
								document.getElementById("sample6_extraAddress").value = extraAddr;

							} else {
								document.getElementById("sample6_extraAddress").value = '';
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('sample6_postcode').value = data.zonecode;
							document.getElementById("sample6_address").value = addr;
							// 커서를 상세주소 필드로 이동한다.
							document.getElementById("sample6_detailAddress")
									.focus();
						}
					}).open();
		}
	</script>
</body>
</html>