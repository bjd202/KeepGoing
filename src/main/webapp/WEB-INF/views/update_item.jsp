<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>상품 수정</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">상품 수정</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="container">

    	<form id="update_item_form" enctype="multipart/form-data" method="post">
    		<input type="hidden" name="member_id" value="${ login_member.member_id }">
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="title" placeholder="Title" value="${ searchedItem.title }">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea rows="20" cols="" class="form-control" name="content" placeholder="Content">${ searchedItem.content }</textarea>
			</div>
			<div class="form-group">
				<label for="category">카테고리</label>
				<select name="category">
					<option value="1" ${ searchedItem.category==1 ? 'selected' : '' }>운동기구</option>
					<option value="2" ${ searchedItem.category==2 ? 'selected' : '' }>보충제</option>
					<option value="3" ${ searchedItem.category==3 ? 'selected' : '' }>기타</option>
				</select>
			</div>
			<div class="form-group">
				<label for="title">개수</label>
				<input type="text" class="form-control" id="number" name="number" placeholder="Number" value="${ searchedItem.number }">
			</div>
			<div class="form-group">
				<label for="title">가격</label>
				<input type="text" class="form-control" id="price" name="price" placeholder="Price" value="${ searchedItem.price }">
			</div>
			<div class="form-group">
				<label for="image">이미지 업로드</label>
				<input type="file" name="image">
				<p>*미 입력시 이전의 이미지 사용</p>
			</div>
			
			<button type="button" class="btn btn-info" id="update_item">상품 수정</button>
		</form>

    </div>
	
	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#update_item").on("click", function() {
				if(numberCheck()==false)
		    		return;
		    	if(priceCheck()==false)
		    		return;
		    	
		    	var form = $('#update_item_form')[0];

		        var formData = new FormData(form);
		    	
			   	 $.ajax({
			   	        type:"POST",
			   	        url:"<%=request.getContextPath()%>/item_update/${searchedItem.board_id}",
			   	     	enctype: 'multipart/form-data',
			   	        contentType: false,
			   	    	processData: false,
			   	        data: formData,
			   	        //datatype:"json",
			   	        success: function(result) {
							alert(result);
			   	        },
			   	        error: function(e) {
			   	          alert("에러 발생");
			   	        }			
			   	});
			})
		})
		
		function numberCheck() {
	        var number = $("#number").val();
	        var languageCheck = /[0-9]/;
	        
	        if (languageCheck.test(number)) {
	
	            return true;
	        } else {
	        	$("#number").focus();
	        	alert("number은 숫자로 입력해주세요.");
	            return false;
	        }
    	}
    
	    function priceCheck() {
	        var price = $("#price").val();
	        var languageCheck = /[0-9]/;
	        
	        if (languageCheck.test(price)) {
	
	            return true;
	        } else {
	        	$("#price").focus();
	        	alert("price은 숫자로 입력해주세요.");
	            return false;
	        }
	    }
	</script>
</body>
</html>