<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bbs.bbs_pjt.article.Article" %>
<%@ page import="java.util.ArrayList" %>
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
			<li><a href="main">메인</a></li>
			<li class = "active"><a href="list">게시판</a></li>
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
				<li><a href="logout">로그아웃</a></li>
			</ul>
			</li>
		</ul>
		<%
		}
		%>
		</div>
	</nav>
	<div class="container" >
		<div class="row">
			<table class="table table-striped" style="text-align:center; border: 1px solid #dddddd" >
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글 보기</th>
					</tr>
				</thead>
				<tbody>
					<%
						Article article = (Article)request.getAttribute("article");
					%>
					<tr>
						<td style="width: 20%;" >글 제목 </td>
						<td colspan="2"> <%=article.getArticleTitle().replaceAll( "<", "&lt;" ).replaceAll( ">", "&gt;" ).replaceAll( " ", "&nbsp;" ).replaceAll( "\n", "<br>") %> </td>
					</tr>
					<tr>
						<td> 작성자 </td>
						<td colspan="2" ><%= article.getUserID() %> </td>
					</tr>
					<tr>
						<td> 작성일  </td>
						<td colspan="2"><%= article.getArticleDate().substring(0, 11) + article.getArticleDate().substring(11,13) + "시" + article.getArticleDate().substring(14,16) + "분" %></td>
					</tr>
					<tr>
						<td> 내용 </td>
						<td colspan="2" style="min-height:200px; text-align: left;" ><%= article.getArticleContent().replaceAll( "<", "&lt;" ).replaceAll( ">", "&gt;" ).replaceAll( " ", "&nbsp;" ).replaceAll( "\n", "<br>") %></td>
					</tr>
				</tbody>
			</table>
			<a href="list" class="btn btn-primary" > 목록 </a>
			<%
				if ( userID != null && userID.equals( article.getUserID() ) ) {
			%>
					<a href="update?articleNo=<%= article.getArticleNo() %>" class="btn btn-primary"> 수정</a>
					<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="delete?articleNo=<%= article.getArticleNo() %>" class="btn btn-primary"> 삭제 </a>			
			<%
				}
			%>
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"> </script>
</body>
</html>