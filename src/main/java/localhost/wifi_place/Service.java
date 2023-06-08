package localhost.wifi_place;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Service {
	// 가까운 wifi 20 보기
	public ArrayList<String[]> dbSelect(String lat, String lnt) throws SQLException {

		ArrayList<String[]> wifiData = new ArrayList<>();
		// db 접속
		// 1. ip(domain)
		// 2. port
		// 3. 계정
		// 4. 패스워드
		// 5. 인스턴스
		
		String dbUrl = "jdbc:mariadb://localhost:3306/wifi_place";
		String dbUserId = "hc";
		String dbPassword = "0427";

		// 1.드라이브 로드
		// 2.커넥션 객체 생성
		// 3.스테이트먼트 객체 생성
		// 4.쿼리 실행
		// 5.결과 수행
		// 6.객체 연결 해제(close)

		// 드라이브 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword); // 커넥션 객체 생성

			String sql = "select *, ACOS(COS(RADIANS(90-LAT))*COS(RADIANS(90-"+lnt+"))+SIN(RADIANS(90-LAT)) "
					+ " *SIN(RADIANS(90-"+lnt+"))*COS(RADIANS(LNT-"+lat+")))*6371 as distance "
					+ " from wifi "
					+ " order by distance "
					+ " limit 20;";
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);

			while (rs.next()) { // 결과 수행
				String[] data = new String[16];
				data[0] = rs.getString("distance");
				data[1] = rs.getString("X_SWIFI_MGR_NO");
				data[2] = rs.getString("X_SWIFI_WRDOFC");
				data[3] = rs.getString("X_SWIFI_MAIN_NM");
				data[4] = rs.getString("X_SWIFI_ADRES1");
				data[5] = rs.getString("X_SWIFI_ADRES2");
				data[6] = rs.getString("X_SWIFI_INSTL_FLOOR");
				data[7] = rs.getString("X_SWIFI_INSTL_TY");
				data[8] = rs.getString("X_SWIFI_INSTL_MBY");
				data[9] = rs.getString("X_SWIFI_SVC_SE");
				data[10] = rs.getString("X_SWIFI_CMCWR");
				data[11] = rs.getString("X_SWIFI_CNSTC_YEAR");
				data[12] = rs.getString("X_SWIFI_INOUT_DOOR");
				data[13] = rs.getString("X_SWIFI_REMARS3");
				data[14] = rs.getString("LAT");
				data[15] = rs.getString("LNT");

				wifiData.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 연결 해체
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (statement != null && !statement.isClosed()) {
					statement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return wifiData;
	}
	
	// 자세히 보기
	public String[] dbDetail(String wifiId) throws SQLException {

		String[] wifiData = new String[15];
		// db 접속
		// 1. ip(domain)
		// 2. port
		// 3. 계정
		// 4. 패스워드
		// 5. 인스턴스

		String dbUrl = "jdbc:mariadb://localhost:3306/wifi_place";
		String dbUserId = "hc";
		String dbPassword = "0427";

		// 1.드라이브 로드
		// 2.커넥션 객체 생성
		// 3.스테이트먼트 객체 생성
		// 4.쿼리 실행
		// 5.결과 수행
		// 6.객체 연결 해제(close)

		// 드라이브 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword); // 커넥션 객체 생성

			String sql = "select X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT "
					+ " from wifi where X_SWIFI_MGR_NO = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, wifiId);
			
			rs = preparedStatement.executeQuery();

			if (rs.next()) { // 결과 수행
				wifiData[0] = rs.getString("X_SWIFI_MGR_NO");
				wifiData[1] = rs.getString("X_SWIFI_WRDOFC");
				wifiData[2] = rs.getString("X_SWIFI_MAIN_NM");
				wifiData[3] = rs.getString("X_SWIFI_ADRES1");
				wifiData[4] = rs.getString("X_SWIFI_ADRES2");
				wifiData[5] = rs.getString("X_SWIFI_INSTL_FLOOR");
				wifiData[6] = rs.getString("X_SWIFI_INSTL_TY");
				wifiData[7] = rs.getString("X_SWIFI_INSTL_MBY");
				wifiData[8] = rs.getString("X_SWIFI_SVC_SE");
				wifiData[9] = rs.getString("X_SWIFI_CMCWR");
				wifiData[10] = rs.getString("X_SWIFI_CNSTC_YEAR");
				wifiData[11] = rs.getString("X_SWIFI_INOUT_DOOR");
				wifiData[12] = rs.getString("X_SWIFI_REMARS3");
				wifiData[13] = rs.getString("LAT");
				wifiData[14] = rs.getString("LNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 연결 해체
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return wifiData;
	}

	// wifi 정보 가져오기
	public void dbInsert(WifiDb wifi) {

		// db 접속
		// 1. ip(domain)
		// 2. port
		// 3. 계정
		// 4. 패스워드
		// 5. 인스턴스

		String dbUrl = "jdbc:mariadb://localhost:3306/wifi_place";
		String dbUserId = "hc";
		String dbPassword = "0427";

		// 1.드라이브 로드
		// 2.커넥션 객체 생성
		// 3.스테이트먼트 객체 생성
		// 4.쿼리 실행
		// 5.결과 수행
		// 6.객체 연결 해제(close)

		// 드라이브 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword); // 커넥션 객체 생성

			for (int i = 0; i < wifi.getX_SWIFI_MGR_NO().size(); i++) {
				String sql = "insert into wifi "
						+ "(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT) "
						+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				preparedStatement = connection.prepareStatement(sql); // 스테이트먼트 객체 생성
				preparedStatement.setString(1, wifi.getX_SWIFI_MGR_NO().get(i));
				preparedStatement.setString(2, wifi.getX_SWIFI_WRDOFC().get(i));
				preparedStatement.setString(3, wifi.getX_SWIFI_MAIN_NM().get(i));
				preparedStatement.setString(4, wifi.getX_SWIFI_ADRES1().get(i));
				preparedStatement.setString(5, wifi.getX_SWIFI_ADRES2().get(i));
				preparedStatement.setString(6, wifi.getX_SWIFI_INSTL_FLOOR().get(i));
				preparedStatement.setString(7, wifi.getX_SWIFI_INSTL_TY().get(i));
				preparedStatement.setString(8, wifi.getX_SWIFI_INSTL_MBY().get(i));
				preparedStatement.setString(9, wifi.getX_SWIFI_SVC_SE().get(i));
				preparedStatement.setString(10, wifi.getX_SWIFI_CMCWR().get(i));
				preparedStatement.setString(11, wifi.getX_SWIFI_CNSTC_YEAR().get(i));
				preparedStatement.setString(12, wifi.getX_SWIFI_INOUT_DOOR().get(i));
				preparedStatement.setString(13, wifi.getX_SWIFI_REMARS3().get(i));
				preparedStatement.setString(14, wifi.getLAT().get(i));
				preparedStatement.setString(15, wifi.getLNT().get(i));

				int affected = preparedStatement.executeUpdate(); // 쿼리 실행

				if (affected > 0) {
					System.out.println("저장 성공");
				} else {
					System.out.println("저장 실패");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 연결 해체
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	// wifi 정보 초기화
	public void dbReset() {
		// db 접속
		// 1. ip(domain)
		// 2. port
		// 3. 계정
		// 4. 패스워드
		// 5. 인스턴스

		String dbUrl = "jdbc:mariadb://localhost:3306/wifi_place";
		String dbUserId = "hc";
		String dbPassword = "0427";

		// 1.드라이브 로드
		// 2.커넥션 객체 생성
		// 3.스테이트먼트 객체 생성
		// 4.쿼리 실행
		// 5.결과 수행
		// 6.객체 연결 해제(close)

		// 드라이브 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		String mEmail = "dd@naver.com";

		try {
			connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword); // 커넥션 객체 생성

			String sql = "truncate wifi;";
			
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 객체 연결 해체
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (statement != null && !statement.isClosed()) {
					statement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}