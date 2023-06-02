package scoreTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class printScore {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");

		Statement k14_stmt = k14_conn.createStatement(); // 스테이트먼트 객체 생성
	
		int k14_totalStudent = 1000; // 총 학생수
		int k14_numPerPage = 30; // 페이지당 학생수
		ResultSet k14_rset = null; // 리절트셋(개인) 
		ResultSet k14_rset2 = null; // 리절트셋(현재 페이지) 
		ResultSet k14_rset3 = null; // 리절트셋(누적)
		String k14_QueryTxt = null; // 쿼리문 위한 문자열 선언
		int k14_page = 0; // 페이지 위한 정수 선언
		
		for (int k14_i = 0; k14_i < k14_totalStudent; k14_i += 30) {// 페이지 터너
			k14_page++; // 페이지 카운터
			System.out.printf("**[%d]페이지************************************************\n", k14_page);//헤더 출력
			System.out.println(" 학번	    이름     국어   영어    수학      총점     평균\n"); 
			
			for (int k14_j = 1; k14_j <= k14_numPerPage; k14_j++) { // 개인 성적 
				k14_QueryTxt = "select *, (korean + english + math), (korean + english + math) / 3.0 from score where id = " + (k14_i + k14_j); // 쿼리문(개인)
				k14_rset = k14_stmt.executeQuery(k14_QueryTxt); // 리절트셋(개인)
				while (k14_rset.next()) { // 개인 성적 출력
					System.out.printf("%4d  %10s  %6d  %6d  %6d  %7d %9.2f\n", // 인덱스로 값 호출해서 출력
							k14_rset.getInt(1), k14_rset.getString(2), k14_rset.getInt(3), k14_rset.getInt(4), k14_rset.getInt(5),
							k14_rset.getInt(6), k14_rset.getDouble(7));
				}
			}
			k14_rset.close(); // 리절트셋(개인) 닫기
			
			////현재 페이지
			System.out.println("============================================================"); // 헤더 출력
			System.out.println("현재 페이지");
			
			k14_QueryTxt = "select sum(a.korean), sum(a.english), sum(a.math), sum(a.korean + a.english + a.math), sum((a.korean + a.english + a.math) / 3.0) "
					+ "from(select * from score limit " + k14_i + "," + k14_numPerPage + ") as a"; // 쿼리문(현재 페이지) - 합계
			k14_rset2 = k14_stmt.executeQuery(k14_QueryTxt); //리절트셋(현재 페이지) - 합계
			
			while(k14_rset2.next()) { // 현재 페이지 출력 - 합계
				System.out.printf("합계: %18d %7d %7d %8d %9.2f\n", 
						k14_rset2.getInt(1), k14_rset2.getInt(2), k14_rset2.getInt(3), k14_rset2.getInt(4), k14_rset2.getDouble(5));
			}
			
			k14_QueryTxt = "select avg(a.korean), avg(a.english), avg(a.math), avg(a.korean + a.english + a.math), avg((a.korean + a.english + a.math) / 3.0) "
					+ "from(select * from score limit " + k14_i + "," + k14_numPerPage + ") as a"; // 쿼리문(현재 페이지) - 평균
			k14_rset2 = k14_stmt.executeQuery(k14_QueryTxt); // 리절트셋(현재 페이지) - 평균
			
			while(k14_rset2.next()) { // 현재 페이지 출력 - 합계
				System.out.printf("평균: %18.2f %7.2f %7.2f %8.2f %9.2f\n", 
						k14_rset2.getDouble(1), k14_rset2.getDouble(2), k14_rset2.getDouble(3), k14_rset2.getDouble(4), k14_rset2.getDouble(5));
			}
			k14_rset2.close(); // 리절트셋 닫기
			
			//////누적페이지
			System.out.println("============================================================"); // 헤더 출력
			System.out.println("누적 페이지");
			k14_QueryTxt = "select sum(a.korean), sum(a.english), sum(a.math), sum(a.korean + a.english + a.math), sum((a.korean + a.english + a.math) / 3.0) "
					+ "from(select * from score limit " + 0 + "," + (k14_i + k14_numPerPage) + ") as a"; // 쿼리문(누적 페이지) - 합계
			k14_rset3 = k14_stmt.executeQuery(k14_QueryTxt); // 리절트셋(누적 페이지) - 합계
			
			while(k14_rset3.next()) { // 누적페이지 출력 - 합계
				System.out.printf("합계: %18d %7d %7d %8d %9.2f\n", 
						k14_rset3.getInt(1), k14_rset3.getInt(2), k14_rset3.getInt(3), k14_rset3.getInt(4), k14_rset3.getDouble(5));
			}
			
			k14_QueryTxt = "select avg(a.korean), avg(a.english), avg(a.math), avg(a.korean + a.english + a.math), avg((a.korean + a.english + a.math) / 3.0) "
					+ "from(select * from score limit " + 0 + "," + (k14_i + k14_numPerPage) + ") as a"; // 쿼리문(누적 페이지) - 평균
			k14_rset3 = k14_stmt.executeQuery(k14_QueryTxt); // 리절트셋(누적 페이지) - 평균
			
			while(k14_rset3.next()) { // 누적페이지 출력 - 평균
				System.out.printf("평균: %18.2f %7.2f %7.2f %8.2f %9.2f\n", 
						k14_rset3.getDouble(1), k14_rset3.getDouble(2), k14_rset3.getDouble(3), k14_rset3.getDouble(4), k14_rset3.getDouble(5));
			}
			k14_rset3.close(); // 리절트셋 닫기
		}
		k14_stmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
	}

}
