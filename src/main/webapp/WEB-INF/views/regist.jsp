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

	<div class="site-blocks-cover inner-page-cover overlay"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/top.jpg);"
		data-aos="fade" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row align-items-center justify-content-center text-center">

				<div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
					<h1
						class="text-white font-weight-light text-uppercase font-weight-bold">회원가입</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<%----------                          
	<div class="container">
    <h3>회원가입 폼 입니다.</h3>
    <form action="/ajax/signup" method="post" id="myForm">
        <div class="form-group has-feedback">
            <label class="control-label" for="id">아이디</label>
            <input class="form-control" type="text" name="id" id="id"/>
            <span id="overlapErr" class="help-block">사용할 수 없는 아이디 입니다.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="pwd">비밀번호</label>
            <input class="form-control" type="password" name="pwd" id="pwd"/>
            <span id="pwdRegErr" class="help-block">8글자 이상 입력하세요.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="rePwd">비밀번호 재확인</label>
            <input class="form-control" type="password" name="rePwd" id="rePwd"/>
            <span id="rePwdErr" class="help-block">비밀번호와 일치하지 않습니다. 다시 입력해 주세요.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <label class="control-label" for="email">이메일</label>
            <input class="form-control" type="text" name="email" id="email"/>
            <span id="emailErr" class="help-block">올바른 이메일 형식이 아닙니다. 다시 입력해 주세요.</span>
            <span class="glyphicon glyphicon-ok form-control-feedback"></span>
        </div>
        <button class="btn btn-success" type="submit">가입</button>
    </form>
