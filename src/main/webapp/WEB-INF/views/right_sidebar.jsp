<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- right sidebar button -->
<button id='trigger' class="btn btn-default"
	style='position: fixed; right: 0px; background-color: #f89d13; z-index: 2;'>
	<span class="icon-th-list" style="color: white;"></span>
</button>

<!-- right sidebar -->
<div id="right_sidebar" class="side-panel "
	style="position: fixed; width: 250px; transition: all 300ms ease 0s; height: 100%; top: 0px; right: -270px; z-index: 2000; background-color: white;">
	<!-- <span class="lead">Hello World!!</span><br>
	<span class="text-muted">Press <kbd>ESC</kbd> to close</span> -->
	
	<c:forEach items="${ r_s_list }" var="result" varStatus="status">
		<a href="<%=request.getContextPath()%>/item_view/${result.board_id}" class="list-group-item list-group-item-action flex-column align-items-start">
			<div class="d-flex w-100 justify-content-between">
				<h5>${ result.getRestrictedTitle() }</h5>
			</div>
			<p class="mb-1">${ result.getRestrictedContent2() }</p>
		</a>
	</c:forEach>
</div>