package localhost.wifi_place;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookMark {
	// 북마크 리스트 보기
	public ArrayList<String[]> BMList() throws SQLException {

		ArrayList<String[]> BMList = new ArrayList<>();
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

			String sql = "select * "
					+ " from bookmark"
					+ " order by NUMBER;";
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);

			while (rs.next()) { // 결과 수행
				String[] data = new String[5];
				data[0] = rs.getString("BOOKMARK_ID");
				data[1] = rs.getString("BOOKMARK_NAME");
				data[2] = rs.getString("NUMBER");
				data[3] = rs.getString("REG_DT");
				data[4] = rs.getString("EDIT_DT");

				BMList.add(data);
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
		return BMList;
	}
	
	// 자세히 보기
	public String[] BMDetail(String id) throws SQLException {

		String[] BMDetail = new String[2];
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

			String sql = "select BOOKMARK_NAME, NUMBER "
					+ " from bookmark where BOOKMARK_ID = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(id));
			
			rs = preparedStatement.executeQuery();

			if (rs.next()) { // 결과 수행
				BMDetail[0] = rs.getString("BOOKMARK_NAME");
				BMDetail[1] = rs.getString("NUMBER");
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
		return BMDetail;
	}

	// 북마크 추가 
	public void BMAdd(String name, String number) {

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

			String sql = "insert into bookmark "
					+ " (BOOKMARK_NAME, NUMBER, REG_DT, EDIT_DT) "
					+ " values (?, ?, NOW(), null);";
			preparedStatement = connection.prepareStatement(sql); // 스테이트먼트 객체 생성
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, Integer.parseInt(number));

			int affected = preparedStatement.executeUpdate(); // 쿼리 실행

			if (affected > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
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

	// 북마크 수정
	public void BMEdit(String id, String name, String number) {
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

			String sql = "update bookmark set BOOKMARK_NAME = ?, NUMBER = ?, EDIT_DT = now() where BOOKMARK_ID = ?;";
			preparedStatement = connection.prepareStatement(sql); // 스테이트먼트 객체 생성
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, Integer.parseInt(number));
			preparedStatement.setInt(3, Integer.parseInt(id));

			int affected = preparedStatement.executeUpdate(); // 쿼리 실행

			if (affected > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
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
	
	// 북마크 삭제
	public void BMDelete(String id) {
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

			String sql = "delete from bookmark where BOOKMARK_ID = ?;";
			preparedStatement = connection.prepareStatement(sql); // 스테이트먼트 객체 생성
			preparedStatement.setString(1, id);
			
			int affected = preparedStatement.executeUpdate(); // 쿼리 실행

			if (affected > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
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



	//북마크 리스트 보기
	public ArrayList<String[]> favList() throws SQLException {

		ArrayList<String[]> favList = new ArrayList<>();
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

			String sql = "select *"
					+ " from favorites;";
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);

			while (rs.next()) { // 결과 수행
				String[] data = new String[4];
				data[0] = rs.getString("FAVORITES_ID");
				data[1] = rs.getString("BOOKMARK_NAME");
				data[2] = rs.getString("X_SWIFI_MAIN_NM");
				data[3] = rs.getString("REG_DT");

				favList.add(data);
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
		return favList;
	}
	
	// 자세히 보기
	public String[] favDetail(String id) throws SQLException {

		String[] favDetail = new String[2];
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

			String sql = "select BOOKMARK_NAME, X_SWIFI_MAIN_NM "
					+ " from favorites where FAVORITES_ID = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(id));
			
			rs = preparedStatement.executeQuery();

			if (rs.next()) { // 결과 수행
				favDetail[0] = rs.getString("BOOKMARK_NAME");
				favDetail[1] = rs.getString("X_SWIFI_MAIN_NM");
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
		return favDetail;
	}

	// 즐겨찾기 추가
	public void favAdd(String BMName, String wifiName) {
	
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
	
			String sql = "insert into favorites "
					+ " (BOOKMARK_NAME, X_SWIFI_MAIN_NM, REG_DT) "
					+ " values (?, ?, NOW());";
			preparedStatement = connection.prepareStatement(sql); // 스테이트먼트 객체 생성
			preparedStatement.setString(1, BMName);
			preparedStatement.setString(2, wifiName);
	
			int affected = preparedStatement.executeUpdate(); // 쿼리 실행
	
			if (affected > 0) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
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
	
	// 북마크 삭제
	public void favDelete(String id) {
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

			String sql = "delete from favorites where FAVORITES_ID = ?;";
			preparedStatement = connection.prepareStatement(sql); // 스테이트먼트 객체 생성
			preparedStatement.setString(1, id);
			
			int affected = preparedStatement.executeUpdate(); // 쿼리 실행

			if (affected > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
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

}