</div>
	
              
	--------%>




	<!-- 회원가입 -->

	<div class="site-section block-13">

		<!--Body-->
		<div class="modal-body" align="center">
			<form action="" method="post" id="regist_form" style="width: 400px">

				<c:if test="${ login_sns_member != null }" var="r">
					<h3>소셜 로그인을 통한 회원가입</h3>
					<input type="hidden" name="member_id"
						value="${ login_sns_member.member_id }">
					<input type="hidden" name="password"
						value="${ login_sns_member.password }">
					<input type="hidden" name="member_type"
						value="${ login_sns_member.member_type }">

					<div class="mb-3" align="left">
						<label for="nickname">*이름 </label> <input type="text"
							class="form-control" id="name" name="name" placeholder="Name"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]/g,''); javascript: nameCheck();">
						<div class="invalid-feedback">X</div>
						<div id="NameCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="nickname">*별명 </label> <input type="text"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]/g,'');"
							class="form-control" id="nickname" name="nickname" maxlength="8"
							placeholder="NickName">
						<div class="invalid-feedback">X</div>
						<div id="NickCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="tel">전화번호 </label> <input type="text"
							class="form-control" id="tel" name="tel" maxlength="11"
							placeholder="'-' 없이 입력">
						<div class="invalid-feedback">X</div>
					</div>

					<div class="mb-3" align="left">
						<label for="address">주소 </label><br> <input type="button"
							onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" class="form-control" id="sample6_postcode"
							name="address_post" placeholder="우편번호"> <input
							type="text" class="form-control" id="sample6_address"
							name="address_basic" placeholder="주소"> <input type="text"
							class="form-control" id="sample6_detailAddress"
							name="address_detail" placeholder="상세주소"><br> <input
							type="text" class="form-control" id="sample6_extraAddress"
							placeholder="참고항목">
						<div class="invalid-feedback">X</div>
					</div>


					<!-- 주소검색 API -->
					<%-- 
				<input type="text" id="sample6_postcode" placeholder="우편번호">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample6_address" placeholder="주소"><br>
				<input type="text" id="sample6_detailAddress" placeholder="상세주소">
				<input type="text" id="sample6_extraAddress" placeholder="참고항목">
				 --%>

					<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
					<script>
				    function sample6_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
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
				                if(data.userSelectedType === 'R'){
				                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
				                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				                        extraAddr += data.bname;
				                    }
				                    // 건물명이 있고, 공동주택일 경우 추가한다.
				                    if(data.buildingName !== '' && data.apartment === 'Y'){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				                    if(extraAddr !== ''){
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
				                document.getElementById("sample6_detailAddress").focus();
				            }
				        }).open();
				    }
				</script>

					<div class="mb-3" align="left">
						<label for="interest">*관심분야 </label>
						<div class="row" align="left">
							<div class="col-md-5">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address1" value="운동"> <label
										class="custom-control-label" for="same-address1">운동</label>
								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address2" value="식이요법"> <label
										class="custom-control-label" for="same-address2">식이요법</label>
									<div class="invalid-feedback">X</div>

								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address3" value="운동기구"> <label
										class="custom-control-label" for="same-address3">운동기구</label>
									<div class="invalid-feedback">X</div>
								</div>
							</div>

							<div class="col-md-5">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address5" value="운동"> <label
										class="custom-control-label" for="same-address5">운동</label>
								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address6" value="식이요법"> <label
										class="custom-control-label" for="same-address6">식이요법</label>
									<div class="invalid-feedback">X</div>

								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address7" value="운동기구"> <label
										class="custom-control-label" for="same-address7">운동기구</label>
									<div class="invalid-feedback">X</div>
								</div>
							</div>
						</div>
					</div>
				</c:if>

				<c:if test="${ not r }">
					<h3> 일반 회원가입</h3>
					
					<hr />
					
					<h3>1 정보 입력</h3>
					<!-- --------------regist123------------- -->

					<input type="hidden" name="member_type" value="0">

					<div class="mb-3" align="left">
						<label for="member_id">*ID </label> <input type="text"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');"
							class="form-control" id="member_id" name="member_id"
							maxlength="20" placeholder="영문 +숫자  최소  6자 ~최대 20자 (중복X)">
						<div class="invalid-feedback">X</div>
						<div id="IDCheck"></div>

					</div>



					<div class="mb-3" align="left">
						<label for="password">*비밀번호 </label> <input type="password"
							class="form-control" name="password" id="password"
							onkeyup="passwordCheck()" maxlength="20"
							placeholder="특수문자,영문 ,숫자  최소 6자 ~최대 20자">
						<div class="invalid-feedback">X</div>

					</div>

					<div class="mb-3" align="left">
						<label for="reenter_password">*비밀번호 확인 </label> <input
							type="password" class="form-control" id="reenter_password"
							name="reenter_password" maxlength="20" onkeyup="passwordCheck()"
							placeholder="위의 password와 같이 입력">
						<div class="invalid-feedback">X</div>
						<div id="passwordCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="name">*이름 </label> <input type="text" maxlength="8"
							class="form-control" id="name" name="name" onkeyup="nameCheck()"
							placeholder="Name">
						<div class="invalid-feedback">X</div>
						<div id="NameCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="nickname">*별명 </label> <input type="text"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]/g,'');"
							class="form-control" id="nickname" name="nickname" maxlength="8"
							placeholder="한글,영문 ,숫자(중복X) 최소 3자 ~최대 10자 ">
						<div class="invalid-feedback">X</div>
						<div id="NickCheck"></div>
					</div>

					<div class="mb-3" align="left">
						<label for="tel">전화번호 </label> <input type="text"
							onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"
							class="form-control" id="tel" name="tel" maxlength="11"
							placeholder="'-' 없이 입력">
						<div class="invalid-feedback">X</div>
					</div>

					<div class="mb-3" align="left">
						<label for="address">주소 </label><br> <input type="button"
							onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" class="form-control" id="sample6_postcode"
							name="address_post" placeholder="우편번호"> <input
							type="text" class="form-control" id="sample6_address"
							name="address_basic" placeholder="주소"> <input type="text"
							class="form-control" id="sample6_detailAddress"
							name="address_detail" placeholder="상세주소"><br> <input
							type="text" class="form-control" id="sample6_extraAddress"
							placeholder="참고항목">
						<div class="invalid-feedback">X</div>
					</div>


					<!-- 주소검색 API -->
					<%-- 
				<input type="text" id="sample6_postcode" placeholder="우편번호">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample6_address" placeholder="주소"><br>
				<input type="text" id="sample6_detailAddress" placeholder="상세주소">
				<input type="text" id="sample6_extraAddress" placeholder="참고항목">
				 --%>

					<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
					<script>
				    function sample6_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
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
				                if(data.userSelectedType === 'R'){
				                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
				                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				                        extraAddr += data.bname;
				                    }
				                    // 건물명이 있고, 공동주택일 경우 추가한다.
				                    if(data.buildingName !== '' && data.apartment === 'Y'){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				                    if(extraAddr !== ''){
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
				                document.getElementById("sample6_detailAddress").focus();
				            }
				        }).open();
				    }
				</script>




					<div class="mb-3" align="left">
						<label for="interest">*관심분야 </label>
						<div class="row" align="left">
							<div class="col-md-5">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address1" value="웨이트 트레이닝"> <label
										class="custom-control-label" for="same-address1">웨이트 트레이닝</label>
								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address2" value="다이어트"> <label
										class="custom-control-label" for="same-address2">다이어트</label>
									<div class="invalid-feedback">X</div>

								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address3" value="요가"> <label
										class="custom-control-label" for="same-address3">요가</label>
									<div class="invalid-feedback">X</div>
								</div>
							</div>

							<div class="col-md-5">
								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address5" value="필라테스"> <label
										class="custom-control-label" for="same-address5">필라테스</label>
								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address6" value="스피닝"> <label
										class="custom-control-label" for="same-address6">스피닝</label>
									<div class="invalid-feedback">X</div>

								</div>

								<div class="custom-control custom-checkbox">
									<input type="checkbox" class="custom-control-input"
										name="interest" id="same-address7" value="식이요법"> <label
										class="custom-control-label" for="same-address7">식이요법</label>
									<div class="invalid-feedback">X</div>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				
				<hr />
				
