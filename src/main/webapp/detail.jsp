<%@page import="localhost.wifi_place.BookMark"%>
<%@page import="java.util.ArrayList"%>
<%@page import="localhost.wifi_place.Service"%>
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
	<%
	request.setCharacterEncoding("UTF-8");
	String getX_SWIFI_MGR_NO = request.getParameter("wifiId"); 
	String distance = request.getParameter("distance");
	
	Service db = new Service();
	String[] dbArr = db.dbDetail(getX_SWIFI_MGR_NO);
		
	BookMark BMDb = new BookMark();
	ArrayList<String[]> BMArr = BMDb.BMList();
		
	%>
	<h1>회원 상세</h1>
	<div>
		<a href="index.jsp">홈</a> <span>|</span>
		<a href="bookmark-group-list.jsp">위치 히스토리목록</a><span>|</span>
		<a href="dbSave.jsp" id="searchWifi">Open API 와이파이 정보 가져오기</a><span>|</span>
		<a href="bookmark-list.jsp">즐겨 찾기 보기</a>
	</div>
	<form action="bookmark-add-submit.jsp" method="post" accept-charset="UTF-8">
	<div>
		<input type="hidden" name="X_SWIFI_MAIN_NM" value="<%= dbArr[2] %>">
		<select name="BMName">
			<option value="">북마크를 선택해 주세요</option>
			<% 
			for(String[] arr : BMArr){
			%>
				<option value="<%= arr[1] %>"><%= arr[1] %></option>	
			<%
			}	
			%>
		</select>
		<button type="submit">즐겨찾기 추가하기</button>
	</div>
	</form>
	<table id="customers">
		<colgroup>
			<col style="width: 30%;"/>
			<col style="width: 70%;"/>
		</colgroup>
		<tbody>
		<tr>
			<th>거리(km)</th>
			<td><%= distance %></td>
		</tr>
		<tr>
			<th>관리번호</th>
			<td><%= dbArr[0] %></td>
		</tr>
		<tr>
			<th>자치구</th>
			<td><%= dbArr[1] %></td>
		</tr>
		<tr>
			<th>와이파이명</th>
			<td><%= dbArr[2] %></td>
		</tr>
		<tr>
			<th>도로명주소명</th>
			<td><%= dbArr[3] %></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><%= dbArr[4] %></td>
		</tr>
		<tr>
			<th>설치위치(층)</th>
			<td><%= dbArr[5] %></td>
		</tr>
		<tr>
			<th>설치유형</th>
			<td><%= dbArr[6] %></td>
		</tr>
		<tr>
			<th>설치기관</th>
			<td><%= dbArr[7] %></td>
		</tr>
		<tr>
			<th>서비스구분</th>
			<td><%= dbArr[8] %></td>
		</tr>
		<tr>
			<th>망종류</th>
			<td><%= dbArr[9] %></td>
		</tr>
		<tr>
			<th>설치년도</th>
			<td><%= dbArr[10] %></td>
		</tr>
		<tr>
			<th>실내외구분</th>
			<td><%= dbArr[11] %></td>
		</tr>
		<tr>
			<th>WIFI접속환경</th>
			<td><%= dbArr[12] %></td>
		</tr>
		<tr>
			<th>X좌표</th>
			<td><%= dbArr[13] %></td>
		</tr>
		<tr>
			<th>Y좌표</th>
			<td><%= dbArr[14] %></td>
		</tr>
		</tbody>
	</table>
</body>
</html>