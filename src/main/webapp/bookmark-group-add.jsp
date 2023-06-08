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
		<a href="dbSave.jsp" id="searchWifi">Open API 와이파이 정보 가져오기</a>
		<a href="bookmark-list.jsp">즐겨 찾기 보기</a><span>|</span>
	</div>
	<form action="bookmark-group-add-submit.jsp" method="post" accept-charset="UTF-8">
		<table id="customers">
			<tbody>
				<tr>
					<th>북마크 이름</th>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<th>순서</th>
					<td><input type="text" name="number"></td>
				</tr>
			</tbody>
		</table>
		<button type="submit">추가</button>
	</form>
</body>
</html>