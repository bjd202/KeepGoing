<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Item</title>
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
						class="text-white font-weight-light text-uppercase font-weight-bold">상품</h1>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="right_sidebar.jsp" flush="false"></jsp:include>
	
	<div class="site-section">
      <div class="container">
      
      	<c:if test="${ login_member.auth >= 2 }">
      		<p><a href="<%=request.getContextPath()%>/add_item" class="btn btn-info" type="button">상품 추가</a></p>
      		<!-- <p><button class="btn btn-info" type="button" id="add_item">상품 추가</button></p> -->
      	</c:if>
            	
        <div class="row">
        
        <c:forEach items="${ item_list }" var="item">
          <div class="col-md-6 col-lg-4 mb-4 mb-lg-4">
            <div class="h-entry">
              <img src="<%=request.getContextPath()%>/resources/images/${item.image}" alt="Image" class="img-fluid rounded" style="min-width: 90%; height: 350px;">
              <h2 class="font-size-regular"><a href="<%=request.getContextPath()%>/item_view/${item.board_id}">${ item.title }</a></h2>
              <div class="meta">${ item.nickname } 
              <span class="mx-2">•</span> ${ item.write_date }
              <span class="mx-2">•</span> ${ item.getCategoryString() }</div>
              <div class="meta mb-4">조회수 : ${ item.view_cnt } <span class="mx-2">•</span>댓글수 : ${ item.comment_cnt }<span class="mx-2">•</span>좋아요/싫어요 : ${ item.like_cnt }/${ item.dislike_cnt } </div>
              <p style="word-wrap: break-word;">${ item.getRestrictedContent() }</p>
            </div> 
          </div>
          </c:forEach>
          
        </div>
      </div>
    </div>
    
    <div class="container text-center pb-5">
      <div class="row">
        <div class="col-12">
        	
          <p class="custom-pagination">
         	<c:if test="${pageMaker.prev == true }">
        		<a href="<%=request.getContextPath()%>/item/${pageMaker.startPage-1}">&laquo;</a>
        	</c:if>
          	<c:forEach var="pageNo" begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }">
          		<c:if test="${curPageNo == pageNo}" var="r">
          			<span>${curPageNo}</span>
          		</c:if>
          		<c:if test="${ not r }">
          			<a href="<%=request.getContextPath()%>/item/${pageNo}">${ pageNo }</a>
          		</c:if>
          	</c:forEach>
            <c:if test="${pageMaker.next == true and pageMaker.endPage > 0}">
        		<a href="<%=request.getContextPath()%>/item/${pageMaker.endPage+1}">&raquo;</a>
        	</c:if>
          </p>
        </div>
      </div>
    </div>

	<jsp:include page="javascriptInclude.jsp" flush="false"></jsp:include>
	
	
 
 <jsp:include page="site_footer.jsp"></jsp:include>

</body>
</html>