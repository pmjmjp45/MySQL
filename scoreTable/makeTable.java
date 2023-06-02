package scoreTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class makeTable {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");// JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");
		
		Statement k14_stmt = k14_conn.createStatement(); // 스테이트먼트 객체 생성
		// 테이블 만드는 쿼리문 / result set이 필요없는 실행문 .execute()
		k14_stmt.execute("create table score("
				+ "id int not null primary key,"
				+ "name varchar(20),"
				+ "korean int,"
				+ "english int,"
				+ "math int"
				+ ")DEFAULT CHARSET = utf8;");
		
		// 열었던 반대 순서로 닫기
		k14_stmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
	}

}