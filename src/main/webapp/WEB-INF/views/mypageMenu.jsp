<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${ login_member.member_id=='admin' }" var="r">
<div class="btn-group-vertical col-md-2" style="justify-content: unset;">
	<div class="btn-group">
		<button type="button" class="btn btn-primary dropdown-toggle"
			data-toggle="dropdown">관리자</button>
		<div class="dropdown-menu">
			<a class="dropdown-item"
				href="<%=request.getContextPath()%>/mypage/admin/member_management">회원
				관리</a>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/mypage/admin/permission_setting">권한 설정</a>
				<a class="dropdown-item" href="<%=request.getContextPath()%>/mypage/admin/board_management">게시물 관리</a>
		</div>
	</div>
</div>
</c:if>


<c:if test="${ not r }">
<div class="btn-group-vertical col-md-2 " style="justify-content: unset;">
	<div class="btn-group">
		<button type="button" class="btn btn-primary dropdown-toggle"
			data-toggle="dropdown">회원 정보</button>
		<div class="dropdown-menu">
			<a class="dropdown-item" href="<%=request.getContextPath()%>/mypage/member_update">회원정보 변경</a> <a
				class="dropdown-item" href="<%=request.getContextPath()%>/mypage/recent_activity">최근 활동</a> <a class="dropdown-item"
				href="<%=request.getContextPath()%>/mypage/buy_list">구매 목록</a>
		</div>
	</div>

	<button type="button" class="btn btn-primary" onclick="location.href='<%=request.getContextPath()%>/mypage/permission_request'">권한 신청</button>
</div>
</c:if>