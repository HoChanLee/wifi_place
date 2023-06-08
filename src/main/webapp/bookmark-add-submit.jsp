<%@page import="localhost.wifi_place.BookMark"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");

String BMName = request.getParameter("BMName");
String wifiName = request.getParameter("X_SWIFI_MAIN_NM");

BookMark db = new BookMark();
db.favAdd(BMName, wifiName);
%>
<script>
	alert("즐겨찾기를 추가하였습니다.");
    location.href="bookmark-list.jsp";
</script>
</body>
</html>