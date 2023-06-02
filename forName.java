import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class forName {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");

		Statement k14_stmt = k14_conn.createStatement(); // 스테이트먼트 객체 생성
		ResultSet k14_rset = k14_stmt.executeQuery("show databases;"); // 리절트셋 객체 생성 -> 쿼리문 실행 -> rset에 저장
	
		while (k14_rset.next()) { // rset 모든 값
			System.out.println("값: " + k14_rset.getString(1)); // .getString(1)로 1번째열 문자열로 반환, 출력
		}
		// 열었던 반대 순서로 닫기
		k14_rset.close(); // 리절트셋 닫기
		k14_stmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
	}
	
}
