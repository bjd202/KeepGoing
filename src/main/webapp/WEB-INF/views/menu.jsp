<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<div class="site-wrap" style="z-index: 3;">
    <div class="site-mobile-menu">
      <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close mt-3">
          <span class="icon-close2 js-menu-toggle"></span>
        </div>
      </div>
      <div class="site-mobile-menu-body"></div>
    </div>
    
    <header class="site-navbar py-3" role="banner">

      <div class="container">
        <div class="row align-items-center">
          
          <div class="col-11 col-xl-2">
            <h1 class="mb-0"><a href="<%= request.getContextPath() %>/home" class="text-white h2 mb-0">KeepGoing
            
            </a></h1>
          </div>
          <div class="col-12 col-md-10 d-none d-xl-block">
            <nav class="site-navigation position-relative text-right" role="navigation">

              <ul class="site-menu js-clone-nav mx-auto d-none d-lg-block">
                <li><a href="<%= request.getContextPath() %>/review/1">리뷰</a></li>
                <li><a href="<%= request.getContextPath() %>/free/1">자유 게시판</a></li>

                <li><a href="<%= request.getContextPath() %>/item">상품</a></li>
                <li class="has-children"><a href="#">고객센터</a>
                	<ul class="dropdown">
	                    <li><a href="<%= request.getContextPath() %>/notice">공지사항</a></li>
	                    <li><a href="<%= request.getContextPath() %>/faq">자주묻는질문</a></li>
	                    <li><a href="<%= request.getContextPath() %>/qna">Q&A</a></li>
	                </ul>
                </li>
                <li><a href="<%= request.getContextPath() %>/cart">장바구니</a></li>
                <c:if test="${ login_member == null }" var="r">
	                <li><a href="<%= request.getContextPath() %>/regist">회원가입</a></li>
                	<li><a href="" data-toggle="modal" data-target="#modalLRForm">로그인</a></li>
                </c:if>
                <c:if test="${ not r }">
                	<li class="has-children"><a href="#">${ login_member.nickname }</a>
                		<ul class="dropdown">
		                    <li><a href="<%=request.getContextPath()%>/mypage">마이페이지</a></li>
		                    <li><a href="<%= request.getContextPath() %>/chatPop" onclick="window.open(this.href, '채팅 유형 선택', 'width = 400, height = 202'); return false;">채팅</a></li>
		                    <li><a href="<%=request.getContextPath()%>/logout">로그아웃</a></li>
	                	</ul>
                	</li>
                </c:if>
              </ul>
            </nav>
          </div>


          <div class="d-inline-block d-xl-none ml-md-0 mr-auto py-3" style="position: relative; top: 3px;"><a href="#" class="site-menu-toggle js-menu-toggle text-white"><span class="icon-menu h3"></span></a></div>

          </div>

        </div>
     
      
    </header>
 </div>