<!--회원 약관 시작-->

	
				
				<div class="terms-agree">
					<div class="inner">
            <!-- [D] 이미지 변경 726.2014-11-13 -->
            <h3><img src="https://pics.auction.co.kr/join/h3_tit_terms_agree02.gif" alt="약관동의"></h3>
						 <div class="terms-cont">
							
							<div class="all-agree">
								<label for="cb_agreeall_1"><input type="checkbox" id="cb_agreeall_1" name="cb_agreeall_1" onclick="toggleAllProvisionsCheck();" /> The Glasses 가입 전체약관 및 마케팅정보 수신에 동의합니다.</label>							
							</div>

							<div class="terms-cont-inner">
								<div class="check-set">
										<label for="cbProvision1"><input type="checkbox" id="cbProvision1" name="cbProvision1" onclick="provisionCheck();"/><span class="type01">(필수)</span>The Glasses 이용약관</label>
										<a target="_blank" class="txt-view-all"  href="<%= request.getContextPath() %>/regist/provision">전체보기</a>							
								</div>		
								
								
								<div class="check-set">
									<label for="cbIndividualInfo"><input name="cbIndividualInfo" type="checkbox" id="cbIndividualInfo" onclick="individualCheck();" /><span class="type01">(필수)</span>개인정보 수집 및 이용</label>
									<a href="#" class="txt-view-all" onClick="window.open(
									'<%= request.getContextPath() %>/regist/individual', 'CLIENT_WINDOW', 'resizable=yes scrollbars=yes')">내용보기</a>								
								</div>					

								<div class="check-set" >
									<label for="cbFinance"><input name="cbFinance" type="checkbox" id="cbFinance" onclick="financeCheck();" /><span class="type01">(필수)</span>전자금융거래 이용약관</label>					
									<a target="_blank" class="txt-view-all" href="<%= request.getContextPath() %>/regist/finance">전체보기</a>
								</div>

								
								

								<div class="check-set">
									
									<label for="cbThirdParty"><input name="cbThirdParty" type="checkbox" id="cbThirdParty" onclick="ConfirmProvision(this);" /><span class="type02">(선택)</span>개인정보 제3자(판매자) 제공</label>
									<a href="#" class="txt-view-all" onClick="window.open(
									'<%= request.getContextPath() %>/regist/thirdparty', 'CLIENT_WINDOW', 'resizable=yes scrollbars=yes')">내용보기</a>						
								</div>

								
								<div class="check-set"  class="terms-box provision3">
									<label for="cbIndividualInfo_option"><input name="cbIndividualInfo_option" type="checkbox" id="cbIndividualInfo_option" onclick="CheckIndividualInfoOptions(this); ConfirmProvision(this);" /><span class="type02">(선택)</span>개인정보 수집 및 이용</label>
									<a href="#" class="txt-view-all" onClick="window.open(
									'<%= request.getContextPath() %>/regist/individual_option', 'CLIENT_WINDOW', 'resizable=yes scrollbars=yes')">내용보기</a>						
								</div>
														
								<div class="check-set">
									<label for="cbLocation"><input name="cbLocation" type="checkbox" id="cbLocation" onclick="ConfirmProvision(this);" /><span class="type02">(선택)</span>위치정보 이용약관</label>									
									<a target="_blank" class="txt-view-all" href="<%= request.getContextPath() %>/regist/location">전체보기</a>								
								</div>
								
								<div class="check-set">
									<label for="sms_yn"><input name="sms_yn" type="checkbox" id="sms_yn" onclick="ConfirmProvision(this);" /><span class="type02">(선택)</span>이벤트/쇼핑정보 SMS수신동의</label>
								</div>
								<div class="check-set">
									<label for="email_yn"><input name="email_yn" type="checkbox" id="email_yn" onclick="ConfirmProvision(this);" /><span class="type02">(선택)</span>이벤트/쇼핑정보 이메일수신동의</label>
								</div>
							</div>												
						</div>
					</div>
				</div>
				<div class="use-notice">
					<p>7세 이상 14세 미만 회원은 회원가입 후 모바일에서 본인인증, 법정대리인 인증을 진행하면 서비스를 이용하실 수 있습니다.</p>
					<p>선택약관에 동의하지 않아도 회원가입은 가능합니다.</p>
					<p class="notice_tit">The Glasses  약관과 개인정보 수집 및 이용을 확인하였으며 이에 동의하십니까?</p>
				</div>
				
				
				<div class="text-center form-sm mt-2">
					<button class="btn btn-info" type="button" id="member_regist">
						동의 하고 회원 가입 <i class="fas fa-sign-in ml-1"></i>
					</button>
					<button class="btn btn-info" type="reset" id="">
						취소 <i class="fas fa-sign-in ml-1"></i>
					</button>
				</div>
				
				<hr />
				
		
				<div  style="overflow:scroll; width:450px; height:200px;" class="terms-agree-notice">
					<div class="inner">
					<!-- [D] 이미지 변경 726.2014-11-13 -->
						<h3><img src="https://pics.auction.co.kr/join/h3_tit_terms_kcc.gif" alt="약관 중요사항 고지"></h3>
						<div class="terms-cont-info">
								<div class="terms-cont-inner">
									<div class="check-set">
										<p class="tit"><span class="type01">(필수)</span>The Glasses  이용약관</p>
										<div style="display:block;" class="terms-box">
											<div class="termsofuse">
												<p>1. 회원에 대한 통지는 회원정보에 기재된 주소 또는 e-mail주소에 도달함으로써 통지된 것으로 보며, 정보 수정/미수정으로 인한 책임은 회원이 부담함. (제12조)</p>
												<p>2. 회원자격이 정지되거나 경매서비스(일반경매, 즉시구매, 고정가판매 및 공동경매 포함)의 이용 등이 제한될 수 있음. (제15조)</p>
												<p>3. 구매자는 물품 수령 후 7일 이내 물품의 반품 또는 교환 요청 가능. (제26조 마항)</p>
												<p>4. 판매예치금에서 회사에 대한 채무금을 우선 출금 및 구매자의 정당한 요청에 판매예치금에서 출금하여 구매자에게 환불 가능. (제27조의2)</p>
												<p>5. 서비스 제공 대가로 등록서비스이용료, 유료부가서비스이용료, 판매서비스이용료 등을 부과할 수 있음. (제30조)</p>
												<p>6. The Glasses 은 통신판매중개자로서 회원 상호간의 거래에 관여하지 않으며 어떠한 보증 및 책임도 부담하지 않음. (제16조, 제31조)</p>
										</div>
									</div>
									<div class="check-set">
										<p class="tit"><span class="type01">(필수)</span>전자금융거래 이용약관</p>
										<div style="display:block;" class="terms-box">
											<div class="termsofuse">
												<p>1. 접근매체의 양도 &middot; 양수, 대여 &middot; 사용위임, 질권설정 기타 담보 제공 및 이의 알선과 접근매체를 제3자에게 누설 &middot; 노출, 방치하는 것은 금지됨. (제17조, 제21조, 제23조)</p>
												<p>2. 소비자가 재화 등을 공급받은 날부터 3영업일이 지나도록 정당한 사유의 제시 없이 그 공급받은 사실을 통보하지 않는 경우 소비자의 동의 없이 판매자에게 결제대금을 지급할 수 있으며, 회사가 결제대금을 지급하기 전에 소비자가 그 결제대금을 환급 받을 사유가 발생한 경우 이를 소비자에게 환급함. (제19조)</p>
												<p>3. 이용자의 선불전자지급수단 잔액이 구매 취소 등의 사유 발생으로 회사가 이용자로부터 환수해야 하는 환수대상액보다 작을 경우 회사는 당해 이용자의 선불전자지급수단을 마이너스로 처리할 수 있음. (제27조)</p>
											</div>
										</div>
									</div>
									<div class="check-set">
										<p class="tit"><span class="type01">(필수)</span>개인정보 수집 및 이용</p>
										<div style="display:block;" class="terms-box">
											<div class="termsofuse">
												<table class="tbl">
												<colgroup>
													<col width="50%">
													<col>
												</colgroup> 
												<caption>개인정보 수집 이용동의</caption>	
												<thead>
													<tr>
													<th scope="col">목적</th>
													<th scope="col">항목</th>
													</tr>
												</thead>
												<tfoot>
													<tr>
													<th scope="row" colspan="2" class="imp_txt"><strong>보유기간 : 회원탈퇴 후 5일 이내 또는 법령에 따른 보존기간</strong></th>
													</tr>
												</tfoot>
												<tbody>
													<tr> 
														<td>본인여부 확인</td> 
														<td>이름, 아이디, 비밀번호, 휴대폰번호, 이메일주소</td> 
													</tr>
													<tr> 
														<td>계약이행 및 약관변경 등의 고지를 위한 연락, 본인의사확인<br />및 민원 등의 고객불만처리
														</td> 
														<td>이름, 아이디, 휴대폰번호, 이메일주소, 전화번호, 주소</td> 
													</tr>
													<tr> 
														<td>부정이용방지, 비인가사용방지, 서비스 제공 및 계약의 이행</td> 
														<td>방문일시, 서비스 이용 기록 및 기기정보</td> 
													</tr>
													<tr> 
														<td>부정거래의 배제 (가입 후 부정거래가 확인된 경우만)</td> 
														<td>ID, 휴대폰번호, 이메일주소, 전화번호, 생년월일, 부정거래<br />사유, 탈퇴 시 회원 상태값</td> 
													</tr>
												</tbody>
											</table>
											</div>
										</div>
									</div> 
									<div class="check-set">
										<p class="tit"><span class="type02">(선택)</span>위치정보 이용약관</p>
										<div style="display:block;" class="terms-box">
											<div class="termsofuse">
												<p>1. 회원의 현 위치를 이용한 쇼핑정보를 제시하는 서비스(위치기반서비스)를 제공하며, 해당 정보의 진실성, 타당성, 사실 부합성 또는 객관성 등에 관하여 일체의 책임 부인. (제5조)</p>
												<p>2. 위치정보 이용 &middot; 제공 &middot; 사실 확인자료를 자동으로 1년간 기록 &middot; 보존함. 위치정보 수집 시 제공받는자, 제공일지 및 제공목적을 통보함. (제6조)</p>
												<p>3. 회원은 위치정보 제3자 제공에 대한 동의 철회 가능하며, 이 경우 회사는 확인자료를 파기함 (제7조)</p>
											</div>
										</div>
									</div>
									<div class="check-set">
										<p class="tit"><span class="type02">(선택)</span>개인정보 제3자(판매자) 제공</p>
										<div style="display:block;" class="terms-box">
											<div class="termsofuse">
												<table class="tbl">
												<colgroup>
													<col width="72px" />
													<col />
													<col />
												</colgroup>
												<caption>개인정보 수집 이용동의</caption>	
												<thead>
													<tr>
													<th scope="col">제공받는자</th>
													<th scope="col">목적</th>
													<th scope="col">항목</th>
													</tr>
												</thead>
												<tfoot>
													<tr>
													<th scope="row" colspan="3" class="imp_txt"><strong>보유기간 : 배송완료 후 한달</strong></th>
													</tr>
												</tfoot>
												<tbody>
													<tr> 
														<th class="imp_txt"><strong>판매자</strong></th>
														<td class="imp_txt"><strong>판매자와 구매자의 거래의 원활한 진행, <br />본인의사의 확인, 고객 상담 및 불만처리, <br />상품과 경품 배송을 위한 배송지 확인 등</strong></td> 
														<td>구매자 이름, 전화번호, ID, 휴대폰번호, 이메일주소, <br />상품 구매정보, 상품 수취인 정보(이름, 주소, 전화번호)</td> 
													</tr>
												</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="check-set">
										<p class="tit"><span class="type02">(선택)</span>개인정보 수집 및 이용</p>
										<div style="display:block;" class="terms-box">
											<div class="termsofuse">
												<table class="tbl">
												<colgroup>
													<col width="72px" />
													<col />
													<col />
												</colgroup>
												<caption>개인정보 수집 이용동의</caption>	
												<thead>
													<tr>
													<th scope="col">동의</th>
													<th scope="col">목적</th>
													<th scope="col">항목</th>
													</tr>
												</thead>
												<tfoot>
													<tr>
													<th scope="row" colspan="3" class="imp_txt"><strong>보유기간 : 회원탈퇴 후 5일 이내 또는 법령에 따른 보존기간</strong></th>
													</tr>
												</tfoot>
												<tbody>
													<tr> 
														<th><input name="cbIndividualInfo_option1" type="checkbox" id="cbIndividualInfo_option1" onclick="CheckIndividualInfoOptions(this); ConfirmProvision(this);" /></th>
														<td>주문, 결제 및 배송 서비스</td> 
														<td>구매자정보, 상품 구매/취소/반품/교환/환불 정보, 수<br />령인 정보, 결제정보, 송장정보, 은행계좌정보, 휴대폰<br />번호(휴대폰 결제 시), 현금영수증정보</td> 
													</tr>
													<tr> 
														<th><input name="cbIndividualInfo_option2" type="checkbox" id="cbIndividualInfo_option2" onclick="CheckIndividualInfoOptions(this); ConfirmProvision(this);" /></th>
														<td>신규 서비스 개발, 맞춤 서비스 제공 및 마케팅, 서비<br />스 이용 통계 및 설문</td> 
														<td>성별, 생년월일, 휴대폰번호, 전화번호, 이메일주소</td> 
													</tr>
												</tbody>
												</table>
											</div>
										</div>
									</div>									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div><!--min-width -->
		</div><!--wrap -->
				
				
				
				
				
				
