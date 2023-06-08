package localhost.wifi_place;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.Request;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
	public static String wifiDb (int startIdx, int endIdx) throws IOException, ParseException, SQLException {
		StringBuilder urlBuilder = new StringBuilder(
	        	"http://openapi.seoul.go.kr:8088/4a544171766c6565353450544a4143/json/TbPublicWifiInfo/"+ startIdx+ "/"
	        	+ endIdx + "/"
	        ); /*URL*/
	        urlBuilder.append("/" +  URLEncoder.encode("sample","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
	        urlBuilder.append("/" +  URLEncoder.encode("xml","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
	        urlBuilder.append("/" + URLEncoder.encode("CardSubwayStatsNew","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
	        urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
	        urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
	        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

	        // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
	        urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/

	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/xml");
	        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
	        BufferedReader rd;

	        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        
	        return sb.toString();
	}
	
	
    public static void main(String[] args) throws IOException, ParseException, SQLException {
//    	String jsonTotalNum = wifiDb(1, 2);
//        
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(jsonTotalNum); 
//        
//        // 아래 code로 이어짐
//        JSONObject jsonMain = (JSONObject)obj;
//        JSONObject jsonArr = (JSONObject)jsonMain.get("TbPublicWifiInfo");
//        System.out.println(jsonArr);
//        
//        
//        JSONParser parser2 = new JSONParser();
//        Object obj2;
//        JSONObject jsonMain2;
//        JSONObject jsonArr2;
//        JSONObject jsonMain3;
//        JSONArray jsonArr3;
//        WifiDb wifiDb = new WifiDb();
//        
//        long totalNum = (long)jsonArr.get("list_total_count");
//        int startIdx = 1;
//        int endIdx = 0;
//        while(true) {
//        	startIdx = endIdx+1;
//        	if(totalNum > 1000) {
//        		endIdx += 1000;
//        		totalNum -= 1000;
//        	} else if (totalNum <= 1000) {
//        		endIdx += totalNum;
//        		totalNum = 0;
//        	}
//        	
//        	String jsonData = wifiDb(startIdx, endIdx);
//        	
//        	obj2 = parser2.parse(jsonData);
//        	jsonMain2 = (JSONObject)obj2;
//            jsonArr2 = (JSONObject)jsonMain2.get("TbPublicWifiInfo");
//            
//            jsonMain3 = (JSONObject)jsonArr2;
//            jsonArr3 = (JSONArray)jsonMain3.get("row");
//            
//            if(jsonArr3.size() > 0) {
//            	for(int i = 0; i < jsonArr3.size(); i++) {
//            		JSONObject jsonObj = (JSONObject)jsonArr3.get(i);
//            		
//            		wifiDb.setX_SWIFI_MGR_NO((String)jsonObj.get("X_SWIFI_MGR_NO"));
//            		wifiDb.setX_SWIFI_WRDOFC((String)jsonObj.get("X_SWIFI_WRDOFC"));
//            		wifiDb.setX_SWIFI_MAIN_NM((String)jsonObj.get("X_SWIFI_MAIN_NM"));
//            		wifiDb.setX_SWIFI_ADRES1((String)jsonObj.get("X_SWIFI_ADRES1"));
//            		wifiDb.setX_SWIFI_ADRES2((String)jsonObj.get("X_SWIFI_ADRES2"));
//            		wifiDb.setX_SWIFI_INSTL_FLOOR((String)jsonObj.get("X_SWIFI_INSTL_FLOOR"));
//            		wifiDb.setX_SWIFI_INSTL_TY((String)jsonObj.get("X_SWIFI_INSTL_TY"));
//            		wifiDb.setX_SWIFI_INSTL_MBY((String)jsonObj.get("X_SWIFI_INSTL_MBY"));
//            		wifiDb.setX_SWIFI_SVC_SE((String)jsonObj.get("X_SWIFI_SVC_SE"));
//            		wifiDb.setX_SWIFI_CMCWR((String)jsonObj.get("X_SWIFI_CMCWR"));
//            		wifiDb.setX_SWIFI_CNSTC_YEAR((String)jsonObj.get("X_SWIFI_CNSTC_YEAR"));
//            		wifiDb.setX_SWIFI_INOUT_DOOR((String)jsonObj.get("X_SWIFI_INOUT_DOOR"));
//            		wifiDb.setX_SWIFI_REMARS3((String)jsonObj.get("X_SWIFI_REMARS3"));
//            		wifiDb.setLAT((String)jsonObj.get("LAT"));
//            		wifiDb.setLNT((String)jsonObj.get("LNT"));
//            	}
//            }
//            
//        	if(totalNum == 0) {
//        		break;
//        	}
//        }
        
        //Service db = new Service();
        //db.dbInsert(wifiDb);
        //db.dbReset();
        
        BookMark bm = new BookMark();
        ArrayList<String[]> arr = bm.BMList();
        
        for(String[] sArr : arr) {
        	for(String s : sArr) {
        		System.out.println(s);
        	}
        }
    }
}