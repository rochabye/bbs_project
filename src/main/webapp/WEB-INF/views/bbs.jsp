<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bbs.bbs_pjt.bbs.Bbs" %>
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
						<th style="background-color: #eeeeee; text-align: center;"> 번호 </th>
						<th style="background-color: #eeeeee; text-align: center;"> 제목 </th>
						<th style="background-color: #eeeeee; text-align: center;"> 작성자 </th>
						<th style="background-color: #eeeeee; text-align: center;"> 작성일 </th>
					</tr>
					
				</thead>
				<tbody>
					<%
						ArrayList<Bbs> list = (ArrayList<Bbs>)request.getAttribute("bbs_list");
						for( int i = 0; i < list.size(); ++i ){
					%>
					<tr>
						<td><%= list.get(i).getBbsID() %></td>
						<td><a href="view.jsp?bbsID=<%=list.get(i).getBbsID() %>"> <%= list.get(i).getBbsTitle().replaceAll( "<", "&lt;" ).replaceAll( ">", "&gt;" ).replaceAll( " ", "&nbsp;" ).replaceAll( "\n", "<br>") %></a></td>
						<td><%= list.get(i).getUserID() %></td>
						<td><%= list.get(i).getBbsDate().substring(0, 11) + list.get(i).getBbsDate().substring(11,13) + "시" + list.get(i).getBbsDate().substring(14,16) + "분" %></td>
					</tr>
					<% 
						}
					%> 
				</tbody>
			</table>

			<a href="write.jsp" class="btn btn-primary pull-right"> 글쓰기</a>
			
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"> </script>
</body>
</html>