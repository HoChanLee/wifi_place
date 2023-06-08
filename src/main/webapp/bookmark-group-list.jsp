<%@page import="java.util.ArrayList"%>
<%@page import="localhost.wifi_place.BookMark"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<div>
		<a href="index.jsp">홈</a> <span>|</span>
		<a href="bookmark-group-list.jsp">위치 히스토리목록</a> <span>|</span>
		<a href="dbSave.jsp" id="searchWifi">Open API 와이파이 정보 가져오기</a><span>|</span>
		<a href="bookmark-list.jsp">즐겨 찾기 보기</a>
	</div>
	<button onclick="location.href='bookmark-group-add.jsp' ">북마크 그룹 이름 추가</button>
	<table style="width: 100%; text-align: center" id="customers">
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>순서</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<%
			BookMark db = new BookMark();
			ArrayList<String[]> dbArr = db.BMList();
			if(dbArr.size() > 0){
				for(String[] arr : dbArr){	
			%>
					<tr>
						<% for(int i = 0; i < arr.length; i++){ %>
							<td>
								<%= arr[i] != null ? arr[i] : "-" %>
							</td>
						<% } %>
						<td>
							<a href="bookmark-group-edit.jsp?id=<%= arr[0] %>">수정</a>
							<a href="bookmark-group-delete.jsp?id=<%= arr[0] %>">삭제</a>
						</td>
					</tr>
			<% 	} 
			} else { %>
			<tr>
				<td colspan="6" style="text-align: center">정보가 존재하지 않습니다.</td>
			</tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>