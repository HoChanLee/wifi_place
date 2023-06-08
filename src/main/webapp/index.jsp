<%@page import="java.util.ArrayList"%>
<%@page import="localhost.wifi_place.Service"%>
<%@page import="localhost.wifi_place.Main"%>
<%@page import="localhost.wifi_place.WifiDb"%>
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
	String lat = request.getParameter("lat");
	String lnt = request.getParameter("lnt");
	%>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<a href="index.jsp">홈</a> <span>|</span>
		<a href="bookmark-group-list.jsp">위치 히스토리목록</a> <span>|</span>
		<a href="dbSave.jsp" id="searchWifi">Open API 와이파이 정보 가져오기</a><span>|</span>
		<a href="bookmark-list.jsp">즐겨 찾기 보기</a>
	</div>
	<div>
	<form action="" method="get">
		<label for="latInput">LAT</label><input type="text" id="latInput" name="lat">
		<label for="lntInput">LNT</label><input type="text" id="lntInput" name="lnt">
		<button id="myLocation" type="button">내 위치 가져오기</button>
		<button id="locationWifi" type="submit">근처 WIFI 정보 보기</button>
	</form>
	</div>
	<table style="font-size: 15px" id="customers">
		<thead>
			<tr>
				<th>거리(km)</th>
				<th>관리번</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로주소명</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
			</tr>
		</thead>
		<tbody>
			
			<%
			if(lat == null && lnt == null){
			%>
			<tr><td colspan="16" style="text-align: center">위치정보를 입력한후에 조회해 주세요.</td></tr>
			<% } else {
				Service db = new Service();
				ArrayList<String[]> dbArr = db.dbSelect(lat, lnt);
				
				for (String[] arr : dbArr) {
					String distance = String.format("%.1f", Double.parseDouble(arr[0]));
				%>
				<tr class="selectShow">
					<td><%= distance %></td>
					<%
					for (int i = 1; i < arr.length; i++) {
					%>
						
						<td>
							<% if(i != 3){
									out.write(arr[i]);
							 } else { 
							 %>
									<a href="detail.jsp?wifiId=<%= arr[1] %>&distance=<%= distance %>">
										<%=	arr[i] %>
									</a>
							<% } %>
						</td>
					<%
					}
					%>
					</a>
				</tr>
			<%
				}
			}
			%>
		</tbody>
	</table>
	<script>
		
		let myLocation = document.getElementById("myLocation");

		myLocation.addEventListener("click", () => {
			function success({ coords, timestamp }) {
	            const latitude = coords.latitude;   // 위도
	            const longitude = coords.longitude; // 경도
	            
	            document.getElementById("latInput").value = latitude;
	            document.getElementById("lntInput").value = longitude;
	        }

	        function getUserLocation() {
	            if (!navigator.geolocation) {
	                throw "위치 정보가 지원되지 않습니다.";
	            }
	            navigator.geolocation.getCurrentPosition(success);
	        }

	        getUserLocation();
		})
	</script>
</body>
</html>