package cctv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class distanceCCTV {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");// JDBC 드라이버 로딩
		// 데이터베이스와 연결
		Connection k14_conn = DriverManager.getConnection("jdbc:mysql://192.168.23.63:33060/kopo14", "root", "0405");
		
		Statement k14_stmt = k14_conn.createStatement(); // 스테이트먼트 객체 생성
		
		double k14_lat = 37.3860521, k14_lng = 127.1214038; // 현재 위치 위도와 경도 지정
		
		String k14_QueryTxt; // 쿼리문 저장할 문자열 선언
		//위도, 경도, 피타고라스의 식을 이용하여 가장 MIN인 곳 select
//		k14_QueryTxt = String.format("select * from cctv where "
//				+ "SQRT(POWER(latitude - %f, 2) + POWER(longitude - %f, 2)) = "
//				+ "(select MIN(SQRT(POWER(latitude - %f, 2) + POWER(longitude - %f, 2))) from cctv);",
//				k14_lat, k14_lng, k14_lat, k14_lng);
		
//		k14_QueryTxt = "select * from cctv where address_road LIKE '경기도 수원시%';"; // where절로 검색 조건 설정 
		
		k14_QueryTxt = "select * from cctv where num_camera = (select MAX(num_camera) from cctv);"; // where절로 검색 조건 설정
		
		ResultSet k14_rset = k14_stmt.executeQuery(k14_QueryTxt); // 리절트셋 생성해서 쿼리문 실행
		int k14_cnt = 0; // 카운터 선언
		
		while (k14_rset.next()) { // 리절트셋 전체
			// 값 문자열 형식으로 가져와서 출력
			System.out.printf("**(%d)********************************\n", k14_cnt++);
			System.out.printf("관리기관명       :%s\n",k14_rset.getString(2));
			System.out.printf("소재지도로명주소 :%s\n",k14_rset.getString(3));
			System.out.printf("소재지지번주소   :%s\n",k14_rset.getString(4));
			System.out.printf("설치목적구분     :%s\n",k14_rset.getString(5));
			System.out.printf("카메라대수       :%s\n",k14_rset.getString(6));
			System.out.printf("카메라화소       :%s\n",k14_rset.getString(7));
			System.out.printf("촬영방면정보     :%s\n",k14_rset.getString(8));
			System.out.printf("보관일수         :%s\n",k14_rset.getString(9));
			System.out.printf("설치년월         :%s\n",k14_rset.getString(10));
			System.out.printf("관리가관전화번호 :%s\n",k14_rset.getString(11));
			System.out.printf("위도             :%s\n",k14_rset.getString(12));
			System.out.printf("경도             :%s\n",k14_rset.getString(13));
			System.out.printf("데이터기준일자   :%s\n",k14_rset.getString(14));
			
		}
		// 열었던 반대 순서로 닫기
		k14_rset.close(); // 리절트셋 닫기
		k14_stmt.close(); // 스테이트먼트 닫기
		k14_conn.close(); // 커넥션 닫기
	}

}