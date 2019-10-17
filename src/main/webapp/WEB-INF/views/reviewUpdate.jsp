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

	<div class="site-section block-13" align="center" style="padding-bottom: 10; margin-left: 10%; margin-right: 10%; margin-top: 130;">
		<form action="<%= request.getContextPath() %>/update_review/${searchedReview.board_id}" method="post" enctype="multipart/form-data">
				<table style="width: 650px;">
				<tr>
					<th> <label for="nickname">작성자</label><input type="text" name="nickname" readonly="readonly" value="${ searchedReview.nickname }"> </th>
					<th> <input type="hidden" name="member_id" readonly="readonly" value="${ searchedReview.member_id }"> </th>
				</tr>
				<tr>
					<th> <label for="title">제목</label><input type="text" id="title" name="title" style="width: 100%;" value="${ searchedReview.title }"></th>
					<th>
					<div class="btn-group">
					  <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
					    <span class="caret">카테고리</span>
					  </button>
					  <ul class="dropdown-menu" role="menu">
					    <li value="2" onclick="adjustCategory('2');">상품</li>
					    <li value="3" onclick="adjustCategory('3');">피트니스</li>
					    <li value="4" onclick="adjustCategory('4');">장소</li>
					    <li value="5" onclick="adjustCategory('5');">다이어트</li>
					    <li value="6" onclick="adjustCategory('6');">웨이트 트레이닝</li>
					    <li value="7" onclick="adjustCategory('7');">레시피</li>
					  </ul>
					</div>
					<input type="hidden" id="category" name="category" value="${ searchedReview.category }">
					</th>
				</tr>
				<tr>
					<td colspan="2"><textarea id="content" name="content" cols="60" rows="15">${searchedReview.content }</textarea></td>
		     	</tr>
		     	<tr>
		     		<td>
		     		<div class="form-group">
						<label for="image">이미지 업로드</label>
						<input type="file" name="file">
						<p>*미 입력시 기존의 이미지 사용*</p>
					</div>
					</td>
		     	</tr>
		     	<tr>
		     		<td>
		     		지도를 첨부하려면 아래 양식을 작성하세요
		     		</td>
		     	</tr>
		     	<tr>
		     	<td>
			     	<div class="mb-3" align="left">
							<label for="address">주소 </label><br> <input type="button"
								onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
							<input type="text" class="form-control" id="sample6_postcode"
								name="address_post" placeholder="우편번호"> <input
								type="text" class="form-control" id="sample6_address"
								name="address_basic" placeholder="주소" value="${searchedReview.selectedAddress }"> <input type="text"
								class="form-control" id="sample6_detailAddress"
								name="address_detail" placeholder="상세주소">
							<input
							type="text" class="form-control" id="sample6_extraAddress"
							placeholder="참고항목">
							<input type="button" id="addMapBtn" name="addMapBtn" value="지도 갱신" onclick="refreshMap();"></input>
							<input type="button" id="resetMapBtn" name="resetMapBtn" value="지도 정보 초기화" onclick="resetMap();"></input>
					</div>
					<div id="map" style="width: 500px; height: 400px;" align="left">
						<!-- 지도 첨부 영역입니다. -->
					</div>
		     	</td>
		     	</tr>
		     	<tr>
		     		<td><input type="hidden" id="selectedAddress" name="selectedAddress" value="${searchedReview.selectedAddress }"></td>
		     		<td><input type="hidden" id="selectedLat" name="selectedLat" value="${ searchedReview.selectedLat }"></td>
		     		<td><input type="hidden" id="selectedLng" name="selectedLng" value="${ searchedReview.selectedLng }"></td>
		     	</tr>
		     	<tr>
		     		<td><input type="button" id="updateBoard" name="updateBoard" value="수정완료"></td>
		     	</tr> 
				</table>
		</form>
	</div>
	


	<jsp:include page="javascriptIncludeForReview.jsp" flush="false"></jsp:include>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f7e87dcf28984113f6360f591c4d3f24&libraries=services,clustere"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script type="text/javascript">
		var address = ''; // 폼에 작성된 주소를 가져온다.
		var geocoder = new kakao.maps.services.Geocoder();
		var selectedAddress_js = null; // 지도에서 선택한 위치의 주소를 저장할 변수
		var selectedLat = null; // 주소 검색으로 검색된 주소의 위도
		var selectedLng = null; // 주소 검색으로 검색된 주소의 경도
	
	
		var container = document.getElementById('map');
		// 아래 options에서 center는 지도를 생성하는데 필수이다.
		var options = {
				center: new kakao.maps.LatLng('${searchedReview.selectedLat}', '${searchedReview.selectedLng}'),
				level: 3 // 지도의 레벨(확대, 축소의 정도)
		};
		var map = new kakao.maps.Map(container, options); // 지도의 생성 및 객체 리턴
		
		
		// 지도를 클릭한 위치에 마커를 생성
		var marker = new kakao.maps.Marker({
			// 중심 좌표에 마커를 생성
			position: map.getCenter()
		});
		marker.setMap(map);
		// 마커에 인포 윈도우를 표시하기.
		// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		var iwContent = '<div style="padding:5px; font-size:11px;">Hello World! <br><a href="https://map.kakao.com/link/map/Hello World!,'+${searchedReview.selectedLat}+','+${searchedReview.selectedLng}+'" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,'+${searchedReview.selectedLat}+','+${searchedReview.selectedLng}+'" style="color:blue" target="_blank">길찾기</a></div>',
						iwPosition = new kakao.maps.LatLng('${searchedReview.selectedLat}', '${searchedReview.selectedLng}'); //인포윈도우 표시 위치입니다
		// 1. 인포 윈도우 생성
		var infowindow = new kakao.maps.InfoWindow({
			position: iwPosition,
			content: iwContent
		});
		// 2. 마커 위에 인포윈도우를 표시. 두번째 파라메터인 marker를 넣어주지 않으면 그냥 지도위에 표시됨.
		infowindow.open(map, marker);
		
		
		// 지도에 클릭 이벤트 부여하여 클릭한 곳의 위도, 경도를 출력
		kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
			// 바로 기존에 있던 인포윈도우를 지운다.
			infowindow.close(map, marker);
			// 클릭한 위도, 경도의 정보를 저장
			var latlng = mouseEvent.latLng;
			alert('위도 : ' + latlng.getLat() + '\n경도 : ' + latlng.getLng());
			// 클릭한 위치로 마커를 옮긴다.
			marker.setPosition(latlng);
			
			geocoder.coord2Address(latlng.getLng(), latlng.getLat(), function(result, status) {
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
			    	alert(result[0].address.address_name);
			    	selectedAddress_js = result[0].address.address_name;
			    	selectedLat = latlng.getLat();
			    	selectedLng = latlng.getLng();
			    	$("#sample6_address").val(selectedAddress_js);
			    	$("#selectedAddress").val(selectedAddress_js);
			    	$("#selectedLat").val(selectedLat);
			    	$("#selectedLng").val(selectedLng);
			    } else {
			    	alert("주소 정보를 정확히 가져올 수 없는 장소입니다.");
			    	$("#selectedAddress").val(0);
				    $("#selectedLat").val(0);
				    $("#selectedLng").val(0);
			     }
			});
			
			
			iwContent = '<div style="padding:5px; font-size:11px;">'+selectedAddress_js+'<br><a href="https://map.kakao.com/link/map/'+selectedAddress_js+',' 
				+ latlng.getLat() + ',' + latlng.getLng() + '" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/'+selectedAddress_js+','
				+ latlng.getLat() + ',' + latlng.getLng() + 'style="color:blue" target="_blank">길찾기</a></div>',
						iwPosition = new kakao.maps.LatLng(latlng.getLat(), latlng.getLng()); //인포윈도우 표시 위치입니다
			
			infowindow = new kakao.maps.InfoWindow({
				position: iwPosition,
				content: iwContent
			});
			// 2. 마커 위에 인포윈도우를 표시.
			infowindow.open(map, marker);
		});
		
		
		// 지도 갱신 버튼을 눌렀을 때 주소 폼에 적힌 값을 기준으로 위도 경도 가져오는 함수
		function refreshMap() {
			address = document.getElementById("sample6_address");
			var target = address.value;
			selectedAddress = target;
			geocoder.addressSearch(target, function(result, status) {
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
			    	infowindow.close(map, marker);
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
					selectedLat = coords.getLat();
					selectedLng = coords.getLng();
					$("#selectedAddress").val(selectedAddress_js);
			    	$("#selectedLat").val(selectedLat);
			    	$("#selectedLng").val(selectedLng);
			        // 결과값으로 받은 위치를 마커로 표시합니다
			        marker.setPosition(coords);

			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="padding:5px; font-size:11px;">'+target+'<br><a href="https://map.kakao.com/link/map/'+target+',' 
							+ coords.getLat() + ',' + coords.getLng() + '" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/'+target+','
							+ coords.getLat() + ',' + coords.getLng() + 'style="color:blue" target="_blank">길찾기</a></div>'
			        });
			        infowindow.open(map, marker);
			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } else {
			    	alert("주소 정보를 정확히 가져올 수 없는 장소입니다.");
			    	$("#selectedAddress").val(0);
			    	$("#selectedLat").val(0);
			    	$("#selectedLng").val(0);
			    }
			});
		}
		
		function resetMap() {
			$("#selectedAddress").val(0);
	    	$("#selectedLat").val(0);
	    	$("#selectedLng").val(0);
	    	alert("지도 정보 초기화 완료.");
	    	infowindow.close(map, marker);
	    	$("#sample6_address").val('');
	    	selectedAddress_js = null; // 지도에서 선택한 위치의 주소를 저장할 변수
			selectedLat = null; // 주소 검색으로 검색된 주소의 위도
			selectedLng = null; // 주소 검색으로 검색된 주소의 경도
			address = ''; // 폼에 작성된 주소를 가져온다.
		}
		
	
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
        
        <%--
        <input type="text" class="form-control" id="sample6_postcode"
								name="address_post" placeholder="우편번호"> <input
								type="text" class="form-control" id="sample6_address"
								name="address_basic" placeholder="주소"> 
		의 값으로 위도 경도 가져오자
        --%>
        
        
    }
    $(function(){
        //전역변수
        var oEditors = [];              
        //스마트에디터 프레임생성
        nhn.husky.EZCreator.createInIFrame({
            oAppRef: oEditors,
            elPlaceHolder: "content",
            sSkinURI: "<%= request.getContextPath() %>/resources/editor/SmartEditor2Skin.html",
            fCreator: "createSEditor2",
            htParams : {
                // 툴바 사용 여부
                bUseToolbar : true,            
                // 입력창 크기 조절바 사용 여부
                bUseVerticalResizer : true,    
                // 모드 탭(Editor | HTML | TEXT) 사용 여부
                bUseModeChanger : true,
            }
        });
        /*
        $("#insertBoard").click(function(){
            //id가 smarteditor인 textarea에 에디터에서 대입
            obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
            //폼 submit
            $("#insertBoardFrm").submit();
        });
        */
    	var btn = document.getElementById("updateBoard");
    	btn.onclick = function () {
    		submitContents(btn);
    	}
    	
    	function submitContents(elClickedObj) {
    		var isTitle = false;
    		var isContent = false;
    		var isCategory = false;
    		var isOk = false;
    		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
    		
    		// 에디터 내용에 대한 검증은 여기서 한다.
    		// document.getElementById("content").value를 이용
    		if( $("#category").val() == 0 ) {
    			alert("카테고리를 선택해주세요.");
    			isCategory = false;
    		} else {
    			isCategory = true;
    		}
    		
    		var strTitle = $("#title").val();
    		var trimTitle = $.trim(strTitle);
    		if( trimTitle.length != 0 ){
    			isTitle = true;
    		} else {
    			alert("제목을 입력해주세요.");
    			isTitle = false;
    		}
    		
    		if( $("#content").val().trim != '' ) {
    			isContent = true;
    		} else {
    			alert("내용을 입력해주세요.");
    			isContent = false;
    		}
    		
    		isOk = isCategory && isTitle && isContent;
    		
    		if(isOk) {
	    		try {
	    			// 해당 오브젝트가 위치한 form이 submit 된다.
	    			elClickedObj.form.submit();
	    		} catch(e) {
	    			
	    		}
    		} else {
    			alert("누락된 내용을 기재해주세요.");
    		}
    	}
    });
	
	function adjustCategory(e) {
		if (e == 2){
			$(".caret").text('상품');
			$("#category").val(2);
		} else if (e == 3){
			$(".caret").text('피트니스');
			$("#category").val(3);
		} else if (e == 4){
			$(".caret").text('장소');
			$("#category").val(4);
		} else if (e == 5){
			$(".caret").text('다이어트');
			$("#category").val(5);
		} else if (e == 6){
			$(".caret").text('웨이트 트레이닝');
			$("#category").val(6);
		} else if (e == 7){
			$(".caret").text('레시피');
			$("#category").val(7);
		}
	}
	</script>
	
</body>
</html>