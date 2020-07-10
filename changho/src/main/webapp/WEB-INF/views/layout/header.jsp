<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

<!-- jQuery -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>



<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


<!-- common CSS -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/common.css">


<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!--  메뉴바 추가 {s} -->
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <a class="navbar-brand" href="${pageContext.request.contextPath}">YANG </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarsExample03">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/board/getBoardList">BOARD <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/menu/getMenuList">MENU LIST</a>
      </li>
      
      
    </ul>
    
    <div class = "form-group">
    
    	<form class="form-inline my-2 my-md-0" >
    
		<c:if test="${member != null}">
			<p>${member.userId}님 안녕하세요.</p>
			<a href = "#" onClick= "logoutClick()">로그아웃</a>
		</c:if>
	  </form>
	</div>
		
	
	
  
  </div>
</nav>
<!--  상단 메뉴바 {e} -->

<script>

	function logoutClick(){ 
		
		location.href="${pageContext.request.contextPath}/member/logout";
		
		}


</script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>