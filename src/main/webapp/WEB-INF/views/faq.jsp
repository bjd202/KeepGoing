<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>자주묻는질문</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/faq.css">

</head>
<body>

	<jsp:include page="menu.jsp" flush="false"></jsp:include>
	<jsp:include page="modalLogin.jsp" flush="false"></jsp:include>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>

	<script type="text/javascript">

	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	var tab = getParameterByName('tabs');
	
	
	$(document).ready(function(){
	    $('#myTab').children().eq(tab).addClass('active');
	});
	
	
	
	

	
	 

	</script>


	<div class="site-blocks-cover inner-page-cover overlay"
		style="background-image: url(<%=request.getContextPath()%>/resources/images/top.jpg);"
		data-aos="fade" data-stellar-background-ratio="0.5">
		<div class="container">
			<div
				class="row align-items-center justify-content-center text-center">

				<div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
					<h1
						class="text-white font-weight-light text-uppercase font-weight-bold">자주
						묻는 질문(FAQ)</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>

	<div class="site-section block-13">
		<div style="margin-left: 15%; margin-right: 15%;">
			<div class="panel panel-default overlay"
				style="height: 100px; background-image: url(<%=request.getContextPath()%>/resources/images/hero_bg_1.jpg);">
				<h3
					class="text-white font-weight-light text-uppercase font-weight-bold">자주
					묻는 질문들을 모았습니다.</h3>
			</div>





			<ul id="myTab" class="nav nav-tabs" role="tablist">
				<li role="presentation" class="tabcontent" id="tabs0"
					data-toggle="tab"
					onclick="location.href='<%=request.getContextPath()%>/faq?tabs=0'"><a
					href="#">전체</a></li>
				<li role="presentation" class="tabcontent" id="tabs1"
					data-toggle="tab"
					onclick="location.href='<%=request.getContextPath()%>/faq?tabs=1'"><a
					href="#">회원가입</a></li>
				<li role="presentation" class="tabcontent" id="tabs2"
					data-toggle="tab"
					onclick="location.href='<%=request.getContextPath()%>/faq?tabs=2'"><a
					href="#">상품</a></li>
				<li role="presentation" class="tabcontent" id="tabs3"
					data-toggle="tab"
					onclick="location.href='<%=request.getContextPath()%>/faq?tabs=3'"><a
					href="#">기타</a></li>
			</ul>

			<!-- 분류별  -->
			<div class="panel-group" id="accordion" role="tablist"
				aria-multiselectable="true">
				<c:if test="${ param.tabs != 0 }">
					<c:forEach items="${ faq }" var="faq">
						<c:if test="${ faq.category == param.tabs }">
							<div class="panel panel-default">
								<div class="panel-heading" role="tab"
									id="heading${ faq.board_id }">
									<h5 class="panel-title">
										<a class="collapsed" data-toggle="collapse"
											data-parent="#accordion" href="#collapse${ faq.board_id }"
											aria-expanded="true"
											aria-controls="collapse${ faq.board_id }"> Q. ${ faq.title }
										</a>
									</h5>
								</div>
								<div id="collapse${ faq.board_id }"
									class="panel-collapse collapse in" role="tabpanel"
									aria-labelledby="heading${ faq.board_id }">
									<div class="panel-body">A. ${ faq.content }</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:if>
				<!-- /분류별  -->

				<!-- 전체  -->
				<c:if test="${ param.tabs == 0 || param.tabs == null }">
					<c:forEach items="${ faq }" var="faq">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab"
								id="heading${ faq.board_id }">
								<h5 class="panel-title">
									<a class="collapsed" data-toggle="collapse"
										data-parent="#accordion" href="#collapse${ faq.board_id }"
										aria-expanded="true" aria-controls="collapse${ faq.board_id }">
										Q. ${ faq.title } </a>
								</h5>
							</div>
							<div id="collapse${ faq.board_id }"
								class="panel-collapse collapse in" role="tabpanel"
								aria-labelledby="heading${ faq.board_id }">
								<div class="panel-body">A. ${ faq.content }</div>
								<c:if test="${ login_member.auth >= 3 }">
									<input type="hidden" name="board_id" value="${ faq.board_id }">
									<p align="right">
										<a class="btn btn-info btn-xs" type="button"
											onclick="javascript:update('${ faq.board_id }', '${ faq.content }');">수정</a>
									</p>
									<p align="right">
										<a href="<%=request.getContextPath()%>/faq/del"
											class="btn btn-info btn-xs" type="button">삭제</a>
									</p>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</c:if>

			</div>
			<!-- /전체  -->

			<c:if test="${ login_member.auth >= 3 }">
				<p align="right">
					<a href="<%=request.getContextPath()%>/faq/write"
						class="btn btn-info btn-xs" type="button">작성</a>
				</p>
			</c:if>

		</div>

	</div>



	<jsp:include page="site_footer.jsp"></jsp:include>

	<script type="text/javascript">



//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function update(bno, content){
    var a ='';
    console.log(bno, content)
    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="content_'+bno+'" value="'+content+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="updateProc('+bno+');">수정</button> </span>';
    a += '</div>';
    
    $('.collapse'+bno).html(a);
    
}
 
//댓글 수정
function updateProc(cno){
    var updateContent = $('[name=content_'+cno+']').val();
    
    $.ajax({
        url : '/comment/update',
        type : 'post',
        data : {'content' : updateContent, 'cno' : cno},
        success : function(data){
            if(data == 1) commentList(bno); //댓글 수정후 목록 출력 
        }
    });
}
 
//댓글 삭제 
function delete(cno){
    $.ajax({
        url : '/comment/delete/'+cno,
        type : 'post',
        success : function(data){
            if(data == 1) commentList(bno); //댓글 삭제후 목록 출력 
        }
    });
}


</script>

</body>
</html>