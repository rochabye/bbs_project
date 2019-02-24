<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bbs.bbs_pjt.article.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bbs.bbs_pjt.commons.paging.PageMaker" %>
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
			<li class = "active" ><a href="list">게시판</a></li>
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
						ArrayList<Article> list = (ArrayList<Article>)request.getAttribute("articleList");
						for( int i = 0; i < list.size(); ++i ){
					%>
					<tr>
						<td><%= list.get(i).getArticleNo() %></td>
						<td><a href="article_view?articleNo=<%=list.get(i).getArticleNo() %>" value="<%=list.get(i).getArticleNo() %>"> <%= list.get(i).getArticleTitle().replaceAll( "<", "&lt;" ).replaceAll( ">", "&gt;" ).replaceAll( " ", "&nbsp;" ).replaceAll( "\n", "<br>") %></a></td>
						<td><%= list.get(i).getUserID() %></td>
						<td><%= list.get(i).getArticleDate().substring(0, 11) + list.get(i).getArticleDate().substring(11,13) + "시" + list.get(i).getArticleDate().substring(14,16) + "분" %></td>
					</tr>
					<% 
						}
					%> 
				</tbody>
			</table>
			<% 
			if ( userID != null )
			{
			%>
				<a href="write" class="btn btn-primary pull-right"> 글쓰기</a>
			<%
			}
			%>
			
			<% 
				PageMaker pageMaker = (PageMaker)request.getAttribute( "pageMaker" );
			%>
			<nav aria-label="Page navigation">
			  <ul class="pagination justify-content-center"> <!-- 안됨 --> 
			    <li class="page-item">
			      <a class="page-link" href="#" aria-label="Previous" <%= pageMaker.hasPrev() ? null : "disabled" %>>
			        <span aria-hidden="true">&laquo;</span>
			        <span class="sr-only">Previous</span>
			      </a>
			    </li>
			    <% for( int i = pageMaker.getStartPage(); i <= pageMaker.getEndPage(); ++i ) { %>
			    <li class="page-item"><a class="page-link" href="list?page=<%=i%>&perPageNum=8"><%=i %></a></li>
			    <% } %>
			    
			    <li class="page-item">
			      <a class="page-link" href="#" aria-label="Next" <%= pageMaker.hasNext() ? null : "disabled" %>>
			        <span aria-hidden="true">&raquo;</span>
			        <span class="sr-only">Next</span>
			      </a>
			    </li>
			  </ul>
			</nav>
			
			
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"> </script>
</body>
</html>