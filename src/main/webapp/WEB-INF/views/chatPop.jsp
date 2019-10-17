<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅 유형 선택</title>
<jsp:include page="cssInclude.jsp" flush="false"></jsp:include>
</head>
<body>
<div align="center" style="margin: 60px;">
	<p><a href="<%= request.getContextPath() %>/chattingWithAdmin" onclick="window.open(this.href, '관리자와 채팅', 'width = 426, height = 805'); window.close(); return false;">관리자와 1:1 상담 채팅</a></p>
	<p><a href="<%= request.getContextPath() %>/chattingGroupSelect" onclick="window.open(this.href, '관심사 채팅', 'width = 456, height = 202'); window.close(); return false;">관심사 별 그룹 채팅</a></p>
</div>
</body>
</html>