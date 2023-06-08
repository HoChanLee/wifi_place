<%@page import="localhost.wifi_place.Main"%>
<%@page import="localhost.wifi_place.WifiDb"%>
<%@page import="java.util.ArrayList"%>
<%@page import="localhost.wifi_place.Service"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="java.net.URLEncoder"%>

<%@page import="org.json.simple.parser.JSONParser"%>

<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.IOException"%>

<%@page import="java.io.FileReader"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.parser.ParseException"%>
<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
Service db = new Service();
db.dbReset();

String jsonTotalNum = Main.wifiDb(1, 2);

JSONParser parser = new JSONParser();
Object obj = parser.parse(jsonTotalNum); 

// 아래 code로 이어짐
JSONObject jsonMain = (JSONObject)obj;
JSONObject jsonArr = (JSONObject)jsonMain.get("TbPublicWifiInfo");
System.out.println(jsonArr);


JSONParser parser2 = new JSONParser();
Object obj2;
JSONObject jsonMain2;
JSONObject jsonArr2;
JSONObject jsonMain3;
JSONArray jsonArr3;
WifiDb wifiDb = new WifiDb();

long totalNum = (long)jsonArr.get("list_total_count");
int startIdx = 1;
int endIdx = 0;
while(true) {
	startIdx = endIdx+1;
	if(totalNum > 1000) {
		endIdx += 1000;
		totalNum -= 1000;
	} else if (totalNum <= 1000) {
		endIdx += totalNum;
		totalNum = 0;
	}
	
	String jsonData = Main.wifiDb(startIdx, endIdx);
	
	obj2 = parser2.parse(jsonData);
	jsonMain2 = (JSONObject)obj2;
    jsonArr2 = (JSONObject)jsonMain2.get("TbPublicWifiInfo");
    
    jsonMain3 = (JSONObject)jsonArr2;
    jsonArr3 = (JSONArray)jsonMain3.get("row");
    
    if(jsonArr3.size() > 0) {
    	for(int i = 0; i < jsonArr3.size(); i++) {
    		JSONObject jsonObj = (JSONObject)jsonArr3.get(i);
    		
    		wifiDb.setX_SWIFI_MGR_NO((String)jsonObj.get("X_SWIFI_MGR_NO"));
    		wifiDb.setX_SWIFI_WRDOFC((String)jsonObj.get("X_SWIFI_WRDOFC"));
    		wifiDb.setX_SWIFI_MAIN_NM((String)jsonObj.get("X_SWIFI_MAIN_NM"));
    		wifiDb.setX_SWIFI_ADRES1((String)jsonObj.get("X_SWIFI_ADRES1"));
    		wifiDb.setX_SWIFI_ADRES2((String)jsonObj.get("X_SWIFI_ADRES2"));
    		wifiDb.setX_SWIFI_INSTL_FLOOR((String)jsonObj.get("X_SWIFI_INSTL_FLOOR"));
    		wifiDb.setX_SWIFI_INSTL_TY((String)jsonObj.get("X_SWIFI_INSTL_TY"));
    		wifiDb.setX_SWIFI_INSTL_MBY((String)jsonObj.get("X_SWIFI_INSTL_MBY"));
    		wifiDb.setX_SWIFI_SVC_SE((String)jsonObj.get("X_SWIFI_SVC_SE"));
    		wifiDb.setX_SWIFI_CMCWR((String)jsonObj.get("X_SWIFI_CMCWR"));
    		wifiDb.setX_SWIFI_CNSTC_YEAR((String)jsonObj.get("X_SWIFI_CNSTC_YEAR"));
    		wifiDb.setX_SWIFI_INOUT_DOOR((String)jsonObj.get("X_SWIFI_INOUT_DOOR"));
    		wifiDb.setX_SWIFI_REMARS3((String)jsonObj.get("X_SWIFI_REMARS3"));
    		wifiDb.setLAT((String)jsonObj.get("LAT"));
    		wifiDb.setLNT((String)jsonObj.get("LNT"));
    	}
    }
    
	if(totalNum == 0) {
		break;
	}
}
db.dbInsert(wifiDb);
%>
<h1 style="text-align: center"><%= (long)jsonArr.get("list_total_count") %>개의 WIFI정보를 정상적으로 저장하였습니다.</h1>
<div>
	<a href="index.jsp">홈</a> <span>|</span>
	<a href="bookmark-group-list.jsp">위치 히스토리목록</a> <span>|</span>
	<a href="dbSave.jsp" id="searchWifi">Open API 와이파이 정보 가져오기</a>
	<a href="bookmark-list.jsp">즐겨 찾기 보기</a><span>|</span>
</div>
</body>
</html>