<!--회원 약관 끝-->

<hr />


		</div>
		<!--Footer-->
		<div class="modal-footer align="center">
			<div class=" ">
				<p class="pt-1" ">
					이미 아이디가 있습니까? <a href="#" class="blue-text">로그인 하기</a>
				</p>
			</div>
		</div>
	</div>
	<!-- 회원가입 -->

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>

	<script type="text/javascript">

		var isIdOk = false;
		var isPwOk = false;
		var isNickOk = false;
		var isNameOk = false;
		var isInterestOk = false;
		//약관 체크 다했는지 확인
		var isToggleAllProvisions = false;
		var isProvision = false;
		var isFinance = false;
		var isIndividual = false;
		var checkResult = false;
		
		
		var member_id = '';
		var nickname = '';
		
		var login_sns_member = '<%= session.getAttribute("login_sns_member") %>';
		
		if( login_sns_member != 'null' ) {
			isIdOk = true;
			isPwOk = true;
		}
		
		console.log($("input:checkbox[name='interest']").is(":checked"));

		
		// 아이디 체크
		$("#member_id").on("keyup", function() {
			member_id = $("#member_id").val().trim();
			if(member_id.length > 5){
				var params=$("#regist_form").serialize();
				$.ajax({
		            url: "<%=request.getContextPath()%>/idCheck",
		            type: "POST",
		            data: params,
		            success: function(data){
		            	if(data == 'idOk'){
			                $("#IDCheck").text('가입 가능한 아이디');
		            		isIdOk = true;
		            	} else if (data == 'idNo'){
			                $("#IDCheck").text('중복된 아이디');
			                isIdOk = false;
		            	} 
		            },
		            error: function(){
		            	console.log("오류 발생");
		            }
		        });
			} else {
				isIdOk = false;
				$("#IDCheck").text('아이디는 6자 이상 20자 미만입니다.');
			}
		});
		
		// 닉네임
		$("#nickname").on("keyup", function() {
			nickname = $("#nickname").val().trim();
			if(nickname.length > 2){
				var params=$("#regist_form").serialize();
				$.ajax({
		            url: "<%=request.getContextPath()%>/nickCheck",
		            type: "POST",
		            data: params,
		            success: function(data){
		            	if(data == 'nickOk'){
			                $("#NickCheck").text('가입 가능한 별명');
		            		isNickOk = true;
		            	} else if (data == 'nickNo'){
			                $("#NickCheck").text('중복된 별명');
			                isNickOk = false;
		            	} 
		            },
		            error: function(){
		            	console.log("오류 발생");
		            }
		        });
			} else {
				isNickOk = false;
				$("#NickCheck").text('별명은 3자 이상 10자 미만입니다.');
			}
			
			
		});
		
		// 비밀번호 일치
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
		
		// 이름 입력
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
		
		

		

		function toggleAllProvisionsCheck() {
            if ($("#cb_agreeall_1").is(':checked')) {
            	isToggleAllProvisions = true;
                $("input[name=cbProvision1]").prop("checked", true);
                $("input[name=cbFinance]").prop("checked", true);
                $("input[name=cbIndividualInfo]").prop("checked", true);
                $("input[name=cbThirdParty]").prop("checked", true);
                $("input[name=cbIndividualInfo_option]").prop("checked", true);
                $("input[name=cbLocation]").prop("checked", true);
                $("input[name=sms_yn]").prop("checked", true);
                $("input[name=email_yn]").prop("checked", true);
                $("input[name=cbIndividualInfo_option1]").prop("checked", true);
                $("input[name=cbIndividualInfo_option2]").prop("checked", true);
            } else {
            	isToggleAllProvisions = false;
                $("input[type=checkbox]").prop("checked", false);
            }
        }
		
			
			function provisionCheck() {
				if(document.getElementById("cbProvision1").checked) {
					isProvision = true;
				} else {
					isProvision = false;
					alert("The Glasses 이용약관에 동의하지 않으셨습니다.");
				}
			}
			
			function financeCheck() {
				if(document.getElementById("cbFinance").checked) {
					isFinance = true;
				} else {
					isFinance = false;
					alert("The Glasses 전자금융서비스 이용약관에 동의하지 않으셨습니다.");
				}
			}
			
			function individualCheck() {
				if(document.getElementById("cbIndividualInfo").checked) {
					isIndividual = true;
				} else {
					isIndividual = false;
					alert("개인정보 수집 및 이용약관에 동의하지 않으셨습니다.");
				}
			}
		
		// 회원가입버튼
		$("#member_regist").on("click", function() {
			if( $("input:checkbox[name='interest']").is(":checked") == false ){	
				alert("관심사는 최소 1개 이상 체크해야 합니다.");
				console.log($("input:checkbox[name='interest']").is(":checked"));
				isInterestOk = false;
			} else {
				isInterestOk = true;
				console.log($("input:checkbox[name='interest']").is(":checked"));
			}
			
			checkResult = isProvision && isIndividual && isFinance;
			alert( "회원가입 검증 결과 : " + 
					"isIdOk : " + isIdOk + ", isPwOk : " + isPwOk + ", isNickOk : " + isNickOk + ", isNameOk : " + isNameOk + ", isInterestOk : " + isInterestOk + ", checkResult : " + checkResult);
			
			var result = isIdOk && isPwOk && isNickOk && isNameOk && isInterestOk && checkResult || isToggleAllProvisions; 
			console.log('result : ' + result);
			if( result == true ) {
				var params=$("#regist_form").serialize();
				$.ajax({
		            url: "<%=request.getContextPath()%>/regist",
		            type: "POST",
		            data: params,
		            success: function(data){
		                console.log(data);
		                alert(data);
						location.href='<%=request.getContextPath()%>/home'
						},
					error : function() {
								alert("err");
						}
				});
			}
		});
	</script>
	
 <jsp:include page="site_footer.jsp"></jsp:include>
	

