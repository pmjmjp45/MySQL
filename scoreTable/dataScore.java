package scoreTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dataScore {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");// JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");
		
		Statement k14_stmt = k14_conn.createStatement(); // 스테이트먼트 객체 생성
		
		for (int k14_i = 1; k14_i <= 1000; k14_i++) { // 1000명 학생 성적 입력
			int id = k14_i; // 아이디
			String name = "name" + k14_i; // 이름
			int korean = (int)(Math.random() * 100); // 국어 점수 랜덤 부여
			int english = (int)(Math.random() * 100); // 영어 점수 랜덤 부여
			int math = (int)(Math.random() * 100); // 수학 점수 랜덤 부여
			String QueryTxt; // 쿼리문 
			
			// 한줄씩 칼럼에 값 입력하는 쿼리문
			QueryTxt = String.format("insert into score ("
					+ "id, name, korean, english, math)"
					+ "values ("
					+ "'%s', '%s', '%s', '%s', '%s');",
					id, name, korean, english, math);
			k14_stmt.execute(QueryTxt); // 쿼리문 실행
			
			System.out.printf("[%d]번째 입력 완료: %s\n", id, QueryTxt); // 입력값 출력
		}
		
		// 열었던 반대 순서로 닫기
		k14_stmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
	}

}
