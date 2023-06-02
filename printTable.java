import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class printTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");

		Statement k14_stmt = k14_conn.createStatement(); // 스테이트먼트 객체 생성
		ResultSet k14_rset = k14_stmt.executeQuery("select * from examtable;"); // 리절트셋 객체 생성 -> 쿼리문 실행 -> rset에 저장
		
		System.out.println("  이름   학번  국어 영어 수학\n"); //헤더 출력
		while (k14_rset.next()) { // 모든 리절트셋
			System.out.printf("%4s  %6d  %3d  %3d  %3d \n", // 인덱스로 값 호출해서 출력
					k14_rset.getString(1), k14_rset.getInt(2), k14_rset.getInt(3), k14_rset.getInt(4), k14_rset.getInt(5));
		
		}
		// 열었던 반대 순서로 닫기
		k14_rset.close(); // 리절트셋 닫기
		k14_stmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
	}

}
