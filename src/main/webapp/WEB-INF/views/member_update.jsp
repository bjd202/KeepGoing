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
					<div class="modal-body" align="center">
						<form action="" method="post" id="update_form" style="width: 400px">
							<div class="mb-3" align="left">
								<label for="member_id">*ID </label> <input type="text"
									onkeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');"
									class="form-control" id="member_id" name="member_id"
									maxlength="20" placeholder="영문 +숫자  최소  6자 ~최대 20자 (중복X)" readonly="readonly" value="${ login_member.member_id }">
								<div class="invalid-feedback">X</div>
								<div id="IDCheck"></div>
							</div>

							<div class="mb-3" align="left">
								<label for="password">*비밀번호 </label> <input type="password"
									class="form-control" name="password" id="password"
									onkeyup="passwordCheck()" maxlength="20"
									placeholder="특수문자,영문 ,숫자  최소 6자 ~최대 20자" value="${ login_member.password }">
								<div class="invalid-feedback">X</div>

							</div>

							<div class="mb-3" align="left">
								<label for="reenter_password">*비밀번호 확인 </label> <input
									type="password" class="form-control" id="reenter_password"
									name="reenter_password" maxlength="20"
									onkeyup="passwordCheck()" placeholder="위의 password와 같이 입력" value="${ login_member.password }">
								<div class="invalid-feedback">X</div>
								<div id="passwordCheck"></div>
							</div>

							<div class="mb-3" align="left">
								<label for="name">*이름 </label> <input type="text" maxlength="8"
									class="form-control" id="name" name="name"
									onkeyup="nameCheck()" placeholder="Name" value="${ login_member.name }">
								<div class="invalid-feedback">X</div>
								<div id="NameCheck"></div>
							</div>

							<div class="mb-3" align="left">
								<label for="nickname">*별명 </label> <input type="text"
									onkeyup="this.value=this.value.replace(/[^0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]/g,'');"
									class="form-control" id="nickname" name="nickname"
									maxlength="8" placeholder="한글,영문 ,숫자(중복X) 최소 3자 ~최대 10자 " readonly="readonly" value="${ login_member.nickname }">
								<div class="invalid-feedback">X</div>
								<div id="NickCheck"></div>
							</div>

							<div class="mb-3" align="left">
								<label for="tel">전화번호 </label> <input type="text"
									onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"
									class="form-control" id="tel" name="tel" maxlength="11"
									placeholder="'-' 없이 입력" value="${ login_member.tel }">
								<div class="invalid-feedback">X</div>
							</div>

							<div class="mb-3" align="left">
								<label for="address">주소 </label><br> <input type="button"
									onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
								<input type="text" class="form-control" id="sample6_postcode"
									name="address_post" placeholder="우편번호" value="${ login_member.address_post }"> <input
									type="text" class="form-control" id="sample6_address"
									name="address_basic" placeholder="주소" value="${ login_member.address_basic }"> <input
									type="text" class="form-control" id="sample6_detailAddress"
									name="address_detail" placeholder="상세주소"><br> <input
									type="text" class="form-control" id="sample6_extraAddress"
									placeholder="참고항목" value="${ login_member.address_detail }">
								<div class="invalid-feedback">X</div>
							</div>

							<div class="mb-3" align="left">
								<label for="interest">*관심분야 </label>
								<div class="row" align="left">
									<div class="col-md-5">
										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input"
												name="interest" id="same-address1" value="웨이트 트레이닝"
												${ login_member.getInterestList().contains('웨이트 트레이닝') ? 'checked' : '' }>
											<label class="custom-control-label" for="same-address1">웨이트
												트레이닝</label>
										</div>

										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input"
												name="interest" id="same-address2" value="다이어트"
												${ login_member.getInterestList().contains('다이어트') ? 'checked' : '' }> <label
												class="custom-control-label" for="same-address2">다이어트</label>
											<div class="invalid-feedback">X</div>

										</div>

										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input"
												name="interest" id="same-address3" value="요가"
												${ login_member.getInterestList().contains('요가') ? 'checked' : '' }> <label
												class="custom-control-label" for="same-address3">요가</label>
											<div class="invalid-feedback">X</div>
										</div>
									</div>

									<div class="col-md-5">
										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input"
												name="interest" id="same-address5" value="필라테스"
												${ login_member.getInterestList().contains('필라테스') ? 'checked' : '' }> <label
												class="custom-control-label" for="same-address5">필라테스</label>
										</div>

										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input"
												name="interest" id="same-address6" value="스피닝"
												${ login_member.getInterestList().contains('스피닝') ? 'checked' : '' }> <label
												class="custom-control-label" for="same-address6">스피닝</label>
												
											<div class="invalid-feedback">X</div>

										</div>

										<div class="custom-control custom-checkbox">
											<input type="checkbox" class="custom-control-input"
												name="interest" id="same-address7" value="식이요법"
												${ login_member.getInterestList().contains('식이요법') ? 'checked' : '' }> <label
												class="custom-control-label" for="same-address7">식이요법</label>
											<div class="invalid-feedback">X</div>
										</div>
									</div>
								</div>
							</div>
							
							<button class="btn btn-primary" type="button" id="member_update">회원 정보 수정</button>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<!-- 주소 검색 API -->
	<script type="text/javascript">
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
	<!-- 회원 정보 수정 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#member_update").on("click",function(){
				var params=$("#update_form").serialize();
				$.ajax({
		            url: "<%=request.getContextPath()%>/mypage/member_update",
		            type: "POST",
		            data: params,
		            success: function(data){
		                alert(data);
						location.reload();
					},
					error : function() {
						alert("err");
					}
				});
			});
		});
	</script>
	<!-- 데이터 검증 -->
	<script type="text/javascript">
		var isPwOk = false;
		var isNameOk = false;
		
		function passwordCheck() {
			if( $("#password").val() == $("#reenter_password").val() ){
				 console.log("일치");
				 $("#passwordCheck").text("일치");
				 isPwOk=true;
			}else{
				 console.log("password 불일치");
				 $("#passwordCheck").text("불일치");
				 isPwOk=false;
			}
			
		};
	
		function nameCheck() {
			console.log($("#name").val().trim().length);
			if( $("#name").val().trim().length > 0 && $("#name").val().trim().length < 9 ){
				 isNameOk=true;
				 $("#NameCheck").text("사용 가능한 이름입니다.");
			}else {
				 $("#NameCheck").text("이름은 8글자 이하입니다.");
				 isNameOk=false;
			}
		};
	</script>
</body>
</html>