</body>
</html>
<%--
			$("#member_regist").on("click", function() {
				var params=$("#regist_form").serialize();
				var member_id = $("#member_id").val().trim();
				var password = $("#password").val().trim();
				var interest = $("input:checkbox[name='interest']").is(":checked")
				console.log(params);
				console.log(interest);
				
				if( member_id.length > 5 && password.length > 5 ){
					//////// 아이디 password 체크구간
					$.ajax({
			            url: "<%=request.getContextPath()%>/registCheck",
			            type: "POST",
			            data: params,
			            success: function(data){
			                console.log(data);
			                alert(data);
							
			            },
			            error: function(){
			                alert("err");
			            }
			        });
				}
					
					if( interest == false ){	
						alert("관심사는 최소 1개 이상 체크해야 합니다.");
						
						} else {
						if( $("#password").val() == $("#reenter_password").val() ){
							$.ajax({
					            url: "<%=request.getContextPath()%>/regist",
					            type: "POST",
					            data: params,
					            success: function(data){
					                console.log(data);
					                alert(data);
					                location.href="<%=request.getContextPath()%>/index";
					            },
					            error: function(){
					                alert("다시 확인 해주세요");
					            }
					            
					        });
						} else {
							 alert("password가 틀림1");
							}
						}
						
						} else {
							 alert("ID 또는 password 6자 이상 입니다.");
						}
				
					////////////관심사 체크 구간
				--%>




<%-- 
	<script type="text/javascript">
		function passwordCheck() {
			if( $("#password").val() == $("#reenter_password").val() ){
				 console.log("일치");
				 $("#passwordCheck").text("일치");
			}else{
				 console.log("password 불일치");
				 $("#passwordCheck").text("불일치");
			}
			
		}
		$(document).ready(function() {
			$("#member_id").on("keyup", function() {
			
				var params=$("#regist_form").serialize();
				$.ajax({
		            url: "<%=request.getContextPath()%>/registCheck",
		            type: "POST",
		            data: params,
		            
		            success: function(data){
		            	
		            	if(data == '아이디 중복'){
			                $("#IDCheck").text(data);
		            	} 
		            	console.log(data);
		            	if( data == '닉네임 중복'){
			                $("#NickCheck").text(data);
		            	}
		                
						
		            },
		            error: function(){
		            	console.log("오류 발생");
		            }
		        });
		
			})
		})
		
		</script>
	--%>





















