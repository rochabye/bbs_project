<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
		if ( session.getAttribute( "userID" ) != null ) {
			userID = ( String ) session.getAttribute( "userID" );
		}
	%>
	<nav class="navbar navbar-default" >
		<div class="navbar-header" >
			<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false" >
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main" >JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" >
			<li class = "active" ><a href="main">메인</a></li>
			<li><a href="bbs">게시판</a></li>
		</ul>
		<% 
		if ( userID == null ) 
		{
		%>
		<ul class="nav navbar-nav navbar-right" >
			<li class="dropdown">
			<a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false" >접속하기 <span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="login">로그인</a></li>
				<li><a href="join">회원가입</a></li>
			</ul>
			</li>
		</ul>
		<%
		}
		else
		{
		%>
		<ul class="nav navbar-nav navbar-right" >
			<li class="dropdown">
			<a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false" >회원관리 <span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="logoutAction">로그아웃</a></li>
			</ul>
			</li>
		</ul>
		<%
		}
		%>
		</div>
	</nav>
	<div class ="container" >
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top:20px;" >
				<h3 style="text-align: center;" > 회원가입을 성공적으로 완료했습니다.  </h3>
				<a href="login" type="button"  class="btn btn-primary form-control" > 로그인 </a> 
				<div class="col-lg-4"></div>
				<a href="main" type="button"  class="btn btn-primary form-control" > 메인으로 이동 </a> 
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"> </script>
</body>
</html>