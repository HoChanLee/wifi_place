<%@page import="localhost.wifi_place.BookMark"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위치 히스토리 목록</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<% 
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");
BookMark db = new BookMark();
String[] dbArr = db.BMDetail(id);
%>
	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="index.jsp">홈</a> <span>|</span>
		<a href="bookmark-group-list.jsp">위치 히스토리목록</a> <span>|</span>
		<a href="dbSave.jsp" id="searchWifi">Open API 와이파이 정보 가져오기</a>
		<a href="bookmark-list.jsp">즐겨 찾기 보기</a><span>|</span>
	</div>
	<form action="bookmark-group-edit-submit.jsp" method="post" accept-charset="UTF-8">
		<input type="hidden" name="id" value="<%= id %>">
		<table id="customers">
			<tbody>
				<tr>
					<th>북마크 이름</th>
					<td><input type="text" name="name" value="<%= dbArr[0] %>"></td>
				</tr>
				<tr>
					<th>순서</th>
					<td><input type="text" name="number" value="<%= dbArr[1] %>"></td>
				</tr>
			</tbody>
		</table>
		<a href="bookmark-group-list.jsp">뒤로가기</a><button type="submit">수정</button>
	</form>
</body>
</html>