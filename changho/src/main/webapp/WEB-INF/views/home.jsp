<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>


<html>
<head>
	<title>Home</title>
		<!-- 합쳐지고 최소화된 최신 CSS -->
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	부가적인 테마
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->
	
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
</head>

<script type="text/javascript">
	
	// 로그아웃 버튼 
	$(document).ready(function(){
		$("#logoutBtn").on("click", function(){
			location.href="member/logout";
		});
		
	});
	
	// 회원가입 버튼 
	$(document).ready(function(){
		$("#registerBtn").on("click", function(){
			location.href="${pageContext.request.contextPath}/member/register";
		});
		
	});
	
	// 회원 수정 버튼 
	$(document).ready(function(){
		$("#memberUpdateBtn").on("click", function(){
			location.href="${pageContext.request.contextPath}/member/memberUpdateView";
		});
		
	});
	
	// 회원 탈퇴 버튼 
	$(document).ready(function(){
		$("#memberDeleteBtn").on("click", function(){
			location.href="${pageContext.request.contextPath}/member/memberDeleteView";
		});
		
	});
	
</script>
<body>
	<form name='homeForm' method="post" action="${pageContext.request.contextPath}/member/login">
		<c:if test="${member == null}">
			<div>
				<label for="userId"></label>
				<input type="text" id="userId" name="userId">
			</div>
			<div>
				<label for="userPass"></label>
				<input type="password" id="userPass" name="userPass">
			</div>
			<div>
				<button type="submit">로그인</button>
				<button id = "registerBtn" type="button">회원가입</button>
			</div>
		</c:if>
		<c:if test="${member != null }">
			<div>
				<p>${member.userId}님 환영 합니다.</p>
				<button id="memberUpdateBtn" type="button">회원정보수정</button>
				<button id="logoutBtn" type="button">로그아웃</button>
				<button id="memberDeleteBtn" type="button">회원탈퇴</button>
			</div>
		</c:if>
		<c:if test="${msg == false}">
			<p style="color: red;">로그인 실패! 아이디와 비밀번호 확인해주세요.</p>
		</c:if>
	</form>
</body>
</html>